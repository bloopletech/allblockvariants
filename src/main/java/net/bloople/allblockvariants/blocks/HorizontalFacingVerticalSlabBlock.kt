package net.bloople.allblockvariants.blocks

import net.minecraft.block.BlockState
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation

@Suppress("OVERRIDE_DEPRECATION")
open class HorizontalFacingVerticalSlabBlock(settings: Settings) : VerticalSlabBlock(settings) {
    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING))) as BlockState
    }

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState {
        return state.rotate(mirror.getRotation(state.get(FACING)))
    }
}

