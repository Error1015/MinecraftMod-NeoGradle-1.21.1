package org.error1015.examplemod.data

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.ItemTags
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.data.ExistingFileHelper
import org.error1015.examplemod.MODID
import org.error1015.examplemod.items.ModItems
import java.util.concurrent.CompletableFuture

class ModItemTagsProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    blockTags: CompletableFuture<TagLookup<Block>>,
    existingFileHelper: ExistingFileHelper
) : ItemTagsProvider(output, lookupProvider, blockTags, MODID, existingFileHelper) {

    override fun addTags(provider: HolderLookup.Provider) {
        tag(ItemTags.AXES).add(
            ModItems.ExampleSuperTool
        )

        tag(ItemTags.PICKAXES).add(
            ModItems.ExampleSuperTool
        )
    }
}