package com.sxnnyside.emeraldessentials.init;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.block.IllusionalFlowerBlock;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public final class ModBlocks {
  private static final Map<ResourceLocation, Block> ENTRIES = new LinkedHashMap<>();

  public static final IllusionalFlowerBlock ILLUSIONAL_FLOWER =
      register("illusional_flower", IllusionalFlowerBlock.create());

  private ModBlocks() {}

  private static <T extends Block> T register(String name, T block) {
    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, name);
    ENTRIES.put(id, block);
    return block;
  }

  public static Map<ResourceLocation, Block> getEntries() {
    return Collections.unmodifiableMap(ENTRIES);
  }

  public static void registerVanilla() {
    ENTRIES.forEach((id, block) -> Registry.register(BuiltInRegistries.BLOCK, id, block));
  }
}
