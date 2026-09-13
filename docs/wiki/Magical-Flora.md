# Magical Flora

Emerald Essentials features **13 unique magical flowers** that populate different biomes, dimensions, and cave systems. Each flower has dedicated soil requirements, dynamic visual particle emissions, ambient light levels, and distinct interactive status effects when entities pass through them.

---

## 🌺 Overview of Magical Flowers

| Flower | Primary Status Effect | Soil Affinities | Light | Particles | Best Biomes |
| :--- | :--- | :--- | :---: | :--- | :--- |
| **Cinder Blossom** | Fire Resistance (160 ticks) | Sand, Red Sand, Netherrack, Soul Soil, Magma | 8 | Flame | Nether Wastes, Basalt Deltas, Deserts |
| **Frost Lily** | Resistance (Players) / Slowness (Mobs) | Grass, Dirt, Podzol | 4 | Snowflake | Snowy Plains, Taiga, Frozen Peaks |
| **Solar Daisy** | Glowing + Regeneration I | Grass, Dirt | 10 | Glow | Sunflower Plains, Meadows, Savannas |
| **Gale Petal** | Slow Falling + Jump Boost II + Updraft | Grass, Dirt | 4 | Cloud | Windswept Hills, Jagged Peaks |
| **Echo Violet** | Night Vision + Resonant Chime | Stone, Deepslate, Sculk, Moss | 6 | Sculk Charge | Deep Dark, Dripstone Caves, Lush Caves |
| **Shadow Orchid** | Invisibility (Players) / Blindness (Mobs) | Grass, Dirt, Podzol | 0 | Smoke | Dark Forest, Old Growth Taiga, Swamps |
| **Vitallia** | Passive Healing + Regeneration I | Grass, Dirt | 6 | Heart | Cherry Groves, Flower Forests, Plains |
| **Thunder Poppy** | Speed II + Haste I | Sand, Soul Sand, Netherrack | 7 | Electric Spark | Badlands, Savannas, Nether Wastes |
| **Void Chrysanthemum** | Controlled Short-Range Teleportation | End Stone, Deepslate, Sculk | 7 | Portal | The End, Deepslate Caves |
| **Slime Lotus** | Trampoline Bounce (Reverses Fall Velocity) | Mud, Muddy Mangrove Roots, Clay, Sand | 3 | Slime | Mangrove Swamps, Riverbanks, Swamps |
| **Ember Rose** | Strength I (160 ticks) + Minor Thorn Prick | Netherrack, Basalt, Soul Soil | 6 | Lava | Crimson Forest, Nether Wastes |
| **Aura Marigold** | Saturation + Ongoing Cleanse of Harmful Effects | Grass, Dirt | 6 | Happy Villager | Plains, Meadows, Village Perimeters |
| **Illusional Flower** | Optical Camouflage / Stealth Phasing | Stone, Moss, Grass | 2 | Arcane Mist | Emerald Caverns, Dark Forests |

---

## 🍃 Mechanics & Behavioral Features

### 1. Interactive Contact Effects
Unlike standard vanilla flowers that only serve decorative purposes, walking through a magical flower activates its intrinsic magic:
- **Buffing Allies**: Flowers like `vitallia` and `aura_marigold` restore health and continuously cleanse debuffs (such as Poison, Wither, and Slowness).
- **Tactical Hazards**: Placing `frost_lily` or `shadow_orchid` along perimeter fences slows and blinds incoming monsters while allowing players to benefit from Resistance or Invisibility.
- **Traversal Utility**: `slime_lotus` acts as a natural bounce pad that completely cancels accumulated fall distance, while `gale_petal` provides a gentle updraft.

### 2. Particle & Lighting System
All 13 flowers emit custom ambient particles matching their elemental affinity:
- Configurable in `emerald_essentials.json` via the `enable_flower_particles` toggle.
- Provide soft localized light (up to level 10 for `solar_daisy`), creating natural subterranean or forest waypoints during exploration.

### 3. Suspicious Stew & Potion Ingredients
All flowers can be crafted into **Suspicious Stew**, imparting their signature potion effect for extended durations, and serve as potent reagents in upcoming alchemy recipes.
