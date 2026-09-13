package com.sxnnyside.emeraldessentials.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

public class EmeraldDaggerItem extends SwordItem {
  public EmeraldDaggerItem(Tier tier, Properties properties) {
    super(tier, properties);
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
    ChatFormatting loreColor =
        (getTier() == ModToolTiers.RUBY) ? ChatFormatting.GOLD : ChatFormatting.DARK_GREEN;
    tooltipComponents.add(
        Component.translatable(this.getDescriptionId() + ".lore")
            .withStyle(loreColor, ChatFormatting.ITALIC));
  }

  @Override
  public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    // Backstab bonus: if attacker is behind target (facing same general direction)
    // or invisible/sneaking, deal bonus critical / bypass invulnerability delay
    net.minecraft.world.phys.Vec3 attackerLook = attacker.getViewVector(1.0F).normalize();
    net.minecraft.world.phys.Vec3 targetLook = target.getViewVector(1.0F).normalize();
    boolean isBackstab = attackerLook.dot(targetLook) > 0.6D;

    if (com.sxnnyside.emeraldessentials.config.ModConfig.get().isEnableBackstabBonus()
        && (isBackstab || attacker.isCrouching() || attacker.isInvisible())) {
      target.invulnerableTime = 0; // Allow rapid follow-up hits
    }
    return super.hurtEnemy(stack, target, attacker);
  }
}
