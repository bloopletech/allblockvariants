package net.bloople.allblockvariants

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.Property
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.registry.Registries
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.util.Util
import org.jetbrains.annotations.Nullable


class ModStickItem(settings: Settings) : Item(settings) {
    companion object {
        private fun <T : Comparable<T>> cycle(state: BlockState, property: Property<T>, inverse: Boolean): BlockState {
            return state.with(property, cycle(property.values, state[property], inverse))
        }

        private fun <T> cycle(elements: Iterable<T>, @Nullable current: T, inverse: Boolean): T {
            return if(inverse) Util.previous(elements, current) else Util.next(elements, current)
        }

        private fun sendMessage(player: PlayerEntity, message: String) {
            (player as ServerPlayerEntity).sendMessage(Text.literal(message), true)
        }

        private fun <T : Comparable<T>> getValueString(state: BlockState, property: Property<T>): String {
            return property.name(state[property])
        }
    }

    override fun hasGlint(stack: ItemStack): Boolean {
        return true
    }

    override fun getRecipeRemainder(): Item {
        return this
    }

    override fun hasRecipeRemainder(): Boolean {
        return true
    }

    override fun canMine(state: BlockState, world: World, pos: BlockPos, miner: PlayerEntity): Boolean {
        if(!world.isClient) {
            this.use(miner, state, world, pos, false, miner.getStackInHand(Hand.MAIN_HAND))
        }
        return false
    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        var blockPos: BlockPos
        val playerEntity = context.player
        val world = context.world

        if(!world.isClient && playerEntity != null && !this.use(
                playerEntity,
                world.getBlockState(context.blockPos.also { blockPos = it }),
                world,
                blockPos,
                true,
                context.stack
            )
        ) {
            return ActionResult.FAIL
        }
        else {
            return ActionResult.success(world.isClient)
        }
    }

    private fun use(
        player: PlayerEntity,
        state: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        update: Boolean,
        stack: ItemStack
    ): Boolean {
        val block: Block = state.block
        val stateManager: StateManager<Block, BlockState> = block.stateManager
        val collection: Collection<Property<*>> = stateManager.properties
        val blockName: String = Registries.BLOCK.getId(block).toString()

        if(collection.isEmpty()) {
            sendMessage(player, "$blockName has no properties")
            return false
        }

        val nbtCompound = stack.getOrCreateSubNbt("ModStickItemProperty")
        var property: Property<*>? = stateManager.getProperty(nbtCompound.getString(blockName))

        if(update) {
            if(property == null) property = collection.iterator().next()

            val blockState = cycle(state, property, player.shouldCancelInteraction())
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS or Block.FORCE_STATE)
            sendMessage(player, "\"${property.name}\" to ${getValueString(blockState, property)}")
        }
        else {
            property = cycle(collection, property, player.shouldCancelInteraction())
            val propertyName: String = property!!.name
            nbtCompound.putString(blockName, propertyName)
            sendMessage(player, "selected \"$propertyName\" (${getValueString(state, property)})")
        }

        return true
    }
}