package net.bloople.allblockvariants

import net.bloople.allblockvariants.ClientUtil.Companion.decodeBase64
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.DyeColor
import net.minecraft.util.registry.Registry
import java.awt.image.BufferedImage

class DyedPlanksCreator(private val metrics: Metrics, private val dyeColor: DyeColor) : BlockCreator() {
    override val dbi = DerivedBlockInfo(BLOCK_INFOS.getValue(Blocks.OAK_PLANKS)) { "${dyeColor.getName()}_planks" }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                Block(existingBlock.copySettings().mapColor(dyeColor.mapColor))
            )
            metrics.common.blocksAdded++

            Registry.register(
                Registry.ITEM,
                identifier,
                BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
            )
            metrics.common.itemsAdded++
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture(blockName) { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(decodeBase64(planksLayerImage),
                    ::createBlockTexture)
            }

            val blockState = """
                {
                  "variants": {
                    "": {
                      "model": "$blockBlockId"
                    }
                  }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "minecraft:block/cube_all",
                  "textures": {
                    "all": "$blockBlockId"
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

            for(existingPlanksIdentifier in existingIdentifiers) {
                val recipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingPlanksIdentifier"
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
                builder.addRecipe("${blockName}_from_${existingPlanksIdentifier.path}", recipe)

                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingPlanksIdentifier"
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
                builder.addRecipe("${blockName}_from_${existingPlanksIdentifier.path}_mod_stick", modStickRecipe)

                val bulkRecipe = """
                    {
                      "type": "minecraft:crafting_shaped",
                      "key": {
                        "#": {
                          "item": "$existingPlanksIdentifier"
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
                builder.addRecipe("${blockName}_from_${existingPlanksIdentifier.path}_bulk", bulkRecipe)
            }

            val fromWoodRecipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "group": "planks",
                  "ingredients": [
                    {
                      "item": "$MOD_ID:${dyeColor.getName()}_wood"
                    }
                  ],
                  "result": {
                    "count": 4,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_wood", fromWoodRecipe)

            val fromLogsRecipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "group": "planks",
                  "ingredients": [
                    {
                      "item": "$MOD_ID:${dyeColor.getName()}_log"
                    }
                  ],
                  "result": {
                    "count": 4,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_logs", fromLogsRecipe)

            builder.addBlockTag("planks", identifier.toString())
            builder.addItemTag("planks", identifier.toString())
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            for(existingPlanksIdentifier in existingIdentifiers) {
                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingPlanksIdentifier"
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
                builder.addRecipe("${blockName}_from_${existingPlanksIdentifier.path}_mod_stick", modStickRecipe)
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
        const val planksLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABb0lEQVQ4y32TsWrDMBCGf9MYDyfQYHtRCQaDRAhZBAU/Qsdufas+UJ8ia/BQMAEvJoOJMghD6ZILF5H0FsOd5Lvvv18vRKR3u93r+Xz+XZYlIgki0s/y1tqPjIg0ADjnSgCIMb4tyzIopaa+7098geucM8ZsACDz3rfpwb7vT865MoRQy65KqUn+pGma92y73X7O82wAQGs9AsA8zybGuAaAoiiO18nWRVEcZT7GuAYjWGu7lDPNEZH23rfe+9Za2xGRvkPgsZVSUwihzvO8YT3k6BJ3FUKojTE1i+acu/HGGJtxHA+pkKxNCAFZVVVfklVrPUoNOLiensmISF8ul5kZuct+v//BP0FE2hizyfjSIz65RtaFvwCQ53mTpZ2f7Z6D6+M4HowxmzsNHvlA1tgvqT7gnUq+q9e7R76QZ1fe+xbAJIURzLDWdrxKpdQkcMvbW2Au4YWSjTQMw/cjUTmyqqq+pN9TPaQm8i1w/AFhquzj46ENGAAAAABJRU5ErkJggg=="

        val existingBlocks = arrayOf(
            Blocks.OAK_PLANKS,
            Blocks.SPRUCE_PLANKS,
            Blocks.BIRCH_PLANKS,
            Blocks.JUNGLE_PLANKS,
            Blocks.ACACIA_PLANKS,
            Blocks.DARK_OAK_PLANKS,
            Blocks.MANGROVE_PLANKS,
            Blocks.CRIMSON_PLANKS,
            Blocks.WARPED_PLANKS
        )

        val existingIdentifiers = existingBlocks.map { Registry.BLOCK.getId(it) }
    }
}