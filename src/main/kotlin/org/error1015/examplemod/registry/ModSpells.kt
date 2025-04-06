package org.error1015.examplemod.registry

import net.minecraft.network.chat.Component
import net.neoforged.neoforge.registries.DeferredRegister
import org.error1015.examplemod.MODID
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModSpells {
    val spells: DeferredRegister<Spell> = DeferredRegister.create(ModRegistries.SpellRegistry, MODID)

    val test: Spell by spells.register("test") { -> Spell("test", 20, 100, Component.translatable("desc")) }
}