package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.*
import net.bloople.allblockvariants.blocks.GlassSlabBlock
import net.bloople.allblockvariants.blocks.StainedGlassSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Stainable
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups
import java.awt.image.BufferedImage


class GlassSlabCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_slab" }

    override fun common() {
        with(dbi) {
            registerBlock(when(existingBlock) {
                is Stainable -> StainedGlassSlabBlock(existingBlock.color, blockSettings.nonOpaque())
                else -> GlassSlabBlock(blockSettings.nonOpaque())
            })

            registerItem(BlockItem(block, itemSettings), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun client(builder: ResourcePackBuilder) {
        with(dbi) {
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
            """
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
            """
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
            """
            builder.addBlockModel("${blockName}_top", topBlockModel)

            builder.addItemModelDefinition(blockName, id(blockBlockId))
        }
    }

    override fun server(builder: ResourcePackBuilder) {
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
            """
            builder.addBlockLootTable(blockName, lootTable)

            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "building",
                  "key": {
                    "#": "$existingIdentifier",
                    "!": "${ModStickCreator.Companion.identifier}"
                  },
                  "pattern": [
                    "  !",
                    "###"
                  ],
                  "result": {
                    "count": 6,
                    "id": "$identifier"
                  }
                }
            """
            builder.addRecipe(blockName, recipe)

            builder.addStonecuttingRecipe(dbi, 2)

            builder.addBlockTag("slabs", identifier)
            builder.addItemTag("slabs", identifier)
        }
    }

    override fun vanillaBlockServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "building",
                  "key": {
                    "#": "$existingIdentifier",
                    "!": "${ModStickCreator.Companion.identifier}"
                  },
                  "pattern": [
                    "  !",
                    "###"
                  ],
                  "result": {
                    "count": 6,
                    "id": "$vanillaIdentifier"
                  }
                }
            """
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
