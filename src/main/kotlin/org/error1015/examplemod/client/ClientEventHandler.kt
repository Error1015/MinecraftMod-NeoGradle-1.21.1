package org.error1015.examplemod.client

import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.network.PacketDistributor
import org.error1015.examplemod.MODID
import org.error1015.examplemod.event.KeyMappingRegister
import org.error1015.examplemod.network.packets.PlayerFlyAbilityPacket
import org.error1015.examplemod.utils.MinecraftInstance
import org.error1015.examplemod.utils.asComponent

@EventBusSubscriber(value = [Dist.CLIENT], modid = MODID)
object ClientEventHandler {
    /**
     * 处理按键点击
     */
    @SubscribeEvent
    fun onClientTick(event: ClientTickEvent.Post) {
        while (KeyMappingRegister.playerFlyMode.consumeClick()) {
            MinecraftInstance.player?.let { player ->
                player.displayClientMessage("正在努力切换飞行模式QwQ".asComponent.withColor(16755200), true)
                PacketDistributor.sendToServer(PlayerFlyAbilityPacket(player.uuid))
            }
        }
    }
}