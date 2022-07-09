package net.bloople.allblockvariants

import net.minecraft.block.Blocks

class SlabsGroupCreator(builder: ResourcePackBuilder) {
    private val creator = SlabCreator(builder)

    fun create() {
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

//        SAND, MiningTool.Shovel
//            RED_SAND, MiningTool.Shovel
//        GRAVEL, MiningTool.Shovel
//            SPONGE, MiningTool.Hoe
//        WET_SPONGE, MiningTool.Hoe
//            GLASS
        creator.create(Blocks.LAPIS_BLOCK, needsTool = MiningToolLevel.Stone)
        creator.create(Blocks.CHISELED_SANDSTONE)

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
        creator.create(Blocks.GOLD_BLOCK, needsTool = MiningToolLevel.Iron)
        creator.create(Blocks.IRON_BLOCK, needsTool = MiningToolLevel.Stone)

        //TNT

        //BOOKSHELF?
        creator.create(Blocks.OBSIDIAN, needsTool = MiningToolLevel.Diamond)
        creator.create(Blocks.DIAMOND_BLOCK, needsTool = MiningToolLevel.Iron)

        //SNOW, MiningTool.Shovel
        //ICE
        creator.create(Blocks.SNOW_BLOCK, MiningTool.Shovel)
        //CACTUS
        creator.create(Blocks.CLAY, MiningTool.Shovel)

        //NETHERRACK
        //SOUL_SAND, MiningTool.Shovel
        creator.create(Blocks.SOUL_SOIL, MiningTool.Shovel)


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
        creator.create(Blocks.EMERALD_BLOCK, needsTool = MiningToolLevel.Iron)

        //REDSTONE_BLOCK
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

        //HAY_BLOCK, MiningTool.Hoe
        creator.create(Blocks.COAL_BLOCK)
        creator.create(Blocks.PACKED_ICE)
        creator.create(Blocks.CHISELED_RED_SANDSTONE)

        //PURPUR_PILLAR
        //FROSTED_ICE
        //MAGMA_BLOCK
        creator.create(Blocks.NETHER_WART_BLOCK, MiningTool.Hoe)
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
        creator.create(Blocks.WHITE_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.ORANGE_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.MAGENTA_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.LIGHT_BLUE_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.YELLOW_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.LIME_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.PINK_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.GRAY_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.LIGHT_GRAY_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.CYAN_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.PURPLE_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.BLUE_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.BROWN_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.GREEN_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.RED_CONCRETE_POWDER, MiningTool.Shovel)
        creator.create(Blocks.BLACK_CONCRETE_POWDER, MiningTool.Shovel)

        //KELP
        creator.create(Blocks.DRIED_KELP_BLOCK, MiningTool.Hoe)

        //DEAD_TUBE_CORAL_BLOCK
        //etc?

        //BLUE_ICE

        //WARPED_STEM
        //STRIPPED_WARPED_STEM
        //WARPED_HYPHAE
        //STRIPPED_WARPED_HYPHAE
        creator.create(Blocks.WARPED_WART_BLOCK, MiningTool.Hoe)

        //CRIMSON_STEM
        //STRIPPED_CRIMSON_STEM
        //CRIMSON_HYPHAE
        //STRIPPED_CRIMSON_HYPHAE

        //HONEY_BLOCK
        creator.create(Blocks.HONEYCOMB_BLOCK)
        creator.create(Blocks.NETHERITE_BLOCK, needsTool = MiningToolLevel.Diamond)
        creator.create(Blocks.ANCIENT_DEBRIS, needsTool = MiningToolLevel.Diamond)

        //CRYING_OBSIDIAN, needsTool = MiningToolLevel.Diamond
        creator.create(Blocks.LODESTONE)
        creator.create(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
        creator.create(Blocks.CHISELED_POLISHED_BLACKSTONE)
        creator.create(Blocks.GILDED_BLACKSTONE)
        creator.create(Blocks.CHISELED_NETHER_BRICKS)
        creator.create(Blocks.CRACKED_NETHER_BRICKS)
        creator.create(Blocks.AMETHYST_BLOCK)
        creator.create(Blocks.TUFF)
        creator.create(Blocks.CALCITE)

        //POWDER_SNOW
        //SCULK, MiningTool.Hoe
        //OXIDIZED_COPPER, needsTool = MiningToolLevel.Stone
        //WEATHERED_COPPER, needsTool = MiningToolLevel.Stone
        //EXPOSED_COPPER, needsTool = MiningToolLevel.Stone
        //COPPER_BLOCK, needsTool = MiningToolLevel.Stone
        //OXIDIZED_CUT_COPPER, needsTool = MiningToolLevel.Stone
        //WEATHERED_CUT_COPPER, needsTool = MiningToolLevel.Stone
        //EXPOSED_CUT_COPPER, needsTool = MiningToolLevel.Stone
        //CUT_COPPER, needsTool = MiningToolLevel.Stone
        //WAXED_COPPER_BLOCK, needsTool = MiningToolLevel.Stone
        //WAXED_WEATHERED_COPPER, needsTool = MiningToolLevel.Stone
        //WAXED_EXPOSED_COPPER, needsTool = MiningToolLevel.Stone
        //WAXED_OXIDIZED_COPPER, needsTool = MiningToolLevel.Stone
        //WAXED_OXIDIZED_CUT_COPPER, needsTool = MiningToolLevel.Stone
        //WAXED_WEATHERED_CUT_COPPER, needsTool = MiningToolLevel.Stone
        //WAXED_EXPOSED_CUT_COPPER, needsTool = MiningToolLevel.Stone
        //WAXED_CUT_COPPER, needsTool = MiningToolLevel.Stone
        creator.create(Blocks.DRIPSTONE_BLOCK)
        //MOSS_BLOCK, MiningTool.Hoe
        //MUD, MiningTool.Shovel
        //DEEPSLATE
        creator.create(Blocks.CHISELED_DEEPSLATE)
        creator.create(Blocks.CRACKED_DEEPSLATE_BRICKS)
        creator.create(Blocks.CRACKED_DEEPSLATE_TILES)
        creator.create(Blocks.SMOOTH_BASALT)
        creator.create(Blocks.RAW_IRON_BLOCK, needsTool = MiningToolLevel.Stone)
        creator.create(Blocks.RAW_COPPER_BLOCK, needsTool = MiningToolLevel.Stone)
        creator.create(Blocks.RAW_GOLD_BLOCK, needsTool = MiningToolLevel.Iron)
        creator.create(Blocks.REINFORCED_DEEPSLATE)
    }
}