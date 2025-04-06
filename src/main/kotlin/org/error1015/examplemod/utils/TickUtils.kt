package org.error1015.examplemod.utils

/**
 * tick中的时分秒
 */
internal val Int.s
    get() = this * 20
internal val Int.min
    get() = this * s * 60
internal val Int.h
    get() = this * min * 60