package net.bloople.allblockvariants.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SlabBlock
import net.minecraft.block.enums.SlabType
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.Direction


@Suppress("OVERRIDE_DEPRECATION")
class PillarSlabBlock(settings: Settings) : SlabBlock(settings) {
    companion object {
        val AXIS: EnumProperty<Direction.Axis> = Properties.AXIS

        fun changeRotation(state: BlockState, rotation: BlockRotation): BlockState {
            return when(rotation) {
                BlockRotation.COUNTERCLOCKWISE_90, BlockRotation.CLOCKWISE_90 -> {
                    when(state.get(AXIS)) {
                        Direction.Axis.X -> {
                            state.with(AXIS, Direction.Axis.Z)
                        }
                        Direction.Axis.Z -> {
                            state.with(AXIS, Direction.Axis.X)
                        }
                        else -> state
                    }
                }
                else -> state
            }
        }
    }

    init {
        defaultState = defaultState.with(AXIS, Direction.Axis.Y)
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return changeRotation(state, rotation)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(AXIS)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val placementState = super.getPlacementState(ctx) ?: defaultState
        return if(placementState.get(TYPE) == SlabType.DOUBLE) placementState
        else placementState.with(AXIS, ctx.side.axis)
    }
}