
package net.mcreator.emeraldessentials.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

public class EmeraldToolsDaggerItem extends SwordItem {
	public EmeraldToolsDaggerItem() {
		super(new Tier() {
			public int getUses() {
				return 1400;
			}

			public float getSpeed() {
				return 11f;
			}

			public float getAttackDamageBonus() {
				return 13f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 20;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(Items.EMERALD));
			}
		}, 3, -1.7f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT));
	}
}
