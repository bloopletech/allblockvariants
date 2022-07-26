package net.bloople.allblockvariants.blocks

import net.minecraft.util.StringIdentifiable

enum class VerticalSlabType(private val valueName: String) : StringIdentifiable {
    LEFT("left"),
    RIGHT("right"),
    NORTH_WEST("north_west"),
    NORTH_EAST("north_east"),
    SOUTH_EAST("south_east"),
    SOUTH_WEST("south_west");

    override fun toString(): String {
        return valueName
    }

    override fun asString(): String {
        return valueName
    }

    fun isStraight() {
        this == LEFT || this == RIGHT
    }
}