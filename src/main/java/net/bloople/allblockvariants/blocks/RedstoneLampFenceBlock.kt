package net.bloople.allblockvariants.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.FenceBlock
import net.minecraft.block.RedstoneTorchBlock
import net.minecraft.item.ItemPlacementContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.World

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class RedstoneLampFenceBlock(settings: Settings) : FenceBlock(settings) {
    companion object {
        val LIT: BooleanProperty = RedstoneTorchBlock.LIT
    }

    init {
        defaultState = defaultState.with(LIT, false)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        return (super.getPlacementState(ctx) ?: defaultState).with(LIT, ctx.world.isReceivingRedstonePower(ctx.blockPos))
    }

    override fun neighborUpdate(
        state: BlockState,
        world: World,
        pos: BlockPos,
        sourceBlock: Block,
        sourcePos: BlockPos,
        notify: Boolean
    ) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify)

        if(world.isClient) return

        val bl = state.get(LIT)
        if(bl != world.isReceivingRedstonePower(pos)) {
            if(bl) world.scheduleBlockTick(pos, this, 4)
            else world.setBlockState(pos, state.cycle(LIT), Block.NOTIFY_LISTENERS)
        }
    }

    override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        super.scheduledTick(state, world, pos, random)

        if(state[LIT] && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(LIT), Block.NOTIFY_LISTENERS)
        }
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(LIT)
    }
}