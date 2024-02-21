package net.bloople.allblockvariants

import net.minecraft.block.BlockSetType
import net.minecraft.block.Blocks
import net.minecraft.block.WoodType
import net.minecraft.util.Identifier


val BLOCK_INFOS = arrayOf(
    BlockInfo(Blocks.STONE, blockSetType = BlockSetType.STONE),
    BlockInfo(Blocks.COBBLESTONE, blockSetType = BlockSetType.STONE),
    BlockInfo(Blocks.MOSSY_COBBLESTONE, blockSetType = BlockSetType.STONE),
    BlockInfo(Blocks.POLISHED_GRANITE),
    BlockInfo(Blocks.POLISHED_DIORITE),
    BlockInfo(Blocks.POLISHED_ANDESITE),
    //BlockInfo(Blocks.GRASS_BLOCK),
    BlockInfo(Blocks.DIRT, preferredTool = MiningTool.Shovel),
    BlockInfo(Blocks.COARSE_DIRT, preferredTool = MiningTool.Shovel),
    BlockInfo(Blocks.BRICKS),
    BlockInfo(Blocks.PRISMARINE),
    BlockInfo(Blocks.MOSSY_STONE_BRICKS, blockSetType = BlockSetType.STONE),
    BlockInfo(Blocks.GRANITE),
    BlockInfo(Blocks.STONE_BRICKS, blockSetType = BlockSetType.STONE),
    BlockInfo(Blocks.MUD_BRICKS),
    BlockInfo(Blocks.ANDESITE),
    BlockInfo(Blocks.NETHER_BRICKS),
    BlockInfo(Blocks.RED_NETHER_BRICKS),
    BlockInfo(Blocks.SANDSTONE),
    BlockInfo(Blocks.END_STONE_BRICKS),
    BlockInfo(Blocks.DIORITE),
    BlockInfo(
        Blocks.OAK_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.OAK,
        woodType = WoodType.OAK
    ),
    BlockInfo(
        Blocks.SPRUCE_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.SPRUCE,
        woodType = WoodType.SPRUCE
    ),
    BlockInfo(
        Blocks.BIRCH_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.BIRCH
    ),
    BlockInfo(
        Blocks.JUNGLE_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.JUNGLE
    ),
    BlockInfo(
        Blocks.ACACIA_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.ACACIA
    ),
    BlockInfo(
        Blocks.CHERRY_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.CHERRY
    ),
    BlockInfo(
        Blocks.DARK_OAK_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.DARK_OAK,
        woodType = WoodType.DARK_OAK
    ),
    BlockInfo(
        Blocks.MANGROVE_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.MANGROVE,
        woodType = WoodType.MANGROVE
    ),
    BlockInfo(
        Blocks.BAMBOO_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.BAMBOO,
        woodType = WoodType.BAMBOO
    ),
    BlockInfo(
        Blocks.BAMBOO_MOSAIC,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.BAMBOO,
        woodType = WoodType.BAMBOO
    ),
    BlockInfo(
        Blocks.CRIMSON_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.CRIMSON,
        woodType = WoodType.CRIMSON
    ),
    BlockInfo(
        Blocks.WARPED_PLANKS,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 20,
        itemFuel = 300,
        blockSetType = BlockSetType.WARPED,
        woodType = WoodType.WARPED
    ),
    BlockInfo(
        Blocks.OAK_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.OAK,
        woodType = WoodType.OAK,
        horizontalModelIdentifier = Identifier("oak_log_horizontal"),
        textureInfo = BlockTextureInfo("oak_log_top", "oak_log")
    ),
    BlockInfo(
        Blocks.SPRUCE_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.SPRUCE,
        woodType = WoodType.SPRUCE,
        horizontalModelIdentifier = Identifier("spruce_log_horizontal"),
        textureInfo = BlockTextureInfo("spruce_log_top", "spruce_log")
    ),
    BlockInfo(
        Blocks.BIRCH_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.BIRCH,
        woodType = WoodType.BIRCH,
        horizontalModelIdentifier = Identifier("birch_log_horizontal"),
        textureInfo = BlockTextureInfo("birch_log_top", "birch_log")
    ),
    BlockInfo(
        Blocks.JUNGLE_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.JUNGLE,
        woodType = WoodType.JUNGLE,
        horizontalModelIdentifier = Identifier("jungle_log_horizontal"),
        textureInfo = BlockTextureInfo("jungle_log_top", "jungle_log")
    ),
    BlockInfo(
        Blocks.ACACIA_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.ACACIA,
        woodType = WoodType.ACACIA,
        horizontalModelIdentifier = Identifier("acacia_log_horizontal"),
        textureInfo = BlockTextureInfo("acacia_log_top", "acacia_log")
    ),
    BlockInfo(
        Blocks.CHERRY_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.CHERRY,
        woodType = WoodType.CHERRY,
        horizontalModelIdentifier = Identifier("cherry_log_horizontal"),
        textureInfo = BlockTextureInfo("cherry_log_top", "cherry_log")
    ),
    BlockInfo(
        Blocks.DARK_OAK_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.DARK_OAK,
        woodType = WoodType.DARK_OAK,
        horizontalModelIdentifier = Identifier("dark_oak_log_horizontal"),
        textureInfo = BlockTextureInfo("dark_oak_log_top", "dark_oak_log")
    ),
    BlockInfo(
        Blocks.MANGROVE_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.MANGROVE,
        woodType = WoodType.MANGROVE,
        horizontalModelIdentifier = Identifier("mangrove_log_horizontal"),
        textureInfo = BlockTextureInfo("mangrove_log_top", "mangrove_log")
    ),
    BlockInfo(
        Blocks.MUDDY_MANGROVE_ROOTS,
        MiningTool.Shovel,
        textureInfo = BlockTextureInfo("muddy_mangrove_roots_top", "muddy_mangrove_roots_side")
    ),
    BlockInfo(
        Blocks.BAMBOO_BLOCK,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.BAMBOO,
        woodType = WoodType.BAMBOO,
        horizontalModelIdentifier = Identifier("bamboo_block_horizontal"),
        textureInfo = BlockTextureInfo("bamboo_block_top", "bamboo_block")
    ),
    BlockInfo(
        Blocks.STRIPPED_SPRUCE_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.SPRUCE,
        woodType = WoodType.SPRUCE,
        horizontalModelIdentifier = Identifier("stripped_spruce_log_horizontal"),
        textureInfo = BlockTextureInfo("stripped_spruce_log_top", "stripped_spruce_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_BIRCH_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.BIRCH,
        woodType = WoodType.BIRCH,
        horizontalModelIdentifier = Identifier("stripped_birch_log_horizontal"),
        textureInfo = BlockTextureInfo("stripped_birch_log_top", "stripped_birch_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_JUNGLE_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.JUNGLE,
        woodType = WoodType.JUNGLE,
        horizontalModelIdentifier = Identifier("stripped_jungle_log_horizontal"),
        textureInfo = BlockTextureInfo("stripped_jungle_log_top", "stripped_jungle_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_ACACIA_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.ACACIA,
        woodType = WoodType.ACACIA,
        horizontalModelIdentifier = Identifier("stripped_acacia_log_horizontal"),
        textureInfo = BlockTextureInfo("stripped_acacia_log_top", "stripped_acacia_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_CHERRY_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.CHERRY,
        woodType = WoodType.CHERRY,
        horizontalModelIdentifier = Identifier("stripped_cherry_log_horizontal"),
        textureInfo = BlockTextureInfo("stripped_cherry_log_top", "stripped_cherry_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_DARK_OAK_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.DARK_OAK,
        woodType = WoodType.DARK_OAK,
        horizontalModelIdentifier = Identifier("stripped_dark_oak_log_horizontal"),
        textureInfo = BlockTextureInfo("stripped_dark_oak_log_top", "stripped_dark_oak_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_OAK_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.OAK,
        woodType = WoodType.OAK,
        horizontalModelIdentifier = Identifier("stripped_oak_log_horizontal"),
        textureInfo = BlockTextureInfo("stripped_oak_log_top", "stripped_oak_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_MANGROVE_LOG,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.MANGROVE,
        woodType = WoodType.MANGROVE,
        horizontalModelIdentifier = Identifier("stripped_mangrove_log_horizontal"),
        textureInfo = BlockTextureInfo("stripped_mangrove_log_top", "stripped_mangrove_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_BAMBOO_BLOCK,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 300,
        blockSetType = BlockSetType.BAMBOO,
        woodType = WoodType.BAMBOO,
        horizontalModelIdentifier = Identifier("stripped_bamboo_block_horizontal"),
        textureInfo = BlockTextureInfo("stripped_bamboo_block_top", "stripped_bamboo_block")
    ),
    BlockInfo(
        Blocks.OAK_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.OAK,
        woodType = WoodType.OAK,
        textureInfo = BlockTextureInfo("oak_log")
    ),
    BlockInfo(
        Blocks.SPRUCE_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.SPRUCE,
        woodType = WoodType.SPRUCE,
        textureInfo = BlockTextureInfo("spruce_log")
    ),
    BlockInfo(
        Blocks.BIRCH_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.BIRCH,
        woodType = WoodType.BIRCH,
        textureInfo = BlockTextureInfo("birch_log")
    ),
    BlockInfo(
        Blocks.JUNGLE_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.JUNGLE,
        woodType = WoodType.JUNGLE,
        textureInfo = BlockTextureInfo("jungle_log")
    ),
    BlockInfo(
        Blocks.ACACIA_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.ACACIA,
        woodType = WoodType.ACACIA,
        textureInfo = BlockTextureInfo("acacia_log")
    ),
    BlockInfo(
        Blocks.CHERRY_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.CHERRY,
        woodType = WoodType.CHERRY,
        textureInfo = BlockTextureInfo("cherry_log")
    ),
    BlockInfo(
        Blocks.DARK_OAK_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.DARK_OAK,
        woodType = WoodType.DARK_OAK,
        textureInfo = BlockTextureInfo("dark_oak_log")
    ),
    BlockInfo(
        Blocks.MANGROVE_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.MANGROVE,
        woodType = WoodType.MANGROVE,
        textureInfo = BlockTextureInfo("mangrove_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_SPRUCE_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.SPRUCE,
        woodType = WoodType.SPRUCE,
        textureInfo = BlockTextureInfo("stripped_spruce_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_BIRCH_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.BIRCH,
        woodType = WoodType.BIRCH,
        textureInfo = BlockTextureInfo("stripped_birch_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_JUNGLE_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.JUNGLE,
        woodType = WoodType.JUNGLE,
        textureInfo = BlockTextureInfo("stripped_jungle_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_ACACIA_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.ACACIA,
        woodType = WoodType.ACACIA,
        textureInfo = BlockTextureInfo("stripped_acacia_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_CHERRY_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.CHERRY,
        woodType = WoodType.CHERRY,
        textureInfo = BlockTextureInfo("stripped_cherry_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_DARK_OAK_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.DARK_OAK,
        woodType = WoodType.DARK_OAK,
        textureInfo = BlockTextureInfo("stripped_dark_oak_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_OAK_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.OAK,
        woodType = WoodType.OAK,
        textureInfo = BlockTextureInfo("stripped_oak_log")
    ),
    BlockInfo(
        Blocks.STRIPPED_MANGROVE_WOOD,
        MiningTool.Axe,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        blockSetType = BlockSetType.MANGROVE,
        woodType = WoodType.MANGROVE,
        textureInfo = BlockTextureInfo("stripped_mangrove_log")
    ),
    BlockInfo(Blocks.SAND, MiningTool.Shovel),
    BlockInfo(Blocks.SUSPICIOUS_SAND, MiningTool.Shovel),
    BlockInfo(Blocks.RED_SAND, MiningTool.Shovel),
    BlockInfo(Blocks.GRAVEL, MiningTool.Shovel),
    BlockInfo(Blocks.SUSPICIOUS_GRAVEL, MiningTool.Shovel),
    BlockInfo(Blocks.GOLD_ORE, needsToolLevel = MiningToolLevel.Iron),
    BlockInfo(Blocks.DEEPSLATE_GOLD_ORE, needsToolLevel = MiningToolLevel.Iron),
    BlockInfo(Blocks.IRON_ORE, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.DEEPSLATE_IRON_ORE, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.COAL_ORE),
    BlockInfo(Blocks.DEEPSLATE_COAL_ORE),
    BlockInfo(Blocks.NETHER_GOLD_ORE),
    //            SPONGE, MiningTool.Hoe
    //        WET_SPONGE, MiningTool.Hoe
    BlockInfo(Blocks.GLASS),
    BlockInfo(Blocks.LAPIS_ORE, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.DEEPSLATE_LAPIS_ORE, needsToolLevel = MiningToolLevel.Stone),
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
    BlockInfo(Blocks.GOLD_BLOCK, needsToolLevel = MiningToolLevel.Iron, blockSetType = BlockSetType.GOLD),
    BlockInfo(Blocks.IRON_BLOCK, needsToolLevel = MiningToolLevel.Iron, blockSetType = BlockSetType.IRON),
    // TNT 15, 100
    // BOOKSHELF? , 30, 20
    BlockInfo(Blocks.OBSIDIAN, needsToolLevel = MiningToolLevel.Diamond),
    BlockInfo(Blocks.DIAMOND_ORE, needsToolLevel = MiningToolLevel.Iron),
    BlockInfo(Blocks.DEEPSLATE_DIAMOND_ORE, needsToolLevel = MiningToolLevel.Iron),
    BlockInfo(Blocks.DIAMOND_BLOCK, needsToolLevel = MiningToolLevel.Iron),
    //BlockInfo(Blocks.REDSTONE_ORE, needsToolLevel = MiningToolLevel.Iron),
    //BlockInfo(Blocks.DEEPSLATE_REDSTONE_ORE, needsToolLevel = MiningToolLevel.Iron),
    //SNOW, MiningTool.Shovel
    //ICE
    BlockInfo(Blocks.SNOW_BLOCK, MiningTool.Shovel, textureInfo = BlockTextureInfo("snow")),
    //CACTUS
    BlockInfo(Blocks.CLAY, MiningTool.Shovel),
    //NETHERRACK
    //SOUL_SAND, MiningTool.Shovel
    BlockInfo(Blocks.SOUL_SOIL, MiningTool.Shovel),
    BlockInfo(Blocks.BASALT, textureInfo = BlockTextureInfo("basalt_top", "basalt_side")),
    BlockInfo(Blocks.POLISHED_BASALT, textureInfo = BlockTextureInfo(
        "polished_basalt_top",
        "polished_basalt_side"
    )),
    BlockInfo(Blocks.GLOWSTONE),
    //CAKE compostability 1.0f,
    BlockInfo(Blocks.WHITE_STAINED_GLASS),
    BlockInfo(Blocks.ORANGE_STAINED_GLASS),
    BlockInfo(Blocks.MAGENTA_STAINED_GLASS),
    BlockInfo(Blocks.LIGHT_BLUE_STAINED_GLASS),
    BlockInfo(Blocks.YELLOW_STAINED_GLASS),
    BlockInfo(Blocks.LIME_STAINED_GLASS),
    BlockInfo(Blocks.PINK_STAINED_GLASS),
    BlockInfo(Blocks.GRAY_STAINED_GLASS),
    BlockInfo(Blocks.LIGHT_GRAY_STAINED_GLASS),
    BlockInfo(Blocks.CYAN_STAINED_GLASS),
    BlockInfo(Blocks.PURPLE_STAINED_GLASS),
    BlockInfo(Blocks.BLUE_STAINED_GLASS),
    BlockInfo(Blocks.BROWN_STAINED_GLASS),
    BlockInfo(Blocks.GREEN_STAINED_GLASS),
    BlockInfo(Blocks.RED_STAINED_GLASS),
    BlockInfo(Blocks.BLACK_STAINED_GLASS),
    BlockInfo(Blocks.CRACKED_STONE_BRICKS),
    BlockInfo(Blocks.CHISELED_STONE_BRICKS),
    BlockInfo(Blocks.PACKED_MUD),
    //BROWN_MUSHROOM_BLOCK compost 0.85f,
    //RED_MUSHROOM_BLOCK compost 0.85f,
    BlockInfo(Blocks.END_STONE),
    BlockInfo(Blocks.REDSTONE_LAMP),
    BlockInfo(Blocks.EMERALD_ORE, needsToolLevel = MiningToolLevel.Iron),
    BlockInfo(Blocks.DEEPSLATE_EMERALD_ORE, needsToolLevel = MiningToolLevel.Iron),
    BlockInfo(Blocks.EMERALD_BLOCK, needsToolLevel = MiningToolLevel.Iron),
    //REDSTONE_BLOCK
    BlockInfo(Blocks.NETHER_QUARTZ_ORE),
    BlockInfo(Blocks.QUARTZ_BLOCK, textureInfo = BlockTextureInfo(
        "quartz_block_top",
        "quartz_block_side",
        "quartz_block_bottom",
    )),
    BlockInfo(Blocks.CHISELED_QUARTZ_BLOCK, textureInfo = BlockTextureInfo(
        "chiseled_quartz_block_top",
        "chiseled_quartz_block"
    )),
    BlockInfo(
        Blocks.QUARTZ_PILLAR,
        horizontalModelIdentifier = Identifier("quartz_pillar_horizontal"),
        textureInfo = BlockTextureInfo(
            "quartz_pillar_top",
            "quartz_pillar"
        )
    ),
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
    BlockInfo(Blocks.SEA_LANTERN),
    //HAY_BLOCK, MiningTool.Hoe   60, 20   compost 0.85f,
    BlockInfo(
        Blocks.COAL_BLOCK,
        flammabilityBurnChance = 5,
        flammabilitySpreadChance = 5,
        itemFuel = 16000
    ),
    BlockInfo(Blocks.PACKED_ICE),
    BlockInfo(Blocks.RED_SANDSTONE, textureInfo = BlockTextureInfo(
        "red_sandstone_top",
        "red_sandstone",
        "red_sandstone_bottom"
    )),
    BlockInfo(Blocks.CHISELED_RED_SANDSTONE),
    BlockInfo(Blocks.CUT_RED_SANDSTONE),
    BlockInfo(Blocks.SMOOTH_STONE),
    BlockInfo(Blocks.SMOOTH_SANDSTONE, textureInfo = BlockTextureInfo("sandstone_top")),
    BlockInfo(Blocks.SMOOTH_QUARTZ, textureInfo = BlockTextureInfo("quartz_block_bottom")),
    BlockInfo(Blocks.SMOOTH_RED_SANDSTONE, textureInfo = BlockTextureInfo("red_sandstone_top")),
    BlockInfo(Blocks.PURPUR_BLOCK),
    BlockInfo(Blocks.PURPUR_PILLAR, horizontalModelIdentifier = Identifier("purpur_pillar_horizontal")),
    //FROSTED_ICE
    //MAGMA_BLOCK
    BlockInfo(Blocks.NETHER_WART_BLOCK, MiningTool.Hoe, itemCompostability = 0.85f),
    BlockInfo(Blocks.BONE_BLOCK, textureInfo = BlockTextureInfo(
        "bone_block_top",
        "bone_block_side",
        "bone_block_top"
    )),
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
    BlockInfo(Blocks.GREEN_GLAZED_TERRACOTTA, textureInfo = BlockTextureInfo("green_glazed_terracotta")),
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
        itemFuel = 4001,
        textureInfo = BlockTextureInfo("dried_kelp_top", "dried_kelp_side", "dried_kelp_bottom")
    ),
    //DEAD_TUBE_CORAL_BLOCK
    //etc?
    BlockInfo(Blocks.BLUE_ICE),
    BlockInfo(Blocks.WARPED_STEM, blockSetType = BlockSetType.WARPED, woodType = WoodType.WARPED),// non flammable
    BlockInfo(Blocks.STRIPPED_WARPED_STEM, blockSetType = BlockSetType.WARPED, woodType = WoodType.WARPED),// non flammable
    BlockInfo(
        Blocks.WARPED_HYPHAE,
        blockSetType = BlockSetType.WARPED,
        woodType = WoodType.WARPED,
        textureInfo = BlockTextureInfo("warped_stem")
    ),// non flammable
    BlockInfo(
        Blocks.STRIPPED_WARPED_HYPHAE,
        blockSetType = BlockSetType.WARPED,
        woodType = WoodType.WARPED,
        textureInfo = BlockTextureInfo("stripped_warped_stem")
    ),// non flammable
    BlockInfo(Blocks.WARPED_WART_BLOCK, MiningTool.Hoe, itemCompostability = 0.85f),
    BlockInfo(Blocks.CRIMSON_STEM, blockSetType = BlockSetType.CRIMSON, woodType = WoodType.CRIMSON),// non flammable
    BlockInfo(
        Blocks.STRIPPED_CRIMSON_STEM,
        blockSetType = BlockSetType.CRIMSON,
        woodType = WoodType.CRIMSON
    ),// non flammable
    BlockInfo(
        Blocks.CRIMSON_HYPHAE,
        blockSetType = BlockSetType.CRIMSON,
        woodType = WoodType.CRIMSON,
        textureInfo = BlockTextureInfo("crimson_stem")
    ),// non flammable
    BlockInfo(
        Blocks.STRIPPED_CRIMSON_HYPHAE,
        blockSetType = BlockSetType.CRIMSON,
        woodType = WoodType.CRIMSON,
        textureInfo = BlockTextureInfo("stripped_crimson_stem")
    ),// non flammable
    BlockInfo(Blocks.TARGET, MiningTool.Hoe,
        flammabilityBurnChance = 15,
        flammabilitySpreadChance = 20,
        textureInfo = BlockTextureInfo("target_top", "target_side")
    ),
    //HONEY_BLOCK
    BlockInfo(Blocks.HONEYCOMB_BLOCK),
    BlockInfo(Blocks.NETHERITE_BLOCK, needsToolLevel = MiningToolLevel.Diamond),
    BlockInfo(Blocks.ANCIENT_DEBRIS, needsToolLevel = MiningToolLevel.Diamond, textureInfo = BlockTextureInfo(
        "ancient_debris_top",
        "ancient_debris_side"
    )),
    //CRYING_OBSIDIAN, needsTool = MiningToolLevel.Diamond
    BlockInfo(Blocks.LODESTONE, textureInfo = BlockTextureInfo("lodestone_top", "lodestone_side")),
    BlockInfo(Blocks.BLACKSTONE),
    BlockInfo(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS),
    BlockInfo(Blocks.CHISELED_POLISHED_BLACKSTONE),
    BlockInfo(Blocks.POLISHED_BLACKSTONE_BRICKS),
    BlockInfo(Blocks.POLISHED_BLACKSTONE, blockSetType = BlockSetType.POLISHED_BLACKSTONE),
    BlockInfo(Blocks.GILDED_BLACKSTONE),
    BlockInfo(Blocks.CHISELED_NETHER_BRICKS),
    BlockInfo(Blocks.CRACKED_NETHER_BRICKS),
    BlockInfo(Blocks.QUARTZ_BRICKS),
    BlockInfo(Blocks.AMETHYST_BLOCK),
    BlockInfo(Blocks.TUFF),
    BlockInfo(Blocks.CALCITE),
    //POWDER_SNOW
    //SCULK, MiningTool.Hoe
    BlockInfo(Blocks.OXIDIZED_COPPER, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.WEATHERED_COPPER, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.EXPOSED_COPPER, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.COPPER_BLOCK, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.COPPER_ORE, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.DEEPSLATE_COPPER_ORE, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.OXIDIZED_CUT_COPPER, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.WEATHERED_CUT_COPPER, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.EXPOSED_CUT_COPPER, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.CUT_COPPER, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(
        Blocks.WAXED_COPPER_BLOCK,
        needsToolLevel = MiningToolLevel.Stone,
        modelIdentifier = Identifier("copper_block")
    ),
    BlockInfo(
        Blocks.WAXED_WEATHERED_COPPER,
        needsToolLevel = MiningToolLevel.Stone,
        modelIdentifier = Identifier("weathered_copper")
    ),
    BlockInfo(
        Blocks.WAXED_EXPOSED_COPPER,
        needsToolLevel = MiningToolLevel.Stone,
        modelIdentifier = Identifier("exposed_copper")
    ),
    BlockInfo(
        Blocks.WAXED_OXIDIZED_COPPER,
        needsToolLevel = MiningToolLevel.Stone,
        modelIdentifier = Identifier("oxidized_copper")
    ),
    BlockInfo(
        Blocks.WAXED_OXIDIZED_CUT_COPPER,
        needsToolLevel = MiningToolLevel.Stone,
        modelIdentifier = Identifier("oxidized_cut_copper")
    ),
    BlockInfo(
        Blocks.WAXED_WEATHERED_CUT_COPPER,
        needsToolLevel = MiningToolLevel.Stone,
        modelIdentifier = Identifier("weathered_cut_copper")
    ),
    BlockInfo(
        Blocks.WAXED_EXPOSED_CUT_COPPER,
        needsToolLevel = MiningToolLevel.Stone,
        modelIdentifier = Identifier("exposed_cut_copper")
    ),
    BlockInfo(
        Blocks.WAXED_CUT_COPPER,
        needsToolLevel = MiningToolLevel.Stone,
        modelIdentifier = Identifier("cut_copper")
    ),
    BlockInfo(Blocks.DRIPSTONE_BLOCK),
    //MOSS_BLOCK, MiningTool.Hoe compost 0.65f
    //MUD, MiningTool.Shovel
    BlockInfo(Blocks.DEEPSLATE),
    BlockInfo(Blocks.COBBLED_DEEPSLATE),
    BlockInfo(Blocks.POLISHED_DEEPSLATE),
    BlockInfo(Blocks.DEEPSLATE_TILES),
    BlockInfo(Blocks.DEEPSLATE_BRICKS),
    BlockInfo(Blocks.CHISELED_DEEPSLATE),
    BlockInfo(Blocks.CRACKED_DEEPSLATE_BRICKS),
    BlockInfo(Blocks.CRACKED_DEEPSLATE_TILES),
    BlockInfo(Blocks.SMOOTH_BASALT),
    BlockInfo(Blocks.RAW_IRON_BLOCK, needsToolLevel = MiningToolLevel.Stone, blockSetType = BlockSetType.IRON),
    BlockInfo(Blocks.RAW_COPPER_BLOCK, needsToolLevel = MiningToolLevel.Stone),
    BlockInfo(Blocks.RAW_GOLD_BLOCK, needsToolLevel = MiningToolLevel.Iron, blockSetType = BlockSetType.GOLD),
    BlockInfo(
        Blocks.OCHRE_FROGLIGHT,
        horizontalModelIdentifier = Identifier("ochre_froglight_horizontal"),
        textureInfo = BlockTextureInfo("ochre_froglight_top", "ochre_froglight_side")
    ),
    BlockInfo(
        Blocks.VERDANT_FROGLIGHT,
        horizontalModelIdentifier = Identifier("verdant_froglight_horizontal"),
        textureInfo = BlockTextureInfo("verdant_froglight_top", "verdant_froglight_side")
    ),
    BlockInfo(
        Blocks.PEARLESCENT_FROGLIGHT,
        horizontalModelIdentifier = Identifier("pearlescent_froglight_horizontal"),
        textureInfo = BlockTextureInfo("pearlescent_froglight_top", "pearlescent_froglight_side")
    ),
    BlockInfo(Blocks.REINFORCED_DEEPSLATE, textureInfo = BlockTextureInfo(
        "reinforced_deepslate_top",
        "reinforced_deepslate_side",
        "reinforced_deepslate_bottom"
    ))
).associateBy() { it.block }

val POTTED_BLOCK_INFOS = arrayOf(
    BlockInfo(Blocks.POTTED_OAK_SAPLING),
    BlockInfo(Blocks.POTTED_SPRUCE_SAPLING),
    BlockInfo(Blocks.POTTED_BIRCH_SAPLING),
    BlockInfo(Blocks.POTTED_JUNGLE_SAPLING),
    BlockInfo(Blocks.POTTED_ACACIA_SAPLING),
    BlockInfo(Blocks.POTTED_CHERRY_SAPLING),
    BlockInfo(Blocks.POTTED_DARK_OAK_SAPLING),
    BlockInfo(Blocks.POTTED_MANGROVE_PROPAGULE),
    BlockInfo(Blocks.POTTED_FERN),
    BlockInfo(Blocks.POTTED_DANDELION),
    BlockInfo(Blocks.POTTED_POPPY),
    BlockInfo(Blocks.POTTED_BLUE_ORCHID),
    BlockInfo(Blocks.POTTED_ALLIUM),
    BlockInfo(Blocks.POTTED_AZURE_BLUET),
    BlockInfo(Blocks.POTTED_RED_TULIP),
    BlockInfo(Blocks.POTTED_ORANGE_TULIP),
    BlockInfo(Blocks.POTTED_WHITE_TULIP),
    BlockInfo(Blocks.POTTED_PINK_TULIP),
    BlockInfo(Blocks.POTTED_OXEYE_DAISY),
    BlockInfo(Blocks.POTTED_CORNFLOWER),
    BlockInfo(Blocks.POTTED_LILY_OF_THE_VALLEY),
    BlockInfo(Blocks.POTTED_WITHER_ROSE),
    BlockInfo(Blocks.POTTED_RED_MUSHROOM),
    BlockInfo(Blocks.POTTED_BROWN_MUSHROOM),
    BlockInfo(Blocks.POTTED_DEAD_BUSH),
    BlockInfo(Blocks.POTTED_CACTUS),
    BlockInfo(Blocks.POTTED_BAMBOO),
    BlockInfo(Blocks.POTTED_CRIMSON_FUNGUS),
    BlockInfo(Blocks.POTTED_WARPED_FUNGUS),
    BlockInfo(Blocks.POTTED_CRIMSON_ROOTS),
    BlockInfo(Blocks.POTTED_WARPED_ROOTS),
    BlockInfo(Blocks.POTTED_AZALEA_BUSH),
    BlockInfo(Blocks.POTTED_FLOWERING_AZALEA_BUSH),
)

val SIGN_BLOCK_INFOS = arrayOf(
    BlockInfo(Blocks.OAK_SIGN, itemFuel = 200),
    BlockInfo(Blocks.SPRUCE_SIGN, itemFuel = 200),
    BlockInfo(Blocks.BIRCH_SIGN, itemFuel = 200),
    BlockInfo(Blocks.JUNGLE_SIGN, itemFuel = 200),
    BlockInfo(Blocks.ACACIA_SIGN, itemFuel = 200),
    BlockInfo(Blocks.CHERRY_SIGN, itemFuel = 200),
    BlockInfo(Blocks.DARK_OAK_SIGN, itemFuel = 200),
    BlockInfo(Blocks.MANGROVE_SIGN, itemFuel = 200),
    BlockInfo(Blocks.CRIMSON_SIGN, itemFuel = 200),
    BlockInfo(Blocks.WARPED_SIGN, itemFuel = 200)
).associateBy() { it.block }