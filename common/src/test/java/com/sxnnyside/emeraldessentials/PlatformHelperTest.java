package com.sxnnyside.emeraldessentials;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sxnnyside.emeraldessentials.platform.Services;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlatformHelperTest {

  @Test
  @DisplayName("ServiceLoader should resolve PlatformHelper in test environment")
  void testPlatformHelperResolution() {
    assertNotNull(Services.PLATFORM, "PlatformHelper SPI service should not be null");
    assertEquals("Test", Services.PLATFORM.getPlatformName());
    assertTrue(Services.PLATFORM.isDevelopmentEnvironment());
    assertFalse(Services.PLATFORM.isModLoaded("some_mod"));
  }
}
