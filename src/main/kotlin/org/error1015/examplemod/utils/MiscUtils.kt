package org.error1015.examplemod.utils

import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.ICancellableEvent
import org.error1015.examplemod.MODID

val MinecraftInstance: Minecraft
    get() = Minecraft.getInstance()

internal val String.asResourceLocationPath: ResourceLocation get() = ResourceLocation.fromNamespaceAndPath(MODID, this)

internal val String.asComponent: MutableComponent get() = Component.literal(this)

internal val String.asTranslatableComponent: MutableComponent get() = Component.translatable(this)

val ICancellableEvent.cancel: Unit
    get() {
        isCanceled = true
    }