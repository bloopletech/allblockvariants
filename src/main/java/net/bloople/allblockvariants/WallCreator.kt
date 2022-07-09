package net.bloople.allblockvariants

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.WallBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class WallCreator(private val builder: ResourcePackBuilder) {
    fun create(existingBlock: Block, mineableBy: MiningTool = MiningTool.Pickaxe, needsTool: MiningToolLevel? = null) {
        val existingIdentifier = Registry.BLOCK.getId(existingBlock)
        val existingBlockName = existingIdentifier.path
        val existingBlockBlockId = "minecraft:block/$existingBlockName"
        val blockName = "${existingBlockName}_wall"
        val blockBlockId = "$MOD_ID:block/$blockName"
        val identifier = Identifier(MOD_ID, blockName)

        val block: Block = Registry.register(
            Registry.BLOCK,
            identifier,
            WallBlock(AbstractBlock.Settings.copy(existingBlock))
        )

        val item: Item = Registry.register(
            Registry.ITEM,
            identifier,
            BlockItem(block, Item.Settings().group(ItemGroup.DECORATIONS))
        )

        Util.copySpecialProperties(existingBlock, block)
        Util.copySpecialProperties(existingBlock.asItem(), item)

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
                "wall": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel("${blockName}_inventory", inventoryBlockModel)

        val postBlockModel = """
            {
              "parent": "minecraft:block/template_wall_post",
              "textures": {
                "wall": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel("${blockName}_post", postBlockModel)

        val sideBlockModel = """
            {
              "parent": "minecraft:block/template_wall_side",
              "textures": {
                "wall": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel("${blockName}_side", sideBlockModel)

        val sideTallBlockModel = """
            {
              "parent": "minecraft:block/template_wall_side_tall",
              "textures": {
                "wall": "$existingBlockBlockId"
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
        builder.addMineableTag(mineableBy, identifier.toString())
        needsTool?.let { builder.addNeedsToolTag(it, identifier.toString()) }
        builder.addTranslation("block.$MOD_ID.$blockName", Util.toTitleCase(blockName))
    }
}