package com.sxnnyside.emeraldessentials.fabric.datagen;

import com.sxnnyside.emeraldessentials.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/** Fabric Data Generator entrypoint connecting to the unified common data providers. */
public class EmeraldEssentialsFabricDataGenerator implements DataGeneratorEntrypoint {

  @Override
  public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
    pack.addProvider(ModRecipeProvider::new);
  }
}
