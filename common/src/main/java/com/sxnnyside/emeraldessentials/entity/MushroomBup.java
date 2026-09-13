package com.sxnnyside.emeraldessentials.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MushroomBup extends PathfinderMob implements GeoEntity {
  private static final RawAnimation IDLE_ANIM =
      RawAnimation.begin().thenLoop("animation.mushroom_bup.idle");

  private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

  public MushroomBup(EntityType<? extends PathfinderMob> entityType, Level level) {
    super(entityType, level);
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
    return Mob.createMobAttributes()
        .add(Attributes.MAX_HEALTH, 6.0D)
        .add(Attributes.MOVEMENT_SPEED, 0.22D);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8D));
    this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
    this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
  }
}
