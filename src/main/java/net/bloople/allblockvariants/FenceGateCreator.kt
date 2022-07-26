package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.OxidizableFenceGateBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.AbstractGlassBlock
import net.minecraft.block.FenceGateBlock
import net.minecraft.block.Oxidizable
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry


class FenceGateCreator(private val metrics: Metrics, blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_fence_gate" }

    override fun shouldCreate(): Boolean {
        if(dbi.existingBlock is AbstractGlassBlock) return false
        return super.shouldCreate()
    }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                if(existingBlock is Oxidizable) {
                    OxidizableFenceGateBlock(existingBlock.degradationLevel, existingBlock.copySettings())
                }
                else {
                    FenceGateBlock(existingBlock.copySettings())
                }
            )
            metrics.common.blocksAdded++

            Registry.register(
                Registry.ITEM,
                identifier,
                BlockItem(block, Item.Settings().group(ItemGroup.REDSTONE))
            )
            metrics.common.itemsAdded++
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
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val openBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_open",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_open", openBlockModel)

            val wallBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_wall",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_wall", wallBlockModel)

            val wallOpenBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_wall_open",
                  "textures": {
                    "texture": "$existingBlockTextureId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_wall_open", wallOpenBlockModel)

            val itemModel = """
                {
                  "parent": "$blockBlockId"
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
                    "#W#",
                    "#W#"
                  ],
                  "result": {
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            builder.addBlockTag("fence_gates", identifier.toString())
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
                    "#W#",
                    "#W#"
                  ],
                  "result": {
                    "item": "$vanillaIdentifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)
        }
    }
}