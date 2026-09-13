package com.sxnnyside.emeraldessentials.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.platform.Services;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

/**
 * Platform-independent configuration stored in the game's config directory. Backed by Java 21
 * immutable {@link ModConfigData} records. File location: {@code
 * .minecraft/config/emerald_essentials.json}
 */
public class ModConfig {

  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
  private static ModConfig instance;

  private ModConfigData data = ModConfigData.createDefault();

  @NotNull
  public static ModConfigData data() {
    return get().getData();
  }

  @NotNull
  public ModConfigData getData() {
    if (data == null) {
      data = ModConfigData.createDefault();
    }
    return data;
  }

  public void setData(@NotNull ModConfigData data) {
    this.data = data;
    save();
  }

  // ---- Backwards Compatibility Getters & Setters ----

  public boolean isEnableArmorAbilities() {
    return getData().enableArmorAbilities();
  }

  public void setEnableArmorAbilities(boolean enableArmorAbilities) {
    ModConfigData current = getData();
    this.data =
        new ModConfigData(
            enableArmorAbilities,
            current.enableFlowerParticles(),
            current.enableBackstabBonus(),
            current.worldGen(),
            current.gameplay(),
            current.mobs());
    save();
  }

  public boolean isEnableFlowerParticles() {
    return getData().enableFlowerParticles();
  }

  public void setEnableFlowerParticles(boolean enableFlowerParticles) {
    ModConfigData current = getData();
    this.data =
        new ModConfigData(
            current.enableArmorAbilities(),
            enableFlowerParticles,
            current.enableBackstabBonus(),
            current.worldGen(),
            current.gameplay(),
            current.mobs());
    save();
  }

  public boolean isEnableBackstabBonus() {
    return getData().enableBackstabBonus();
  }

  public void setEnableBackstabBonus(boolean enableBackstabBonus) {
    ModConfigData current = getData();
    this.data =
        new ModConfigData(
            current.enableArmorAbilities(),
            current.enableFlowerParticles(),
            enableBackstabBonus,
            current.worldGen(),
            current.gameplay(),
            current.mobs());
    save();
  }

  // ---- Lifecycle ----

  @NotNull
  private static Path getConfigPath() {
    return Services.PLATFORM.getConfigDirectory().resolve("emerald_essentials.json");
  }

  public static void load() {
    Path path = getConfigPath();
    if (Files.exists(path)) {
      try (Reader reader = Files.newBufferedReader(path)) {
        instance = GSON.fromJson(reader, ModConfig.class);
        if (instance == null || instance.data == null) {
          instance = new ModConfig();
        }
        EmeraldEssentials.LOGGER.info("Configuration loaded from {}", path);
      } catch (Exception e) {
        EmeraldEssentials.LOGGER.error("Failed to read configuration; using defaults.", e);
        instance = new ModConfig();
      }
    } else {
      instance = new ModConfig();
      EmeraldEssentials.LOGGER.info("No configuration found; creating default at {}", path);
    }
    save();
  }

  public static void save() {
    Path path = getConfigPath();
    try {
      if (path.getParent() != null) {
        Files.createDirectories(path.getParent());
      }
      try (Writer writer = Files.newBufferedWriter(path)) {
        GSON.toJson(get(), writer);
      }
    } catch (IOException e) {
      EmeraldEssentials.LOGGER.error("Failed to save configuration.", e);
    }
  }

  @NotNull
  public static ModConfig get() {
    if (instance == null) {
      load();
    }
    return instance;
  }
}
