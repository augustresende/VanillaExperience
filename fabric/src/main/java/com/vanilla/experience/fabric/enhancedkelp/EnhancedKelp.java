package com.vanilla.experience.fabric.enhancedkelp;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Iterator;

public class EnhancedKelp implements UseBlockCallback {

    public EnhancedKelp(){
        UseBlockCallback.EVENT.register(this);
    }

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult block) {
        ItemStack stack = player.getStackInHand(hand);

        if(stack.getItem().equals(Items.KELP)){

            BlockPos blockPos = block.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            Block currentBlock = blockState.getBlock();

            if (BoneMealItem.useOnFertilizable(stack, world, blockPos) || BoneMealItem.useOnGround(stack, world, blockPos, null)) {
                world.syncWorldEvent(2005, blockPos, 0);
            } else if(currentBlock.equals(Blocks.CACTUS) || currentBlock.equals((Blocks.SUGAR_CANE))) {
                for(int y = blockPos.getY(); y <= 256; y++) {
                    BlockPos upperPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block upperBlock = world.getBlockState(upperPos).getBlock();
                    if(upperBlock.equals(Blocks.AIR)) {
                        world.setBlockState(upperPos, currentBlock.getDefaultState());
                        world.syncWorldEvent(2005, upperPos, 0);
                        world.syncWorldEvent(2005, upperPos.up(), 0);
                        if(!player.isCreative()) stack.decrement(1);
                        return ActionResult.SUCCESS;
                    }
                }
            } else if(currentBlock.equals(Blocks.VINE)) {
                for(int y = blockPos.getY(); y > 0; y--) {
                    BlockPos downPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block downBlock = world.getBlockState(downPos).getBlock();
                    if(downBlock.equals(Blocks.AIR)) {
                        world.setBlockState(downPos, currentBlock.getDefaultState());
                        world.syncWorldEvent(2005, downPos, 0);
                        world.syncWorldEvent(2005, downPos.down(), 0);
                        if(!player.isCreative()) stack.decrement(1);
                        return ActionResult.SUCCESS;
                    }
                }
            } else if(currentBlock.equals(Blocks.NETHER_WART)) {
                Iterator<Property<?>> itp = Collections.unmodifiableCollection(blockState.getProperties()).iterator();

                while (itp.hasNext()) {
                    Property<?> property = itp.next();
                    if(property instanceof IntProperty) {
                        IntProperty prop = (IntProperty)property;
                        String name = prop.getName();
                        if (name.equals("age")) {
                            Comparable<?> cv = blockState.get(property);
                            int value = Integer.parseUnsignedInt(cv.toString());
                            int max = Collections.<Integer>max(prop.getValues());
                            if (value == max) break;
                            world.setBlockState(blockPos, world.getBlockState(blockPos).cycle(property));
                            if (!player.isCreative()) stack.decrement(1);
                            world.syncWorldEvent(2005, blockPos, 0);
                            return ActionResult.SUCCESS;
                        }
                    }
                }
            }

        }
        return ActionResult.PASS;
    }
}
