package org.error1015.examplemod.event

import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.Items
import net.minecraft.world.item.component.Unbreakable
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object ModBusEventHandler {
    @SubscribeEvent
    fun modifyComponents(event: ModifyDefaultComponentsEvent) {
        event.modify(Items.DIAMOND_PICKAXE) { builder ->
            builder.set(DataComponents.UNBREAKABLE, Unbreakable(false))
        }

        event.modifyMatching({ _ -> true }) { builder -> /* code here */ }
    }
}