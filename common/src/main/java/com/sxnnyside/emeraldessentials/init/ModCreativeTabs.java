package com.sxnnyside.emeraldessentials.init;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class ModCreativeTabs {
  private static final Map<ResourceLocation, CreativeModeTab> ENTRIES = new LinkedHashMap<>();

  public static final CreativeModeTab EMERALD_TAB =
      register(
          "emerald_tab",
          CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
              .title(Component.translatable("itemGroup.emerald_essentials.tab"))
              .icon(() -> new ItemStack(ModItems.EMERALD_SWORD))
              .displayItems(
                  (params, output) -> {
                    output.accept(ModItems.EMERALD_SWORD);
                    output.accept(ModItems.EMERALD_DAGGER);
                    output.accept(ModItems.EMERALD_PICKAXE);
                    output.accept(ModItems.EMERALD_AXE);
                    output.accept(ModItems.EMERALD_SHOVEL);
                    output.accept(ModItems.EMERALD_HOE);
                    output.accept(ModItems.EMERALD_HELMET);
                    output.accept(ModItems.EMERALD_CHESTPLATE);
                    output.accept(ModItems.EMERALD_LEGGINGS);
                    output.accept(ModItems.EMERALD_BOOTS);
                    output.accept(ModItems.ILLUSIONAL_FLOWER);
                  })
              .build());

  private ModCreativeTabs() {}

  private static CreativeModeTab register(String name, CreativeModeTab tab) {
    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, name);
    ENTRIES.put(id, tab);
    return tab;
  }

  public static Map<ResourceLocation, CreativeModeTab> getEntries() {
    return Collections.unmodifiableMap(ENTRIES);
  }

  public static void registerVanilla() {
    ENTRIES.forEach((id, tab) -> Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, id, tab));
  }
}
