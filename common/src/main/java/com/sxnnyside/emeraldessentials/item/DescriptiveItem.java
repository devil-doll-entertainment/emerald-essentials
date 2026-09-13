package com.sxnnyside.emeraldessentials.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

/** Standard item with localized description and atmospheric lore tooltip. */
public class DescriptiveItem extends Item {
  private final ChatFormatting descColor;
  private final ChatFormatting loreColor;

  public DescriptiveItem(
      @NotNull Properties properties,
      @NotNull ChatFormatting descColor,
      @NotNull ChatFormatting loreColor) {
    super(properties);
    this.descColor = descColor;
    this.loreColor = loreColor;
  }

  public DescriptiveItem(@NotNull Properties properties) {
    this(properties, ChatFormatting.GRAY, ChatFormatting.DARK_GREEN);
  }

  @Override
  public void appendHoverText(
      @NotNull ItemStack stack,
      @NotNull TooltipContext context,
      @NotNull List<Component> tooltipComponents,
      @NotNull TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable(this.getDescriptionId() + ".desc").withStyle(descColor));
    tooltipComponents.add(
        Component.translatable(this.getDescriptionId() + ".lore")
            .withStyle(loreColor, ChatFormatting.ITALIC));
  }
}
