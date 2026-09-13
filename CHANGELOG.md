# Changelog

All notable changes to **Emerald Essentials** are documented here.

This project follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/)
and [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.1.0] — 2026-09-13

### Added

- **Multi-Loader Architecture**: Native support for Minecraft 1.21.1 across both **Fabric** and **NeoForge** from a single shared `:common` codebase.
- **Emerald Caverns Biome**: Subterranean crystal cave biome with emerald cyan atmosphere, villager spore particles, cave ambient audio, and dense clusters of emerald and ruby ores (`ore_emerald_cavern_dense`, `ore_ruby_cavern_dense`).
- **13 Magical Flora Blocks**: Subterranean and dimensional flora with in-world stepping interactions (`entityInside`), particle animations (`animateTick`), soft luminescence, and cutout render layers:
  - `Cinder Blossom` (Nether/Desert — Fire Resistance)
  - `Frost Lily` (Snowy Peaks — Slowness to foes / Resistance to players)
  - `Solar Daisy` (Meadow — Glowing & Regeneration)
  - `Gale Petal` (Peaks — Jump Boost & Slow Falling draft)
  - `Echo Violet` (Deep Dark/Caves — Resonance chime & Night Vision)
  - `Shadow Orchid` (Swamp — Invisibility & Blindness to enemies)
  - `Vitallia` (Jungle/Lush Caves — Instant Health & Regeneration)
  - `Thunder Poppy` (Savanna — Speed & Haste)
  - `Void Chrysanthemum` (The End — Spatial micro-teleportation)
  - `Slime Lotus` (Rivers/Swamp — Trampoline bounce & fall damage negation)
  - `Ember Rose` (Badlands/Nether — Strength & retaliatory thorns)
  - `Aura Marigold` (Forest/Meadow — Negative status effect cleansing & Saturation)
  - `Illusional Flower` (Emerald Caverns / Dark Forest — Optical camouflage & stealth phasing)
- **Alchemical Potion Brewing**: 6 new potion lines brewed from the magical flora with Awkward Potion, Redstone, and Glowstone paths on Fabric (`FabricBrewingRecipeRegistryBuilder`) and NeoForge (`RegisterBrewingRecipesEvent`):
  - Potions of Haste (Normal, Long, Strong)
  - Potions of True Sight (Normal, Long)
  - Potions of Levitation (Normal, Long)
  - Potions of Resistance (Normal, Long, Strong)
  - Potions of Vitality (Normal, Strong)
  - Potion of Cleansing
- **GeckoLib Subterranean Fauna**: Full custom animated creature models using GeckoLib 4.7:
  - `Emerald Titan Boss`: 300 HP subterranean boss with seismic shockwaves, enraged minion phase, and guaranteed non-craftable `Emerald Core` drop.
  - `Emerald Scuttler`: Agile insectoid monster with a projectile-reflective crystal carapace and gem affinity.
  - `Amethyst Scarab`: Acoustic subterranean scarab tuned to crystalline harmonic chimes.
  - `Mushroom Bup`: Gentle fungal companion roaming damp underground caverns.
  - `Mantabu`: Majestic gliding creature adding life to underground expanses.
- **Endgame Progression Artifacts**:
  - `Crown of the Gem Lord` (`emerald_crown`): Forged with the Emerald Core, granting permanent Hero of the Village, retaliatory Gem Radiance shockwaves, and Regeneration II.
  - `Earth Staff` (`emerald_staff`): Seismic staff unleashing piercing emerald shockwaves damaging and knocking back enemies.
  - `Emerald Mirror` (`emerald_mirror`): Emergency return item teleporting the player to their bed or respawn anchor across dimensions.
  - `Emerald Whetstone` (`emerald_whetstone`): Utility sharpening stone repairing off-hand emerald tools and granting mining or combat buffs.
  - `Amethyst Resonator` (`amethyst_resonator`): Arcane staff unleashing directional sonic shockwaves.
  - `Amethyst Lens` (`amethyst_lens`): Acoustic divination lens revealing subterranean entities with Glowing.
  - `Emerald Bow` & `Emerald Shield`: Reinforced gemstone equipment with custom pull stages and blocking animations.
  - `Ruby Charm`: Geothermal talisman granting automatic fire extinguishing and Fire Resistance in lava.
