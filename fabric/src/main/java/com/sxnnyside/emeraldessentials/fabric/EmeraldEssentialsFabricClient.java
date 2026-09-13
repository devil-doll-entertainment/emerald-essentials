package com.sxnnyside.emeraldessentials.fabric;

import com.sxnnyside.emeraldessentials.init.ModBlocks;
import com.sxnnyside.emeraldessentials.init.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class EmeraldEssentialsFabricClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ILLUSIONAL_FLOWER, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CINDER_BLOSSOM, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FROST_LILY, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SOLAR_DAISY, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GALE_PETAL, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ECHO_VIOLET, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SHADOW_ORCHID, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VITALLIA, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.THUNDER_POPPY, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VOID_CHRYSANTHEMUM, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SLIME_LOTUS, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMBER_ROSE, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AURA_MARIGOLD, RenderType.cutout());

    net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(
        com.sxnnyside.emeraldessentials.init.ModEntities.EMERALD_SCUTTLER,
        com.sxnnyside.emeraldessentials.client.renderer.EmeraldScuttlerRenderer::new);
    net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(
        com.sxnnyside.emeraldessentials.init.ModEntities.EMERALD_TITAN,
        com.sxnnyside.emeraldessentials.client.renderer.EmeraldTitanRenderer::new);
    net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(
        com.sxnnyside.emeraldessentials.init.ModEntities.AMETHYST_SCARAB,
        com.sxnnyside.emeraldessentials.client.renderer.AmethystScarabRenderer::new);
    net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(
        com.sxnnyside.emeraldessentials.init.ModEntities.MUSHROOM_BUP,
        com.sxnnyside.emeraldessentials.client.renderer.MushroomBupRenderer::new);
    net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(
        com.sxnnyside.emeraldessentials.init.ModEntities.MANTABU,
        com.sxnnyside.emeraldessentials.client.renderer.MantabuRenderer::new);

    // Bow model predicates
    ItemProperties.register(
        ModItems.EMERALD_BOW,
        ResourceLocation.withDefaultNamespace("pull"),
        (stack, level, entity, seed) -> {
          if (entity == null) {
            return 0.0F;
          }
          return entity.getUseItem() != stack
              ? 0.0F
              : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 15.0F;
        });

    ItemProperties.register(
        ModItems.EMERALD_BOW,
        ResourceLocation.withDefaultNamespace("pulling"),
        (stack, level, entity, seed) -> {
          return entity != null && entity.isUsingItem() && entity.getUseItem() == stack
              ? 1.0F
              : 0.0F;
        });

    // Shield model predicate
    ItemProperties.register(
        ModItems.EMERALD_SHIELD,
        ResourceLocation.withDefaultNamespace("blocking"),
        (stack, level, entity, seed) -> {
          return entity != null && entity.isUsingItem() && entity.getUseItem() == stack
              ? 1.0F
              : 0.0F;
        });
  }
}
