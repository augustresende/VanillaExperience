package com.vanilla.experience.fabric.enhancedseeds;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnhancedSeeds implements UseBlockCallback {

    public EnhancedSeeds(){
        UseBlockCallback.EVENT.register(this);
    }

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult block) {
        ItemStack stack = player.getStackInHand(hand);

        if(stack.getItem().equals(Items.WHEAT_SEEDS)){
            BlockPos blockPos = block.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            Block currentBlock = blockState.getBlock();

            if(currentBlock.equals(Blocks.DIRT)) {
                world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState());
                if(!player.isCreative()) stack.decrement(1);
                return ActionResult.SUCCESS;
            } else if(currentBlock.equals(Blocks.GRASS_BLOCK)) {
                BlockPos upperPos = blockPos.up();
                Block upperBlock = world.getBlockState(upperPos).getBlock();
                if(upperBlock.equals(Blocks.AIR)) {
                    world.setBlockState(upperPos, Blocks.GRASS.getDefaultState());
                    if(!player.isCreative()) stack.decrement(1);
                    return ActionResult.SUCCESS;
                } else if(upperBlock.equals(Blocks.GRASS)) {
                    if(upgrade(world, upperPos)) {
                        if(!player.isCreative()) stack.decrement(1);
                        return ActionResult.SUCCESS;
                    }
                }
            } else if(currentBlock.equals(Blocks.GRASS)) {
                if(upgrade(world, blockPos)) {
                    if(!player.isCreative()) stack.decrement(1);
                    return ActionResult.SUCCESS;
                }
            }

        }
        return ActionResult.PASS;
    }

    public boolean upgrade(World world, BlockPos pos) {
        TallPlantBlock blockDoublePlant = (TallPlantBlock) Blocks.TALL_GRASS;

        if(world.isAir(pos.up())) {
            blockDoublePlant.placeAt(world, pos, 2);
            return true;
        }
        return false;
    }
}
