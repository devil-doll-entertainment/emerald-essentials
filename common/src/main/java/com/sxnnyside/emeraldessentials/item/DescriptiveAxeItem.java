package com.sxnnyside.emeraldessentials.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

public class DescriptiveAxeItem extends AxeItem {
  private final ChatFormatting loreColor;

  public DescriptiveAxeItem(Tier tier, Properties properties, ChatFormatting loreColor) {
    super(tier, properties);
    this.loreColor = loreColor;
  }

  @Override
  public void appendHoverText(
      ItemStack stack,
      TooltipContext context,
      List<Component> tooltipComponents,
      TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable(this.getDescriptionId() + ".lore")
            .withStyle(loreColor, ChatFormatting.ITALIC));
  }
}
