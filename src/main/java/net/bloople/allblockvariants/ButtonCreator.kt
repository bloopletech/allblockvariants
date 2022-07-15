package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.AbstractBlock
import net.minecraft.block.StoneButtonBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

class ButtonCreator(blockInfo: BlockInfo) :
    BlockCreator(DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_button" }) {
    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                StoneButtonBlock(AbstractBlock.Settings.copy(existingBlock))
            )

            Registry.register(
                Registry.ITEM,
                identifier,
                BlockItem(block, Item.Settings().group(ItemGroup.REDSTONE))
            )
        }
    }

    @Environment(value= EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
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

            builder.addTranslation("block.$MOD_ID.$blockName", Util.toTitleCase(blockName))
        }
    }

    override fun doCreateServer(builder: ResourcePackBuilder) {
        registerBlockCommon(builder)

        with(dbi) {
//            val lootTable = """
//                {
//                  "type": "minecraft:block",
//                  "pools": [
//                    {
//                      "bonus_rolls": 0.0,
//                      "conditions": [
//                        {
//                          "condition": "minecraft:survives_explosion"
//                        }
//                      ],
//                      "entries": [
//                        {
//                          "type": "minecraft:item",
//                          "name": "$identifier"
//                        }
//                      ],
//                      "rolls": 1.0
//                    }
//                  ]
//                }
//            """.trimIndent()
//            builder.addBlockLootTable(blockName, lootTable)

            val recipe = """
                {
                  "type": "minecraft:crafting_shapeless",
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

            val backupRecipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "ingredients": [
                    {
                      "item": "$existingIdentifier"
                    },
                    {
                      "item": "minecraft:shears"
                    }
                  ],
                  "result": {
                    "item": "$identifier",
                    "count": 9
                  }
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_backup", backupRecipe)

            builder.addTag("buttons", identifier.toString())
        }
    }
}