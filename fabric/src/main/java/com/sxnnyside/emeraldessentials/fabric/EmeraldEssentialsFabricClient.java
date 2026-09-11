package com.sxnnyside.emeraldessentials.fabric;

import com.sxnnyside.emeraldessentials.init.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class EmeraldEssentialsFabricClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ILLUSIONAL_FLOWER, RenderType.cutout());
  }
}
