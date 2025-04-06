package org.error1015.examplemod.utils

import net.minecraft.resources.ResourceLocation
import org.error1015.examplemod.MODID

internal fun String.asResourceLocationPath(): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MODID, this)