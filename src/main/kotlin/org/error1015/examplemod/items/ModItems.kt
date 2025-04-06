package org.error1015.examplemod.items

import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredRegister
import org.error1015.examplemod.MODID

import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModItems {
    val ITEMS: DeferredRegister.Items = DeferredRegister.Items.createItems(MODID)

    val ExampleItem: Item by ITEMS.registerItem("example_item") { Item(Item.Properties()) }
}