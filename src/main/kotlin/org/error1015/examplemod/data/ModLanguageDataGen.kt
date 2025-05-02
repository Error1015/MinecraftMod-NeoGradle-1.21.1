package org.error1015.examplemod.data

import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider
import org.error1015.examplemod.MODID
import org.error1015.examplemod.items.ModItems

class ModLanguageDataGen(
    val output: PackOutput
) {
    val zh_cn = ZhCnLanguageProvider()
    val en_us = EnUsLanguageProvider()

    inner class ZhCnLanguageProvider : LanguageProvider(
        output, MODID, "zh_cn"
    ) {
        override fun addTranslations() {
            this.addItems()
            this.addCommonKeys()
        }

        private fun addItems() {
            add(ModItems.ExampleItem, "ExampleItem")
        }

        private fun addCommonKeys() {
            add("examplemod_keymappings", "Example Mod Keys")
            add("player_fly_mode", "飞行模式")
        }
    }

    inner class EnUsLanguageProvider : LanguageProvider(output, MODID, "en_us") {
        override fun addTranslations() {
            this.addItems()
            this.addCommonKeys()
        }

        private fun addItems() {
            add(ModItems.ExampleItem, "ExampleItem")
        }

        private fun addCommonKeys() {
            add("examplemod_keymappings", "Example Mod Keys")
            add("player_fly_mode", "Flying Mode")
        }
    }
}