package org.error1015.examplemod.data

import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider
import org.error1015.examplemod.MODID
import org.error1015.examplemod.items.ModItems

class ModLanguageDataGen(
    val output: PackOutput
) {
    val zhCn = ZhCnLanguageProvider()
    val enUs = EnUsLanguageProvider()

    inner class ZhCnLanguageProvider : AbstractModLanguageProvider(
        output, "zh_cn"
    ) {
        override fun addItems() {
            add(ModItems.ExampleItem, "ExampleItem")
        }

        override fun addBlocks() {
        }

        override fun addKeys() {
            add("examplemod_keymappings", "Example Mod Keys")
            add("player_fly_mode", "飞行模式")
        }
    }

    inner class EnUsLanguageProvider : AbstractModLanguageProvider(output, "en_us") {
        override fun addItems() {
            add(ModItems.ExampleItem, "ExampleItem")
        }

        override fun addBlocks() {
        }

        override fun addKeys() {
            add("examplemod_keymappings", "Example Mod Keys")
            add("player_fly_mode", "Flying Mode")
        }
    }
}