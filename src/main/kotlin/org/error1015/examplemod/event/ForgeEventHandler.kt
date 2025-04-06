package org.error1015.examplemod.event

import net.minecraft.client.Minecraft
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LightningBolt
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.level.Level
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent
import net.neoforged.neoforge.event.tick.EntityTickEvent
import org.error1015.examplemod.MODID
import org.error1015.examplemod.event.KeyMappingRegister.exampleKeyMapping
import org.error1015.examplemod.utils.*
import thedarkcolour.kotlinforforge.neoforge.forge.vectorutil.v3d.toVec3

@EventBusSubscriber(modid = MODID)
object ForgeEventHandler {
    /**
     * 添加酿造台配方
     */
    @SubscribeEvent
    fun registerBrewingRecipes(event: RegisterBrewingRecipesEvent) {
        val builder = event.builder ?: return
        builder.addMix(Potions.NIGHT_VISION, Items.DIAMOND, Potions.LUCK)
    }


    /**
     * 检测客户端按键点击
     * 检测到按键点击时清除掉落物并显示提示信息
     */
    @SubscribeEvent
    fun onClientTick(event: ClientTickEvent.Post) {
        while (exampleKeyMapping.consumeClick()) {
            Minecraft.getInstance().player?.let { player: LocalPlayer ->
                val itemEntities = player.getNearbyEntities<ItemEntity>(128.0)
                // PacketDistributor.sendToServer(ClearItemsPacket(itemEntities))
                // TODO 存在Bug 需要发包
                player.displayClientMessage(Component.literal("掉落物已清理").withColor(16755200), true)
            }
        }
    }

    /**
     * 右键钻石闪电攻击附近32x32x32的区块实体 并设置天气为雷雨
     */
    @SubscribeEvent
    fun onItemClick(event: PlayerInteractEvent.RightClickItem) {
        if (event.level.isClientSide) return
        val itemStack = event.itemStack ?: return
        if (itemStack.item == Items.DIAMOND) {
            val entities = event.entity.getNearbyEntities<LivingEntity>(32.0)
            entities.forEach { entity ->
                entity.hurt(event.level.damageSources().playerAttack(event.entity), entity.health)
                val lightning = LightningBolt(EntityType.LIGHTNING_BOLT, event.level)
                lightning.setPos(entity.blockPosition().toVec3())
                lightning.spawn()
            }
            (event.level as? ServerLevel)?.setWeatherParameters(0, 20.min, true, true) ?: return
            event.entity.cooldowns.addCooldown(itemStack.item, 5.s)
            event.itemStack.count--
        }
    }

    /**
     * 每tick检测一次是否有掉落物在水中 如果是钻石则爆炸 其他的则删除
     */
    @SubscribeEvent
    fun itemEntityTick(event: EntityTickEvent.Post) {
        if (event.entity.level.isClientSide) return

        (event.entity as? ItemEntity)?.let { itemEntity ->
            val itemCount = itemEntity.item.count
            if (itemEntity.isInWater) {
                if (itemEntity.item.item == Items.DIAMOND) {
                    itemEntity.level().explode(
                        itemEntity, itemEntity.x, itemEntity.y, itemEntity.z, itemCount.toFloat(), false, Level.ExplosionInteraction.NONE
                    ).explode()
                }
                itemEntity.remove(Entity.RemovalReason.KILLED)
            }
        }
    }
}