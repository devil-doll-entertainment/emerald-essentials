package com.sxnnyside.emeraldessentials.entity;

import com.sxnnyside.emeraldessentials.init.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EmeraldScuttler extends Monster implements GeoEntity {
  private static final RawAnimation IDLE_ANIM =
      RawAnimation.begin().thenLoop("animation.emerald_scuttler.idle");

  private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

  public EmeraldScuttler(EntityType<? extends Monster> entityType, Level level) {
    super(entityType, level);
    this.moveControl = new FlyingMoveControl(this, 20, true);
    this.setNoGravity(true);
    this.setPathfindingMalus(PathType.WATER, 0.0F);
    this.xpReward = 5;
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
        .add(Attributes.MAX_HEALTH, 24.0D)
        .add(Attributes.MOVEMENT_SPEED, 0.32D)
        .add(Attributes.FLYING_SPEED, 0.75D)
        .add(Attributes.ATTACK_DAMAGE, 4.0D)
        .add(Attributes.ARMOR, 6.0D);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(
        2,
        new TemptGoal(
            this,
            1.15D,
            Ingredient.of(Items.EMERALD, ModItems.RUBY, ModItems.ENCHANTED_EMERALD),
            false));
    this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.4D, false));
    this.goalSelector.addGoal(4, new WaterAvoidingRandomFlyingGoal(this, 1.1D));
    this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
    this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    this.targetSelector.addGoal(
        2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
  }

  @Override
  protected PathNavigation createNavigation(Level level) {
    FlyingPathNavigation navigation = new FlyingPathNavigation(this, level);
    navigation.setCanOpenDoors(false);
    return navigation;
  }

  private boolean isAngryAt(net.minecraft.world.entity.LivingEntity target) {
    if (target instanceof Player player) {
      // Pacified if holding emerald or ruby
      return !player.isHolding(Items.EMERALD)
          && !player.isHolding(ModItems.RUBY)
          && !player.isHolding(ModItems.ENCHANTED_EMERALD);
    }
    return true;
  }

  @Override
  public void aiStep() {
    super.aiStep();
    if (this.level().isClientSide && this.random.nextInt(6) == 0) {
      this.level()
          .addParticle(
              ParticleTypes.HAPPY_VILLAGER,
              this.getRandomX(0.6D),
              this.getRandomY() + 0.2D,
              this.getRandomZ(0.6D),
              0.0D,
              0.02D,
              0.0D);
    }
  }

  @Override
  public boolean hurt(DamageSource source, float amount) {
    // 50% damage reduction from projectiles due to crystalline carapace
    if (source.is(DamageTypeTags.IS_PROJECTILE)) {
      amount *= 0.5F;
      this.playSound(SoundEvents.AMETHYST_BLOCK_HIT, 1.0F, 1.5F);
    }
    return super.hurt(source, amount);
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.RABBIT_AMBIENT;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSource) {
    return SoundEvents.AMETHYST_BLOCK_HIT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundEvents.AMETHYST_CLUSTER_BREAK;
  }
}
