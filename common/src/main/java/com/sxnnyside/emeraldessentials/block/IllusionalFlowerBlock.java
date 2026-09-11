package com.sxnnyside.emeraldessentials.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IllusionalFlowerBlock extends FlowerBlock {
  protected static final VoxelShape SHAPE = box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

  public IllusionalFlowerBlock(
      Holder<MobEffect> effect, float effectDuration, Properties properties) {
    super(effect, effectDuration, properties);
  }

  public static IllusionalFlowerBlock create() {
    return new IllusionalFlowerBlock(
        MobEffects.CONFUSION,
        12.0F,
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .noCollission()
            .instabreak()
            .sound(SoundType.SCULK_SENSOR)
            .lightLevel(state -> 5));
  }

  @Override
  public VoxelShape getShape(
      BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }

  @Override
  public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
    if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
      livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0, true, true));
    }
    super.entityInside(state, level, pos, entity);
  }

  @Override
  public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
    if (random.nextInt(4) == 0) {
      double x = pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.4D;
      double y = pos.getY() + 0.6D + random.nextDouble() * 0.3D;
      double z = pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.4D;
      level.addParticle(ParticleTypes.SCULK_SOUL, x, y, z, 0.0D, 0.02D, 0.0D);
    }
  }
}
