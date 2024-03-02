package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.RedstoneLampDoorBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import java.awt.image.BufferedImage


class RedstoneLampDoorCreator(metrics: Metrics, blockInfo: BlockInfo) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_door" }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(RedstoneLampDoorBlock(blockInfo.blockSetType, existingBlock.copySettings()))
            registerItem(BlockItem(block, Item.Settings()), ItemGroups.REDSTONE)
        }
    }

    @Environment(value= EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())

            builder.addBlockTexture("${blockName}_top") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createTopDoorBlockTexture)
            }

            builder.addBlockTexture("${blockName}_top_on") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/${existingBlockTextureName}_on.png",
                    ::createTopDoorBlockTexture)
            }

            builder.addBlockTexture("${blockName}_bottom") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createBottomDoorBlockTexture)
            }

            builder.addBlockTexture("${blockName}_bottom_on") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/${existingBlockTextureName}_on.png",
                    ::createBottomDoorBlockTexture)
            }

            builder.addItemTexture(blockName) { ->
                return@addItemTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createDoorItemTexture)
            }

            val blockState = """
                {
                  "variants": {
                    "facing=east,half=lower,hinge=left,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom_left"
                    },
                    "facing=east,half=lower,hinge=left,open=true,lit=false": {
                      "model": "${blockBlockId}_bottom_left_open",
                      "y": 90
                    },
                    "facing=east,half=lower,hinge=right,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom_right"
                    },
                    "facing=east,half=lower,hinge=right,open=true,lit=false": {
                      "model": "${blockBlockId}_bottom_right_open",
                      "y": 270
                    },
                    "facing=east,half=upper,hinge=left,open=false,lit=false": {
                      "model": "${blockBlockId}_top_left"
                    },
                    "facing=east,half=upper,hinge=left,open=true,lit=false": {
                      "model": "${blockBlockId}_top_left_open",
                      "y": 90
                    },
                    "facing=east,half=upper,hinge=right,open=false,lit=false": {
                      "model": "${blockBlockId}_top_right"
                    },
                    "facing=east,half=upper,hinge=right,open=true,lit=false": {
                      "model": "${blockBlockId}_top_right_open",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=left,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom_left",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=left,open=true,lit=false": {
                      "model": "${blockBlockId}_bottom_left_open"
                    },
                    "facing=north,half=lower,hinge=right,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom_right",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=right,open=true,lit=false": {
                      "model": "${blockBlockId}_bottom_right_open",
                      "y": 180
                    },
                    "facing=north,half=upper,hinge=left,open=false,lit=false": {
                      "model": "${blockBlockId}_top_left",
                      "y": 270
                    },
                    "facing=north,half=upper,hinge=left,open=true,lit=false": {
                      "model": "${blockBlockId}_top_left_open"
                    },
                    "facing=north,half=upper,hinge=right,open=false,lit=false": {
                      "model": "${blockBlockId}_top_right",
                      "y": 270
                    },
                    "facing=north,half=upper,hinge=right,open=true,lit=false": {
                      "model": "${blockBlockId}_top_right_open",
                      "y": 180
                    },
                    "facing=south,half=lower,hinge=left,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom_left",
                      "y": 90
                    },
                    "facing=south,half=lower,hinge=left,open=true,lit=false": {
                      "model": "${blockBlockId}_bottom_left_open",
                      "y": 180
                    },
                    "facing=south,half=lower,hinge=right,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom_right",
                      "y": 90
                    },
                    "facing=south,half=lower,hinge=right,open=true,lit=false": {
                      "model": "${blockBlockId}_bottom_right_open"
                    },
                    "facing=south,half=upper,hinge=left,open=false,lit=false": {
                      "model": "${blockBlockId}_top_left",
                      "y": 90
                    },
                    "facing=south,half=upper,hinge=left,open=true,lit=false": {
                      "model": "${blockBlockId}_top_left_open",
                      "y": 180
                    },
                    "facing=south,half=upper,hinge=right,open=false,lit=false": {
                      "model": "${blockBlockId}_top_right",
                      "y": 90
                    },
                    "facing=south,half=upper,hinge=right,open=true,lit=false": {
                      "model": "${blockBlockId}_top_right_open"
                    },
                    "facing=west,half=lower,hinge=left,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom_left",
                      "y": 180
                    },
                    "facing=west,half=lower,hinge=left,open=true,lit=false": {
                      "model": "${blockBlockId}_bottom_left_open",
                      "y": 270
                    },
                    "facing=west,half=lower,hinge=right,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom_right",
                      "y": 180
                    },
                    "facing=west,half=lower,hinge=right,open=true,lit=false": {
                      "model": "${blockBlockId}_bottom_right_open",
                      "y": 90
                    },
                    "facing=west,half=upper,hinge=left,open=false,lit=false": {
                      "model": "${blockBlockId}_top_left",
                      "y": 180
                    },
                    "facing=west,half=upper,hinge=left,open=true,lit=false": {
                      "model": "${blockBlockId}_top_left_open",
                      "y": 270
                    },
                    "facing=west,half=upper,hinge=right,open=false,lit=false": {
                      "model": "${blockBlockId}_top_right",
                      "y": 180
                    },
                    "facing=west,half=upper,hinge=right,open=true,lit=false": {
                      "model": "${blockBlockId}_top_right_open",
                      "y": 90
                    },
                    "facing=east,half=lower,hinge=left,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_left_on"
                    },
                    "facing=east,half=lower,hinge=left,open=true,lit=true": {
                      "model": "${blockBlockId}_bottom_left_open_on",
                      "y": 90
                    },
                    "facing=east,half=lower,hinge=right,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_right_on"
                    },
                    "facing=east,half=lower,hinge=right,open=true,lit=true": {
                      "model": "${blockBlockId}_bottom_right_open_on",
                      "y": 270
                    },
                    "facing=east,half=upper,hinge=left,open=false,lit=true": {
                      "model": "${blockBlockId}_top_left_on"
                    },
                    "facing=east,half=upper,hinge=left,open=true,lit=true": {
                      "model": "${blockBlockId}_top_left_open_on",
                      "y": 90
                    },
                    "facing=east,half=upper,hinge=right,open=false,lit=true": {
                      "model": "${blockBlockId}_top_right_on"
                    },
                    "facing=east,half=upper,hinge=right,open=true,lit=true": {
                      "model": "${blockBlockId}_top_right_open_on",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=left,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_left_on",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=left,open=true,lit=true": {
                      "model": "${blockBlockId}_bottom_left_open_on"
                    },
                    "facing=north,half=lower,hinge=right,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_right_on",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=right,open=true,lit=true": {
                      "model": "${blockBlockId}_bottom_right_open_on",
                      "y": 180
                    },
                    "facing=north,half=upper,hinge=left,open=false,lit=true": {
                      "model": "${blockBlockId}_top_left_on",
                      "y": 270
                    },
                    "facing=north,half=upper,hinge=left,open=true,lit=true": {
                      "model": "${blockBlockId}_top_left_open_on"
                    },
                    "facing=north,half=upper,hinge=right,open=false,lit=true": {
                      "model": "${blockBlockId}_top_right_on",
                      "y": 270
                    },
                    "facing=north,half=upper,hinge=right,open=true,lit=true": {
                      "model": "${blockBlockId}_top_right_open_on",
                      "y": 180
                    },
                    "facing=south,half=lower,hinge=left,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_left_on",
                      "y": 90
                    },
                    "facing=south,half=lower,hinge=left,open=true,lit=true": {
                      "model": "${blockBlockId}_bottom_left_open_on",
                      "y": 180
                    },
                    "facing=south,half=lower,hinge=right,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_right_on",
                      "y": 90
                    },
                    "facing=south,half=lower,hinge=right,open=true,lit=true": {
                      "model": "${blockBlockId}_bottom_right_open_on"
                    },
                    "facing=south,half=upper,hinge=left,open=false,lit=true": {
                      "model": "${blockBlockId}_top_left_on",
                      "y": 90
                    },
                    "facing=south,half=upper,hinge=left,open=true,lit=true": {
                      "model": "${blockBlockId}_top_left_open_on",
                      "y": 180
                    },
                    "facing=south,half=upper,hinge=right,open=false,lit=true": {
                      "model": "${blockBlockId}_top_right_on",
                      "y": 90
                    },
                    "facing=south,half=upper,hinge=right,open=true,lit=true": {
                      "model": "${blockBlockId}_top_right_open_on"
                    },
                    "facing=west,half=lower,hinge=left,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_left_on",
                      "y": 180
                    },
                    "facing=west,half=lower,hinge=left,open=true,lit=true": {
                      "model": "${blockBlockId}_bottom_left_open_on",
                      "y": 270
                    },
                    "facing=west,half=lower,hinge=right,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_right_on",
                      "y": 180
                    },
                    "facing=west,half=lower,hinge=right,open=true,lit=true": {
                      "model": "${blockBlockId}_bottom_right_open_on",
                      "y": 90
                    },
                    "facing=west,half=upper,hinge=left,open=false,lit=true": {
                      "model": "${blockBlockId}_top_left_on",
                      "y": 180
                    },
                    "facing=west,half=upper,hinge=left,open=true,lit=true": {
                      "model": "${blockBlockId}_top_left_open_on",
                      "y": 270
                    },
                    "facing=west,half=upper,hinge=right,open=false,lit=true": {
                      "model": "${blockBlockId}_top_right_on",
                      "y": 180
                    },
                    "facing=west,half=upper,hinge=right,open=true,lit=true": {
                      "model": "${blockBlockId}_top_right_open_on",
                      "y": 90
                    }
                  }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val bottomLeftBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_left",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom",
                    "top": "${blockBlockId}_top"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_left", bottomLeftBlockModel)

            val bottomLeftOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_left_open",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom",
                    "top": "${blockBlockId}_top"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_left_open", bottomLeftOpenBlockModel)

            val bottomRightBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_right",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom",
                    "top": "${blockBlockId}_top"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_right", bottomRightBlockModel)

            val bottomRightOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_right_open",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom",
                    "top": "${blockBlockId}_top"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_right_open", bottomRightOpenBlockModel)

            val topLeftBlockModel = """
                {
                  "parent": "minecraft:block/door_top_left",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom",
                    "top": "${blockBlockId}_top"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_left", topLeftBlockModel)

            val topLeftOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_top_left_open",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom",
                    "top": "${blockBlockId}_top"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_left_open", topLeftOpenBlockModel)

            val topRightBlockModel = """
                {
                  "parent": "minecraft:block/door_top_right",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom",
                    "top": "${blockBlockId}_top"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_right", topRightBlockModel)

            val topRightOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_top_right_open",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom",
                    "top": "${blockBlockId}_top"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_right_open", topRightOpenBlockModel)

            val onBottomLeftBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_left",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom_on",
                    "top": "${blockBlockId}_top_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_left_on", onBottomLeftBlockModel)

            val onBottomLeftOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_left_open",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom_on",
                    "top": "${blockBlockId}_top_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_left_open_on", onBottomLeftOpenBlockModel)

            val onBottomRightBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_right",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom_on",
                    "top": "${blockBlockId}_top_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_right_on", onBottomRightBlockModel)

            val onBottomRightOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_right_open",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom_on",
                    "top": "${blockBlockId}_top_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_right_open_on", onBottomRightOpenBlockModel)

            val onTopLeftBlockModel = """
                {
                  "parent": "minecraft:block/door_top_left",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom_on",
                    "top": "${blockBlockId}_top_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_left_on", onTopLeftBlockModel)

            val onTopLeftOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_top_left_open",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom_on",
                    "top": "${blockBlockId}_top_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_left_open_on", onTopLeftOpenBlockModel)

            val onTopRightBlockModel = """
                {
                  "parent": "minecraft:block/door_top_right",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom_on",
                    "top": "${blockBlockId}_top_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_right_on", onTopRightBlockModel)

            val onTopRightOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_top_right_open",
                  "textures": {
                    "bottom": "${blockBlockId}_bottom_on",
                    "top": "${blockBlockId}_top_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_right_open_on", onTopRightOpenBlockModel)

            val itemModel = """
                {
                  "parent": "minecraft:item/generated",
                  "textures": {
                    "layer0": "$itemItemId"
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
                      "conditions": [
                        {
                          "condition": "minecraft:survives_explosion"
                        }
                      ],
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "conditions": [
                            {
                              "block": "$identifier",
                              "condition": "minecraft:block_state_property",
                              "properties": {
                                "half": "lower"
                              }
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
                    "## ",
                    "## ",
                    "##!"
                  ],
                  "result": {
                    "count": 3,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            builder.addBlockTag("doors", identifier.toString())
            builder.addItemTag("doors", identifier.toString())
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
                    "## ",
                    "## ",
                    "##!"
                  ],
                  "result": {
                    "count": 3,
                    "item": "$vanillaIdentifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createTopDoorBlockTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = input.blankClone()

            raster.setRect(3, 3, blank.getData(3, 3, 4, 3))
            raster.setRect(9, 3, blank.getData(9, 3, 4, 3))
            raster.setRect(3, 8, blank.getData(3, 8, 4, 3))
            raster.setRect(9, 8, blank.getData(9, 8, 4, 3))
            setRGB(0, 4, 0xff4f4f4f.toInt())
            setRGB(0, 5, 0xff818181.toInt())
            setRGB(0, 15, 0xff4f4f4f.toInt())
            setRGB(11, 14, 0xff434343.toInt())
            setRGB(12, 14, 0xff818181.toInt())
            setRGB(13, 14, 0xff818181.toInt())
            setRGB(11, 15, 0xff434343.toInt())
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createBottomDoorBlockTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            setRGB(0, 0, 0xff2e2e2e.toInt())
            setRGB(0, 10, 0xff434343.toInt())
            setRGB(0, 11, 0xff2e2e2e.toInt())
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createDoorItemTexture(input: BufferedImage): BufferedImage {
        val scaledBlock = input.scaleImage(10, 7)

        return input.blankClone().apply {
            val blank = input.blankClone()

            raster.setRect(3, 2, scaledBlock.raster)
            raster.setRect(3, 9, scaledBlock.raster)
            raster.setRect(5, 3, blank.getData(5, 3, 3, 2))
            raster.setRect(9, 3, blank.getData(9, 3, 3, 2))
            raster.setRect(5, 6, blank.getData(5, 6, 3, 2))
            raster.setRect(9, 6, blank.getData(9, 6, 3, 2))
        }
    }
}