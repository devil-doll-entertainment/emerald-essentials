package com.sxnnyside.emeraldessentials.client.renderer;

import com.sxnnyside.emeraldessentials.client.model.MushroomBupGeoModel;
import com.sxnnyside.emeraldessentials.entity.MushroomBup;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MushroomBupRenderer extends GeoEntityRenderer<MushroomBup> {
  public MushroomBupRenderer(EntityRendererProvider.Context context) {
    super(context, new MushroomBupGeoModel());
  }
}
