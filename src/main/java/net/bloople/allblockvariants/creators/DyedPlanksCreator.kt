package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.*
import net.bloople.allblockvariants.ClientUtil.decodeBase64
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.util.DyeColor
import java.awt.image.BufferedImage

class DyedPlanksCreator(private val dyeColor: DyeColor) : BlockCreator() {
    override val dbi = DerivedBlockInfo(BLOCK_INFOS.getValue(Blocks.OAK_PLANKS)) { "${dyeColor.id}_planks" }

    override fun common() {
        with(dbi) {
            registerBlock(Block(blockSettings.mapColor(dyeColor)))
            registerItem(BlockItem(block, itemSettings), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun client(builder: ResourcePackBuilder) {
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
            """
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "minecraft:block/cube_all",
                  "textures": {
                    "all": "$blockBlockId"
                  }
                }
            """
            builder.addBlockModel(blockName, blockModel)

            builder.addItemModelDefinition(blockName, id(blockBlockId))
        }
    }

    override fun server(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockLootTable(dbi)

            for(existingPlanksIdentifier in existingIdentifiers) {
                val recipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "category": "building",
                      "ingredients": [
                        "$existingPlanksIdentifier",
                        "minecraft:${dyeColor.id}_dye"
                      ],
                      "result": {
                        "id": "$identifier",
                        "count": 1
                      }
                    }
                """
                builder.addRecipe("${blockName}_from_${existingPlanksIdentifier.path}", recipe)

                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "category": "building",
                      "ingredients": [
                        "$existingPlanksIdentifier",
                        "minecraft:${dyeColor.id}_dye",
                        "${ModStickCreator.Companion.identifier}"
                      ],
                      "result": {
                        "id": "$identifier",
                        "count": 1
                      }
                    }
                """
                builder.addRecipe("${blockName}_from_${existingPlanksIdentifier.path}_mod_stick", modStickRecipe)

                val bulkRecipe = """
                    {
                      "type": "minecraft:crafting_shaped",
                      "category": "building",
                      "key": {
                        "#": "$existingPlanksIdentifier",
                        "D": "minecraft:${dyeColor.id}_dye"
                      },
                      "pattern": [
                        "###",
                        "#D#",
                        "###"
                      ],
                      "result": {
                        "count": 8,
                        "id": "$identifier"
                      }
                    }
                """
                builder.addRecipe("${blockName}_from_${existingPlanksIdentifier.path}_bulk", bulkRecipe)
            }

            val fromWoodRecipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "category": "building",
                  "group": "planks",
                  "ingredients": [
                    "${MOD_ID}:${dyeColor.id}_wood"
                  ],
                  "result": {
                    "count": 4,
                    "id": "$identifier"
                  }
                }
            """
            builder.addRecipe("${blockName}_from_wood", fromWoodRecipe)

            val fromLogsRecipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "category": "building",
                  "group": "planks",
                  "ingredients": [
                    "${MOD_ID}:${dyeColor.id}_log"
                  ],
                  "result": {
                    "count": 4,
                    "id": "$identifier"
                  }
                }
            """
            builder.addRecipe("${blockName}_from_logs", fromLogsRecipe)

            builder.addBlockTag("planks", identifier)
            builder.addItemTag("planks", identifier)
        }
    }

    override fun vanillaBlockServer(builder: ResourcePackBuilder) {
        with(dbi) {
            for(existingPlanksIdentifier in existingIdentifiers) {
                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "category": "building",
                      "ingredients": [
                        "$existingPlanksIdentifier",
                        "minecraft:${dyeColor.id}_dye",
                        "${ModStickCreator.Companion.identifier}"
                      ],
                      "result": {
                        "id": "$vanillaIdentifier",
                        "count": 1
                      }
                    }
                """
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
        const val planksLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABmJLR0QA/wD/AP+gvaeTAAABb0lEQVQ4y32TsWrDMBCGf9MYDyfQYHtRCQaDRAhZBAU/Qsdufas+UJ8ia/BQMAEvJoOJMghD6ZILF5H0FsOd5Lvvv18vRKR3u93r+Xz+XZYlIgki0s/y1tqPjIg0ADjnSgCIMb4tyzIopaa+7098geucM8ZsACDz3rfpwb7vT865MoRQy65KqUn+pGma92y73X7O82wAQGs9AsA8zybGuAaAoiiO18nWRVEcZT7GuAYjWGu7lDPNEZH23rfe+9Za2xGRvkPgsZVSUwihzvO8YT3k6BJ3FUKojTE1i+acu/HGGJtxHA+pkKxNCAFZVVVfklVrPUoNOLiensmISF8ul5kZuct+v//BP0FE2hizyfjSIz65RtaFvwCQ53mTpZ2f7Z6D6+M4HowxmzsNHvlA1tgvqT7gnUq+q9e7R76QZ1fe+xbAJIURzLDWdrxKpdQkcMvbW2Au4YWSjTQMw/cjUTmyqqq+pN9TPaQm8i1w/AFhquzj46ENGAAAAABJRU5ErkJggg=="

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

        val existingIdentifiers = existingBlocks.map { Registries.BLOCK.getId(it) }
    }
}