package com.sxnnyside.emeraldessentials.init;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import com.sxnnyside.emeraldessentials.item.EmeraldDaggerItem;
import com.sxnnyside.emeraldessentials.item.ModArmorMaterials;
import com.sxnnyside.emeraldessentials.item.ModToolTiers;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;

public final class ModItems {
  private static final Map<ResourceLocation, Item> ENTRIES = new LinkedHashMap<>();

  // Emerald Tools
  public static final SwordItem EMERALD_SWORD =
      register(
          "emerald_sword",
          new SwordItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(SwordItem.createAttributes(ModToolTiers.EMERALD, 3, -2.4F))));

  public static final PickaxeItem EMERALD_PICKAXE =
      register(
          "emerald_pickaxe",
          new PickaxeItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(PickaxeItem.createAttributes(ModToolTiers.EMERALD, 1.0F, -2.8F))));

  public static final AxeItem EMERALD_AXE =
      register(
          "emerald_axe",
          new AxeItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(AxeItem.createAttributes(ModToolTiers.EMERALD, 5.0F, -3.0F))));

  public static final ShovelItem EMERALD_SHOVEL =
      register(
          "emerald_shovel",
          new ShovelItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(ShovelItem.createAttributes(ModToolTiers.EMERALD, 1.5F, -3.0F))));

  public static final HoeItem EMERALD_HOE =
      register(
          "emerald_hoe",
          new HoeItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(HoeItem.createAttributes(ModToolTiers.EMERALD, -3.0F, 0.0F))));

  public static final EmeraldDaggerItem EMERALD_DAGGER =
      register(
          "emerald_dagger",
          new EmeraldDaggerItem(
              ModToolTiers.EMERALD,
              new Item.Properties()
                  .attributes(SwordItem.createAttributes(ModToolTiers.EMERALD, 2, -1.2F))));

  // Emerald Armor
  public static final ArmorItem EMERALD_HELMET =
      register(
          "emerald_helmet",
          new ArmorItem(
              ModArmorMaterials.EMERALD,
              ArmorItem.Type.HELMET,
              new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(22))));

  public static final ArmorItem EMERALD_CHESTPLATE =
      register(
          "emerald_chestplate",
          new ArmorItem(
              ModArmorMaterials.EMERALD,
              ArmorItem.Type.CHESTPLATE,
              new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(22))));

  public static final ArmorItem EMERALD_LEGGINGS =
      register(
          "emerald_leggings",
          new ArmorItem(
              ModArmorMaterials.EMERALD,
              ArmorItem.Type.LEGGINGS,
              new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(22))));

  public static final ArmorItem EMERALD_BOOTS =
      register(
          "emerald_boots",
          new ArmorItem(
              ModArmorMaterials.EMERALD,
              ArmorItem.Type.BOOTS,
              new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(22))));

  // Flora
  public static final BlockItem ILLUSIONAL_FLOWER =
      register(
          "illusional_flower", new BlockItem(ModBlocks.ILLUSIONAL_FLOWER, new Item.Properties()));

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
