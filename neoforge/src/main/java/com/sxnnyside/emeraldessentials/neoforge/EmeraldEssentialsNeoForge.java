package com.sxnnyside.emeraldessentials.neoforge;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.datagen.ModRecipeProvider;
import com.sxnnyside.emeraldessentials.enchantment.ModEnchantmentHandler;
import com.sxnnyside.emeraldessentials.init.ModBlocks;
import com.sxnnyside.emeraldessentials.init.ModCreativeTabs;
import com.sxnnyside.emeraldessentials.init.ModEntities;
import com.sxnnyside.emeraldessentials.init.ModItems;
import com.sxnnyside.emeraldessentials.init.ModPotions;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(EmeraldEssentials.MOD_ID)
public class EmeraldEssentialsNeoForge {

  public EmeraldEssentialsNeoForge(IEventBus modEventBus) {
    EmeraldEssentials.init();

    modEventBus.addListener(this::registerEntries);
    modEventBus.addListener(this::registerAttributes);
    modEventBus.addListener(this::buildCreativeTabContents);
    modEventBus.addListener(this::gatherData);
    NeoForge.EVENT_BUS.register(this);

    if (net.neoforged.fml.loading.FMLEnvironment.dist == net.neoforged.api.distmarker.Dist.CLIENT) {
      EmeraldEssentialsNeoForgeClient.init(modEventBus);
    }
  }

  private void registerEntries(RegisterEvent event) {
    event.register(Registries.BLOCK, helper -> ModBlocks.getEntries().forEach(helper::register));
    event.register(Registries.ITEM, helper -> ModItems.getEntries().forEach(helper::register));
    event.register(Registries.POTION, helper -> ModPotions.getEntries().forEach(helper::register));
    event.register(
        Registries.ENTITY_TYPE, helper -> ModEntities.getEntries().forEach(helper::register));
    event.register(
        Registries.CREATIVE_MODE_TAB,
        helper -> ModCreativeTabs.getEntries().forEach(helper::register));
  }

