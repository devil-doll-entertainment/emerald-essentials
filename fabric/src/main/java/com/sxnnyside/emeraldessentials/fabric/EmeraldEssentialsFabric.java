package com.sxnnyside.emeraldessentials.fabric;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.enchantment.ModEnchantmentHandler;
import com.sxnnyside.emeraldessentials.init.ModBlocks;
import com.sxnnyside.emeraldessentials.init.ModCreativeTabs;
import com.sxnnyside.emeraldessentials.init.ModItems;
import com.sxnnyside.emeraldessentials.init.ModPotions;
import com.sxnnyside.emeraldessentials.item.ModArmorMaterials;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.world.entity.LivingEntity;

public class EmeraldEssentialsFabric implements ModInitializer {
  @Override
  public void onInitialize() {
    EmeraldEssentials.init();
    ModArmorMaterials.init();
    ModBlocks.registerVanilla();
    ModItems.registerVanilla();
    ModPotions.registerVanilla();
    com.sxnnyside.emeraldessentials.init.ModEntities.registerVanilla();
    ModCreativeTabs.registerVanilla();

    net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry.register(
        com.sxnnyside.emeraldessentials.init.ModEntities.EMERALD_SCUTTLER,
        com.sxnnyside.emeraldessentials.entity.EmeraldScuttler.createAttributes());
    net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry.register(
        com.sxnnyside.emeraldessentials.init.ModEntities.EMERALD_TITAN,
        com.sxnnyside.emeraldessentials.entity.EmeraldTitan.createAttributes());
    net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry.register(
        com.sxnnyside.emeraldessentials.init.ModEntities.AMETHYST_SCARAB,
        com.sxnnyside.emeraldessentials.entity.AmethystScarab.createAttributes());
    net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry.register(
        com.sxnnyside.emeraldessentials.init.ModEntities.MUSHROOM_BUP,
        com.sxnnyside.emeraldessentials.entity.MushroomBup.createAttributes());
    net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry.register(
        com.sxnnyside.emeraldessentials.init.ModEntities.MANTABU,
        com.sxnnyside.emeraldessentials.entity.Mantabu.createAttributes());

    net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder.BUILD.register(
        com.sxnnyside.emeraldessentials.init.ModBrewingRecipes::registerWithBuilder);

    // Biome modification for Illusional Flower subterranean cave generation
    registerFeature(
        net.fabricmc.fabric.api.biome.v1.BiomeSelectors.foundInOverworld(),
        net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION,
        "illusional_flower");

    registerFeature(
        net.fabricmc.fabric.api.biome.v1.BiomeSelectors.foundInOverworld(),
        net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES,
        "ruby_ore");

    registerFeature(
        net.fabricmc.fabric.api.biome.v1.BiomeSelectors.foundInTheNether(),
        net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION,
        "cinder_blossom");

    registerFeature(
        net.fabricmc.fabric.api.biome.v1.BiomeSelectors.foundInTheEnd(),
        net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION,
        "void_chrysanthemum");

    String[] overworldFlowers = {
      "frost_lily",
      "solar_daisy",
      "gale_petal",
      "echo_violet",
      "shadow_orchid",
      "vitallia",
      "thunder_poppy",
      "slime_lotus",
      "ember_rose",
      "aura_marigold"
    };
    for (String flower : overworldFlowers) {
      registerFeature(
          net.fabricmc.fabric.api.biome.v1.BiomeSelectors.foundInOverworld(),
          net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION,
          flower);
    }

    // Vanilla Creative Tab Injections
    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.world.item.CreativeModeTabs.COMBAT)
        .register(
            entries -> {
              entries.accept(ModItems.EMERALD_SWORD);
              entries.accept(ModItems.EMERALD_DAGGER);
              entries.accept(ModItems.EMERALD_AXE);
              entries.accept(ModItems.EMERALD_HELMET);
              entries.accept(ModItems.EMERALD_CHESTPLATE);
              entries.accept(ModItems.EMERALD_LEGGINGS);
              entries.accept(ModItems.EMERALD_BOOTS);
              entries.accept(ModItems.RUBY_SWORD);
              entries.accept(ModItems.RUBY_DAGGER);
              entries.accept(ModItems.RUBY_AXE);
              entries.accept(ModItems.RUBY_HELMET);
              entries.accept(ModItems.RUBY_CHESTPLATE);
              entries.accept(ModItems.RUBY_LEGGINGS);
              entries.accept(ModItems.RUBY_BOOTS);
              entries.accept(ModItems.EMERALD_SHIELD);
              entries.accept(ModItems.EMERALD_BOW);
              entries.accept(ModItems.AMETHYST_RESONATOR);
              entries.accept(ModItems.EMERALD_CROWN);
              entries.accept(ModItems.EMERALD_STAFF);
            });

    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.world.item.CreativeModeTabs.TOOLS_AND_UTILITIES)
        .register(
            entries -> {
              entries.accept(ModItems.EMERALD_SHOVEL);
              entries.accept(ModItems.EMERALD_PICKAXE);
              entries.accept(ModItems.EMERALD_AXE);
              entries.accept(ModItems.EMERALD_HOE);
              entries.accept(ModItems.EMERALD_MIRROR);
              entries.accept(ModItems.EMERALD_WHETSTONE);
              entries.accept(ModItems.RUBY_SHOVEL);
              entries.accept(ModItems.RUBY_PICKAXE);
              entries.accept(ModItems.RUBY_AXE);
              entries.accept(ModItems.RUBY_HOE);
              entries.accept(ModItems.AMETHYST_LENS);
              entries.accept(ModItems.RUBY_CHARM);
            });

    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.world.item.CreativeModeTabs.FOOD_AND_DRINKS)
        .register(
            entries -> {
              entries.accept(ModItems.EMERALD_APPLE);
            });

    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.world.item.CreativeModeTabs.INGREDIENTS)
        .register(
            entries -> {
              entries.accept(ModItems.ENCHANTED_EMERALD);
              entries.accept(ModItems.RUBY);
              entries.accept(ModItems.EMERALD_KEYSTONE);
              entries.accept(ModItems.EMERALD_CORE);
            });

    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.world.item.CreativeModeTabs.SPAWN_EGGS)
        .register(
            entries -> {
              entries.accept(ModItems.EMERALD_SCUTTLER_SPAWN_EGG);
              entries.accept(ModItems.EMERALD_TITAN_SPAWN_EGG);
            });

    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.world.item.CreativeModeTabs.BUILDING_BLOCKS)
        .register(
            entries -> {
              entries.accept(ModItems.RUBY_BLOCK);
            });

    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.world.item.CreativeModeTabs.NATURAL_BLOCKS)
        .register(
            entries -> {
              entries.accept(ModItems.RUBY_ORE);
              entries.accept(ModItems.DEEPSLATE_RUBY_ORE);
              entries.accept(ModItems.ILLUSIONAL_FLOWER);
              entries.accept(ModItems.CINDER_BLOSSOM);
              entries.accept(ModItems.FROST_LILY);
              entries.accept(ModItems.SOLAR_DAISY);
              entries.accept(ModItems.GALE_PETAL);
              entries.accept(ModItems.ECHO_VIOLET);
              entries.accept(ModItems.SHADOW_ORCHID);
              entries.accept(ModItems.VITALLIA);
              entries.accept(ModItems.THUNDER_POPPY);
              entries.accept(ModItems.VOID_CHRYSANTHEMUM);
              entries.accept(ModItems.SLIME_LOTUS);
              entries.accept(ModItems.EMBER_ROSE);
              entries.accept(ModItems.AURA_MARIGOLD);
            });

    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.world.item.CreativeModeTabs.FUNCTIONAL_BLOCKS)
        .register(
            entries -> {
              entries.accept(ModItems.AMETHYST_ALTAR);
            });

    ServerTickEvents.END_SERVER_TICK.register(
        server -> {
          server.getPlayerList().getPlayers().forEach(ModEnchantmentHandler::onPlayerTick);
          server
              .getPlayerList()
              .getPlayers()
              .forEach(com.sxnnyside.emeraldessentials.entity.MushroomBupSpawner::onPlayerTick);
          server
              .getPlayerList()
              .getPlayers()
              .forEach(com.sxnnyside.emeraldessentials.entity.MantabuSpawner::onPlayerTick);
        });

    ServerLivingEntityEvents.AFTER_DAMAGE.register(
        (entity, source, baseDamageTaken, damageTaken, blocked) -> {
          if (source.getEntity() instanceof LivingEntity attacker) {
            ModEnchantmentHandler.onLivingHurt(entity, attacker);
          }
        });

    net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents.AFTER.register(
        (level, player, pos, state, blockEntity) ->
            com.sxnnyside.emeraldessentials.entity.AmethystScarabSpawner.onBlockBreak(
                level, pos, state));
  }

  private static void registerFeature(
      java.util.function.Predicate<net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext> selector,
      net.minecraft.world.level.levelgen.GenerationStep.Decoration step,
      String name) {
    net.fabricmc.fabric.api.biome.v1.BiomeModifications.addFeature(
        selector,
        step,
        net.minecraft.resources.ResourceKey.create(
            net.minecraft.core.registries.Registries.PLACED_FEATURE,
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                EmeraldEssentials.MOD_ID, name)));
  }
}
