package net.bloople.allblockvariants

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass


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

fun Block.copySettings() = AbstractBlock.Settings.copy(this).mapColor(this.defaultMapColor)!!

val Block.identifier: Identifier get() = Registry.BLOCK.getId(this)

val Identifier.blockResourceLocation: String get() = "$namespace:block/$path"
val Identifier.itemResourceLocation: String get() = "$namespace:item/$path"

fun AbstractBlock.Settings.noSpawning() = this.allowsSpawning { _, _, _, _ -> false }!!

fun AbstractBlock.Settings.mapColor(dyeColor: DyeColor) = this.mapColor(dyeColor.mapColor)!!

fun getLogger(name: String): Logger {
    return LoggerFactory.getLogger("$MOD_ID/$name")
}

fun getLogger(clazz: KClass<*>): Logger {
    val fullName = if(clazz.isCompanion) clazz.java.declaringClass.name
    else clazz.qualifiedName

    val name = fullName!!.removePrefix("net.bloople.allblockvariants.")

    return getLogger(name)
}