package org.error1015.examplemod.network.packets

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.neoforged.neoforge.network.handling.IPayloadContext
import org.error1015.examplemod.utils.asPath
import java.util.*

data class PlayerFlyAbilityPacket(
    val playerUUID: UUID
) : CustomPacketPayload {
    companion object {
        @JvmStatic
        val CODEC: StreamCodec<FriendlyByteBuf, PlayerFlyAbilityPacket> = StreamCodec.of({ buf, packet -> buf.writeUUID(packet.playerUUID) }, { buf -> PlayerFlyAbilityPacket(buf.readUUID()) })

        @JvmStatic
        val type = CustomPacketPayload.Type<PlayerFlyAbilityPacket>("set_player_ability".asPath)
    }

    override fun type() = type

    fun handle(ctx: IPayloadContext) {
        ctx.enqueueWork {
            val server = ctx.player().server ?: return@enqueueWork
            server.execute {
                val player = server.playerList.getPlayer(playerUUID) ?: return@execute
                player.abilities.mayfly = !player.abilities.mayfly

                player.onUpdateAbilities()
                val text = when (player.mayFly()) {
                    true -> Component
                        .literal("可以飞起来里OwO")
                        .withColor(16755200)

                    false -> Component
                        .literal("飞不起来里QwQ")
                        .withColor(0xFF0000)
                } ?: return@execute

                player.displayClientMessage(text, true)
            }
        }
    }
}