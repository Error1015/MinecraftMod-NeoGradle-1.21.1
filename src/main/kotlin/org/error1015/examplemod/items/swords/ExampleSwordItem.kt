package org.error1015.examplemod.items.swords

import net.minecraft.core.component.DataComponents
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.monster.Creeper
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SwordItem
import net.minecraft.world.item.component.Unbreakable
import org.error1015.examplemod.items.tiers.ExampleTier

object ExampleSwordItem : SwordItem(
    ExampleTier, Properties()
        .stacksTo(1)
        .durability(1000)
        .attributes(
            createAttributes(ExampleTier, ExampleTier.attackDamageBonus, -2.4f)
        )
        .component(DataComponents.UNBREAKABLE, Unbreakable(true))
) {
    override fun interactLivingEntity(
        stack: ItemStack,
        player: Player,
        interactionTarget: LivingEntity,
        usedHand: InteractionHand
    ): InteractionResult {
        if (player.level().isClientSide) return InteractionResult.PASS

        if (usedHand == InteractionHand.MAIN_HAND && stack.item is ExampleSwordItem && interactionTarget is Creeper) {
            interactionTarget.ignite()
        }

        return super.interactLivingEntity(stack, player, interactionTarget, usedHand)
    }
}