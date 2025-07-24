package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.*
import net.bloople.allblockvariants.blocks.OxidizableButtonBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups


class ButtonCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_button" }

    override fun shouldCreate(): Boolean {
        if(dbi.existingBlock is TransparentBlock) return false
        return super.shouldCreate()
    }

    override fun common() {
        with(dbi) {
            val existingButton = Blocks.STONE_BUTTON as ButtonBlock

            registerBlock(when(existingBlock) {
                is Oxidizable -> OxidizableButtonBlock(
                    existingBlock.degradationLevel,
                    existingButton.blockSetType,
                    existingButton.pressTicks,
                    blockSettings
                )
                else -> ButtonBlock(existingButton.blockSetType, existingButton.pressTicks, blockSettings)
            })

            registerItem(BlockItem(block, itemSettings), ItemGroups.REDSTONE)
        }
    }

    @Environment(value= EnvType.CLIENT)
    override fun client(builder: ResourcePackBuilder) {
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
            """
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "minecraft:block/button",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel(blockName, blockModel)

            val inventoryBlockModel = """
                {
                  "parent": "minecraft:block/button_inventory",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_inventory", inventoryBlockModel)

            val pressedBlockModel = """
                {
                  "parent": "minecraft:block/button_pressed",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_pressed", pressedBlockModel)

            builder.addItemModelDefinition(blockName, id("${blockBlockId}_inventory"))
        }
    }

    override fun server(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockLootTable(dbi)

            val recipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "category": "redstone",
                  "ingredients": [
                    "$existingIdentifier",
                    "${ModStickCreator.Companion.identifier}"
                  ],
                  "result": {
                    "id": "$identifier",
                    "count": 1
                  }
                }
            """
            builder.addRecipe(blockName, recipe)

            builder.addBlockTag("buttons", identifier)
            builder.addItemTag("buttons", identifier)
        }
    }

    override fun vanillaBlockServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "category": "redstone",
                  "ingredients": [
                    "$existingIdentifier",
                    "${ModStickCreator.Companion.identifier}"
                  ],
                  "result": {
                    "id": "$vanillaIdentifier",
                    "count": 1
                  }
                }
            """
            builder.addRecipe(blockName, recipe)
        }
    }

    companion object {
        fun getCreator(blockInfo: BlockInfo): BlockCreator {
            return when(blockInfo.block) {
                is RedstoneLampBlock -> RedstoneLampButtonCreator(blockInfo)
                else -> ButtonCreator(blockInfo)
            }
        }
    }
}