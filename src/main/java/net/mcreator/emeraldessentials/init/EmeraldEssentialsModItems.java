
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.emeraldessentials.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.mcreator.emeraldessentials.item.Emerald_ToolsSwordItem;
import net.mcreator.emeraldessentials.item.Emerald_ToolsShovelItem;
import net.mcreator.emeraldessentials.item.Emerald_ToolsPickaxeItem;
import net.mcreator.emeraldessentials.item.Emerald_ToolsHoeItem;
import net.mcreator.emeraldessentials.item.Emerald_ToolsAxeItem;
import net.mcreator.emeraldessentials.item.Emerald_ArmorArmorItem;
import net.mcreator.emeraldessentials.item.EmeraldToolsDaggerItem;
import net.mcreator.emeraldessentials.EmeraldEssentialsMod;

public class EmeraldEssentialsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, EmeraldEssentialsMod.MODID);
	public static final RegistryObject<Item> EMERALD_TOOLS_AXE = REGISTRY.register("emerald_tools_axe", () -> new Emerald_ToolsAxeItem());
	public static final RegistryObject<Item> EMERALD_TOOLS_PICKAXE = REGISTRY.register("emerald_tools_pickaxe", () -> new Emerald_ToolsPickaxeItem());
	public static final RegistryObject<Item> EMERALD_TOOLS_SWORD = REGISTRY.register("emerald_tools_sword", () -> new Emerald_ToolsSwordItem());
	public static final RegistryObject<Item> EMERALD_TOOLS_SHOVEL = REGISTRY.register("emerald_tools_shovel", () -> new Emerald_ToolsShovelItem());
	public static final RegistryObject<Item> EMERALD_TOOLS_HOE = REGISTRY.register("emerald_tools_hoe", () -> new Emerald_ToolsHoeItem());
	public static final RegistryObject<Item> EMERALD_ARMOR_ARMOR_HELMET = REGISTRY.register("emerald_armor_armor_helmet", () -> new Emerald_ArmorArmorItem.Helmet());
	public static final RegistryObject<Item> EMERALD_ARMOR_ARMOR_CHESTPLATE = REGISTRY.register("emerald_armor_armor_chestplate", () -> new Emerald_ArmorArmorItem.Chestplate());
	public static final RegistryObject<Item> EMERALD_ARMOR_ARMOR_LEGGINGS = REGISTRY.register("emerald_armor_armor_leggings", () -> new Emerald_ArmorArmorItem.Leggings());
	public static final RegistryObject<Item> EMERALD_ARMOR_ARMOR_BOOTS = REGISTRY.register("emerald_armor_armor_boots", () -> new Emerald_ArmorArmorItem.Boots());
	public static final RegistryObject<Item> EMERALD_TOOLS_DAGGER = REGISTRY.register("emerald_tools_dagger", () -> new EmeraldToolsDaggerItem());
	public static final RegistryObject<Item> ILUSSIONAL_FLOWER = block(EmeraldEssentialsModBlocks.ILUSSIONAL_FLOWER, CreativeModeTab.TAB_BREWING);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
