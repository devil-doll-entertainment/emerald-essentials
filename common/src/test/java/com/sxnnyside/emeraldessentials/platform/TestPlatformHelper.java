package com.sxnnyside.emeraldessentials.platform;

import org.jetbrains.annotations.NotNull;

public class TestPlatformHelper implements PlatformHelper {

  @Override
  public @NotNull String getPlatformName() {
    return "Test";
  }

  @Override
  public boolean isModLoaded(@NotNull String modId) {
    return false;
  }

  @Override
  public @NotNull java.nio.file.Path getConfigDirectory() {
    return java.nio.file.Paths.get("build", "test-config");
  }

  @Override
  public boolean isDevelopmentEnvironment() {
    return true;
  }
}
