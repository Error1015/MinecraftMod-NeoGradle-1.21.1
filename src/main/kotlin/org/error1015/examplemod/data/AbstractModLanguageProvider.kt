package org.error1015.examplemod.data

import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider
import org.error1015.examplemod.MODID

abstract class AbstractModLanguageProvider(
    output: PackOutput,
    locale: String
) : LanguageProvider(output, MODID, locale) {
    override fun addTranslations() {
        addItems()
        addKeys()
        addBlocks()
    }

    abstract fun addItems()
    abstract fun addBlocks()
    abstract fun addKeys()
}