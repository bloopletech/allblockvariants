package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.OxidizableWallBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.AbstractGlassBlock
import net.minecraft.block.Oxidizable
import net.minecraft.block.WallBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

class WallCreator(blockInfo: BlockInfo) :
    BlockCreator(DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_wall" }) {

    override fun shouldCreate(): Boolean {
        if(dbi.existingBlock is AbstractGlassBlock) return false
        return super.shouldCreate()
    }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                if(existingBlock is Oxidizable) {
                    OxidizableWallBlock(existingBlock.degradationLevel, existingBlock.copySettings())
                }
                else {
                    WallBlock(existingBlock.copySettings())
                }
            )

            Registry.register(
                Registry.ITEM,
                identifier,
                BlockItem(block, Item.Settings().group(ItemGroup.DECORATIONS))
            )
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
                    }
                  },
                  "pattern": [
                    "###",
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
                  "count": 1,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_cobblestone_stonecutting", stonecuttingRecipe)

            builder.addTag("walls", identifier.toString())
        }
    }
}