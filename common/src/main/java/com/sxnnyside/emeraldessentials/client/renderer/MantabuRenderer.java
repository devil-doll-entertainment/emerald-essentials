package com.sxnnyside.emeraldessentials.client.renderer;

import com.sxnnyside.emeraldessentials.client.model.MantabuGeoModel;
import com.sxnnyside.emeraldessentials.entity.Mantabu;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MantabuRenderer extends GeoEntityRenderer<Mantabu> {
  public MantabuRenderer(EntityRendererProvider.Context context) {
    super(context, new MantabuGeoModel());
  }
}
