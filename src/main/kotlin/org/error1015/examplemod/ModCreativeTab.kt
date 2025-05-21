package org.error1015.examplemod

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.CreativeModeTab
import net.neoforged.neoforge.registries.DeferredRegister
import org.error1015.examplemod.items.ModItems
import org.error1015.examplemod.utils.asComponent
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModCreativeTab {
    val builder: DeferredRegister<CreativeModeTab> = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, MODID)

    val myTab: CreativeModeTab by builder.register("my_tab") { ->
        CreativeModeTab
            .builder()
            .title("My Tab".asComponent)
            .displayItems { _, output ->
                output.acceptAll(ModItems.ITEMS.entries.map { it.get().defaultInstance })
            }
            .build()
    }
}