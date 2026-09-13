package com.sxnnyside.emeraldessentials.client.model;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.entity.EmeraldTitan;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmeraldTitanGeoModel extends GeoModel<EmeraldTitan> {
  private static final ResourceLocation MODEL =
      ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, "geo/emerald_titan.geo.json");
  private static final ResourceLocation TEXTURE =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "textures/entity/emerald_titan.png");
  private static final ResourceLocation ANIMATIONS =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "animations/emerald_titan.animation.json");

  @Override
  public ResourceLocation getModelResource(EmeraldTitan entity) {
    return MODEL;
  }

  @Override
  public ResourceLocation getTextureResource(EmeraldTitan entity) {
    return TEXTURE;
  }

  @Override
  public ResourceLocation getAnimationResource(EmeraldTitan entity) {
    return ANIMATIONS;
  }
}
