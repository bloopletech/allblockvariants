package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.AdvancedDerivedBlockInfo
import net.bloople.allblockvariants.BlockCreator
import net.bloople.allblockvariants.BlockInfo
import net.bloople.allblockvariants.ClientUtil
import net.bloople.allblockvariants.ModStickCreator
import net.bloople.allblockvariants.ResourcePackBuilder
import net.bloople.allblockvariants.blocks.GlazedTerracottaThinSlabBlock
import net.bloople.allblockvariants.blocks.ThinSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.GlazedTerracottaBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups


class HorizontalFacingThinSlabCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = AdvancedDerivedBlockInfo(blockInfo) {
        Pair("${transformedExistingBlockName}_thin_slab", "${transformedExistingBlockName}_slab")
    }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(when(existingBlock) {
                is GlazedTerracottaBlock -> GlazedTerracottaThinSlabBlock(blockSettings)
                else -> ThinSlabBlock(blockSettings)
            })

            registerItem(BlockItem(block, Item.Settings()), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture("${blockName}_90") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture90)
            }

            builder.addBlockTexture("${blockName}_180") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture180)
            }

            builder.addBlockTexture("${blockName}_270") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture270)
            }

            val blockState = """
                {
                   "variants": {
                     "facing=east,type=bottom": {
                       "model": "$blockBlockId",
                        "y": 270
                     },
                     "facing=east,type=top": {
                       "model": "${blockBlockId}_top",
                        "y": 270
                     },
                     "facing=north,type=bottom": {
                       "model": "$blockBlockId",
                        "y": 180
                     },
                     "facing=north,type=top": {
                       "model": "${blockBlockId}_top",
                        "y": 180
                     },
                     "facing=south,type=bottom": {
                       "model": "$blockBlockId"
                     },
                     "facing=south,type=top": {
                       "model": "${blockBlockId}_top"
                     },
                     "facing=west,type=bottom": {
                       "model": "$blockBlockId",
                        "y": 90
                     },
                     "facing=west,type=top": {
                       "model": "${blockBlockId}_top",
                        "y": 90
                     }
                   }
                }
            """
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "${blockBlockId}_90",
                        "east": "${blockBlockId}_180",
                        "south": "${blockBlockId}_270",
                        "west": "$existingBlockWestTextureId",
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
                        "north": "${blockBlockId}_90",
                        "east": "${blockBlockId}_180",
                        "south": "${blockBlockId}_270",
                        "west": "$existingBlockWestTextureId",
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
}