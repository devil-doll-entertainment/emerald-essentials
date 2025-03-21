
package net.mcreator.emeraldessentials.enchantment;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EquipmentSlot;

public class AmethystShadowEnchantment extends Enchantment {
	public AmethystShadowEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_LEGS, slots);
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	@Override
	public boolean isCurse() {
		return true;
	}

	@Override
	public boolean isTradeable() {
		return false;
	}
}
