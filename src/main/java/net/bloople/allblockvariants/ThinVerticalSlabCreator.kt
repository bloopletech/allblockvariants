package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.ThinVerticalSlabBlock
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
                     "facing=east,type=left": {
                       "model": "$blockBlockId"
                     },
                     "facing=east,type=right": {
                       "model": "${blockBlockId}_right"
                     },
                     "facing=east,type=north_west": {
                       "model": "${blockBlockId}_right"
                     },
                     "facing=east,type=north_east": {
                       "model": "${blockBlockId}_right"
                     },
                     "facing=east,type=south_east": {
                       "model": "${blockBlockId}_right"
                     },
                     "facing=east,type=south_west": {
                       "model": "${blockBlockId}_right"
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
                       "model": "${blockBlockId}_right",
                       "y": 90
                     },
                     "facing=south,type=north_east": {
                       "model": "${blockBlockId}_right",
                       "y": 90
                     },
                     "facing=south,type=south_east": {
                       "model": "${blockBlockId}_right",
                       "y": 90
                     },
                     "facing=south,type=south_west": {
                       "model": "${blockBlockId}_right",
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
                       "model": "${blockBlockId}_right",
                       "y": 180
                     },
                     "facing=west,type=north_east": {
                       "model": "${blockBlockId}_right",
                       "y": 180
                     },
                     "facing=west,type=south_east": {
                       "model": "${blockBlockId}_right",
                       "y": 180
                     },
                     "facing=west,type=south_west": {
                       "model": "${blockBlockId}_right",
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
                       "model": "${blockBlockId}_right",
                       "y": 270
                     },
                     "facing=north,type=north_east": {
                       "model": "${blockBlockId}_right",
                       "y": 270
                     },
                     "facing=north,type=south_east": {
                       "model": "${blockBlockId}_right",
                       "y": 270
                     },
                     "facing=north,type=south_west": {
                       "model": "${blockBlockId}_right",
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
                  "parent": "$MOD_ID:block/thin_vertical_slab_right",
                  "textures": {
                    "full": "$existingBlockBlockId",
                    "bottom": "$existingBlockBlockId",
                    "side": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_right", rightBlockModel)

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
                                "down":  { "uv": [ 16, 4, 0, 0 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#side", "cullface": "west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#side", "cullface": "east" }
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
                                "down":  { "uv": [ 16, 16, 0, 12 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#full", "cullface": "south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#side", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#side", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("thin_vertical_slab_right", rightBlockModel)
        }
    }
}