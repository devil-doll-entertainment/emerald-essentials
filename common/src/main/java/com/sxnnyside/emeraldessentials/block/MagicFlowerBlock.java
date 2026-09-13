package com.sxnnyside.emeraldessentials.block;

import com.sxnnyside.emeraldessentials.config.ModConfig;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MagicFlowerBlock extends FlowerBlock {
  protected static final VoxelShape SHAPE = box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

  public enum SoilType {
    GRASS_DIRT,
    DESERT_NETHER,
    SUBTERRANEAN,
    AQUATIC_SHORE,
    ALL
  }

  private final SoilType soilType;
  private final Supplier<? extends ParticleOptions> particleSupplier;
  private final BiConsumer<LivingEntity, Level> contactEffect;

  public MagicFlowerBlock(
      Holder<MobEffect> stewEffect,
      float stewDuration,
      SoilType soilType,
      Supplier<? extends ParticleOptions> particleSupplier,
      BiConsumer<LivingEntity, Level> contactEffect,
      BlockBehaviour.Properties properties) {
    super(stewEffect, stewDuration, properties);
    this.soilType = soilType;
    this.particleSupplier = particleSupplier;
    this.contactEffect = contactEffect;
  }

  @Override
  public VoxelShape getShape(
      BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }

  @Override
  protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
    if (soilType == SoilType.DESERT_NETHER) {
      return super.mayPlaceOn(state, level, pos)
          || state.is(BlockTags.SAND)
          || state.is(Blocks.RED_SAND)
          || state.is(Blocks.NETHERRACK)
          || state.is(Blocks.SOUL_SAND)
          || state.is(Blocks.SOUL_SOIL)
          || state.is(Blocks.MAGMA_BLOCK)
          || state.is(Blocks.BASALT);
    } else if (soilType == SoilType.SUBTERRANEAN) {
      return super.mayPlaceOn(state, level, pos)
          || state.is(BlockTags.BASE_STONE_OVERWORLD)
          || state.is(BlockTags.BASE_STONE_NETHER)
          || state.is(Blocks.SCULK)
          || state.is(Blocks.MOSS_BLOCK)
          || state.is(Blocks.END_STONE);
    } else if (soilType == SoilType.AQUATIC_SHORE) {
      return super.mayPlaceOn(state, level, pos)
          || state.is(Blocks.MUD)
          || state.is(Blocks.MUDDY_MANGROVE_ROOTS)
          || state.is(Blocks.CLAY)
          || state.is(BlockTags.SAND);
    }
    return super.mayPlaceOn(state, level, pos);
  }

  @Override
  public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
    if (entity instanceof LivingEntity living && contactEffect != null) {
      contactEffect.accept(living, level);
    }
    super.entityInside(state, level, pos, entity);
  }

  @Override
  public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
    if (!ModConfig.get().isEnableFlowerParticles() || particleSupplier == null) {
      return;
    }
    if (random.nextInt(3) == 0) {
      double x = pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.5D;
      double y = pos.getY() + 0.5D + random.nextDouble() * 0.4D;
      double z = pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.5D;
      level.addParticle(particleSupplier.get(), x, y, z, 0.0D, 0.015D, 0.0D);
    }
  }

  // Pre-configured factory helpers for the 12 magical flowers

  public static MagicFlowerBlock createCinderBlossom() {
    return new MagicFlowerBlock(
        MobEffects.FIRE_RESISTANCE,
        15.0F,
        SoilType.DESERT_NETHER,
        () -> net.minecraft.core.particles.ParticleTypes.FLAME,
        (living, level) -> {
          if (!level.isClientSide) {
            living.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 160, 0, true, true));
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_ORANGE)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.GRASS)
            .lightLevel(state -> 8));
  }

  public static MagicFlowerBlock createFrostLily() {
    return new MagicFlowerBlock(
        MobEffects.DAMAGE_RESISTANCE,
        10.0F,
        SoilType.GRASS_DIRT,
        () -> net.minecraft.core.particles.ParticleTypes.SNOWFLAKE,
        (living, level) -> {
          if (!level.isClientSide) {
            if (living instanceof Player) {
              living.addEffect(
                  new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 0, true, true));
            } else {
              living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            }
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_LIGHT_BLUE)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.GRASS)
            .lightLevel(state -> 4));
  }

  public static MagicFlowerBlock createSolarDaisy() {
    return new MagicFlowerBlock(
        MobEffects.GLOWING,
        12.0F,
        SoilType.GRASS_DIRT,
        () -> net.minecraft.core.particles.ParticleTypes.GLOW,
        (living, level) -> {
          if (!level.isClientSide) {
            living.addEffect(new MobEffectInstance(MobEffects.GLOWING, 160, 0, true, true));
            living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, true, true));
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_YELLOW)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.GRASS)
            .lightLevel(state -> 10));
  }

  public static MagicFlowerBlock createGalePetal() {
    return new MagicFlowerBlock(
        MobEffects.SLOW_FALLING,
        10.0F,
        SoilType.GRASS_DIRT,
        () -> net.minecraft.core.particles.ParticleTypes.CLOUD,
        (living, level) -> {
          living.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 160, 0, true, true));
          living.addEffect(new MobEffectInstance(MobEffects.JUMP, 100, 1, true, true));
          Vec3 delta = living.getDeltaMovement();
          if (delta.y < 0.25D) {
            living.setDeltaMovement(delta.x, 0.45D, delta.z);
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_CYAN)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.GRASS)
            .lightLevel(state -> 4));
  }

  public static MagicFlowerBlock createEchoViolet() {
    return new MagicFlowerBlock(
        MobEffects.NIGHT_VISION,
        12.0F,
        SoilType.SUBTERRANEAN,
        () -> net.minecraft.core.particles.ParticleTypes.SCULK_CHARGE_POP,
        (living, level) -> {
          if (!level.isClientSide) {
            living.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0, true, true));
            level.playSound(
                null,
                living.blockPosition(),
                SoundEvents.AMETHYST_BLOCK_CHIME,
                SoundSource.BLOCKS,
                0.8F,
                1.4F);
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_BLUE)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.SCULK_SENSOR)
            .lightLevel(state -> 6));
  }

  public static MagicFlowerBlock createShadowOrchid() {
    return new MagicFlowerBlock(
        MobEffects.INVISIBILITY,
        12.0F,
        SoilType.GRASS_DIRT,
        () -> net.minecraft.core.particles.ParticleTypes.SMOKE,
        (living, level) -> {
          if (!level.isClientSide) {
            if (living instanceof Player) {
              living.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 160, 0, true, true));
            } else {
              living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 80, 0));
            }
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_BLACK)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.GRASS)
            .lightLevel(state -> 0));
  }

  public static MagicFlowerBlock createVitallia() {
    return new MagicFlowerBlock(
        MobEffects.HEAL,
        5.0F,
        SoilType.GRASS_DIRT,
        () -> net.minecraft.core.particles.ParticleTypes.HEART,
        (living, level) -> {
          if (!level.isClientSide && living.tickCount % 20 == 0) {
            living.heal(1.0F);
            living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 0, true, true));
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_MAGENTA)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.GRASS)
            .lightLevel(state -> 6));
  }

  public static MagicFlowerBlock createThunderPoppy() {
    return new MagicFlowerBlock(
        MobEffects.DIG_SPEED,
        12.0F,
        SoilType.DESERT_NETHER,
        () -> net.minecraft.core.particles.ParticleTypes.ELECTRIC_SPARK,
        (living, level) -> {
          if (!level.isClientSide) {
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 160, 1, true, true));
            living.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 160, 0, true, true));
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_YELLOW)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.GRASS)
            .lightLevel(state -> 7));
  }

  public static MagicFlowerBlock createVoidChrysanthemum() {
    return new MagicFlowerBlock(
        MobEffects.LEVITATION,
        8.0F,
        SoilType.SUBTERRANEAN,
        () -> net.minecraft.core.particles.ParticleTypes.PORTAL,
        (living, level) -> {
          if (!level.isClientSide && living.tickCount % 30 == 0) {
            RandomSource rng = level.random;
            double targetX = living.getX() + (rng.nextDouble() - 0.5D) * 8.0D;
            double targetY = Math.clamp(living.getY() + (rng.nextDouble() - 0.5D) * 4.0D, 0, 320);
            double targetZ = living.getZ() + (rng.nextDouble() - 0.5D) * 8.0D;
            living.teleportTo(targetX, targetY, targetZ);
            level.playSound(
                null,
                living.blockPosition(),
                SoundEvents.CHORUS_FRUIT_TELEPORT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F);
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_PURPLE)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.SCULK)
            .lightLevel(state -> 7));
  }

  public static MagicFlowerBlock createSlimeLotus() {
    return new MagicFlowerBlock(
        MobEffects.JUMP,
        10.0F,
        SoilType.AQUATIC_SHORE,
        () -> net.minecraft.core.particles.ParticleTypes.ITEM_SLIME,
        (living, level) -> {
          Vec3 delta = living.getDeltaMovement();
          if (delta.y < 0.0D) {
            living.setDeltaMovement(delta.x, -delta.y * 0.8D + 0.35D, delta.z);
            living.fallDistance = 0.0F;
            level.playSound(
                null,
                living.blockPosition(),
                SoundEvents.SLIME_BLOCK_FALL,
                SoundSource.BLOCKS,
                0.7F,
                1.2F);
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_LIGHT_GREEN)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.SLIME_BLOCK)
            .lightLevel(state -> 3));
  }

  public static MagicFlowerBlock createEmberRose() {
    return new MagicFlowerBlock(
        MobEffects.DAMAGE_BOOST,
        10.0F,
        SoilType.DESERT_NETHER,
        () -> net.minecraft.core.particles.ParticleTypes.LAVA,
        (living, level) -> {
          if (!level.isClientSide) {
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 160, 0, true, true));
            living.hurt(level.damageSources().cactus(), 1.0F);
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_RED)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.GRASS)
            .lightLevel(state -> 6));
  }

  public static MagicFlowerBlock createAuraMarigold() {
    return new MagicFlowerBlock(
        MobEffects.SATURATION,
        8.0F,
        SoilType.GRASS_DIRT,
        () -> net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER,
        (living, level) -> {
          if (!level.isClientSide && living.tickCount % 20 == 0) {
            living.addEffect(new MobEffectInstance(MobEffects.SATURATION, 40, 0, true, true));
            List<Holder<MobEffect>> harmful =
                living.getActiveEffects().stream()
                    .filter(
                        instance ->
                            instance.getEffect().value().getCategory() == MobEffectCategory.HARMFUL)
                    .map(MobEffectInstance::getEffect)
                    .toList();
            harmful.forEach(living::removeEffect);
          }
        },
        BlockBehaviour.Properties.of()
            .mapColor(net.minecraft.world.level.material.MapColor.COLOR_PINK)
            .noCollission()
            .instabreak()
            .sound(net.minecraft.world.level.block.SoundType.GRASS)
            .lightLevel(state -> 6));
  }
}
