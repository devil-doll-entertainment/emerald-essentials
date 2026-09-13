package com.sxnnyside.emeraldessentials.client.model;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.entity.MushroomBup;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MushroomBupGeoModel extends GeoModel<MushroomBup> {
  private static final ResourceLocation MODEL =
      ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, "geo/mushroom_bup.geo.json");
  private static final ResourceLocation TEXTURE =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "textures/entity/mushroom_bup.png");
  private static final ResourceLocation ANIMATIONS =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "animations/mushroom_bup.animation.json");

  @Override
  public ResourceLocation getModelResource(MushroomBup entity) {
    return MODEL;
  }

  @Override
  public ResourceLocation getTextureResource(MushroomBup entity) {
    return TEXTURE;
  }

  @Override
  public ResourceLocation getAnimationResource(MushroomBup entity) {
    return ANIMATIONS;
  }
}
