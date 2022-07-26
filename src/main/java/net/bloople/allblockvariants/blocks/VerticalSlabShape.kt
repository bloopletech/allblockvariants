package net.bloople.allblockvariants.blocks

import net.minecraft.util.StringIdentifiable

enum class VerticalSlabShape(private val valueName: String) : StringIdentifiable {
    STRAIGHT("straight"), CORNER("corner");

    override fun toString(): String {
        return valueName
    }

    override fun asString(): String {
        return valueName
    }
}