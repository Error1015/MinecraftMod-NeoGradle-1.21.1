package org.error1015.examplemod.network.packets

import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.item.ItemEntity
import net.neoforged.neoforge.network.handling.IPayloadContext
import org.error1015.examplemod.utils.asResourceLocationPath

data class ClearItemsPacket(val itemEntities: List<ItemEntity>) : CustomPacketPayload {
    companion object {
        @JvmStatic
        val type: CustomPacketPayload.Type<ClearItemsPacket> = CustomPacketPayload.Type<ClearItemsPacket>(
            "clear_item_packet".asResourceLocationPath()
        )
    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = type

    fun handle(ctx: IPayloadContext) {
        ctx.enqueueWork {
            itemEntities.forEach {
                it.remove(Entity.RemovalReason.KILLED)
            }
        }
    }
}