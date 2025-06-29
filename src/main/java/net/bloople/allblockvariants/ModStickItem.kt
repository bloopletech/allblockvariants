package net.bloople.allblockvariants

import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


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

    override fun canMine(state: BlockState, world: World, pos: BlockPos, miner: PlayerEntity): Boolean {
        return false
    }
}