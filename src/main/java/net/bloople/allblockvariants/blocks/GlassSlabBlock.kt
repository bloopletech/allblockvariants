package net.bloople.allblockvariants.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.SlabBlock
import net.minecraft.block.enums.SlabType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView


class GlassSlabBlock(settings: Settings) : SlabBlock(settings) {
    @Deprecated("Deprecated in Java")
    override fun hasSidedTransparency(state: BlockState): Boolean {
        return state.get(TYPE) != SlabType.DOUBLE
    }

    @Deprecated("Deprecated in Java")
    override fun getCameraCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape? {
        return VoxelShapes.empty()
    }

    @Deprecated("Deprecated in Java")
    override fun getAmbientOcclusionLightLevel(state: BlockState, world: BlockView, pos: BlockPos): Float {
        return 1.0f
    }

    override fun isTranslucent(state: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return true
    }
}