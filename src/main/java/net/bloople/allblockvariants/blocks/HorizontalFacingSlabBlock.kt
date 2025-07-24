package net.bloople.allblockvariants.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.SlabBlock
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.Direction

@Suppress("OVERRIDE_DEPRECATION")
open class HorizontalFacingSlabBlock(settings: Settings) : SlabBlock(settings) {
    companion object {
        val FACING: EnumProperty<Direction> = Properties.HORIZONTAL_FACING
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING))) as BlockState
    }

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState {
        return state.rotate(mirror.getRotation(state.get(FACING)))
    }
}

