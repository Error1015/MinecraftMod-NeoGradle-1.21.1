package org.error1015.examplemod.block.render

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import org.error1015.examplemod.block.blockentity.MyBlockEntity

object MyBlockEntityRender: BlockEntityRenderer<MyBlockEntity> {
    /*
    此方法每帧调用一次，以便渲染 block 实体。参数包括：
    - blockEntity： 正在渲染的方块实体实例
    - partialTick：自上次时钟周期（0.0 到 1.0）以来经过的时间量，以时钟周期的分数（0.0 到 1.0）为单位。
    - poseStack：要渲染到的姿势堆栈。
    - bufferSource：要从中获取顶点缓冲区的缓冲区源。
    - packedLight： 方块实体的光照值。
    - packedOverlay：方块实体的当前覆盖层值，通常为 OverlayTexture.NO_OVERLAY。
     */
    override fun render(
        blockEntity: MyBlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) {
    }
}