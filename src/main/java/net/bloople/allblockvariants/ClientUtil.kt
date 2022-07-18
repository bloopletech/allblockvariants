package net.bloople.allblockvariants

import net.devtech.arrp.util.CountingInputStream
import net.devtech.arrp.util.UnsafeByteArrayOutputStream
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.resource.DefaultClientResourcePack
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import java.awt.Rectangle
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_ARGB
import java.awt.image.Raster
import java.io.InputStream
import javax.imageio.ImageIO
import kotlin.math.PI

@Environment(value= EnvType.CLIENT)
class ClientUtil {
    companion object {
        private val vanillaResourcePack = DefaultClientResourcePack(null, EmptyResourceIndex())
        fun getVanillaClientResource(identifier: Identifier): InputStream {
            return vanillaResourcePack.open(ResourceType.CLIENT_RESOURCES, identifier)
        }

//        fun getVanillaServerData(identifier: Identifier): InputStream {
//            vanillaResourcePack.open(ResourceType.SERVER_DATA, identifier)
//        }

        fun createDerivedTexture(source: InputStream, block: (BufferedImage) -> BufferedImage): ByteArray {
            try {
                // optimize buffer allocation, input and output image after recoloring should be roughly the same size
                CountingInputStream(source).use {
                    val output = block(ImageIO.read(it).asARGB())
                    return UnsafeByteArrayOutputStream(it.bytes())
                        .also { ImageIO.write(output, "png", it) }.bytes
                }
            }
            catch(e: Throwable) {
                e.printStackTrace()
                throw RuntimeException(e)
            }
        }

        fun createDerivedTexture(
            source1: InputStream,
            source2: InputStream,
            block: (BufferedImage, BufferedImage) -> BufferedImage): ByteArray {
            try {
                // optimize buffer allocation, input and output image after recoloring should be roughly the same size
                CountingInputStream(source1).use { inputStream1 ->
                    source2.use { inputStream2 ->
                        val output = block(
                            ImageIO.read(inputStream1).asARGB(),
                            ImageIO.read(inputStream2).asARGB())
                        return UnsafeByteArrayOutputStream(inputStream1.bytes())
                            .also { ImageIO.write(output, "png", it) }.bytes
                    }
                }
            }
            catch(e: Throwable) {
                e.printStackTrace()
                throw RuntimeException(e)
            }
        }

        fun createVanillaDerivedTexture(identifier: Identifier, block: (BufferedImage) -> BufferedImage): ByteArray {
            getVanillaClientResource(identifier).use { return createDerivedTexture(it, block) }
        }

        fun createVanillaDerivedTexture(identifier: String, block: (BufferedImage) -> BufferedImage): ByteArray {
            getVanillaClientResource(Identifier(identifier)).use { return createDerivedTexture(it, block) }
        }
    }
}

@Environment(value= EnvType.CLIENT)
fun Raster.createChild(parentX: Int, parentY: Int, width: Int, height: Int): Raster {
    return this.createChild(parentX, parentY, width, height, parentX, parentY, null)
}

@Environment(value= EnvType.CLIENT)
fun BufferedImage.asARGB(): BufferedImage {
    if(type == TYPE_INT_ARGB) return this
    return BufferedImage(width, height, TYPE_INT_ARGB).apply {
        val graphics = graphics
        graphics.drawImage(this@asARGB, 0, 0, null)
        graphics.dispose()
    }
}

@Environment(value= EnvType.CLIENT)
fun BufferedImage.blankClone(): BufferedImage {
    return BufferedImage(width, height, type)
}

@Environment(value= EnvType.CLIENT)
fun BufferedImage.getData(x: Int, y: Int, width: Int, height: Int): Raster {
    return this.getData(Rectangle(x, y, width, height)).createTranslatedChild(0, 0)
}

// Based on https://stackoverflow.com/a/46211880
@Environment(value= EnvType.CLIENT)
fun BufferedImage.scaleImage(w2: Int, h2: Int): BufferedImage {
    // Create a new image of the proper size
    val scalex = w2 / width.toDouble()
    val scaley = h2 / height.toDouble()
    val after = BufferedImage(w2, h2, type)
    val scaleInstance = AffineTransform.getScaleInstance(scalex, scaley)
    val scaleOp = AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BICUBIC)
    scaleOp.filter(this, after)
    return after
}

// Based on https://stackoverflow.com/a/47994302
@Environment(value= EnvType.CLIENT)
fun BufferedImage.rotateImage(degrees: Double): BufferedImage {
    val affineTransform = AffineTransform.getRotateInstance(
        degrees * (PI / 180.0),
        width / 2.0,
        height / 2.0)
    val affineTransformOp = AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC)
    return affineTransformOp.filter(this, null)
}

@Environment(value= EnvType.CLIENT)
private fun createFlipTransform(mode: ImageFlipMode, imageWidth: Double, imageHeight: Double): AffineTransform {
    return when(mode) {
        ImageFlipMode.NORMAL -> AffineTransform()
        ImageFlipMode.TOP_BOTTOM -> {
            AffineTransform(doubleArrayOf(1.0, 0.0, 0.0, -1.0)).apply { translate(0.0, -imageHeight) }
        }
        ImageFlipMode.LEFT_RIGHT -> {
            AffineTransform(doubleArrayOf(-1.0, 0.0, 0.0, 1.0)).apply { translate(-imageWidth, 0.0) }
        }
        ImageFlipMode.TOP_BOTTOM_LEFT_RIGHT -> {
            AffineTransform(doubleArrayOf(-1.0, 0.0, 0.0, -1.0)).apply { translate(-imageWidth, -imageHeight) }
        }
    }
}

@Environment(value= EnvType.CLIENT)
enum class ImageFlipMode {
    NORMAL,
    TOP_BOTTOM,
    LEFT_RIGHT,
    TOP_BOTTOM_LEFT_RIGHT
}

// Based on https://www.informit.com/articles/article.aspx?p=23667&seqNum=11
@Environment(value= EnvType.CLIENT)
fun BufferedImage.flipImage(mode: ImageFlipMode): BufferedImage {
    val affineTransform =
        createFlipTransform(mode, width.toDouble(), height.toDouble())
    val affineTransformOp = AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC)
    return affineTransformOp.filter(this, null)
}