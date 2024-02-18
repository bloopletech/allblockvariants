package net.bloople.allblockvariants

import net.bloople.allblockvariants.ClientUtil.Companion.decodeBase64
import net.bloople.allblockvariants.blocks.DyedFlowerPotBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.*
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.util.DyeColor
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import java.awt.AlphaComposite
import java.awt.image.BufferedImage

class DyedFlowerPotCreator(private val metrics: Metrics, private val dyeColor: DyeColor) : BlockCreator() {
    override val dbi = DerivedBlockInfo(BlockInfo(Blocks.FLOWER_POT)) { "${dyeColor.getName()}_flower_pot" }

    override fun doCreateCommon() {
        with(dbi) {
            block = Registry.register(
                Registries.BLOCK,
                identifier,
                DyedFlowerPotBlock(
                    Blocks.AIR,
                    existingBlock.copySettings().mapColor(dyeColor.mapColor),
                    dyeColor.mapColor
                )
            )
            metrics.common.blocksAdded++

            item = Registry.register(
                Registries.ITEM,
                identifier,
                BlockItem(block, Item.Settings())
            )
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register {
                it.add(item)
            }
            metrics.common.itemsAdded++
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())

            builder.addBlockTexture(blockName) { ->
                return@addBlockTexture ClientUtil.createDerivedTexture(decodeBase64(flowerPotBlockLayerImage),
                    ::createBlockTexture)
            }

            builder.addItemTexture(blockName) { ->
                return@addItemTexture ClientUtil.createDerivedTexture(
                    decodeBase64(flowerPotItemLayerImage),
                    decodeBase64(flowerPotItemLayer2Image),
                    ::createItemTexture)
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
                  "parent": "minecraft:block/flower_pot",
                  "textures": {
                    "flowerpot": "$blockBlockId"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val itemModel = """
                {
                  "parent": "minecraft:item/generated",
                  "textures": {
                    "layer0": "$itemItemId"
                  }
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

            builder.addBlockTag("flower_pots", identifier.toString())
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

    override fun getBlockInfo(): BlockInfo? {
        return null
    }

    @Environment(value=EnvType.CLIENT)
    private fun createBlockTexture(input: BufferedImage): BufferedImage {
        return input.blankClone().apply {
            createGraphics().use {
                color = dyeColor.toColor()
                fillRect(5, 5, 6, 11)
                applyComposite(AlphaComposite.Clear) { fillRect(6, 6, 4, 4) }
                drawImage(input)
            }
        }
    }

    @Environment(value=EnvType.CLIENT)
    private fun createItemTexture(mask: BufferedImage, pot: BufferedImage): BufferedImage {
        return mask.blankClone().apply {
            createGraphics().use {
                color = dyeColor.toColor()
                fillRect(0, 0, width, height)
                applyComposite(AlphaComposite.Xor) { drawImage(mask) }
                drawImage(pot)
            }
        }
    }

    companion object {
        const val flowerPotBlockLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAnElEQVQ4y+2QwQrCMBBEx6U5BAISyCEggvkTr/0U/8SP7Sk0JGFh8aJQpAnWgifnOMy+nV3gr906rJne+1FrnQEgxngWEZNzPpZS7h9RQwjXd89ae1vL0t4TfgcgonnNH1oD3vsRAJjZKaUmZt7WgJkdACilplrrpZVrNnhudSJiiGhOKZ02ARYQiIj56omv+3uZoTcsIqbWanqAB9imPdA0dgzYAAAAAElFTkSuQmCC"
        const val flowerPotItemLayerImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAARklEQVQ4y2NkYGD4z0ABYGKgELDgkWNE4//Hpeg/AY0M+AxiIlEzhhqKw2DwGUBMmvhPcy/8J9Z2mgbif2JdxjjgmYliAwAmDQsZ16v56gAAAABJRU5ErkJggg=="
        const val flowerPotItemLayer2Image = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABGElEQVQ4y91Su2qFQBA962MtVhAbEVcLyS0DwSrlLUN+I+X9gnxJmvxHIKVNwMomv6DYbGGuQlZdTSVcRL3pApluZs7MOYcZ4K+DbDUYY58AFAAYhoG6ru/WcMbKYKZpmul53puu6+dhGL4IIVxKmVmWxeq6vt1UwBjLfN9PdV0/K6VuLnvTNOWEEF5V1bFt2/u5rl2CXNfthBCH5TAAEEISIcTBcZzvaxZOQoiXNb+u675KKZ+vLXgCcCqKouKc+wBAKU0AwDTNREq5f4UgCFLbtt/XFDRN81CW5XFXAQD0fZ93XZcv2X/9HFEUpbN8zrkfx/FjEATpGlZbKyqlQClNOOc+pTTZY9/8xDAMP8Zx7Od86f0fxQ9bBV6OHKPXhwAAAABJRU5ErkJggg=="
    }
}