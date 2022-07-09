package net.bloople.allblockvariants

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.StoneButtonBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class ButtonCreator(private val builder: ResourcePackBuilder) {
    fun create(existingBlock: Block, mineableBy: MiningTool = MiningTool.Pickaxe, needsTool: MiningToolLevel? = null) {
        val existingIdentifier = Registry.BLOCK.getId(existingBlock)
        val existingBlockName = existingIdentifier.path
        val existingBlockBlockId = "minecraft:block/$existingBlockName"
        val blockName = "${existingBlockName}_button"
        val blockBlockId = "$MOD_ID:block/$blockName"
        val identifier = Identifier(MOD_ID, blockName)

        val block: Block = Registry.register(
            Registry.BLOCK,
            identifier,
            StoneButtonBlock(AbstractBlock.Settings.copy(existingBlock))
        )

        val item: Item = Registry.register(
            Registry.ITEM,
            identifier,
            BlockItem(block, Item.Settings().group(ItemGroup.REDSTONE))
        )

        Util.copySpecialProperties(existingBlock, block)
        Util.copySpecialProperties(existingBlock.asItem(), item)

        val blockState = """
            {
              "variants": {
                "face=ceiling,facing=east,powered=false": {
                  "model": "$blockBlockId",
                  "x": 180,
                  "y": 270
                },
                "face=ceiling,facing=east,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "x": 180,
                  "y": 270
                },
                "face=ceiling,facing=north,powered=false": {
                  "model": "$blockBlockId",
                  "x": 180,
                  "y": 180
                },
                "face=ceiling,facing=north,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "x": 180,
                  "y": 180
                },
                "face=ceiling,facing=south,powered=false": {
                  "model": "$blockBlockId",
                  "x": 180
                },
                "face=ceiling,facing=south,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "x": 180
                },
                "face=ceiling,facing=west,powered=false": {
                  "model": "$blockBlockId",
                  "x": 180,
                  "y": 90
                },
                "face=ceiling,facing=west,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "x": 180,
                  "y": 90
                },
                "face=floor,facing=east,powered=false": {
                  "model": "$blockBlockId",
                  "y": 90
                },
                "face=floor,facing=east,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "y": 90
                },
                "face=floor,facing=north,powered=false": {
                  "model": "$blockBlockId"
                },
                "face=floor,facing=north,powered=true": {
                  "model": "${blockBlockId}_pressed"
                },
                "face=floor,facing=south,powered=false": {
                  "model": "$blockBlockId",
                  "y": 180
                },
                "face=floor,facing=south,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "y": 180
                },
                "face=floor,facing=west,powered=false": {
                  "model": "$blockBlockId",
                  "y": 270
                },
                "face=floor,facing=west,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "y": 270
                },
                "face=wall,facing=east,powered=false": {
                  "model": "$blockBlockId",
                  "uvlock": true,
                  "x": 90,
                  "y": 90
                },
                "face=wall,facing=east,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "uvlock": true,
                  "x": 90,
                  "y": 90
                },
                "face=wall,facing=north,powered=false": {
                  "model": "$blockBlockId",
                  "uvlock": true,
                  "x": 90
                },
                "face=wall,facing=north,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "uvlock": true,
                  "x": 90
                },
                "face=wall,facing=south,powered=false": {
                  "model": "$blockBlockId",
                  "uvlock": true,
                  "x": 90,
                  "y": 180
                },
                "face=wall,facing=south,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "uvlock": true,
                  "x": 90,
                  "y": 180
                },
                "face=wall,facing=west,powered=false": {
                  "model": "$blockBlockId",
                  "uvlock": true,
                  "x": 90,
                  "y": 270
                },
                "face=wall,facing=west,powered=true": {
                  "model": "${blockBlockId}_pressed",
                  "uvlock": true,
                  "x": 90,
                  "y": 270
                }
              }
            }
        """.trimIndent()
        builder.addBlockState(blockName, blockState)

        val blockModel = """
            {
              "parent": "minecraft:block/button",
              "textures": {
                "texture": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel(blockName, blockModel)

        val inventoryBlockModel = """
            {
              "parent": "minecraft:block/button_inventory",
              "textures": {
                "texture": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel("${blockName}_inventory", inventoryBlockModel)

        val pressedBlockModel = """
            {
              "parent": "minecraft:block/button_pressed",
              "textures": {
                "texture": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel("${blockName}_pressed", pressedBlockModel)

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
              "type": "minecraft:crafting_shapeless",
              "group": "wooden_button",
              "ingredients": [
                {
                  "item": "$existingIdentifier"
                }
              ],
              "result": {
                "item": "$identifier"
              }
            }
        """.trimIndent()
        builder.addRecipe(blockName, recipe)

        builder.addTag("buttons", identifier.toString())
        builder.addMineableTag(mineableBy, identifier.toString())
        needsTool?.let { builder.addNeedsToolTag(it, identifier.toString()) }
        builder.addTranslation("block.$MOD_ID.$blockName", Util.toTitleCase(blockName))
    }
}