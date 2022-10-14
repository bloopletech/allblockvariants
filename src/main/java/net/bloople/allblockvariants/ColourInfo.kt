package net.bloople.allblockvariants

import net.minecraft.block.MapColor
import net.minecraft.block.MapColor.Brightness
import java.awt.Color

data class ColourInfo(
    val colour: MapColor,
    val name: String
) {
    fun toColor(brightness: Brightness = Brightness.NORMAL): Color {
        val original = colour.getRenderColor(brightness)
        val alpha: Int = original shr 24 and 0xFF
        val red: Int = original shr 16 and 0xFF
        val green: Int = original shr 8 and 0xFF
        val blue: Int = original shr 0 and 0xFF
        val result = alpha shl 24 or (blue shl 16) or (green shl 8) or (red shl 0)
        return Color(result, true)
    }
}