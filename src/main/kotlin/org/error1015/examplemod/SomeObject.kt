package org.error1015.examplemod

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class SomeObject(var s: String, var i: Int, var b: Boolean) {
    companion object {
        val CODEC: Codec<SomeObject> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("s").forGetter(SomeObject::s),
                Codec.INT.optionalFieldOf("i", 0).forGetter(SomeObject::i),
                Codec.BOOL.fieldOf("b").forGetter(SomeObject::b)
            ).apply(instance, ::SomeObject)
        }
    }
}