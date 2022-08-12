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
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import java.awt.image.BufferedImage


class GlassVerticalSlabCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_vertical_slab" }

    override fun doCreateCommon() {
        with(dbi) {
            val bSettings = existingBlock.copySettings().nonOpaque()
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                if(existingBlock is Stainable) StainedGlassVerticalSlabBlock(existingBlock.color, bSettings)
                else GlassVerticalSlabBlock(bSettings)
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
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 0, 8, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 8 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 8 ],
                            "to": [ 8, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 8, 8 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 8, 8, 16 ], "texture": "#top" },
                                "south": { "uv": [ 0, 0, 8, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_west", northWestBlockModel)

            val northEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 0, 8, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 8 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 8, 0, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 8, 0, 16, 8 ], "texture": "#bottom" },
                                "up":    { "uv": [ 8, 8, 16, 16 ], "texture": "#top" },
                                "south": { "uv": [ 8, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#west" },
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
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 8, 0, 0 ],
                            "to": [ 16, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 8, 8, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 8, 0, 16, 8 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 8, 16 ], "texture": "#north" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 8 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 8, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#west" },
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
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 8, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 0, 8, 8, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 8, 8 ], "texture": "#top" },
                                "north": { "uv": [ 8, 0, 16, 16 ], "texture": "#north" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 8 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 8, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#east" }
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
        }.rotateImage(90.0).flipImage(ImageFlipMode.LEFT_RIGHT)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createTopTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val rightRow = getData(15, 0, 1, 16)
            val leftRow = getData(0, 0, 1, 16)

            raster.setRect(7, 0, rightRow)
            raster.setRect(8, 0, leftRow)
        }.rotateImage(90.0)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createFlippedTexture(input: BufferedImage): BufferedImage {
        return input.flipImage(ImageFlipMode.LEFT_RIGHT)
    }
}
