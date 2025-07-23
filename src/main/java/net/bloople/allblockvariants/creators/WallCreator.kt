package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.BlockCreator
import net.bloople.allblockvariants.BlockInfo
import net.bloople.allblockvariants.DerivedBlockInfo
import net.bloople.allblockvariants.ModStickCreator
import net.bloople.allblockvariants.ResourcePackBuilder
import net.bloople.allblockvariants.blocks.OxidizableWallBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Oxidizable
import net.minecraft.block.RedstoneLampBlock
import net.minecraft.block.TransparentBlock
import net.minecraft.block.WallBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups

class WallCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_wall" }

    override fun shouldCreate(): Boolean {
        if(dbi.existingBlock is TransparentBlock) return false
        return super.shouldCreate()
    }

    override fun common() {
        with(dbi) {
            registerBlock(when(existingBlock) {
                is Oxidizable -> OxidizableWallBlock(existingBlock.degradationLevel, blockSettings)
                else -> WallBlock(blockSettings)
            })

            registerItem(BlockItem(block, Item.Settings()), ItemGroups.BUILDING_BLOCKS)
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
                        "up": "true"
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side",
                        "uvlock": true
                      },
                      "when": {
                        "north": "low"
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side",
                        "uvlock": true,
                        "y": 90
                      },
                      "when": {
                        "east": "low"
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side",
                        "uvlock": true,
                        "y": 180
                      },
                      "when": {
                        "south": "low"
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side",
                        "uvlock": true,
                        "y": 270
                      },
                      "when": {
                        "west": "low"
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall",
                        "uvlock": true
                      },
                      "when": {
                        "north": "tall"
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall",
                        "uvlock": true,
                        "y": 90
                      },
                      "when": {
                        "east": "tall"
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall",
                        "uvlock": true,
                        "y": 180
                      },
                      "when": {
                        "south": "tall"
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_tall",
                        "uvlock": true,
                        "y": 270
                      },
                      "when": {
                        "west": "tall"
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
                  "category": "misc",
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
                  "category": "misc",
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

    companion object {
        fun getCreator(blockInfo: BlockInfo): BlockCreator {
            return when(blockInfo.block) {
                is RedstoneLampBlock -> RedstoneLampWallCreator(blockInfo)
                else -> WallCreator(blockInfo)
            }
        }
    }
}