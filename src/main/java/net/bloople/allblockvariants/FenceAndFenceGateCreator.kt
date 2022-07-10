package net.bloople.allblockvariants

class FenceAndFenceGateCreator(builder: ResourcePackBuilder) : BlockCreator(builder) {
    private val fenceCreator = FenceCreator(builder)
    private val fenceGateCreator = FenceGateCreator(builder)

    override fun create(blockInfo: BlockInfo) {
        fenceCreator.create(blockInfo)
        fenceGateCreator.create(blockInfo)
    }
}