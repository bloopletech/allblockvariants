package net.bloople.allblockvariants.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.piston.PistonBehavior

@Suppress("OVERRIDE_DEPRECATION")
open class GlazedTerracottaThinVerticalSlabBlock(settings: Settings) : HorizontalFacingThinVerticalSlabBlock(settings) {
    override fun getPistonBehavior(state: BlockState): PistonBehavior {
        return PistonBehavior.PUSH_ONLY
    }
}