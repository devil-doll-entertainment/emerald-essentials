package com.sxnnyside.emeraldessentials.client.renderer;

import com.sxnnyside.emeraldessentials.client.model.EmeraldScuttlerGeoModel;
import com.sxnnyside.emeraldessentials.entity.EmeraldScuttler;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EmeraldScuttlerRenderer extends GeoEntityRenderer<EmeraldScuttler> {
  public EmeraldScuttlerRenderer(EntityRendererProvider.Context context) {
    super(context, new EmeraldScuttlerGeoModel());
  }
}
