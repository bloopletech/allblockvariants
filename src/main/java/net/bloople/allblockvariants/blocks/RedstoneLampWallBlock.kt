package net.bloople.allblockvariants.blocks

import com.google.common.collect.ImmutableMap
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


@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class RedstoneLampWallBlock(settings: Settings) : WallBlock(settings) {
    private val rlShapeMap: Map<BlockState, VoxelShape>
    private val rlCollisionShapeMap: Map<BlockState, VoxelShape>
    companion object {
        val LIT: BooleanProperty = RedstoneTorchBlock.LIT
    }

    init {
        defaultState = defaultState.with(LIT, false)

        val smBuilder: ImmutableMap.Builder<BlockState, VoxelShape> = ImmutableMap.builder()
        for((baseBlockState, shape) in shapeMap) {
            smBuilder.put(baseBlockState.with(LIT, false), shape)
            smBuilder.put(baseBlockState.with(LIT, true), shape)
        }
        rlShapeMap = smBuilder.build()

        val csmBuilder: ImmutableMap.Builder<BlockState, VoxelShape> = ImmutableMap.builder()
        for((baseBlockState, shape) in collisionShapeMap) {
            csmBuilder.put(baseBlockState.with(LIT, false), shape)
            csmBuilder.put(baseBlockState.with(LIT, true), shape)
        }
        rlCollisionShapeMap = csmBuilder.build()
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return rlShapeMap[state]!!
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return rlCollisionShapeMap[state]!!
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
            if(bl) world.createAndScheduleBlockTick(pos, this, 4)
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