package org.error1015.examplemod.block

import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.registries.DeferredRegister
import org.error1015.examplemod.MODID
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModBlocks {
    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(MODID)

    val exampleBlock: Block by BLOCKS.register("example_block") { -> MyBlock }
}