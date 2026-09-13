package com.sxnnyside.emeraldessentials.item;

import com.sxnnyside.emeraldessentials.config.ModConfig;
import java.util.List;
import java.util.Set;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class EmeraldMirrorItem extends Item {

  public EmeraldMirrorItem(Properties properties) {
    super(properties.stacksTo(1).rarity(Rarity.RARE));
  }

  @Override
  public void appendHoverText(
      @NotNull ItemStack stack,
      @NotNull TooltipContext context,
      @NotNull List<Component> tooltipComponents,
      @NotNull TooltipFlag tooltipFlag) {
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_mirror.desc")
            .withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.emerald_mirror.lore")
            .withStyle(ChatFormatting.DARK_GREEN, ChatFormatting.ITALIC));
  }

  @Override
  public @NotNull InteractionResultHolder<ItemStack> use(
      @NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
    ItemStack stack = player.getItemInHand(hand);

    if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
      ServerLevel originLevel = serverPlayer.serverLevel();
      double originX = serverPlayer.getX();
      double originY = serverPlayer.getY();
      double originZ = serverPlayer.getZ();

      DimensionTransition transition =
          serverPlayer.findRespawnPositionAndUseSpawnBlock(false, DimensionTransition.DO_NOTHING);

      ServerLevel destLevel;
      double destX;
      double destY;
      double destZ;
      float destYRot;
      float destXRot;

      if (transition != null) {
        destLevel = transition.newLevel();
        Vec3 pos = transition.pos();
        destX = pos.x;
        destY = pos.y;
        destZ = pos.z;
        destYRot = transition.yRot();
        destXRot = transition.xRot();

        if (transition.missingRespawnBlock()) {
          serverPlayer.sendSystemMessage(
              Component.translatable("message.emerald_essentials.mirror_bed_missing"));
        } else {
          serverPlayer.sendSystemMessage(
              Component.translatable("message.emerald_essentials.mirror_teleported"));
        }
      } else {
        destLevel = serverPlayer.server.overworld();
        BlockPos spawn = destLevel.getSharedSpawnPos();
        destX = spawn.getX() + 0.5D;
        destY = spawn.getY();
        destZ = spawn.getZ() + 0.5D;
        destYRot = 0.0F;
        destXRot = 0.0F;
        serverPlayer.sendSystemMessage(
            Component.translatable("message.emerald_essentials.mirror_world_spawn"));
      }

      // Origin audio and particles
      originLevel.playSound(
          null,
          originX,
          originY,
          originZ,
          SoundEvents.AMETHYST_BLOCK_CHIME,
          SoundSource.PLAYERS,
          1.0F,
          1.2F);
      originLevel.playSound(
          null,
          originX,
          originY,
          originZ,
          SoundEvents.CHORUS_FRUIT_TELEPORT,
          SoundSource.PLAYERS,
          1.0F,
          1.0F);

      // Perform teleportation
      serverPlayer.teleportTo(destLevel, destX, destY, destZ, Set.of(), destYRot, destXRot);
      serverPlayer.resetFallDistance();

      // Destination audio
      destLevel.playSound(
          null,
          destX,
          destY,
          destZ,
          SoundEvents.AMETHYST_BLOCK_CHIME,
          SoundSource.PLAYERS,
          1.0F,
          1.2F);
      destLevel.playSound(
          null,
          destX,
          destY,
          destZ,
          SoundEvents.CHORUS_FRUIT_TELEPORT,
          SoundSource.PLAYERS,
          1.0F,
          1.0F);

      // Consume item unless in creative mode or disabled by config
      if (!player.getAbilities().instabuild
          && ModConfig.data().gameplay().emeraldMirrorSingleUse()) {
        stack.shrink(1);
      }
      int cooldownTicks = ModConfig.data().gameplay().emeraldMirrorCooldownSeconds() * 20;
      player.getCooldowns().addCooldown(this, cooldownTicks);
    }

    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
  }
}
