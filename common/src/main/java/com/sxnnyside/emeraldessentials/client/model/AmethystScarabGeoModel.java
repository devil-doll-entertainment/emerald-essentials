package com.sxnnyside.emeraldessentials.client.model;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.entity.AmethystScarab;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AmethystScarabGeoModel extends GeoModel<AmethystScarab> {
  private static final ResourceLocation MODEL =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "geo/amethyst_scarab.geo.json");
  private static final ResourceLocation TEXTURE =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "textures/entity/amethyst_scarab.png");
  private static final ResourceLocation ANIMATIONS =
      ResourceLocation.fromNamespaceAndPath(
          EmeraldEssentials.MOD_ID, "animations/amethyst_scarab.animation.json");

  @Override
  public ResourceLocation getModelResource(AmethystScarab entity) {
    return MODEL;
  }

  @Override
  public ResourceLocation getTextureResource(AmethystScarab entity) {
    return TEXTURE;
  }

  @Override
  public ResourceLocation getAnimationResource(AmethystScarab entity) {
    return ANIMATIONS;
  }
}
