package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Blocks
import net.minecraft.block.StairsBlock
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import java.awt.image.BufferedImage


class GrassStairsCreator(private val metrics: Metrics, blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_stairs" }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                StairsBlock(Blocks.AIR.defaultState, existingBlock.copySettings())
            )
            metrics.common.blocksAdded++

            item = Registry.register(
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
            builder.addBlockColorProvider({ _, world, pos, _ ->
                if(world == null || pos == null) {
                    return@addBlockColorProvider GrassColors.getColor(0.5, 1.0)
                }
                BiomeColors.getGrassColor(world, pos)
            }, arrayOf(block))

            builder.addItemColorProvider({ stack, tintIndex, blockColors ->
                val blockState = (stack.item as BlockItem).block.defaultState
                 blockColors.getColor(blockState, null, null, tintIndex)
            }, arrayOf(item))

            builder.addBlockTexture("${blockName}_overlay_bottom_west") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/grass_block_side_overlay.png",
                    ::createOverlayBottomWestTexture)
            }

            builder.addBlockTexture("${blockName}_overlay_bottom_north") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/grass_block_side_overlay.png",
                    ::createOverlayBottomNorthTexture)
            }

            builder.addBlockTexture("${blockName}_overlay_bottom_south") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/grass_block_side_overlay.png",
                    ::createOverlayBottomSouthTexture)
            }

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
                        "particle": "minecraft:block/dirt",
                        "bottom": "minecraft:block/dirt",
                        "top": "minecraft:block/grass_block_top",
                        "side": "minecraft:block/grass_block_side",
                        "overlay_top": "minecraft:block/grass_block_side_overlay",
                        "overlay_bottom_west": "$MOD_ID:block/${blockName}_overlay_bottom_west",
                        "overlay_bottom_north": "$MOD_ID:block/${blockName}_overlay_bottom_north",
                        "overlay_bottom_south": "$MOD_ID:block/${blockName}_overlay_bottom_south"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top", "tintindex": 0 },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#side" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#side" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#side" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#side" }
                            }
                        },
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay_bottom_north", "tintindex": 0 },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay_bottom_south", "tintindex": 0 },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay_bottom_west", "tintindex": 0 }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top", "cullface": "up", "tintindex": 0 },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#side", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#side" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#side" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#side" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "north": { "uv": [ 0, 0, 8, 8 ], "texture": "#overlay_top", "tintindex": 0, "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16, 8 ], "texture": "#overlay_top", "tintindex": 0 },
                                "west":  { "uv": [ 0, 0, 16, 8 ], "texture": "#overlay_top", "tintindex": 0 },
                                "east":  { "uv": [ 0, 0, 16, 8 ], "texture": "#overlay_top", "tintindex": 0 }
                            }
                        }
                    ]
                }

            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val innerBlockModel = """
                {
                    "textures": {
                        "particle": "minecraft:block/dirt",
                        "bottom": "minecraft:block/dirt",
                        "top": "minecraft:block/grass_block_top",
                        "side": "minecraft:block/grass_block_side",
                        "overlay": "minecraft:block/grass_block_side_overlay"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top", "tintindex": 0 },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#side", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#side", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#side", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#side", "cullface": "east" }
                            }
                        },
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay", "tintindex": 0, "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay", "tintindex": 0, "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay", "tintindex": 0, "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay", "tintindex": 0, "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top", "cullface": "up", "tintindex": 0 },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#side", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#side", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#side" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#side", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "east" }
                            }
                        },
                        {   "from": [ 0, 8, 8 ],
                            "to": [ 8, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 0, 8,  8, 16 ], "texture": "#top", "cullface": "up", "tintindex": 0 },
                                "north": { "uv": [ 8, 0, 16,  8 ], "texture": "#side" },
                                "south": { "uv": [ 0, 0,  8,  8 ], "texture": "#side", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#side", "cullface": "west" }
                            }
                        },
                        {   "from": [ 0, 8, 8 ],
                            "to": [ 8, 16, 16 ],
                            "faces": {
                                "north": { "uv": [ 8, 0, 16,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "north" },
                                "south": { "uv": [ 0, 0,  8,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "west" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_inner", innerBlockModel)

            val outerBlockModel = """
                {
                    "textures": {
                        "particle": "minecraft:block/dirt",
                        "bottom": "minecraft:block/dirt",
                        "top": "minecraft:block/grass_block_top",
                        "side": "minecraft:block/grass_block_side",
                        "overlay": "minecraft:block/grass_block_side_overlay"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top", "tintindex": 0 },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#side", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#side", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#side", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#side", "cullface": "east" }
                            }
                        },
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay", "tintindex": 0, "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay", "tintindex": 0, "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay", "tintindex": 0, "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#overlay", "tintindex": 0, "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 8, 16, 16 ], "texture": "#top", "cullface": "up", "tintindex": 0 },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#side" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#side", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#side" },
                                "east":  { "uv": [ 0, 0,  8,  8 ], "texture": "#side", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "west" },
                                "east":  { "uv": [ 0, 0,  8,  8 ], "texture": "#overlay", "tintindex": 0, "cullface": "east" }
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

            builder.addBlockTag("stairs", identifier.toString())
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

    @Environment(value=EnvType.CLIENT)
    private fun createOverlayBottomWestTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val topHalf = getData(0, 0, 16, 8)
            raster.setRect(0, 8, topHalf)
            setRGB(0, 0, 0xffff0000.toInt())
            //setRGB(0, 15, 0xff00ff00.toInt())
            setRGB(15, 0, 0xff0000ff.toInt())
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createOverlayBottomNorthTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val rightHalf = getData(8, 0, 8, 8)
            raster.setRect(8, 8, rightHalf)
            setRGB(0, 0, 0xffff0000.toInt())
            setRGB(0, 15, 0xff00ff00.toInt())
            setRGB(15, 0, 0xff0000ff.toInt())
            setRGB(15, 15, 0xffffff00.toInt())
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createOverlayBottomSouthTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val topHalf = getData(0, 0, 16, 8)
            raster.setRect(0, 8, topHalf)
            setRGB(0, 0, 0xffff0000.toInt())
            //setRGB(0, 15, 0xff00ff00.toInt())
            setRGB(15, 0, 0xff0000ff.toInt())
        }
    }
}