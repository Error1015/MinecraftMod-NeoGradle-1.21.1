package org.error1015.examplemod.registry

import net.minecraft.network.chat.Component
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import org.error1015.examplemod.MODID

data class Spell(
    val name: String, var minCost: Int, var maxCost: Int, var description: Component = Component.translatable("spell.$MODID.$name.desc")
)