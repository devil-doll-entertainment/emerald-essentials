package com.sxnnyside.emeraldessentials.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

/** Divination lens that pings subterranean acoustics to reveal nearby life through solid rock. */
public class AmethystLensItem extends Item {

  private static final int COOLDOWN_TICKS = 200;
  private static final int GLOWING_DURATION_TICKS = 160;
  private static final double RANGE = 24.0D;

  public AmethystLensItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack lens = player.getItemInHand(hand);

    if (!level.isClientSide()) {
      AABB detectionBox = player.getBoundingBox().inflate(RANGE);
      List<LivingEntity> targets =
          level.getEntitiesOfClass(
              LivingEntity.class, detectionBox, e -> e != player && e.isAlive());

      for (LivingEntity target : targets) {
        target.addEffect(
            new MobEffectInstance(
                MobEffects.GLOWING, GLOWING_DURATION_TICKS, 0, false, false, true));
      }

      ServerLevel serverLevel = (ServerLevel) level;
      for (int i = 0; i < 24; i++) {
        double angle = i * (Math.PI * 2 / 24);
        double px = player.getX() + Math.cos(angle) * 1.8D;
        double pz = player.getZ() + Math.sin(angle) * 1.8D;
        serverLevel.sendParticles(
            ParticleTypes.ELECTRIC_SPARK, px, player.getY() + 1.0D, pz, 1, 0, 0.05D, 0, 0.02D);
        serverLevel.sendParticles(
            ParticleTypes.PORTAL, px, player.getY() + 1.0D, pz, 2, 0, 0.1D, 0, 0.1D);
      }

      level.playSound(
          null,
          player.getX(),
          player.getY(),
          player.getZ(),
          SoundEvents.AMETHYST_BLOCK_CHIME,
          SoundSource.PLAYERS,
          1.2F,
          1.0F);
      level.playSound(
          null,
          player.getX(),
          player.getY(),
          player.getZ(),
          SoundEvents.BEACON_ACTIVATE,
          SoundSource.PLAYERS,
          0.8F,
          1.8F);

      lens.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
      player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

      if (targets.isEmpty()) {
        player.displayClientMessage(
            Component.translatable("item.emerald_essentials.amethyst_lens.none_found")
                .withStyle(ChatFormatting.GRAY),
            true);
      } else {
        player.displayClientMessage(
            Component.translatable("item.emerald_essentials.amethyst_lens.detected", targets.size())
                .withStyle(ChatFormatting.LIGHT_PURPLE),
            true);
      }
    }

    return InteractionResultHolder.sidedSuccess(lens, level.isClientSide());
  }

  @Override
  public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
    return repairCandidate.is(Items.AMETHYST_SHARD)
        || super.isValidRepairItem(stack, repairCandidate);
  }

  @Override
  public void appendHoverText(
      ItemStack stack,
      TooltipContext context,
      List<Component> tooltipComponents,
      TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.amethyst_lens.desc")
            .withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.amethyst_lens.lore")
            .withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.ITALIC));
  }
}
