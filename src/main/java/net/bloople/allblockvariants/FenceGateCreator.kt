package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.AbstractBlock
import net.minecraft.block.FenceGateBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

class FenceGateCreator(builder: ResourcePackBuilder, blockInfo: BlockInfo) :
    BlockCreator(builder, DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_fence_gate" }) {
    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                FenceGateBlock(AbstractBlock.Settings.copy(existingBlock))
            )

            Registry.register(
                Registry.ITEM,
                identifier,
                BlockItem(block, Item.Settings().group(ItemGroup.REDSTONE))
            )
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient() {
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
                    "texture": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val openBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_open",
                  "textures": {
                    "texture": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_open", openBlockModel)

            val wallBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_wall",
                  "textures": {
                    "texture": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_wall", wallBlockModel)

            val wallOpenBlockModel = """
                {
                  "parent": "minecraft:block/template_fence_gate_wall_open",
                  "textures": {
                    "texture": "$existingBlockBlockId"
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

    override fun doCreateServer() {
        applyBlockInfo()

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
                    }
                  },
                  "pattern": [
                    "#W#",
                    "#W#"
                  ],
                  "result": {
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            builder.addTag("fence_gates", identifier.toString())
        }
    }
}