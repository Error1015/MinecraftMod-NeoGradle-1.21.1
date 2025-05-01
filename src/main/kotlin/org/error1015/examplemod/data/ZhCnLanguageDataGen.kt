package org.error1015.examplemod.data

import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider
import org.error1015.examplemod.MODID
import org.error1015.examplemod.items.ModItems

class ZhCnLanguageDataGen(output: PackOutput) : LanguageProvider(
    output, MODID, "zh_cn"
) {
    override fun addTranslations() {
        this.addItems()
        this.addCommonKeys()
    }

    private fun addItems() {
        add(ModItems.ExampleItem, "ExampleItem")
    }

    private fun addCommonKeys(){
        add("examplemod_keymappings", "Example Mod Keys")
        add("player_fly_mode", "飞行模式")
    }
}