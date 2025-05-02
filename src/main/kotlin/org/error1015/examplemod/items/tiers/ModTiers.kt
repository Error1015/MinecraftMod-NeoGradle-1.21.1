package org.error1015.examplemod.items.tiers

import net.minecraft.tags.BlockTags
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.common.SimpleTier

object ExampleTier : SimpleTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL , 1000, 10.0f, 114513f, 10086, { Ingredient.of(Items.ICE) })