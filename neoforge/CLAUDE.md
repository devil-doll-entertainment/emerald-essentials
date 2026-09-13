# Module: :neoforge

## Overview
- **Path:** `neoforge/`
- **Role:** NeoForge loader adapter. Registers `@Mod("emerald_essentials")` lifecycle, Mod Event Bus registrations, and Game Event Bus event listeners.
- **Dependencies:** `:common`, `net.neoforged:neoforge`.

## Key Implementations
- `EmeraldEssentialsNeoForge`: NeoForge `@Mod` entrypoint and registration bus listeners.
- `EmeraldEssentialsNeoForgeClient`: Isolated client entrypoint guarded by `FMLEnvironment.dist == Dist.CLIENT`.
- `NeoForgePlatformHelper`: Implements `PlatformHelper` using `ModList.get()` and `FMLLoader`.
- `META-INF/neoforge.mods.toml`: NeoForge mod manifest.

## Submodule Commands
```bash
./gradlew :neoforge:compileJava
./gradlew :neoforge:assemble
./gradlew :neoforge:runClient
```
