package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.RedstoneLampFenceBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry


class RedstoneLampFenceCreator(private val metrics: Metrics, blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_fence" }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registries.BLOCK,
                identifier,
                RedstoneLampFenceBlock(existingBlock.copySettings())
            )
            metrics.common.blocksAdded++

            item = Registry.register(
                Registries.ITEM,
                identifier,
                BlockItem(block, Item.Settings())
            )
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register {
                it.add(item)
            }
            metrics.common.itemsAdded++
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
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val inventoryBlockModel = """
                {
                  "parent": "minecraft:block/fence_inventory",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_inventory", inventoryBlockModel)

            val postBlockModel = """
                {
                  "parent": "minecraft:block/fence_post",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_post", postBlockModel)

            val sideBlockModel = """
                {
                  "parent": "minecraft:block/fence_side",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_side", sideBlockModel)

            val onPostBlockModel = """
                {
                  "parent": "minecraft:block/fence_post",
                  "textures": {
                    "texture": "${existingBlockTextureId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_post_on", onPostBlockModel)

            val onSideBlockModel = """
                {
                  "parent": "minecraft:block/fence_side",
                  "textures": {
                    "texture": "${existingBlockTextureId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_side_on", onSideBlockModel)

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
                      "item": "minecraft:stick"
                    },
                    "W": {
                      "item": "$existingIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.identifier}"
                    }
                  },
                  "pattern": [
                    "  !",
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

            builder.addBlockTag("fences", identifier.toString())
            builder.addItemTag("fences", identifier.toString())
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "key": {
                    "#": {
                      "item": "minecraft:stick"
                    },
                    "W": {
                      "item": "$existingIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.identifier}"
                    }
                  },
                  "pattern": [
                    "  !",
                    "W#W",
                    "W#W"
                  ],
                  "result": {
                    "count": 3,
                    "item": "$vanillaIdentifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)
        }
    }
}