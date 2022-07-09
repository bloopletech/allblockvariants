package net.bloople.allblockvariants

import net.minecraft.block.Block

class FenceAndFenceGateCreator(builder: ResourcePackBuilder) {
    private val fenceCreator = FenceCreator(builder)
    private val fenceGateCreator = FenceGateCreator(builder)

    fun create(existingBlock: Block, mineableBy: MiningTool = MiningTool.Pickaxe, needsTool: MiningToolLevel? = null) {
        fenceCreator.create(existingBlock, mineableBy, needsTool)
        fenceGateCreator.create(existingBlock, mineableBy, needsTool)
    }
}