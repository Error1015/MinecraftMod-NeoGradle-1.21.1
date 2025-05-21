package org.error1015.examplemod.utils

import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.AABB

fun Entity.spawn() {
    level().addFreshEntity(this)
}

inline fun <reified T> Entity.getNearbyEntities(radius: Double): List<T> where T : Entity {
    val pos = blockPosition() ?: return emptyList()
    val aabb = AABB(
        pos.x - radius, pos.y - radius, pos.z - radius, pos.x + radius, pos.y + radius, pos.z + radius
    )
    return level()
        .getEntitiesOfClass(T::class.java, aabb)
        .asSequence()
        .filter { it != this && it != null }
        .toList()
}