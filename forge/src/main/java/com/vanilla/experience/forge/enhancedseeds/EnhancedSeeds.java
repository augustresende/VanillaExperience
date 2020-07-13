package com.vanilla.experience.forge.enhancedseeds;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
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
        ItemStack hand = e.getItemStack();
        if(!hand.getItem().equals(Items.WHEAT_SEEDS)) return;

        BlockPos blockPos = e.getPos();
        Block currentBlock = world.getBlockState(blockPos).getBlock();
        PlayerEntity player = e.getPlayer();
        if(currentBlock.equals(Blocks.DIRT)) {
            world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState());
            if(!player.isCreative())
                hand.shrink(1);
        } else if(currentBlock.equals(Blocks.GRASS_BLOCK)) {
            BlockPos upperPos = blockPos.up();
            Block upperBlock = world.getBlockState(upperPos).getBlock();
            if(upperBlock.equals(Blocks.AIR)) {
                world.setBlockState(upperPos, Blocks.GRASS.getDefaultState());
                if(!player.isCreative())
                    hand.shrink(1);
            } else if(upperBlock.equals(Blocks.GRASS)) {
                if(upgrade(world, blockPos.up()) && !player.isCreative())
                    hand.shrink(1);
            }
        } else if(currentBlock.equals(Blocks.GRASS)) {
            if(upgrade(world, blockPos) && !player.isCreative())
                hand.shrink(1);
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
