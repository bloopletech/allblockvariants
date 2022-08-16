package net.bloople.allblockvariants.blocks

import net.minecraft.util.StringIdentifiable


enum class VerticalSlabType(private val valueName: String) : StringIdentifiable {
    LEFT("left"),
    RIGHT("right");

    override fun toString(): String {
        return valueName
    }

    override fun asString(): String {
        return valueName
    }

    fun isStraight(): Boolean {
        return this == LEFT || this == RIGHT
    }

    fun isCorner(): Boolean {
        return !isStraight()
    }
}