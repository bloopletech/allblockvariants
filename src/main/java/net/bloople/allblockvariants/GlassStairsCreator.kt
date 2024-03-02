package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.GlassStairsBlock
import net.bloople.allblockvariants.blocks.StainedGlassStairsBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.Stainable
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import java.awt.image.BufferedImage


class GlassStairsCreator(metrics: Metrics, blockInfo: BlockInfo) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_stairs" }

    override fun doCreateCommon() {
        with(dbi) {
            val bState = existingBlock.defaultState
            registerBlock(when(existingBlock) {
                is Stainable -> StainedGlassStairsBlock(existingBlock.color, bState, blockSettings.nonOpaque())
                else -> GlassStairsBlock(bState, blockSettings.nonOpaque())
            })

            registerItem(BlockItem(block, Item.Settings()), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent())

            builder.addBlockTexture("${blockName}_bottom_bottom") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createBottomBottomTexture)
            }

            builder.addBlockTexture("${blockName}_bottom_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createBottomTopTexture)
            }
            
            builder.addBlockTexture("${blockName}_top_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createTopTopTexture)
            }

            builder.addBlockTexture("${blockName}_front") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createFrontTexture)
            }

            builder.addBlockTexture("${blockName}_left") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createLeftTexture)
            }

            builder.addBlockTexture("${blockName}_right") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createRightTexture)
            }

            builder.addBlockTexture("${blockName}_corner_inner_left") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerInnerLeftTexture)
            }

            builder.addBlockTexture("${blockName}_corner_inner_right") { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(
                    builder.getBlockTexture("${blockName}_corner_inner_left"),
                    ::createFlippedTexture)
            }

            builder.addBlockTexture("${blockName}_corner_outer_bottom_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerOuterBottomTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_outer_top_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerOuterTopTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_outer_left") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerOuterLeftTexture)
            }

            builder.addBlockTexture("${blockName}_corner_outer_right") { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(
                    builder.getBlockTexture("${blockName}_corner_outer_left"),
                    ::createFlippedTexture)
            }

            builder.addBlockTexture("${blockName}_corner_inner_bottom_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerInnerBottomTopTexture)
            }

            builder.addBlockTexture("${blockName}_corner_inner_top_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createCornerInnerTopTopTexture)
            }
            
            val blockState = """
                {
                  "variants": {
                    "facing=east,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner"
                    },
                    "facing=east,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
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
                      "x": 180
                    },
                    "facing=east,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "x": 180
                    },
                    "facing=east,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "x": 180
                    },
                    "facing=north,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=straight": {
                      "model": "$blockBlockId",
                      "y": 270
                    },
                    "facing=north,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "x": 180
                    },
                    "facing=north,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "x": 180
                    },
                    "facing=north,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "x": 180,
                      "y": 270
                    },
                    "facing=south,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner"
                    },
                    "facing=south,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer"
                    },
                    "facing=south,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=straight": {
                      "model": "$blockBlockId",
                      "y": 90
                    },
                    "facing=south,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "x": 180,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=straight": {
                      "model": "$blockBlockId",
                      "y": 180
                    },
                    "facing=west,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=straight": {
                      "model": "$blockBlockId",
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
                        "back": "$existingBlockBlockId",
                        "bottom_top": "${blockBlockId}_bottom_top",
                        "bottom_bottom": "${blockBlockId}_bottom_bottom",
                        "top_top": "${blockBlockId}_top_top",
                        "front": "${blockBlockId}_front",
                        "left": "${blockBlockId}_left",
                        "right": "${blockBlockId}_right",
                        "particle": "#back"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom_bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom_top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#right", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#left", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#front", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#back", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top_top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#right", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#left", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#front" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#back", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()

            builder.addBlockModel(blockName, blockModel)

            val innerBlockModel = """
                {
                    "textures": {
                        "back": "$existingBlockBlockId",
                        "bottom_bottom": "${blockBlockId}_bottom_bottom",
                        "corner_inner_bottom_top": "${blockBlockId}_corner_inner_bottom_top",
                        "corner_inner_top_top": "${blockBlockId}_corner_inner_top_top",
                        "corner_inner_right": "${blockBlockId}_corner_inner_right",
                        "corner_inner_left": "${blockBlockId}_corner_inner_left",
                        "particle": "#back"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom_bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#corner_inner_bottom_top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#corner_inner_left", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#back", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#corner_inner_right", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#back", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#corner_inner_top_top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#corner_inner_left", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#back", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#corner_inner_right" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#back", "cullface": "east" }
                            }
                        },
                        {   "from": [ 0, 8, 8 ],
                            "to": [ 8, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 0, 8,  8, 16 ], "texture": "#corner_inner_top_top", "cullface": "up" },
                                "north": { "uv": [ 8, 0, 16,  8 ], "texture": "#corner_inner_left" },
                                "south": { "uv": [ 0, 0,  8,  8 ], "texture": "#back", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#corner_inner_right", "cullface": "west" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_inner", innerBlockModel)

            val outerBlockModel = """
                {
                    "textures": {
                        "bottom_bottom": "${blockBlockId}_bottom_bottom",
                        "corner_outer_bottom_top": "${blockBlockId}_corner_outer_bottom_top",
                        "corner_outer_top_top": "${blockBlockId}_corner_outer_top_top",
                        "corner_outer_right": "${blockBlockId}_corner_outer_right",
                        "corner_outer_left": "${blockBlockId}_corner_outer_left",
                        "left": "${blockBlockId}_left",
                        "right": "${blockBlockId}_right",
                        "particle": "#left"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom_bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#corner_outer_bottom_top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#corner_outer_left", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#left", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#corner_outer_right", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#right", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 8, 16, 16 ], "texture": "#corner_outer_top_top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#corner_outer_left" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#left", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#corner_outer_right" },
                                "east":  { "uv": [ 0, 0,  8,  8 ], "texture": "#right", "cullface": "east" }
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
            builder.addItemTag("stairs", identifier.toString())
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
    private fun createBottomBottomTexture(input: BufferedImage): BufferedImage {
        return input.rotate180()
    }

    @Environment(value=EnvType.CLIENT)
    private fun createBottomTopTexture(input: BufferedImage): BufferedImage {
        val blank = input.blankClone()

        val rotated = input.rotate90()

        val topRow = rotated.getData(15, 0, 1, 16)
        rotated.raster.setRect(7, 0, topRow)
        rotated.raster.setRect(8, 0, blank.getData(0, 0, 8, 16))

        return rotated
    }

    @Environment(value=EnvType.CLIENT)
    private fun createTopTopTexture(input: BufferedImage): BufferedImage {
        return input.rotate90().apply {
            val topRow = getData(0, 0, 1, 16)
            val bottomRow = getData(15, 0, 1, 16)

            raster.setRect(7, 0, bottomRow)
            raster.setRect(8, 0, topRow)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createFrontTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val topRow = getData(0, 0, 16, 1)
            val bottomRow = getData(0, 15, 16, 1)

            raster.setRect(0, 7, bottomRow)
            raster.setRect(0, 8, topRow)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createLeftTexture(input: BufferedImage): BufferedImage {
        return input.flipImage(ImageFlipMode.LEFT_RIGHT).apply {
            val topRow = getData(0, 0, 9, 1)
            val leftRow = getData(0, 0, 1, 9)

            raster.setRect(0, 8, topRow)
            raster.setRect(8, 0, leftRow)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createRightTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val topRow = getData(7, 0, 9, 1)
            val rightRow = getData(15, 0, 1, 9)

            raster.setRect(7, 8, topRow)
            raster.setRect(7, 0, rightRow)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerInnerLeftTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val topRow = getData(7, 0, 9, 1)
            val rightRow = getData(15, 0, 1, 8)

            raster.setRect(7, 8, topRow)
            raster.setRect(7, 0, rightRow)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerOuterBottomTopTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            val topRow = getData(0, 0, 9, 1)
            val leftRow = getData(0, 0, 1, 9)

            raster.setRect(0, 8, topRow)
            raster.setRect(8, 0, leftRow)

            raster.setRect(0, 0, blank.getData(0, 0, 8, 8))
        }.rotate180()
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerOuterTopTopTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            val bottomRow = getData(0, 15, 8, 1)
            val rightRow = getData(15, 0, 1, 8)

            raster.setRect(0, 7, bottomRow)
            raster.setRect(7, 0, rightRow)

            raster.setRect(8, 0, blank.getData(0, 0, 16, 8))
            raster.setRect(0, 8, blank.getData(0, 0, 8, 16))
        }.rotate180()
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerOuterLeftTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val topRow = getData(0, 0, 16, 1)
            val bottomRow = getData(0, 15, 8, 1)
            val rightRow = getData(15, 0, 1, 8)

            raster.setRect(0, 8, topRow)
            raster.setRect(0, 7, bottomRow)
            raster.setRect(7, 0, rightRow)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerInnerBottomTopTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = blankClone()

            val topRow = getData(8, 0, 8, 1)
            val leftRow = getData(0, 8, 1, 8)

            raster.setRect(8, 8, topRow)
            raster.setRect(8, 8, leftRow)

            raster.setRect(0, 0, blank.getData(0, 0, 16, 8))
            raster.setRect(0, 0, blank.getData(0, 0, 8, 16))
        }.rotate180()
    }

    @Environment(value=EnvType.CLIENT)
    private fun createCornerInnerTopTopTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val bottomRow = getData(7, 15, 9, 1)
            val rightRow = getData(15, 7, 1, 9)

            raster.setRect(7, 7, bottomRow)
            raster.setRect(7, 7, rightRow)
        }.rotate180()
    }

    @Environment(value=EnvType.CLIENT)
    private fun createFlippedTexture(input: BufferedImage): BufferedImage {
        return input.flipImage(ImageFlipMode.LEFT_RIGHT)
    }
}