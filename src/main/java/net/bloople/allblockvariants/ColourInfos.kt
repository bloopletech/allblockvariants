package net.bloople.allblockvariants

import net.minecraft.block.MapColor

object ColourInfos {
    val COLOUR_INFOS = arrayOf(
        ColourInfo(MapColor.WHITE, "white"),
        ColourInfo(MapColor.ORANGE, "orange"),
        ColourInfo(MapColor.MAGENTA, "magenta"),
        ColourInfo(MapColor.LIGHT_BLUE, "light_blue"),
        ColourInfo(MapColor.YELLOW, "yellow"),
        ColourInfo(MapColor.LIME, "lime"),
        ColourInfo(MapColor.PINK, "pink"),
        ColourInfo(MapColor.GRAY, "gray"),
        ColourInfo(MapColor.LIGHT_GRAY, "light_gray"),
        ColourInfo(MapColor.CYAN, "cyan"),
        ColourInfo(MapColor.PURPLE, "purple"),
        ColourInfo(MapColor.BLUE, "blue"),
        ColourInfo(MapColor.BROWN, "brown"),
        ColourInfo(MapColor.GREEN, "green"),
        ColourInfo(MapColor.RED, "red"),
        ColourInfo(MapColor.BLACK, "black")
    )

    fun each(block: (colourInfo: ColourInfo) -> Unit) {
        COLOUR_INFOS.forEach(block)
    }
}