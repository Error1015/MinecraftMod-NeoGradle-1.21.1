package org.error1015.examplemod.network.packets

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.neoforged.neoforge.network.handling.IPayloadContext
import org.error1015.examplemod.utils.asResourceLocationPath
import java.util.*

data class PlayerAbilityPacket(
    val playerUUID: UUID
) : CustomPacketPayload {
    companion object {
        @JvmStatic
        val CODEC: StreamCodec<FriendlyByteBuf, PlayerAbilityPacket> = StreamCodec.of(
            { buf, packet -> buf.writeUUID(packet.playerUUID) },
            { buf -> PlayerAbilityPacket(buf.readUUID()) })

        @JvmStatic
        val type = CustomPacketPayload.Type<PlayerAbilityPacket>("set_player_ability".asResourceLocationPath())
    }

    override fun type() = type

    fun handle(ctx: IPayloadContext) {
        ctx.enqueueWork {
            val server = ctx.player().server ?: return@enqueueWork
            server.execute {
                val player = server.playerList.getPlayer(playerUUID) ?: return@execute
                player.abilities.mayfly = !player.abilities.mayfly
                player.onUpdateAbilities()

                val text = when (player.abilities.mayfly) {
                    true -> Component.literal("可以飞起来里OwO").withColor(16755200)
                    false -> Component.literal("飞不起来里QwQ").withColor(0xFF0000)
                } ?: return@execute
                player.displayClientMessage(text, true)
            }
        }
    }
}