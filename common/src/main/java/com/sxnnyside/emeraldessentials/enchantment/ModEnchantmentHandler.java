package com.sxnnyside.emeraldessentials.enchantment;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public final class ModEnchantmentHandler {
  public static final ResourceKey<Enchantment> AMETHYST_LEVITATE =
      ResourceKey.create(
          Registries.ENCHANTMENT,
          ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, "amethyst_levitate"));
  public static final ResourceKey<Enchantment> AMETHYST_BLIND =
      ResourceKey.create(
          Registries.ENCHANTMENT,
          ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, "amethyst_blind"));
  public static final ResourceKey<Enchantment> AMETHYST_SHADOW =
      ResourceKey.create(
          Registries.ENCHANTMENT,
          ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, "amethyst_shadow"));
  public static final ResourceKey<Enchantment> AMETHYST_THIEF =
      ResourceKey.create(
          Registries.ENCHANTMENT,
          ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, "amethyst_thief"));
  public static final ResourceKey<Enchantment> AMETHYST_RESONANCE =
      ResourceKey.create(
          Registries.ENCHANTMENT,
          ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, "amethyst_resonance"));
  public static final ResourceKey<Enchantment> AMETHYST_VITALITY =
      ResourceKey.create(
          Registries.ENCHANTMENT,
          ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, "amethyst_vitality"));

  private ModEnchantmentHandler() {}

  public static int getLevel(
      LivingEntity entity, EquipmentSlot slot, ResourceKey<Enchantment> key) {
    ItemStack stack = entity.getItemBySlot(slot);
    if (stack.isEmpty()) {
      return 0;
    }
    Optional<Holder.Reference<Enchantment>> holder =
        entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).get(key);
    return holder
        .map(
            enchantmentReference ->
                EnchantmentHelper.getItemEnchantmentLevel(enchantmentReference, stack))
        .orElse(0);
  }

  public static void onPlayerTick(Player player) {
    if (player.level().isClientSide()
        || !com.sxnnyside.emeraldessentials.config.ModConfig.get().isEnableArmorAbilities()) {
      return;
    }

    Level level = player.level();
    BlockPos pos = player.blockPosition();

    // 1. Amethyst Blind (Helmet)
    int blindLevel = getLevel(player, EquipmentSlot.HEAD, AMETHYST_BLIND);
    if (blindLevel > 0 && level.getMaxLocalRawBrightness(pos) <= 5) {
      player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0, false, false, false));
      player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 60, 1, false, false, true));
    }

    // 2. Amethyst Shadow (Leggings)
    int shadowLevel = getLevel(player, EquipmentSlot.LEGS, AMETHYST_SHADOW);
    if (shadowLevel > 0) {
      if (player.isCrouching()) {
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 30, 0, false, false, true));
      } else if (player.isSprinting() && shadowLevel < 2) {
        player.addEffect(
            new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0, false, false, true));
      }
    }

    // 3. Amethyst Thief (Boots)
    int thiefLevel = getLevel(player, EquipmentSlot.FEET, AMETHYST_THIEF);
    if (thiefLevel > 0) {
      Holder<net.minecraft.world.level.biome.Biome> biomeHolder = level.getBiome(pos);
      boolean isAridOrMountain =
          biomeHolder.is(net.minecraft.tags.BiomeTags.IS_BADLANDS)
              || biomeHolder.is(net.minecraft.tags.BiomeTags.IS_MOUNTAIN)
              || biomeHolder.is(net.minecraft.tags.BiomeTags.HAS_DESERT_PYRAMID)
              || biomeHolder.value().getBaseTemperature() >= 1.0F;

      if (isAridOrMountain) {
        player.addEffect(
            new MobEffectInstance(
                MobEffects.MOVEMENT_SPEED, 30, Math.min(thiefLevel - 1, 1), false, false, true));
      } else if (biomeHolder.value().coldEnoughToSnow(pos)
          || biomeHolder.is(net.minecraft.tags.BiomeTags.SPAWNS_SNOW_FOXES)) {
        // Throttled to execute once per second (every 20 ticks) to eliminate server tick lag
        if (player.tickCount % 20 == 0) {
          Vec3 center = player.position();
          List<LivingEntity> nearby =
              level.getEntitiesOfClass(
                  LivingEntity.class,
                  new AABB(
                      center.x - 6,
                      center.y - 3,
                      center.z - 6,
                      center.x + 6,
                      center.y + 3,
                      center.z + 6),
                  e -> e != player && e.getType().is(EntityTypeTags.UNDEAD));
          for (LivingEntity undead : nearby) {
            undead.addEffect(
                new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1, false, false, true));
          }
        }
      }
    }
  }

  public static void onLivingHurt(LivingEntity victim, LivingEntity attacker) {
    if (victim == null
        || attacker == null
        || victim.level().isClientSide()
        || !com.sxnnyside.emeraldessentials.config.ModConfig.get().isEnableArmorAbilities()) {
      return;
    }

    // 1. Amethyst Levitate (Victim Chestplate)
    int levitateLevel = getLevel(victim, EquipmentSlot.CHEST, AMETHYST_LEVITATE);
    if (levitateLevel > 0) {
      attacker.setDeltaMovement(attacker.getDeltaMovement().add(0, 0.35D * levitateLevel, 0));
      attacker.hurtMarked = true;
      attacker.addEffect(
          new MobEffectInstance(MobEffects.LEVITATION, 20 * levitateLevel, 0, false, true, true));
    }

    // 2. Amethyst Vitality (Victim Chestplate Emergency Shield)
    int vitalityLevel = getLevel(victim, EquipmentSlot.CHEST, AMETHYST_VITALITY);
    if (vitalityLevel > 0 && victim.getHealth() <= victim.getMaxHealth() * 0.5F) {
      if (!victim.hasEffect(MobEffects.ABSORPTION)) {
        victim.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, vitalityLevel - 1));
        victim.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, vitalityLevel - 1));
        Level level = victim.level();
        level.playSound(
            null,
            victim.getX(),
            victim.getY(),
            victim.getZ(),
            SoundEvents.AMETHYST_BLOCK_CHIME,
            SoundSource.PLAYERS,
            1.2F,
            1.5F);
        if (level instanceof ServerLevel serverLevel) {
          serverLevel.sendParticles(
              ParticleTypes.END_ROD,
              victim.getX(),
              victim.getY() + 1.0,
              victim.getZ(),
              16,
              0.3,
              0.5,
              0.3,
              0.05);
        }
      }
    }

    // 3. Amethyst Resonance (Attacker Weapon Sonic Pulse)
    int resonanceLevel = getLevel(attacker, EquipmentSlot.MAINHAND, AMETHYST_RESONANCE);
    if (resonanceLevel > 0) {
      Level level = attacker.level();
      Vec3 pos = victim.position();
      level.playSound(
          null,
          pos.x,
          pos.y,
          pos.z,
          SoundEvents.AMETHYST_BLOCK_RESONATE,
          SoundSource.PLAYERS,
          1.2F,
          1.4F);
      level.playSound(
          null,
          pos.x,
          pos.y,
          pos.z,
          SoundEvents.AMETHYST_CLUSTER_HIT,
          SoundSource.PLAYERS,
          1.0F,
          1.8F);

      if (level instanceof ServerLevel serverLevel) {
        serverLevel.sendParticles(
            ParticleTypes.ENCHANT, pos.x, pos.y + 1.0, pos.z, 20, 0.4, 0.4, 0.4, 0.15);
      }

      victim.hurt(attacker.damageSources().magic(), 1.5F * resonanceLevel);

      List<LivingEntity> nearby =
          level.getEntitiesOfClass(
              LivingEntity.class,
              victim.getBoundingBox().inflate(3.0D),
              e -> e != attacker && e != victim && e.isAlive() && !e.isAlliedTo(attacker));
      for (LivingEntity nearbyEnemy : nearby) {
        nearbyEnemy.hurt(attacker.damageSources().magic(), 1.0F * resonanceLevel);
        Vec3 knockback =
            nearbyEnemy.position().subtract(pos).normalize().scale(0.3D * resonanceLevel);
        nearbyEnemy.setDeltaMovement(
            nearbyEnemy.getDeltaMovement().add(knockback.x, 0.2D, knockback.z));
        nearbyEnemy.hurtMarked = true;
      }
    }
  }
}
