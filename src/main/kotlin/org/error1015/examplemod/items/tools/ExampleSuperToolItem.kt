package org.error1015.examplemod.items.tools

import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.SwordItem
import net.minecraft.world.item.TieredItem
import net.minecraft.world.item.component.Tool
import org.error1015.examplemod.items.tiers.ExampleTier

object ExampleSuperToolItem : TieredItem(
    ExampleTier,
    Properties()
        .stacksTo(1)
        .rarity(Rarity.COMMON)
        .durability(32767)
        .component(DataComponents.TOOL, Tool(listOf<Tool.Rule>(), 1f, 1))
        .attributes(SwordItem.createAttributes(ExampleTier, ExampleTier.attackDamageBonus, -2f))
) {
    // 看不懂怎么重写喵
    // override fun canPerformAction(
    //     stack: ItemStack, itemAbility: ItemAbility
    // ): Boolean {
    //     return super.canPerformAction(stack, itemAbility)
    // }
}