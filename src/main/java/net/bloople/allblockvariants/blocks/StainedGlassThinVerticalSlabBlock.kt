package net.bloople.allblockvariants.blocks

import net.minecraft.block.Stainable
import net.minecraft.util.DyeColor


class StainedGlassThinVerticalSlabBlock(private val dyeColor: DyeColor, settings: Settings)
    : GlassThinVerticalSlabBlock(settings), Stainable {
    override fun getColor(): DyeColor {
        return dyeColor
    }
}