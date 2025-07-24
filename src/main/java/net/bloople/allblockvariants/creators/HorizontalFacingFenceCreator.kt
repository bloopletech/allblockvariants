package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.*
import net.bloople.allblockvariants.blocks.GlazedTerracottaFenceBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.FenceBlock
import net.minecraft.block.GlazedTerracottaBlock
import net.minecraft.block.TransparentBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups


class HorizontalFacingFenceCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_fence" }

    override fun shouldCreate(): Boolean {
        if(dbi.existingBlock is TransparentBlock) return false
        return super.shouldCreate()
    }

    override fun common() {
        with(dbi) {
            registerBlock(when(existingBlock) {
                is GlazedTerracottaBlock -> GlazedTerracottaFenceBlock(blockSettings)
                else -> FenceBlock(blockSettings)
            })

            registerItem(BlockItem(block, itemSettings), ItemGroups.FUNCTIONAL)
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
            """
            builder.addBlockState(blockName, blockState)

            val inventoryBlockModel = """
                {
                  "parent": "minecraft:block/fence_inventory",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_inventory", inventoryBlockModel)

            val postBlockModel = """
                {
                  "parent": "minecraft:block/fence_post",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_post", postBlockModel)

            val sideBlockModel = """
                {
                  "parent": "minecraft:block/fence_side",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_side", sideBlockModel)

            builder.addItemModelDefinition(blockName, id("${blockBlockId}_inventory"))
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
                    "#": "minecraft:stick",
                    "W": "$existingIdentifier",
                    "!": "${ModStickCreator.Companion.identifier}"
                  },
                  "pattern": [
                    "  !",
                    "W#W",
                    "W#W"
                  ],
                  "result": {
                    "count": 3,
                    "id": "$identifier"
                  }
                }
            """
            builder.addRecipe(blockName, recipe)

            builder.addBlockTag("fences", identifier)
            builder.addItemTag("fences", identifier)
        }
    }

    override fun vanillaBlockServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "misc",
                  "key": {
                    "#": "minecraft:stick",
                    "W": "$existingIdentifier",
                    "!": "${ModStickCreator.Companion.identifier}"
                  },
                  "pattern": [
                    "  !",
                    "W#W",
                    "W#W"
                  ],
                  "result": {
                    "count": 3,
                    "id": "$vanillaIdentifier"
                  }
                }
            """
            builder.addRecipe(blockName, recipe)
        }
    }
}