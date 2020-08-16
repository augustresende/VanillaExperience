package com.vanilla.experience.fabric.enhancedbonemeal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;

import java.util.Collections;
import java.util.Iterator;

public class EnhancedBoneMealDispenserBehaviour extends FallibleItemDispenserBehavior {

    public EnhancedBoneMealDispenserBehaviour() {
        DispenserBlock.registerBehavior(Items.BONE_MEAL, this);
    }

    protected ItemStack dispenseSilently(BlockPointer block, ItemStack stack) {
        this.setSuccess(true);
        ServerWorld world = (ServerWorld) block.getWorld();
        BlockPos blockPos = block.getBlockPos().offset(block.getBlockState().get(DispenserBlock.FACING));
        if (BoneMealItem.useOnFertilizable(stack, world, blockPos) || BoneMealItem.useOnGround(stack, world, blockPos, null)) {
            world.syncWorldEvent(2005, blockPos, 0);
        } else {
            BlockState blockState = world.getBlockState(blockPos);
            Block currentBlock = blockState.getBlock();

            if(currentBlock.equals(Blocks.SUGAR_CANE) || currentBlock.equals(Blocks.CACTUS)) {
                for(int y = blockPos.getY(); y <= 256; y++) {
                    BlockPos upperPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block upperBlock = world.getBlockState(upperPos).getBlock();
                    if(upperBlock.equals(Blocks.AIR)) {
                        world.setBlockState(upperPos, blockState.getBlock().getDefaultState());
                        world.syncWorldEvent(2005, upperPos, 0);
                        world.syncWorldEvent(2005, upperPos.up(), 0);
                        stack.decrement(1);
                        break;
                    }
                }
            } else if(blockState.getBlock().equals(Blocks.VINE)) {
                for(int y = blockPos.getY(); y > 0; y--) {
                    BlockPos downPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block downBlock = world.getBlockState(downPos).getBlock();
                    if (downBlock.equals(Blocks.AIR)) {
                        world.setBlockState(downPos, blockState.getBlock().getDefaultState());
                        world.syncWorldEvent(2005, downPos, 0);
                        world.syncWorldEvent(2005, downPos.down(), 0);
                        stack.decrement(1);
                        break;
                    }
                }
            } else if(blockState.getBlock().equals(Blocks.NETHER_WART)) {
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
                            world.syncWorldEvent(2005, blockPos, 0);
                            stack.decrement(1);
                            break;
                        }
                    }
                }
            } else {
                this.setSuccess(false);
            }
        }
        return stack;
    }
}