package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.*
import net.bloople.allblockvariants.ClientUtil.decodeBase64
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Blocks
import net.minecraft.block.PillarBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.util.DyeColor
import java.awt.image.BufferedImage

class DyedLogCreator(private val dyeColor: DyeColor) : BlockCreator() {
    override val dbi = DerivedBlockInfo(BLOCK_INFOS.getValue(Blocks.OAK_LOG)) { "${dyeColor.id}_log" }

    override fun common() {
        with(dbi) {
            registerBlock(PillarBlock(blockSettings.mapColor(dyeColor)))
            registerItem(BlockItem(block, itemSettings), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun client(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture(blockName) { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(decodeBase64(logLayerImage),
                    ::createBlockTexture)
            }

            builder.addBlockTexture("${blockName}_top") { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(decodeBase64(topLogLayerImage),
                    ::createBlockTexture)
            }

            val blockState = """
                {
                  "variants": {
                    "axis=x": {
                      "model": "${blockBlockId}_horizontal",
                      "x": 90,
                      "y": 90
                    },
                    "axis=y": {
                      "model": "$blockBlockId"
                    },
                    "axis=z": {
                      "model": "${blockBlockId}_horizontal",
                      "x": 90
                    }
                  }
                }
            """
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "minecraft:block/cube_column",
                  "textures": {
                    "end": "${blockBlockId}_top",
                    "side": "$blockBlockId"
                  }
                }
            """
            builder.addBlockModel(blockName, blockModel)

            val horizontalBlockModel = """
                {
                  "parent": "minecraft:block/cube_column_horizontal",
                  "textures": {
                    "end": "${blockBlockId}_top",
                    "side": "$blockBlockId"
                  }
                }
            """
            builder.addBlockModel("${blockName}_horizontal", horizontalBlockModel)

            builder.addItemModelDefinition(blockName, id(blockBlockId))
        }
    }

    override fun server(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockLootTable(dbi)

            for(existingLogsIdentifier in existingIdentifiers) {
                val recipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "category": "building",
                      "ingredients": [
                        "$existingLogsIdentifier",
                        "minecraft:${dyeColor.id}_dye"
                      ],
                      "result": {
                        "id": "$identifier",
                        "count": 1
                      }
                    }
                """
                builder.addRecipe("${blockName}_from_${existingLogsIdentifier.path}", recipe)

                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "category": "building",
                      "ingredients": [
                        "$existingLogsIdentifier",
                        "minecraft:${dyeColor.id}_dye",
                        "${ModStickCreator.Companion.identifier}"
                      ],
                      "result": {
                        "id": "$identifier",
                        "count": 1
                      }
                    }
                """
                builder.addRecipe("${blockName}_from_${existingLogsIdentifier.path}_mod_stick", modStickRecipe)

                val bulkRecipe = """
                    {
                      "type": "minecraft:crafting_shaped",
                      "category": "building",
                      "key": {
                        "#": "$existingLogsIdentifier",
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
                builder.addRecipe("${blockName}_from_${existingLogsIdentifier.path}_bulk", bulkRecipe)
            }

            builder.addBlockTag("logs", identifier)
            builder.addItemTag("logs", identifier)
        }
    }

    override fun vanillaBlockServer(builder: ResourcePackBuilder) {
        with(dbi) {
            for(existingLogsIdentifier in existingIdentifiers) {
                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "category": "building",
                      "ingredients": [
                        "$existingLogsIdentifier",
                        "minecraft:${dyeColor.id}_dye",
                        "${ModStickCreator.Companion.identifier}"
                      ],
                      "result": {
                        "id": "$vanillaIdentifier",
                        "count": 1
                      }
                    }
                """
                builder.addRecipe("${blockName}_from_${existingLogsIdentifier.path}_mod_stick", modStickRecipe)
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
        const val logLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABYUlEQVQ4y3VSvW7CQAy2fDb3CO3aCSksN0RhY2Dq2ifoWvY+Rde+DwOdIgZOSETNEwASnatTIqdLXTlX8HT2+e/7Pjvv/XIYhueqqj4vl0tFRA/OubeyLD+sT0RfZVl+H4/HV82dTCaPCL9W1/UJEae3fGsxxkLfBABARDPn3ApuWN/3B2aG7Xb7kv9hCKEBABCR1hZ475fqe+/POkhjWodaYLvaxJTSWt/OuTUzb7quW+hQBADQoIi0IYRGG6aU7uxE6/9B2O/3T3nQe39GxCkzb+bz+b3CY+bNvwbX1taCEEJjGVdVlBMAABSRVjGllNa2IC+05I02YOZNTmSut+VERNrdbvc+gqB4daKItMMwrG41G22gEK5ZSmmtnBDRrK7rk5Uerc4xxkIxWqyWAz0wJXJ0B1aFGGPR9/1BYeUHp1uhTbCyhRAaIprFGAt7QF3XLUbb5ZjtJrky+SEh4vQH9snkZbHDkMoAAAAASUVORK5CYII="
        const val topLogLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABmElEQVQ4y4VTsWrjQBB9NhJbzAoZjFwshIWAt0hnyD+kyz/cN+Q7jrsPuC+4bwm4M2aLgAhSYWGwsaYYtrkinrAnfLlppJmdnX1v5s2MiH4wc0VEFwDvAO4yHwDemflB/ckZZgB+EdELMgshLGOMxxDCcrvdvm02m3sAiDEe8zxm/l5otcVi8Q0A6rruRQTOuVZE/Hq9bsZxBAA455qyLD0A7Ha730R0KfKKXdf97LoORFRfQ/sckca890+aU2iCiPSn06kOISxxwzQeYzymlNprURTMXMUYj865VpNF5BEAUkotAChsY8xr5g/MXM2J6BJCWJZl6fWV8/nsjDGv1trBWjvoxSkSIrrM9SCl1CrPuq57ABjHsRnHscnR5BQAYI7/mMLX7/S/YObqX5ettQOAQREYY7ThqoOPHkyraiMPh8OziDwqjZwCAPylAw3m3V6tVurfojV8UriOsdEXJsLJpXvORAZmrop8Mbz3TymlVkS89/7L5uoYPyn0fb8HsL+OZ1AkAPDVQs0m64wbK3trnXcA7gDgD12AAenY/suBAAAAAElFTkSuQmCC"

        val existingBlocks = arrayOf(
            Blocks.OAK_LOG,
            Blocks.SPRUCE_LOG,
            Blocks.BIRCH_LOG,
            Blocks.JUNGLE_LOG,
            Blocks.ACACIA_LOG,
            Blocks.DARK_OAK_LOG,
            Blocks.MANGROVE_LOG,
            Blocks.CRIMSON_STEM,
            Blocks.WARPED_STEM
        )

        val existingIdentifiers = existingBlocks.map { Registries.BLOCK.getId(it) }
    }
}