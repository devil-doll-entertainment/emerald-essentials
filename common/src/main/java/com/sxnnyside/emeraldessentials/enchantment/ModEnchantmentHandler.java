package com.sxnnyside.emeraldessentials.enchantment;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
    if (player.level().isClientSide()) {
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
      String biomePath =
          level.getBiome(pos).unwrapKey().map(k -> k.location().getPath()).orElse("");

      if (biomePath.contains("desert")
          || biomePath.contains("badlands")
          || biomePath.contains("windswept")
          || biomePath.contains("peaks")) {
        player.addEffect(
            new MobEffectInstance(
                MobEffects.MOVEMENT_SPEED, 30, Math.min(thiefLevel - 1, 1), false, false, true));
      } else if (biomePath.contains("snow")
          || biomePath.contains("ice")
          || biomePath.contains("frozen")) {
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

  public static void onLivingHurt(LivingEntity victim, LivingEntity attacker) {
    if (victim == null || attacker == null || victim.level().isClientSide()) {
      return;
    }

    int levitateLevel = getLevel(victim, EquipmentSlot.CHEST, AMETHYST_LEVITATE);
    if (levitateLevel > 0) {
      attacker.setDeltaMovement(attacker.getDeltaMovement().add(0, 0.35D * levitateLevel, 0));
      attacker.hurtMarked = true;
      attacker.addEffect(
          new MobEffectInstance(MobEffects.LEVITATION, 20 * levitateLevel, 0, false, true, true));
    }
  }
}
