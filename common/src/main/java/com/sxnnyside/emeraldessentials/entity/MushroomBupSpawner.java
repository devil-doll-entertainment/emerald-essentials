package com.sxnnyside.emeraldessentials.entity;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public final class MushroomBupSpawner {
  public static final TagKey<net.minecraft.world.level.block.Block> MOD_FLOWERS =
      TagKey.create(
          net.minecraft.core.registries.Registries.BLOCK,
          ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, "mod_flowers"));

  private static final int ATTEMPT_CHANCE = 800;
  private static final int SEARCH_RADIUS_XZ = 16;
  private static final int SEARCH_RADIUS_Y = 6;
  private static final int FLOWER_CHECK_RADIUS = 5;
  private static final int MAX_NEARBY_BUPS = 3;

  private MushroomBupSpawner() {}

  public static void onPlayerTick(Player player) {
    if (!(player.level() instanceof ServerLevel level) || player.level().isClientSide()) {
      return;
    }

    RandomSource random = level.random;
    if (random.nextInt(ATTEMPT_CHANCE) != 0) {
      return;
    }

    BlockPos.MutableBlockPos candidate = new BlockPos.MutableBlockPos();
    candidate.setWithOffset(
        player.blockPosition(),
        random.nextInt(SEARCH_RADIUS_XZ * 2 + 1) - SEARCH_RADIUS_XZ,
        random.nextInt(SEARCH_RADIUS_Y * 2 + 1) - SEARCH_RADIUS_Y,
        random.nextInt(SEARCH_RADIUS_XZ * 2 + 1) - SEARCH_RADIUS_XZ);

    if (!hasNearbyModFlower(level, candidate)) {
      return;
    }

    BlockPos spawnPos =
        level
            .getHeightmapPos(
                net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                candidate)
            .below();
    if (!level.getBlockState(spawnPos.above()).isAir()
        || !level.getBlockState(spawnPos).entityCanStandOn(level, spawnPos, null)) {
      return;
    }

    AABB nearby = new AABB(spawnPos).inflate(SEARCH_RADIUS_XZ);
    if (level.getEntitiesOfClass(MushroomBup.class, nearby).size() >= MAX_NEARBY_BUPS) {
      return;
    }

    MushroomBup bup = ModEntities.MUSHROOM_BUP.create(level);
    if (bup == null) {
      return;
    }
    bup.moveTo(spawnPos.getX() + 0.5D, spawnPos.getY() + 1.0D, spawnPos.getZ() + 0.5D, 0.0F, 0.0F);
    level.addFreshEntity(bup);
  }

  private static boolean hasNearbyModFlower(ServerLevel level, BlockPos origin) {
    BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
    for (int x = -FLOWER_CHECK_RADIUS; x <= FLOWER_CHECK_RADIUS; x++) {
      for (int y = -3; y <= 3; y++) {
        for (int z = -FLOWER_CHECK_RADIUS; z <= FLOWER_CHECK_RADIUS; z++) {
          cursor.setWithOffset(origin, x, y, z);
          BlockState state = level.getBlockState(cursor);
          if (state.is(MOD_FLOWERS)) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
