package com.sxnnyside.emeraldessentials.client.model;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.entity.Mantabu;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MantabuGeoModel extends GeoModel<Mantabu> {
  private static final ResourceLocation MODEL =
      ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, "geo/mantabu.geo.json");
  private static final ResourceLocation TEXTURE =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "textures/entity/mantabu.png");
  private static final ResourceLocation ANIMATIONS =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "animations/mantabu.animation.json");

  @Override
  public ResourceLocation getModelResource(Mantabu entity) {
    return MODEL;
  }

  @Override
  public ResourceLocation getTextureResource(Mantabu entity) {
    return TEXTURE;
  }

  @Override
  public ResourceLocation getAnimationResource(Mantabu entity) {
    return ANIMATIONS;
  }
}
