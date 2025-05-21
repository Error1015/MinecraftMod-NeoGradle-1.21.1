package org.error1015.examplemod.client

import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.common.Mod
import org.error1015.examplemod.MODID

@Mod(value = MODID, dist = [Dist.CLIENT])
object ExampleModClient {
    init {
        println("客户端入口点")
    }
}