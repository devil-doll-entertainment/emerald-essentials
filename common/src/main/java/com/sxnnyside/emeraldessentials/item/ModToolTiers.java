package com.sxnnyside.emeraldessentials.item;

import com.sxnnyside.emeraldessentials.init.ModItems;
import java.util.function.Supplier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public enum ModToolTiers implements Tier {
  EMERALD(
      BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
      550,
      8.0F,
      3.0F,
      22,
      () -> Ingredient.of(Items.EMERALD)),
  RUBY(
      BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
      1750,
      8.5F,
      3.5F,
      16,
      () -> Ingredient.of(ModItems.RUBY));

  private final TagKey<Block> incorrectBlocksForDrops;
  private final int uses;
  private final float speed;
  private final float damage;
  private final int enchantmentValue;
  private final Supplier<Ingredient> repairIngredient;

  ModToolTiers(
      TagKey<Block> incorrectBlocksForDrops,
      int uses,
      float speed,
      float damage,
      int enchantmentValue,
      Supplier<Ingredient> repairIngredient) {
    this.incorrectBlocksForDrops = incorrectBlocksForDrops;
    this.uses = uses;
    this.speed = speed;
    this.damage = damage;
    this.enchantmentValue = enchantmentValue;
    this.repairIngredient = repairIngredient;
  }

  @Override
  public int getUses() {
    return this.uses;
  }

  @Override
  public float getSpeed() {
    return this.speed;
  }

  @Override
  public float getAttackDamageBonus() {
    return this.damage;
  }

  @Override
  public TagKey<Block> getIncorrectBlocksForDrops() {
    return this.incorrectBlocksForDrops;
  }

  @Override
  public int getEnchantmentValue() {
    return this.enchantmentValue;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return this.repairIngredient.get();
  }
}
