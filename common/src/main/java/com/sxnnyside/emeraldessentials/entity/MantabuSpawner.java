package com.sxnnyside.emeraldessentials.entity;

import com.sxnnyside.emeraldessentials.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public final class MantabuSpawner {
  private static final int ATTEMPT_CHANCE = 900;
  private static final int SEARCH_RADIUS_XZ = 16;
  private static final int SEARCH_RADIUS_Y = 10;
  private static final int SOURCE_CHECK_RADIUS = 6;
  private static final int MAX_NEARBY = 2;
  private static final int CAVE_MAX_LIGHT = 7;

  private MantabuSpawner() {}

  public static void onPlayerTick(Player player) {
    if (!(player.level() instanceof ServerLevel level)) {
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

    if (level.canSeeSky(candidate) || level.getMaxLocalRawBrightness(candidate) > CAVE_MAX_LIGHT) {
      return;
    }

    if (!level.getBlockState(candidate).isAir()
        || !level.getBlockState(candidate.above()).isAir()) {
      return;
    }

    if (!hasNearbySource(level, candidate)) {
      return;
    }

    AABB nearby = new AABB(candidate).inflate(SEARCH_RADIUS_XZ);
    if (level.getEntitiesOfClass(Mantabu.class, nearby).size() >= MAX_NEARBY) {
      return;
    }

    Mantabu mantabu = ModEntities.MANTABU.create(level);
    if (mantabu == null) {
      return;
    }
    mantabu.moveTo(
        candidate.getX() + 0.5D, candidate.getY() + 0.5D, candidate.getZ() + 0.5D, 0.0F, 0.0F);
    level.addFreshEntity(mantabu);
  }

  private static boolean hasNearbySource(ServerLevel level, BlockPos origin) {
    if (!level
        .getEntitiesOfClass(GlowSquid.class, new AABB(origin).inflate(SOURCE_CHECK_RADIUS))
        .isEmpty()) {
      return true;
    }

    BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
    for (int x = -SOURCE_CHECK_RADIUS; x <= SOURCE_CHECK_RADIUS; x++) {
      for (int y = -SOURCE_CHECK_RADIUS; y <= SOURCE_CHECK_RADIUS; y++) {
        for (int z = -SOURCE_CHECK_RADIUS; z <= SOURCE_CHECK_RADIUS; z++) {
          cursor.setWithOffset(origin, x, y, z);
          BlockState state = level.getBlockState(cursor);
          if (state.is(Blocks.LAPIS_ORE) || state.is(Blocks.DEEPSLATE_LAPIS_ORE)) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
