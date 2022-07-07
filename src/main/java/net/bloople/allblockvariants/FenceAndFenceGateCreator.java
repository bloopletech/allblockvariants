package net.bloople.allblockvariants;

import net.minecraft.block.Block;

public class FenceAndFenceGateCreator {
    private final FenceCreator fenceCreator;
    private final FenceGateCreator fenceGateCreator;

    public FenceAndFenceGateCreator(ResourcePackBuilder builder) {
        this.fenceCreator = new FenceCreator(builder);
        this.fenceGateCreator = new FenceGateCreator(builder);
    }

    public void create(Block existingBlock) {
        fenceCreator.create(existingBlock);
        fenceGateCreator.create(existingBlock);
    }
}
