package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.VerticalSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.AbstractBlock
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import java.awt.image.BufferedImage

class VerticalSlabCreator(blockInfo: BlockInfo) :
    BlockCreator(DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_vertical_slab" }) {
    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                VerticalSlabBlock(AbstractBlock.Settings.copy(existingBlock))
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
            builder.addBlockTexture("${blockName}_north_west_top") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createNorthWestTopTexture)
            }

            builder.addBlockTexture("${blockName}_north_west_bottom") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createNorthWestBottomTexture)
            }

            builder.addBlockTexture("${blockName}_north_west_east") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createNorthWestEastTexture)
            }

            builder.addBlockTexture("${blockName}_north_west_south") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createNorthWestSouthTexture)
            }

            val blockState = """
                {
                   "variants": {
                     "facing=east,type=left": {
                       "model": "$blockBlockId"
                     },
                     "facing=east,type=right": {
                       "model": "${blockBlockId}_right"
                     },
                     "facing=east,type=north_west": {
                       "model": "${blockBlockId}_north_west"
                     },
                     "facing=east,type=north_east": {
                       "model": "${blockBlockId}_north_east"
                     },
                     "facing=east,type=south_east": {
                       "model": "${blockBlockId}_south_east"
                     },
                     "facing=east,type=south_west": {
                       "model": "${blockBlockId}_south_west"
                     },
                     "facing=south,type=left": {
                       "model": "$blockBlockId",
                       "y": 90
                     },
                     "facing=south,type=right": {
                       "model": "${blockBlockId}_right",
                       "y": 90
                     },
                     "facing=south,type=north_west": {
                       "model": "${blockBlockId}_north_west",
                       "y": 90
                     },
                     "facing=south,type=north_east": {
                       "model": "${blockBlockId}_north_east",
                       "y": 90
                     },
                     "facing=south,type=south_east": {
                       "model": "${blockBlockId}_south_east",
                       "y": 90
                     },
                     "facing=south,type=south_west": {
                       "model": "${blockBlockId}_south_west",
                       "y": 90
                     },
                     "facing=west,type=left": {
                       "model": "$blockBlockId",
                       "y": 180
                     },
                     "facing=west,type=right": {
                       "model": "${blockBlockId}_right",
                       "y": 180
                     },
                     "facing=west,type=north_west": {
                       "model": "${blockBlockId}_north_west",
                       "y": 180
                     },
                     "facing=west,type=north_east": {
                       "model": "${blockBlockId}_north_east",
                       "y": 180
                     },
                     "facing=west,type=south_east": {
                       "model": "${blockBlockId}_south_east",
                       "y": 180
                     },
                     "facing=west,type=south_west": {
                       "model": "${blockBlockId}_south_west",
                       "y": 180
                     },
                     "facing=north,type=left": {
                       "model": "$blockBlockId",
                       "y": 270
                     },
                     "facing=north,type=right": {
                       "model": "${blockBlockId}_right",
                       "y": 270
                     },
                     "facing=north,type=north_west": {
                       "model": "${blockBlockId}_north_west",
                       "y": 270
                     },
                     "facing=north,type=north_east": {
                       "model": "${blockBlockId}_north_east",
                       "y": 270
                     },
                     "facing=north,type=south_east": {
                       "model": "${blockBlockId}_south_east",
                       "y": 270
                     },
                     "facing=north,type=south_west": {
                       "model": "${blockBlockId}_south_west",
                       "y": 270
                     }
                   }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "$MOD_ID:block/vertical_slab",
                  "textures": {
                    "full": "$existingBlockBlockId",
                    "bottom": "$existingBlockBlockId",
                    "side": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val rightBlockModel = """
                {
                  "parent": "$MOD_ID:block/vertical_slab_right",
                  "textures": {
                    "full": "$existingBlockBlockId",
                    "bottom": "$existingBlockBlockId",
                    "side": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_right", rightBlockModel)

            val northWestBlockModel = """
                {
                  "parent": "$MOD_ID:block/vertical_slab_north_west",
                  "textures": {
                    "top": "${blockBlockId}_north_west_top",
                    "bottom": "${blockBlockId}_north_west_bottom",
                    "north": "$existingBlockBlockId",
                    "east": "${blockBlockId}_north_west_east",
                    "south": "${blockBlockId}_north_west_south",
                    "west": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_west", northWestBlockModel)

            val northEastBlockModel = """
                {
                  "parent": "$MOD_ID:block/vertical_slab_north_east",
                  "textures": {
                    "full": "$existingBlockBlockId",
                    "bottom": "$existingBlockBlockId",
                    "side": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId",
                    "front": "${blockBlockId}_north_west_front"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_east", northEastBlockModel)

            val southEastBlockModel = """
                {
                  "parent": "$MOD_ID:block/vertical_slab_south_east",
                  "textures": {
                    "full": "$existingBlockBlockId",
                    "bottom": "$existingBlockBlockId",
                    "side": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId",
                    "front": "${blockBlockId}_north_west_front"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_south_east", southEastBlockModel)

            val southWestBlockModel = """
                {
                  "parent": "$MOD_ID:block/vertical_slab_south_west",
                  "textures": {
                    "full": "$existingBlockBlockId",
                    "bottom": "$existingBlockBlockId",
                    "side": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId",
                    "front": "${blockBlockId}_north_west_front"
                  }
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
                    }
                  },
                  "pattern": [
                    "#  ",
                    "#  ",
                    "#  "
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
            builder.addRecipe("${blockName}_from_cobblestone_stonecutting", stonecuttingRecipe)

            builder.addTag("slabs", identifier.toString())
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createNorthWestTopTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()
            raster.setRect(8, 8, blank.getData(7, 7, 8, 8))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createNorthWestBottomTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(180.0).apply {
            val blank = blankClone()
            raster.setRect(0, 8, blank.getData(0, 7, 8, 8))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createNorthWestEastTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            raster.setRect(0, 0, blank.getData(0, 0, 8, 16))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createNorthWestSouthTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            raster.setRect(8, 0, blank.getData(8, 0, 8, 16))
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createFlippedTexture(input: BufferedImage): BufferedImage {
        return input.flipImage(ImageFlipMode.LEFT_RIGHT)
    }

    companion object {
        fun createClient(builder: ResourcePackBuilder) {
            val blockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 16, 8, 0, 0 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 8 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#side", "cullface": "west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#side", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("vertical_slab", blockModel)

            val cornerBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 8 ],
                            "faces": {
                                "down":  { "uv": [ 16, 8, 0, 0 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 8 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 8, 16 ], "texture": "#side", "cullface": "west" },
                                "east":  { "uv": [ 8, 0, 16, 16 ], "texture": "#side", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("vertical_slab_corner", cornerBlockModel)

            val rightBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 16, 16, 0, 8 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 8, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#side", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#side", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("vertical_slab_right", rightBlockModel)

            val northWestBlockModel = """
                {   "parent": "block/block",
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 16, 16, 0, 0 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("vertical_slab_north_west", northWestBlockModel)

            val northEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 16, 16, 0, 8 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 8, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#side", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#side", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("vertical_slab_north_east", northEastBlockModel)

            val southEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 16, 16, 0, 8 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 8, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#side", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#side", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("vertical_slab_south_east", southEastBlockModel)

            val southWestBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 16, 16, 0, 8 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 8, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16, 16 ], "texture": "#side", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 8, 16 ], "texture": "#side", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("vertical_slab_south_west", southWestBlockModel)
        }
    }
}