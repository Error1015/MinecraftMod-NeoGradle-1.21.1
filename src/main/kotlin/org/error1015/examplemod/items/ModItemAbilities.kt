package org.error1015.examplemod.items

import net.neoforged.neoforge.common.ItemAbilities
import net.neoforged.neoforge.common.ItemAbility

val superTool: ItemAbility = ItemAbility.get("super_tool")

fun initItemAbilities() {
    ItemAbilities.DEFAULT_AXE_ACTIONS.add(superTool)
    ItemAbilities.DEFAULT_PICKAXE_ACTIONS.add(superTool)
    ItemAbilities.DEFAULT_SHOVEL_ACTIONS.add(superTool)
    ItemAbilities.DEFAULT_SWORD_ACTIONS.add(superTool)
    ItemAbilities.DEFAULT_SHEARS_ACTIONS.add(superTool)
}