package org.error1015.examplemod.data

import net.minecraft.core.RegistrySetBuilder
import net.minecraft.data.DataProvider
import net.minecraft.data.tags.TagsProvider
import net.minecraft.world.level.block.Block
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.data.event.GatherDataEvent
import org.error1015.examplemod.MODID
import org.error1015.examplemod.registry.ModRegistries
import org.error1015.examplemod.registry.Spell
import org.error1015.examplemod.utils.*
import java.util.concurrent.CompletableFuture

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
object ModData {
    @SubscribeEvent
    fun genData(event: GatherDataEvent) {
        val generator = event.generator ?: return
        val output = generator.packOutput ?: return
        val existingFileHelper = event.existingFileHelper ?: return
        val lookUpProvider = event.lookupProvider ?: return

        event.generator.apply {
            addProvider(event.includeServer(), ModRecipesProvider(output, lookUpProvider))
            addProvider(
                event.includeServer(), ModItemTagsProvider(
                    output, lookUpProvider, CompletableFuture.completedFuture(TagsProvider.TagLookup.empty()), existingFileHelper
                )
            )

            addProvider(event.includeClient(), ZhCnLanguageProvider(output))
            addProvider(event.includeClient(), EnUsLanguageProvider(output))
        }
    }
}