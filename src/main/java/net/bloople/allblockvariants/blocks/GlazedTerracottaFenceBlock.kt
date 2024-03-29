package net.bloople.allblockvariants.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager

@Suppress("OVERRIDE_DEPRECATION")
open class GlazedTerracottaFenceBlock(settings: Settings) : HorizontalFacingFenceBlock(settings) {
    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(FACING)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        return super.getPlacementState(ctx)!!.with(FACING, ctx.playerFacing.opposite) as BlockState
    }

    override fun getPistonBehavior(state: BlockState): PistonBehavior {
        return PistonBehavior.PUSH_ONLY
    }
}