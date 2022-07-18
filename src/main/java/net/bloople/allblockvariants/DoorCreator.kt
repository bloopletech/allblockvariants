package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.AbstractBlock
import net.minecraft.block.DoorBlock
import net.minecraft.block.GlassBlock
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.TallBlockItem
import net.minecraft.util.registry.Registry
import java.awt.image.BufferedImage

class DoorCreator(blockInfo: BlockInfo) :
    BlockCreator(DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_door" }) {

    override fun shouldCreate(): Boolean {
        if(dbi.existingBlock is GlassBlock) return false
        return super.shouldCreate()
    }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                DoorBlock(AbstractBlock.Settings.copy(existingBlock).nonOpaque())
            )

            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())

            Registry.register(
                Registry.ITEM,
                identifier,
                TallBlockItem(block, Item.Settings().group(ItemGroup.REDSTONE))
            )
        }
    }

    @Environment(value= EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture("${blockName}_top") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createTopDoorBlockTexture)
            }

            builder.addBlockTexture("${blockName}_bottom") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createBottomDoorBlockTexture)
            }

            builder.addItemTexture(blockName) { ->
                return@addItemTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createDoorItemTexture)
            }

            val blockState = """
                {
                  "variants": {
                    "facing=east,half=lower,hinge=left,open=false": {
                      "model": "${blockBlockId}_bottom_left"
                    },
                    "facing=east,half=lower,hinge=left,open=true": {
                      "model": "${blockBlockId}_bottom_left_open",
                      "y": 90
                    },
                    "facing=east,half=lower,hinge=right,open=false": {
                      "model": "${blockBlockId}_bottom_right"
                    },
                    "facing=east,half=lower,hinge=right,open=true": {
                      "model": "${blockBlockId}_bottom_right_open",
                      "y": 270
                    },
                    "facing=east,half=upper,hinge=left,open=false": {
                      "model": "${blockBlockId}_top_left"
                    },
                    "facing=east,half=upper,hinge=left,open=true": {
                      "model": "${blockBlockId}_top_left_open",
                      "y": 90
                    },
                    "facing=east,half=upper,hinge=right,open=false": {
                      "model": "${blockBlockId}_top_right"
                    },
                    "facing=east,half=upper,hinge=right,open=true": {
                      "model": "${blockBlockId}_top_right_open",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=left,open=false": {
                      "model": "${blockBlockId}_bottom_left",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=left,open=true": {
                      "model": "${blockBlockId}_bottom_left_open"
                    },
                    "facing=north,half=lower,hinge=right,open=false": {
                      "model": "${blockBlockId}_bottom_right",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=right,open=true": {
                      "model": "${blockBlockId}_bottom_right_open",
                      "y": 180
                    },
                    "facing=north,half=upper,hinge=left,open=false": {
                      "model": "${blockBlockId}_top_left",
                      "y": 270
                    },
                    "facing=north,half=upper,hinge=left,open=true": {
                      "model": "${blockBlockId}_top_left_open"
                    },
                    "facing=north,half=upper,hinge=right,open=false": {
                      "model": "${blockBlockId}_top_right",
                      "y": 270
                    },
                    "facing=north,half=upper,hinge=right,open=true": {
                      "model": "${blockBlockId}_top_right_open",
                      "y": 180
                    },
                    "facing=south,half=lower,hinge=left,open=false": {
                      "model": "${blockBlockId}_bottom_left",
                      "y": 90
                    },
                    "facing=south,half=lower,hinge=left,open=true": {
                      "model": "${blockBlockId}_bottom_left_open",
                      "y": 180
                    },
                    "facing=south,half=lower,hinge=right,open=false": {
                      "model": "${blockBlockId}_bottom_right",
                      "y": 90
                    },
                    "facing=south,half=lower,hinge=right,open=true": {
                      "model": "${blockBlockId}_bottom_right_open"
                    },
                    "facing=south,half=upper,hinge=left,open=false": {
                      "model": "${blockBlockId}_top_left",
                      "y": 90
                    },
                    "facing=south,half=upper,hinge=left,open=true": {
                      "model": "${blockBlockId}_top_left_open",
                      "y": 180
                    },
                    "facing=south,half=upper,hinge=right,open=false": {
                      "model": "${blockBlockId}_top_right",
                      "y": 90
                    },
                    "facing=south,half=upper,hinge=right,open=true": {
                      "model": "${blockBlockId}_top_right_open"
                    },
                    "facing=west,half=lower,hinge=left,open=false": {
                      "model": "${blockBlockId}_bottom_left",
                      "y": 180
                    },
                    "facing=west,half=lower,hinge=left,open=true": {
                      "model": "${blockBlockId}_bottom_left_open",
                      "y": 270
                    },
                    "facing=west,half=lower,hinge=right,open=false": {
                      "model": "${blockBlockId}_bottom_right",
                      "y": 180
                    },
                    "facing=west,half=lower,hinge=right,open=true": {
                      "model": "${blockBlockId}_bottom_right_open",
                      "y": 90
                    },
                    "facing=west,half=upper,hinge=left,open=false": {
                      "model": "${blockBlockId}_top_left",
                      "y": 180
                    },
                    "facing=west,half=upper,hinge=left,open=true": {
                      "model": "${blockBlockId}_top_left_open",
                      "y": 270
                    },
                    "facing=west,half=upper,hinge=right,open=false": {
                      "model": "${blockBlockId}_top_right",
                      "y": 180
                    },
                    "facing=west,half=upper,hinge=right,open=true": {
                      "model": "${blockBlockId}_top_right_open",
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
                    }
                  },
                  "pattern": [
                    "##",
                    "##",
                    "##"
                  ],
                  "result": {
                    "count": 3,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            builder.addTag("doors", identifier.toString())
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