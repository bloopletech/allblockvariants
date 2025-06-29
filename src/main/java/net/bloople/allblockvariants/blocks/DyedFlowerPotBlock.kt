package net.bloople.allblockvariants.blocks

import net.minecraft.block.*
import net.minecraft.entity.Entity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.stat.Stats
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.ItemActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import net.minecraft.world.event.GameEvent


@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class DyedFlowerPotBlock(
    private val content: Block,
    settings: Settings,
    private val mapColor: MapColor) : Block(settings) {
    companion object {
        private val CONTENT_TO_DYED_POTTED: HashMap<Pair<Block, MapColor>, Block> = HashMap()
        private val SHAPE = createCuboidShape(5.0, 0.0, 5.0, 11.0, 6.0, 11.0)
    }

    init {
        CONTENT_TO_DYED_POTTED[Pair(content, mapColor)] = this
    }

    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape? {
        return SHAPE
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun onUseWithItem(
        stack: ItemStack,
        state: BlockState?,
        world: World,
        pos: BlockPos?,
        player: PlayerEntity,
        hand: Hand?,
        hit: BlockHitResult?
    ): ItemActionResult {
        val item = stack.item
        val blockState =
            (if(item is BlockItem) CONTENT_TO_DYED_POTTED.getOrDefault(Pair(item.block, mapColor), Blocks.AIR) else Blocks.AIR).defaultState

        if(blockState.isAir) return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
        if(!this.isEmpty()) return ItemActionResult.CONSUME

        world.setBlockState(pos, blockState, NOTIFY_ALL)
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos)
        player.incrementStat(Stats.POT_FLOWER)
        stack.decrementUnlessCreative(1, player)
        return ItemActionResult.success(world.isClient)
    }

    override fun onUse(
        state: BlockState?,
        world: World,
        pos: BlockPos?,
        player: PlayerEntity,
        hit: BlockHitResult?
    ): ActionResult {
        if(this.isEmpty()) return ActionResult.CONSUME

        val itemStack = ItemStack(this.content)
        if(!player.giveItemStack(itemStack)) player.dropItem(itemStack, false)

        world.setBlockState(pos, Blocks.FLOWER_POT.defaultState, NOTIFY_ALL)
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos)
        return ActionResult.success(world.isClient)
    }

    override fun getPickStack(world: WorldView?, pos: BlockPos?, state: BlockState?): ItemStack? {
        return if(isEmpty()) super.getPickStack(world, pos, state) else ItemStack(content)
    }

    private fun isEmpty(): Boolean {
        return content === Blocks.AIR
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState?,
        world: WorldAccess?,
        pos: BlockPos?,
        neighborPos: BlockPos?
    ): BlockState? {
        if(direction === Direction.DOWN && !state.canPlaceAt(world, pos)) return Blocks.AIR.defaultState
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun canPathfindThrough(state: BlockState?, type: NavigationType?): Boolean {
        return false
    }
}
