package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.GlassSlabBlock
import net.bloople.allblockvariants.blocks.StainedGlassSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.Stainable
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import java.awt.image.BufferedImage


class GlassSlabCreator(metrics: Metrics, blockInfo: BlockInfo) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_slab" }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(when(existingBlock) {
                is Stainable -> StainedGlassSlabBlock(existingBlock.color, blockSettings.nonOpaque())
                else -> GlassSlabBlock(blockSettings.nonOpaque())
            })

            registerItem(BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS)))
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent())

            builder.addBlockTexture("${blockName}_side") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createSideTexture)
            }

            val blockState = """
                {
                   "variants": {
                     "type=bottom": {
                       "model": "$blockBlockId"
                     },
                     "type=double": {
                       "model": "$existingBlockBlockId"
                     },
                     "type=top": {
                       "model": "${blockBlockId}_top"
                     }
                   }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "minecraft:block/slab",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "side": "${blockBlockId}_side",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val topBlockModel = """
                {
                  "parent": "minecraft:block/slab_top",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "side": "${blockBlockId}_side",
                    "top": "$existingBlockBlockId"
                  }
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
                  "count": 2,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_stonecutting", stonecuttingRecipe)

            builder.addBlockTag("slabs", identifier.toString())
            builder.addItemTag("slabs", identifier.toString())
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
    private fun createSideTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val topRow = getData(0, 0, 16, 1)
            val bottomRow = getData(0, 15, 16, 1)

            raster.setRect(0, 8, topRow)
            raster.setRect(0, 7, bottomRow)
        }
    }
}
