package net.bloople.allblockvariants.blocks

import net.minecraft.block.Stainable
import net.minecraft.util.DyeColor


class StainedGlassThinSlabBlock(private val dyeColor: DyeColor, settings: Settings)
    : GlassThinSlabBlock(settings), Stainable {
    override fun getColor(): DyeColor {
        return dyeColor
    }
}