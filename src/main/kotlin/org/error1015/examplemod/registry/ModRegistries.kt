package org.error1015.examplemod.registry

import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.neoforged.neoforge.registries.RegistryBuilder
import org.error1015.examplemod.MODID
import org.error1015.examplemod.utils.asResourceLocationPath


object ModRegistries {
    val SpellRegistryKey: ResourceKey<Registry<Spell>> = ResourceKey.createRegistryKey(
        "spells".asResourceLocationPath
    )

    val SpellRegistry: Registry<Spell> = RegistryBuilder(SpellRegistryKey)
        .defaultKey("empty".asResourceLocationPath)
        .sync(true)
        .maxId(256)
        .create()

    val EXAMPLE_CONFIGURED_FEATURE: ResourceKey<ConfiguredFeature<*, *>?> = ResourceKey.create(
        Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MODID, "example_configured_feature")
    )
}