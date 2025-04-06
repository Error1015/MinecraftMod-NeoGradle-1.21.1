package org.error1015.examplemod.data

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent
import org.error1015.examplemod.MODID

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
object DataGen {
    @SubscribeEvent
    fun genData(event: GatherDataEvent) {
        val generator = event.generator
        val output = generator.packOutput
        val existingFileHelper = event.existingFileHelper
        val lookUpProvider = event.lookupProvider
        generator.addProvider(event.includeServer(), MyRecipeProvider(output, lookUpProvider))
    }
}