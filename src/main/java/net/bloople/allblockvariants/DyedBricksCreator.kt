package net.bloople.allblockvariants

import net.bloople.allblockvariants.ClientUtil.Companion.decodeBase64
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.util.DyeColor
import java.awt.image.BufferedImage

class DyedBricksCreator(metrics: Metrics, private val dyeColor: DyeColor) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(BLOCK_INFOS.getValue(Blocks.BRICKS)) { "${dyeColor.getName()}_bricks" }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(Block(existingBlock.copySettings().mapColor(dyeColor.mapColor)))
            registerItem(BlockItem(block, Item.Settings()), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture(blockName) { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(decodeBase64(bricksLayerImage),
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

            val recipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "ingredients": [
                    {
                      "item": "$existingIdentifier"
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
            builder.addRecipe(blockName, recipe)

            val modStickRecipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "ingredients": [
                    {
                      "item": "$existingIdentifier"
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
            builder.addRecipe("${blockName}_from_mod_stick", modStickRecipe)

            val bulkRecipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "key": {
                    "#": {
                      "item": "$existingIdentifier"
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
            builder.addRecipe("${blockName}_from_bulk", bulkRecipe)
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val modStickRecipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "ingredients": [
                    {
                      "item": "$existingIdentifier"
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
            builder.addRecipe("${blockName}_from_mod_stick", modStickRecipe)
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
        const val bricksLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABdUlEQVQ4y4WTMW4qMRRFL2YkG4Q1uLKlUKZgpN+zhCyDhiUw1SyBWRhLSJEysh4dEl9YLmx+k0csf0im9LFm3rnvDrque+m67sUYs+n7/obiYcZ8HMdbfd4Q0QoAcs5Lay2UUlspJQkhzkR0f1nOeQkASqktEb3zeRNCWKeUFgCgtUZKaXG9Xl/n8/kHAMQYHXMASCktcs7LGKMDgAmPba2F1hqXywWn06k0gbUW/IGaT0pfACCilXPuk4hWwzAc9/v9hFnNd7vdsVFKbWez2Xvp673/wyMqpbbeeyon8t4vY4zOWouGAxJCnEMI69K1zGQ6nf6VUlKdy2Qcx9tvzgAe5qK1/t5puecyF2PM5hF/2AMAaNv2TQhx/grsHhzrMWfWMGCnr/Bc6VtmEmN0UsrvNfZ9f/tpz7Xzfz0wxmycc5/P9nw4HJ72YBiGYxNCWJcd4Ev8b7Rt+0ZE55qzepNSWpT7r3vAHXjGH/agdq57cO8AgH9S5RvUnxAQjAAAAABJRU5ErkJggg=="
    }
}