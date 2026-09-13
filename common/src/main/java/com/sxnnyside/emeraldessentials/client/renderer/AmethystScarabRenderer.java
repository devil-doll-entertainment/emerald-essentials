package com.sxnnyside.emeraldessentials.client.renderer;

import com.sxnnyside.emeraldessentials.client.model.AmethystScarabGeoModel;
import com.sxnnyside.emeraldessentials.entity.AmethystScarab;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AmethystScarabRenderer extends GeoEntityRenderer<AmethystScarab> {
  public AmethystScarabRenderer(EntityRendererProvider.Context context) {
    super(context, new AmethystScarabGeoModel());
  }
}
