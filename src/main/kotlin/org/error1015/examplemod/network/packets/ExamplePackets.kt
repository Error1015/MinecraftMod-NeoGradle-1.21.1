package org.error1015.examplemod.network.packets

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.neoforged.neoforge.network.handling.IPayloadContext
import org.error1015.examplemod.utils.asComponent
import org.error1015.examplemod.utils.asResourceLocationPath

data class ExamplePackets(
    val string: String,
    val int: Int,
    val boolean: Boolean
) : CustomPacketPayload {
    companion object {
        @JvmStatic
        val CODEC: StreamCodec<FriendlyByteBuf, ExamplePackets> = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ExamplePackets::string, ByteBufCodecs.INT, ExamplePackets::int, ByteBufCodecs.BOOL, ExamplePackets::boolean, ::ExamplePackets
        )

        @JvmStatic
        val type = CustomPacketPayload.Type<ExamplePackets>("example_packet".asResourceLocationPath)
    }

    override fun type() = type

    fun handle(ctx: IPayloadContext) {
        val (str, int, bool) = this
        ctx
            .player()
            .displayClientMessage("聊天信息: ${str}, 信息长度: $int, 事件被取消 $bool".asComponent, true)
    }
}