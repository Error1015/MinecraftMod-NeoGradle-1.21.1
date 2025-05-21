package org.error1015.examplemod.registry

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.registries.DataPackRegistryEvent
import net.neoforged.neoforge.registries.NewRegistryEvent
import org.error1015.examplemod.MODID

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
object RegisterEventHandler {
    @SubscribeEvent
    fun registerResource(event: NewRegistryEvent) {
        event.register(ModRegistries.SpellRegistry)
    }

    // @SubscribeEvent
    // fun registerDataResource(event: DataPackRegistryEvent.NewRegistry) {
    //     event.dataPackRegistry(
    //         ModRegistries.SpellRegistryKey, Spell.CODEC
    //     )
    // }
}