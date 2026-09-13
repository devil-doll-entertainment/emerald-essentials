package com.sxnnyside.emeraldessentials;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sxnnyside.emeraldessentials.item.ModToolTiers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ModToolTiersTest {

  @Test
  @DisplayName("Emerald tool tier should have balanced stats")
  void testEmeraldToolTierStats() {
    assertEquals(550, ModToolTiers.EMERALD.getUses(), "Emerald tier durability should be 550");
    assertEquals(8.0F, ModToolTiers.EMERALD.getSpeed(), "Emerald tier speed should be 8.0f");
    assertEquals(
        3.0F,
        ModToolTiers.EMERALD.getAttackDamageBonus(),
        "Emerald tier attack damage bonus should be 3.0f");
    assertEquals(
        22, ModToolTiers.EMERALD.getEnchantmentValue(), "Emerald tier enchantability should be 22");
    assertNotNull(
        ModToolTiers.EMERALD.getIncorrectBlocksForDrops(),
        "Incorrect blocks tag should be configured");
  }

  @Test
  @DisplayName(
      "Ruby tool tier should have advanced intermediate stats between Diamond and Netherite")
  void testRubyToolTierStats() {
    assertEquals(1750, ModToolTiers.RUBY.getUses(), "Ruby tier durability should be 1750");
    assertEquals(8.5F, ModToolTiers.RUBY.getSpeed(), "Ruby tier speed should be 8.5f");
    assertEquals(
        3.5F,
        ModToolTiers.RUBY.getAttackDamageBonus(),
        "Ruby tier attack damage bonus should be 3.5f");
    assertEquals(
        16, ModToolTiers.RUBY.getEnchantmentValue(), "Ruby tier enchantability should be 16");
    assertNotNull(
        ModToolTiers.RUBY.getIncorrectBlocksForDrops(),
        "Incorrect blocks tag should be configured");
  }
}
