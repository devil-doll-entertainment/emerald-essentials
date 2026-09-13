package com.sxnnyside.emeraldessentials.entity;

import com.sxnnyside.emeraldessentials.init.ModEntities;
import com.sxnnyside.emeraldessentials.init.ModItems;
import java.util.List;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EmeraldTitan extends Monster implements GeoEntity {
  private static final RawAnimation IDLE_ANIM =
      RawAnimation.begin().thenLoop("animation.emerald_titan.idle");

  private final ServerBossEvent bossEvent;
  private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
  private boolean enraged = false;

  public EmeraldTitan(EntityType<? extends Monster> entityType, Level level) {
    super(entityType, level);
    this.bossEvent =
        new ServerBossEvent(
            Component.translatable("entity.emerald_essentials.emerald_titan"),
            BossEvent.BossBarColor.GREEN,
            BossEvent.BossBarOverlay.PROGRESS);
    this.xpReward = 50;
  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    controllers.add(
        new AnimationController<>(this, "idle", state -> state.setAndContinue(IDLE_ANIM)));
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return this.geoCache;
  }

  @Override
  public double getTick(Object entity) {
    return this.tickCount;
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Monster.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH, 300.0D)
        .add(Attributes.MOVEMENT_SPEED, 0.24D)
        .add(Attributes.ATTACK_DAMAGE, 10.0D)
        .add(Attributes.ARMOR, 12.0D)
        .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
    this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.8D));
    this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 12.0F));
    this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
  }

  @Override
  public void startSeenByPlayer(ServerPlayer player) {
    super.startSeenByPlayer(player);
    this.bossEvent.addPlayer(player);
  }

  @Override
  public void stopSeenByPlayer(ServerPlayer player) {
    super.stopSeenByPlayer(player);
    this.bossEvent.removePlayer(player);
  }

  @Override
  public void aiStep() {
    super.aiStep();
    this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

    if (this.level().isClientSide && this.random.nextInt(4) == 0) {
      this.level()
          .addParticle(
              ParticleTypes.ELECTRIC_SPARK,
              this.getRandomX(0.8D),
              this.getY() + this.getBbHeight() * 0.6D,
              this.getRandomZ(0.8D),
              0.0D,
              0.01D,
              0.0D);
    }

    // Enrage phase: attack boost and particle emission when health drops below 50%
    if (!this.level().isClientSide && !enraged && this.getHealth() <= this.getMaxHealth() * 0.5F) {
      triggerEnrage();
    }
  }

  private void triggerEnrage() {
    this.enraged = true;
    this.bossEvent.setColor(BossEvent.BossBarColor.PURPLE);
    this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 99999, 0, false, false));
    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 99999, 0, false, false));

    if (this.level() instanceof ServerLevel serverLevel) {
      serverLevel.playSound(
          null,
          this.blockPosition(),
          SoundEvents.AMETHYST_BLOCK_RESONATE,
          SoundSource.HOSTILE,
          2.0F,
          0.8F);
      serverLevel.sendParticles(
          ParticleTypes.EXPLOSION_EMITTER,
          this.getX(),
          this.getY() + 1.0D,
          this.getZ(),
          1,
          0,
          0,
          0,
          0);

      // Summon 2 Emerald Scuttler minions
      for (int i = 0; i < 2; i++) {
        EmeraldScuttler minion = new EmeraldScuttler(ModEntities.EMERALD_SCUTTLER, serverLevel);
        double offsetX = (i == 0 ? 2.0D : -2.0D);
        minion.moveTo(this.getX() + offsetX, this.getY(), this.getZ(), this.getYRot(), 0.0F);
        serverLevel.addFreshEntity(minion);
      }
    }
  }

  @Override
  public boolean doHurtTarget(Entity target) {
    boolean success = super.doHurtTarget(target);
    if (success) {
      // Ground slam AOE knockup
      this.playSound(SoundEvents.GENERIC_EXPLODE.value(), 1.0F, 1.2F);
      AABB slamBox = this.getBoundingBox().inflate(3.5D, 1.5D, 3.5D);
      List<LivingEntity> nearby =
          this.level().getEntitiesOfClass(LivingEntity.class, slamBox, e -> e != this);
      for (LivingEntity entity : nearby) {
        if (!(entity instanceof EmeraldTitan) && !(entity instanceof EmeraldScuttler)) {
          Vec3 delta = entity.getDeltaMovement();
          entity.setDeltaMovement(delta.x * 1.5D, 0.55D, delta.z * 1.5D);
          entity.hurt(this.damageSources().mobAttack(this), 6.0F);
        }
      }
    }
    return success;
  }

  @Override
  protected void dropCustomDeathLoot(
      ServerLevel level, DamageSource damageSource, boolean recentlyHit) {
    super.dropCustomDeathLoot(level, damageSource, recentlyHit);
    // Guaranteed non-craftable Emerald Core drop
    this.spawnAtLocation(ModItems.EMERALD_CORE);
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.IRON_GOLEM_STEP;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSource) {
    return SoundEvents.AMETHYST_CLUSTER_HIT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundEvents.AMETHYST_CLUSTER_BREAK;
  }
}
