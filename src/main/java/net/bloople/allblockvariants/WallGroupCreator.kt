package net.bloople.allblockvariants

import net.minecraft.block.Blocks

class WallGroupCreator(builder: ResourcePackBuilder) {
    private val creator: WallBlockCreator = WallBlockCreator(builder)

    fun create() {
        // Minerals
        creator.create(Blocks.STONE)
        creator.create(Blocks.POLISHED_GRANITE)
        creator.create(Blocks.POLISHED_DIORITE)
        creator.create(Blocks.POLISHED_ANDESITE)

        // Planks
        creator.create(Blocks.OAK_PLANKS)
        creator.create(Blocks.SPRUCE_PLANKS)
        creator.create(Blocks.BIRCH_PLANKS)
        creator.create(Blocks.JUNGLE_PLANKS)
        creator.create(Blocks.ACACIA_PLANKS)
        creator.create(Blocks.DARK_OAK_PLANKS)
        creator.create(Blocks.MANGROVE_PLANKS)
        creator.create(Blocks.CRIMSON_PLANKS)
        creator.create(Blocks.WARPED_PLANKS)

        // Logs
//        creator.create(Blocks.OAK_LOG);
//        creator.create(Blocks.SPRUCE_LOG);
//        creator.create(Blocks.BIRCH_LOG);
//        creator.create(Blocks.JUNGLE_LOG);
//        creator.create(Blocks.ACACIA_LOG);
//        creator.create(Blocks.DARK_OAK_LOG);
//        creator.create(Blocks.MANGROVE_LOG);
//        creator.create(Blocks.STRIPPED_SPRUCE_LOG);
//        creator.create(Blocks.STRIPPED_BIRCH_LOG);
//        creator.create(Blocks.STRIPPED_JUNGLE_LOG);
//        creator.create(Blocks.STRIPPED_ACACIA_LOG);
//        creator.create(Blocks.STRIPPED_DARK_OAK_LOG);
//        creator.create(Blocks.STRIPPED_OAK_LOG);
//        creator.create(Blocks.STRIPPED_MANGROVE_LOG);

        // Wood
//        creator.create(Blocks.OAK_WOOD);
//        creator.create(Blocks.SPRUCE_WOOD);
//        creator.create(Blocks.BIRCH_WOOD);
//        creator.create(Blocks.JUNGLE_WOOD);
//        creator.create(Blocks.ACACIA_WOOD);
//        creator.create(Blocks.DARK_OAK_WOOD);
//        creator.create(Blocks.MANGROVE_WOOD);
//        creator.create(Blocks.STRIPPED_SPRUCE_WOOD);
//        creator.create(Blocks.STRIPPED_BIRCH_WOOD);
//        creator.create(Blocks.STRIPPED_JUNGLE_WOOD);
//        creator.create(Blocks.STRIPPED_ACACIA_WOOD);
//        creator.create(Blocks.STRIPPED_DARK_OAK_WOOD);
//        creator.create(Blocks.STRIPPED_OAK_WOOD);
//        creator.create(Blocks.STRIPPED_MANGROVE_WOOD);

//        SAND
//            RED_SAND
//        GRAVEL
//            SPONGE
//        WET_SPONGE
//            GLASS
        creator.create(Blocks.LAPIS_BLOCK)
        creator.create(Blocks.CHISELED_SANDSTONE)
        creator.create(Blocks.CUT_SANDSTONE)

        // Wool
        creator.create(Blocks.WHITE_WOOL)
        creator.create(Blocks.ORANGE_WOOL)
        creator.create(Blocks.MAGENTA_WOOL)
        creator.create(Blocks.LIGHT_BLUE_WOOL)
        creator.create(Blocks.YELLOW_WOOL)
        creator.create(Blocks.LIME_WOOL)
        creator.create(Blocks.PINK_WOOL)
        creator.create(Blocks.GRAY_WOOL)
        creator.create(Blocks.LIGHT_GRAY_WOOL)
        creator.create(Blocks.CYAN_WOOL)
        creator.create(Blocks.PURPLE_WOOL)
        creator.create(Blocks.BLUE_WOOL)
        creator.create(Blocks.BROWN_WOOL)
        creator.create(Blocks.GREEN_WOOL)
        creator.create(Blocks.RED_WOOL)
        creator.create(Blocks.BLACK_WOOL)
        creator.create(Blocks.GOLD_BLOCK)
        creator.create(Blocks.IRON_BLOCK)

        //TNT

        //BOOKSHELF?
        creator.create(Blocks.OBSIDIAN)
        creator.create(Blocks.DIAMOND_BLOCK)

        //SNOW
        //ICE
        creator.create(Blocks.SNOW_BLOCK)
        //CACTUS
        creator.create(Blocks.CLAY)

        //NETHERRACK
        //SOUL_SAND
        creator.create(Blocks.SOUL_SOIL)


        //BASALT
        //POLISHED_BASALT
        creator.create(Blocks.GLOWSTONE)

        //CAKE
        //_STAINED_GLASS
        creator.create(Blocks.CRACKED_STONE_BRICKS)
        creator.create(Blocks.CHISELED_STONE_BRICKS)
        creator.create(Blocks.PACKED_MUD)

        //BROWN_MUSHROOM_BLOCK
        //RED_MUSHROOM_BLOCK
        creator.create(Blocks.END_STONE)
        creator.create(Blocks.EMERALD_BLOCK)

        //REDSTONE_BLOCK
        creator.create(Blocks.QUARTZ_BLOCK)
        creator.create(Blocks.CHISELED_QUARTZ_BLOCK)
        //QUARTZ_PILLAR

        // Terracotta
        creator.create(Blocks.WHITE_TERRACOTTA)
        creator.create(Blocks.ORANGE_TERRACOTTA)
        creator.create(Blocks.MAGENTA_TERRACOTTA)
        creator.create(Blocks.LIGHT_BLUE_TERRACOTTA)
        creator.create(Blocks.YELLOW_TERRACOTTA)
        creator.create(Blocks.LIME_TERRACOTTA)
        creator.create(Blocks.PINK_TERRACOTTA)
        creator.create(Blocks.GRAY_TERRACOTTA)
        creator.create(Blocks.LIGHT_GRAY_TERRACOTTA)
        creator.create(Blocks.CYAN_TERRACOTTA)
        creator.create(Blocks.PURPLE_TERRACOTTA)
        creator.create(Blocks.BLUE_TERRACOTTA)
        creator.create(Blocks.BROWN_TERRACOTTA)
        creator.create(Blocks.GREEN_TERRACOTTA)
        creator.create(Blocks.RED_TERRACOTTA)
        creator.create(Blocks.BLACK_TERRACOTTA)
        creator.create(Blocks.TERRACOTTA)


        //SLIME_BLOCK
        creator.create(Blocks.PRISMARINE_BRICKS)
        creator.create(Blocks.DARK_PRISMARINE)

        //HAY_BLOCK
        creator.create(Blocks.COAL_BLOCK)
        creator.create(Blocks.PACKED_ICE)
        creator.create(Blocks.RED_SANDSTONE)
        creator.create(Blocks.CHISELED_RED_SANDSTONE)
        creator.create(Blocks.CUT_RED_SANDSTONE)
        creator.create(Blocks.SMOOTH_STONE)
        creator.create(Blocks.SMOOTH_SANDSTONE)
        creator.create(Blocks.SMOOTH_QUARTZ)
        creator.create(Blocks.SMOOTH_RED_SANDSTONE)
        creator.create(Blocks.PURPUR_BLOCK)

        //PURPUR_PILLAR
        //FROSTED_ICE
        //MAGMA_BLOCK
        creator.create(Blocks.NETHER_WART_BLOCK)
        //BONE_BLOCK

        // Glazed Terracotta
        creator.create(Blocks.WHITE_GLAZED_TERRACOTTA)
        creator.create(Blocks.ORANGE_GLAZED_TERRACOTTA)
        creator.create(Blocks.MAGENTA_GLAZED_TERRACOTTA)
        creator.create(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA)
        creator.create(Blocks.YELLOW_GLAZED_TERRACOTTA)
        creator.create(Blocks.LIME_GLAZED_TERRACOTTA)
        creator.create(Blocks.PINK_GLAZED_TERRACOTTA)
        creator.create(Blocks.GRAY_GLAZED_TERRACOTTA)
        creator.create(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA)
        creator.create(Blocks.CYAN_GLAZED_TERRACOTTA)
        creator.create(Blocks.PURPLE_GLAZED_TERRACOTTA)
        creator.create(Blocks.BLUE_GLAZED_TERRACOTTA)
        creator.create(Blocks.BROWN_GLAZED_TERRACOTTA)
        creator.create(Blocks.GREEN_GLAZED_TERRACOTTA)
        creator.create(Blocks.RED_GLAZED_TERRACOTTA)
        creator.create(Blocks.BLACK_GLAZED_TERRACOTTA)

        // Concrete
        creator.create(Blocks.WHITE_CONCRETE)
        creator.create(Blocks.ORANGE_CONCRETE)
        creator.create(Blocks.MAGENTA_CONCRETE)
        creator.create(Blocks.LIGHT_BLUE_CONCRETE)
        creator.create(Blocks.YELLOW_CONCRETE)
        creator.create(Blocks.LIME_CONCRETE)
        creator.create(Blocks.PINK_CONCRETE)
        creator.create(Blocks.GRAY_CONCRETE)
        creator.create(Blocks.LIGHT_GRAY_CONCRETE)
        creator.create(Blocks.CYAN_CONCRETE)
        creator.create(Blocks.PURPLE_CONCRETE)
        creator.create(Blocks.BLUE_CONCRETE)
        creator.create(Blocks.BROWN_CONCRETE)
        creator.create(Blocks.GREEN_CONCRETE)
        creator.create(Blocks.RED_CONCRETE)
        creator.create(Blocks.BLACK_CONCRETE)

        // Concrete Powder
        creator.create(Blocks.WHITE_CONCRETE_POWDER)
        creator.create(Blocks.ORANGE_CONCRETE_POWDER)
        creator.create(Blocks.MAGENTA_CONCRETE_POWDER)
        creator.create(Blocks.LIGHT_BLUE_CONCRETE_POWDER)
        creator.create(Blocks.YELLOW_CONCRETE_POWDER)
        creator.create(Blocks.LIME_CONCRETE_POWDER)
        creator.create(Blocks.PINK_CONCRETE_POWDER)
        creator.create(Blocks.GRAY_CONCRETE_POWDER)
        creator.create(Blocks.LIGHT_GRAY_CONCRETE_POWDER)
        creator.create(Blocks.CYAN_CONCRETE_POWDER)
        creator.create(Blocks.PURPLE_CONCRETE_POWDER)
        creator.create(Blocks.BLUE_CONCRETE_POWDER)
        creator.create(Blocks.BROWN_CONCRETE_POWDER)
        creator.create(Blocks.GREEN_CONCRETE_POWDER)
        creator.create(Blocks.RED_CONCRETE_POWDER)
        creator.create(Blocks.BLACK_CONCRETE_POWDER)

        //KELP
        creator.create(Blocks.DRIED_KELP_BLOCK)

        //DEAD_TUBE_CORAL_BLOCK
        //etc?

        //BLUE_ICE

        //WARPED_STEM
        //STRIPPED_WARPED_STEM
        //WARPED_HYPHAE
        //STRIPPED_WARPED_HYPHAE
        creator.create(Blocks.WARPED_WART_BLOCK)

        //CRIMSON_STEM
        //STRIPPED_CRIMSON_STEM
        //CRIMSON_HYPHAE
        //STRIPPED_CRIMSON_HYPHAE

        //HONEY_BLOCK
        creator.create(Blocks.HONEYCOMB_BLOCK)
        creator.create(Blocks.NETHERITE_BLOCK)
        creator.create(Blocks.ANCIENT_DEBRIS)

        //CRYING_OBSIDIAN
        creator.create(Blocks.LODESTONE)
        creator.create(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
        creator.create(Blocks.CHISELED_POLISHED_BLACKSTONE)
        creator.create(Blocks.GILDED_BLACKSTONE)
        creator.create(Blocks.CHISELED_NETHER_BRICKS)
        creator.create(Blocks.CRACKED_NETHER_BRICKS)
        creator.create(Blocks.QUARTZ_BRICKS)
        creator.create(Blocks.AMETHYST_BLOCK)
        creator.create(Blocks.TUFF)
        creator.create(Blocks.CALCITE)

        //POWDER_SNOW
        //SCULK
        //OXIDIZED_COPPER
        //WEATHERED_COPPER
        //EXPOSED_COPPER
        //COPPER_BLOCK
        //OXIDIZED_CUT_COPPER
        //WEATHERED_CUT_COPPER
        //EXPOSED_CUT_COPPER
        //CUT_COPPER
        //WAXED_COPPER_BLOCK
        //WAXED_WEATHERED_COPPER
        //WAXED_EXPOSED_COPPER
        //WAXED_OXIDIZED_COPPER
        //WAXED_OXIDIZED_CUT_COPPER
        //WAXED_WEATHERED_CUT_COPPER
        //WAXED_EXPOSED_CUT_COPPER
        //WAXED_CUT_COPPER
        creator.create(Blocks.DRIPSTONE_BLOCK)
        //MOSS_BLOCK
        //MUD
        //DEEPSLATE
        creator.create(Blocks.CHISELED_DEEPSLATE)
        creator.create(Blocks.CRACKED_DEEPSLATE_BRICKS)
        creator.create(Blocks.CRACKED_DEEPSLATE_TILES)
        creator.create(Blocks.SMOOTH_BASALT)
        creator.create(Blocks.RAW_IRON_BLOCK)
        creator.create(Blocks.RAW_COPPER_BLOCK)
        creator.create(Blocks.RAW_GOLD_BLOCK)
        creator.create(Blocks.REINFORCED_DEEPSLATE)
    }
}