package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.RedstoneLampTrapdoorBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.BlockState
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.EntityType
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import java.awt.image.BufferedImage


class RedstoneLampTrapdoorCreator(metrics: Metrics, blockInfo: BlockInfo) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_trapdoor" }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(RedstoneLampTrapdoorBlock(existingBlock.copySettings().nonOpaque()
                .allowsSpawning { _: BlockState, _: BlockView, _: BlockPos, _: EntityType<*> -> false }))
            registerItem(BlockItem(block, Item.Settings().group(ItemGroup.REDSTONE)))
        }
    }

    @Environment(value= EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())

            builder.addBlockTexture(blockName) { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createTrapdoorBlockTexture)
            }

            builder.addBlockTexture("${blockName}_on") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/${existingBlockTextureName}_on.png",
                    ::createTrapdoorBlockTexture)
            }

            val blockState = """
                {
                  "variants": {
                    "facing=east,half=bottom,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom",
                      "y": 90
                    },
                    "facing=east,half=bottom,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "y": 90
                    },
                    "facing=east,half=top,open=false,lit=false": {
                      "model": "${blockBlockId}_top",
                      "y": 90
                    },
                    "facing=east,half=top,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=bottom,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom"
                    },
                    "facing=north,half=bottom,open=true,lit=false": {
                      "model": "${blockBlockId}_open"
                    },
                    "facing=north,half=top,open=false,lit=false": {
                      "model": "${blockBlockId}_top"
                    },
                    "facing=north,half=top,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=bottom,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom",
                      "y": 180
                    },
                    "facing=south,half=bottom,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "y": 180
                    },
                    "facing=south,half=top,open=false,lit=false": {
                      "model": "${blockBlockId}_top",
                      "y": 180
                    },
                    "facing=south,half=top,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "x": 180,
                      "y": 0
                    },
                    "facing=west,half=bottom,open=false,lit=false": {
                      "model": "${blockBlockId}_bottom",
                      "y": 270
                    },
                    "facing=west,half=bottom,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "y": 270
                    },
                    "facing=west,half=top,open=false,lit=false": {
                      "model": "${blockBlockId}_top",
                      "y": 270
                    },
                    "facing=west,half=top,open=true,lit=false": {
                      "model": "${blockBlockId}_open",
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=bottom,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_on",
                      "y": 90
                    },
                    "facing=east,half=bottom,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "y": 90
                    },
                    "facing=east,half=top,open=false,lit=true": {
                      "model": "${blockBlockId}_top_on",
                      "y": 90
                    },
                    "facing=east,half=top,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=bottom,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_on"
                    },
                    "facing=north,half=bottom,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on"
                    },
                    "facing=north,half=top,open=false,lit=true": {
                      "model": "${blockBlockId}_top_on"
                    },
                    "facing=north,half=top,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=bottom,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_on",
                      "y": 180
                    },
                    "facing=south,half=bottom,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "y": 180
                    },
                    "facing=south,half=top,open=false,lit=true": {
                      "model": "${blockBlockId}_top_on",
                      "y": 180
                    },
                    "facing=south,half=top,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "x": 180,
                      "y": 0
                    },
                    "facing=west,half=bottom,open=false,lit=true": {
                      "model": "${blockBlockId}_bottom_on",
                      "y": 270
                    },
                    "facing=west,half=bottom,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "y": 270
                    },
                    "facing=west,half=top,open=false,lit=true": {
                      "model": "${blockBlockId}_top_on",
                      "y": 270
                    },
                    "facing=west,half=top,open=true,lit=true": {
                      "model": "${blockBlockId}_open_on",
                      "x": 180,
                      "y": 90
                    }
                  }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val bottomBlockModel = """
                {
                  "parent": "minecraft:block/template_orientable_trapdoor_bottom",
                  "textures": {
                    "texture": "$blockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom", bottomBlockModel)

            val topBlockModel = """
                {
                  "parent": "minecraft:block/template_orientable_trapdoor_top",
                  "textures": {
                    "texture": "$blockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top", topBlockModel)

            val openBlockModel = """
                {
                  "parent": "minecraft:block/template_orientable_trapdoor_open",
                  "textures": {
                    "texture": "$blockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_open", openBlockModel)

            val onBottomBlockModel = """
                {
                  "parent": "minecraft:block/template_orientable_trapdoor_bottom",
                  "textures": {
                    "texture": "${blockBlockId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_bottom_on", onBottomBlockModel)

            val onTopBlockModel = """
                {
                  "parent": "minecraft:block/template_orientable_trapdoor_top",
                  "textures": {
                    "texture": "${blockBlockId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_top_on", onTopBlockModel)

            val onOpenBlockModel = """
                {
                  "parent": "minecraft:block/template_orientable_trapdoor_open",
                  "textures": {
                    "texture": "${blockBlockId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_open_on", onOpenBlockModel)

            val itemModel = """
                {
                  "parent": "${blockBlockId}_bottom"
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
                    },
                    "!": {
                      "item": "${ModStickCreator.identifier}"
                    }
                  },
                  "pattern": [
                    "  !",
                    "###",
                    "###"
                  ],
                  "result": {
                    "count": 2,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            builder.addBlockTag("trapdoors", identifier.toString())
            builder.addItemTag("trapdoors", identifier.toString())
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "key": {
                    "#": {
                      "item": "$existingIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.identifier}"
                    }
                  },
                  "pattern": [
                    "  !",
                    "###",
                    "###"
                  ],
                  "result": {
                    "count": 2,
                    "item": "$vanillaIdentifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createTrapdoorBlockTexture(input: BufferedImage): BufferedImage {
        return input.apply {
            val blank = input.blankClone()

            raster.setRect(3, 3, blank.getData(3, 3, 3, 3))
            raster.setRect(10, 3, blank.getData(10, 3, 3, 3))
            raster.setRect(3, 10, blank.getData(3, 10, 3, 3))
            raster.setRect(10, 10, blank.getData(10, 10, 3, 3))
        }
    }
}