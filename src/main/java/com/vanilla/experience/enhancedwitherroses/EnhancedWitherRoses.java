package com.vanilla.experience.enhancedwitherroses;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.WitherRoseBlock;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnhancedWitherRoses extends WitherRoseBlock {
    private static final Logger LOGGER = LogManager.getLogger();

    public EnhancedWitherRoses(Properties properties) {
        super(Effects.WITHER, properties);
    }

    @Override
    public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, EntitySpawnPlacementRegistry.PlacementType type, EntityType<?> entityType) {
        return true;
    }
}
