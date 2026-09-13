package com.sxnnyside.emeraldessentials.init;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.item.AmethystLensItem;
import com.sxnnyside.emeraldessentials.item.AmethystResonatorItem;
import com.sxnnyside.emeraldessentials.item.DescriptiveArmorItem;
import com.sxnnyside.emeraldessentials.item.DescriptiveAxeItem;
import com.sxnnyside.emeraldessentials.item.DescriptiveBlockItem;
import com.sxnnyside.emeraldessentials.item.DescriptiveHoeItem;
import com.sxnnyside.emeraldessentials.item.DescriptiveItem;
import com.sxnnyside.emeraldessentials.item.DescriptivePickaxeItem;
import com.sxnnyside.emeraldessentials.item.DescriptiveShovelItem;
import com.sxnnyside.emeraldessentials.item.DescriptiveSwordItem;
import com.sxnnyside.emeraldessentials.item.EmeraldBowItem;
import com.sxnnyside.emeraldessentials.item.EmeraldCrownItem;
import com.sxnnyside.emeraldessentials.item.EmeraldDaggerItem;
import com.sxnnyside.emeraldessentials.item.EmeraldMirrorItem;
import com.sxnnyside.emeraldessentials.item.EmeraldShieldItem;
import com.sxnnyside.emeraldessentials.item.EmeraldStaffItem;
import com.sxnnyside.emeraldessentials.item.EmeraldWhetstoneItem;
import com.sxnnyside.emeraldessentials.item.EnchantedEmeraldItem;
import com.sxnnyside.emeraldessentials.item.ModArmorMaterials;
import com.sxnnyside.emeraldessentials.item.ModToolTiers;
import com.sxnnyside.emeraldessentials.item.RubyCharmItem;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.SwordItem;

public final class ModItems {
  private static final Map<ResourceLocation, Item> ENTRIES = new LinkedHashMap<>();

