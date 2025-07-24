package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.*
import net.bloople.allblockvariants.blocks.RedstoneLampFenceBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups


class RedstoneLampFenceCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_fence" }

    override fun common() {
        with(dbi) {
            registerBlock(RedstoneLampFenceBlock(blockSettings))
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
                      },
                      "when": {
                        "lit": "false"
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side",
                        "uvlock": true
                      },
                      "when": {
                        "AND": [
                          { "north": "true" },
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
                          { "east": "true" },
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
                          { "south": "true" },
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
                          { "west": "true" },
                          { "lit": "false" }
                        ]
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_post_on"
                      },
                      "when": {
                        "lit": "true"
                      }
                    },
                    {
                      "apply": {
                        "model": "${blockBlockId}_side_on",
                        "uvlock": true
                      },
                      "when": {
                        "AND": [
                          { "north": "true" },
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
                          { "east": "true" },
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
                          { "south": "true" },
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
                          { "west": "true" },
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

            val onPostBlockModel = """
                {
                  "parent": "minecraft:block/fence_post",
                  "textures": {
                    "texture": "${existingBlockTextureId}_on"
                  }
                }
            """
            builder.addBlockModel("${blockName}_post_on", onPostBlockModel)

            val onSideBlockModel = """
                {
                  "parent": "minecraft:block/fence_side",
                  "textures": {
                    "texture": "${existingBlockTextureId}_on"
                  }
                }
            """
            builder.addBlockModel("${blockName}_side_on", onSideBlockModel)

            builder.addItemModelDefinition(blockName, id("${blockBlockId}_inventory"))
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
                  "category": "redstone",
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