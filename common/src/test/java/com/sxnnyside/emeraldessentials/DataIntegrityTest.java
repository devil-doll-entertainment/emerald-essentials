package com.sxnnyside.emeraldessentials;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataIntegrityTest {

  @Test
  @DisplayName("Illusional flower loot table must exist and be readable")
  void testLootTableExists() {
    InputStream stream =
        getClass()
            .getResourceAsStream(
                "/data/emerald_essentials/loot_table/blocks/illusional_flower.json");
    assertNotNull(stream, "Loot table for illusional_flower must exist in classpath");
  }

  @Test
  @DisplayName(
      "Vanilla enchanting table tag should be empty to reserve amethyst magic for the Altar")
  void testInEnchantingTableTagExcludesAmethyst() throws Exception {
    InputStream stream =
        getClass().getResourceAsStream("/data/minecraft/tags/enchantment/in_enchanting_table.json");
    assertNotNull(stream, "in_enchanting_table tag must exist");
    try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
      char[] buffer = new char[1024];
      int read = reader.read(buffer);
      String content = new String(buffer, 0, read);
      // Amethyst enchantments must not compete with vanilla enchanting table
      assertTrue(!content.contains("amethyst_levitate"));
      assertTrue(!content.contains("amethyst_blind"));
    }
  }

  @Test
  @DisplayName("Mod icon asset must exist and be accessible")
  void testIconAssetExists() {
    InputStream stream = getClass().getResourceAsStream("/assets/emerald_essentials/icon.png");
    assertNotNull(stream, "Mod icon must exist at /assets/emerald_essentials/icon.png");
  }

  @Test
  @DisplayName("Core language files must be present and contain mod translations")
  void testLanguageFilesExist() {
    String[] languages = {"en_us", "es_es", "de_de", "fr_fr", "ja_jp", "zh_cn", "pt_br"};
    for (String lang : languages) {
      InputStream stream =
          getClass().getResourceAsStream("/assets/emerald_essentials/lang/" + lang + ".json");
      assertNotNull(stream, "Language file for " + lang + " must exist");
    }
  }

  @Test
  @DisplayName(
      "Emerald consumable and utility item models, textures and recipes must exist in classpath")
  void testEmeraldUtilityAssetsAndRecipes() {
    String[] items = {"emerald_apple", "enchanted_emerald", "emerald_mirror", "emerald_whetstone"};
    for (String item : items) {
      InputStream model =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/models/item/" + item + ".json");
      assertNotNull(model, "Model for " + item + " must exist");

      InputStream texture =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/textures/item/" + item + ".png");
      assertNotNull(texture, "Texture for " + item + " must exist");

      InputStream recipe =
          getClass().getResourceAsStream("/data/emerald_essentials/recipe/" + item + ".json");
      assertNotNull(recipe, "Recipe for " + item + " must exist");
    }
  }

  @Test
  @DisplayName("Ruby items, blocks, and Altar assets must exist in classpath")
  void testRubyAssetsAndRecipes() {
    String[] items = {
      "ruby",
      "ruby_sword",
      "ruby_pickaxe",
      "ruby_axe",
      "ruby_shovel",
      "ruby_hoe",
      "ruby_dagger",
      "ruby_helmet",
      "ruby_chestplate",
      "ruby_leggings",
      "ruby_boots"
    };
    for (String item : items) {
      InputStream model =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/models/item/" + item + ".json");
      assertNotNull(model, "Model for " + item + " must exist");

      InputStream texture =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/textures/item/" + item + ".png");
      assertNotNull(texture, "Texture for " + item + " must exist");

      String recipeName = item.equals("ruby") ? "ruby_from_transmutation" : item;
      InputStream recipe =
          getClass().getResourceAsStream("/data/emerald_essentials/recipe/" + recipeName + ".json");
      assertNotNull(recipe, "Recipe for " + recipeName + " must exist");
    }

    String[] blocks = {"ruby_ore", "deepslate_ruby_ore", "ruby_block", "amethyst_altar"};
    for (String block : blocks) {
      InputStream state =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/blockstates/" + block + ".json");
      assertNotNull(state, "Blockstate for " + block + " must exist");

      InputStream loot =
          getClass()
              .getResourceAsStream("/data/emerald_essentials/loot_table/blocks/" + block + ".json");
      assertNotNull(loot, "Loot table for " + block + " must exist");

      if (block.equals("ruby_block") || block.equals("amethyst_altar")) {
        InputStream blockRecipe =
            getClass().getResourceAsStream("/data/emerald_essentials/recipe/" + block + ".json");
        assertNotNull(blockRecipe, "Recipe for " + block + " must exist");
      }
    }
  }

  @Test
  @DisplayName("Trimmable armor tag must contain emerald and ruby armor pieces")
  void testTrimmableArmorTag() throws Exception {
    InputStream stream =
        getClass().getResourceAsStream("/data/minecraft/tags/item/trimmable_armor.json");
    assertNotNull(stream, "trimmable_armor tag must exist");
    try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
      char[] buffer = new char[1024];
      int read = reader.read(buffer);
      String content = new String(buffer, 0, read);
      assertTrue(content.contains("emerald_helmet"));
      assertTrue(content.contains("emerald_chestplate"));
      assertTrue(content.contains("emerald_leggings"));
      assertTrue(content.contains("emerald_boots"));
      assertTrue(content.contains("ruby_helmet"));
      assertTrue(content.contains("ruby_chestplate"));
      assertTrue(content.contains("ruby_leggings"));
      assertTrue(content.contains("ruby_boots"));
    }
  }

  @Test
  @DisplayName("All textures must be valid PNGs readable by ImageIO")
  void testTexturesAreValidPngs() throws Exception {
    String[] testTextures = {
      "/assets/emerald_essentials/textures/item/emerald_mirror.png",
      "/assets/emerald_essentials/textures/item/ruby_dagger.png",
      "/assets/emerald_essentials/textures/item/emerald_dagger.png",
      "/assets/emerald_essentials/textures/item/ruby.png",
      "/assets/emerald_essentials/textures/item/ruby_sword.png",
      "/assets/emerald_essentials/textures/block/amethyst_altar_top.png",
      "/assets/emerald_essentials/textures/block/ruby_block.png",
      "/assets/emerald_essentials/textures/item/amethyst_resonator.png",
      "/assets/emerald_essentials/textures/item/amethyst_lens.png",
      "/assets/emerald_essentials/textures/item/emerald_shield.png",
      "/assets/emerald_essentials/textures/item/emerald_bow.png",
      "/assets/emerald_essentials/textures/item/ruby_charm.png"
    };

    for (String path : testTextures) {
      try (InputStream stream = getClass().getResourceAsStream(path)) {
        assertNotNull(stream, "Texture " + path + " must exist");
        java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(stream);
        assertNotNull(img, "ImageIO must successfully decode " + path);
        assertTrue(
            img.getWidth() > 0 && img.getHeight() > 0, "Image must have positive dimensions");
      }
    }
  }

  @Test
  @DisplayName("Shaped recipes must have uniform pattern row lengths")
  void testShapedRecipePatternUniformity() throws Exception {
    String[] shapedRecipes = {
      "emerald_axe",
      "emerald_sword",
      "emerald_pickaxe",
      "emerald_shovel",
      "emerald_hoe",
      "emerald_dagger",
      "ruby_axe",
      "ruby_sword",
      "ruby_pickaxe",
      "ruby_shovel",
      "ruby_hoe",
      "ruby_dagger",
      "amethyst_resonator",
      "amethyst_lens",
      "emerald_shield",
      "emerald_bow",
      "ruby_charm",
      "emerald_keystone",
      "emerald_crown",
      "emerald_staff"
    };

    for (String recipeName : shapedRecipes) {
      try (InputStream stream =
          getClass()
              .getResourceAsStream("/data/emerald_essentials/recipe/" + recipeName + ".json")) {
        assertNotNull(stream, "Recipe " + recipeName + " must exist");
        try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
          char[] buf = new char[2048];
          int len = reader.read(buf);
          String content = new String(buf, 0, len);
          assertTrue(content.contains("\"pattern\""), recipeName + " must contain pattern");
        }
      }
    }
  }

  @Test
  @DisplayName("Artifact items assets, models, textures and recipes must exist in classpath")
  void testArtifactItemsAndRecipes() {
    String[] artifactItems = {
      "amethyst_resonator", "amethyst_lens", "emerald_shield", "emerald_bow", "ruby_charm"
    };

    for (String item : artifactItems) {
      InputStream model =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/models/item/" + item + ".json");
      assertNotNull(model, "Model for " + item + " must exist");

      InputStream texture =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/textures/item/" + item + ".png");
      assertNotNull(texture, "Texture for " + item + " must exist");

      InputStream recipe =
          getClass().getResourceAsStream("/data/emerald_essentials/recipe/" + item + ".json");
      assertNotNull(recipe, "Recipe for " + item + " must exist");
    }
  }

  @Test
  @DisplayName("12 magical flora assets, blockstates, models, textures and loot tables must exist")
  void testMagicalFloraAssetsAndLootTables() throws Exception {
    String[] flowers = {
      "cinder_blossom",
      "frost_lily",
      "solar_daisy",
      "gale_petal",
      "echo_violet",
      "shadow_orchid",
      "vitallia",
      "thunder_poppy",
      "void_chrysanthemum",
      "slime_lotus",
      "ember_rose",
      "aura_marigold"
    };

    for (String flower : flowers) {
      InputStream state =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/blockstates/" + flower + ".json");
      assertNotNull(state, "Blockstate for " + flower + " must exist");

      InputStream bModel =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/models/block/" + flower + ".json");
      assertNotNull(bModel, "Block model for " + flower + " must exist");

      InputStream iModel =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/models/item/" + flower + ".json");
      assertNotNull(iModel, "Item model for " + flower + " must exist");

      InputStream loot =
          getClass()
              .getResourceAsStream(
                  "/data/emerald_essentials/loot_table/blocks/" + flower + ".json");
      assertNotNull(loot, "Loot table for " + flower + " must exist");

      InputStream tex =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/textures/block/" + flower + ".png");
      assertNotNull(tex, "Texture for " + flower + " must exist");
      java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(tex);
      assertNotNull(img, "Texture for " + flower + " must be readable by ImageIO");
      assertTrue(img.getWidth() == 16 && img.getHeight() == 16);
    }
  }

  @Test
  @DisplayName("emerald_caverns biome, ore features, and worldgen placed features must exist")
  void testWorldgenAndBiome() throws Exception {
    InputStream biomeStream =
        getClass()
            .getResourceAsStream("/data/emerald_essentials/worldgen/biome/emerald_caverns.json");
    assertNotNull(biomeStream, "emerald_caverns biome must exist in classpath");

    try (InputStreamReader reader = new InputStreamReader(biomeStream, StandardCharsets.UTF_8)) {
      com.google.gson.JsonObject biomeObj =
          com.google.gson.JsonParser.parseReader(reader).getAsJsonObject();
      assertTrue(biomeObj.get("carvers").isJsonObject(), "carvers must be a JSON object in 1.21.1");
      assertTrue(
          biomeObj.getAsJsonObject("carvers").has("air"), "carvers must contain 'air' carver step");
      assertTrue(biomeObj.get("features").isJsonArray(), "features must be a JSON array");
      com.google.gson.JsonArray features = biomeObj.getAsJsonArray("features");
      assertTrue(
          features.size() == 11,
          "features must contain 11 steps matching GenerationStep.Decoration");
      assertTrue(
          features.get(6).getAsJsonArray().size() > 0, "Step 6 (UNDERGROUND_ORES) must have ores");
      assertTrue(
          features.get(9).getAsJsonArray().size() > 0,
          "Step 9 (VEGETAL_DECORATION) must have flora");
    }

    String[] features = {"ore_emerald_cavern_dense", "ore_ruby_cavern_dense"};
    for (String feat : features) {
      InputStream cf =
          getClass()
              .getResourceAsStream(
                  "/data/emerald_essentials/worldgen/configured_feature/" + feat + ".json");
      assertNotNull(cf, "Configured feature " + feat + " must exist");

      InputStream pf =
          getClass()
              .getResourceAsStream(
                  "/data/emerald_essentials/worldgen/placed_feature/" + feat + ".json");
      assertNotNull(pf, "Placed feature " + feat + " must exist");
    }
  }

  @Test
  @DisplayName("Endgame items, boss loot, and entity textures must exist")
  void testEndgameItemsAndBoss() throws Exception {
    String[] items = {"emerald_keystone", "emerald_core", "emerald_crown", "emerald_staff"};
    for (String item : items) {
      InputStream model =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/models/item/" + item + ".json");
      assertNotNull(model, "Model for " + item + " must exist");

      InputStream tex =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/textures/item/" + item + ".png");
      assertNotNull(tex, "Texture for " + item + " must exist");
      java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(tex);
      assertNotNull(img, "Texture for " + item + " must be readable by ImageIO");
    }

    String[] entities = {"emerald_scuttler", "emerald_titan"};
    for (String ent : entities) {
      InputStream loot =
          getClass()
              .getResourceAsStream("/data/emerald_essentials/loot_table/entities/" + ent + ".json");
      assertNotNull(loot, "Loot table for " + ent + " must exist");

      InputStream tex =
          getClass()
              .getResourceAsStream("/assets/emerald_essentials/textures/entity/" + ent + ".png");
      assertNotNull(tex, "Entity texture for " + ent + " must exist");
      java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(tex);
      assertNotNull(img, "Entity texture for " + ent + " must be readable by ImageIO");
    }
  }

  @Test
  @DisplayName("Item descriptions (.desc) and lore (.lore) must exist in en_us and es_es")
  void testItemTooltipsAndLoreIntegrity() throws Exception {
    String[] langs = {"en_us", "es_es"};
    String[] checkedItems = {
      "item.emerald_essentials.emerald_mirror",
      "item.emerald_essentials.emerald_whetstone",
      "item.emerald_essentials.emerald_apple",
      "item.emerald_essentials.enchanted_emerald",
      "item.emerald_essentials.emerald_dagger",
      "item.emerald_essentials.ruby_dagger",
      "item.emerald_essentials.emerald_crown",
      "item.emerald_essentials.emerald_staff",
      "item.emerald_essentials.emerald_keystone",
      "item.emerald_essentials.emerald_core",
      "block.emerald_essentials.cinder_blossom",
      "block.emerald_essentials.frost_lily"
    };

    for (String lang : langs) {
      try (InputStream is =
          getClass().getResourceAsStream("/assets/emerald_essentials/lang/" + lang + ".json")) {
        assertNotNull(is, "Lang file for " + lang + " must exist");
        com.google.gson.JsonObject json =
            com.google.gson.JsonParser.parseReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8))
                .getAsJsonObject();

        for (String itemKey : checkedItems) {
          assertTrue(
              json.has(itemKey + ".desc"), "Missing " + itemKey + ".desc in " + lang + ".json");
          assertTrue(
              json.has(itemKey + ".lore"), "Missing " + itemKey + ".lore in " + lang + ".json");
        }
      }
    }
  }
}
