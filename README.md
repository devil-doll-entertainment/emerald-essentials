# Emerald Essentials

![Version](https://img.shields.io/badge/version-1.1.0-blue)
![License](https://img.shields.io/badge/License-MIT-green)
[![CI](https://github.com/devil-doll-entertainment/emerald-essentials/workflows/CI/badge.svg)](https://github.com/devil-doll-entertainment/emerald-essentials/actions)

<p align="center">
  <strong>Multi-loader ✦ Balanced Progression ✦ Vanilla-faithful</strong><br>
  <em>Emerald tools, balanced armor, subterranean flora, and amethyst enchantments for Minecraft 1.21.1 on Fabric and NeoForge.</em>
</p>

<p align="center">
  <a href="#about">About</a> ✦
  <a href="#features">Features</a> ✦
  <a href="#installation">Installation</a> ✦
  <a href="#usage">Usage</a> ✦
  <a href="#architecture">Architecture</a> ✦
  <a href="#contributing">Contributing</a>
</p>

---

## About

**Emerald Essentials** brings purposeful utility to emeralds, amethysts, and rubies in modern Minecraft.

While vanilla emeralds serve primarily as trading currency and amethysts remain largely decorative, Emerald Essentials integrates them directly into survival progression through carefully balanced gear, subterranean flora, alchemy, and unique mechanics without overshadowing endgame tiers.

The mod functions across both Fabric and NeoForge using a single shared codebase with native 1.21.1 data-driven mechanics.

### Philosophy

> *"Meaningful utility without power creep."*

This is a Devil Doll Entertainment project, part of the Sxnnyside Project.

## Features

- **Emerald Equipment**: Complete tier of emerald tools, stealth daggers with backstab synergy, bows, shields, and high-enchantability armor.
- **Ruby Mineral Tier**: Subterranean ruby ore, blocks, tools, armor with knockback resistance, and geothermal transmutation.
- **13 Magical Flora Blocks**: Subterranean and dimensional flora with contact effects, soft luminescence, and alchemy paths.
- **Dedicated Amethyst Altar**: Standalone infusion table for infusing equipment with amethyst enchantments using shards and XP.
- **Subterranean Fauna & Boss**: Animated creatures (*Emerald Scuttler*, *Amethyst Scarab*, *Mushroom Bup*, *Mantabu*) and the seismic *Emerald Titan* boss.
- **Endgame Artifacts**: Legendary *Crown of the Gem Lord*, *Earth Staff*, *Emerald Mirror*, and *Amethyst Resonator*.
- **Native Multi-Loader**: Single shared `:common` engine running natively on **Fabric** and **NeoForge** 1.21.1.
- **18 Languages Supported**: Full in-game translations and dual effect/lore tooltips across 18 languages.

## Installation

### Prerequisites

- Java 21 or later
- Minecraft 1.21.1
- Fabric Loader (>=0.16.0) with Fabric API, or NeoForge (>=21.1.0)

### From Source

```bash
git clone https://github.com/devil-doll-entertainment/emerald-essentials.git
cd emerald-essentials

just build
```

The compiled mod JARs will be located in:
- Fabric: `fabric/build/libs/emerald-essentials-fabric-1.1.0.jar`
- NeoForge: `neoforge/build/libs/emerald-essentials-neoforge-1.1.0.jar`

## Usage

```bash
# Verify environment, toolchain, and configure git hooks
just install

# Launch Fabric development client
just dev

# Launch NeoForge development client
just dev-neoforge

# Run full quality gate
just check
```

## Architecture

```
emerald-essentials/
├── common/       # Shared game logic, registries, data packs, recipes, and assets
├── fabric/       # Fabric Loader entrypoint, Fabric API integration, client rendering
└── neoforge/     # NeoForge entrypoint and event bus listeners
```

For a detailed breakdown, see [ARCHITECTURE.md](docs/ARCHITECTURE.md).

## Contributing

Contributions are accepted. See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

Before contributing, read the [Code of Conduct](CODE_OF_CONDUCT.md).

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

<p align="center">
  <strong>Emerald Essentials</strong> — A Devil Doll Entertainment Project<br>
  <em>&copy; 2025 Sxnnyside Project</em>
</p>
