package net.bloople.allblockvariants.blocks

import net.minecraft.block.BlockSetType
import net.minecraft.block.BlockState
import net.minecraft.block.DoorBlock
import net.minecraft.block.Oxidizable
import net.minecraft.block.Oxidizable.OxidationLevel
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random


@Suppress("OVERRIDE_DEPRECATION")
class OxidizableDoorBlock(private val oxidationLevel: OxidationLevel, settings: Settings, blockSetType: BlockSetType)
    : DoorBlock(settings, blockSetType), Oxidizable {

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        tickDegradation(state, world, pos, random)
    }

    override fun hasRandomTicks(state: BlockState): Boolean {
        return Oxidizable.getIncreasedOxidationBlock(state.block).isPresent
    }

    override fun getDegradationLevel(): OxidationLevel {
        return oxidationLevel
    }
}