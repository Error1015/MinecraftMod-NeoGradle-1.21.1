package org.error1015.examplemod.data

import net.minecraft.core.HolderLookup
import net.minecraft.data.tags.TagsProvider
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent
import org.error1015.examplemod.MODID
import java.util.concurrent.CompletableFuture

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
object ModData {
    @SubscribeEvent
    fun genData(event: GatherDataEvent) {
        val generator = event.generator ?: return
        val output = generator.packOutput ?: return
        val existingFileHelper = event.existingFileHelper ?: return
        val lookUpProvider: CompletableFuture<HolderLookup.Provider> = event.lookupProvider ?: return

        event.generator.apply {
            addProvider(event.includeServer(), ModRecipesProvider(output, lookUpProvider))
            addProvider(
                event.includeServer(), ModItemTagsProvider(
                    output, lookUpProvider, CompletableFuture.completedFuture(TagsProvider.TagLookup.empty()), existingFileHelper
                )
            )

            ModLanguageDataGen(output).apply {
                addProvider(event.includeClient(), zhCn)
                addProvider(event.includeClient(), enUs)
            }
        }
    }
}