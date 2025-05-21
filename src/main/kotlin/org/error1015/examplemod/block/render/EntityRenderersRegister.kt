package org.error1015.examplemod.block.render

import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import org.error1015.examplemod.block.blockentity.ModBlockEntities

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object EntityRenderersRegister {
    @SubscribeEvent
    fun register(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerBlockEntityRenderer(ModBlockEntities.myBlockEntityType) { _ -> MyBlockEntityRender }
    }

    @SubscribeEvent
    fun registerClientExtensions(event: RegisterClientExtensionsEvent) {
        // event.registerItem(
        //     MyClientItemExtensions,
        //     // Items Here
        // )
    }
}