- **Ruby Mineral Tier & Progression**: Ruby Ore, Deepslate Ruby Ore, Ruby Block, and Ruby Gem with worldgen, smelting/blasting, and geothermal transmutation crafting.
- **Ruby Equipment Set**: Full set of ruby tools (sword, pickaxe, axe, shovel, hoe, dagger) and ruby armor (helmet, chestplate, leggings, boots) with 1750 durability and trimmable armor support.
- **Dedicated Amethyst Altar**: Arcane block for infusing tools and armor with amethyst enchantments without vanilla table conflicts.
- **Amethyst Enchantments**: Data-driven enchantments including `Amethyst Levitation`, `Curse of Blind Mining`, `Curse of the Shadows`, `Thief's Stride`, `Amethyst Resonance`, and `Amethyst Vitality`.
- **Emerald Apple & Enchanted Emerald**: Consumable granting Regeneration II, Resistance I, and Absorption II alongside advanced crafting catalyst gem with permanent foil glint.
- **Dual Tooltip UX & Lore**: Descriptive mechanics tooltip plus immersive italic lore across all tools, armors, relics, and flowers.
- **Expanded Localization**: Full in-game translations across 18 languages (`en_us`, `es_es`, `de_de`, `fr_fr`, `pt_br`, `it_it`, `ru_ru`, `uk_ua`, `pl_pl`, `ja_jp`, `ko_kr`, `zh_cn`, `zh_tw`, `cs_cz`, `nl_nl`, `sv_se`, `tr_tr`, `ar_sa`).
- **Official GitHub Wiki Documentation**: Complete multi-page documentation in `docs/wiki/` with automated CI sync workflow (`.github/workflows/wiki.yml`) and local `just wiki-sync`.
- **Developer Lifecycle Recipes**: Added `clean-saves` and `clean-runs` recipes to `Justfile` matching brother mod standard for clearing test world saves and dev logs.
- **Unified Data Generation**: Cross-loader `ModRecipeProvider` in `:common` extending vanilla `RecipeProvider`, executed by Fabric (`EmeraldEssentialsFabricDataGenerator`) and NeoForge (`GatherDataEvent`).
- **Java 21 Configuration Records**: Strongly-typed `ModConfigData` record hierarchy persisted to `config/emerald_essentials.json`.
- **Quality Tooling**: Complete `Justfile` task runner workflow, Checkstyle 10.18.1, Spotless (Google Java Format), and JUnit 5 test suite.

### Changed

- **Upgraded GeckoLib to 4.7**: Replaced initial 4.6 dependency with 4.7 across Fabric and NeoForge, resolving mixin conflicts on NeoForge 21.1.77.
- **Pinned Loom Version**: Migrated Fabric Loom from floating `1.7-SNAPSHOT` to verified stable `1.7.4`.
- **Declarative Resources**: Configured idiomatic Gradle `sourceSets.main.resources.srcDir` across Fabric and NeoForge.
- **Modern Item Properties**: Replaced deprecated `FabricModelPredicateProviderRegistry` with vanilla `ItemProperties.register`.
- **Safe NeoForge Registry Lifecycle**: Migrated mod registration to `RegisterEvent` hooks to avoid frozen registry exceptions.
- **Rebalanced Emerald Tier**: Calibrated emerald armor (19 defense, +1.0 toughness, 25 enchantability) and emerald tools (550 durability, diamond mining tier).
- **Hardened Backstab Angle**: Implemented vector dot-product calculation for `EmeraldDaggerItem`.

### Fixed

- **Texture Dimensions & Mipmapping**: Resized `illusional_flower.png` to standard 16x16 power-of-two, restoring mipmap level 4 on the block atlas.
- **Model Definitions**: Created missing model JSON files for `emerald_bow_pulling_0/1/2` and `emerald_shield_blocking`.
- **NeoForge Mixin Error**: Resolved `HumanoidArmorLayerMixin` injection failure on NeoForge by upgrading GeckoLib.
- **Brewing Registration Parity**: Unified Fabric potion brewing registration using `ModBrewingRecipes::registerWithBuilder`.
- **DataFixer & Entity Safety**: Ensured valid registry string arguments in `EntityType.Builder.build` preventing null pointer exceptions during data generation.
- **PNG Encoding**: Fixed PNG filter row encoding on `emerald_mirror.png` ensuring full compliance with Java `ImageIO`.

### Removed

- Removed legacy MCreator workspace artifacts, unmaintained scripts, and Forge 1.19.2 dependencies.
- Removed deprecated `ROADMAP.md` and phase tracking references in favor of the finalized v1.1.0 release specification.

---

[Unreleased]: https://github.com/devil-doll-entertainment/emerald-essentials/compare/v1.1.0...HEAD
[1.1.0]: https://github.com/devil-doll-entertainment/emerald-essentials/releases/tag/v1.1.0

