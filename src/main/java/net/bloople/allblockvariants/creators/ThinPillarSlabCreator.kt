package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.AdvancedDerivedBlockInfo
import net.bloople.allblockvariants.BlockCreator
import net.bloople.allblockvariants.BlockInfo
import net.bloople.allblockvariants.ClientUtil
import net.bloople.allblockvariants.ModStickCreator
import net.bloople.allblockvariants.ResourcePackBuilder
import net.bloople.allblockvariants.blocks.ThinPillarSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups


class ThinPillarSlabCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = AdvancedDerivedBlockInfo(blockInfo) {
        Pair("${transformedExistingBlockName}_thin_slab", "${transformedExistingBlockName}_slab")
    }

    override fun common() {
        with(dbi) {
            registerBlock(ThinPillarSlabBlock(blockSettings))
            registerItem(BlockItem(block, Item.Settings()), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun client(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture("${blockName}_z_east") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture90)
            }

            builder.addBlockTexture("${blockName}_z_west") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture270)
            }

            builder.addBlockTexture("${blockName}_z_bottom") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture180)
            }

            val blockState = """
                {
                   "variants": {
                     "type=bottom,axis=x": {
                       "model": "${blockBlockId}_x"
                     },
                     "type=bottom,axis=y": {
                       "model": "$blockBlockId"
                     },
                     "type=bottom,axis=z": {
                       "model": "${blockBlockId}_z"
                     },
                     "type=top,axis=x": {
                       "model": "${blockBlockId}_top_x"
                     },
                     "type=top,axis=y": {
                       "model": "${blockBlockId}_top"
                     },
                     "type=top,axis=z": {
                       "model": "${blockBlockId}_top_z"
                     }
                   }
                }
            """
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
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

            val xBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${blockBlockId}_z_west",
                        "north": "${blockBlockId}_z_west",
                        "east": "$existingBlockEndTextureId",
                        "south": "${blockBlockId}_z_east",
                        "west": "$existingBlockEndTextureId",
                        "bottom": "${blockBlockId}_z_east",
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
            builder.addBlockModel("${blockName}_x", xBlockModel)

            val zBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockSideTextureId",
                        "north": "$existingBlockEndTextureId",
                        "east": "${blockBlockId}_z_east",
                        "south": "$existingBlockEndTextureId",
                        "west": "${blockBlockId}_z_west",
                        "bottom": "${blockBlockId}_z_bottom",
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
            builder.addBlockModel("${blockName}_z", zBlockModel)

            val topBlockModel = """
                {
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
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

            val xTopBlockModel = """
                {
                    "textures": {
                        "top": "${blockBlockId}_z_west",
                        "north": "${blockBlockId}_z_west",
                        "east": "$existingBlockEndTextureId",
                        "south": "${blockBlockId}_z_east",
                        "west": "$existingBlockEndTextureId",
                        "bottom": "${blockBlockId}_z_east",
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
            builder.addBlockModel("${blockName}_top_x", xTopBlockModel)

            val zTopBlockModel = """
                {
                    "textures": {
                        "top": "$existingBlockSideTextureId",
                        "north": "$existingBlockEndTextureId",
                        "east": "${blockBlockId}_z_east",
                        "south": "$existingBlockEndTextureId",
                        "west": "${blockBlockId}_z_west",
                        "bottom": "${blockBlockId}_z_bottom",
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
            builder.addBlockModel("${blockName}_top_z", zTopBlockModel)

            val itemModel = """
                {
                  "parent": "$blockBlockId"
                }
            """
            builder.addItemModel(blockName, itemModel)
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

    override fun vanillaBlockServer(builder: ResourcePackBuilder) {
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