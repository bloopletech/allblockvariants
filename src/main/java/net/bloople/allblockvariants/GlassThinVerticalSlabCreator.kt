package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.GlassThinVerticalSlabBlock
import net.bloople.allblockvariants.blocks.StainedGlassThinVerticalSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Stainable
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import java.awt.image.BufferedImage


class GlassThinVerticalSlabCreator(blockInfo: BlockInfo) :
    BlockCreator(DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_thin_vertical_slab" }) {

    override fun doCreateCommon() {
        with(dbi) {
            val bSettings = AbstractBlock.Settings.copy(existingBlock).nonOpaque()
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                if(existingBlock is Stainable) StainedGlassThinVerticalSlabBlock(existingBlock.color, bSettings)
                else GlassThinVerticalSlabBlock(bSettings)
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
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent())

            builder.addBlockTexture("${blockName}_top") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createTopTexture)
            }

            builder.addBlockTexture("${blockName}_bottom") { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(
                    builder.getBlockTexture("${blockName}_top"),
                    ::createFlippedTexture)
            }

            builder.addBlockTexture("${blockName}_side") { ->
                return@addBlockTexture ClientUtil.createVanillaDerivedTexture("textures/block/$existingBlockName.png",
                    ::createSideTexture)
            }

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
                  "parent": "$MOD_ID:block/thin_vertical_slab",
                  "textures": {
                    "full": "$existingBlockBlockId",
                    "bottom": "${blockBlockId}_bottom",
                    "side": "${blockBlockId}_side",
                    "top": "${blockBlockId}_top"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val rightBlockModel = """
                {
                  "parent": "$MOD_ID:block/thin_vertical_slab_right",
                  "textures": {
                    "full": "$existingBlockBlockId",
                    "bottom": "${blockBlockId}_bottom",
                    "side": "${blockBlockId}_side",
                    "top": "${blockBlockId}_top"
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

    @Environment(value=EnvType.CLIENT)
    private fun createSideTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val topRow = getData(0, 0, 16, 1)
            val bottomRow = getData(0, 15, 16, 1)

            raster.setRect(0, 12, topRow)
            raster.setRect(0, 3, bottomRow)
        }.rotateImage(90.0).flipImage(ImageFlipMode.LEFT_RIGHT)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createTopTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val rightRow = getData(15, 0, 1, 16)
            val leftRow = getData(0, 0, 1, 16)

            raster.setRect(3, 0, rightRow)
            raster.setRect(12, 0, leftRow)
        }.rotateImage(90.0)
    }

    @Environment(value=EnvType.CLIENT)
    private fun createFlippedTexture(input: BufferedImage): BufferedImage {
        return input.flipImage(ImageFlipMode.LEFT_RIGHT)
    }
}
