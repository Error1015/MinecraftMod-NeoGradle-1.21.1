package org.error1015.examplemod.registry

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class Spell(
    val name: String,
    var minCost: Int,
    var maxCost: Int,
) {
    companion object {
        val CODEC = RecordCodecBuilder.create { instance ->
            instance
                .group(
                    Codec.STRING
                        .fieldOf("name")
                        .forGetter(Spell::name),
                    Codec.INT
                        .optionalFieldOf("minCost", 0)
                        .forGetter(Spell::minCost),
                    Codec.INT
                        .optionalFieldOf("maxCost", 0)
                        .forGetter(Spell::maxCost),
                )
                .apply(instance, ::Spell)
        }
    }
}