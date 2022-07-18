package net.bloople.allblockvariants.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.DoorBlock
import net.minecraft.block.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldEvents


//class ModDoorBlock(settings: Settings) : DoorBlock(settings) {
//    override fun onUse(
//        state: BlockState,
//        world: World,
//        pos: BlockPos,
//        player: PlayerEntity,
//        hand: Hand,
//        hit: BlockHitResult
//    ): ActionResult? {
//        if(material == Material.WOOD) return super.onUse(state, world, pos, player, hand, hit)
//        return ActionResult.PASS
//    }
//
//    private fun getCloseSoundEventId(): Int {
//        return if(material == Material.METAL) WorldEvents.IRON_DOOR_CLOSES else WorldEvents.WOODEN_DOOR_CLOSES
//    }
//
//    private fun getOpenSoundEventId(): Int {
//        return if(material == Material.METAL) WorldEvents.IRON_DOOR_OPENS else WorldEvents.WOODEN_DOOR_OPENS
//    }
//}