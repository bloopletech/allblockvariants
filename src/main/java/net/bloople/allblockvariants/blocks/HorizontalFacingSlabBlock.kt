package net.bloople.allblockvariants.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.SlabBlock
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation

@Suppress("OVERRIDE_DEPRECATION")
open class HorizontalFacingSlabBlock(settings: Settings) : SlabBlock(settings) {
    companion object {
        val FACING: DirectionProperty = Properties.HORIZONTAL_FACING
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING))) as BlockState
    }

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState {
        return state.rotate(mirror.getRotation(state.get(FACING)))
    }
}

