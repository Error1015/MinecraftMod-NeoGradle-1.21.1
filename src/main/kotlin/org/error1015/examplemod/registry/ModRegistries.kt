package org.error1015.examplemod.registry

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.neoforged.neoforge.registries.RegistryBuilder
import org.error1015.examplemod.utils.asPath

object ModRegistries {
    val SpellRegistryKey: ResourceKey<Registry<Spell>> = ResourceKey.createRegistryKey(
        "spells".asPath
    )

    val SpellRegistry: Registry<Spell> = RegistryBuilder(SpellRegistryKey)
        .defaultKey("empty".asPath)
        .sync(true)
        .maxId(256)
        .create()

    val ExampleSpell: ResourceKey<Spell> = ResourceKey.create(
        SpellRegistryKey, "example_spell".asPath
    )
}