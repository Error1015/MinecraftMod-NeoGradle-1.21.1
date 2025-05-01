package org.error1015.examplemod.items.misc

import net.minecraft.core.component.DataComponents
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.ShearsItem
import net.minecraft.world.item.component.Tool
import net.minecraft.world.level.block.Blocks

class MyShearsItem : ShearsItem(Properties().stacksTo(1).durability(500).component(DataComponents.TOOL, createMyToolProperties())) {
    companion object {
        fun createMyToolProperties() = Tool(
            listOf<Tool.Rule>(
                Tool.Rule.minesAndDrops(listOf(Blocks.COBWEB), 30.0f),
                Tool.Rule.overrideSpeed(BlockTags.LEAVES, 30.0f),
                Tool.Rule.overrideSpeed(BlockTags.WOOL, 10.0f),
                Tool.Rule.overrideSpeed(listOf(Blocks.VINE, Blocks.GLOW_LICHEN), 5.0f)
            ), 2f, 2
        )
    }
}