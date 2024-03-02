package net.bloople.allblockvariants

import com.google.common.collect.ImmutableSet
import net.bloople.allblockvariants.ClientUtil.Companion.decodeBase64
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.render.TexturedRenderLayers
import net.minecraft.client.util.SpriteIdentifier
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.SignItem
import net.minecraft.registry.Registries
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier
import java.awt.AlphaComposite
import java.awt.image.BufferedImage


class DyedSignCreator(metrics: Metrics, private val dyeColor: DyeColor) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(SIGN_BLOCK_INFOS.getValue(Blocks.OAK_SIGN)) { "${dyeColor.getName()}_sign" }
    private val woodType = WoodType.register(WoodType(dbi.blockName, dbi.blockInfo.blockSetType))
    private val wallDbi = DerivedBlockInfo(SIGN_BLOCK_INFOS.getValue(Blocks.OAK_SIGN)) { "${dyeColor.getName()}_wall_sign" }
    private lateinit var wallBlock: Block

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(SignBlock(woodType, blockSettings.mapColor(dyeColor)))

            wallBlock = customRegisterBlock(
                wallDbi.identifier,
                WallSignBlock(woodType, wallDbi.blockSettings.mapColor(dyeColor).dropsLike(block))
            )

            val mutableBETBlocks = BlockEntityType.SIGN.blocks.toMutableSet()
            mutableBETBlocks.add(block)
            mutableBETBlocks.add(wallBlock)
            BlockEntityType.SIGN.blocks = ImmutableSet.copyOf(mutableBETBlocks)

            registerItem(SignItem(Item.Settings().maxCount(16), block, wallBlock), ItemGroups.FUNCTIONAL)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            TexturedRenderLayers.SIGN_TYPE_TEXTURES[woodType] = SpriteIdentifier(
                TexturedRenderLayers.SIGNS_ATLAS_TEXTURE,
                Identifier(MOD_ID, "entity/signs/" + woodType.name)
            )

            builder.addEntityTexture("signs", blockName) { ->
                return@addEntityTexture ClientUtil.createDerivedTexture(
                    decodeBase64(signMaskImage),
                    decodeBase64(signImage),
                    ::createTexture
                )
            }

            builder.addItemTexture(blockName) { ->
                return@addItemTexture ClientUtil.createDerivedTexture(
                    decodeBase64(signItemMaskImage),
                    decodeBase64(signItemImage),
                    ::createTexture
                )
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

            val wallBlockState = """
                {
                  "variants": {
                    "": {
                      "model": "$blockBlockId"
                    }
                  }
                }
            """.trimIndent()
            builder.addBlockState(wallDbi.blockName, wallBlockState)

            val blockModel = """
                {
                  "textures": {
                    "particle": "$MOD_ID:block/${dyeColor.getName()}_planks"
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
        registerBlockCommon(builder, wallDbi, wallBlock)

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

            for(existingSignsIdentifier in existingIdentifiers) {
                val recipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingSignsIdentifier"
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
                builder.addRecipe("${blockName}_from_${existingSignsIdentifier.path}", recipe)

                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingSignsIdentifier"
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
                builder.addRecipe("${blockName}_from_${existingSignsIdentifier.path}_mod_stick", modStickRecipe)

                val bulkRecipe = """
                    {
                      "type": "minecraft:crafting_shaped",
                      "key": {
                        "#": {
                          "item": "$existingSignsIdentifier"
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
                builder.addRecipe("${blockName}_from_${existingSignsIdentifier.path}_bulk", bulkRecipe)
            }

            builder.addBlockTag("standing_signs", identifier.toString())
            builder.addItemTag("signs", identifier.toString())
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            for(existingSignsIdentifier in existingIdentifiers) {
                val modStickRecipe = """
                    {
                      "type": "minecraft:crafting_shapeless",
                      "ingredients": [
                        {
                          "item": "$existingSignsIdentifier"
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
                builder.addRecipe("${blockName}_from_${existingSignsIdentifier.path}_mod_stick", modStickRecipe)
            }

        }
    }

    override fun getBlockInfo(): BlockInfo? {
        return null
    }

    @Environment(value=EnvType.CLIENT)
    private fun createTexture(mask: BufferedImage, input: BufferedImage): BufferedImage {
        return input.blankClone().apply {
            createGraphics().use {
                color = dyeColor.toColor()
                fillRect(0, 0, width, height)
                applyComposite(AlphaComposite.Xor) { drawImage(mask) }
                drawImage(input)
            }
        }
    }

    companion object {
        const val signImage = "iVBORw0KGgoAAAANSUhEUgAAAEAAAAAgCAYAAACinX6EAAAABmJLR0QA/wD/AP+gvaeTAAADdklEQVRo3u1Xv2vbUBD+FBxjW8pQJBORIJBBhQwaQkohSzZNXSL6X2QrGLoGrwUPpYP/i+JuGbxp6ZLgwZRABBEIgYJlMkROzMOgLjl4POQfcguhUQ+M9e69z893vu+7s4Rn8zyv1e1279rt9i4AdDqduaIoE1qL1ul05vR8fn5eAYCXxJ+cnNxiA5Po4fDw8GMURce1Wi0k32w2M2q1WjibzQwA4PcAQFXVKIqiYwDY39//+ZL48Xj8eaMEuK7baLfbu2dnZ29Go9EVv2nb9pHocxzHAIA4jpv8Xt5Z3kcVlqapSnjLsq4BgO7v9Xr3PF70rYMvWgmS4ziGoiiTfr//6DiOEcdxU9f1cRzHzUqlYs7n80DX9TEADAaDMC8Z5F+FVxRlwuMpGPKnaar+KV78jisTYNv2EQDouj4eDAYhBQUASZK8Hw6H3/N+ffGDVuFd123wv14eXlGUCZ0hvGmaF2LAy/D9fv+xUAKazeYXnl+qqkaTyWR/GWjZmSJ44nbe/fyeyP9l+DAMv22kAcQdsayX2TKNEPGe57X4dbfbvfN9/8CyrGtSep7j1BkW2SJ84Qqg0szjN19qxEt6B4BKpWIOh8PvjuMYm+JN07xI01TN4/cm+MIJyFNnkVv8mvZHo9EVXwGL9GER3rKsa9/3D3q93v0m9y/CFxZBXgNUVY0AII/D63J7HbzI77z7RX6vi+dFey3zPK9FnSCP48vWRfx0j+d5Ldd1G57ntehl2/YR73Ndt+G6boPO8+dW4QsPQqJo8W2RL3cqc7HVka3Ck9aIrY6MOOz7/oGIp4FnHXxhDeDFiu/lNIhQSYmiJorT38JTgISnOUAUxWX3F9YAccbm+byI06tMxItzfV6P52f+TfFF/xMs/DNUdKD4500Ul7LELe3s7HxijJ3u7e19TZLEBADG2Gm1Wv2haVpwe3vbf80J2KKHp6enSZZlBkpmW3lOxtgNAARB8KEUCWCM3Uyn03fklGX5AQCq1erbUiSAD1SW5Ut63t7evnr1CdA0LaCSn06nO7ShaVrAr191Bciy/JBlmaEoyq96va5KkhQ+j5e/SpEACljTtIBaIQCUoSts8QGXKfDcNigmozQakGWZIUlSyBg7XZSM0gxCsixfkiaUYg7QNC0gASytBkiSFCZJYjLGbur1uiqOxP/tv71e+w3mom/TaR39NAAAAABJRU5ErkJggg=="
        const val signMaskImage = "iVBORw0KGgoAAAANSUhEUgAAAEAAAAAgCAYAAACinX6EAAAABmJLR0QA/wD/AP+gvaeTAAAATklEQVRo3u3YsQ0AIAhFQXD/nXEGExv992qqCw10/decDK8KDwAAAAAAAAAAAAAAAJn17evKBgAAAAAAAAAAAAAAAAAAAABP/APGBgS3ARgNAzt6lbzKAAAAAElFTkSuQmCC"

        const val signItemImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABmJLR0QA/wD/AP+gvaeTAAAA+0lEQVQ4y8WSP2vDMBDF35kbPBosQZeWDm3RXo3pB+jYrWTpnO/p0aMHkSnFo2xjbwJj3KVnnBCU/oO+RUL3dPo9ScB/i2RSVdUTAIQQDgBgra2lVpblNQCkaXobQjhYa2tjzMY5V2BtIqLduVO01lupyWiM2QBAIiZrba2UGrTW29NNAKCUGohop5QaAKBt25ujUyTCV6W1vjoikOxr1DXBaYQ8z+8AgNcRPvEH7z0EtWkawV7Wvfd/F2F5RrnV78g5VywRuq57+cE/KihWZea3aZoemfl+HMfnc54k1iDLsj0zNzFPcolxnuf3XzW4pGiDvu8fiOg15vkAHnddZo3c02QAAAAASUVORK5CYII="
        const val signItemMaskImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABmJLR0QA/wD/AP+gvaeTAAAALklEQVQ4y2NkYGD4z0ABYGKgELAgsRnJ0P+fYheMGjAsDGAkkJSRE9f/0UDEDgD6YQQfnSu0ZwAAAABJRU5ErkJggg=="

        val existingBlocks = arrayOf(
            Blocks.OAK_SIGN,
            Blocks.SPRUCE_SIGN,
            Blocks.BIRCH_SIGN,
            Blocks.JUNGLE_SIGN,
            Blocks.ACACIA_SIGN,
            Blocks.CHERRY_SIGN,
            Blocks.DARK_OAK_SIGN,
            Blocks.MANGROVE_SIGN,
            Blocks.CRIMSON_SIGN,
            Blocks.WARPED_SIGN
        )

        val existingIdentifiers = existingBlocks.map { Registries.BLOCK.getId(it) }
    }
}