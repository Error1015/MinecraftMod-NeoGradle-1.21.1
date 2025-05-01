package org.error1015.examplemod.items.tiers

import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.common.SimpleTier
import net.neoforged.neoforge.common.Tags

object ExampleTier : SimpleTier(Tags.Blocks.STONES, 1000, 10.0f, 114513f , 10086, { Ingredient.of(Items.ICE) })