package org.error1015.examplemod.items.tools

import net.minecraft.core.component.DataComponents
import net.minecraft.tags.BlockTags
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.*
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.item.component.Tool
import net.neoforged.neoforge.common.ItemAbilities
import net.neoforged.neoforge.common.ItemAbility
import org.error1015.examplemod.utils.asResourceLocationPath
import org.error1015.examplemod.utils.plus

class ExampleSuperToolItem(tier: Tier) : Item(
    Properties().stacksTo(1).rarity(Rarity.COMMON).durability(32767).component(
        DataComponents.TOOL, createToolProperties(tier)
    ).attributes(createAttributes(tier))
) {
    companion object {
        fun createAttributes(tier: Tier): ItemAttributeModifiers = ItemAttributeModifiers.builder().add(
            Attributes.ATTACK_DAMAGE, AttributeModifier(
                "attack_damage".asResourceLocationPath, tier.attackDamageBonus.toDouble(), AttributeModifier.Operation.ADD_VALUE
            ), EquipmentSlotGroup.MAINHAND
        ).add(
            Attributes.ATTACK_SPEED, AttributeModifier(
                "attack_speed".asResourceLocationPath, tier.speed.toDouble(), AttributeModifier.Operation.ADD_VALUE
            ), EquipmentSlotGroup.MAINHAND
        ).build()

        fun createToolProperties(tier: Tier) = SwordItem.createToolProperties() + ShearsItem.createToolProperties() + Tool(
            listOf<Tool.Rule>(
                Tool.Rule.minesAndDrops(
                    BlockTags.NEEDS_DIAMOND_TOOL, tier.speed
                )
            ), tier.attackDamageBonus, 1
        )
    }

    override fun canPerformAction(
        stack: ItemStack, itemAbility: ItemAbility
    ): Boolean {
        return itemAbility in ItemAbilities.DEFAULT_SWORD_ACTIONS || itemAbility in ItemAbilities.DEFAULT_SHEARS_ACTIONS || itemAbility in ItemAbilities.DEFAULT_PICKAXE_ACTIONS || itemAbility in ItemAbilities.DEFAULT_AXE_ACTIONS
    }
}