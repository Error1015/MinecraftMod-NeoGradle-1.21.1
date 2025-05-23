package org.error1015.examplemod.items.armors

import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.registries.DeferredRegister
import org.error1015.examplemod.MODID
import org.error1015.examplemod.items.ModItems
import org.error1015.examplemod.utils.asResourceLocationPath

object ModArmorMaterials {
    val registrar: DeferredRegister<ArmorMaterial> = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, MODID)

    val layer: ArmorMaterial.Layer = ArmorMaterial.Layer("example".asResourceLocationPath)

    val exampleArmorMaterial: Holder<ArmorMaterial> = registrar.register("example_material") { ->
        ArmorMaterial(
            mapOf<ArmorItem.Type, Int>(
                ArmorItem.Type.BOOTS to 2, ArmorItem.Type.LEGGINGS to 5, ArmorItem.Type.CHESTPLATE to 7, ArmorItem.Type.HELMET to 3
            ), 20,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            { Ingredient.of(ModItems.ExampleItem) },
            listOf(layer),
            20f,
            20f
        )
    }
}