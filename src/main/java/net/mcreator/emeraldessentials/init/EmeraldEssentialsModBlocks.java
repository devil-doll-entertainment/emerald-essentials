
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.emeraldessentials.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import net.mcreator.emeraldessentials.block.IlussionalFlowerBlock;
import net.mcreator.emeraldessentials.EmeraldEssentialsMod;

public class EmeraldEssentialsModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, EmeraldEssentialsMod.MODID);
	public static final RegistryObject<Block> ILUSSIONAL_FLOWER = REGISTRY.register("ilussional_flower", () -> new IlussionalFlowerBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			IlussionalFlowerBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
			IlussionalFlowerBlock.itemColorLoad(event);
		}
	}
}
