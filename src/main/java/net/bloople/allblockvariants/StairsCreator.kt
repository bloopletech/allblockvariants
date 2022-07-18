package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.GlassStairsBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.AbstractBlock
import net.minecraft.block.GlassBlock
import net.minecraft.block.StairsBlock
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

class StairsCreator(blockInfo: BlockInfo) :
    BlockCreator(DerivedBlockInfo(blockInfo) { "${transformBlockName(existingBlockName)}_stairs" }) {

    override fun doCreateCommon() {
        with(dbi) {
            val isGlass = dbi.existingBlock is GlassBlock
            val bSettings = AbstractBlock.Settings.copy(existingBlock)
            val bState = existingBlock.defaultState

            block = Registry.register(
                Registry.BLOCK,
                identifier,
                if(isGlass) GlassStairsBlock(bState, bSettings.nonOpaque()) else StairsBlock(bState, bSettings)
            )

            if(isGlass) {
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
            }

            Registry.register(
                Registry.ITEM,
                identifier,
                BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
            )
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            val blockState = """
                {
                  "variants": {
                    "facing=east,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner"
                    },
                    "facing=east,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer"
                    },
                    "facing=east,half=bottom,shape=straight": {
                      "model": "$blockBlockId"
                    },
                    "facing=east,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=south,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner"
                    },
                    "facing=south,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer"
                    },
                    "facing=south,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=top,shape=inner_left": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=inner_right": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=outer_left": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=outer_right": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=straight": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    }
                  }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "minecraft:block/stairs",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "side": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val innerBlockModel = """
                {
                  "parent": "minecraft:block/inner_stairs",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "side": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_inner", innerBlockModel)

            val outerBlockModel = """
                {
                  "parent": "minecraft:block/outer_stairs",
                  "textures": {
                    "bottom": "$existingBlockBlockId",
                    "side": "$existingBlockBlockId",
                    "top": "$existingBlockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_outer", outerBlockModel)

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
                      "item": "$existingIdentifier"
                    }
                  },
                  "pattern": [
                    "#  ",
                    "## ",
                    "###"
                  ],
                  "result": {
                    "count": 4,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            val stonecuttingRecipe = """
                {
                  "type": "minecraft:stonecutting",
                  "count": 1,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_cobblestone_stonecutting", stonecuttingRecipe)

            builder.addTag("stairs", identifier.toString())
        }
    }
}