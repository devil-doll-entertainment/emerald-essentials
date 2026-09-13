package com.sxnnyside.emeraldessentials.datagen;

import com.sxnnyside.emeraldessentials.init.ModBlocks;
import com.sxnnyside.emeraldessentials.init.ModItems;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

/**
 * Unified cross-loader recipe provider built on vanilla Minecraft's DataGen framework. Executable
 * by both Fabric and NeoForge data generators without duplication.
 */
public class ModRecipeProvider extends RecipeProvider {

  public ModRecipeProvider(
      @NotNull PackOutput output,
      @NotNull CompletableFuture<HolderLookup.Provider> lookupProvider) {
    super(output, lookupProvider);
  }

  @Override
  public void buildRecipes(@NotNull RecipeOutput output) {
    // Ruby block from 9 rubies
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RUBY_BLOCK)
        .pattern("RRR")
        .pattern("RRR")
        .pattern("RRR")
        .define('R', ModItems.RUBY)
        .unlockedBy("has_ruby", has(ModItems.RUBY))
        .save(output);

    // 9 rubies from ruby block
    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY, 9)
        .requires(ModBlocks.RUBY_BLOCK)
        .unlockedBy("has_ruby_block", has(ModBlocks.RUBY_BLOCK))
        .save(output, "ruby_from_block");

    // Enchanted Emerald: surrounded by 4 amethyst shards and 4 redstone
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENCHANTED_EMERALD)
        .pattern("ARA")
        .pattern("RER")
        .pattern("ARA")
        .define('E', Items.EMERALD)
        .define('A', Items.AMETHYST_SHARD)
        .define('R', Items.REDSTONE)
        .unlockedBy("has_emerald", has(Items.EMERALD))
        .save(output);

    // Emerald Mirror: 4 enchanted emeralds + glass pane
    ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.EMERALD_MIRROR)
        .pattern(" E ")
        .pattern("EGE")
        .pattern(" E ")
        .define('E', ModItems.ENCHANTED_EMERALD)
        .define('G', Items.GLASS_PANE)
        .unlockedBy("has_enchanted_emerald", has(ModItems.ENCHANTED_EMERALD))
        .save(output);
  }
}
