package net.bloople.allblockvariants

import net.minecraft.client.resource.DefaultClientResourcePack
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage
import java.io.InputStream


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

        // Based on https://stackoverflow.com/a/46211880
        fun scaleImage(before: BufferedImage, w2: Int, h2: Int): BufferedImage {
            val w = before.width
            val h = before.height
            // Create a new image of the proper size
            val scalex = w2 / w.toDouble()
            val scaley = h2 / h.toDouble()
            val after = BufferedImage(w2, h2, BufferedImage.TYPE_INT_ARGB)
            val scaleInstance = AffineTransform.getScaleInstance(scalex, scaley)
            val scaleOp = AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BICUBIC)
            scaleOp.filter(before, after)
            return after
        }

        private val vanillaResourcePack = DefaultClientResourcePack(null, EmptyResourceIndex())
        fun getVanillaClientResource(identifier: Identifier): InputStream {
            return vanillaResourcePack.open(ResourceType.CLIENT_RESOURCES, identifier)
        }

//        fun getVanillaServerData(identifier: Identifier): InputStream {
//            vanillaResourcePack.open(ResourceType.SERVER_DATA, identifier)
//        }
    }
}


