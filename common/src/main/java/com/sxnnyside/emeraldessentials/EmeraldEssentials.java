package com.sxnnyside.emeraldessentials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EmeraldEssentials {
  public static final String MOD_ID = "emerald_essentials";
  public static final String MOD_NAME = "Emerald Essentials";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

  private EmeraldEssentials() {}

  public static void init() {
    LOGGER.info("Initializing {}", MOD_NAME);
  }
}
