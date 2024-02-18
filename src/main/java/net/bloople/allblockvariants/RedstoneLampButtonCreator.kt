package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.RedstoneLampButtonBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.Blocks
import net.minecraft.block.ButtonBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry


class RedstoneLampButtonCreator(private val metrics: Metrics, blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_button" }

    override fun doCreateCommon() {
        with(dbi) {
            val existingButton = Blocks.STONE_BUTTON as ButtonBlock

            block = Registry.register(
                Registries.BLOCK,
                identifier,
                RedstoneLampButtonBlock(
                    existingBlock.copySettings(),
                    existingButton.blockSetType,
                    existingButton.pressTicks,
                    existingButton.wooden
                )
            )
            metrics.common.blocksAdded++

            item = Registry.register(
                Registries.ITEM,
                identifier,
                BlockItem(block, Item.Settings())
            )
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register {
                it.add(item)
            }
            metrics.common.itemsAdded++
        }
    }

    @Environment(value= EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            val blockState = """
                {
                  "variants": {
                    "face=ceiling,facing=east,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "x": 180,
                      "y": 270
                    },
                    "face=ceiling,facing=east,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "x": 180,
                      "y": 270
                    },
                    "face=ceiling,facing=north,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "x": 180,
                      "y": 180
                    },
                    "face=ceiling,facing=north,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "x": 180,
                      "y": 180
                    },
                    "face=ceiling,facing=south,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "x": 180
                    },
                    "face=ceiling,facing=south,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "x": 180
                    },
                    "face=ceiling,facing=west,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "x": 180,
                      "y": 90
                    },
                    "face=ceiling,facing=west,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "x": 180,
                      "y": 90
                    },
                    "face=floor,facing=east,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "y": 90
                    },
                    "face=floor,facing=east,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "y": 90
                    },
                    "face=floor,facing=north,powered=false,lit=false": {
                      "model": "$blockBlockId"
                    },
                    "face=floor,facing=north,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed"
                    },
                    "face=floor,facing=south,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "y": 180
                    },
                    "face=floor,facing=south,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "y": 180
                    },
                    "face=floor,facing=west,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "y": 270
                    },
                    "face=floor,facing=west,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "y": 270
                    },
                    "face=wall,facing=east,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 90,
                      "y": 90
                    },
                    "face=wall,facing=east,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "uvlock": true,
                      "x": 90,
                      "y": 90
                    },
                    "face=wall,facing=north,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 90
                    },
                    "face=wall,facing=north,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "uvlock": true,
                      "x": 90
                    },
                    "face=wall,facing=south,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 90,
                      "y": 180
                    },
                    "face=wall,facing=south,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "uvlock": true,
                      "x": 90,
                      "y": 180
                    },
                    "face=wall,facing=west,powered=false,lit=false": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 90,
                      "y": 270
                    },
                    "face=wall,facing=west,powered=true,lit=false": {
                      "model": "${blockBlockId}_pressed",
                      "uvlock": true,
                      "x": 90,
                      "y": 270
                    },
                    "face=ceiling,facing=east,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "x": 180,
                      "y": 270
                    },
                    "face=ceiling,facing=east,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
                      "x": 180,
                      "y": 270
                    },
                    "face=ceiling,facing=north,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "x": 180,
                      "y": 180
                    },
                    "face=ceiling,facing=north,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
                      "x": 180,
                      "y": 180
                    },
                    "face=ceiling,facing=south,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "x": 180
                    },
                    "face=ceiling,facing=south,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
                      "x": 180
                    },
                    "face=ceiling,facing=west,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "x": 180,
                      "y": 90
                    },
                    "face=ceiling,facing=west,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
                      "x": 180,
                      "y": 90
                    },
                    "face=floor,facing=east,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "y": 90
                    },
                    "face=floor,facing=east,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
                      "y": 90
                    },
                    "face=floor,facing=north,powered=false,lit=true": {
                      "model": "${blockBlockId}_on"
                    },
                    "face=floor,facing=north,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on"
                    },
                    "face=floor,facing=south,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "y": 180
                    },
                    "face=floor,facing=south,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
                      "y": 180
                    },
                    "face=floor,facing=west,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "y": 270
                    },
                    "face=floor,facing=west,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
                      "y": 270
                    },
                    "face=wall,facing=east,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "uvlock": true,
                      "x": 90,
                      "y": 90
                    },
                    "face=wall,facing=east,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
                      "uvlock": true,
                      "x": 90,
                      "y": 90
                    },
                    "face=wall,facing=north,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "uvlock": true,
                      "x": 90
                    },
                    "face=wall,facing=north,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
                      "uvlock": true,
                      "x": 90
                    },
                    "face=wall,facing=south,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "uvlock": true,
                      "x": 90,
                      "y": 180
                    },
                    "face=wall,facing=south,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
                      "uvlock": true,
                      "x": 90,
                      "y": 180
                    },
                    "face=wall,facing=west,powered=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "uvlock": true,
                      "x": 90,
                      "y": 270
                    },
                    "face=wall,facing=west,powered=true,lit=true": {
                      "model": "${blockBlockId}_pressed_on",
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
                    "texture": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val inventoryBlockModel = """
                {
                  "parent": "minecraft:block/button_inventory",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_inventory", inventoryBlockModel)

            val pressedBlockModel = """
                {
                  "parent": "minecraft:block/button_pressed",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_pressed", pressedBlockModel)

            val onBlockModel = """
                {
                  "parent": "minecraft:block/button",
                  "textures": {
                    "texture": "${existingBlockTextureId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_on", onBlockModel)

            val onPressedBlockModel = """
                {
                  "parent": "minecraft:block/button_pressed",
                  "textures": {
                    "texture": "${existingBlockTextureId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_pressed_on", onPressedBlockModel)

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
                  "type": "minecraft:crafting_shapeless",
                  "ingredients": [
                    {
                      "item": "$existingIdentifier"
                    },
                    {
                      "item": "${ModStickCreator.identifier}"
                    }
                  ],
                  "result": {
                    "item": "$identifier",
                    "count": 1
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            builder.addBlockTag("buttons", identifier.toString())
            builder.addItemTag("buttons", identifier.toString())
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "ingredients": [
                    {
                      "item": "$existingIdentifier"
                    },
                    {
                      "item": "${ModStickCreator.identifier}"
                    }
                  ],
                  "result": {
                    "item": "$vanillaIdentifier",
                    "count": 1
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)
        }
    }
}