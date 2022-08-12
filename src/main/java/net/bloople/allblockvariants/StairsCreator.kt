package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry


class StairsCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_stairs" }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                if(existingBlock is Oxidizable) {
                    OxidizableStairsBlock(
                        existingBlock.degradationLevel,
                        Blocks.AIR.defaultState,
                        existingBlock.copySettings()
                    )
                }
                else {
                    StairsBlock(Blocks.AIR.defaultState, existingBlock.copySettings())
                }
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
                    "facing=east,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner"
                    },
                    "facing=east,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer"
                    },
                    "facing=east,half=bottom,shape=straight": {
                      "model": "$blockBlockId"
                    },
                    "facing=east,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=south,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner"
                    },
                    "facing=south,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer"
                    },
                    "facing=south,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    }
                  }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {   "parent": "block/block",
                    "display": {
                        "gui": {
                            "rotation": [ 30, 135, 0 ],
                            "translation": [ 0, 0, 0],
                            "scale":[ 0.625, 0.625, 0.625 ]
                        },
                        "head": {
                            "rotation": [ 0, -90, 0 ],
                            "translation": [ 0, 0, 0 ],
                            "scale": [ 1, 1, 1 ]
                        },
                        "thirdperson_lefthand": {
                            "rotation": [ 75, -135, 0 ],
                            "translation": [ 0, 2.5, 0],
                            "scale": [ 0.375, 0.375, 0.375 ]
                        }
                    },
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
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val innerBlockModel = """
                {
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
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 0, 8, 8 ],
                            "to": [ 8, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 0, 8,  8, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 8, 0, 16,  8 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0,  8,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#west", "cullface": "west" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_inner", innerBlockModel)

            val outerBlockModel = """
                {
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
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 8, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0,  8,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_outer", outerBlockModel)

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
                      "conditions": [
                        {
                          "condition": "minecraft:survives_explosion"
                        }
                      ],
                      "entries": [
                        {
                          "type": "minecraft:item",
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
                    "# !",
                    "## ",
                    "###"
                  ],
                  "result": {
                    "count": 4,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            val stonecuttingRecipe = """
                {
                  "type": "minecraft:stonecutting",
                  "count": 1,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_stonecutting", stonecuttingRecipe)

            builder.addTag("stairs", identifier.toString())
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
                    "# !",
                    "## ",
                    "###"
                  ],
                  "result": {
                    "count": 4,
                    "item": "$vanillaIdentifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)
        }
    }

    companion object {
        fun getCreator(blockInfo: BlockInfo): BlockCreator {
            return when(blockInfo.block) {
                is AbstractGlassBlock -> GlassStairsCreator(blockInfo)
                is GrassBlock -> GrassStairsCreator(blockInfo)
                else -> StairsCreator(blockInfo)
            }
        }
    }
}