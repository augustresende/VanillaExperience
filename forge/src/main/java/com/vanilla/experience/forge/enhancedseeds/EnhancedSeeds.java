package com.vanilla.experience.forge.enhancedseeds;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EnhancedSeeds {

    public EnhancedSeeds(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onDirtGrassClick(RightClickBlock e) {
        World world = e.getWorld();
        ItemStack stack = e.getItemStack();
        if(!stack.getItem().equals(Items.WHEAT_SEEDS)) return;

        BlockPos blockPos = e.getPos();
        BlockState blockState = world.getBlockState(blockPos);
        Block currentBlock = blockState.getBlock();
        PlayerEntity player = e.getPlayer();

        if(currentBlock.equals(Blocks.DIRT)) {
            world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState());
            if(!player.isCreative()) stack.shrink(1);
            e.setResult(Event.Result.ALLOW);
        } else if(currentBlock.equals(Blocks.GRASS_BLOCK)) {
            BlockPos upperPos = blockPos.up();
            Block upperBlock = world.getBlockState(upperPos).getBlock();
            if(upperBlock.equals(Blocks.AIR)) {
                world.setBlockState(upperPos, Blocks.GRASS.getDefaultState());
                if(!player.isCreative()) stack.shrink(1);
                e.setResult(Event.Result.ALLOW);
            } else if(upperBlock.equals(Blocks.GRASS)) {
                if(upgrade(world, blockPos.up())) {
                    if(!player.isCreative()) stack.shrink(1);
                    e.setResult(Event.Result.ALLOW);
                }
            }
        } else if(currentBlock.equals(Blocks.GRASS)) {
            if(upgrade(world, blockPos)) {
                if(!player.isCreative()) stack.shrink(1);
                e.setResult(Event.Result.ALLOW);
            }

        }
    }

    public boolean upgrade(World world, BlockPos pos) {
        DoublePlantBlock blockDoublePlant = (DoublePlantBlock)Blocks.TALL_GRASS;
        if(blockDoublePlant.getDefaultState().isValidPosition(world, pos) && world.isAirBlock(pos.up())) {
            blockDoublePlant.placeAt( world, pos, 2);
            return true;
        }
        return false;
    }
}
