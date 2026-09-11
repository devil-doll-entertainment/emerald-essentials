package com.sxnnyside.emeraldessentials.neoforge;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.enchantment.ModEnchantmentHandler;
import com.sxnnyside.emeraldessentials.init.ModBlocks;
import com.sxnnyside.emeraldessentials.init.ModCreativeTabs;
import com.sxnnyside.emeraldessentials.init.ModItems;
import com.sxnnyside.emeraldessentials.item.ModArmorMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(EmeraldEssentials.MOD_ID)
public class EmeraldEssentialsNeoForge {
  public EmeraldEssentialsNeoForge(IEventBus modEventBus) {
    EmeraldEssentials.init();
    ModArmorMaterials.init();

    modEventBus.addListener(this::register);
    NeoForge.EVENT_BUS.register(this);
  }

  private void register(RegisterEvent event) {
    event.register(Registries.BLOCK, helper -> ModBlocks.getEntries().forEach(helper::register));
    event.register(Registries.ITEM, helper -> ModItems.getEntries().forEach(helper::register));
    event.register(
        Registries.CREATIVE_MODE_TAB,
        helper -> ModCreativeTabs.getEntries().forEach(helper::register));
  }

  @SubscribeEvent
  public void onPlayerTick(PlayerTickEvent.Post event) {
    Player player = event.getEntity();
    ModEnchantmentHandler.onPlayerTick(player);
  }

  @SubscribeEvent
  public void onLivingDamage(LivingDamageEvent.Post event) {
    if (event.getSource().getEntity() instanceof LivingEntity attacker) {
      ModEnchantmentHandler.onLivingHurt(event.getEntity(), attacker);
    }
  }
}
