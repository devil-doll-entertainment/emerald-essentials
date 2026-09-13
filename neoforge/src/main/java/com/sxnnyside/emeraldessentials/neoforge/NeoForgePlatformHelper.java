package com.sxnnyside.emeraldessentials.neoforge;

import com.sxnnyside.emeraldessentials.platform.PlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import org.jetbrains.annotations.NotNull;

public class NeoForgePlatformHelper implements PlatformHelper {

  @Override
  public @NotNull String getPlatformName() {
    return "NeoForge";
  }

  @Override
  public boolean isModLoaded(@NotNull String modId) {
    return ModList.get().isLoaded(modId);
  }

  @Override
  public @NotNull java.nio.file.Path getConfigDirectory() {
    return net.neoforged.fml.loading.FMLPaths.CONFIGDIR.get();
  }

  @Override
  public boolean isDevelopmentEnvironment() {
    return !FMLLoader.isProduction();
  }
}
