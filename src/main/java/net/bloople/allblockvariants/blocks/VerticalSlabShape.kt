package net.bloople.allblockvariants.blocks

import net.minecraft.util.StringIdentifiable

enum class VerticalSlabShape(private val valueName: String) : StringIdentifiable {
    STRAIGHT("straight"),
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
}