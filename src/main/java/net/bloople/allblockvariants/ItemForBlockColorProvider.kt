package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.color.block.BlockColors
import net.minecraft.item.ItemStack

@Environment(value=EnvType.CLIENT)
fun interface ItemForBlockColorProvider {
    fun getColor(stack: ItemStack, tintIndex: Int, blockColors: BlockColors): Int
}