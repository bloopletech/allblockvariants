package net.bloople.allblockvariants

import net.bloople.allblockvariants.ClientUtil.Companion.decodeBase64
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Blocks
import net.minecraft.block.TargetBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.util.DyeColor
import java.awt.image.BufferedImage

class DyedTargetCreator(metrics: Metrics, private val dyeColor: DyeColor) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(BLOCK_INFOS.getValue(Blocks.TARGET)) { "${dyeColor.getName()}_target" }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(TargetBlock(blockSettings.mapColor(dyeColor)))
            registerItem(BlockItem(block, Item.Settings()), ItemGroups.REDSTONE)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture(blockName) { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(decodeBase64(sideTargetLayerImage),
                    ::createBlockTexture)
            }

            builder.addBlockTexture("${blockName}_top") { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(decodeBase64(topTargetLayerImage),
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
                  "parent": "minecraft:block/cube_column",
                  "textures": {
                    "end": "${blockBlockId}_top",
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
        const val sideTargetLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAACTklEQVQ4y3VTMWvbQBT+7B4o8on46mAv7pVupQYPhXrqlkLihGaobJIpi0imQujWtX8gP6AUAm0nkyZTocV/IUMNAnvIYPDVSy6x5ViyLZB1HYwUpSZvufe9+3jv4+59KdlpKqP4EjOnjxVWhNv/g7XnbzD6a2OFFeN64LsgmnEP07WnIIQW8P7Dxy3HcfKMMek4Tv78fOO7aZr7jDHZaDRIqVTSOOcZxpi0bZteXFycmaa5//Xz8TfITlOZprk/ll0VhnMlO001vZVq2Gupsew+iCuVSm16K1Wa0AIYYzKYjgAAhBagGblFrhlLGECMAYAAgG3bFAB8d4AnL17veJ73s1Kp1DjnGSHEpFwue41Gg+zt7QXHn45+AQDnPDNz+sCw11KWZVXHsntP2rDXUmE4V2PZVWPZVcNeSwELfhjOFaX07Vh2FSF6NpaTlJaMSDoAED0L3x1gc3PzMdEMpP8nc84zUaODg8Ot9e3deoQty6pGg4QQE83ILRqcnJz8XpqqZ+O67w7wIC9Kol9IRhjOlXfTg2bkEPjuklLfHYDMri/jou8OIISY+O4AwXQUT44GWJZVnV1fgmgGhBATAEgTWoBlWdXAu4rJ+mo+tVE7rOur+dT69m49nX6U2qgd1pN7wjnPBL4LEnhXsG2bEnoEzcihefblx0OeIHoWwXQE76YXq7p7g4SCwHdBaOGO6LvxfZJH9OzCTOVy2Xv26t1O0jTR5iVNJoSYtNttv1Qqae1226drp4DsNONNm95KJTtNFYbzJRyZLdrE6PwH+SGLlsWBqm8AAAAASUVORK5CYII="
        const val topTargetLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAACdklEQVQ4y11TMWvbQBh9dg8i+1ysYsWLUUgHUTB4CES/oJCk0AyRA+nixZC1W+mWPXTpH+jSLnVJM5UWDO7ckCECgY3xENA1i2Qjq/FZNlx8Hcypdg+Ou++7d/De+74vcx/eytlwAELLAADBAwAAoeX0rmK1VjFkNhzg7bvPL8bj8aau6+F4PN68vLz85DhOQ9f1sNVqkWq1umGaZl7X9dDzPHp9ff3VcZzGh/dnHxH5rnQcpxH5rlwsHmTku/I+vJWR76Z7sXhIc/fhrQx7bWnbdj3stWVW8AC6roeCB+AjH4IHoKWtJcVcEYIHiH97mA0HAABa2krlEFoGAQDP8yihrzEbDrC9e3TIOf9m23bdNM08Y2xaq9V4q9UiJycn4vzNqx+aYcE0zbzgARD22rLZbB6EvbaMfFfatl2PfFeGvfYa9bDXlsASv1g8SErpy8h3ZXaNTq6YuqwZFkb9DkQSQ/AApWfPU9yo38H+/v4TwYOlBPVJ8ACmaeYJLUNVx/M8enX162LU76DZbB4oPGNsSmh5SQkAlMOO4zQUdQBQbke+KwFAvSmpKQMAqdOjfgeElhH22lIzrDQPYFmpJE5NzK52oGZYYIxNNcMCAGiGBZHEqX4lgeSKYIxNASAreLCmDQAebz7N7NVPj9WZzT7K7NVPj9U7LW3BNM28ZlggmmHB8zxaqJxjcneDn9+/XIgkBskVMRsOoCSk1dooYNTvLFknMbJK3+TuBoo6yRXTjhNJnOY1vQIxn6BQ2fk3ZISWUavV+Pbu0eHq0KjOWx0yxti02+3Oq9XqRrfbnQNnyCR/QinmE4gkXjPzfxkqXsUVKjv4C2imnfal2AF3AAAAAElFTkSuQmCC"
    }
}