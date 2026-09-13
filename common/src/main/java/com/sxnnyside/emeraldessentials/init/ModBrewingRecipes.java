package com.sxnnyside.emeraldessentials.init;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

public final class ModBrewingRecipes {

  @FunctionalInterface
  public interface BrewingMixConsumer {
    void addMix(Holder<Potion> input, Item ingredient, Holder<Potion> output);
  }

  private ModBrewingRecipes() {}

  public static void registerAll(BrewingMixConsumer consumer) {
    // Haste: Awkward + Thunder Poppy -> Haste
    consumer.addMix(
        Potions.AWKWARD, ModItems.THUNDER_POPPY, ModPotions.getHolder(ModPotions.HASTE));
    consumer.addMix(
        ModPotions.getHolder(ModPotions.HASTE),
        Items.REDSTONE,
        ModPotions.getHolder(ModPotions.LONG_HASTE));
    consumer.addMix(
        ModPotions.getHolder(ModPotions.HASTE),
        Items.GLOWSTONE_DUST,
        ModPotions.getHolder(ModPotions.STRONG_HASTE));

    // True Sight: Awkward + Solar Daisy or Echo Violet -> True Sight
    consumer.addMix(
        Potions.AWKWARD, ModItems.SOLAR_DAISY, ModPotions.getHolder(ModPotions.TRUE_SIGHT));
    consumer.addMix(
        Potions.AWKWARD, ModItems.ECHO_VIOLET, ModPotions.getHolder(ModPotions.TRUE_SIGHT));
    consumer.addMix(
        ModPotions.getHolder(ModPotions.TRUE_SIGHT),
        Items.REDSTONE,
        ModPotions.getHolder(ModPotions.LONG_TRUE_SIGHT));

    // Levitation: Awkward + Gale Petal -> Levitation
    consumer.addMix(
        Potions.AWKWARD, ModItems.GALE_PETAL, ModPotions.getHolder(ModPotions.LEVITATION));
    consumer.addMix(
        ModPotions.getHolder(ModPotions.LEVITATION),
        Items.REDSTONE,
        ModPotions.getHolder(ModPotions.LONG_LEVITATION));

    // Resistance: Awkward + Frost Lily -> Resistance
    consumer.addMix(
        Potions.AWKWARD, ModItems.FROST_LILY, ModPotions.getHolder(ModPotions.RESISTANCE));
    consumer.addMix(
        ModPotions.getHolder(ModPotions.RESISTANCE),
        Items.REDSTONE,
        ModPotions.getHolder(ModPotions.LONG_RESISTANCE));
    consumer.addMix(
        ModPotions.getHolder(ModPotions.RESISTANCE),
        Items.GLOWSTONE_DUST,
        ModPotions.getHolder(ModPotions.STRONG_RESISTANCE));

    // Vitality: Awkward + Vitallia -> Vitality
    consumer.addMix(Potions.AWKWARD, ModItems.VITALLIA, ModPotions.getHolder(ModPotions.VITALITY));
    consumer.addMix(
        ModPotions.getHolder(ModPotions.VITALITY),
        Items.GLOWSTONE_DUST,
        ModPotions.getHolder(ModPotions.STRONG_VITALITY));

    // Cleansing: Awkward + Aura Marigold -> Cleansing
    consumer.addMix(
        Potions.AWKWARD, ModItems.AURA_MARIGOLD, ModPotions.getHolder(ModPotions.CLEANSING));
  }

  public static void registerWithBuilder(PotionBrewing.Builder builder) {
    registerAll(builder::addMix);
  }
}
