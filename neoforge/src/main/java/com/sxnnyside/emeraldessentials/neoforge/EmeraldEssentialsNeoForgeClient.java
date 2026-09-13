package com.sxnnyside.emeraldessentials.neoforge;

import com.sxnnyside.emeraldessentials.init.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Encapsulates all client-only lifecycle events and registration for NeoForge. Kept in an isolated
 * class to guarantee that client classes are never loaded by the JVM on dedicated servers.
 */
public final class EmeraldEssentialsNeoForgeClient {

  private EmeraldEssentialsNeoForgeClient() {}

  public static void init(@NotNull IEventBus modBus) {
    modBus.addListener(EmeraldEssentialsNeoForgeClient::onClientSetup);
    modBus.addListener(EmeraldEssentialsNeoForgeClient::registerRenderers);
  }

  private static void registerRenderers(
      net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(
        com.sxnnyside.emeraldessentials.init.ModEntities.EMERALD_SCUTTLER,
        com.sxnnyside.emeraldessentials.client.renderer.EmeraldScuttlerRenderer::new);
    event.registerEntityRenderer(
        com.sxnnyside.emeraldessentials.init.ModEntities.EMERALD_TITAN,
        com.sxnnyside.emeraldessentials.client.renderer.EmeraldTitanRenderer::new);
    event.registerEntityRenderer(
        com.sxnnyside.emeraldessentials.init.ModEntities.AMETHYST_SCARAB,
        com.sxnnyside.emeraldessentials.client.renderer.AmethystScarabRenderer::new);
    event.registerEntityRenderer(
        com.sxnnyside.emeraldessentials.init.ModEntities.MUSHROOM_BUP,
        com.sxnnyside.emeraldessentials.client.renderer.MushroomBupRenderer::new);
    event.registerEntityRenderer(
        com.sxnnyside.emeraldessentials.init.ModEntities.MANTABU,
        com.sxnnyside.emeraldessentials.client.renderer.MantabuRenderer::new);
  }

  private static void onClientSetup(FMLClientSetupEvent event) {
    event.enqueueWork(
        () -> {
          ItemProperties.register(
              ModItems.EMERALD_BOW,
              ResourceLocation.withDefaultNamespace("pull"),
              (stack, level, entity, seed) -> {
                if (entity == null) {
                  return 0.0F;
                }
                return entity.getUseItem() != stack
                    ? 0.0F
                    : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks())
                        / 15.0F;
              });

          ItemProperties.register(
              ModItems.EMERALD_BOW,
              ResourceLocation.withDefaultNamespace("pulling"),
              (stack, level, entity, seed) -> {
                return entity != null && entity.isUsingItem() && entity.getUseItem() == stack
                    ? 1.0F
                    : 0.0F;
              });

          ItemProperties.register(
              ModItems.EMERALD_SHIELD,
              ResourceLocation.withDefaultNamespace("blocking"),
              (stack, level, entity, seed) -> {
                return entity != null && entity.isUsingItem() && entity.getUseItem() == stack
                    ? 1.0F
                    : 0.0F;
              });
        });
  }
}
