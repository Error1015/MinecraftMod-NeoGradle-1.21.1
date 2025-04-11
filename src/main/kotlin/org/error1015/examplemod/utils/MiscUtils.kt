package org.error1015.examplemod.utils

import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceLocation
import org.error1015.examplemod.MODID

internal fun String.asResourceLocationPath(): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MODID, this)

internal fun String.asComponent(): MutableComponent = Component.literal(this)

internal fun String.asTranslatableComponent(): MutableComponent = Component.translatable(this)