package org.error1015.examplemod.block.render

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions

object MyClientItemExtensions: IClientItemExtensions {
    private val myBEWLR = MyBlockEntityWithoutLevelRenderer

    override fun getCustomRenderer(): BlockEntityWithoutLevelRenderer = myBEWLR
}