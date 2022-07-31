package net.bloople.allblockvariants.blocks

import net.minecraft.block.*
import net.minecraft.block.enums.SlabType
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess

@Suppress("OVERRIDE_DEPRECATION")
class ThinSlabBlock(settings: Settings) : Block(settings), Waterloggable {
    companion object {
        val THIN_SLAB_TYPE: EnumProperty<ThinSlabType> = EnumProperty.of("type", ThinSlabType::class.java)
        val TYPE: EnumProperty<ThinSlabType> = THIN_SLAB_TYPE
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
        private val BOTTOM_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0)
        private val TOP_SHAPE: VoxelShape = createCuboidShape(0.0, 12.0, 0.0, 16.0, 16.0, 16.0)
    }

    init {
        defaultState = defaultState.with(TYPE, ThinSlabType.BOTTOM).with(WATERLOGGED, false)
    }

    override fun hasSidedTransparency(state: BlockState): Boolean {
        return true
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(TYPE, WATERLOGGED)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return when(state.get(TYPE)!!) {
            ThinSlabType.TOP -> TOP_SHAPE
            ThinSlabType.BOTTOM -> BOTTOM_SHAPE
        }
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val blockPos = ctx.blockPos
        val blockState = ctx.world.getBlockState(blockPos)

        if(blockState.isOf(this)) {
            return blockState.with(TYPE, ThinSlabType.BOTTOM).with(WATERLOGGED, false)
        }

        val fluidState = ctx.world.getFluidState(blockPos)
        val blockState2 = defaultState.with(TYPE, ThinSlabType.BOTTOM).with(WATERLOGGED,fluidState.fluid === Fluids.WATER)

        val direction = ctx.side

        if(direction == Direction.DOWN || direction != Direction.UP && ctx.hitPos.y - blockPos.y.toDouble() > 0.5) {
            return blockState2.with(TYPE, ThinSlabType.TOP) as BlockState
        }
        else {
            return blockState2
        }
    }

    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean {
        val itemStack = context.stack
        val slabType = state.get(TYPE)

        if(!itemStack.isOf(asItem())) {
            return false
        }

        if(context.canReplaceExisting()) {
            val bl = context.hitPos.y - context.blockPos.y.toDouble() > 0.5
            val direction = context.side
            if(slabType == ThinSlabType.BOTTOM) return direction == Direction.UP || bl && direction.axis.isHorizontal
            else return direction == Direction.DOWN || !bl && direction.axis.isHorizontal
        }

        return true
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if(state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if(state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return when(type) {
            NavigationType.LAND -> {
                false
            }
            NavigationType.WATER -> {
                world.getFluidState(pos).isIn(FluidTags.WATER)
            }
            NavigationType.AIR -> {
                false
            }
        }
    }
}

