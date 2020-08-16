package com.vanilla.experience.forge.enhancedkelp;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Collections;
import java.util.Iterator;

@EventBusSubscriber
public class EnhancedKelp {

    public EnhancedKelp(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKelpClick(PlayerInteractEvent.RightClickBlock e) {
        ItemStack stack = e.getItemStack();

        if(stack.getItem().equals(Items.KELP)){
            World world = e.getWorld();

            BlockPos blockPos = e.getPos();
            BlockState blockState = world.getBlockState(blockPos);
            Block currentBlock = blockState.getBlock();
            PlayerEntity player = e.getPlayer();

            if (BoneMealItem.applyBonemeal(stack, world, blockPos, player)) {
                world.playEvent(2005, blockPos, 0);
            } else if(currentBlock.equals(Blocks.CACTUS) || currentBlock.equals((Blocks.SUGAR_CANE))) {
                for(int y = blockPos.getY(); y <= 256; y++) {
                    BlockPos upperPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block upperBlock = world.getBlockState(upperPos).getBlock();
                    if(upperBlock.equals(Blocks.AIR)) {
                        world.setBlockState(upperPos, currentBlock.getDefaultState());
                        world.playEvent(2005, upperPos, 0);
                        world.playEvent(2005, upperPos.up(), 0);
                        if(!player.isCreative()) stack.shrink(1);
                        e.setResult(Event.Result.ALLOW);
                        return;
                    }
                }
            } else if(currentBlock.equals(Blocks.VINE)) {
                for(int y = blockPos.getY(); y > 0; y--) {
                    BlockPos downPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block downBlock = world.getBlockState(downPos).getBlock();
                    if(downBlock.equals(Blocks.AIR)) {
                        world.setBlockState(downPos, currentBlock.getDefaultState());
                        world.playEvent(2005, downPos, 0);
                        world.playEvent(2005, downPos.down(), 0);
                        if(!player.isCreative()) stack.shrink(1);
                        e.setResult(Event.Result.ALLOW);
                        return;
                    }
                }
            } else if(currentBlock.equals(Blocks.NETHER_WART)) {
                Iterator<Property<?>> itp = Collections.unmodifiableCollection(blockState.getValues().keySet()).iterator();

                while (itp.hasNext()) {
                    Property<?> property = itp.next();
                    if(property instanceof IntegerProperty) {
                        IntegerProperty prop = (IntegerProperty)property;
                        String name = prop.getName();
                        if (name.equals("age")) {
                            Comparable<?> cv = blockState.get(property);
                            int value = Integer.parseUnsignedInt(cv.toString());
                            int max = Collections.<Integer>max(prop.getAllowedValues());
                            if (value == max) break;
                            world.setBlockState(blockPos, world.getBlockState(blockPos).func_235896_a_(property));
                            if (!player.isCreative()) stack.shrink(1);
                            world.playEvent(2005, blockPos, 0);
                            e.setResult(Event.Result.ALLOW);
                            return;
                        }
                    }
                }
            }

        }
        return;
    }
}
