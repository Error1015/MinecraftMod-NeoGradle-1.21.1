package org.error1015.examplemod.network

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import org.error1015.examplemod.network.packets.*

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object NetworkEventHandler {
    /**
     * 注册Packet
     */
    @SubscribeEvent
    fun onNetworkRegister(event: RegisterPayloadHandlersEvent) {
        val registrar = event.registrar("1.0") ?: return
        registrar.playToServer(PlayerFlyAbilityPacket.type, PlayerFlyAbilityPacket.CODEC, PlayerFlyAbilityPacket::handle)
        registrar.playToServer(ExamplePackets.type, ExamplePackets.CODEC, ExamplePackets::handle)
    }
}