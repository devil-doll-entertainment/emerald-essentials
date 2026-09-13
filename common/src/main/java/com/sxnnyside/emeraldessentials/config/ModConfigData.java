package com.sxnnyside.emeraldessentials.config;

import org.jetbrains.annotations.NotNull;

/**
 * Immutable configuration state represented as modern Java 21 records. Provides hierarchical
 * strongly-typed configuration sections for worldgen, gameplay mechanics, and mob balancing.
 */
public record ModConfigData(
    boolean enableArmorAbilities,
    boolean enableFlowerParticles,
    boolean enableBackstabBonus,
    @NotNull WorldGenConfig worldGen,
    @NotNull GameplayConfig gameplay,
    @NotNull MobConfig mobs) {

  /** Generates default configuration values balanced for standard survival gameplay. */
  @NotNull
  public static ModConfigData createDefault() {
    return new ModConfigData(
        true,
        true,
        true,
        new WorldGenConfig(true, true),
        new GameplayConfig(60, true, 30, 20),
        new MobConfig(18.0, 3.0, 160.0, 10.0));
  }

  /** World generation parameters and feature toggles. */
  public record WorldGenConfig(boolean generateEmeraldCaverns, boolean generateMagicFlora) {}

  /** Mechanics and cooldown balancing for custom items and blocks. */
  public record GameplayConfig(
      int emeraldMirrorCooldownSeconds,
      boolean emeraldMirrorSingleUse,
      int whetstoneBonusDurationSeconds,
      int staffFireballCooldownTicks) {}

  /** Health and attack attributes for mod entities. */
  public record MobConfig(
      double scuttlerMaxHealth,
      double scuttlerAttackDamage,
      double titanMaxHealth,
      double titanAttackDamage) {}
}
