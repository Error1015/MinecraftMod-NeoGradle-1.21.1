package org.error1015.examplemod.event

import com.mojang.blaze3d.platform.InputConstants
import net.minecraft.client.KeyMapping
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent
import org.error1015.examplemod.MODID
import org.lwjgl.glfw.GLFW

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object KeyMappingRegister {
    val playerFlyMode by lazy {
        KeyMapping("player_fly_mode", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, "examplemod_keymappings")
    }

    @SubscribeEvent
    fun registerKeyMappings(event: RegisterKeyMappingsEvent) {
        event.register(playerFlyMode)
    }
}