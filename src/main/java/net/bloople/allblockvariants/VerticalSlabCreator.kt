package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.VerticalSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.AbstractBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

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
            val blockState = """
                {
                   "variants": {
                     "facing=east,type=left": {
                       "model": "$blockBlockId"
                     },
                     "facing=east,type=double": {
                       "model": "$existingBlockBlockId"
                     },
                     "facing=east,type=right": {
                       "model": "${blockBlockId}_right"
                     },
                     "facing=south,type=left": {
                       "model": "$blockBlockId",
                       "y": 90
                     },
                     "facing=south,type=double": {
                       "model": "$existingBlockBlockId",
                       "y": 90
                     },
                     "facing=south,type=right": {
                       "model": "${blockBlockId}_right",
                       "y": 90
                     },
                     "facing=west,type=left": {
                       "model": "$blockBlockId",
                       "y": 180
                     },
                     "facing=west,type=double": {
                       "model": "$existingBlockBlockId",
                       "y": 180
                     },
                     "facing=west,type=right": {
                       "model": "${blockBlockId}_right",
                       "y": 180
                     },
                     "facing=north,type=left": {
                       "model": "$blockBlockId",
                       "y": 270
                     },
                     "facing=north,type=double": {
                       "model": "$existingBlockBlockId",
                       "y": 270
                     },
                     "facing=north,type=right": {
                       "model": "${blockBlockId}_right",
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
        }
    }
}