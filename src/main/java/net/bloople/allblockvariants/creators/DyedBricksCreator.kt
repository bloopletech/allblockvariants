package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.BLOCK_INFOS
import net.bloople.allblockvariants.BlockCreator
import net.bloople.allblockvariants.ClientUtil
import net.bloople.allblockvariants.ClientUtil.decodeBase64
import net.bloople.allblockvariants.DerivedBlockInfo
import net.bloople.allblockvariants.ModStickCreator
import net.bloople.allblockvariants.ResourcePackBuilder
import net.bloople.allblockvariants.blankClone
import net.bloople.allblockvariants.drawImage
import net.bloople.allblockvariants.toColor
import net.bloople.allblockvariants.use
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.util.DyeColor
import java.awt.image.BufferedImage

class DyedBricksCreator(private val dyeColor: DyeColor) : BlockCreator() {
    override val dbi = DerivedBlockInfo(BLOCK_INFOS.getValue(Blocks.BRICKS)) { "${dyeColor.getName()}_bricks" }

    override fun common() {
        with(dbi) {
            registerBlock(Block(blockSettings.mapColor(dyeColor)))
            registerItem(BlockItem(block, Item.Settings()), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun client(builder: ResourcePackBuilder) {
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

            val itemModel = """
                {
                  "parent": "$blockBlockId"
                }
            """
            builder.addItemModel(blockName, itemModel)
        }
    }

    override fun server(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockLootTable(dbi)

            val recipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "category": "building",
                  "ingredients": [
                    {
                      "item": "$existingIdentifier"
                    },
                    {
                      "item": "minecraft:${dyeColor.getName()}_dye"
                    }
                  ],
                  "result": {
                    "id": "$identifier",
                    "count": 1
                  }
                }
            """
            builder.addRecipe(blockName, recipe)

            val modStickRecipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "category": "building",
                  "ingredients": [
                    {
                      "item": "$existingIdentifier"
                    },
                    {
                      "item": "minecraft:${dyeColor.getName()}_dye"
                    },
                    {
                      "item": "${ModStickCreator.Companion.identifier}"
                    }
                  ],
                  "result": {
                    "id": "$identifier",
                    "count": 1
                  }
                }
            """
            builder.addRecipe("${blockName}_from_mod_stick", modStickRecipe)

            val bulkRecipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "building",
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
                    "id": "$identifier"
                  }
                }
            """
            builder.addRecipe("${blockName}_from_bulk", bulkRecipe)
        }
    }

    override fun vanillaBlockServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val modStickRecipe = """
                {
                  "type": "minecraft:crafting_shapeless",
                  "category": "building",
                  "ingredients": [
                    {
                      "item": "$existingIdentifier"
                    },
                    {
                      "item": "minecraft:${dyeColor.getName()}_dye"
                    },
                    {
                      "item": "${ModStickCreator.Companion.identifier}"
                    }
                  ],
                  "result": {
                    "id": "$identifier",
                    "count": 1
                  }
                }
            """
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