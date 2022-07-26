package net.bloople.allblockvariants.blocks

import net.minecraft.block.*
import net.minecraft.block.enums.SlabType
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.tag.FluidTags
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess


@Suppress("OVERRIDE_DEPRECATION")
open class VerticalSlabBlock(settings: Settings) : Block(settings), Waterloggable {
    companion object {
        val FACING: DirectionProperty = HorizontalFacingBlock.FACING

        val VERTICAL_SLAB_TYPE: EnumProperty<VerticalSlabType> = EnumProperty.of("type", VerticalSlabType::class.java)
        val TYPE: EnumProperty<VerticalSlabType> = VERTICAL_SLAB_TYPE

        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED

        protected val LEFT_NORTH_SOUTH_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 8.0, 16.0, 16.0)
        protected val RIGHT_NORTH_SOUTH_SHAPE: VoxelShape = createCuboidShape(8.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        protected val LEFT_EAST_WEST_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 8.0)
        protected val RIGHT_EAST_WEST_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 8.0, 16.0, 16.0, 16.0)
        private val STRAIGHT_SHAPES = arrayOf(
            RIGHT_NORTH_SOUTH_SHAPE,
            LEFT_NORTH_SOUTH_SHAPE,
            RIGHT_EAST_WEST_SHAPE,
            LEFT_EAST_WEST_SHAPE,
            LEFT_NORTH_SOUTH_SHAPE,
            RIGHT_NORTH_SOUTH_SHAPE,
            LEFT_EAST_WEST_SHAPE,
            RIGHT_EAST_WEST_SHAPE,
        )

        protected val NORTH_WEST_SHAPE: VoxelShape = VoxelShapes.union(LEFT_NORTH_SOUTH_SHAPE, LEFT_EAST_WEST_SHAPE)
        protected val NORTH_EAST_SHAPE: VoxelShape = VoxelShapes.union(LEFT_EAST_WEST_SHAPE, RIGHT_NORTH_SOUTH_SHAPE)
        protected val SOUTH_EAST_SHAPE: VoxelShape = VoxelShapes.union(RIGHT_NORTH_SOUTH_SHAPE, RIGHT_EAST_WEST_SHAPE)
        protected val SOUTH_WEST_SHAPE: VoxelShape = VoxelShapes.union(RIGHT_EAST_WEST_SHAPE, LEFT_NORTH_SOUTH_SHAPE)
        private val CORNER_SHAPES = arrayOf(
            NORTH_WEST_SHAPE,
            NORTH_EAST_SHAPE,
            SOUTH_EAST_SHAPE,
            SOUTH_WEST_SHAPE
        )
    }

    init {
        defaultState = defaultState
            .with(FACING, Direction.EAST)
            .with(WATERLOGGED, false)
            .with(TYPE, VerticalSlabType.LEFT)
    }

    override fun hasSidedTransparency(state: BlockState): Boolean {
        return true
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING, TYPE, WATERLOGGED)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return when(state.get(TYPE)) {
            VerticalSlabType.LEFT, VerticalSlabType.RIGHT -> {
                STRAIGHT_SHAPES[(state.get(FACING).horizontal * 2) + state.get(TYPE).ordinal]
            }
            else -> {
                CORNER_SHAPES[state.get(TYPE).ordinal - 2]
            }
        }
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val blockPos = ctx.blockPos
        val blockState = ctx.world.getBlockState(blockPos)

        val direction = ctx.side
        val facing = if(direction.axis.isVertical) ctx.playerFacing else direction

        if(blockState.isOf(this)) {
            return blockState
                .with(FACING, facing)
                .with(WATERLOGGED, false)
                //.with(TYPE, VerticalSlabType.DOUBLE)
        }

        val fluidState = ctx.world.getFluidState(blockPos)

        val slabType = focussedSlabType(ctx, facing)
        return defaultState
            .with(FACING, facing)
            .with(WATERLOGGED, fluidState.fluid === Fluids.WATER)
            .with(TYPE, slabType)
    }

    override fun canReplace(state: BlockState, ctx: ItemPlacementContext): Boolean {
        val itemStack = ctx.stack
        val slabType = state.get(TYPE)

        if(/*slabType == VerticalSlabType.DOUBLE || */!itemStack.isOf(asItem())) return false

        if(ctx.canReplaceExisting()) {
            return false
        }
        return true
    }

    private fun focussedSlabType(ctx: ItemPlacementContext, facing: Direction): VerticalSlabType {
        val right = when(facing) {
            Direction.WEST -> ctx.hitPos.z - ctx.blockPos.z.toDouble() <= 0.5
            Direction.NORTH -> ctx.hitPos.x - ctx.blockPos.x.toDouble() > 0.5
            Direction.EAST -> ctx.hitPos.z - ctx.blockPos.z.toDouble() > 0.5
            Direction.SOUTH -> ctx.hitPos.x - ctx.blockPos.x.toDouble() <= 0.5
            else -> throw java.lang.RuntimeException("Invalid direction")
        }
        return if(right) VerticalSlabType.RIGHT else VerticalSlabType.LEFT
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state[FACING])) as BlockState
    }

    //mirror?

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