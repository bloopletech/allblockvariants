package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.ThinVerticalSlabBlock
import net.bloople.allblockvariants.blocks.VerticalSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.AbstractBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

class ThinVerticalSlabCreator(blockInfo: BlockInfo) :
    BlockCreator(DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_thin_vertical_slab" }) {
    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                ThinVerticalSlabBlock(AbstractBlock.Settings.copy(existingBlock))
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
                {
                  "parent": "$MOD_ID:block/thin_vertical_slab",
                  "textures": {
                    "full": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val rightBlockModel = """
                {
                  "parent": "$MOD_ID:block/thin_vertical_slab_right",
                  "textures": {
                    "full": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_right", rightBlockModel)

            val northWestBlockModel = """
                {
                  "parent": "$MOD_ID:block/thin_vertical_slab_north_west",
                  "textures": {
                    "full": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_west", northWestBlockModel)

            val northEastBlockModel = """
                {
                  "parent": "$MOD_ID:block/thin_vertical_slab_north_east",
                  "textures": {
                    "full": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_east", northEastBlockModel)

            val southEastBlockModel = """
                {
                  "parent": "$MOD_ID:block/thin_vertical_slab_south_east",
                  "textures": {
                    "full": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_south_east", southEastBlockModel)

            val southWestBlockModel = """
                {
                  "parent": "$MOD_ID:block/thin_vertical_slab_south_west",
                  "textures": {
                    "full": "$existingBlockBlockId"
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
                    },
                    "X": {
                      "item": "minecraft:shears"
                    }
                  },
                  "pattern": [
                    "#  ",
                    "#  ",
                    "#X "
                  ],
                  "result": {
                    "count": 12,
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
            builder.addRecipe("${blockName}_from_cobblestone_stonecutting", stonecuttingRecipe)

            builder.addTag("slabs", identifier.toString())
        }
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
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 16, 4, 0, 0 ], "texture": "#full", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#full" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#full", "cullface": "west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#full", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("thin_vertical_slab", blockModel)

            val rightBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 16, 16, 0, 12 ], "texture": "#full", "cullface": "down" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#full" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#full", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#full", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("thin_vertical_slab_right", rightBlockModel)

            val northWestBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 0, 12, 16, 16 ], "texture": "#full" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#full" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#full" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#full" }
                            }
                        },
                        {   "from": [ 0, 0, 4 ],
                            "to": [ 4, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 4, 12 ], "texture": "#full" },
                                "up":    { "uv": [ 0, 4, 4, 16 ], "texture": "#full" },
                                "south": { "uv": [ 0, 0, 4, 16 ], "texture": "#full" },
                                "west":  { "uv": [ 4, 0, 16, 16 ], "texture": "#full" },
                                "east":  { "uv": [ 0, 0, 12, 16 ], "texture": "#full" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("thin_vertical_slab_north_west", northWestBlockModel)

            val northEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 0, 12, 16, 16 ], "texture": "#full" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#full" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#full" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#full" }
                            }
                        },
                        {   "from": [ 12, 0, 4 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 12, 0, 16, 12 ], "texture": "#full" },
                                "up":    { "uv": [ 12, 4, 16, 16 ], "texture": "#full" },
                                "south": { "uv": [ 12, 0, 16, 16 ], "texture": "#full" },
                                "west":  { "uv": [ 4, 0, 16, 16 ], "texture": "#full" },
                                "east":  { "uv": [ 0, 0, 12, 16 ], "texture": "#full" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("thin_vertical_slab_north_east", northEastBlockModel)

            val southEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 12, 0, 0 ],
                            "to": [ 16, 16, 12 ],
                            "faces": {
                                "down":  { "uv": [ 12, 4, 16, 16 ], "texture": "#full" },
                                "up":    { "uv": [ 12, 0, 16, 12 ], "texture": "#full" },
                                "north": { "uv": [ 0, 0, 4, 16 ], "texture": "#full" },
                                "west":  { "uv": [ 0, 0, 12, 16 ], "texture": "#full" },
                                "east":  { "uv": [ 4, 0, 16, 16 ], "texture": "#full" }
                            }
                        },
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 4 ], "texture": "#full" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#full" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#full" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#full" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("thin_vertical_slab_south_east", southEastBlockModel)

            val southWestBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "particle": "#full"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 4, 16, 12 ],
                            "faces": {
                                "down":  { "uv": [ 0, 4, 4, 16 ], "texture": "#full" },
                                "up":    { "uv": [ 0, 0, 4, 12 ], "texture": "#full" },
                                "north": { "uv": [ 12, 0, 16, 16 ], "texture": "#full" },
                                "west":  { "uv": [ 0, 0, 12, 16 ], "texture": "#full" },
                                "east":  { "uv": [ 4, 0, 16, 16 ], "texture": "#full" }
                            }
                        },
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 4 ], "texture": "#full" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#full" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#full" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#full" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("thin_vertical_slab_south_west", southWestBlockModel)
        }
    }
}