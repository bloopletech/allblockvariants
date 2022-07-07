package net.bloople.allblockvariants

import net.minecraft.block.Block

class FenceAndFenceGateCreator(builder: ResourcePackBuilder) {
    private val fenceCreator = FenceCreator(builder)
    private val fenceGateCreator = FenceGateCreator(builder)

    fun create(existingBlock: Block, mineableBy: MiningTool = MiningTool.Pickaxe) {
        create(existingBlock, listOf(mineableBy))
    }

    fun create(existingBlock: Block, mineableBy: List<MiningTool>) {
        fenceCreator.create(existingBlock, mineableBy)
        fenceGateCreator.create(existingBlock, mineableBy)
    }
}