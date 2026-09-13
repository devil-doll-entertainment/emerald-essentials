# Enchantments

Emerald Essentials introduces a collection of modern, data-driven enchantments fully compliant with Minecraft 1.21.1's component-based enchantment architecture.

---

## 🔮 Custom Enchantment Catalog

| Enchantment | Target Equipment | Max Level | Rarity | Primary Effect |
| :--- | :--- | :---: | :---: | :--- |
| **Amethyst Resonance** (`amethyst_resonance`) | Weapons & Tools | III | Rare | Hits against armored targets create sonic vibration waves that deal true armor-penetrating harmonic damage to nearby entities. |
| **Amethyst Vitality** (`amethyst_vitality`) | Chestplates & Armor | II | Very Rare | Converts incoming kinetic and projectile damage into temporary regeneration pulses and bonus absorption hearts. |
| **Amethyst Levitation** (`amethyst_levitate`) | Boots & Weapons | II | Rare | Attacks apply momentary zero-gravity levitation to opponents, exposing them to fall damage or projectile attacks. |
| **Thief's Stride** (`amethyst_thief`) | Boots & Daggers | III | Uncommon | Increases movement speed and sneaking speed while reducing sound detection distance from Sculk sensors and nearby mobs. |
| **Curse of Blind Mining** (`amethyst_blind`) | Pickaxes (Curse) | I | Very Rare | Mining blocks in low light levels occasionally causes momentary darkness or disorientation, but increases rare gem yield. |
| **Curse of the Shadows** (`amethyst_shadow`) | Armor (Curse) | I | Very Rare | Renders the wearer semi-translucent, reducing detection range but amplifying vulnerability to radiant and sunlight attacks. |

---

## ⚙️ Data-Driven Mechanics (1.21.1 Architecture)

All enchantments in Emerald Essentials are defined under `data/emerald_essentials/enchantment/` using the official Mojang 1.21.1 JSON schema:

- **Supported Items Tag**: Defined via `#minecraft:enchantable/weapon`, `#minecraft:enchantable/armor`, and `#minecraft:enchantable/mining`.
- **Anvil Cost & Weighting**: Carefully balanced to preserve vanilla parity while providing rewarding payoffs for late-game enchanting setups.
- **Cross-Mod Compatibility**: Readily modified or extended via custom datapacks without requiring Java code changes.
