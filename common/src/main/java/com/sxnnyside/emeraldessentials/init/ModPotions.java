package com.sxnnyside.emeraldessentials.init;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

public final class ModPotions {
  private static final Map<ResourceLocation, Potion> ENTRIES = new LinkedHashMap<>();

  // Haste (from thunder_poppy)
  public static final Potion HASTE =
      register("haste", new Potion("haste", new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 0)));
  public static final Potion LONG_HASTE =
      register(
          "long_haste", new Potion("haste", new MobEffectInstance(MobEffects.DIG_SPEED, 9600, 0)));
  public static final Potion STRONG_HASTE =
      register(
          "strong_haste",
          new Potion("haste", new MobEffectInstance(MobEffects.DIG_SPEED, 1800, 1)));

  // True Sight (from solar_daisy / echo_violet)
  public static final Potion TRUE_SIGHT =
      register(
          "true_sight",
          new Potion(
              "true_sight",
              new MobEffectInstance(MobEffects.GLOWING, 3600, 0),
              new MobEffectInstance(MobEffects.NIGHT_VISION, 3600, 0)));
  public static final Potion LONG_TRUE_SIGHT =
      register(
          "long_true_sight",
          new Potion(
              "true_sight",
              new MobEffectInstance(MobEffects.GLOWING, 9600, 0),
              new MobEffectInstance(MobEffects.NIGHT_VISION, 9600, 0)));

  // Levitation (from gale_petal)
  public static final Potion LEVITATION =
      register(
          "levitation",
          new Potion("levitation", new MobEffectInstance(MobEffects.LEVITATION, 300, 0)));
  public static final Potion LONG_LEVITATION =
      register(
          "long_levitation",
          new Potion("levitation", new MobEffectInstance(MobEffects.LEVITATION, 600, 0)));

  // Resistance (from frost_lily)
  public static final Potion RESISTANCE =
      register(
          "resistance",
          new Potion("resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600, 0)));
  public static final Potion LONG_RESISTANCE =
      register(
          "long_resistance",
          new Potion("resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9600, 0)));
  public static final Potion STRONG_RESISTANCE =
      register(
          "strong_resistance",
          new Potion("resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800, 1)));

  // Vitality (from vitallia)
  public static final Potion VITALITY =
      register(
          "vitality",
          new Potion(
              "vitality",
              new MobEffectInstance(MobEffects.ABSORPTION, 3600, 1),
              new MobEffectInstance(MobEffects.REGENERATION, 900, 0)));
  public static final Potion STRONG_VITALITY =
      register(
          "strong_vitality",
          new Potion(
              "vitality",
              new MobEffectInstance(MobEffects.ABSORPTION, 1800, 2),
              new MobEffectInstance(MobEffects.REGENERATION, 600, 1)));

  // Cleansing (from aura_marigold)
  public static final Potion CLEANSING =
      register(
          "cleansing",
          new Potion(
              "cleansing",
              new MobEffectInstance(MobEffects.SATURATION, 20, 0),
              new MobEffectInstance(MobEffects.HEAL, 1, 0)));

  private ModPotions() {}

  private static Potion register(String name, Potion potion) {
    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, name);
    ENTRIES.put(id, potion);
    return potion;
  }

  public static Map<ResourceLocation, Potion> getEntries() {
    return Collections.unmodifiableMap(ENTRIES);
  }

  public static void registerVanilla() {
    ENTRIES.forEach((id, potion) -> Registry.register(BuiltInRegistries.POTION, id, potion));
  }

  public static Holder<Potion> getHolder(Potion potion) {
    return BuiltInRegistries.POTION.wrapAsHolder(potion);
  }
}
