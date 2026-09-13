package com.sxnnyside.emeraldessentials.item;

import com.sxnnyside.emeraldessentials.config.ModConfig;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class EmeraldStaffItem extends Item {

  public EmeraldStaffItem(Properties properties) {
    super(properties.rarity(Rarity.EPIC).durability(350));
  }

  @Override
  public void appendHoverText(
      @NotNull ItemStack stack,
      @NotNull TooltipContext context,
      @NotNull List<Component> tooltipComponents,
      @NotNull TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_staff.desc")
            .withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_staff.lore")
            .withStyle(ChatFormatting.DARK_GREEN, ChatFormatting.ITALIC));
  }

  @Override
  public @NotNull InteractionResultHolder<ItemStack> use(
      @NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
    ItemStack stack = player.getItemInHand(hand);

    level.playSound(
        null,
        player.blockPosition(),
        SoundEvents.AMETHYST_BLOCK_RESONATE,
        SoundSource.PLAYERS,
        1.5F,
        1.0F);

    if (level instanceof ServerLevel serverLevel) {
      Vec3 look = player.getLookAngle();
      Vec3 start = player.getEyePosition();

      for (int i = 1; i <= 14; i++) {
        Vec3 point = start.add(look.scale(i));
        serverLevel.sendParticles(
            ParticleTypes.HAPPY_VILLAGER, point.x, point.y, point.z, 4, 0.2D, 0.2D, 0.2D, 0.05D);
        serverLevel.sendParticles(
            ParticleTypes.SCULK_SOUL, point.x, point.y, point.z, 2, 0.1D, 0.1D, 0.1D, 0.02D);

        AABB box =
            new AABB(
                point.x - 1.2D,
                point.y - 1.2D,
                point.z - 1.2D,
                point.x + 1.2D,
                point.y + 1.2D,
                point.z + 1.2D);
        List<LivingEntity> targets =
            serverLevel.getEntitiesOfClass(LivingEntity.class, box, e -> e != player);
        for (LivingEntity target : targets) {
          target.hurt(serverLevel.damageSources().magic(), 10.0F);
          target.setDeltaMovement(look.x * 0.8D, 0.4D, look.z * 0.8D);
        }
      }
    }

    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
    int cdTicks = ModConfig.data().gameplay().staffFireballCooldownTicks();
    player.getCooldowns().addCooldown(this, cdTicks);
    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
  }
}
