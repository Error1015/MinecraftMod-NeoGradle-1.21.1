package org.error1015.examplemod.items

import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredRegister
import org.error1015.examplemod.MODID
import org.error1015.examplemod.items.misc.MyShearsItem
import org.error1015.examplemod.items.swords.ExampleSwordItem
import org.error1015.examplemod.items.tiers.ExampleTier
import org.error1015.examplemod.items.tools.ExamplePickAxeItem
import org.error1015.examplemod.items.tools.ExampleSuperToolItem
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModItems {
    val ITEMS: DeferredRegister.Items = DeferredRegister.Items.createItems(MODID)

    val ExampleItem: Item by ITEMS.registerItem("example_item") { Item(Item.Properties()) }
    val ModShear: Item by ITEMS.registerItem("my_shears") { MyShearsItem() }
    val ExampleSword: Item by ITEMS.registerItem("example_sword") { ExampleSwordItem }
    val ExamplePickAxe: Item by ITEMS.registerItem("example_pickaxe") { ExamplePickAxeItem }
    val ExampleSuperTool: Item by ITEMS.registerItem("example_super_tool") { ExampleSuperToolItem(ExampleTier) }
}