package org.error1015.examplemod.block.blockentity

import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class MyBlockEntity(
    pos: BlockPos,
    state: BlockState
) : BlockEntity(ModBlockEntities.myBlockEntityType, pos, state) {
    private var value: Int = 0

    override fun loadAdditional(
        tag: CompoundTag,
        registries: HolderLookup.Provider
    ) {
        super.loadAdditional(tag, registries)
        value = tag.getInt("value")
    }

    override fun saveAdditional(
        tag: CompoundTag,
        registries: HolderLookup.Provider
    ) {
        super.saveAdditional(tag, registries)
        tag.putInt("value", value)
        setChanged()
    }

    override fun getUpdatePacket(): Packet<ClientGamePacketListener?>? {
        return ClientboundBlockEntityDataPacket.create(this)
    }
}