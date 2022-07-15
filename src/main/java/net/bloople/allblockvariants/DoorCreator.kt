package net.bloople.allblockvariants

import net.devtech.arrp.api.RuntimeResourcePack
import net.devtech.arrp.util.CountingInputStream
import net.devtech.arrp.util.UnsafeByteArrayOutputStream
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.AbstractBlock
import net.minecraft.block.DoorBlock
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.TallBlockItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.awt.image.BufferedImage
import java.io.InputStream
import javax.imageio.ImageIO

class DoorCreator(blockInfo: BlockInfo) :
    BlockCreator(DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_door" }) {

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                DoorBlock(AbstractBlock.Settings.copy(existingBlock))
            )

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
            builder.addItemTexture(blockName) { _: RuntimeResourcePack, _: Identifier ->
                Util.getVanillaClientResource(Identifier("textures/block/$existingBlockName.png")).use {
                    return@addItemTexture createDoorTexture(it)
                }
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
                    "bottom": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_left", bottomLeftBlockModel)

            val bottomLeftOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_left_open",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_left_open", bottomLeftOpenBlockModel)

            val bottomRightBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_right",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_right", bottomRightBlockModel)

            val bottomRightOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_bottom_right_open",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_right_open", bottomRightOpenBlockModel)

            val topLeftBlockModel = """
                {
                  "parent": "minecraft:block/door_top_left",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_left", topLeftBlockModel)

            val topLeftOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_top_left_open",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_left_open", topLeftOpenBlockModel)

            val topRightBlockModel = """
                {
                  "parent": "minecraft:block/door_top_right",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_right", topRightBlockModel)

            val topRightOpenBlockModel = """
                {
                  "parent": "minecraft:block/door_top_right_open",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
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
    private fun createDoorTexture(source: InputStream): ByteArray {
        try {
            // optimize buffer allocation, input and output image after recoloring should be roughly the same size
            val inputStream = CountingInputStream(source)
            // repaint image
            val input: BufferedImage = ImageIO.read(inputStream)
            val scaledBlock = Util.scaleImage(input, 8, 7)

            val output = BufferedImage(input.width, input.height, BufferedImage.TYPE_INT_ARGB)

            output.raster.setRect(4, 2, scaledBlock.raster)
            output.raster.setRect(4, 9, scaledBlock.raster)

            // write image
            val baos = UnsafeByteArrayOutputStream(inputStream.bytes())
            ImageIO.write(output, "png", baos)
            return baos.bytes
        }
        catch(e: Throwable) {
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }
}