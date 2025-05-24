package org.error1015.examplemod.network

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import org.error1015.examplemod.MODID
import org.error1015.examplemod.network.packets.ExamplePackets
import org.error1015.examplemod.network.packets.PlayerFlyAbilityPacket

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MODID)
object NetworkEventHandler {
    /**
     * 注册Packet
     */
    @SubscribeEvent
    fun onNetworkRegister(event: RegisterPayloadHandlersEvent) {
        event.registrar("1.0").apply {
            playToServer(PlayerFlyAbilityPacket.type, PlayerFlyAbilityPacket.CODEC, PlayerFlyAbilityPacket::handle)
            playToServer(ExamplePackets.type, ExamplePackets.CODEC, ExamplePackets::handle)
        }
    }
}