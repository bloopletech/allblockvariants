package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.GlazedTerracottaSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.GlazedTerracottaBlock
import net.minecraft.block.SlabBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup


class HorizontalFacingSlabCreator(metrics: Metrics, blockInfo: BlockInfo) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_slab" }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(when(existingBlock) {
                is GlazedTerracottaBlock -> GlazedTerracottaSlabBlock(blockSettings)
                else -> SlabBlock(blockSettings)
            })

            registerItem(BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS)))
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture("${blockName}_90") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture90)
            }

            builder.addBlockTexture("${blockName}_180") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture180)
            }

            builder.addBlockTexture("${blockName}_270") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture270)
            }

            val blockState = """
                {
                  "variants": {
                    "facing=east,type=bottom": {
                      "model": "$blockBlockId",
                      "y": 270
                    },
                    "facing=east,type=double": {
                      "model": "$existingBlockBlockId",
                      "y": 270
                    },
                    "facing=east,type=top": {
                      "model": "${blockBlockId}_top",
                      "y": 270
                    },
                    "facing=north,type=bottom": {
                      "model": "$blockBlockId",
                      "y": 180
                    },
                    "facing=north,type=double": {
                      "model": "$existingBlockBlockId",
                      "y": 180
                    },
                    "facing=north,type=top": {
                      "model": "${blockBlockId}_top",
                      "y": 180
                    },
                    "facing=south,type=bottom": {
                      "model": "$blockBlockId"
                    },
                    "facing=south,type=double": {
                      "model": "$existingBlockBlockId"
                    },
                    "facing=south,type=top": {
                      "model": "${blockBlockId}_top"
                    },
                    "facing=west,type=bottom": {
                      "model": "$blockBlockId",
                      "y": 90
                    },
                    "facing=west,type=double": {
                      "model": "$existingBlockBlockId",
                      "y": 90
                    },
                    "facing=west,type=top": {
                      "model": "${blockBlockId}_top",
                      "y": 90
                    }
                  }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                    "parent": "block/cube",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "${blockBlockId}_90",
                        "east": "${blockBlockId}_180",
                        "south": "${blockBlockId}_270",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "display": {
                        "firstperson_righthand": {
                            "rotation": [ 0, 135, 0 ],
                            "translation": [ 0, 0, 0 ],
                            "scale": [ 0.40, 0.40, 0.40 ]
                        }
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0,  0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0,  0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0,  8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0,  8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0,  8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0,  8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val topBlockModel = """
                {
                    "parent": "block/cube",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "${blockBlockId}_90",
                        "east": "${blockBlockId}_180",
                        "south": "${blockBlockId}_270",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "display": {
                        "firstperson_righthand": {
                            "rotation": [ 0, 135, 0 ],
                            "translation": [ 0, 0, 0 ],
                            "scale": [ 0.40, 0.40, 0.40 ]
                        }
                    },
                    "elements": [
                        {   "from": [ 0, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0,  0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0,  0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0,  0, 16, 8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0,  0, 16, 8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0,  0, 16, 8 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0,  0, 16, 8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top", topBlockModel)

            val itemModel = """
                {
                  "parent": "$blockBlockId"
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
                    "  !",
                    "###"
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
                    "  !",
                    "###"
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
}