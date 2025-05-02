package org.error1015.examplemod.items.tools

import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.component.ItemAttributeModifiers
import org.error1015.examplemod.items.tiers.ExampleTier
import org.error1015.examplemod.utils.asPath

object ExampleHoeItem : HoeItem(
    ExampleTier, Properties()
        .stacksTo(1)
        .fireResistant()
        .attributes(
            ItemAttributeModifiers(
                listOf(
                    ItemAttributeModifiers.Entry(
                        Attributes.MAX_HEALTH, AttributeModifier("max_health".asPath, 20.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
                    )
                ), false
            )
        )
)