package org.error1015.examplemod.data

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class MyRecipeProvider(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) : RecipeProvider(output, registries) {
    override fun buildRecipes(recipeOutput: RecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DIAMOND, 2)
            .pattern("A")
            .pattern("A")
            .pattern("A")
            .define('A', Items.STONE)
            .unlockedBy("has_stone", has(Items.STONE))
            .save(recipeOutput)
    }
}