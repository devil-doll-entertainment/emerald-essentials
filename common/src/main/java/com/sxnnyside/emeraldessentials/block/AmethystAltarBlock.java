package com.sxnnyside.emeraldessentials.block;

import com.sxnnyside.emeraldessentials.enchantment.ModEnchantmentHandler;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AmethystAltarBlock extends Block {

  public static final int REQUIRED_XP_LEVELS = 3;

  public AmethystAltarBlock(BlockBehaviour.Properties properties) {
    super(properties);
  }

  public static AmethystAltarBlock create() {
    return new AmethystAltarBlock(
        BlockBehaviour.Properties.of()
            .sound(SoundType.AMETHYST)
            .strength(3.5F, 6.0F)
            .requiresCorrectToolForDrops()
            .lightLevel(state -> 7));
  }

  @Override
  protected ItemInteractionResult useItemOn(
      ItemStack stack,
      BlockState state,
      Level level,
      BlockPos pos,
      Player player,
      InteractionHand hand,
      BlockHitResult hitResult) {
    if (stack.isEmpty()) {
      return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    // Boss Summoning via Emerald Keystone
    if (stack.is(com.sxnnyside.emeraldessentials.init.ModItems.EMERALD_KEYSTONE)) {
      if (!level.isClientSide()) {
        if (!player.getAbilities().instabuild) {
          stack.shrink(1);
        }
        com.sxnnyside.emeraldessentials.entity.EmeraldTitan titan =
            new com.sxnnyside.emeraldessentials.entity.EmeraldTitan(
                com.sxnnyside.emeraldessentials.init.ModEntities.EMERALD_TITAN, level);
        titan.moveTo(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, 0.0F, 0.0F);
        level.addFreshEntity(titan);
        level.playSound(
            null, pos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 2.0F, 0.6F);
        player.displayClientMessage(
            Component.translatable("message.emerald_essentials.titan_awakened"), true);
      }
      return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    ResourceKey<Enchantment> targetKey = getTargetEnchantment(stack, player.isCrouching());
    if (targetKey == null) {
      if (level.isClientSide()) {
        player.displayClientMessage(
            Component.translatable("message.emerald_essentials.altar_not_applicable"), true);
      }
      return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    // Check amethyst shard
    boolean hasShard = hasAmethystShard(player);
    boolean hasXp =
        player.experienceLevel >= REQUIRED_XP_LEVELS || player.getAbilities().instabuild;

    if (!hasShard) {
      if (level.isClientSide()) {
        player.displayClientMessage(
            Component.translatable("message.emerald_essentials.altar_need_shards"), true);
      }
      return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    if (!hasXp) {
      if (level.isClientSide()) {
        player.displayClientMessage(
            Component.translatable("message.emerald_essentials.altar_need_xp", REQUIRED_XP_LEVELS),
            true);
      }
      return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    Optional<Holder.Reference<Enchantment>> holderOpt =
        level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).get(targetKey);

    if (holderOpt.isEmpty()) {
      return ItemInteractionResult.FAIL;
    }

    Holder.Reference<Enchantment> holder = holderOpt.get();
    int currentLevel = EnchantmentHelper.getItemEnchantmentLevel(holder, stack);
    int maxLevel = holder.value().getMaxLevel();

    if (currentLevel >= maxLevel) {
      if (level.isClientSide()) {
        player.displayClientMessage(
            Component.translatable("message.emerald_essentials.altar_already_max"), true);
      }
      return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
      // Consume shard
      consumeAmethystShard(player);

      // Deduct XP
      if (!player.getAbilities().instabuild) {
        player.giveExperienceLevels(-REQUIRED_XP_LEVELS);
      }

      // Apply enchantment
      stack.enchant(holder, currentLevel + 1);

      // Audio and particles
      serverLevel.playSound(
          null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 1.4F);
      serverLevel.playSound(
          null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

      double cx = pos.getX() + 0.5D;
      double cy = pos.getY() + 1.2D;
      double cz = pos.getZ() + 0.5D;
      serverLevel.sendParticles(ParticleTypes.ENCHANT, cx, cy, cz, 24, 0.3, 0.4, 0.3, 0.1);
      serverLevel.sendParticles(ParticleTypes.END_ROD, cx, cy - 0.2, cz, 12, 0.2, 0.2, 0.2, 0.05);

      player.displayClientMessage(
          Component.translatable(
              "message.emerald_essentials.altar_success", holder.value().description()),
          true);
    }

    return ItemInteractionResult.sidedSuccess(level.isClientSide());
  }

  @Override
  protected InteractionResult useWithoutItem(
      BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    if (level.isClientSide()) {
      player.displayClientMessage(
          Component.translatable("message.emerald_essentials.altar_info"), true);
    }
    return InteractionResult.sidedSuccess(level.isClientSide());
  }

  @Override
  public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
    if (random.nextInt(6) == 0) {
      double x = pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.6D;
      double y = pos.getY() + 1.05D + random.nextDouble() * 0.2D;
      double z = pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.6D;
      level.addParticle(ParticleTypes.END_ROD, x, y, z, 0.0D, 0.015D, 0.0D);
    }
  }

  private ResourceKey<Enchantment> getTargetEnchantment(ItemStack stack, boolean crouching) {
    if (stack.getItem() instanceof ArmorItem armor) {
      return switch (armor.getType()) {
        case HELMET -> ModEnchantmentHandler.AMETHYST_BLIND;
        case CHESTPLATE ->
            crouching
                ? ModEnchantmentHandler.AMETHYST_VITALITY
                : ModEnchantmentHandler.AMETHYST_LEVITATE;
        case LEGGINGS -> ModEnchantmentHandler.AMETHYST_SHADOW;
        case BOOTS -> ModEnchantmentHandler.AMETHYST_THIEF;
        default -> null;
      };
    } else if (stack.getItem() instanceof SwordItem) {
      return ModEnchantmentHandler.AMETHYST_RESONANCE;
    }
    return null;
  }

  private boolean hasAmethystShard(Player player) {
    if (player.getAbilities().instabuild) {
      return true;
    }
    if (player.getOffhandItem().is(Items.AMETHYST_SHARD)) {
      return true;
    }
    for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
      if (player.getInventory().getItem(i).is(Items.AMETHYST_SHARD)) {
        return true;
      }
    }
    return false;
  }

  private void consumeAmethystShard(Player player) {
    if (player.getAbilities().instabuild) {
      return;
    }
    if (player.getOffhandItem().is(Items.AMETHYST_SHARD)) {
      player.getOffhandItem().shrink(1);
      return;
    }
    for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
      ItemStack s = player.getInventory().getItem(i);
      if (s.is(Items.AMETHYST_SHARD)) {
        s.shrink(1);
        return;
      }
    }
  }
}
