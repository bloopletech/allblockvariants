package net.bloople.allblockvariants

import net.bloople.allblockvariants.ClientUtil.Companion.decodeBase64
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Blocks
import net.minecraft.block.PillarBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.util.DyeColor
import java.awt.image.BufferedImage

class DyedWoodCreator(metrics: Metrics, private val dyeColor: DyeColor) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(BLOCK_INFOS.getValue(Blocks.OAK_WOOD)) { "${dyeColor.getName()}_wood" }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(PillarBlock(blockSettings.mapColor(dyeColor)))
            registerItem(BlockItem(block, Item.Settings()), ItemGroups.REDSTONE)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture(blockName) { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(decodeBase64(DyedLogCreator.logLayerImage),
                    ::createBlockTexture)
            }

            val blockState = """
                {
                  "variants": {
                    "axis=x": {
                      "model": "$blockBlockId",
                      "x": 90,
                      "y": 90
                    },
                    "axis=y": {
                      "model": "$blockBlockId"
                    },
                    "axis=z": {
                      "model": "$blockBlockId",
                      "x": 90
                    }
                  }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "minecraft:block/cube_column",
                  "textures": {
                    "end": "$blockBlockId",
                    "side": "$blockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

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

            for(existingWoodIdentifier in existingIdentifiers) {
                val recipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingWoodIdentifier"
                        },
                        {
                          "item": "minecraft:${dyeColor.getName()}_dye"
                        }
                      ],
                      "result": {
                        "item": "$identifier",
                        "count": 1
                      }
                    }
                """.trimIndent()
                builder.addRecipe("${blockName}_from_${existingWoodIdentifier.path}", recipe)

                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingWoodIdentifier"
                        },
                        {
                          "item": "minecraft:${dyeColor.getName()}_dye"
                        },
                        {
                          "item": "${ModStickCreator.identifier}"
                        }
                      ],
                      "result": {
                        "item": "$identifier",
                        "count": 1
                      }
                    }
                """.trimIndent()
                builder.addRecipe("${blockName}_from_${existingWoodIdentifier.path}_mod_stick", modStickRecipe)

                val bulkRecipe = """
                    {
                      "type": "minecraft:crafting_shaped",
                      "key": {
                        "#": {
                          "item": "$existingWoodIdentifier"
                        },
                        "D": {
                          "item": "minecraft:${dyeColor.getName()}_dye"
                        }
                      },
                      "pattern": [
                        "###",
                        "#D#",
                        "###"
                      ],
                      "result": {
                        "count": 8,
                        "item": "$identifier"
                      }
                    }
                """.trimIndent()
                builder.addRecipe("${blockName}_from_${existingWoodIdentifier.path}_bulk", bulkRecipe)
            }

            val fromLogsRecipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "group": "bark",
                  "key": {
                    "#": {
                      "item": "$MOD_ID:${dyeColor.getName()}_log"
                    }
                  },
                  "pattern": [
                    "##",
                    "##"
                  ],
                  "result": {
                    "count": 3,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_logs", fromLogsRecipe)

            val fromStrippedLogsRecipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "group": "bark",
                  "key": {
                    "#": {
                      "item": "$MOD_ID:stripped_${dyeColor.getName()}_log"
                    }
                  },
                  "pattern": [
                    "##",
                    "##"
                  ],
                  "result": {
                    "count": 3,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_stripped_logs", fromStrippedLogsRecipe)

            builder.addBlockTag("logs", identifier.toString())
            builder.addItemTag("logs", identifier.toString())
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            for(existingWoodIdentifier in existingIdentifiers) {
                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingWoodIdentifier"
                        },
                        {
                          "item": "minecraft:${dyeColor.getName()}_dye"
                        },
                        {
                          "item": "${ModStickCreator.identifier}"
                        }
                      ],
                      "result": {
                        "item": "$vanillaIdentifier",
                        "count": 1
                      }
                    }
                """.trimIndent()
                builder.addRecipe("${blockName}_from_${existingWoodIdentifier.path}_mod_stick", modStickRecipe)
            }

        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createBlockTexture(input: BufferedImage): BufferedImage {
        return input.blankClone().apply {
            createGraphics().use {
                color = dyeColor.toColor()
                fillRect(0, 0, width, height)
                drawImage(input)
            }
        }
    }

    companion object {
        val existingBlocks = arrayOf(
            Blocks.OAK_WOOD,
            Blocks.SPRUCE_WOOD,
            Blocks.BIRCH_WOOD,
            Blocks.JUNGLE_WOOD,
            Blocks.ACACIA_WOOD,
            Blocks.DARK_OAK_WOOD,
            Blocks.MANGROVE_WOOD,
            Blocks.CRIMSON_HYPHAE,
            Blocks.WARPED_HYPHAE
        )

        val existingIdentifiers = existingBlocks.map { Registries.BLOCK.getId(it) }
    }
}