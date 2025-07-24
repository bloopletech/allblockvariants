package net.bloople.allblockvariants

import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


class ModStickItem(settings: Settings) : Item(settings) {
    override fun hasGlint(stack: ItemStack): Boolean {
        return true
    }

    override fun getRecipeRemainder(): ItemStack {
        return ItemStack(this)
    }

    override fun canMine(
        stack: ItemStack,
        state: BlockState,
        world: World,
        pos: BlockPos,
        miner: LivingEntity): Boolean {
        return false
    }
}