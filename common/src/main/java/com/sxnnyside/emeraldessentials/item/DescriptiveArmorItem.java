package com.sxnnyside.emeraldessentials.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

public class DescriptiveArmorItem extends ArmorItem {
  private final ChatFormatting loreColor;

  public DescriptiveArmorItem(
      @NotNull Holder<ArmorMaterial> material,
      @NotNull Type type,
      @NotNull Properties properties,
      @NotNull ChatFormatting loreColor) {
    super(material, type, properties);
    this.loreColor = loreColor;
  }

  @Override
  public void appendHoverText(
      @NotNull ItemStack stack,
      @NotNull TooltipContext context,
      @NotNull List<Component> tooltipComponents,
      @NotNull TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable(this.getDescriptionId() + ".lore")
            .withStyle(loreColor, ChatFormatting.ITALIC));
  }
}
