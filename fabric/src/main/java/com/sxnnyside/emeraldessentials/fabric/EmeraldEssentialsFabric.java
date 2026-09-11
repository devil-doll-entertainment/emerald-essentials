package com.sxnnyside.emeraldessentials.fabric;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.enchantment.ModEnchantmentHandler;
import com.sxnnyside.emeraldessentials.init.ModBlocks;
import com.sxnnyside.emeraldessentials.init.ModCreativeTabs;
import com.sxnnyside.emeraldessentials.init.ModItems;
import com.sxnnyside.emeraldessentials.item.ModArmorMaterials;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.world.entity.LivingEntity;

public class EmeraldEssentialsFabric implements ModInitializer {
  @Override
  public void onInitialize() {
    EmeraldEssentials.init();
    ModArmorMaterials.init();
    ModBlocks.registerVanilla();
    ModItems.registerVanilla();
    ModCreativeTabs.registerVanilla();

    ServerTickEvents.END_SERVER_TICK.register(
        server -> {
          server.getPlayerList().getPlayers().forEach(ModEnchantmentHandler::onPlayerTick);
        });

    ServerLivingEntityEvents.AFTER_DAMAGE.register(
        (entity, source, baseDamageTaken, damageTaken, blocked) -> {
          if (source.getEntity() instanceof LivingEntity attacker) {
            ModEnchantmentHandler.onLivingHurt(entity, attacker);
          }
        });
  }
}
