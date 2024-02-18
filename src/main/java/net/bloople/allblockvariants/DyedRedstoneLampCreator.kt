package net.bloople.allblockvariants

import net.bloople.allblockvariants.ClientUtil.Companion.decodeBase64
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.util.DyeColor
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import java.awt.image.BufferedImage

class DyedRedstoneLampCreator(private val metrics: Metrics, private val dyeColor: DyeColor) : BlockCreator() {
    override val dbi = DerivedBlockInfo(BLOCK_INFOS.getValue(Blocks.REDSTONE_LAMP)) { "${dyeColor.getName()}_redstone_lamp" }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registries.BLOCK,
                identifier,
                RedstoneLampBlock(existingBlock.copySettings().mapColor(dyeColor.mapColor))
            )
            metrics.common.blocksAdded++

            item = Registry.register(
                Registries.ITEM,
                identifier,
                BlockItem(block, Item.Settings())
            )
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register {
                it.add(item)
            }
            metrics.common.itemsAdded++
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture(blockName) { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(decodeBase64(redstoneLampLayerImage),
                    ::createBlockTexture)
            }

            builder.addBlockTexture("${blockName}_on") { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(decodeBase64(onRedstoneLampLayerImage),
                    ::createBlockTexture)
            }

            val blockState = """
                {
                  "variants": {
                    "lit=false": {
                      "model": "$blockBlockId"
                    },
                    "lit=true": {
                      "model": "${blockBlockId}_on"
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

            val onBlockModel = """
                {
                  "parent": "minecraft:block/cube_all",
                  "textures": {
                    "all": "${blockBlockId}_on"
                  }
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_on", onBlockModel)

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
        const val redstoneLampLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAByUlEQVQ4y6WTIZLbQBBF/5daPkGQgZQyFFi0OMA4yMClExjlEEZ7ghwhQLVARzAICjAyEJRjqUooJ9D0TIfMuJTQHTjVr/vP7z+sqkrxgZMBgIj0AGBmbQjhGEI4mlmbisyszfN893g8JN0nhkmBmbVm1onIDACqut1sNmdVrUMIx9SM5B8AJ5LNPwrMrCuK4uq935CcSR5UtY7QIYIws09xUP9s4Jy7FUVxHYZhHMfxp6q+kWyiqn2cdiqK4pqUOOduACAAkOf5d1X9DGAsy/JHhPd5ni8kZ1Xdk7yoKgB00zR1ALqqqhoBAO/9NzPr1rCI/HbOvYpIkn3MsuwdAHa73VVV354KSDZFUbyoam1mbYKTmck8M2tJNiRfSNbPBrGwjiZ1ZrZN9yIy3+/3XwBQliUANKn2aWKe57s4uc+y7N17v0mGkZwj/IXkRUT6dU6yOHUbJ38VkT4a9iYis3PuNXpzEZF+WZZzUvp8QnqnmW2dc+cYoMZ7D5Ig2axhEZkTk62DkuQuy3KOSpr/4Wmauhi2w7pBA+A0DMMYdwzn3C2lLYRwWsUY6yizqioVkV5VaxHpnXO3GKxt2nv6JyQPad2J4Ue/81/Rk0Y2a6Y3AgAAAABJRU5ErkJggg=="
        const val onRedstoneLampLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABJUlEQVQ4y6VTwY3CQAycbO5taT+RXIEruWsiIPpIAfSB4JrgCjltBRZ8kPjv5l6DDATdg/1Ysj2T8djpzGzGGy8BwPl8/gEAd1+VUrpSSufuKzYt5YmBmc1mNovICACttb27TwCQcz5SobtPrbU9AIjISBzMbM45HwleaorkrJM8AUCtdcdCSmktIqOqHihdVQ8iMqaU1iSqte5uI1Bi/DJzzEclMXdXiGDKjZ4s9X4AgKoe+r7fDMPw6e4rAuJz90lVOzOb2XtbIwAwcb1ev1V1ezqdfgGAUVW3JGbvnQciMtLZOCtB7j7Fjd3GfTTxkWQJ/GTiqx2T5NWtPG1hieS/Q0vcQjwUd59qrTve++Vy+aLseGiI8hjj7I+nHI1m7N79nf8Ak0gijm+/PnQAAAAASUVORK5CYII="
    }
}