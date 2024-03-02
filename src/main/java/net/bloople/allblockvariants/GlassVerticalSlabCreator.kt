package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.GlassVerticalSlabBlock
import net.bloople.allblockvariants.blocks.StainedGlassVerticalSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.Stainable
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import java.awt.image.BufferedImage


class GlassVerticalSlabCreator(metrics: Metrics, blockInfo: BlockInfo) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_vertical_slab" }

    override fun doCreateCommon() {
        with(dbi) {
            val bSettings = existingBlock.copySettings().nonOpaque()
            registerBlock(when(existingBlock) {
                is Stainable -> StainedGlassVerticalSlabBlock(existingBlock.color, bSettings)
                else -> GlassVerticalSlabBlock(bSettings)
            })

            registerItem(BlockItem(block, Item.Settings()), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent())

            builder.addBlockTexture("${blockName}_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createTopTexture)
            }

            builder.addBlockTexture("${blockName}_bottom") { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(
                    builder.getBlockTexture("${blockName}_top"),
                    ::createFlippedTexture)
            }

            builder.addBlockTexture("${blockName}_side") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createSideTexture)
            }

            builder.addBlockTexture("${blockName}_left_south") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createLeftSouthTexture)
            }

            builder.addBlockTexture("${blockName}_right_south") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createRightSouthTexture)
            }

            builder.addBlockTexture("${blockName}_corner_north_west_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerNorthWestTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_north_east_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerNorthEastTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_south_east_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerSouthEastTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_south_west_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerSouthWestTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_north_west_bottom") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerNorthWestBottomTexture)
            }

            builder.addBlockTexture("${blockName}_corner_north_east_bottom") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerNorthEastBottomTexture)
            }

            builder.addBlockTexture("${blockName}_corner_south_east_bottom") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerSouthEastBottomTexture)
            }

            builder.addBlockTexture("${blockName}_corner_south_west_bottom") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
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
                            "to": [ 16, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 16, 8, 0, 0 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 8 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#east", "cullface": "east" }
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
                        {   "from": [ 0, 0, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 16, 16, 0, 8 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 8, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#east", "cullface": "east" }
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
                        "left_east": "${blockBlockId}_left_south",
                        "right_east": "${blockBlockId}_right_south",
                        "left_south": "${blockBlockId}_left_south",
                        "right_south": "${blockBlockId}_right_south",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "${blockBlockId}_corner_north_west_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 0, 8, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 8 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#left_south" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#left_east" }
                            }
                        },
                        {   "from": [ 0, 0, 8 ],
                            "to": [ 8, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 8, 8 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 8, 8, 16 ], "texture": "#top" },
                                "south": { "uv": [ 0, 0, 8, 16 ], "texture": "#right_south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#right_east" }
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
                        "left_south": "${blockBlockId}_right_south",
                        "right_south": "${blockBlockId}_left_south",
                        "left_west": "${blockBlockId}_right_south",
                        "right_west": "${blockBlockId}_left_south",
                        "bottom": "${blockBlockId}_corner_north_east_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 0, 8, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 8 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#left_south" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#left_west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 8, 0, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 8, 0, 16, 8 ], "texture": "#bottom" },
                                "up":    { "uv": [ 8, 8, 16, 16 ], "texture": "#top" },
                                "south": { "uv": [ 8, 0, 16, 16 ], "texture": "#right_south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#right_west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#east" }
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
                        "left_west": "${blockBlockId}_right_south",
                        "right_west": "${blockBlockId}_left_south",
                        "bottom": "${blockBlockId}_corner_south_east_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 8, 0, 0 ],
                            "to": [ 16, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 8, 8, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 8, 0, 16, 8 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 8, 16 ], "texture": "#left_north" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#left_west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 8 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 8, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#right_north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#right_west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#east" }
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
                        "left_north": "${blockBlockId}_left_south",
                        "right_north": "${blockBlockId}_right_south",
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
                            "to": [ 8, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 0, 8, 8, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 8, 8 ], "texture": "#top" },
                                "north": { "uv": [ 8, 0, 16, 16 ], "texture": "#left_north" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#left_east" }
                            }
                        },
                        {   "from": [ 0, 0, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 8 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 8, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#right_north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#right_east" }
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
                      "item": "$existingIdentifier"
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
                  "count": 2,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_stonecutting", stonecuttingRecipe)
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "key": {
                    "#": {
                      "item": "$existingIdentifier"
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

            raster.setRect(0, 8, topRow)
            raster.setRect(0, 7, bottomRow)
        }.rotate90().flipImage(ImageFlipMode.LEFT_RIGHT)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createTopTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val rightRow = getData(15, 0, 1, 16)
            val leftRow = getData(0, 0, 1, 16)

            raster.setRect(7, 0, rightRow)
            raster.setRect(8, 0, leftRow)
        }.rotate90()
    }

    @Environment(value=EnvType.CLIENT)
    private fun createFlippedTexture(input: BufferedImage): BufferedImage {
        return input.flipImage(ImageFlipMode.LEFT_RIGHT)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createLeftSouthTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            val leftRow = getData(0, 0, 1, 16)

            raster.setRect(8, 0, leftRow)
            raster.setRect(0, 0, blank.getData(0, 0, 8, 16))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createRightSouthTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            val rightRow = getData(15, 0, 1, 16)

            raster.setRect(7, 0, rightRow)
            raster.setRect(8, 0, blank.getData(8, 0, 8, 16))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerNorthWestTopTexture(input: BufferedImage): BufferedImage {
        return input.rotate90().apply {
            val blank = blankClone()

            val rightRow = getData(15, 7, 1, 9)
            val bottomRow = getData(7, 15, 9, 1)

            raster.setRect(7, 7, bottomRow)
            raster.setRect(7, 7, rightRow)
            raster.setRect(8, 8, blank.getData(8, 8, 8, 8))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerNorthEastTopTexture(input: BufferedImage): BufferedImage {
        return input.rotate90().apply {
            val leftRow = getData(0, 7, 1, 9)
            val bottomRow = getData(0, 15, 9, 1)

            raster.setRect(0, 7, bottomRow)
            raster.setRect(8, 7, leftRow)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerSouthEastTopTexture(input: BufferedImage): BufferedImage {
        return input.rotate90().apply {
            val leftRow = getData(0, 0, 1, 9)
            val topRow = getData(0, 0, 9, 1)

            raster.setRect(0, 8, topRow)
            raster.setRect(8, 0, leftRow)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerSouthWestTopTexture(input: BufferedImage): BufferedImage {
        return input.rotate90().apply {
            val blank = blankClone()

            val rightRow = getData(15, 7, 1, 9)
            val bottomRow = getData(7, 15, 9, 1)

            raster.setRect(7, 8, bottomRow)
            raster.setRect(7, 0, rightRow)

        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerNorthWestBottomTexture(input: BufferedImage): BufferedImage {
        return input.rotate180().apply {
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
        return input.rotate180().apply {
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
        return input.rotate180().apply {
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
        return input.rotate180().apply {
            val blank = blankClone()

            val rightRow = getData(15, 7, 1, 9)
            val bottomRow = getData(7, 15, 9, 1)

            raster.setRect(7, 7, bottomRow)
            raster.setRect(7, 7, rightRow)
            raster.setRect(8, 8, blank.getData(8, 8, 8, 8))
        }
    }
}
