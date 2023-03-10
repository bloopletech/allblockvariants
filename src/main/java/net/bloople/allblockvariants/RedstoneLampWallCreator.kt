package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.RedstoneLampWallBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

class RedstoneLampWallCreator(private val metrics: Metrics, blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_wall" }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                RedstoneLampWallBlock(existingBlock.copySettings())
            )
            metrics.common.blocksAdded++

            Registry.register(
                Registry.ITEM,
                identifier,
                BlockItem(block, Item.Settings().group(ItemGroup.DECORATIONS))
            )
            metrics.common.itemsAdded++
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            val blockState = """
                {
                  "multipart": [
                    {
                      "apply": {
                        "model": "${blockBlockId}_post"
                      },
                      "when": {
                        "AND": [
                          { "up": "true" },
                          { "lit": "false" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side",
                        "uvlock": true
                      },
                      "when": {
                        "AND": [
                          { "north": "low" },
                          { "lit": "false" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side",
                        "uvlock": true,
                        "y": 90
                      },
                      "when": {
                        "AND": [
                          { "east": "low" },
                          { "lit": "false" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side",
                        "uvlock": true,
                        "y": 180
                      },
                      "when": {
                        "AND": [
                          { "south": "low" },
                          { "lit": "false" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side",
                        "uvlock": true,
                        "y": 270
                      },
                      "when": {
                        "AND": [
                          { "west": "low" },
                          { "lit": "false" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall",
                        "uvlock": true
                      },
                      "when": {
                        "AND": [
                          { "north": "tall" },
                          { "lit": "false" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall",
                        "uvlock": true,
                        "y": 90
                      },
                      "when": {
                        "AND": [
                          { "east": "tall" },
                          { "lit": "false" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall",
                        "uvlock": true,
                        "y": 180
                      },
                      "when": {
                        "AND": [
                          { "south": "tall" },
                          { "lit": "false" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall",
                        "uvlock": true,
                        "y": 270
                      },
                      "when": {
                        "AND": [
                          { "west": "tall" },
                          { "lit": "false" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_post_on"
                      },
                      "when": {
                        "AND": [
                          { "up": "true" },
                          { "lit": "true" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_on",
                        "uvlock": true
                      },
                      "when": {
                        "AND": [
                          { "north": "low" },
                          { "lit": "true" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_on",
                        "uvlock": true,
                        "y": 90
                      },
                      "when": {
                        "AND": [
                          { "east": "low" },
                          { "lit": "true" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_on",
                        "uvlock": true,
                        "y": 180
                      },
                      "when": {
                        "AND": [
                          { "south": "low" },
                          { "lit": "true" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_on",
                        "uvlock": true,
                        "y": 270
                      },
                      "when": {
                        "AND": [
                          { "west": "low" },
                          { "lit": "true" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall_on",
                        "uvlock": true
                      },
                      "when": {
                        "AND": [
                          { "north": "tall" },
                          { "lit": "true" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall_on",
                        "uvlock": true,
                        "y": 90
                      },
                      "when": {
                        "AND": [
                          { "east": "tall" },
                          { "lit": "true" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall_on",
                        "uvlock": true,
                        "y": 180
                      },
                      "when": {
                        "AND": [
                          { "south": "tall" },
                          { "lit": "true" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall_on",
                        "uvlock": true,
                        "y": 270
                      },
                      "when": {
                        "AND": [
                          { "west": "tall" },
                          { "lit": "true" }
                        ]
                      }
                    }
                  ]
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val inventoryBlockModel = """
                {
                  "parent": "minecraft:block/wall_inventory",
                  "textures": {
                    "wall": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_inventory", inventoryBlockModel)

            val postBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_post",
                  "textures": {
                    "wall": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_post", postBlockModel)

            val sideBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_side",
                  "textures": {
                    "wall": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_side", sideBlockModel)

            val sideTallBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_side_tall",
                  "textures": {
                    "wall": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_side_tall", sideTallBlockModel)

            val onPostBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_post",
                  "textures": {
                    "wall": "${existingBlockTextureId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_post_on", onPostBlockModel)

            val onSideBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_side",
                  "textures": {
                    "wall": "${existingBlockTextureId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_side_on", onSideBlockModel)

            val onSideTallBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_side_tall",
                  "textures": {
                    "wall": "${existingBlockTextureId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_side_tall_on", onSideTallBlockModel)

            val itemModel = """
                {
                  "parent": "${blockBlockId}_inventory"
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
                    "###",
                    "###",
                    "  !"
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
                  "count": 1,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_stonecutting", stonecuttingRecipe)

            builder.addBlockTag("walls", identifier.toString())
            builder.addItemTag("walls", identifier.toString())
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
                    "###",
                    "###",
                    "  !"
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
}