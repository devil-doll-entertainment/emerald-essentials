
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.emeraldessentials.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import net.mcreator.emeraldessentials.enchantment.AmeythistThiefEnchantment;
import net.mcreator.emeraldessentials.enchantment.AmethystShadowEnchantment;
import net.mcreator.emeraldessentials.enchantment.AmethystLevitateEnchantment;
import net.mcreator.emeraldessentials.enchantment.AmethystBlindEnchantment;
import net.mcreator.emeraldessentials.EmeraldEssentialsMod;

public class EmeraldEssentialsModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EmeraldEssentialsMod.MODID);
	public static final RegistryObject<Enchantment> AMETHYST_LEVITATE = REGISTRY.register("amethyst_levitate", () -> new AmethystLevitateEnchantment());
	public static final RegistryObject<Enchantment> AMETHYST_BLIND = REGISTRY.register("amethyst_blind", () -> new AmethystBlindEnchantment());
	public static final RegistryObject<Enchantment> AMEYTHIST_THIEF = REGISTRY.register("ameythist_thief", () -> new AmeythistThiefEnchantment());
	public static final RegistryObject<Enchantment> AMETHYST_SHADOW = REGISTRY.register("amethyst_shadow", () -> new AmethystShadowEnchantment());
}
