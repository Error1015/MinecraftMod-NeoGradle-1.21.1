package org.error1015.examplemod

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.neoforged.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.error1015.examplemod.block.ModBlocks.BLOCKS
import org.error1015.examplemod.items.ModItems.ITEMS
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

const val MODID = "examplemod"

@Mod(MODID)
object ExampleMod {
    val logger: Logger = LogManager.getLogger("$MODID-Main")

    val testObject = MySerializedThing(name = "ExampleMod", version = "1.0.0")
    val json = Json.encodeToString(testObject)

    init {
        ITEMS.register(MOD_BUS)
        BLOCKS.register(MOD_BUS)
        ModCreativeTab.builder.register(MOD_BUS)
    }
}

@Serializable
data class MySerializedThing(
    val name: String,
    val version: String
)