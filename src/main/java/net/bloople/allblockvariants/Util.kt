package net.bloople.allblockvariants

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.util.Identifier
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage

class Util {
    companion object {
        // Based on https://stackoverflow.com/a/1086134
        @JvmStatic
        fun toTitleCase(input: String): String {
            val titleCase = StringBuilder(input.length)
            var nextTitleCase = true

            for(c in input.toCharArray()) {
                var d = c
                if(d == '_') {
                    d = ' '
                    nextTitleCase = true
                }
                else if(nextTitleCase) {
                    d = d.titlecaseChar()
                    nextTitleCase = false
                }
                titleCase.append(d)
            }

            return titleCase.toString()
        }
    }
}

fun Block.copySettings(): AbstractBlock.Settings {
    return AbstractBlock.Settings.copy(this).mapColor(this.defaultMapColor)
}

val Identifier.blockResourceLocation: String get() = "$namespace:block/$path"
val Identifier.itemResourceLocation: String get() = "$namespace:item/$path"