package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.BlockCreator
import net.bloople.allblockvariants.BlockInfo
import net.bloople.allblockvariants.DerivedBlockInfo
import net.bloople.allblockvariants.ModStickCreator
import net.bloople.allblockvariants.ResourcePackBuilder
import net.bloople.allblockvariants.blocks.RedstoneLampWallBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups

class RedstoneLampWallCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_wall" }

    override fun common() {
        with(dbi) {
            registerBlock(RedstoneLampWallBlock(blockSettings))
            registerItem(BlockItem(block, Item.Settings()), ItemGroups.FUNCTIONAL)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun client(builder: ResourcePackBuilder) {
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
            """
            builder.addBlockState(blockName, blockState)

            val inventoryBlockModel = """
                {
                  "parent": "minecraft:block/wall_inventory",
                  "textures": {
                    "wall": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_inventory", inventoryBlockModel)

            val postBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_post",
                  "textures": {
                    "wall": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_post", postBlockModel)

            val sideBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_side",
                  "textures": {
                    "wall": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_side", sideBlockModel)

            val sideTallBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_side_tall",
                  "textures": {
                    "wall": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_side_tall", sideTallBlockModel)

            val onPostBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_post",
                  "textures": {
                    "wall": "${existingBlockTextureId}_on"
                  }
                }
            """
            builder.addBlockModel("${blockName}_post_on", onPostBlockModel)

            val onSideBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_side",
                  "textures": {
                    "wall": "${existingBlockTextureId}_on"
                  }
                }
            """
            builder.addBlockModel("${blockName}_side_on", onSideBlockModel)

            val onSideTallBlockModel = """
                {
                  "parent": "minecraft:block/template_wall_side_tall",
                  "textures": {
                    "wall": "${existingBlockTextureId}_on"
                  }
                }
            """
            builder.addBlockModel("${blockName}_side_tall_on", onSideTallBlockModel)

            val itemModel = """
                {
                  "parent": "${blockBlockId}_inventory"
                }
            """
            builder.addItemModel(blockName, itemModel)
        }
    }

    override fun server(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockLootTable(dbi)

            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "redstone",
                  "key": {
                    "#": {
                      "item": "$existingIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.Companion.identifier}"
                    }
                  },
                  "pattern": [
                    "###",
                    "###",
                    "  !"
                  ],
                  "result": {
                    "count": 6,
                    "id": "$identifier"
                  }
                }
            """
            builder.addRecipe(blockName, recipe)

            builder.addStonecuttingRecipe(dbi, 1)

            builder.addBlockTag("walls", identifier)
            builder.addItemTag("walls", identifier)
        }
    }

    override fun vanillaBlockServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "redstone",
                  "key": {
                    "#": {
                      "item": "$existingIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.Companion.identifier}"
                    }
                  },
                  "pattern": [
                    "###",
                    "###",
                    "  !"
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