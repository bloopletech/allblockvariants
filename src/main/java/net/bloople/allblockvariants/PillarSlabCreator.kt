package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.PillarSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import java.awt.image.BufferedImage


class PillarSlabCreator(private val metrics: Metrics, blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_slab" }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                PillarSlabBlock(existingBlock.copySettings())
            )
            metrics.common.blocksAdded++

            Registry.register(
                Registry.ITEM,
                identifier,
                BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
            )
            metrics.common.itemsAdded++
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture("${blockName}_z_east") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockTextureName.png",
                    ::createZEastTexture)
            }

            builder.addBlockTexture("${blockName}_z_west") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockTextureName.png",
                    ::createZWestTexture)
            }

            builder.addBlockTexture("${blockName}_z_bottom") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockTextureName.png",
                    ::createZBottomTexture)
            }

            val blockState = """
                {
                   "variants": {
                     "type=bottom,axis=x": {
                       "model": "${blockBlockId}_x"
                     },
                     "type=bottom,axis=y": {
                       "model": "$blockBlockId"
                     },
                     "type=bottom,axis=z": {
                       "model": "${blockBlockId}_z"
                     },
                     "type=double,axis=x": {
                       "model": "$existingBlockHorizontalBlockId",
                       "x": 90,
                       "y": 90
                     },
                     "type=double,axis=y": {
                       "model": "$existingBlockBlockId"
                     },
                     "type=double,axis=z": {
                       "model": "$existingBlockHorizontalBlockId",
                        "x": 90
                     },
                     "type=top,axis=x": {
                       "model": "${blockBlockId}_top_x"
                     },
                     "type=top,axis=y": {
                       "model": "${blockBlockId}_top"
                     },
                     "type=top,axis=z": {
                       "model": "${blockBlockId}_top_z"
                     }
                   }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
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
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val xBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${blockBlockId}_z_west",
                        "north": "${blockBlockId}_z_west",
                        "east": "$existingBlockEndTextureId",
                        "south": "${blockBlockId}_z_east",
                        "west": "$existingBlockEndTextureId",
                        "bottom": "${blockBlockId}_z_east",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top", "rotation": 180 },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_x", xBlockModel)

            val zBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockSideTextureId",
                        "north": "$existingBlockEndTextureId",
                        "east": "${blockBlockId}_z_east",
                        "south": "$existingBlockEndTextureId",
                        "west": "${blockBlockId}_z_west",
                        "bottom": "${blockBlockId}_z_bottom",
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
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_z", zBlockModel)

            val topBlockModel = """
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
                        {   "from": [ 0, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0, 16,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top", topBlockModel)

            val topXBlockModel = """
                {
                    "textures": {
                        "top": "${blockBlockId}_z_west",
                        "north": "${blockBlockId}_z_west",
                        "east": "$existingBlockEndTextureId",
                        "south": "${blockBlockId}_z_east",
                        "west": "$existingBlockEndTextureId",
                        "bottom": "${blockBlockId}_z_east",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top", "rotation": 180, "cullface": "up" },
                                "north": { "uv": [ 0, 0, 16,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_x", topXBlockModel)

            val topZBlockModel = """
                {
                    "textures": {
                        "top": "$existingBlockSideTextureId",
                        "north": "$existingBlockEndTextureId",
                        "east": "${blockBlockId}_z_east",
                        "south": "$existingBlockEndTextureId",
                        "west": "${blockBlockId}_z_west",
                        "bottom": "${blockBlockId}_z_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top", "rotation": 180, "cullface": "up" },
                                "north": { "uv": [ 0, 0, 16,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_z", topZBlockModel)

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
                  "count": 2,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_stonecutting", stonecuttingRecipe)

            builder.addBlockTag("slabs", identifier.toString())
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

    @Environment(value=EnvType.CLIENT)
    private fun createZEastTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(90.0)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createZWestTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(270.0)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createZBottomTexture(input: BufferedImage): BufferedImage {
        return input.rotateImage(180.0)
    }
}