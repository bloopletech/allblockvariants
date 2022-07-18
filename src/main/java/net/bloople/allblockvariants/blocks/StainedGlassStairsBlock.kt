package net.bloople.allblockvariants.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.Stainable
import net.minecraft.util.DyeColor


class StainedGlassStairsBlock(private val dyeColor: DyeColor, baseBlockState: BlockState, settings: Settings)
    : GlassStairsBlock(baseBlockState, settings), Stainable {
    override fun getColor(): DyeColor {
        return dyeColor
    }
}