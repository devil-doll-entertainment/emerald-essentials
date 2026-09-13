package com.sxnnyside.emeraldessentials.entity;

import com.sxnnyside.emeraldessentials.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class AmethystScarabSpawner {
  private static final float SAND_SPAWN_CHANCE = 0.005F;
  private static final float AMETHYST_SPAWN_CHANCE = 0.16F;

  private AmethystScarabSpawner() {}

  public static void onBlockBreak(Level level, BlockPos pos, BlockState state) {
    if (!(level instanceof ServerLevel serverLevel)) {
      return;
    }

    float chance;
    if (isAmethystSource(state)) {
      chance = AMETHYST_SPAWN_CHANCE;
    } else if (state.is(BlockTags.SAND)) {
      chance = SAND_SPAWN_CHANCE;
    } else {
      return;
    }

    if (serverLevel.random.nextFloat() >= chance) {
      return;
    }

    AmethystScarab scarab = ModEntities.AMETHYST_SCARAB.create(serverLevel);
    if (scarab == null) {
      return;
    }
    scarab.moveTo(pos.getX() + 0.5D, pos.getY() + 0.2D, pos.getZ() + 0.5D, 0.0F, 0.0F);
    serverLevel.addFreshEntity(scarab);
  }

  private static boolean isAmethystSource(BlockState state) {
    return state.is(Blocks.AMETHYST_BLOCK)
        || state.is(Blocks.BUDDING_AMETHYST)
        || state.is(Blocks.AMETHYST_CLUSTER)
        || state.is(Blocks.LARGE_AMETHYST_BUD)
        || state.is(Blocks.MEDIUM_AMETHYST_BUD)
        || state.is(Blocks.SMALL_AMETHYST_BUD);
  }
}
