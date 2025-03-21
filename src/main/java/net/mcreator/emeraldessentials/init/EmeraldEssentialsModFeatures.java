
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.emeraldessentials.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

import net.mcreator.emeraldessentials.world.features.plants.IlussionalFlowerFeature;
import net.mcreator.emeraldessentials.EmeraldEssentialsMod;

@Mod.EventBusSubscriber
public class EmeraldEssentialsModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, EmeraldEssentialsMod.MODID);
	public static final RegistryObject<Feature<?>> ILUSSIONAL_FLOWER = REGISTRY.register("ilussional_flower", IlussionalFlowerFeature::feature);
}