  private void gatherData(GatherDataEvent event) {
    var generator = event.getGenerator();
    var packOutput = generator.getPackOutput();
    var lookupProvider = event.getLookupProvider();

    generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));
  }

  private void registerAttributes(
      net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent event) {
    event.put(
        com.sxnnyside.emeraldessentials.init.ModEntities.EMERALD_SCUTTLER,
        com.sxnnyside.emeraldessentials.entity.EmeraldScuttler.createAttributes().build());
    event.put(
        com.sxnnyside.emeraldessentials.init.ModEntities.EMERALD_TITAN,
        com.sxnnyside.emeraldessentials.entity.EmeraldTitan.createAttributes().build());
    event.put(
        com.sxnnyside.emeraldessentials.init.ModEntities.AMETHYST_SCARAB,
        com.sxnnyside.emeraldessentials.entity.AmethystScarab.createAttributes().build());
    event.put(
        com.sxnnyside.emeraldessentials.init.ModEntities.MUSHROOM_BUP,
        com.sxnnyside.emeraldessentials.entity.MushroomBup.createAttributes().build());
    event.put(
        com.sxnnyside.emeraldessentials.init.ModEntities.MANTABU,
        com.sxnnyside.emeraldessentials.entity.Mantabu.createAttributes().build());
  }

  private void buildCreativeTabContents(
      net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.COMBAT) {
      event.accept(ModItems.EMERALD_SWORD);
      event.accept(ModItems.EMERALD_DAGGER);
      event.accept(ModItems.EMERALD_AXE);
      event.accept(ModItems.EMERALD_HELMET);
      event.accept(ModItems.EMERALD_CHESTPLATE);
      event.accept(ModItems.EMERALD_LEGGINGS);
      event.accept(ModItems.EMERALD_BOOTS);
      event.accept(ModItems.RUBY_SWORD);
      event.accept(ModItems.RUBY_DAGGER);
      event.accept(ModItems.RUBY_AXE);
      event.accept(ModItems.RUBY_HELMET);
      event.accept(ModItems.RUBY_CHESTPLATE);
      event.accept(ModItems.RUBY_LEGGINGS);
      event.accept(ModItems.RUBY_BOOTS);
      event.accept(ModItems.EMERALD_SHIELD);
      event.accept(ModItems.EMERALD_BOW);
      event.accept(ModItems.AMETHYST_RESONATOR);
      event.accept(ModItems.EMERALD_CROWN);
      event.accept(ModItems.EMERALD_STAFF);
    } else if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.TOOLS_AND_UTILITIES) {
      event.accept(ModItems.EMERALD_SHOVEL);
      event.accept(ModItems.EMERALD_PICKAXE);
      event.accept(ModItems.EMERALD_AXE);
      event.accept(ModItems.EMERALD_HOE);
      event.accept(ModItems.EMERALD_MIRROR);
      event.accept(ModItems.EMERALD_WHETSTONE);
      event.accept(ModItems.RUBY_SHOVEL);
      event.accept(ModItems.RUBY_PICKAXE);
      event.accept(ModItems.RUBY_AXE);
      event.accept(ModItems.RUBY_HOE);
      event.accept(ModItems.AMETHYST_LENS);
      event.accept(ModItems.RUBY_CHARM);
    } else if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.FOOD_AND_DRINKS) {
      event.accept(ModItems.EMERALD_APPLE);
    } else if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.INGREDIENTS) {
      event.accept(ModItems.ENCHANTED_EMERALD);
      event.accept(ModItems.RUBY);
      event.accept(ModItems.EMERALD_KEYSTONE);
      event.accept(ModItems.EMERALD_CORE);
    } else if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.BUILDING_BLOCKS) {
      event.accept(ModItems.RUBY_BLOCK);
    } else if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.NATURAL_BLOCKS) {
      event.accept(ModItems.RUBY_ORE);
      event.accept(ModItems.DEEPSLATE_RUBY_ORE);
      event.accept(ModItems.ILLUSIONAL_FLOWER);
      event.accept(ModItems.CINDER_BLOSSOM);
      event.accept(ModItems.FROST_LILY);
      event.accept(ModItems.SOLAR_DAISY);
      event.accept(ModItems.GALE_PETAL);
      event.accept(ModItems.ECHO_VIOLET);
      event.accept(ModItems.SHADOW_ORCHID);
      event.accept(ModItems.VITALLIA);
      event.accept(ModItems.THUNDER_POPPY);
      event.accept(ModItems.VOID_CHRYSANTHEMUM);
      event.accept(ModItems.SLIME_LOTUS);
      event.accept(ModItems.EMBER_ROSE);
      event.accept(ModItems.AURA_MARIGOLD);
    } else if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.SPAWN_EGGS) {
      event.accept(ModItems.EMERALD_SCUTTLER_SPAWN_EGG);
      event.accept(ModItems.EMERALD_TITAN_SPAWN_EGG);
    } else if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.FUNCTIONAL_BLOCKS) {
      event.accept(ModItems.AMETHYST_ALTAR);
    }
  }

  @SubscribeEvent
  public void onPlayerTick(PlayerTickEvent.Post event) {
    Player player = event.getEntity();
    ModEnchantmentHandler.onPlayerTick(player);
    com.sxnnyside.emeraldessentials.entity.MushroomBupSpawner.onPlayerTick(player);
    com.sxnnyside.emeraldessentials.entity.MantabuSpawner.onPlayerTick(player);
  }

  @SubscribeEvent
  public void onLivingDamage(LivingDamageEvent.Post event) {
    if (event.getSource().getEntity() instanceof LivingEntity attacker) {
      ModEnchantmentHandler.onLivingHurt(event.getEntity(), attacker);
    }
  }

  @SubscribeEvent
  public void onBlockBreak(net.neoforged.neoforge.event.level.BlockEvent.BreakEvent event) {
    if (event.getLevel() instanceof net.minecraft.world.level.Level level) {
      com.sxnnyside.emeraldessentials.entity.AmethystScarabSpawner.onBlockBreak(
          level, event.getPos(), event.getState());
    }
  }

  @SubscribeEvent
  public void onRegisterBrewingRecipes(
      net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent event) {
    com.sxnnyside.emeraldessentials.init.ModBrewingRecipes.registerWithBuilder(event.getBuilder());
  }
}
