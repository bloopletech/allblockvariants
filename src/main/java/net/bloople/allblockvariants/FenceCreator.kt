package net.bloople.allblockvariants

import net.bloople.allblockvariants.Util.toTitleCase
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.FenceBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class FenceCreator(private val builder: ResourcePackBuilder) {
    fun create(existingBlock: Block) {
        val existingIdentifier = Registry.BLOCK.getId(existingBlock)
        val existingBlockName = existingIdentifier.path
        val existingBlockBlockId = "minecraft:block/$existingBlockName"
        val blockName = "${existingBlockName}_fence"
        val blockBlockId = "$MOD_ID:block/$blockName"
        val identifier = Identifier(MOD_ID, blockName)

        val block: Block = Registry.register(
            Registry.BLOCK,
            identifier,
            FenceBlock(AbstractBlock.Settings.copy(existingBlock))
        )

        Registry.register(
            Registry.ITEM,
            identifier,
            BlockItem(block, Item.Settings().group(ItemGroup.DECORATIONS))
        )

        val blockState = """
            {
              "multipart": [
                {
                  "apply": {
                    "model": "${blockBlockId}_post"
                  }
                },
                {
                  "apply": {
                    "model": "${blockBlockId}_side",
                    "uvlock": true
                  },
                  "when": {
                    "north": "true"
                  }
                },
                {
                  "apply": {
                    "model": "${blockBlockId}_side",
                    "uvlock": true,
                    "y": 90
                  },
                  "when": {
                    "east": "true"
                  }
                },
                {
                  "apply": {
                    "model": "${blockBlockId}_side",
                    "uvlock": true,
                    "y": 180
                  },
                  "when": {
                    "south": "true"
                  }
                },
                {
                  "apply": {
                    "model": "${blockBlockId}_side",
                    "uvlock": true,
                    "y": 270
                  },
                  "when": {
                    "west": "true"
                  }
                }
              ]
            }
        """.trimIndent()
        builder.addBlockState(blockName, blockState)

        val inventoryBlockModel = """
            {
              "parent": "minecraft:block/fence_inventory",
              "textures": {
                "texture": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel("${blockName}_inventory", inventoryBlockModel)

        val postBlockModel = """
            {
              "parent": "minecraft:block/fence_post",
              "textures": {
                "texture": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel("${blockName}_post", postBlockModel)

        val sideBlockModel = """
            {
              "parent": "minecraft:block/fence_side",
              "textures": {
                "texture": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel("${blockName}_side", sideBlockModel)

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
                  "item": "minecraft:stick"
                },
                "W": {
                  "item": "$existingIdentifier"
                }
              },
              "pattern": [
                "W#W",
                "W#W"
              ],
              "result": {
                "count": 3,
                "item": "$identifier"
              }
            }
        """.trimIndent()
        builder.addRecipe(blockName, recipe)

        builder.addTag("fences", identifier.toString())
        builder.addTranslation("block.$MOD_ID.$blockName", toTitleCase(blockName))
    }
}