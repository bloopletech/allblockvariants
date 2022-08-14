package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.GlassThinVerticalSlabBlock
import net.bloople.allblockvariants.blocks.StainedGlassThinVerticalSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.Stainable
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import java.awt.image.BufferedImage


class GlassThinVerticalSlabCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = AdvancedDerivedBlockInfo(blockInfo) {
        Pair(
            "${transformBlockName(existingBlockName)}_thin_vertical_slab",
            "${transformBlockName(existingBlockName)}_vertical_slab"
        )
    }

    override fun doCreateCommon() {
        with(dbi) {
            val bSettings = existingBlock.copySettings().nonOpaque()
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                if(existingBlock is Stainable) StainedGlassThinVerticalSlabBlock(existingBlock.color, bSettings)
                else GlassThinVerticalSlabBlock(bSettings)
            )

            Registry.register(
                Registry.ITEM,
                identifier,
                BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
            )
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent())

            builder.addBlockTexture("${blockName}_top") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createTopTexture)
            }

            builder.addBlockTexture("${blockName}_bottom") { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(
                    builder.getBlockTexture("${blockName}_top"),
                    ::createFlippedTexture)
            }

            builder.addBlockTexture("${blockName}_side") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createSideTexture)
            }

            builder.addBlockTexture("${blockName}_left_east") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createLeftEastTexture)
            }

            builder.addBlockTexture("${blockName}_right_east") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createRightEastTexture)
            }

            builder.addBlockTexture("${blockName}_left_south") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createLeftSouthTexture)
            }

            builder.addBlockTexture("${blockName}_right_south") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createRightSouthTexture)
            }

            builder.addBlockTexture("${blockName}_corner_north_west_top") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createCornerNorthWestTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_north_east_top") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createCornerNorthEastTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_south_east_top") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createCornerSouthEastTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_south_west_top") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createCornerSouthWestTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_north_west_bottom") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createCornerNorthWestBottomTexture)
            }

            builder.addBlockTexture("${blockName}_corner_north_east_bottom") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createCornerNorthEastBottomTexture)
            }

            builder.addBlockTexture("${blockName}_corner_south_east_bottom") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createCornerSouthEastBottomTexture)
            }

            builder.addBlockTexture("${blockName}_corner_south_west_bottom") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createCornerSouthWestBottomTexture)
            }

            val blockState = """
                {
                   "variants": {
                     "facing=east,shape=straight,type=left": {
                       "model": "$blockBlockId"
                     },
                     "facing=east,shape=straight,type=right": {
                       "model": "${blockBlockId}_right"
                     },
                     "facing=east,shape=north_west": {
                       "model": "${blockBlockId}_north_west"
                     },
                     "facing=east,shape=north_east": {
                       "model": "${blockBlockId}_north_east"
                     },
                     "facing=east,shape=south_east": {
                       "model": "${blockBlockId}_south_east"
                     },
                     "facing=east,shape=south_west": {
                       "model": "${blockBlockId}_south_west"
                     },
                     "facing=south,shape=straight,type=left": {
                       "model": "$blockBlockId",
                       "y": 90
                     },
                     "facing=south,shape=straight,type=right": {
                       "model": "${blockBlockId}_right",
                       "y": 90
                     },
                     "facing=south,shape=north_west": {
                       "model": "${blockBlockId}_north_west",
                       "y": 90
                     },
                     "facing=south,shape=north_east": {
                       "model": "${blockBlockId}_north_east",
                       "y": 90
                     },
                     "facing=south,shape=south_east": {
                       "model": "${blockBlockId}_south_east",
                       "y": 90
                     },
                     "facing=south,shape=south_west": {
                       "model": "${blockBlockId}_south_west",
                       "y": 90
                     },
                     "facing=west,shape=straight,type=left": {
                       "model": "$blockBlockId",
                       "y": 180
                     },
                     "facing=west,shape=straight,type=right": {
                       "model": "${blockBlockId}_right",
                       "y": 180
                     },
                     "facing=west,shape=north_west": {
                       "model": "${blockBlockId}_north_west",
                       "y": 180
                     },
                     "facing=west,shape=north_east": {
                       "model": "${blockBlockId}_north_east",
                       "y": 180
                     },
                     "facing=west,shape=south_east": {
                       "model": "${blockBlockId}_south_east",
                       "y": 180
                     },
                     "facing=west,shape=south_west": {
                       "model": "${blockBlockId}_south_west",
                       "y": 180
                     },
                     "facing=north,shape=straight,type=left": {
                       "model": "$blockBlockId",
                       "y": 270
                     },
                     "facing=north,shape=straight,type=right": {
                       "model": "${blockBlockId}_right",
                       "y": 270
                     },
                     "facing=north,shape=north_west": {
                       "model": "${blockBlockId}_north_west",
                       "y": 270
                     },
                     "facing=north,shape=north_east": {
                       "model": "${blockBlockId}_north_east",
                       "y": 270
                     },
                     "facing=north,shape=south_east": {
                       "model": "${blockBlockId}_south_east",
                       "y": 270
                     },
                     "facing=north,shape=south_west": {
                       "model": "${blockBlockId}_south_west",
                       "y": 270
                     }
                   }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${blockBlockId}_top",
                        "north": "$existingBlockNorthTextureId",
                        "east": "${blockBlockId}_side",
                        "south": "$existingBlockSouthTextureId",
                        "west": "${blockBlockId}_side",
                        "bottom": "${blockBlockId}_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 16, 4, 0, 0 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val rightBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${blockBlockId}_top",
                        "north": "$existingBlockNorthTextureId",
                        "east": "${blockBlockId}_side",
                        "south": "$existingBlockSouthTextureId",
                        "west": "${blockBlockId}_side",
                        "bottom": "${blockBlockId}_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 16, 16, 0, 12 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_right", rightBlockModel)

            val northWestBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${blockBlockId}_corner_north_west_top",
                        "north": "$existingBlockNorthTextureId",
                        "left_east": "${blockBlockId}_left_east",
                        "right_east": "${blockBlockId}_right_east",
                        "left_south": "${blockBlockId}_left_south",
                        "right_south": "${blockBlockId}_right_south",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "${blockBlockId}_corner_north_west_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 0, 12, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#left_south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#left_east" }
                            }
                        },
                        {   "from": [ 0, 0, 4 ],
                            "to": [ 4, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 4, 12 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 4, 4, 16 ], "texture": "#top" },
                                "south": { "uv": [ 0, 0, 4, 16 ], "texture": "#right_south" },
                                "west":  { "uv": [ 4, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 12, 16 ], "texture": "#right_east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_west", northWestBlockModel)

            val northEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${blockBlockId}_corner_north_east_top",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "left_south": "${blockBlockId}_right_east",
                        "right_south": "${blockBlockId}_left_east",
                        "left_west": "${blockBlockId}_right_south",
                        "right_west": "${blockBlockId}_left_south",
                        "bottom": "${blockBlockId}_corner_north_east_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 0, 12, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#left_south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#left_west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 12, 0, 4 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 12, 0, 16, 12 ], "texture": "#bottom" },
                                "up":    { "uv": [ 12, 4, 16, 16 ], "texture": "#top" },
                                "south": { "uv": [ 12, 0, 16, 16 ], "texture": "#right_south" },
                                "west":  { "uv": [ 4, 0, 16, 16 ], "texture": "#right_west" },
                                "east":  { "uv": [ 0, 0, 12, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_east", northEastBlockModel)

            val southEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${blockBlockId}_corner_south_east_top",
                        "left_north": "${blockBlockId}_right_south",
                        "right_north": "${blockBlockId}_left_south",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "left_west": "${blockBlockId}_right_east",
                        "right_west": "${blockBlockId}_left_east",
                        "bottom": "${blockBlockId}_corner_south_east_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 12, 0, 0 ],
                            "to": [ 16, 16, 12 ],
                            "faces": {
                                "down":  { "uv": [ 12, 4, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 12, 0, 16, 12 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 4, 16 ], "texture": "#left_north" },
                                "west":  { "uv": [ 0, 0, 12, 16 ], "texture": "#left_west" },
                                "east":  { "uv": [ 4, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 4 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#right_north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#right_west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_south_east", southEastBlockModel)

            val southWestBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${blockBlockId}_corner_south_west_top",
                        "left_north": "${blockBlockId}_left_east",
                        "right_north": "${blockBlockId}_right_east",
                        "north": "$existingBlockNorthTextureId",
                        "left_east": "${blockBlockId}_left_south",
                        "right_east": "${blockBlockId}_right_south",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "${blockBlockId}_corner_south_west_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 4, 16, 12 ],
                            "faces": {
                                "down":  { "uv": [ 0, 4, 4, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 4, 12 ], "texture": "#top" },
                                "north": { "uv": [ 12, 0, 16, 16 ], "texture": "#left_north" },
                                "west":  { "uv": [ 0, 0, 12, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 4, 0, 16, 16 ], "texture": "#left_east" }
                            }
                        },
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 4 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#right_north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#right_east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_south_west", southWestBlockModel)

            val itemModel = """
                {
                  "parent": "$blockBlockId",
                  "display": {
                    "gui": {
                      "rotation": [30, 45, 0],
                      "translation": [0, 0, 0],
                      "scale": [0.625, 0.625, 0.625]
                    }
                  }
                }
            """.trimIndent()
            builder.addItemModel(blockName, itemModel)

            builder.addTranslation("block.$MOD_ID.$blockName", Util.toTitleCase(blockName))
        }
    }

    override fun doCreateServer(builder: ResourcePackBuilder) {
        registerBlockCommon(builder)

        with(dbi) {
            val lootTable = """
                {
                  "type": "minecraft:block",
                  "pools": [
                    {
                      "bonus_rolls": 0.0,
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "functions": [
                            {
                              "add": false,
                              "conditions": [
                                {
                                  "block": "$identifier",
                                  "condition": "minecraft:block_state_property",
                                  "properties": {
                                    "type": "double"
                                  }
                                }
                              ],
                              "count": 2.0,
                              "function": "minecraft:set_count"
                            },
                            {
                              "function": "minecraft:explosion_decay"
                            }
                          ],
                          "name": "$identifier"
                        }
                      ],
                      "rolls": 1.0
                    }
                  ]
                }
            """.trimIndent()
            builder.addBlockLootTable(blockName, lootTable)

            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "key": {
                    "#": {
                      "item": "$parentIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.identifier}"
                    }
                  },
                  "pattern": [
                    "# ",
                    "# ",
                    "#!"
                  ],
                  "result": {
                    "count": 6,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            val stonecuttingRecipe = """
                {
                  "type": "minecraft:stonecutting",
                  "count": 4,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_existing_stonecutting", stonecuttingRecipe)

            val parentStonecuttingRecipe = """
                {
                  "type": "minecraft:stonecutting",
                  "count": 4,
                  "ingredient": {
                    "item": "$parentIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_parent_stonecutting", parentStonecuttingRecipe)

            builder.addTag("slabs", identifier.toString())
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "key": {
                    "#": {
                      "item": "$parentIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.identifier}"
                    }
                  },
                  "pattern": [
                    "# ",
                    "# ",
                    "#!"
                  ],
                  "result": {
                    "count": 6,
                    "item": "$vanillaIdentifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createSideTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val topRow = getData(0, 0, 16, 1)
            val bottomRow = getData(0, 15, 16, 1)

            raster.setRect(0, 12, topRow)
            raster.setRect(0, 3, bottomRow)
        }.rotateImage(90.0).flipImage(ImageFlipMode.LEFT_RIGHT)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createTopTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val rightRow = getData(15, 0, 1, 16)
            val leftRow = getData(0, 0, 1, 16)

            raster.setRect(3, 0, rightRow)
            raster.setRect(12, 0, leftRow)
        }.rotateImage(90.0)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createFlippedTexture(input: BufferedImage): BufferedImage {
        return input.flipImage(ImageFlipMode.LEFT_RIGHT)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createLeftEastTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            val leftRow = getData(0, 0, 1, 16)

            raster.setRect(12, 0, leftRow)
            raster.setRect(0, 0, blank.getData(0, 0, 12, 16))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createRightEastTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            val rightRow = getData(15, 0, 1, 16)

            raster.setRect(11, 0, rightRow)
            raster.setRect(12, 0, blank.getData(12, 0, 4, 16))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createLeftSouthTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            val leftRow = getData(0, 0, 1, 16)

            raster.setRect(4, 0, leftRow)
            raster.setRect(0, 0, blank.getData(0, 0, 4, 16))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createRightSouthTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            val rightRow = getData(15, 0, 1, 16)

            raster.setRect(3, 0, rightRow)
            raster.setRect(4, 0, blank.getData(4, 0, 12, 16))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerNorthWestTopTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(90.0).apply {
            val blank = blankClone()

            val rightRow = getData(15, 3, 1, 13)
            val bottomRow = getData(3, 15, 13, 1)

            raster.setRect(3, 3, bottomRow)
            raster.setRect(3, 3, rightRow)
            raster.setRect(4, 4, blank.getData(4, 4, 12, 12))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerNorthEastTopTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(90.0).apply {
            val leftRow = getData(0, 7, 1, 9)
            val bottomRow = getData(0, 15, 9, 1)

            raster.setRect(0, 7, bottomRow)
            raster.setRect(8, 7, leftRow)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerSouthEastTopTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(90.0).apply {
            val leftRow = getData(0, 0, 1, 9)
            val topRow = getData(0, 0, 9, 1)

            raster.setRect(0, 8, topRow)
            raster.setRect(8, 0, leftRow)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerSouthWestTopTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(90.0).apply {
            val blank = blankClone()

            val rightRow = getData(15, 7, 1, 9)
            val bottomRow = getData(7, 15, 9, 1)

            raster.setRect(7, 8, bottomRow)
            raster.setRect(7, 0, rightRow)

        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerNorthWestBottomTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(180.0).apply {
            val blank = blankClone()

            val rightRow = getData(15, 7, 1, 9)
            val topRow = getData(7, 15, 9, 1)

            raster.setRect(7, 8, topRow)
            raster.setRect(7, 0, rightRow)
            raster.setRect(8, 0, blank.getData(8, 0, 8, 8))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerNorthEastBottomTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(180.0).apply {
            val blank = blankClone()

            val leftRow = getData(0, 0, 1, 9)
            val topRow = getData(0, 0, 9, 1)

            raster.setRect(0, 8, topRow)
            raster.setRect(8, 0, leftRow)
            raster.setRect(0, 0, blank.getData(0, 0, 8, 8))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerSouthEastBottomTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(180.0).apply {
            val blank = blankClone()

            val leftRow = getData(0, 7, 1, 9)
            val bottomRow = getData(0, 15, 9, 1)

            raster.setRect(0, 7, bottomRow)
            raster.setRect(8, 7, leftRow)
            raster.setRect(0, 8, blank.getData(0, 8, 8, 8))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerSouthWestBottomTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(180.0).apply {
            val blank = blankClone()

            val rightRow = getData(15, 7, 1, 9)
            val bottomRow = getData(7, 15, 9, 1)

            raster.setRect(7, 7, bottomRow)
            raster.setRect(7, 7, rightRow)
            raster.setRect(8, 8, blank.getData(8, 8, 8, 8))
        }
    }
}
