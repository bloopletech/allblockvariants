package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.AdvancedDerivedBlockInfo
import net.bloople.allblockvariants.BlockCreator
import net.bloople.allblockvariants.BlockInfo
import net.bloople.allblockvariants.ClientUtil
import net.bloople.allblockvariants.ModStickCreator
import net.bloople.allblockvariants.ResourcePackBuilder
import net.bloople.allblockvariants.blocks.GlassThinSlabBlock
import net.bloople.allblockvariants.blocks.StainedGlassThinSlabBlock
import net.bloople.allblockvariants.getData
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.Stainable
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import java.awt.image.BufferedImage


class GlassThinSlabCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = AdvancedDerivedBlockInfo(blockInfo) {
        Pair("${transformedExistingBlockName}_thin_slab", "${transformedExistingBlockName}_slab")
    }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(when(existingBlock) {
                is Stainable -> StainedGlassThinSlabBlock(existingBlock.color, blockSettings.nonOpaque())
                else -> GlassThinSlabBlock(blockSettings.nonOpaque())
            })

            registerItem(BlockItem(block, Item.Settings()), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
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
                     "type=top": {
                       "model": "${blockBlockId}_top"
                     }
                   }
                }
            """
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "${blockBlockId}_side",
                        "east": "${blockBlockId}_side",
                        "south": "${blockBlockId}_side",
                        "west": "${blockBlockId}_side",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 4, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 12, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 12, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 12, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 12, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """
            builder.addBlockModel(blockName, blockModel)

            val topBlockModel = """
                {
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "${blockBlockId}_side",
                        "east": "${blockBlockId}_side",
                        "south": "${blockBlockId}_side",
                        "west": "${blockBlockId}_side",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 12, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0, 16, 4 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 0, 16, 4 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16, 4 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 0, 16, 4 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """
            builder.addBlockModel("${blockName}_top", topBlockModel)

            val itemModel = """
                {
                  "parent": "$blockBlockId"
                }
            """
            builder.addItemModel(blockName, itemModel)
        }
    }

    override fun doCreateServer(builder: ResourcePackBuilder) {
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
                    "#": {
                      "item": "$parentIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.Companion.identifier}"
                    }
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

            builder.addStonecuttingRecipe("${blockName}_from_existing_stonecutting", 4, existingIdentifier, identifier)
            builder.addStonecuttingRecipe("${blockName}_from_parent_stonecutting", 4, parentIdentifier!!, identifier)
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "building",
                  "key": {
                    "#": {
                      "item": "$parentIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.Companion.identifier}"
                    }
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

            raster.setRect(0, 12, topRow)
            raster.setRect(0, 3, bottomRow)
        }
    }
}
