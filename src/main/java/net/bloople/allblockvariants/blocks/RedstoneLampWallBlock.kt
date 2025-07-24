package net.bloople.allblockvariants.blocks

import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.block.WireOrientation


@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class RedstoneLampWallBlock(settings: Settings) : WallBlock(settings) {
    private val rlOutlineShapeFunction: java.util.function.Function<BlockState, VoxelShape>
    private val rlCollisionShapeFunction: java.util.function.Function<BlockState, VoxelShape>
    companion object {
        val LIT: BooleanProperty = RedstoneTorchBlock.LIT
    }

    init {
        defaultState = defaultState.with(LIT, false)

        rlOutlineShapeFunction = createShapeFunction(outlineShapeFunction, LIT)
        rlCollisionShapeFunction = createShapeFunction(collisionShapeFunction, LIT)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return rlOutlineShapeFunction.apply(state)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return rlCollisionShapeFunction.apply(state)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        return (super.getPlacementState(ctx) ?: defaultState).with(LIT, ctx.world.isReceivingRedstonePower(ctx.blockPos))
    }

    override fun neighborUpdate(
        state: BlockState,
        world: World,
        pos: BlockPos,
        sourceBlock: Block,
        wireOrientation: WireOrientation?,
        notify: Boolean
    ) {
        super.neighborUpdate(state, world, pos, sourceBlock, wireOrientation, notify)

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