  // Consumables & Magic Utilities
  public static final FoodProperties EMERALD_APPLE_FOOD =
      new FoodProperties.Builder()
          .nutrition(6)
          .saturationModifier(1.2F)
          .alwaysEdible()
          .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F)
          .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0F)
          .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 1), 1.0F)
          .build();

  public static final DescriptiveItem EMERALD_APPLE =
      register(
          "emerald_apple",
          new DescriptiveItem(
              new Item.Properties().rarity(Rarity.RARE).food(EMERALD_APPLE_FOOD),
              ChatFormatting.GRAY,
              ChatFormatting.DARK_GREEN));

  public static final EnchantedEmeraldItem ENCHANTED_EMERALD =
      register("enchanted_emerald", new EnchantedEmeraldItem(new Item.Properties()));

  public static final EmeraldMirrorItem EMERALD_MIRROR =
      register("emerald_mirror", new EmeraldMirrorItem(new Item.Properties()));

  public static final EmeraldWhetstoneItem EMERALD_WHETSTONE =
      register("emerald_whetstone", new EmeraldWhetstoneItem(new Item.Properties()));

  // Emerald Tools
  public static final DescriptiveSwordItem EMERALD_SWORD =
      register(
          "emerald_sword",
          new DescriptiveSwordItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(SwordItem.createAttributes(ModToolTiers.EMERALD, 3, -2.4F)),
              ChatFormatting.DARK_GREEN));

  public static final DescriptivePickaxeItem EMERALD_PICKAXE =
      register(
          "emerald_pickaxe",
          new DescriptivePickaxeItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(PickaxeItem.createAttributes(ModToolTiers.EMERALD, 1.0F, -2.8F)),
              ChatFormatting.DARK_GREEN));

  public static final DescriptiveAxeItem EMERALD_AXE =
      register(
          "emerald_axe",
          new DescriptiveAxeItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(AxeItem.createAttributes(ModToolTiers.EMERALD, 5.0F, -3.0F)),
              ChatFormatting.DARK_GREEN));

  public static final DescriptiveShovelItem EMERALD_SHOVEL =
      register(
          "emerald_shovel",
          new DescriptiveShovelItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(ShovelItem.createAttributes(ModToolTiers.EMERALD, 1.5F, -3.0F)),
              ChatFormatting.DARK_GREEN));

  public static final DescriptiveHoeItem EMERALD_HOE =
      register(
          "emerald_hoe",
          new DescriptiveHoeItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(HoeItem.createAttributes(ModToolTiers.EMERALD, -3.0F, 0.0F)),
              ChatFormatting.DARK_GREEN));

  public static final EmeraldDaggerItem EMERALD_DAGGER =
      register(
          "emerald_dagger",
          new EmeraldDaggerItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(SwordItem.createAttributes(ModToolTiers.EMERALD, 2, -1.2F))));

  // Emerald Armor
  public static final DescriptiveArmorItem EMERALD_HELMET =
      register(
          "emerald_helmet",
          new DescriptiveArmorItem(
              ModArmorMaterials.EMERALD,
              ArmorItem.Type.HELMET,
              new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(22)),
              ChatFormatting.DARK_GREEN));

  public static final DescriptiveArmorItem EMERALD_CHESTPLATE =
      register(
          "emerald_chestplate",
          new DescriptiveArmorItem(
              ModArmorMaterials.EMERALD,
              ArmorItem.Type.CHESTPLATE,
              new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(22)),
              ChatFormatting.DARK_GREEN));

  public static final DescriptiveArmorItem EMERALD_LEGGINGS =
      register(
          "emerald_leggings",
          new DescriptiveArmorItem(
              ModArmorMaterials.EMERALD,
              ArmorItem.Type.LEGGINGS,
              new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(22)),
              ChatFormatting.DARK_GREEN));

  public static final DescriptiveArmorItem EMERALD_BOOTS =
      register(
          "emerald_boots",
          new DescriptiveArmorItem(
              ModArmorMaterials.EMERALD,
              ArmorItem.Type.BOOTS,
              new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(22)),
              ChatFormatting.DARK_GREEN));

  // Flora & Blocks
  public static final DescriptiveBlockItem ILLUSIONAL_FLOWER =
      register(
          "illusional_flower",
          new DescriptiveBlockItem(
              ModBlocks.ILLUSIONAL_FLOWER,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.DARK_GREEN));

  public static final DescriptiveBlockItem CINDER_BLOSSOM =
      register(
          "cinder_blossom",
          new DescriptiveBlockItem(
              ModBlocks.CINDER_BLOSSOM,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.GOLD));

  public static final DescriptiveBlockItem FROST_LILY =
      register(
          "frost_lily",
          new DescriptiveBlockItem(
              ModBlocks.FROST_LILY,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.AQUA));

  public static final DescriptiveBlockItem SOLAR_DAISY =
      register(
          "solar_daisy",
          new DescriptiveBlockItem(
              ModBlocks.SOLAR_DAISY,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.YELLOW));

  public static final DescriptiveBlockItem GALE_PETAL =
      register(
          "gale_petal",
          new DescriptiveBlockItem(
              ModBlocks.GALE_PETAL,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.WHITE));

  public static final DescriptiveBlockItem ECHO_VIOLET =
      register(
          "echo_violet",
          new DescriptiveBlockItem(
              ModBlocks.ECHO_VIOLET,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.DARK_AQUA));

  public static final DescriptiveBlockItem SHADOW_ORCHID =
      register(
          "shadow_orchid",
          new DescriptiveBlockItem(
              ModBlocks.SHADOW_ORCHID,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.DARK_PURPLE));

  public static final DescriptiveBlockItem VITALLIA =
      register(
          "vitallia",
          new DescriptiveBlockItem(
              ModBlocks.VITALLIA,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.GREEN));

  public static final DescriptiveBlockItem THUNDER_POPPY =
      register(
          "thunder_poppy",
          new DescriptiveBlockItem(
              ModBlocks.THUNDER_POPPY,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.GOLD));

  public static final DescriptiveBlockItem VOID_CHRYSANTHEMUM =
      register(
          "void_chrysanthemum",
          new DescriptiveBlockItem(
              ModBlocks.VOID_CHRYSANTHEMUM,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.LIGHT_PURPLE));

  public static final DescriptiveBlockItem SLIME_LOTUS =
      register(
          "slime_lotus",
          new DescriptiveBlockItem(
              ModBlocks.SLIME_LOTUS,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.GREEN));

  public static final DescriptiveBlockItem EMBER_ROSE =
      register(
          "ember_rose",
          new DescriptiveBlockItem(
              ModBlocks.EMBER_ROSE,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.RED));

  public static final DescriptiveBlockItem AURA_MARIGOLD =
      register(
          "aura_marigold",
          new DescriptiveBlockItem(
              ModBlocks.AURA_MARIGOLD,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.GOLD));

  public static final DescriptiveBlockItem RUBY_ORE =
      register(
          "ruby_ore",
          new DescriptiveBlockItem(
              ModBlocks.RUBY_ORE, new Item.Properties(), ChatFormatting.GRAY, ChatFormatting.GOLD));

  public static final DescriptiveBlockItem DEEPSLATE_RUBY_ORE =
      register(
          "deepslate_ruby_ore",
          new DescriptiveBlockItem(
              ModBlocks.DEEPSLATE_RUBY_ORE,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.GOLD));

  public static final DescriptiveBlockItem RUBY_BLOCK =
      register(
          "ruby_block",
          new DescriptiveBlockItem(
              ModBlocks.RUBY_BLOCK,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.GOLD));

  public static final DescriptiveBlockItem AMETHYST_ALTAR =
      register(
          "amethyst_altar",
          new DescriptiveBlockItem(
              ModBlocks.AMETHYST_ALTAR,
              new Item.Properties(),
              ChatFormatting.GRAY,
              ChatFormatting.LIGHT_PURPLE));

  // Ruby Mineral & Gear
  public static final DescriptiveItem RUBY =
      register(
          "ruby",
          new DescriptiveItem(new Item.Properties(), ChatFormatting.GRAY, ChatFormatting.GOLD));

  public static final DescriptiveSwordItem RUBY_SWORD =
      register(
          "ruby_sword",
          new DescriptiveSwordItem(
              ModToolTiers.RUBY,
              new Item.Properties()
                  .attributes(SwordItem.createAttributes(ModToolTiers.RUBY, 3, -2.4F)),
              ChatFormatting.GOLD));

  public static final DescriptivePickaxeItem RUBY_PICKAXE =
      register(
          "ruby_pickaxe",
          new DescriptivePickaxeItem(
              ModToolTiers.RUBY,
              new Item.Properties()
                  .attributes(PickaxeItem.createAttributes(ModToolTiers.RUBY, 1.0F, -2.8F)),
              ChatFormatting.GOLD));

  public static final DescriptiveAxeItem RUBY_AXE =
      register(
          "ruby_axe",
          new DescriptiveAxeItem(
              ModToolTiers.RUBY,
              new Item.Properties()
                  .attributes(AxeItem.createAttributes(ModToolTiers.RUBY, 5.0F, -3.0F)),
              ChatFormatting.GOLD));

  public static final DescriptiveShovelItem RUBY_SHOVEL =
      register(
          "ruby_shovel",
          new DescriptiveShovelItem(
              ModToolTiers.RUBY,
              new Item.Properties()
                  .attributes(ShovelItem.createAttributes(ModToolTiers.RUBY, 1.5F, -3.0F)),
              ChatFormatting.GOLD));

  public static final DescriptiveHoeItem RUBY_HOE =
      register(
          "ruby_hoe",
          new DescriptiveHoeItem(
              ModToolTiers.RUBY,
              new Item.Properties()
                  .attributes(HoeItem.createAttributes(ModToolTiers.RUBY, -3.0F, 0.0F)),
              ChatFormatting.GOLD));

  public static final EmeraldDaggerItem RUBY_DAGGER =
      register(
          "ruby_dagger",
          new EmeraldDaggerItem(
              ModToolTiers.RUBY,
              new Item.Properties()
                  .attributes(SwordItem.createAttributes(ModToolTiers.RUBY, 2, -1.2F))));

  public static final DescriptiveArmorItem RUBY_HELMET =
      register(
          "ruby_helmet",
          new DescriptiveArmorItem(
              ModArmorMaterials.RUBY,
              ArmorItem.Type.HELMET,
              new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33)),
              ChatFormatting.GOLD));

  public static final DescriptiveArmorItem RUBY_CHESTPLATE =
      register(
          "ruby_chestplate",
          new DescriptiveArmorItem(
              ModArmorMaterials.RUBY,
              ArmorItem.Type.CHESTPLATE,
              new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33)),
              ChatFormatting.GOLD));

  public static final DescriptiveArmorItem RUBY_LEGGINGS =
      register(
          "ruby_leggings",
          new DescriptiveArmorItem(
              ModArmorMaterials.RUBY,
              ArmorItem.Type.LEGGINGS,
              new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33)),
              ChatFormatting.GOLD));

  public static final DescriptiveArmorItem RUBY_BOOTS =
      register(
          "ruby_boots",
          new DescriptiveArmorItem(
              ModArmorMaterials.RUBY,
              ArmorItem.Type.BOOTS,
              new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33)),
              ChatFormatting.GOLD));

  // Artifacts & Utility Gear
  public static final AmethystResonatorItem AMETHYST_RESONATOR =
      register(
          "amethyst_resonator", new AmethystResonatorItem(new Item.Properties().durability(250)));

  public static final AmethystLensItem AMETHYST_LENS =
      register("amethyst_lens", new AmethystLensItem(new Item.Properties().durability(64)));

  public static final EmeraldShieldItem EMERALD_SHIELD =
      register("emerald_shield", new EmeraldShieldItem(new Item.Properties().durability(500)));

  public static final EmeraldBowItem EMERALD_BOW =
      register("emerald_bow", new EmeraldBowItem(new Item.Properties().durability(576)));

  public static final RubyCharmItem RUBY_CHARM =
      register("ruby_charm", new RubyCharmItem(new Item.Properties().durability(32)));

  // Endgame Progression & Summoning
  public static final DescriptiveItem EMERALD_KEYSTONE =
      register(
          "emerald_keystone",
          new DescriptiveItem(
              new Item.Properties().rarity(Rarity.RARE),
              ChatFormatting.GRAY,
              ChatFormatting.DARK_GREEN));

  public static final DescriptiveItem EMERALD_CORE =
      register(
          "emerald_core",
          new DescriptiveItem(
              new Item.Properties().rarity(Rarity.EPIC), ChatFormatting.GRAY, ChatFormatting.GOLD));

  public static final EmeraldCrownItem EMERALD_CROWN =
      register("emerald_crown", new EmeraldCrownItem(new Item.Properties()));

  public static final EmeraldStaffItem EMERALD_STAFF =
      register("emerald_staff", new EmeraldStaffItem(new Item.Properties()));

  public static final SpawnEggItem EMERALD_SCUTTLER_SPAWN_EGG =
      register(
          "emerald_scuttler_spawn_egg",
          new SpawnEggItem(
              ModEntities.EMERALD_SCUTTLER, 0x1B4D3E, 0x0BDA51, new Item.Properties()));

  public static final SpawnEggItem EMERALD_TITAN_SPAWN_EGG =
      register(
          "emerald_titan_spawn_egg",
          new SpawnEggItem(ModEntities.EMERALD_TITAN, 0x072A1A, 0x9B59B6, new Item.Properties()));

  // Amethyst Scarab
  public static final SpawnEggItem AMETHYST_SCARAB_SPAWN_EGG =
      register(
          "amethyst_scarab_spawn_egg",
          new SpawnEggItem(ModEntities.AMETHYST_SCARAB, 0x3A2A5E, 0xC9A0F5, new Item.Properties()));

  // Mushroom Bup
  public static final SpawnEggItem MUSHROOM_BUP_SPAWN_EGG =
      register(
          "mushroom_bup_spawn_egg",
          new SpawnEggItem(ModEntities.MUSHROOM_BUP, 0xB33033, 0xE8D5B5, new Item.Properties()));

  // Mantabu
  public static final SpawnEggItem MANTABU_SPAWN_EGG =
      register(
          "mantabu_spawn_egg",
          new SpawnEggItem(ModEntities.MANTABU, 0x3696D7, 0xE9D9C0, new Item.Properties()));

  private ModItems() {}

  private static <T extends Item> T register(String name, T item) {
    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, name);
    ENTRIES.put(id, item);
    return item;
  }

  public static Map<ResourceLocation, Item> getEntries() {
    return Collections.unmodifiableMap(ENTRIES);
  }

  public static void registerVanilla() {
    ENTRIES.forEach((id, item) -> Registry.register(BuiltInRegistries.ITEM, id, item));
  }
}
