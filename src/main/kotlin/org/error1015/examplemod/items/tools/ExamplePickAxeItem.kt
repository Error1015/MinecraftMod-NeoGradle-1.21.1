package org.error1015.examplemod.items.tools

import net.minecraft.world.item.Rarity
import net.minecraft.world.item.TieredItem
import org.error1015.examplemod.items.tiers.ExampleTier

object ExamplePickAxeItem : TieredItem(
    ExampleTier, Properties().stacksTo(1).rarity(Rarity.COMMON)
)