package com.sxnnyside.emeraldessentials;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sxnnyside.emeraldessentials.enchantment.ModEnchantmentHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnchantmentKeysTest {

  @Test
  @DisplayName("Enchantment keys must conform to emerald_essentials namespace")
  void testEnchantmentKeys() {
    assertEquals(
        EmeraldEssentials.MOD_ID,
        ModEnchantmentHandler.AMETHYST_LEVITATE.location().getNamespace());
    assertEquals("amethyst_levitate", ModEnchantmentHandler.AMETHYST_LEVITATE.location().getPath());

    assertEquals(
        EmeraldEssentials.MOD_ID, ModEnchantmentHandler.AMETHYST_BLIND.location().getNamespace());
    assertEquals("amethyst_blind", ModEnchantmentHandler.AMETHYST_BLIND.location().getPath());

    assertEquals(
        EmeraldEssentials.MOD_ID, ModEnchantmentHandler.AMETHYST_SHADOW.location().getNamespace());
    assertEquals("amethyst_shadow", ModEnchantmentHandler.AMETHYST_SHADOW.location().getPath());

    assertEquals(
        EmeraldEssentials.MOD_ID, ModEnchantmentHandler.AMETHYST_THIEF.location().getNamespace());
    assertEquals("amethyst_thief", ModEnchantmentHandler.AMETHYST_THIEF.location().getPath());
  }
}
