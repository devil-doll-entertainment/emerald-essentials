package com.sxnnyside.emeraldessentials.item;

import com.sxnnyside.emeraldessentials.EmeraldEssentials;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class ModArmorMaterials {
  public static final Holder<ArmorMaterial> EMERALD =
      register(
          "emerald",
          Util.make(
              new EnumMap<>(ArmorItem.Type.class),
              map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 9);
              }),
          25,
          SoundEvents.ARMOR_EQUIP_DIAMOND,
          1.0F,
          0.0F,
          () -> Ingredient.of(Items.EMERALD));

  private ModArmorMaterials() {}

  private static Holder<ArmorMaterial> register(
      String name,
      EnumMap<ArmorItem.Type, Integer> defense,
      int enchantmentValue,
      Holder<SoundEvent> equipSound,
      float toughness,
      float knockbackResistance,
      Supplier<Ingredient> repairIngredient) {
    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EmeraldEssentials.MOD_ID, name);
    List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(id));
    return Registry.registerForHolder(
        BuiltInRegistries.ARMOR_MATERIAL,
        id,
        new ArmorMaterial(
            defense,
            enchantmentValue,
            equipSound,
            repairIngredient,
            layers,
            toughness,
            knockbackResistance));
  }

  public static void init() {
    // Classloading trigger
  }
}
