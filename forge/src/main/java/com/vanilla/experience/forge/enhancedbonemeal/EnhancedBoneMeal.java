package com.vanilla.experience.forge.enhancedbonemeal;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Collections;
import java.util.Iterator;

@EventBusSubscriber
public class EnhancedBoneMeal {

    public EnhancedBoneMeal(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBoneMeal(BonemealEvent e) {

        World world = e.getWorld();

        PlayerEntity player = e.getPlayer();
        ItemStack hand = null;
        if(player.getHeldItemMainhand().getItem().equals(Items.BONE_MEAL)) {
            hand = player.getHeldItemMainhand();
        } else if(player.getHeldItemOffhand().getItem().equals(Items.BONE_MEAL)) {
            hand = player.getHeldItemOffhand();
        }
        if(hand == null) return;

        BlockPos blockPos = e.getPos();
        BlockState blockState = world.getBlockState(blockPos);
        Block currentBlock = blockState.getBlock();

        if(currentBlock.equals(Blocks.CACTUS) || currentBlock.equals((Blocks.SUGAR_CANE))) {
            for(int y = blockPos.getY(); y <= 256; y++) {
                BlockPos upperPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                Block upperBlock = world.getBlockState(upperPos).getBlock();
                if(upperBlock.equals(Blocks.AIR)) {
                    world.setBlockState(upperPos, currentBlock.getDefaultState());
                    world.playEvent(2005, upperPos, 0);
                    world.playEvent(2005, upperPos.up(), 0);
                    if(!player.isCreative()) hand.shrink(1);
                    e.setResult(Event.Result.ALLOW);
                    return;
                }
            }
        }
        if(currentBlock.equals(Blocks.VINE)) {
            for(int y = blockPos.getY(); y > 0; y--) {
                BlockPos downPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                Block downBlock = world.getBlockState(downPos).getBlock();
                if(downBlock.equals(Blocks.AIR)) {
                    world.setBlockState(downPos, currentBlock.getDefaultState());
                    world.playEvent(2005, downPos, 0);
                    world.playEvent(2005, downPos.down(), 0);
                    if(!player.isCreative()) hand.shrink(1);
                    e.setResult(Event.Result.ALLOW);
                    return;
                }
            }
        }
        if(currentBlock.equals(Blocks.NETHER_WART)) {
            Iterator<Property<?>> itp = Collections.unmodifiableCollection(blockState.getValues().keySet()).iterator();

            while (itp.hasNext()) {
                Property<?> property = itp.next();
                if (property instanceof IntegerProperty) {
                    IntegerProperty prop = (IntegerProperty)property;
                    String name = prop.getName();
                    if (name.equals("age")) {
                        Comparable<?> cv = blockState.getValues().get(property);
                        int value = Integer.parseUnsignedInt(cv.toString());
                        int max = Collections.<Integer>max(prop.getAllowedValues());
                        if (value == max) break;
                        world.setBlockState(blockPos, world.getBlockState(blockPos).func_235896_a_(property));
                        if (!player.isCreative()) hand.shrink(1);
                        world.playEvent(2005, blockPos, 0);
                        e.setResult(Event.Result.ALLOW);
                        return;
                    }
                }
            }
        }
    }
}

