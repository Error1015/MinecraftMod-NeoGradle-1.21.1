package org.error1015.examplemod.block

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import org.error1015.examplemod.block.blockentity.MyBlockEntity

object MyBlock : Block(Properties.of()), EntityBlock {
    override fun newBlockEntity(
        pos: BlockPos,
        state: BlockState
    ) = MyBlockEntity(pos, state)


    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T?>
    ): BlockEntityTicker<T?>? {
        return super.getTicker(level, state, blockEntityType)
    }
}