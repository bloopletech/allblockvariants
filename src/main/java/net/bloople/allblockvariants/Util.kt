package net.bloople.allblockvariants

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

// Based on https://stackoverflow.com/a/1086134
fun String.toTitleCase(): String {
    val titleCase = StringBuilder(this.length)
    var nextTitleCase = true

    for(c in this.toCharArray()) {
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

fun Block.copySettings() = AbstractBlock.Settings.copy(this).mapColor(this.defaultMapColor)!!

val Block.identifier: Identifier get() = Registries.BLOCK.getId(this)

val Identifier.blockResourceLocation: String get() = "$namespace:block/$path"
val Identifier.itemResourceLocation: String get() = "$namespace:item/$path"

fun modId(path: String): Identifier = Identifier.of(MOD_ID, path)
fun id(path: String): Identifier = Identifier.of(path)

fun AbstractBlock.Settings.noSpawning() = this.allowsSpawning { _, _, _, _ -> false }!!

fun getLogger(name: String): Logger {
    return LoggerFactory.getLogger("$MOD_ID/$name")
}

fun getLogger(clazz: KClass<*>): Logger {
    val fullName = if(clazz.isCompanion) clazz.java.declaringClass.name
    else clazz.qualifiedName

    val name = fullName!!.removePrefix("net.bloople.allblockvariants.")

    return getLogger(name)
}