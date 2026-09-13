# Module: :common

## Overview
- **Path:** `common/`
- **Role:** Pure, loader-agnostic game logic, registry definitions, item tiers, data packs, recipes, loot tables, and assets.
- **Dependencies:** Minecraft (via Mojang Official Mappings). Loader-agnostic.

## Architectural Constraints
- **Strict Loader Isolation:** Never import `net.fabricmc.*` or `net.neoforged.*` classes here.
- **Service Loader Pattern:** Platform-dependent operations must be accessed strictly through `com.sxnnyside.emeraldessentials.platform.PlatformHelper` via `Services.PLATFORM`.
- **Registry Conventions:** Registries in `:common` are declared statically and loaded safely across loader lifecycles.
- **Data-Driven Rules:** All 1.21.1 enchantments, recipes, loot tables, and tags live under `src/main/resources/data/`.

## Submodule Commands
```bash
./gradlew :common:compileJava
./gradlew :common:test
./gradlew :common:spotlessCheck
./gradlew :common:spotlessApply
```
