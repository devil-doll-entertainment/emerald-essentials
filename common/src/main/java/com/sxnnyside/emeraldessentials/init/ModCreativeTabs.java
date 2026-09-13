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
                    output.accept(ModItems.EMERALD_APPLE);
                    output.accept(ModItems.ENCHANTED_EMERALD);
                    output.accept(ModItems.EMERALD_MIRROR);
                    output.accept(ModItems.EMERALD_WHETSTONE);
                    output.accept(ModItems.RUBY);
                    output.accept(ModItems.RUBY_SWORD);
                    output.accept(ModItems.RUBY_DAGGER);
                    output.accept(ModItems.RUBY_PICKAXE);
                    output.accept(ModItems.RUBY_AXE);
                    output.accept(ModItems.RUBY_SHOVEL);
                    output.accept(ModItems.RUBY_HOE);
                    output.accept(ModItems.RUBY_HELMET);
                    output.accept(ModItems.RUBY_CHESTPLATE);
                    output.accept(ModItems.RUBY_LEGGINGS);
                    output.accept(ModItems.RUBY_BOOTS);
                    output.accept(ModItems.RUBY_ORE);
                    output.accept(ModItems.DEEPSLATE_RUBY_ORE);
                    output.accept(ModItems.RUBY_BLOCK);
                    output.accept(ModItems.AMETHYST_ALTAR);
                    output.accept(ModItems.AMETHYST_RESONATOR);
                    output.accept(ModItems.AMETHYST_LENS);
                    output.accept(ModItems.EMERALD_SHIELD);
                    output.accept(ModItems.EMERALD_BOW);
                    output.accept(ModItems.RUBY_CHARM);
                    output.accept(ModItems.ILLUSIONAL_FLOWER);
                    output.accept(ModItems.CINDER_BLOSSOM);
                    output.accept(ModItems.FROST_LILY);
                    output.accept(ModItems.SOLAR_DAISY);
                    output.accept(ModItems.GALE_PETAL);
                    output.accept(ModItems.ECHO_VIOLET);
                    output.accept(ModItems.SHADOW_ORCHID);
                    output.accept(ModItems.VITALLIA);
                    output.accept(ModItems.THUNDER_POPPY);
                    output.accept(ModItems.VOID_CHRYSANTHEMUM);
                    output.accept(ModItems.SLIME_LOTUS);
                    output.accept(ModItems.EMBER_ROSE);
                    output.accept(ModItems.AURA_MARIGOLD);
                    output.accept(ModItems.EMERALD_KEYSTONE);
                    output.accept(ModItems.EMERALD_CORE);
                    output.accept(ModItems.EMERALD_CROWN);
                    output.accept(ModItems.EMERALD_STAFF);
                    output.accept(ModItems.EMERALD_SCUTTLER_SPAWN_EGG);
                    output.accept(ModItems.EMERALD_TITAN_SPAWN_EGG);
                    output.accept(ModItems.AMETHYST_SCARAB_SPAWN_EGG);
                    output.accept(ModItems.MUSHROOM_BUP_SPAWN_EGG);
                    output.accept(ModItems.MANTABU_SPAWN_EGG);
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
