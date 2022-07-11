package net.bloople.allblockvariants

import net.minecraft.block.Blocks

object BlockInfos {
    val BLOCK_INFOS = arrayOf(
        BlockInfo(Blocks.STONE),
        BlockInfo(Blocks.COBBLESTONE),
        BlockInfo(Blocks.MOSSY_COBBLESTONE),
        BlockInfo(Blocks.POLISHED_GRANITE),
        BlockInfo(Blocks.POLISHED_DIORITE),
        BlockInfo(Blocks.POLISHED_ANDESITE),
        BlockInfo(Blocks.BRICKS),
        BlockInfo(Blocks.PRISMARINE),
        BlockInfo(Blocks.MOSSY_STONE_BRICKS),
        BlockInfo(Blocks.GRANITE),
        BlockInfo(Blocks.STONE_BRICKS),
        BlockInfo(Blocks.MUD_BRICKS),
        BlockInfo(Blocks.ANDESITE),
        BlockInfo(Blocks.RED_NETHER_BRICKS),
        BlockInfo(Blocks.SANDSTONE),
        BlockInfo(Blocks.END_STONE_BRICKS),
        BlockInfo(Blocks.DIORITE),
        BlockInfo(
            Blocks.OAK_PLANKS,
            MiningTool.Axe,
            flammabilityBurnChance = 5,
            flammabilitySpreadChance = 20,
            itemFuel = 300
        ),
        BlockInfo(
            Blocks.SPRUCE_PLANKS,
            MiningTool.Axe,
            flammabilityBurnChance = 5,
            flammabilitySpreadChance = 20,
            itemFuel = 300
        ),
        BlockInfo(
            Blocks.BIRCH_PLANKS,
            MiningTool.Axe,
            flammabilityBurnChance = 5,
            flammabilitySpreadChance = 20,
            itemFuel = 300
        ),
        BlockInfo(
            Blocks.JUNGLE_PLANKS,
            MiningTool.Axe,
            flammabilityBurnChance = 5,
            flammabilitySpreadChance = 20,
            itemFuel = 300
        ),
        BlockInfo(
            Blocks.ACACIA_PLANKS,
            MiningTool.Axe,
            flammabilityBurnChance = 5,
            flammabilitySpreadChance = 20,
            itemFuel = 300
        ),
        BlockInfo(
            Blocks.DARK_OAK_PLANKS,
            MiningTool.Axe,
            flammabilityBurnChance = 5,
            flammabilitySpreadChance = 20,
            itemFuel = 300
        ),
        BlockInfo(
            Blocks.MANGROVE_PLANKS,
            MiningTool.Axe,
            flammabilityBurnChance = 5,
            flammabilitySpreadChance = 20,
            itemFuel = 300
        ),
        BlockInfo(
            Blocks.CRIMSON_PLANKS,
            MiningTool.Axe,
            flammabilityBurnChance = 5,
            flammabilitySpreadChance = 20,
            itemFuel = 300
        ),
        BlockInfo(
            Blocks.WARPED_PLANKS,
            MiningTool.Axe,
            flammabilityBurnChance = 5,
            flammabilitySpreadChance = 20,
            itemFuel = 300
        ),
        // Logs 5, 5,  300
        //        creator.create(Blocks.OAK_LOG); 5, 5
        //        creator.create(Blocks.SPRUCE_LOG);5, 5
        //        creator.create(Blocks.BIRCH_LOG);5, 5
        //        creator.create(Blocks.JUNGLE_LOG);5, 5
        //        creator.create(Blocks.ACACIA_LOG);5, 5
        //        creator.create(Blocks.DARK_OAK_LOG);5, 5
        //        creator.create(Blocks.MANGROVE_LOG);5, 5
        //        creator.create(Blocks.STRIPPED_SPRUCE_LOG);5, 5
        //        creator.create(Blocks.STRIPPED_BIRCH_LOG);5, 5
        //        creator.create(Blocks.STRIPPED_JUNGLE_LOG);5, 5
        //        creator.create(Blocks.STRIPPED_ACACIA_LOG);5, 5
        //        creator.create(Blocks.STRIPPED_DARK_OAK_LOG);5, 5
        //        creator.create(Blocks.STRIPPED_OAK_LOG);5, 5
        //        creator.create(Blocks.STRIPPED_MANGROVE_LOG);5, 5

        // Wood  5, 5, ??? not usable in furnace
        //        creator.create(Blocks.OAK_WOOD);5, 5
        //        creator.create(Blocks.SPRUCE_WOOD);5, 5
        //        creator.create(Blocks.BIRCH_WOOD);5, 5
        //        creator.create(Blocks.JUNGLE_WOOD);5, 5
        //        creator.create(Blocks.ACACIA_WOOD);5, 5
        //        creator.create(Blocks.DARK_OAK_WOOD);5, 5
        //        creator.create(Blocks.MANGROVE_WOOD);5, 5
        //        creator.create(Blocks.STRIPPED_SPRUCE_WOOD);5, 5
        //        creator.create(Blocks.STRIPPED_BIRCH_WOOD);5, 5
        //        creator.create(Blocks.STRIPPED_JUNGLE_WOOD);5, 5
        //        creator.create(Blocks.STRIPPED_ACACIA_WOOD);5, 5
        //        creator.create(Blocks.STRIPPED_DARK_OAK_WOOD);5, 5
        //        creator.create(Blocks.STRIPPED_OAK_WOOD);5, 5
        //        creator.create(Blocks.STRIPPED_MANGROVE_WOOD);5, 5

        //        SAND, MiningTool.Shovel
        //            RED_SAND, MiningTool.Shovel
        //        GRAVEL, MiningTool.Shovel
        //            SPONGE, MiningTool.Hoe
        //        WET_SPONGE, MiningTool.Hoe
        //            GLASS
        BlockInfo(Blocks.LAPIS_BLOCK, needsToolLevel = MiningToolLevel.Stone),
        BlockInfo(Blocks.CHISELED_SANDSTONE),
        BlockInfo(Blocks.CUT_SANDSTONE),
        BlockInfo(
            Blocks.WHITE_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.ORANGE_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.MAGENTA_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.LIGHT_BLUE_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.YELLOW_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.LIME_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.PINK_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.GRAY_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.LIGHT_GRAY_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.CYAN_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.PURPLE_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.BLUE_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.BROWN_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.GREEN_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.RED_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(
            Blocks.BLACK_WOOL,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemFuel = 100
        ),
        BlockInfo(Blocks.GOLD_BLOCK, needsToolLevel = MiningToolLevel.Iron),
        BlockInfo(Blocks.IRON_BLOCK, needsToolLevel = MiningToolLevel.Iron),
        // TNT 15, 100
        // BOOKSHELF? , 30, 20
        BlockInfo(Blocks.OBSIDIAN, needsToolLevel = MiningToolLevel.Diamond),
        BlockInfo(Blocks.DIAMOND_BLOCK, needsToolLevel = MiningToolLevel.Iron),
        //SNOW, MiningTool.Shovel
        //ICE
        BlockInfo(Blocks.SNOW_BLOCK, MiningTool.Shovel),
        //CACTUS
        BlockInfo(Blocks.CLAY, MiningTool.Shovel),
        //NETHERRACK
        //SOUL_SAND, MiningTool.Shovel
        BlockInfo(Blocks.SOUL_SOIL, MiningTool.Shovel),
        //BASALT
        //POLISHED_BASALT
        BlockInfo(Blocks.GLOWSTONE),
        //CAKE compostability 1.0f,
        //_STAINED_GLASS
        BlockInfo(Blocks.CRACKED_STONE_BRICKS),
        BlockInfo(Blocks.CHISELED_STONE_BRICKS),
        BlockInfo(Blocks.PACKED_MUD),
        //BROWN_MUSHROOM_BLOCK compost 0.85f,
        //RED_MUSHROOM_BLOCK compost 0.85f,
        BlockInfo(Blocks.END_STONE),
        BlockInfo(Blocks.EMERALD_BLOCK, needsToolLevel = MiningToolLevel.Iron),
        //REDSTONE_BLOCK
        BlockInfo(Blocks.QUARTZ_BLOCK),
        BlockInfo(Blocks.CHISELED_QUARTZ_BLOCK),
        //QUARTZ_PILLAR
        BlockInfo(Blocks.WHITE_TERRACOTTA),
        BlockInfo(Blocks.ORANGE_TERRACOTTA),
        BlockInfo(Blocks.MAGENTA_TERRACOTTA),
        BlockInfo(Blocks.LIGHT_BLUE_TERRACOTTA),
        BlockInfo(Blocks.YELLOW_TERRACOTTA),
        BlockInfo(Blocks.LIME_TERRACOTTA),
        BlockInfo(Blocks.PINK_TERRACOTTA),
        BlockInfo(Blocks.GRAY_TERRACOTTA),
        BlockInfo(Blocks.LIGHT_GRAY_TERRACOTTA),
        BlockInfo(Blocks.CYAN_TERRACOTTA),
        BlockInfo(Blocks.PURPLE_TERRACOTTA),
        BlockInfo(Blocks.BLUE_TERRACOTTA),
        BlockInfo(Blocks.BROWN_TERRACOTTA),
        BlockInfo(Blocks.GREEN_TERRACOTTA),
        BlockInfo(Blocks.RED_TERRACOTTA),
        BlockInfo(Blocks.BLACK_TERRACOTTA),
        BlockInfo(Blocks.TERRACOTTA),
        //SLIME_BLOCK
        BlockInfo(Blocks.PRISMARINE_BRICKS),
        BlockInfo(Blocks.DARK_PRISMARINE),
        //HAY_BLOCK, MiningTool.Hoe   60, 20   compost 0.85f,
        BlockInfo(
            Blocks.COAL_BLOCK,
            flammabilityBurnChance = 5,
            flammabilitySpreadChance = 5,
            itemFuel = 16000
        ),
        BlockInfo(Blocks.PACKED_ICE),
        BlockInfo(Blocks.RED_SANDSTONE),
        BlockInfo(Blocks.CHISELED_RED_SANDSTONE),
        BlockInfo(Blocks.CUT_RED_SANDSTONE),
        BlockInfo(Blocks.SMOOTH_STONE),
        BlockInfo(Blocks.SMOOTH_SANDSTONE),
        BlockInfo(Blocks.SMOOTH_QUARTZ),
        BlockInfo(Blocks.SMOOTH_RED_SANDSTONE),
        BlockInfo(Blocks.PURPUR_BLOCK),
        //PURPUR_PILLAR
        //FROSTED_ICE
        //MAGMA_BLOCK
        BlockInfo(Blocks.NETHER_WART_BLOCK, MiningTool.Hoe, itemCompostability = 0.85f),
        //BONE_BLOCK
        BlockInfo(Blocks.WHITE_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.ORANGE_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.MAGENTA_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.YELLOW_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.LIME_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.PINK_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.GRAY_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.CYAN_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.PURPLE_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.BLUE_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.BROWN_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.GREEN_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.RED_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.BLACK_GLAZED_TERRACOTTA),
        BlockInfo(Blocks.WHITE_CONCRETE),
        BlockInfo(Blocks.ORANGE_CONCRETE),
        BlockInfo(Blocks.MAGENTA_CONCRETE),
        BlockInfo(Blocks.LIGHT_BLUE_CONCRETE),
        BlockInfo(Blocks.YELLOW_CONCRETE),
        BlockInfo(Blocks.LIME_CONCRETE),
        BlockInfo(Blocks.PINK_CONCRETE),
        BlockInfo(Blocks.GRAY_CONCRETE),
        BlockInfo(Blocks.LIGHT_GRAY_CONCRETE),
        BlockInfo(Blocks.CYAN_CONCRETE),
        BlockInfo(Blocks.PURPLE_CONCRETE),
        BlockInfo(Blocks.BLUE_CONCRETE),
        BlockInfo(Blocks.BROWN_CONCRETE),
        BlockInfo(Blocks.GREEN_CONCRETE),
        BlockInfo(Blocks.RED_CONCRETE),
        BlockInfo(Blocks.BLACK_CONCRETE),
        BlockInfo(Blocks.WHITE_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.ORANGE_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.MAGENTA_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.LIGHT_BLUE_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.YELLOW_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.LIME_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.PINK_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.GRAY_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.LIGHT_GRAY_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.CYAN_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.PURPLE_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.BLUE_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.BROWN_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.GREEN_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.RED_CONCRETE_POWDER, MiningTool.Shovel),
        BlockInfo(Blocks.BLACK_CONCRETE_POWDER, MiningTool.Shovel),
        //KELP compost 0.3f
        BlockInfo(Blocks.DRIED_KELP_BLOCK, MiningTool.Hoe,
            flammabilityBurnChance = 30,
            flammabilitySpreadChance = 60,
            itemCompostability = 0.5f,
            itemFuel = 4001
        ),
        //DEAD_TUBE_CORAL_BLOCK
        //etc?
        BlockInfo(Blocks.BLUE_ICE),
        //WARPED_STEM non flammable
        //STRIPPED_WARPED_STEM non flammable
        //WARPED_HYPHAE non flammable
        //STRIPPED_WARPED_HYPHAE non flammable
        BlockInfo(Blocks.WARPED_WART_BLOCK, MiningTool.Hoe, itemCompostability = 0.85f),
        //CRIMSON_STEM non flammable
        //STRIPPED_CRIMSON_STEM non flammable
        //CRIMSON_HYPHAE non flammable
        //STRIPPED_CRIMSON_HYPHAE non flammable
        //HONEY_BLOCK
        BlockInfo(Blocks.HONEYCOMB_BLOCK),
        BlockInfo(Blocks.NETHERITE_BLOCK, needsToolLevel = MiningToolLevel.Diamond),
        BlockInfo(Blocks.ANCIENT_DEBRIS, needsToolLevel = MiningToolLevel.Diamond),
        //CRYING_OBSIDIAN, needsTool = MiningToolLevel.Diamond
        BlockInfo(Blocks.LODESTONE),
        BlockInfo(Blocks.BLACKSTONE),
        BlockInfo(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS),
        BlockInfo(Blocks.CHISELED_POLISHED_BLACKSTONE),
        BlockInfo(Blocks.POLISHED_BLACKSTONE_BRICKS),
        BlockInfo(Blocks.POLISHED_BLACKSTONE),
        BlockInfo(Blocks.GILDED_BLACKSTONE),
        BlockInfo(Blocks.CHISELED_NETHER_BRICKS),
        BlockInfo(Blocks.CRACKED_NETHER_BRICKS),
        BlockInfo(Blocks.QUARTZ_BRICKS),
        BlockInfo(Blocks.AMETHYST_BLOCK),
        BlockInfo(Blocks.TUFF),
        BlockInfo(Blocks.CALCITE),
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
        BlockInfo(Blocks.DRIPSTONE_BLOCK),
        //MOSS_BLOCK, MiningTool.Hoe compost 0.65f
        //MUD, MiningTool.Shovel
        //DEEPSLATE
        BlockInfo(Blocks.COBBLED_DEEPSLATE),
        BlockInfo(Blocks.POLISHED_DEEPSLATE),
        BlockInfo(Blocks.DEEPSLATE_TILES),
        BlockInfo(Blocks.DEEPSLATE_BRICKS),
        BlockInfo(Blocks.CHISELED_DEEPSLATE),
        BlockInfo(Blocks.CRACKED_DEEPSLATE_BRICKS),
        BlockInfo(Blocks.CRACKED_DEEPSLATE_TILES),
        BlockInfo(Blocks.SMOOTH_BASALT),
        BlockInfo(Blocks.RAW_IRON_BLOCK, needsToolLevel = MiningToolLevel.Stone),
        BlockInfo(Blocks.RAW_COPPER_BLOCK, needsToolLevel = MiningToolLevel.Stone),
        BlockInfo(Blocks.RAW_GOLD_BLOCK, needsToolLevel = MiningToolLevel.Iron),
        BlockInfo(Blocks.REINFORCED_DEEPSLATE)
    )

    fun each(block: (blockInfo: BlockInfo) -> Unit) {
        BLOCK_INFOS.forEach(block)
    }
}
