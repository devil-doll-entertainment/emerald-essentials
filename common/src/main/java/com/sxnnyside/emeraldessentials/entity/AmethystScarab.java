package com.sxnnyside.emeraldessentials.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
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
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class AmethystScarab extends Monster implements GeoEntity {
  private static final RawAnimation IDLE_ANIM =
      RawAnimation.begin().thenLoop("animation.amethyst_scarab.idle");

  private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

  public AmethystScarab(EntityType<? extends Monster> entityType, Level level) {
    super(entityType, level);
    this.xpReward = 3;
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
        .add(Attributes.MAX_HEALTH, 8.0D)
        .add(Attributes.MOVEMENT_SPEED, 0.34D)
        .add(Attributes.ATTACK_DAMAGE, 2.0D);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.4D, true));
    this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.2D));
    this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
    this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.SILVERFISH_AMBIENT;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSource) {
    return SoundEvents.AMETHYST_BLOCK_HIT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundEvents.AMETHYST_BLOCK_BREAK;
  }
}
