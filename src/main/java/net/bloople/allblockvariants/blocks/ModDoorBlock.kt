package net.bloople.allblockvariants.blocks


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