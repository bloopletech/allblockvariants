package net.bloople.allblockvariants

import net.minecraft.item.Item
import net.minecraft.item.ItemStack


class ModStickItem(settings: Settings) : Item(settings) {
    override fun hasGlint(stack: ItemStack): Boolean {
        return true
    }

    override fun getRecipeRemainder(): Item {
        return this
    }

    override fun hasRecipeRemainder(): Boolean {
        return true
    }
}