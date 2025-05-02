package org.error1015.examplemod.items.tools

import net.minecraft.core.component.DataComponents
import net.minecraft.tags.BlockTags
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.*
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.item.component.Tool
import net.minecraft.world.item.component.Unbreakable
import net.neoforged.neoforge.common.ItemAbilities
import net.neoforged.neoforge.common.ItemAbility
import org.error1015.examplemod.utils.asPath
import org.error1015.examplemod.utils.plus

class ExampleSuperToolItem(tier: Tier) : Item(
    Properties()
        .stacksTo(1)
        .rarity(Rarity.COMMON)
        .durability(32767)
        .component(DataComponents.TOOL, createToolProperties(tier))
        .component(DataComponents.UNBREAKABLE, Unbreakable(false))
        .attributes(createAttributes(tier))
) {
    companion object {
        fun createAttributes(tier: Tier) = ItemAttributeModifiers
            .builder()
            .add(
                Attributes.ATTACK_DAMAGE, AttributeModifier("attack_damage".asPath, tier.attackDamageBonus.toDouble(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
            )
            .add(
                Attributes.ATTACK_SPEED, AttributeModifier(
                    "attack_speed".asPath, tier.speed.toDouble(), AttributeModifier.Operation.ADD_VALUE
                ), EquipmentSlotGroup.MAINHAND
            )
            .build() ?: throw IllegalStateException("Failed to create attributes for ExampleSuperToolItem")

        fun createToolProperties(tier: Tier) = SwordItem.createToolProperties() + ShearsItem.createToolProperties() + Tool(
            listOf(
                Tool.Rule.minesAndDrops(
                    BlockTags.NEEDS_DIAMOND_TOOL, tier.speed
                )
            ), tier.attackDamageBonus, 1
        )
    }

    /**
     * 被诈骗了?
     */
    override fun canPerformAction(
        stack: ItemStack,
        itemAbility: ItemAbility
    ) = ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility)
}