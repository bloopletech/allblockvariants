package net.bloople.allblockvariants

import net.minecraft.block.Block

class FenceAndFenceGateCreator(builder: ResourcePackBuilder) {
    private val fenceCreator = FenceCreator(builder)
    private val fenceGateCreator = FenceGateCreator(builder)

    fun create(existingBlock: Block) {
        fenceCreator.create(existingBlock)
        fenceGateCreator.create(existingBlock)
    }
}