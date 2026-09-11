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

**Emerald Essentials** brings purposeful utility to emeralds and amethysts in modern Minecraft.

While vanilla emeralds serve primarily as trading currency and amethysts remain largely decorative, Emerald Essentials integrates them directly into survival progression through carefully balanced gear and unique mechanics without overshadowing endgame tiers.

The mod functions across both Fabric and NeoForge using a single shared codebase with native 1.21.1 data-driven mechanics.

### Philosophy

> *"Meaningful utility without power creep."*

This is a Devil Doll Entertainment project, part of the Sxnnyside Project.

## Features

- **Emerald Tools & Dagger**: Diamond-equivalent mining tier with 550 durability and an agile dagger built for stealth.
- **Balanced Emerald Armor**: 19 total armor points with +1.0 toughness, situated between iron and diamond with high enchantability.
- **Amethyst Levitation**: Chestplate enchantment that propels attackers upward upon dealing damage.
- **Curse of Blind Mining**: Helmet curse granting Haste II in pitch blackness at the cost of limited vision.
- **Curse of the Shadows**: Leggings curse granting tactical invisibility while crouching, penalizing uncalculated sprints.
- **Thief's Stride**: Boots enchantment providing speed in arid biomes and slowing nearby undead in frozen climates.
- **Illusional Flower**: Mystical cave plant emitting light and disorienting confusion upon contact.

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

## Usage

```bash
# Verify environment and toolchain
just install

# Launch development test client
just dev

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

For a detailed breakdown, see [CLAUDE.md](CLAUDE.md).

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
