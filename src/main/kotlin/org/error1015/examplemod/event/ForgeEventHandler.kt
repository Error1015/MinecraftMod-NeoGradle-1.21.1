package org.error1015.examplemod.event

import net.minecraft.client.Minecraft
import net.minecraft.client.player.LocalPlayer
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LightningBolt
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.monster.Slime
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.level.Level
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent
import net.neoforged.neoforge.event.entity.living.MobSplitEvent
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent
import net.neoforged.neoforge.event.tick.EntityTickEvent
import net.neoforged.neoforge.network.PacketDistributor
import org.error1015.examplemod.MODID
import org.error1015.examplemod.event.KeyMappingRegister.exampleKeyMapping
import org.error1015.examplemod.network.packets.PlayerAbilityPacket
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
     * 检测到按键点击时切换玩家创造飞行模式
     * 发送玩家能力包到服务器
     */
    @SubscribeEvent
    fun onClientTick(event: ClientTickEvent.Post) {
        while (exampleKeyMapping.consumeClick()) {
            Minecraft.getInstance().player?.let { player: LocalPlayer ->
                PacketDistributor.sendToServer(PlayerAbilityPacket(player.uuid))
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

            if (Math.random() < 0.1) {
                (event.level as? ServerLevel)?.setWeatherParameters(0, 20.min, true, true) ?: return
            }

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

    /**
     * 禁止史莱姆分裂
     */
    @SubscribeEvent
    fun onMobSplit(event: MobSplitEvent) {
        if (event.parent.level.isClientSide) {
            return
        }
        if (event.parent is Slime) {
            event.isCanceled = true
        }
    }
}