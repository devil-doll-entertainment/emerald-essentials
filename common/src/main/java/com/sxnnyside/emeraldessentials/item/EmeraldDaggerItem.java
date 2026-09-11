package com.sxnnyside.emeraldessentials.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class EmeraldDaggerItem extends SwordItem {
  public EmeraldDaggerItem(Tier tier, Properties properties) {
    super(tier, properties);
  }

  @Override
  public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    // Backstab bonus: if attacker is behind target or invisible/sneaking, deal bonus critical
    // effect
    if (attacker.isCrouching() || attacker.isInvisible()) {
      target.invulnerableTime = 0; // Allow rapid follow-up hits
    }
    return super.hurtEnemy(stack, target, attacker);
  }
}
