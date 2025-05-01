package org.error1015.examplemod.utils

import net.minecraft.data.DataProvider
import net.neoforged.neoforge.data.event.GatherDataEvent

internal fun <T : DataProvider> GatherDataEvent.addServer(provider: T) {
    generator.addProvider(includeServer(), provider)
}

internal fun <T : DataProvider> GatherDataEvent.addServer(factory:  DataProvider.Factory<T>) {
    generator.addProvider(includeServer(), factory)
}

internal fun <T : DataProvider> GatherDataEvent.addClient(provider: T) {
    generator.addProvider(includeClient(), provider)
}

internal fun <T : DataProvider> GatherDataEvent.addClient(factory:  DataProvider.Factory<T>) {
    generator.addProvider(includeClient(), factory)
}

internal fun <T : DataProvider> GatherDataEvent.addDev(provider: T) {
    generator.addProvider(includeDev(), provider)
}

internal fun <T : DataProvider> GatherDataEvent.addDev(factory:  DataProvider.Factory<T>) {
    generator.addProvider(includeDev(), factory)
}

internal fun <T : DataProvider> GatherDataEvent.addReports(provider: T) {
    generator.addProvider(includeReports(), provider)
}

internal fun <T : DataProvider> GatherDataEvent.addReports(factory:  DataProvider.Factory<T>) {
    generator.addProvider(includeReports(), factory)
}


/**
 * 添加至服务端。
 * 给一个DataProvider数组，遍历添加DataGen
 */
internal fun <T : DataProvider> GatherDataEvent.addAllServer(providers: Array<T>) {
    if (providers.isNotEmpty()) {
        providers.forEach { generator.addProvider(includeServer(), it) }
    }
}

internal fun <T : DataProvider> GatherDataEvent.addAllClient(providers: Array<T>) {
    if (providers.isNotEmpty()) {
        providers.forEach { generator.addProvider(includeClient(), it) }
    }
}

internal fun <T : DataProvider> GatherDataEvent.addAllDev(providers: Array<T>) {
    if (providers.isNotEmpty()) {
        providers.forEach { generator.addProvider(includeDev(), it) }
    }
}

internal fun <T : DataProvider> GatherDataEvent.addAllReports(providers: Array<T>) {
    if (providers.isNotEmpty()) {
        providers.forEach { generator.addProvider(includeReports(), it) }
    }
}