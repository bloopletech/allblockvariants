package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.*
import net.bloople.allblockvariants.blocks.RedstoneLampFenceGateBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups


class RedstoneLampFenceGateCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_fence_gate" }

    override fun common() {
        with(dbi) {
            registerBlock(RedstoneLampFenceGateBlock(blockInfo.woodType, blockSettings))
            registerItem(BlockItem(block, itemSettings), ItemGroups.REDSTONE)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun client(builder: ResourcePackBuilder) {
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

            builder.addItemModelDefinition(blockName, id(blockBlockId))
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