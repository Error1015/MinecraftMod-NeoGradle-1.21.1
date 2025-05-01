package org.error1015.examplemod.data

import net.minecraft.core.RegistrySetBuilder
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceKey
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.data.event.GatherDataEvent
import org.error1015.examplemod.MODID
import org.error1015.examplemod.registry.ModRegistries
import org.error1015.examplemod.registry.Spell
import org.error1015.examplemod.utils.*

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
object ModData {
    @SubscribeEvent
    fun genData(event: GatherDataEvent) {
        val generator = event.generator ?: return
        val output = generator.packOutput ?: return
        val existingFileHelper = event.existingFileHelper ?: return
        val lookUpProvider = event.lookupProvider ?: return

        val serversDataProvider = arrayOf<DataProvider>(
            ModRecipesProvider(output, lookUpProvider)
        )
        val clientsDataProvider = arrayOf<DataProvider>(
            ZhCnLanguageDataGen(output), EnUsLanguageProvider(output)
        )
        val devsDataProvider = arrayOf<DataProvider>()
        val reportersDataProvider = arrayOf<DataProvider>()

        event.apply {
            addAllServer(serversDataProvider)
            addAllClient(clientsDataProvider)
            addAllDev(devsDataProvider)
            addAllReports(reportersDataProvider)

            addServer {
                DatapackBuiltinEntriesProvider(
                    output, lookUpProvider, RegistrySetBuilder().add(ModRegistries.SpellRegistryKey) { bootstrap ->
                        bootstrap.register(
                            ModRegistries.ExampleSpell,
                            Spell("example_spell", 1, 5, "".asComponent)
                        )
                    }, setOf(MODID)
                )
            }
        }
    }
}