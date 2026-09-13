package com.sxnnyside.emeraldessentials.item;

import com.sxnnyside.emeraldessentials.init.ModItems;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/** Geothermal ruby talisman granting automated and active fire immunity. */
public class RubyCharmItem extends Item {

  private static final int PASSIVE_COOLDOWN_TICKS = 1200; // 60 seconds
  private static final int ACTIVE_COOLDOWN_TICKS = 900; // 45 seconds
  private static final int PASSIVE_DURATION_TICKS = 300; // 15 seconds
  private static final int ACTIVE_DURATION_TICKS = 400; // 20 seconds

  public RubyCharmItem(Properties properties) {
    super(properties);
  }

  @Override
  public void inventoryTick(
      ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
    if (level.isClientSide() || !(entity instanceof Player player)) {
      return;
    }

    if ((player.isOnFire() || player.isInLava()) && !player.getCooldowns().isOnCooldown(this)) {
      player.clearFire();
      player.addEffect(
          new MobEffectInstance(
              MobEffects.FIRE_RESISTANCE, PASSIVE_DURATION_TICKS, 0, false, true, true));
      player.getCooldowns().addCooldown(this, PASSIVE_COOLDOWN_TICKS);
      stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

      level.playSound(
          null,
          player.getX(),
          player.getY(),
          player.getZ(),
          SoundEvents.FIRECHARGE_USE,
          SoundSource.PLAYERS,
          0.9F,
          1.2F);
      level.playSound(
          null,
          player.getX(),
          player.getY(),
          player.getZ(),
          SoundEvents.LAVA_EXTINGUISH,
          SoundSource.PLAYERS,
          0.8F,
          1.0F);

      player.displayClientMessage(
          Component.translatable("item.emerald_essentials.ruby_charm.activated")
              .withStyle(ChatFormatting.GOLD),
          true);
    }
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack charm = player.getItemInHand(hand);

    if (player.getCooldowns().isOnCooldown(this)) {
      return InteractionResultHolder.fail(charm);
    }

    if (!level.isClientSide()) {
      player.addEffect(
          new MobEffectInstance(
              MobEffects.FIRE_RESISTANCE, ACTIVE_DURATION_TICKS, 0, false, true, true));
      player.getCooldowns().addCooldown(this, ACTIVE_COOLDOWN_TICKS);
      charm.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));

      level.playSound(
          null,
          player.getX(),
          player.getY(),
          player.getZ(),
          SoundEvents.FIRECHARGE_USE,
          SoundSource.PLAYERS,
          1.0F,
          1.0F);
    }

    return InteractionResultHolder.sidedSuccess(charm, level.isClientSide());
  }

  @Override
  public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
    return repairCandidate.is(ModItems.RUBY) || super.isValidRepairItem(stack, repairCandidate);
  }

  @Override
  public void appendHoverText(
      ItemStack stack,
      TooltipContext context,
      List<Component> tooltipComponents,
      TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.ruby_charm.desc")
            .withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.ruby_charm.lore")
            .withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC));
  }
}
