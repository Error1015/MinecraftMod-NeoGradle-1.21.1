package org.error1015.examplemod.network

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object NetworkEventHandler {
    @SubscribeEvent
    fun onNetworkRegister(event: RegisterPayloadHandlersEvent) {
        val registrar = event.registrar("1.0") ?: return
        // registrar.playToServer(
        //     ClearItemsPacket.type, null, ClearItemsPacket::handle
        // )
    }
}