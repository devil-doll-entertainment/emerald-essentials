package com.sxnnyside.emeraldessentials.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sxnnyside.emeraldessentials.platform.Services;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ModConfigTest {

  private Path configPath;

  @BeforeEach
  void setUp() throws IOException {
    configPath = Services.PLATFORM.getConfigDirectory().resolve("emerald_essentials.json");
    if (Files.exists(configPath)) {
      Files.delete(configPath);
    }
    ModConfig.load();
  }

  @AfterEach
  void tearDown() throws IOException {
    if (Files.exists(configPath)) {
      Files.delete(configPath);
    }
  }

  @Test
  @DisplayName("Default configuration values should all be enabled")
  void testDefaultConfigValues() {
    ModConfig config = ModConfig.get();
    assertNotNull(config, "Config instance should not be null");
    assertTrue(config.isEnableArmorAbilities(), "Armor abilities should be enabled by default");
    assertTrue(config.isEnableFlowerParticles(), "Flower particles should be enabled by default");
    assertTrue(config.isEnableBackstabBonus(), "Backstab bonus should be enabled by default");

    ModConfigData data = ModConfig.data();
    assertNotNull(data, "ModConfigData should not be null");
    assertTrue(data.worldGen().generateEmeraldCaverns());
    assertTrue(data.worldGen().generateMagicFlora());
    assertTrue(data.gameplay().emeraldMirrorSingleUse());
    org.junit.jupiter.api.Assertions.assertEquals(
        60, data.gameplay().emeraldMirrorCooldownSeconds());
    org.junit.jupiter.api.Assertions.assertEquals(
        30, data.gameplay().whetstoneBonusDurationSeconds());
    org.junit.jupiter.api.Assertions.assertEquals(20, data.gameplay().staffFireballCooldownTicks());
    org.junit.jupiter.api.Assertions.assertEquals(160.0, data.mobs().titanMaxHealth());
  }

  @Test
  @DisplayName("Setters should update values and persist to disk")
  void testSettersAndPersistence() {
    ModConfig config = ModConfig.get();
    config.setEnableArmorAbilities(false);
    config.setEnableFlowerParticles(false);
    config.setEnableBackstabBonus(false);

    assertFalse(config.isEnableArmorAbilities());
    assertFalse(config.isEnableFlowerParticles());
    assertFalse(config.isEnableBackstabBonus());
    assertTrue(Files.exists(configPath), "Config file should exist on disk after saving");

    // Reload from disk
    ModConfig.load();
    ModConfig reloaded = ModConfig.get();
    assertFalse(reloaded.isEnableArmorAbilities(), "Persisted false value should reload");
    assertFalse(reloaded.isEnableFlowerParticles(), "Persisted false value should reload");
    assertFalse(reloaded.isEnableBackstabBonus(), "Persisted false value should reload");
  }

  @Test
  @DisplayName("Record updates should persist and reload correctly")
  void testRecordPersistence() {
    ModConfig config = ModConfig.get();
    ModConfigData customData =
        new ModConfigData(
            true,
            false,
            true,
            new ModConfigData.WorldGenConfig(false, true),
            new ModConfigData.GameplayConfig(120, false, 45, 10),
            new ModConfigData.MobConfig(25.0, 5.0, 200.0, 15.0));

    config.setData(customData);
    assertTrue(Files.exists(configPath), "Config file should exist after setData");

    ModConfig.load();
    ModConfigData reloadedData = ModConfig.data();
    assertFalse(reloadedData.enableFlowerParticles());
    assertFalse(reloadedData.worldGen().generateEmeraldCaverns());
    assertTrue(reloadedData.worldGen().generateMagicFlora());
    assertFalse(reloadedData.gameplay().emeraldMirrorSingleUse());
    org.junit.jupiter.api.Assertions.assertEquals(
        120, reloadedData.gameplay().emeraldMirrorCooldownSeconds());
    org.junit.jupiter.api.Assertions.assertEquals(
        45, reloadedData.gameplay().whetstoneBonusDurationSeconds());
    org.junit.jupiter.api.Assertions.assertEquals(
        10, reloadedData.gameplay().staffFireballCooldownTicks());
    org.junit.jupiter.api.Assertions.assertEquals(200.0, reloadedData.mobs().titanMaxHealth());
  }
}
