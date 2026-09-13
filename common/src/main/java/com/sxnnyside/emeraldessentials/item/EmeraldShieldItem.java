package com.sxnnyside.emeraldessentials.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;

/** Reinforced emerald shield with enhanced durability and enchantability. */
public class EmeraldShieldItem extends ShieldItem {

  public EmeraldShieldItem(Properties properties) {
    super(properties);
  }

  @Override
  public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
    return repairCandidate.is(Items.EMERALD) || super.isValidRepairItem(stack, repairCandidate);
  }

  @Override
  public int getEnchantmentValue() {
    return 16;
  }

  @Override
  public void appendHoverText(
      ItemStack stack,
      TooltipContext context,
      List<Component> tooltipComponents,
      TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_shield.desc")
            .withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_shield.lore")
            .withStyle(ChatFormatting.DARK_GREEN, ChatFormatting.ITALIC));
  }
}
