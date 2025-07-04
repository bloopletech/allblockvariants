package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.RedstoneLampFenceGateBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups


class RedstoneLampFenceGateCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_fence_gate" }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(RedstoneLampFenceGateBlock(blockInfo.woodType, blockSettings))
            registerItem(BlockItem(block, Item.Settings()), ItemGroups.REDSTONE)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        createClientCommon(builder)

        with(dbi) {
            val blockState = """
                {
                  "variants": {
                    "facing=east,in_wall=false,open=false,lit=false": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=false,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=true,open=false,lit=false": {
                      "model": "${blockBlockId}_wall",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=true,open=true,lit=false": {
                      "model": "${blockBlockId}_wall_open",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,in_wall=false,open=false,lit=false": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=false,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=true,open=false,lit=false": {
                      "model": "${blockBlockId}_wall",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=true,open=true,lit=false": {
                      "model": "${blockBlockId}_wall_open",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=south,in_wall=false,open=false,lit=false": {
                      "model": "$blockBlockId",
                      "uvlock": true
                    },
                    "facing=south,in_wall=false,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "uvlock": true
                    },
                    "facing=south,in_wall=true,open=false,lit=false": {
                      "model": "${blockBlockId}_wall",
                      "uvlock": true
                    },
                    "facing=south,in_wall=true,open=true,lit=false": {
                      "model": "${blockBlockId}_wall_open",
                      "uvlock": true
                    },
                    "facing=west,in_wall=false,open=false,lit=false": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=false,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=true,open=false,lit=false": {
                      "model": "${blockBlockId}_wall",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=true,open=true,lit=false": {
                      "model": "${blockBlockId}_wall_open",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=east,in_wall=false,open=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=false,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=true,open=false,lit=true": {
                      "model": "${blockBlockId}_wall_on",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=true,open=true,lit=true": {
                      "model": "${blockBlockId}_wall_open_on",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,in_wall=false,open=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=false,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=true,open=false,lit=true": {
                      "model": "${blockBlockId}_wall_on",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=true,open=true,lit=true": {
                      "model": "${blockBlockId}_wall_open_on",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=south,in_wall=false,open=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "uvlock": true
                    },
                    "facing=south,in_wall=false,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "uvlock": true
                    },
                    "facing=south,in_wall=true,open=false,lit=true": {
                      "model": "${blockBlockId}_wall_on",
                      "uvlock": true
                    },
                    "facing=south,in_wall=true,open=true,lit=true": {
                      "model": "${blockBlockId}_wall_open_on",
                      "uvlock": true
                    },
                    "facing=west,in_wall=false,open=false,lit=true": {
                      "model": "${blockBlockId}_on",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=false,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=true,open=false,lit=true": {
                      "model": "${blockBlockId}_wall_on",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=true,open=true,lit=true": {
                      "model": "${blockBlockId}_wall_open_on",
                      "uvlock": true,
                      "y": 90
                    }
                  }
                }
            """
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel(blockName, blockModel)

            val openBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_open",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_open", openBlockModel)

            val wallBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_wall",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_wall", wallBlockModel)

            val wallOpenBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_wall_open",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_wall_open", wallOpenBlockModel)

            val onBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate",
                  "textures": {
                    "texture": "${existingBlockTextureId}_on"
                  }
                }
            """
            builder.addBlockModel("${blockName}_on", onBlockModel)

            val onOpenBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_open",
                  "textures": {
                    "texture": "${existingBlockTextureId}_on"
                  }
                }
            """
            builder.addBlockModel("${blockName}_open_on", onOpenBlockModel)

            val onWallBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_wall",
                  "textures": {
                    "texture": "${existingBlockTextureId}_on"
                  }
                }
            """
            builder.addBlockModel("${blockName}_wall_on", onWallBlockModel)

            val onWallOpenBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_wall_open",
                  "textures": {
                    "texture": "${existingBlockTextureId}_on"
                  }
                }
            """
            builder.addBlockModel("${blockName}_wall_open_on", onWallOpenBlockModel)

            val itemModel = """
                {
                  "parent": "$blockBlockId"
                }
            """
            builder.addItemModel(blockName, itemModel)
        }
    }

    override fun doCreateServer(builder: ResourcePackBuilder) {
        createServerCommon(builder)

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
            """
            builder.addBlockLootTable(blockName, lootTable)

            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "redstone",
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
                    "#W#",
                    "#W#"
                  ],
                  "result": {
                    "id": "$identifier"
                  }
                }
            """
            builder.addRecipe(blockName, recipe)

            builder.addBlockTag("fence_gates", identifier)
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "redstone",
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
                    "#W#",
                    "#W#"
                  ],
                  "result": {
                    "id": "$vanillaIdentifier"
                  }
                }
            """
            builder.addRecipe(blockName, recipe)
        }
    }
}