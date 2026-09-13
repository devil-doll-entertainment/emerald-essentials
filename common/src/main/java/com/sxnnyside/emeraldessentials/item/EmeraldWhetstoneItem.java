package com.sxnnyside.emeraldessentials.item;

import com.sxnnyside.emeraldessentials.config.ModConfig;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EmeraldWhetstoneItem extends Item {

  private static final int REPAIR_AMOUNT = 150;
  private static final int COOLDOWN_TICKS = 600; // 30 seconds

  public EmeraldWhetstoneItem(Properties properties) {
    super(properties.durability(32).rarity(Rarity.UNCOMMON));
  }

  @Override
  public void appendHoverText(
      @NotNull ItemStack stack,
      @NotNull TooltipContext context,
      @NotNull List<Component> tooltipComponents,
      @NotNull TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_whetstone.desc")
            .withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_whetstone.lore")
            .withStyle(ChatFormatting.DARK_GREEN, ChatFormatting.ITALIC));
  }

  @Override
  public @NotNull InteractionResultHolder<ItemStack> use(
      @NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
    ItemStack whetstoneStack = player.getItemInHand(hand);
    InteractionHand otherHand =
        (hand == InteractionHand.MAIN_HAND) ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
    ItemStack targetStack = player.getItemInHand(otherHand);

    if (!isSharpenableEmeraldTool(targetStack)) {
      if (level.isClientSide()) {
        player.displayClientMessage(
            Component.translatable("message.emerald_essentials.whetstone_need_tool"), true);
      }
      return InteractionResultHolder.fail(whetstoneStack);
    }

    if (!level.isClientSide()) {
      // Repair the emerald tool
      if (targetStack.isDamaged()) {
        int currentDamage = targetStack.getDamageValue();
        targetStack.setDamageValue(Math.max(0, currentDamage - REPAIR_AMOUNT));
      }

      // Apply tailored enhancement buff
      int buffDurationTicks = ModConfig.data().gameplay().whetstoneBonusDurationSeconds() * 20;
      if (targetStack.getItem() instanceof DiggerItem) {
        player.addEffect(
            new MobEffectInstance(MobEffects.DIG_SPEED, buffDurationTicks, 1)); // Haste II
      } else {
        player.addEffect(
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, buffDurationTicks, 0)); // Strength I
      }

      // Damage whetstone & apply cooldown
      EquipmentSlot slot =
          (hand == InteractionHand.MAIN_HAND) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
      whetstoneStack.hurtAndBreak(1, player, slot);
      player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

      // Play audiovisual feedback
      level.playSound(
          null,
          player.getX(),
          player.getY(),
          player.getZ(),
          SoundEvents.GRINDSTONE_USE,
          SoundSource.PLAYERS,
          1.0F,
          1.2F);
      level.playSound(
          null,
          player.getX(),
          player.getY(),
          player.getZ(),
          SoundEvents.AMETHYST_BLOCK_CHIME,
          SoundSource.PLAYERS,
          0.8F,
          1.5F);

      player.displayClientMessage(
          Component.translatable(
              "message.emerald_essentials.whetstone_sharpened", targetStack.getHoverName()),
          true);
    }

    return InteractionResultHolder.sidedSuccess(whetstoneStack, level.isClientSide());
  }

  private boolean isSharpenableEmeraldTool(ItemStack stack) {
    if (stack.isEmpty()) {
      return false;
    }
    return stack.getItem() instanceof TieredItem tiered && tiered.getTier() == ModToolTiers.EMERALD;
  }
}
