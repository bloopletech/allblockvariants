package net.bloople.allblockvariants.blocks

import net.minecraft.block.Stainable
import net.minecraft.util.DyeColor


class StainedGlassSlabBlock(private val dyeColor: DyeColor, settings: Settings)
    : GlassSlabBlock(settings), Stainable {
    override fun getColor(): DyeColor {
        return dyeColor
    }
}