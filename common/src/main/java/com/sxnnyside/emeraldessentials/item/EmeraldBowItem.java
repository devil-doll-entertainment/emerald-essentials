package com.sxnnyside.emeraldessentials.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/** Composite emerald bow with rapid tension draw (25% faster than standard bow). */
public class EmeraldBowItem extends BowItem {

  public EmeraldBowItem(Properties properties) {
    super(properties);
  }

  @Override
  public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
    int actualDrawnTicks = this.getUseDuration(stack, entity) - timeLeft;
    // Accelerate draw progression so 15 ticks equals a full 20-tick vanilla charge
    int acceleratedTicks = (int) (actualDrawnTicks * 1.334F);
    int modifiedTimeLeft = this.getUseDuration(stack, entity) - acceleratedTicks;
    super.releaseUsing(stack, level, entity, modifiedTimeLeft);
  }

  @Override
  public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
    return repairCandidate.is(Items.EMERALD) || super.isValidRepairItem(stack, repairCandidate);
  }

  @Override
  public int getEnchantmentValue() {
    return 18;
  }

  @Override
  public void appendHoverText(
      ItemStack stack,
      TooltipContext context,
      List<Component> tooltipComponents,
      TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_bow.desc")
            .withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_bow.lore")
            .withStyle(ChatFormatting.DARK_GREEN, ChatFormatting.ITALIC));
  }
}
