package com.sxnnyside.emeraldessentials.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class EmeraldCrownItem extends ArmorItem {

  public EmeraldCrownItem(Properties properties) {
    super(
        ModArmorMaterials.EMERALD,
        Type.HELMET,
        properties.rarity(Rarity.EPIC).durability(Type.HELMET.getDurability(36)));
  }

  @Override
  public void appendHoverText(
      ItemStack stack,
      TooltipContext context,
      List<Component> tooltipComponents,
      TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_crown.desc")
            .withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_crown.lore")
            .withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC));
  }

  @Override
  public void inventoryTick(
      ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
    if (!(entity instanceof Player player) || level.isClientSide) {
      return;
    }

    // Must be equipped in the head slot
    if (player.getItemBySlot(EquipmentSlot.HEAD) == stack) {
      // Passive: Hero of the Village
      if (player.tickCount % 80 == 0) {
        player.addEffect(
            new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 160, 0, false, false, true));
      }

      // Reactive: Gem Radiance retaliatory burst
      if (player.hurtTime == player.hurtDuration && player.hurtDuration > 0) {
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 1, false, false, true));

        if (level instanceof ServerLevel serverLevel) {
          serverLevel.playSound(
              null,
              player.blockPosition(),
              SoundEvents.AMETHYST_BLOCK_CHIME,
              SoundSource.PLAYERS,
              1.5F,
              1.2F);
          serverLevel.sendParticles(
              ParticleTypes.HAPPY_VILLAGER,
              player.getX(),
              player.getY() + 1.0D,
              player.getZ(),
              20,
              0.5D,
              0.5D,
              0.5D,
              0.1D);

          AABB shockBox = player.getBoundingBox().inflate(5.0D);
          List<LivingEntity> enemies =
              serverLevel.getEntitiesOfClass(LivingEntity.class, shockBox, e -> e instanceof Enemy);
          for (LivingEntity enemy : enemies) {
            enemy.hurt(serverLevel.damageSources().magic(), 4.0F);
          }
        }
      }
    }
  }
}
