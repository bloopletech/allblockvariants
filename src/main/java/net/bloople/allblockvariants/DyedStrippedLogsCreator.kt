package net.bloople.allblockvariants

import net.bloople.allblockvariants.ClientUtil.Companion.decodeBase64
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.awt.image.BufferedImage

class DyedStrippedLogsCreator(private val metrics: Metrics, private val colourInfo: ColourInfo) : BlockCreator() {
    override val dbi = DerivedBlockInfo(BlockInfos[Blocks.STRIPPED_OAK_LOG]) { "stripped_${colourInfo.name}_log" }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registry.BLOCK,
                identifier,
                PillarBlock(existingBlock.copySettings().mapColor(colourInfo.colour))
            )
            metrics.common.blocksAdded++

            Registry.register(
                Registry.ITEM,
                identifier,
                BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
            )
            metrics.common.itemsAdded++

            StrippableBlockRegistry.register(Registry.BLOCK[Identifier(MOD_ID,"${colourInfo.name}_log")], block)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
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
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {
                  "parent": "minecraft:block/cube_column",
                  "textures": {
                    "end": "${blockBlockId}_top",
                    "side": "$blockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val horizontalBlockModel = """
                {
                  "parent": "minecraft:block/cube_column_horizontal",
                  "textures": {
                    "end": "${blockBlockId}_top",
                    "side": "$blockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_horizontal", horizontalBlockModel)

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

            for(existingLogsIdentifier in existingIdentifiers) {
                val recipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingLogsIdentifier"
                        },
                        {
                          "item": "minecraft:${colourInfo.name}_dye"
                        }
                      ],
                      "result": {
                        "item": "$identifier",
                        "count": 1
                      }
                    }
                """.trimIndent()
                builder.addRecipe("${blockName}_from_${existingLogsIdentifier.path}", recipe)

                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingLogsIdentifier"
                        },
                        {
                          "item": "minecraft:${colourInfo.name}_dye"
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
                builder.addRecipe("${blockName}_from_${existingLogsIdentifier.path}_mod_stick", modStickRecipe)

                val bulkRecipe = """
                    {
                      "type": "minecraft:crafting_shaped",
                      "key": {
                        "#": {
                          "item": "$existingLogsIdentifier"
                        },
                        "D": {
                          "item": "minecraft:${colourInfo.name}_dye"
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
                builder.addRecipe("${blockName}_from_${existingLogsIdentifier.path}_bulk", bulkRecipe)
            }

            builder.addTag("logs", identifier.toString())
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            for(existingLogsIdentifier in existingIdentifiers) {
                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingLogsIdentifier"
                        },
                        {
                          "item": "minecraft:${colourInfo.name}_dye"
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
                builder.addRecipe("${blockName}_from_${existingLogsIdentifier.path}_mod_stick", modStickRecipe)
            }

        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createBlockTexture(input: BufferedImage): BufferedImage {
        return input.blankClone().apply {
            val graphics = createGraphics()
            graphics.color = colourInfo.toColor()
            graphics.fillRect(0, 0, width, height)
            graphics.drawImage(input, 0, 0, null)
            graphics.dispose()
        }
    }

    companion object {
        const val logLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAACZUlEQVQ4y21Ty07bQBQ9HmI7zIBjjZzaTh0DyiIRUjddsOYn6l/hI/gLlv4EfqAREpWqSqhRVYGRihUbGccv7EQOXTQTUcRZ3bn3jO7j3CuNRqMvtm3njuP8AoD1eh2maTrRdf0nNiCE2Ov1OhR2EATqwcFBEwSBSgQpSZIe3sA0TZUQYiuKkgCA7/uViAVBoJZlaRNKaS2cNzc3h6Zpqnme1wAQxzEHgLqumyiKbMEry9IOw3BFKe0SACiKgorgbDY7AoBOp4P30O/3E2FLknRLGGOrt6ThcHj38PBgAwBjLBd+z/PofD5vJpPJVwDI8/wzieN4X1GUgnO+sCzraTwe377udbVarXzfryzLwvn5eQMAV1dXH7cD3pC2A5zP542wGWN5Xdfb99nZ2WEURfZgMDA2ioT/qSCG53keFdnFu9/vJ2+VYowZhFJay7K8yPN/rRqGQUzTVAHg4uLiiRBi+75fva7McZzvg8HAKMvykVRV1dV1/VFV1YUkSb2dnR05juP915lOT09HADAej2+FrygKyhgzyO7urpqmqSEC19fXBgDZsix4nkeTJOlZloVOp9MCwHK5BCFkuxOk2+1WnPPfAKAoSvSe9qL32Wx25LouptMpybLs/uXlpSIA0DRNL0mSQwBwXbcRN2Gapso5X2wyjwBgOp0STdNcXdc/VVVVkbquadu2HzRNkzcJZXEHYpUvLy+/ZVnW5ZwvNE1zRWWapskEALIso23b3i+Xy23ZcRxzcYFCVoEsy+7TNP1RFAUlz8/Pzd7eXjUcDu8cxwkFSXwWEOd9fHx8d3Jy8kf4/wLCQh/JrfZlOAAAAABJRU5ErkJggg=="
        const val topLogLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABsklEQVQ4y5VTsYrcMBB9t9gYLIENxlvIhZbAql+4f7guX5Am9f1MviMfkS84WNIsi4qAC6uwMXhBMgwqNo3H+I7bQF7jmdF45s3T6KlpmtcQggMAIYSa53nM87zCgnmeR7bv9zuxzbkJAMQYfwHANE0AgMPhUFlrR2NMdT6f/5xOpy8AYK1di03ThLIsvyUcKMvyOwAUReGICEqploj08XisvfcAAKVUnaapBoDL5fIzTVO3FgCArut+dF0HIUSxhK4AYIypNt2vWusXIUQhhFAJJxCRm6apMMZU+AQct9aOMcbWGFP1fY9knufRWjsqpVpOJqLnRZsWAJh2lmVvG38IIbhdnueVMaZK01Rzl9vtprIse5NSDlLKgX/8yAQAdnwQY2w5WBSFAwDvfe29r7dstiO8K/AITJ+/W3sV8RGklAOAgRlkWcaCr0u24y3cdmAh+77/SkTPPMZ2BPYTIYQC8HsbZNH2+z37n401AEDCVZVSNdsfFmdFCOG2WTLkeV6900Br/RJjbIlIa63/Ka61dizLEmsB59wVwHW5noGZAMCjBxVCcE9N07yys+jxX/gL0Vv372Gw5zEAAAAASUVORK5CYII="

        val existingBlocks = arrayOf(
            Blocks.STRIPPED_OAK_LOG,
            Blocks.STRIPPED_SPRUCE_LOG,
            Blocks.STRIPPED_BIRCH_LOG,
            Blocks.STRIPPED_JUNGLE_LOG,
            Blocks.STRIPPED_ACACIA_LOG,
            Blocks.STRIPPED_DARK_OAK_LOG,
            Blocks.STRIPPED_MANGROVE_LOG,
            Blocks.STRIPPED_CRIMSON_STEM,
            Blocks.STRIPPED_WARPED_STEM
        )

        val existingIdentifiers = existingBlocks.map { Registry.BLOCK.getId(it) }
    }
}