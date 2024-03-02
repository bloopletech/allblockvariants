package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.OxidizableThinSlabBlock
import net.bloople.allblockvariants.blocks.ThinSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups


class ThinSlabCreator(metrics: Metrics, blockInfo: BlockInfo) : BlockCreator(metrics) {
    override val dbi = AdvancedDerivedBlockInfo(blockInfo) {
        Pair("${transformedExistingBlockName}_thin_slab", "${transformedExistingBlockName}_slab")
    }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(when(existingBlock) {
                is Oxidizable -> OxidizableThinSlabBlock(existingBlock.degradationLevel, existingBlock.copySettings())
                else -> ThinSlabBlock(existingBlock.copySettings())
            })

            registerItem(BlockItem(block, Item.Settings()), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
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
            """.trimIndent()
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
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

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
                      "item": "$parentIdentifier"
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
                  "count": 4,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_existing_stonecutting", stonecuttingRecipe)

            val parentStonecuttingRecipe = """
                {
                  "type": "minecraft:stonecutting",
                  "count": 4,
                  "ingredient": {
                    "item": "$parentIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_parent_stonecutting", parentStonecuttingRecipe)
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "key": {
                    "#": {
                      "item": "$parentIdentifier"
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

    companion object {
        fun getCreator(blockInfo: BlockInfo, metrics: Metrics): BlockCreator {
            return when(blockInfo.block) {
                is HorizontalFacingBlock -> HorizontalFacingThinSlabCreator(metrics, blockInfo)
                is RedstoneLampBlock -> RedstoneLampThinSlabCreator(metrics, blockInfo)
                is TransparentBlock -> GlassThinSlabCreator(metrics, blockInfo)
                is PillarBlock -> ThinPillarSlabCreator(metrics, blockInfo)
                else -> ThinSlabCreator(metrics, blockInfo)
            }
        }
    }
}