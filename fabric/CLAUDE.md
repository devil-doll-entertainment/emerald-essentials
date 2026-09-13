# Module: :fabric

## Overview
- **Path:** `fabric/`
- **Role:** Fabric loader adapter. Registers Fabric API mod initializers, client render layers, and `fabric.mod.json`.
- **Dependencies:** `:common`, `fabric-loader`, `fabric-api`.

## Key Implementations
- `EmeraldEssentialsFabric`: Implements `ModInitializer` for server/common registration and game events.
- `EmeraldEssentialsFabricClient`: Implements `ClientModInitializer` for client render layers.
- `FabricPlatformHelper`: Implements `PlatformHelper` using `FabricLoader.getInstance()`.
- `fabric.mod.json`: Fabric mod manifest with main and client entrypoint declarations.

## Submodule Commands
```bash
./gradlew :fabric:compileJava
./gradlew :fabric:assemble
./gradlew :fabric:runClient
```
