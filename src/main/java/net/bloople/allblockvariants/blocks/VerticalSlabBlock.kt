package net.bloople.allblockvariants.blocks

import net.minecraft.block.*
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

        val VERTICAL_SLAB_SHAPE: EnumProperty<VerticalSlabShape> = EnumProperty.of("shape", VerticalSlabShape::class.java)
        val SHAPE: EnumProperty<VerticalSlabShape> = VERTICAL_SLAB_SHAPE

        val VERTICAL_SLAB_TYPE: EnumProperty<VerticalSlabType> = EnumProperty.of("type", VerticalSlabType::class.java)
        val TYPE: EnumProperty<VerticalSlabType> = VERTICAL_SLAB_TYPE

        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED

        protected val WEST_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 8.0, 16.0, 16.0)
        protected val EAST_SHAPE: VoxelShape = createCuboidShape(8.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        protected val NORTH_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 8.0)
        protected val SOUTH_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 8.0, 16.0, 16.0, 16.0)
        private val STRAIGHT_SHAPES = arrayOf(
            EAST_SHAPE,
            WEST_SHAPE,
            SOUTH_SHAPE,
            NORTH_SHAPE,
            WEST_SHAPE,
            EAST_SHAPE,
            NORTH_SHAPE,
            SOUTH_SHAPE,
        )

        protected val NORTH_WEST_SHAPE: VoxelShape = VoxelShapes.union(WEST_SHAPE, NORTH_SHAPE)
        protected val NORTH_EAST_SHAPE: VoxelShape = VoxelShapes.union(NORTH_SHAPE, EAST_SHAPE)
        protected val SOUTH_EAST_SHAPE: VoxelShape = VoxelShapes.union(EAST_SHAPE, SOUTH_SHAPE)
        protected val SOUTH_WEST_SHAPE: VoxelShape = VoxelShapes.union(SOUTH_SHAPE, WEST_SHAPE)
        private val CORNER_SHAPES = arrayOf(
            NORTH_EAST_SHAPE,
            SOUTH_EAST_SHAPE,
            SOUTH_WEST_SHAPE,
            NORTH_WEST_SHAPE
        )

        fun isVerticalSlab(state: BlockState): Boolean {
            return state.block is VerticalSlabBlock
        }

        fun getAbsoluteOrientation(state: BlockState): Array<VerticalSlabOrientation> {
            return getAbsoluteOrientation(state.get(SHAPE), state.get(TYPE)!!, state.get(FACING))
        }

        fun getAbsoluteOrientation(
            shape: VerticalSlabShape,
            type: VerticalSlabType,
            facing: Direction): Array<VerticalSlabOrientation> {
            return when(facing) {
                Direction.NORTH -> {
                    when(shape) {
                        VerticalSlabShape.STRAIGHT -> {
                            when(type) {
                                VerticalSlabType.LEFT -> arrayOf(VerticalSlabOrientation.WEST)
                                VerticalSlabType.RIGHT -> arrayOf(VerticalSlabOrientation.EAST)
                            }
                        }
                        VerticalSlabShape.NORTH_WEST -> arrayOf(VerticalSlabOrientation.WEST, VerticalSlabOrientation.SOUTH)
                        VerticalSlabShape.NORTH_EAST -> arrayOf(VerticalSlabOrientation.WEST, VerticalSlabOrientation.NORTH)
                        VerticalSlabShape.SOUTH_EAST -> arrayOf(VerticalSlabOrientation.NORTH, VerticalSlabOrientation.EAST)
                        VerticalSlabShape.SOUTH_WEST -> arrayOf(VerticalSlabOrientation.EAST, VerticalSlabOrientation.SOUTH)
                    }
                }
                Direction.EAST -> {
                    when(shape) {
                        VerticalSlabShape.STRAIGHT -> {
                            when(type) {
                                VerticalSlabType.LEFT -> arrayOf(VerticalSlabOrientation.NORTH)
                                VerticalSlabType.RIGHT -> arrayOf(VerticalSlabOrientation.SOUTH)
                            }
                        }
                        VerticalSlabShape.NORTH_WEST -> arrayOf(VerticalSlabOrientation.NORTH, VerticalSlabOrientation.WEST)
                        VerticalSlabShape.NORTH_EAST -> arrayOf(VerticalSlabOrientation.NORTH, VerticalSlabOrientation.EAST)
                        VerticalSlabShape.SOUTH_EAST -> arrayOf(VerticalSlabOrientation.SOUTH, VerticalSlabOrientation.EAST)
                        VerticalSlabShape.SOUTH_WEST -> arrayOf(VerticalSlabOrientation.SOUTH, VerticalSlabOrientation.WEST)
                    }
                }
                Direction.SOUTH -> {
                    when(shape) {
                        VerticalSlabShape.STRAIGHT -> {
                            when(type) {
                                VerticalSlabType.LEFT -> arrayOf(VerticalSlabOrientation.EAST)
                                VerticalSlabType.RIGHT -> arrayOf(VerticalSlabOrientation.WEST)
                            }
                        }
                        VerticalSlabShape.NORTH_WEST -> arrayOf(VerticalSlabOrientation.NORTH, VerticalSlabOrientation.EAST)
                        VerticalSlabShape.NORTH_EAST -> arrayOf(VerticalSlabOrientation.EAST, VerticalSlabOrientation.SOUTH)
                        VerticalSlabShape.SOUTH_EAST -> arrayOf(VerticalSlabOrientation.WEST, VerticalSlabOrientation.SOUTH)
                        VerticalSlabShape.SOUTH_WEST -> arrayOf(VerticalSlabOrientation.WEST, VerticalSlabOrientation.NORTH)
                    }
                }
                Direction.WEST -> {
                    when(shape) {
                        VerticalSlabShape.STRAIGHT -> {
                            when(type) {
                                VerticalSlabType.LEFT -> arrayOf(VerticalSlabOrientation.SOUTH)
                                VerticalSlabType.RIGHT -> arrayOf(VerticalSlabOrientation.NORTH)
                            }
                        }
                        VerticalSlabShape.NORTH_WEST -> arrayOf(VerticalSlabOrientation.SOUTH, VerticalSlabOrientation.EAST)
                        VerticalSlabShape.NORTH_EAST -> arrayOf(VerticalSlabOrientation.SOUTH, VerticalSlabOrientation.WEST)
                        VerticalSlabShape.SOUTH_EAST -> arrayOf(VerticalSlabOrientation.NORTH, VerticalSlabOrientation.WEST)
                        VerticalSlabShape.SOUTH_WEST -> arrayOf(VerticalSlabOrientation.NORTH, VerticalSlabOrientation.EAST)
                    }
                }
                else -> throw java.lang.RuntimeException("Invalid direction")
            }
        }

        fun getRelativeOrientation(
            d1: VerticalSlabOrientation,
            d2: VerticalSlabOrientation,
            facing: Direction): VerticalSlabShape {
            return when(facing) {
                Direction.NORTH -> {
                    if((d1 == VerticalSlabOrientation.WEST && d2 == VerticalSlabOrientation.SOUTH)
                        || (d1 == VerticalSlabOrientation.SOUTH && d2 == VerticalSlabOrientation.WEST))
                        VerticalSlabShape.NORTH_WEST
                    else if((d1 == VerticalSlabOrientation.NORTH && d2 == VerticalSlabOrientation.WEST)
                        || (d1 == VerticalSlabOrientation.WEST && d2 == VerticalSlabOrientation.NORTH))
                        VerticalSlabShape.NORTH_EAST
                    else if((d1 == VerticalSlabOrientation.EAST && d2 == VerticalSlabOrientation.NORTH)
                        || (d1 == VerticalSlabOrientation.NORTH && d2 == VerticalSlabOrientation.EAST))
                        VerticalSlabShape.SOUTH_EAST
                    else if((d1 == VerticalSlabOrientation.SOUTH && d2 == VerticalSlabOrientation.EAST)
                        || (d1 == VerticalSlabOrientation.EAST && d2 == VerticalSlabOrientation.SOUTH))
                        VerticalSlabShape.SOUTH_WEST
                    else throw java.lang.RuntimeException("Invalid orientations")
                }
                Direction.EAST -> {
                    if((d1 == VerticalSlabOrientation.NORTH && d2 == VerticalSlabOrientation.WEST)
                        || (d1 == VerticalSlabOrientation.WEST && d2 == VerticalSlabOrientation.NORTH))
                        VerticalSlabShape.NORTH_WEST
                    else if((d1 == VerticalSlabOrientation.NORTH && d2 == VerticalSlabOrientation.EAST)
                        || (d1 == VerticalSlabOrientation.EAST && d2 == VerticalSlabOrientation.NORTH))
                        VerticalSlabShape.NORTH_EAST
                    else if((d1 == VerticalSlabOrientation.EAST && d2 == VerticalSlabOrientation.SOUTH)
                        || (d1 == VerticalSlabOrientation.SOUTH && d2 == VerticalSlabOrientation.EAST))
                        VerticalSlabShape.SOUTH_EAST
                    else if((d1 == VerticalSlabOrientation.SOUTH && d2 == VerticalSlabOrientation.WEST)
                        || (d1 == VerticalSlabOrientation.WEST && d2 == VerticalSlabOrientation.SOUTH))
                        VerticalSlabShape.SOUTH_WEST
                    else throw java.lang.RuntimeException("Invalid orientations")
                }
                Direction.SOUTH -> {
                    if((d1 == VerticalSlabOrientation.NORTH && d2 == VerticalSlabOrientation.EAST)
                        || (d1 == VerticalSlabOrientation.EAST && d2 == VerticalSlabOrientation.NORTH))
                        VerticalSlabShape.NORTH_WEST
                    else if((d1 == VerticalSlabOrientation.SOUTH && d2 == VerticalSlabOrientation.EAST)
                        || (d1 == VerticalSlabOrientation.EAST && d2 == VerticalSlabOrientation.SOUTH))
                        VerticalSlabShape.NORTH_EAST
                    else if((d1 == VerticalSlabOrientation.WEST && d2 == VerticalSlabOrientation.SOUTH)
                        || (d1 == VerticalSlabOrientation.SOUTH && d2 == VerticalSlabOrientation.WEST))
                        VerticalSlabShape.SOUTH_EAST
                    else if((d1 == VerticalSlabOrientation.NORTH && d2 == VerticalSlabOrientation.WEST)
                        || (d1 == VerticalSlabOrientation.WEST && d2 == VerticalSlabOrientation.NORTH))
                        VerticalSlabShape.SOUTH_WEST
                    else throw java.lang.RuntimeException("Invalid orientations")
                }
                Direction.WEST -> {
                    if((d1 == VerticalSlabOrientation.EAST && d2 == VerticalSlabOrientation.SOUTH)
                        || (d1 == VerticalSlabOrientation.SOUTH && d2 == VerticalSlabOrientation.EAST))
                        VerticalSlabShape.NORTH_WEST
                    else if((d1 == VerticalSlabOrientation.WEST && d2 == VerticalSlabOrientation.SOUTH)
                        || (d1 == VerticalSlabOrientation.SOUTH && d2 == VerticalSlabOrientation.WEST))
                        VerticalSlabShape.NORTH_EAST
                    else if((d1 == VerticalSlabOrientation.NORTH && d2 == VerticalSlabOrientation.WEST)
                        || (d1 == VerticalSlabOrientation.WEST && d2 == VerticalSlabOrientation.NORTH))
                        VerticalSlabShape.SOUTH_EAST
                    else if((d1 == VerticalSlabOrientation.NORTH && d2 == VerticalSlabOrientation.EAST)
                        || (d1 == VerticalSlabOrientation.EAST && d2 == VerticalSlabOrientation.NORTH))
                        VerticalSlabShape.SOUTH_WEST
                    else throw java.lang.RuntimeException("Invalid orientations")
                }
                else -> throw java.lang.RuntimeException("Invalid orientations")
            }
        }
    }

    init {
        defaultState = defaultState
            .with(FACING, Direction.EAST)
            .with(SHAPE, VerticalSlabShape.STRAIGHT)
            .with(TYPE, VerticalSlabType.LEFT)
            .with(WATERLOGGED, false)
    }

    override fun hasSidedTransparency(state: BlockState): Boolean {
        return true
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING, TYPE, SHAPE, WATERLOGGED)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return when(state.get(SHAPE)) {
            VerticalSlabShape.STRAIGHT -> {
                STRAIGHT_SHAPES[(state.get(FACING).horizontal * 2) + state.get(TYPE).ordinal]
            }
            else -> {
                CORNER_SHAPES[(state.get(SHAPE).ordinal - 1 + state.get(FACING).horizontal) % CORNER_SHAPES.size]
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
        }

        val fluidState = ctx.world.getFluidState(blockPos)

        val slabType = focussedSlabType(ctx, facing)
        val slabShape = getVerticalSlabType(ctx.world, blockPos, facing)

        return defaultState
            .with(FACING, facing)
            .with(SHAPE, slabShape)
            .with(TYPE, slabType)
            .with(WATERLOGGED, fluidState.fluid === Fluids.WATER)
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

    private fun getVerticalSlabType(world: BlockView, pos: BlockPos, facing: Direction): VerticalSlabShape {
        val neighbourBlockStates = listOf(
            pos.north(),
            pos.east(),
            pos.south(),
            pos.west()
        ).map { world.getBlockState(it) }.map { if(isVerticalSlab(it)) it else null }

        val neighbours = neighbourBlockStates.map { if(it != null) getAbsoluteOrientation(it) else null }

        val north = neighbours[0]
        val east = neighbours[1]
        val south = neighbours[2]
        val west = neighbours[3]

        if(north?.contains(VerticalSlabOrientation.WEST) == true && east?.contains(VerticalSlabOrientation.SOUTH) == true) {
            return getRelativeOrientation(VerticalSlabOrientation.WEST, VerticalSlabOrientation.SOUTH, facing)
        }

        if(east?.contains(VerticalSlabOrientation.NORTH) == true && south?.contains(VerticalSlabOrientation.WEST) == true) {
            return getRelativeOrientation(VerticalSlabOrientation.NORTH, VerticalSlabOrientation.WEST, facing)
        }

        if(south?.contains(VerticalSlabOrientation.EAST) == true && west?.contains(VerticalSlabOrientation.NORTH) == true) {
            return getRelativeOrientation(VerticalSlabOrientation.EAST, VerticalSlabOrientation.NORTH, facing)
        }

        if(west?.contains(VerticalSlabOrientation.SOUTH) == true && north?.contains(VerticalSlabOrientation.EAST) == true) {
            return getRelativeOrientation(VerticalSlabOrientation.SOUTH, VerticalSlabOrientation.EAST, facing)
        }

        return VerticalSlabShape.STRAIGHT
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
        if(direction.axis.isHorizontal) {
            val slabShape = getVerticalSlabType(world, pos, state.get(FACING))
            return state.with(SHAPE, slabShape)
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