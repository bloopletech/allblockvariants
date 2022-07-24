package net.bloople.allblockvariants.blocks

import net.minecraft.util.StringIdentifiable

enum class VerticalSlabType(private val valueName: String) : StringIdentifiable {
    LEFT("left"), RIGHT("right"), DOUBLE("double");

    override fun toString(): String {
        return valueName
    }

    override fun asString(): String {
        return valueName
    }
}