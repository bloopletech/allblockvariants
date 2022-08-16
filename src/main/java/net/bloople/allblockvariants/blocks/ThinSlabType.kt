package net.bloople.allblockvariants.blocks

import net.minecraft.util.StringIdentifiable


enum class ThinSlabType(private val valueName: String) : StringIdentifiable {
    BOTTOM("bottom"),
    TOP("top");

    override fun toString(): String {
        return valueName
    }

    override fun asString(): String {
        return valueName
    }
}