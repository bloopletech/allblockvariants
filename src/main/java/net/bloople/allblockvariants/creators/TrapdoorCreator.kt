package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.*
import net.bloople.allblockvariants.blocks.OxidizableTrapdoorBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.*
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups
import java.awt.image.BufferedImage


class TrapdoorCreator(blockInfo: BlockInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_trapdoor" }

    override fun shouldCreate(): Boolean {
        if(dbi.existingBlock is TransparentBlock) return false
        if(dbi.existingBlock is GrassBlock) return false
        return super.shouldCreate()
    }

    override fun common() {
        with(dbi) {
            registerBlock(when(existingBlock) {
                is Oxidizable -> OxidizableTrapdoorBlock(
                    existingBlock.degradationLevel,
                    blockInfo.blockSetType,
                    blockSettings.nonOpaque().noSpawning()
                )
                else -> TrapdoorBlock(blockInfo.blockSetType, blockSettings.nonOpaque().noSpawning())
            })

            registerItem(BlockItem(block, itemSettings), ItemGroups.REDSTONE)
        }
    }

    @Environment(value= EnvType.CLIENT)
    override fun client(builder: ResourcePackBuilder) {
        with(dbi) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())

            builder.addBlockTexture(blockName) { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ::createTrapdoorBlockTexture)
            }

            val blockState = """
                {
                  "variants": {
                    "facing=east,half=bottom,open=false": {
                      "model": "${blockBlockId}_bottom",
                      "y": 90
                    },
                    "facing=east,half=bottom,open=true": {
                      "model": "${blockBlockId}_open",
                      "y": 90
                    },
                    "facing=east,half=top,open=false": {
                      "model": "${blockBlockId}_top",
                      "y": 90
                    },
                    "facing=east,half=top,open=true": {
                      "model": "${blockBlockId}_open",
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=bottom,open=false": {
                      "model": "${blockBlockId}_bottom"
                    },
                    "facing=north,half=bottom,open=true": {
                      "model": "${blockBlockId}_open"
                    },
                    "facing=north,half=top,open=false": {
                      "model": "${blockBlockId}_top"
                    },
                    "facing=north,half=top,open=true": {
                      "model": "${blockBlockId}_open",
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=bottom,open=false": {
                      "model": "${blockBlockId}_bottom",
                      "y": 180
                    },
                    "facing=south,half=bottom,open=true": {
                      "model": "${blockBlockId}_open",
                      "y": 180
                    },
                    "facing=south,half=top,open=false": {
                      "model": "${blockBlockId}_top",
                      "y": 180
                    },
                    "facing=south,half=top,open=true": {
                      "model": "${blockBlockId}_open",
                      "x": 180,
                      "y": 0
                    },
                    "facing=west,half=bottom,open=false": {
                      "model": "${blockBlockId}_bottom",
                      "y": 270
                    },
                    "facing=west,half=bottom,open=true": {
                      "model": "${blockBlockId}_open",
                      "y": 270
                    },
                    "facing=west,half=top,open=false": {
                      "model": "${blockBlockId}_top",
                      "y": 270
                    },
                    "facing=west,half=top,open=true": {
                      "model": "${blockBlockId}_open",
                      "x": 180,
                      "y": 90
                    }
                  }
                }
            """
            builder.addBlockState(blockName, blockState)

            val bottomBlockModel = """
                {
                  "parent": "minecraft:block/template_orientable_trapdoor_bottom",
                  "textures": {
                    "texture": "$blockBlockId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_bottom", bottomBlockModel)

            val topBlockModel = """
                {
                  "parent": "minecraft:block/template_orientable_trapdoor_top",
                  "textures": {
                    "texture": "$blockBlockId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_top", topBlockModel)

            val openBlockModel = """
                {
                  "parent": "minecraft:block/template_orientable_trapdoor_open",
                  "textures": {
                    "texture": "$blockBlockId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_open", openBlockModel)

            builder.addItemModelDefinition(blockName, id("${blockBlockId}_bottom"))
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
                    "#": "$existingIdentifier",
                    "!": "${ModStickCreator.Companion.identifier}"
                  },
                  "pattern": [
                    "  !",
                    "###",
                    "###"
                  ],
                  "result": {
                    "count": 2,
                    "id": "$identifier"
                  }
                }
            """
            builder.addRecipe(blockName, recipe)

            builder.addBlockTag("trapdoors", identifier)
            builder.addItemTag("trapdoors", identifier)
        }
    }

    override fun vanillaBlockServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "redstone",
                  "key": {
                    "#": "$existingIdentifier",
                    "!": "${ModStickCreator.Companion.identifier}"
                  },
                  "pattern": [
                    "  !",
                    "###",
                    "###"
                  ],
                  "result": {
                    "count": 2,
                    "id": "$vanillaIdentifier"
                  }
                }
            """
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

    companion object {
        fun getCreator(blockInfo: BlockInfo): BlockCreator {
            return when(blockInfo.block) {
                is RedstoneLampBlock -> RedstoneLampTrapdoorCreator(blockInfo)
                //is AbstractGlassBlock -> GlassSlabCreator(blockInfo)
                is GrassBlock -> GrassTrapdoorCreator(blockInfo)
                else -> TrapdoorCreator(blockInfo)
            }
        }
    }
}