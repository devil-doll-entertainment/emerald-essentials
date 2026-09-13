package com.sxnnyside.emeraldessentials.platform;

import org.jetbrains.annotations.NotNull;

/** Cross-loader abstraction for environment and platform services. */
public interface PlatformHelper {

  /** Gets the name of the current platform ("Fabric" or "NeoForge"). */
  @NotNull
  String getPlatformName();

  /** Checks if a given mod ID is loaded in the current environment. */
  boolean isModLoaded(@NotNull String modId);

  /** Gets the path to the game's configuration directory. */
  @NotNull
  java.nio.file.Path getConfigDirectory();

  /** Checks if the environment is a development environment. */
  boolean isDevelopmentEnvironment();
}
