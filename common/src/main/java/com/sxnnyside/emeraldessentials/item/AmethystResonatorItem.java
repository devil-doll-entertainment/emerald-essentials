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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

/** Arcane resonator that channels sonic shockwaves using amethyst resonance. */
public class AmethystResonatorItem extends Item {

  private static final int COOLDOWN_TICKS = 20;
  private static final float SONIC_DAMAGE = 7.0F;
  private static final double RANGE = 8.0D;

  public AmethystResonatorItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack resonator = player.getItemInHand(hand);

    boolean isCreative = player.getAbilities().instabuild;
    ItemStack shardStack = findAmethystShard(player);

    if (!isCreative && shardStack.isEmpty()) {
      if (level.isClientSide()) {
        player.displayClientMessage(
            Component.translatable("item.emerald_essentials.amethyst_resonator.no_shards")
                .withStyle(ChatFormatting.RED),
            true);
      }
      return InteractionResultHolder.fail(resonator);
    }

    if (!level.isClientSide()) {
      if (!isCreative) {
        shardStack.shrink(1);
      }

      Vec3 eyePos = player.getEyePosition();
      Vec3 look = player.getLookAngle();

      AABB area = player.getBoundingBox().inflate(RANGE);
      List<LivingEntity> targets =
          level.getEntitiesOfClass(
              LivingEntity.class, area, e -> e != player && e.isAlive() && !e.isAlliedTo(player));

      for (LivingEntity target : targets) {
        Vec3 toTarget = target.getEyePosition().subtract(eyePos);
        double dist = toTarget.length();
        if (dist <= RANGE && dist > 0.001D) {
          Vec3 norm = toTarget.normalize();
          if (look.dot(norm) > 0.5D) {
            target.hurt(level.damageSources().indirectMagic(player, player), SONIC_DAMAGE);
            target.knockback(0.85F, -look.x, -look.z);
          }
        }
      }

      ServerLevel serverLevel = (ServerLevel) level;
      Vec3 particleOrigin = eyePos.add(look.scale(1.5D));
      serverLevel.sendParticles(
          ParticleTypes.SONIC_BOOM,
          particleOrigin.x,
          particleOrigin.y,
          particleOrigin.z,
          1,
          0,
          0,
          0,
          0);
      serverLevel.sendParticles(
          ParticleTypes.ELECTRIC_SPARK,
          particleOrigin.x,
          particleOrigin.y,
          particleOrigin.z,
          15,
          look.x * 0.4D,
          look.y * 0.4D,
          look.z * 0.4D,
          0.15D);

      level.playSound(
          null,
          player.getX(),
          player.getY(),
          player.getZ(),
          SoundEvents.WARDEN_SONIC_BOOM,
          SoundSource.PLAYERS,
          0.75F,
          1.5F);
      level.playSound(
          null,
          player.getX(),
          player.getY(),
          player.getZ(),
          SoundEvents.AMETHYST_BLOCK_RESONATE,
          SoundSource.PLAYERS,
          1.0F,
          1.2F);

      resonator.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
      player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
    }

    return InteractionResultHolder.sidedSuccess(resonator, level.isClientSide());
  }

  private ItemStack findAmethystShard(Player player) {
    if (player.getOffhandItem().is(Items.AMETHYST_SHARD)) {
      return player.getOffhandItem();
    }
    for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
      ItemStack stack = player.getInventory().getItem(i);
      if (stack.is(Items.AMETHYST_SHARD)) {
        return stack;
      }
    }
    return ItemStack.EMPTY;
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
        Component.translatable("item.emerald_essentials.amethyst_resonator.desc")
            .withStyle(ChatFormatting.GRAY));
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.amethyst_resonator.ammo")
            .withStyle(ChatFormatting.DARK_PURPLE));
    tooltipComponents.add(
        Component.translatable("item.emerald_essentials.amethyst_resonator.lore")
            .withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.ITALIC));
  }
}
