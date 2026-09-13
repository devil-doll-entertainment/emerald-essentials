package com.sxnnyside.emeraldessentials.platform;

import java.util.ServiceLoader;
import org.jetbrains.annotations.NotNull;

/** Service locator to load platform-specific implementations via Java's ServiceLoader. */
public final class Services {

  public static final PlatformHelper PLATFORM = load(PlatformHelper.class);

  private Services() {}

  @NotNull
  public static <T> T load(@NotNull Class<T> clazz) {
    return ServiceLoader.load(clazz)
        .findFirst()
        .orElseThrow(
            () -> new NullPointerException("Failed to load service for " + clazz.getName()));
  }
}
