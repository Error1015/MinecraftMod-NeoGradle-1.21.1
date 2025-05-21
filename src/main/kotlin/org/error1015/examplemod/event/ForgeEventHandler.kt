package org.error1015.examplemod.event

import kotlinx.coroutines.*
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.ClickEvent
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.TextColor
import net.minecraft.server.level.ServerLevel
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
import net.neoforged.neoforge.client.event.ClientChatEvent
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent
import net.neoforged.neoforge.event.entity.living.MobSplitEvent
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent
import net.neoforged.neoforge.event.tick.EntityTickEvent
import net.neoforged.neoforge.network.PacketDistributor
import org.error1015.examplemod.MODID
import org.error1015.examplemod.network.packets.ExamplePackets
import org.error1015.examplemod.utils.*
import thedarkcolour.kotlinforforge.neoforge.forge.vectorutil.v3d.component1
import thedarkcolour.kotlinforforge.neoforge.forge.vectorutil.v3d.component2
import thedarkcolour.kotlinforforge.neoforge.forge.vectorutil.v3d.component3
import thedarkcolour.kotlinforforge.neoforge.forge.vectorutil.v3d.toVec3
import kotlin.time.Duration.Companion.seconds

@EventBusSubscriber(modid = MODID)
object ForgeEventHandler {
    val scope = CoroutineScope(
        SupervisorJob() + CoroutineExceptionHandler { _, exc ->
            println("发生异常: $exc")
            exc.printStackTrace()
        })

    val ciallo: MutableComponent = "Ciallo～(∠・ω< )⌒☆".asComponent.withStyle(
        Style.EMPTY.withColor(
            TextColor.fromLegacyFormat(
                ChatFormatting.GOLD
            )
        ) + Style.EMPTY.withClickEvent(
            ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.bilibili.com/video/BV1GJ411x7h7/?spm_id_from=333.337.search-card.all.click")
        ) + Style.EMPTY.withUnderlined(true) + Style.EMPTY.withClickEvent(ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "Ciallo～(∠・ω< )⌒☆"))
    )

    /**
     * 添加酿造台配方
     */
    @SubscribeEvent
    fun registerBrewingRecipes(event: RegisterBrewingRecipesEvent) {
        val builder = event.builder ?: return
        builder.addMix(Potions.NIGHT_VISION, Items.DIAMOND, Potions.LUCK)
    }

    @SubscribeEvent
    fun onClientChat(event: ClientChatEvent) {
        scope.launch {
            delay(1.seconds)
            PacketDistributor.sendToServer(ExamplePackets(event.message, event.message.length, event.isCanceled))
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
            entities.forEach { livingEntity ->
                livingEntity.hurt(
                    event.level
                        .damageSources()
                        .playerAttack(event.entity), livingEntity.health
                )
                LightningBolt(EntityType.LIGHTNING_BOLT, event.level).apply {
                    setPos(livingEntity.blockPosition().toVec3())
                    spawn()
                }
            }
            event.entity.sendSystemMessage(ciallo)

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
        if (event.entity.level().isClientSide) return
        event.entity.safeClassCastAndHandle<ItemEntity> { itemEntity ->
            val itemCount = itemEntity.item.count
            if (itemEntity.isInWater && itemEntity.item.item == Items.DIAMOND) {
                itemEntity.apply {
                    level()
                        .explode(this, x, y, z, itemCount.toFloat(), false, Level.ExplosionInteraction.NONE)
                        .explode()
                    kill()
                }
            }
        }
    }

    /**
     * 当食物物品被扔出来成为掉落物 将掉落物的位置提高5格
     */
    @SubscribeEvent
    fun onEntityJoinLevel(event: EntityJoinLevelEvent) {
        event.handleServer {
            val itemEntity = event.entity ?: return
            if (itemEntity is ItemEntity) {
                itemEntity.apply {
                    val (x, y, z) = blockPosition()
                    // 检测物品是否是食物
                    if (item.getFoodProperties(null) != null) {
                        setPos(x.toDouble(), (y + 5).toDouble(), z.toDouble())
                    }
                }
            }
        }
    }

    @SubscribeEvent
    fun onMobSplit(event: MobSplitEvent) {
        if (event.parent.level().isClientSide) return
        if (event.parent is Slime) {
            event.cancel()
        }
    }
}