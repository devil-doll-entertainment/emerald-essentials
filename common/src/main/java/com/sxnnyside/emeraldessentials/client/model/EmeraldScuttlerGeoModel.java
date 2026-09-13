package com.sxnnyside.emeraldessentials.client.model;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.entity.EmeraldScuttler;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmeraldScuttlerGeoModel extends GeoModel<EmeraldScuttler> {
  private static final ResourceLocation MODEL =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "geo/emerald_scuttler.geo.json");
  private static final ResourceLocation TEXTURE =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "textures/entity/emerald_scuttler.png");
  private static final ResourceLocation ANIMATIONS =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "animations/emerald_scuttler.animation.json");

  @Override
  public ResourceLocation getModelResource(EmeraldScuttler entity) {
    return MODEL;
  }

  @Override
  public ResourceLocation getTextureResource(EmeraldScuttler entity) {
    return TEXTURE;
  }

  @Override
  public ResourceLocation getAnimationResource(EmeraldScuttler entity) {
    return ANIMATIONS;
  }
}
