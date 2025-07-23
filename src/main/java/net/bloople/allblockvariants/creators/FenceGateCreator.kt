package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.BlockCreator
import net.bloople.allblockvariants.BlockInfo
import net.bloople.allblockvariants.DerivedBlockInfo
import net.bloople.allblockvariants.ModStickCreator
import net.bloople.allblockvariants.ResourcePackBuilder
import net.bloople.allblockvariants.blocks.OxidizableFenceGateBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.FenceGateBlock
import net.minecraft.block.Oxidizable
import net.minecraft.block.RedstoneLampBlock
import net.minecraft.block.TransparentBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups


class FenceGateCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_fence_gate" }

    override fun shouldCreate(): Boolean {
        if(dbi.existingBlock is TransparentBlock) return false
        return super.shouldCreate()
    }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(when(existingBlock) {
                is Oxidizable -> OxidizableFenceGateBlock(
                    existingBlock.degradationLevel,
                    blockInfo.woodType,
                    blockSettings
                )
                else -> FenceGateBlock(blockInfo.woodType, blockSettings)
            })

            registerItem(BlockItem(block, Item.Settings()), ItemGroups.REDSTONE)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            val blockState = """
                {
                  "variants": {
                    "facing=east,in_wall=false,open=false": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=false,open=true": {
                      "model": "${blockBlockId}_open",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=true,open=false": {
                      "model": "${blockBlockId}_wall",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=true,open=true": {
                      "model": "${blockBlockId}_wall_open",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,in_wall=false,open=false": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=false,open=true": {
                      "model": "${blockBlockId}_open",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=true,open=false": {
                      "model": "${blockBlockId}_wall",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=true,open=true": {
                      "model": "${blockBlockId}_wall_open",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=south,in_wall=false,open=false": {
                      "model": "$blockBlockId",
                      "uvlock": true
                    },
                    "facing=south,in_wall=false,open=true": {
                      "model": "${blockBlockId}_open",
                      "uvlock": true
                    },
                    "facing=south,in_wall=true,open=false": {
                      "model": "${blockBlockId}_wall",
                      "uvlock": true
                    },
                    "facing=south,in_wall=true,open=true": {
                      "model": "${blockBlockId}_wall_open",
                      "uvlock": true
                    },
                    "facing=west,in_wall=false,open=false": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=false,open=true": {
                      "model": "${blockBlockId}_open",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=true,open=false": {
                      "model": "${blockBlockId}_wall",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=true,open=true": {
                      "model": "${blockBlockId}_wall_open",
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

            val itemModel = """
                {
                  "parent": "$blockBlockId"
                }
            """
            builder.addItemModel(blockName, itemModel)
        }
    }

    override fun doCreateServer(builder: ResourcePackBuilder) {
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
                      "item": "${ModStickCreator.Companion.identifier}"
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
                      "item": "${ModStickCreator.Companion.identifier}"
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

    companion object {
        fun getCreator(blockInfo: BlockInfo): BlockCreator {
            return when(blockInfo.block) {
                is RedstoneLampBlock -> RedstoneLampFenceGateCreator(blockInfo)
                else -> FenceGateCreator(blockInfo)
            }
        }
    }
}