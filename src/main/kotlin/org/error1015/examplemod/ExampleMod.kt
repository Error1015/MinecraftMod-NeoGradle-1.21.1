package org.error1015.examplemod

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.neoforged.fml.common.Mod
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.error1015.examplemod.block.ModBlocks
import org.error1015.examplemod.items.ModItems
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import kotlin.time.Duration.Companion.seconds as secondsDuration

const val MODID = "examplemod"

@Mod(MODID)
object ExampleMod {
    init {
        val logger: Logger = LogManager.getLogger("$MODID-Main")
        initResource()

        val testObject = MySerializedThing(name = "ExampleMod", version = "1.0.0")
        val json = Json.encodeToString(testObject)

        logger.log(Level.INFO, "--- JSON: $json")

        // 这是协程代码示例
        CoroutineScope(Dispatchers.Default).launch {
            logger.log(Level.INFO, "Before delay")
            delay(5.secondsDuration)
            logger.log(Level.INFO, "After 5 seconds")
        }
    }

    fun initResource() {
        ModItems.ITEMS.register(MOD_BUS)
        ModBlocks.BLOCKS.register(MOD_BUS)
    }
}

@Serializable
data class MySerializedThing(val name: String, val version: String)