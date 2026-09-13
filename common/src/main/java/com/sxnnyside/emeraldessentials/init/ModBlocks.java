package com.sxnnyside.emeraldessentials.init;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.block.AmethystAltarBlock;
import com.sxnnyside.emeraldessentials.block.IllusionalFlowerBlock;
import com.sxnnyside.emeraldessentials.block.MagicFlowerBlock;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ModBlocks {
  private static final Map<ResourceLocation, Block> ENTRIES = new LinkedHashMap<>();

  public static final IllusionalFlowerBlock ILLUSIONAL_FLOWER =
      register("illusional_flower", IllusionalFlowerBlock.create());

  // 12 Additional Magical Flora Blocks
  public static final MagicFlowerBlock CINDER_BLOSSOM =
      register("cinder_blossom", MagicFlowerBlock.createCinderBlossom());

  public static final MagicFlowerBlock FROST_LILY =
      register("frost_lily", MagicFlowerBlock.createFrostLily());

  public static final MagicFlowerBlock SOLAR_DAISY =
      register("solar_daisy", MagicFlowerBlock.createSolarDaisy());

  public static final MagicFlowerBlock GALE_PETAL =
      register("gale_petal", MagicFlowerBlock.createGalePetal());

  public static final MagicFlowerBlock ECHO_VIOLET =
      register("echo_violet", MagicFlowerBlock.createEchoViolet());

  public static final MagicFlowerBlock SHADOW_ORCHID =
      register("shadow_orchid", MagicFlowerBlock.createShadowOrchid());

  public static final MagicFlowerBlock VITALLIA =
      register("vitallia", MagicFlowerBlock.createVitallia());

  public static final MagicFlowerBlock THUNDER_POPPY =
      register("thunder_poppy", MagicFlowerBlock.createThunderPoppy());

  public static final MagicFlowerBlock VOID_CHRYSANTHEMUM =
      register("void_chrysanthemum", MagicFlowerBlock.createVoidChrysanthemum());

  public static final MagicFlowerBlock SLIME_LOTUS =
      register("slime_lotus", MagicFlowerBlock.createSlimeLotus());

  public static final MagicFlowerBlock EMBER_ROSE =
      register("ember_rose", MagicFlowerBlock.createEmberRose());

  public static final MagicFlowerBlock AURA_MARIGOLD =
      register("aura_marigold", MagicFlowerBlock.createAuraMarigold());

  // Ruby Mineral Blocks
  public static final Block RUBY_ORE =
      register(
          "ruby_ore",
          new Block(
              BlockBehaviour.Properties.of()
                  .sound(SoundType.STONE)
                  .strength(3.0F, 3.0F)
                  .requiresCorrectToolForDrops()));

  public static final Block DEEPSLATE_RUBY_ORE =
      register(
          "deepslate_ruby_ore",
          new Block(
              BlockBehaviour.Properties.of()
                  .sound(SoundType.DEEPSLATE)
                  .strength(4.5F, 3.0F)
                  .requiresCorrectToolForDrops()));

  public static final Block RUBY_BLOCK =
      register(
          "ruby_block",
          new Block(
              BlockBehaviour.Properties.of()
                  .sound(SoundType.METAL)
                  .strength(5.0F, 6.0F)
                  .requiresCorrectToolForDrops()));

  // Magic Infusion Altar
  public static final AmethystAltarBlock AMETHYST_ALTAR =
      register("amethyst_altar", AmethystAltarBlock.create());

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
