package org.error1015.examplemod.utils

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.AABB
import thedarkcolour.kotlinforforge.neoforge.forge.vectorutil.v3d.toVec3

fun Entity.spawn() {
    level().addFreshEntity(this)
}

fun Entity.spawnOnPos(blockPos: BlockPos) {
    setPos(blockPos.toVec3())
    spawn()
}

fun Entity.spawnOnPos(
    x: Int,
    y: Int,
    z: Int
) {
    setPos(x, y, z)
    spawn()
}

fun Entity.setPos(
    x: Int,
    y: Int,
    z: Int
) {
    setPos(x.toDouble(), y.toDouble(), z.toDouble())
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