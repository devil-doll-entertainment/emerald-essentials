package com.sxnnyside.emeraldessentials.init;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.entity.AmethystScarab;
import com.sxnnyside.emeraldessentials.entity.EmeraldScuttler;
import com.sxnnyside.emeraldessentials.entity.EmeraldTitan;
import com.sxnnyside.emeraldessentials.entity.Mantabu;
import com.sxnnyside.emeraldessentials.entity.MushroomBup;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntities {
  private static final Map<ResourceLocation, EntityType<?>> ENTRIES = new LinkedHashMap<>();

  public static final EntityType<EmeraldScuttler> EMERALD_SCUTTLER =
      register(
          "emerald_scuttler",
          EntityType.Builder.of(EmeraldScuttler::new, MobCategory.MONSTER)
              .sized(0.9F, 0.6F)
              .clientTrackingRange(8)
              .build("emerald_scuttler"));

  public static final EntityType<EmeraldTitan> EMERALD_TITAN =
      register(
          "emerald_titan",
          EntityType.Builder.of(EmeraldTitan::new, MobCategory.MONSTER)
              .sized(2.2F, 4.6F)
              .fireImmune()
              .clientTrackingRange(10)
              .build("emerald_titan"));

  public static final EntityType<AmethystScarab> AMETHYST_SCARAB =
      register(
          "amethyst_scarab",
          EntityType.Builder.of(AmethystScarab::new, MobCategory.MONSTER)
              .sized(0.5F, 0.4F)
              .clientTrackingRange(6)
              .build("amethyst_scarab"));

  public static final EntityType<MushroomBup> MUSHROOM_BUP =
      register(
          "mushroom_bup",
          EntityType.Builder.of(MushroomBup::new, MobCategory.CREATURE)
              .sized(0.5F, 0.7F)
              .clientTrackingRange(6)
              .build("mushroom_bup"));

  public static final EntityType<Mantabu> MANTABU =
      register(
          "mantabu",
          EntityType.Builder.of(Mantabu::new, MobCategory.CREATURE)
              .sized(1.4F, 0.9F)
              .clientTrackingRange(8)
              .build("mantabu"));

  private ModEntities() {}

  private static <T extends EntityType<?>> T register(String name, T entityType) {
    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, name);
    ENTRIES.put(id, entityType);
    return entityType;
  }

  public static Map<ResourceLocation, EntityType<?>> getEntries() {
    return Collections.unmodifiableMap(ENTRIES);
  }

  public static void registerVanilla() {
    ENTRIES.forEach((id, type) -> Registry.register(BuiltInRegistries.ENTITY_TYPE, id, type));
  }
}
