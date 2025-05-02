package org.error1015.examplemod.utils

import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.component.Tool
import net.neoforged.bus.api.ICancellableEvent
import net.neoforged.neoforge.event.entity.EntityEvent
import org.error1015.examplemod.MODID

val MinecraftInstance: Minecraft
    get() = Minecraft.getInstance()

internal val String.asPath: ResourceLocation get() = ResourceLocation.fromNamespaceAndPath(MODID, this)

internal val String.asComponent: MutableComponent get() = Component.literal(this)

internal val String.asTranslatableComponent: MutableComponent get() = Component.translatable(this)

val ICancellableEvent.cancel: Unit
    @JvmName("cancel") get() {
        isCanceled = true
    }

inline fun <reified T> Any?.safeClassCastAndHandle(block: (T) -> Unit) {
    if (this != null) {
        if (this is T) {
            block(this)
        }
    }
}

inline fun <E : EntityEvent> E.handleClient(clientBLock: E.() -> Unit) {
    if (this.entity.level().isClientSide) {
        clientBLock()
    }
}

inline fun <E : EntityEvent> E.handleServer(serverBlock: E.() -> Unit) {
    if (!this.entity.level().isClientSide) {
        serverBlock()
    }
}

operator fun Tool.plus(theOther: Tool) = Tool(
    this.rules + theOther.rules, this.defaultMiningSpeed + theOther.defaultMiningSpeed, this.damagePerBlock + theOther.damagePerBlock
)