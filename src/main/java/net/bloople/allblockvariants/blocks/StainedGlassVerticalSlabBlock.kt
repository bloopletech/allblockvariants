package net.bloople.allblockvariants.blocks

import net.minecraft.block.Stainable
import net.minecraft.util.DyeColor


class StainedGlassVerticalSlabBlock(private val dyeColor: DyeColor, settings: Settings)
    : GlassVerticalSlabBlock(settings), Stainable {
    override fun getColor(): DyeColor {
        return dyeColor
    }
}