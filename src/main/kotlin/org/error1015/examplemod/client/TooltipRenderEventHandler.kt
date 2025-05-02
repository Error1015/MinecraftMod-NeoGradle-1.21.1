package org.error1015.examplemod.client

import net.minecraft.world.item.Rarity
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.RenderTooltipEvent
import org.error1015.examplemod.MODID

@EventBusSubscriber(modid = MODID, value = [Dist.CLIENT])
object TooltipRenderEventHandler {
    @SubscribeEvent
    fun renderTooltipColor(event: RenderTooltipEvent.Color) {
        val rarity = event.itemStack.rarity ?: return
        when (rarity) {
            Rarity.COMMON -> event.setColor(borderStart = 0xFF47C7A9, borderEnd = 0xFF9933FF, background = 0xE6000000)
            Rarity.UNCOMMON -> event.setColor(borderStart = 0xFF23F3A7, borderEnd = 0xFF23A0F3, background = 0xE6000000)
            Rarity.RARE -> event.setColor(borderStart = 0xFFFFF33, borderEnd = 0xFFFF33FF, background = 0xE6000000)
            Rarity.EPIC -> event.setColor(borderStart = 0xFF843FD2, borderEnd = 0xFFAB3FD2, background = 0xE6000000)
        }
    }

    private fun RenderTooltipEvent.Color.setColor(
        borderStart: Long,
        borderEnd: Long,
        background: Long
    ) {
        this.borderStart = borderStart.toInt()
        this.borderEnd = borderEnd.toInt()
        this.setBackground(background.toInt())
    }
}