package com.sxnnyside.emeraldessentials.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

/** BlockItem providing localized gameplay description and atmospheric lore tooltips. */
public class DescriptiveBlockItem extends BlockItem {
  private final ChatFormatting descColor;
  private final ChatFormatting loreColor;

  public DescriptiveBlockItem(
      @NotNull Block block,
      @NotNull Properties properties,
      @NotNull ChatFormatting descColor,
      @NotNull ChatFormatting loreColor) {
    super(block, properties);
    this.descColor = descColor;
    this.loreColor = loreColor;
  }

  public DescriptiveBlockItem(@NotNull Block block, @NotNull Properties properties) {
    this(block, properties, ChatFormatting.GRAY, ChatFormatting.DARK_GREEN);
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
