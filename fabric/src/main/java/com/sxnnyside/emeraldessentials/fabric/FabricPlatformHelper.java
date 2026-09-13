package com.sxnnyside.emeraldessentials.fabric;

import com.sxnnyside.emeraldessentials.platform.PlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

public class FabricPlatformHelper implements PlatformHelper {

  @Override
  public @NotNull String getPlatformName() {
    return "Fabric";
  }

  @Override
  public boolean isModLoaded(@NotNull String modId) {
    return FabricLoader.getInstance().isModLoaded(modId);
  }

  @Override
  public @NotNull java.nio.file.Path getConfigDirectory() {
    return FabricLoader.getInstance().getConfigDir();
  }

  @Override
  public boolean isDevelopmentEnvironment() {
    return FabricLoader.getInstance().isDevelopmentEnvironment();
  }
}
