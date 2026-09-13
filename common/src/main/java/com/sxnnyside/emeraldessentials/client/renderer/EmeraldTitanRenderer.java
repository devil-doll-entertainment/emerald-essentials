package com.sxnnyside.emeraldessentials.client.renderer;

import com.sxnnyside.emeraldessentials.client.model.EmeraldTitanGeoModel;
import com.sxnnyside.emeraldessentials.entity.EmeraldTitan;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EmeraldTitanRenderer extends GeoEntityRenderer<EmeraldTitan> {
  public EmeraldTitanRenderer(EntityRendererProvider.Context context) {
    super(context, new EmeraldTitanGeoModel());
  }
}
