# Assistant Guide for Emerald Essentials

## Project Overview
Emerald Essentials is a modern Minecraft 1.21.1 mod providing emerald tools, balanced armor, mystical flora, and amethyst-based enchantments. It supports both Fabric and NeoForge loaders simultaneously from a shared codebase.

## Repository Topology
This repository follows a **Non-Monolithic (Gradle Multi-Module)** topology:
- `:common`: Pure, loader-agnostic game logic, registry definitions, item tiers, data packs, recipes, and assets.
- `:fabric`: Fabric Loader integration, Fabric API hooks, client render layers, and `fabric.mod.json`.
- `:neoforge`: NeoForge integration, event bus subscribers, and `META-INF/neoforge.mods.toml`.

Shared configuration, formatting, and linting rules are defined at the root and inherited by all subprojects.

## Toolchain & Stack
- **Language:** Java 21
- **Build System:** Gradle 8.10.2 (Multi-module)
- **Task Runner:** `just` (via root `Justfile`)
- **Formatter:** Spotless (`google-java-format` 1.22.0)
- **Linter:** Checkstyle (`config/checkstyle/checkstyle.xml`)
- **Compiler Flags:** `-Xlint:all` strict verification
- **Testing:** JUnit 5 (`:common:test`)
- **Loaders:** Fabric Loom 1.7.4 & NeoForge ModDevGradle 2.0.78

## Command Surface
Always use `just` commands rather than invoking Gradle directly:

```bash
just install     # Verify toolchain and wrapper
just dev         # Launch Fabric test client
just build       # Build production JARs for both Fabric and NeoForge
just test        # Run unit test suite
just typecheck   # Strict compiler type check across all modules
just lint        # Run static analysis (Checkstyle & Spotless check)
just format      # Automatically format Java files (Spotless)
just check       # Run the full quality gate (format -> lint -> typecheck -> test -> check)
just clean       # Clean build directories and caches
```

## Coding Conventions
1. **Packaging & Namespaces:**
   - Root package: `com.sxnnyside.emeraldessentials`
   - Mod ID / Resource namespace: `emerald_essentials`
2. **Registry Architecture:**
   - Items, blocks, tiers, and materials must be defined in `:common` using lazy or supplier-based collections.
   - Do not instantiate Minecraft registry objects in standalone test classes without checking initialization lifecycle.
3. **Data-Driven Enchantments:**
   - In Minecraft 1.21.1, enchantments are defined in `common/src/main/resources/data/emerald_essentials/enchantment/*.json`.
   - Event listeners and tick logic live in `ModEnchantmentHandler.java`.
4. **Git Workflow:**
   - Use Conventional Commits (`feat:`, `fix:`, `refactor:`, `chore:`, `docs:`).
   - Pre-commit hooks automatically execute `just check` before committing.
