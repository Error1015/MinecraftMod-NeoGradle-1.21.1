package org.error1015.examplemod

import net.minecraft.world.item.CreativeModeTabs
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import org.error1015.examplemod.items.ModItems

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MODID)
object ModCreativeTab {
    @SubscribeEvent
    fun addToCreativeTab(event: BuildCreativeModeTabContentsEvent) {
        if (event.tabKey == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.acceptAll(ModItems.ITEMS.entries.map { it.get().defaultInstance })
        }
    }
}