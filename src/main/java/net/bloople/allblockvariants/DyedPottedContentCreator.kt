package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.DyedFlowerPotBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.FlowerPotBlock
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.DyeColor

class DyedPottedContentCreator(
    metrics: Metrics,
    blockInfo: BlockInfo,
    private val dyeColor: DyeColor) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(blockInfo) { "${dyeColor.getName()}_${transformedExistingBlockName}" }
    private val contentBlock = (dbi.existingBlock as FlowerPotBlock).content

    override fun doCreateCommon() {
        registerBlock(DyedFlowerPotBlock(contentBlock, dbi.blockSettings.mapColor(dyeColor), dyeColor.mapColor))
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())

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
                  "parent": "$existingBlockBlockId",
                  "textures": {
                    "particle": "$MOD_ID:block/${dyeColor.getName()}_flower_pot",
                    "flowerpot": "$MOD_ID:block/${dyeColor.getName()}_flower_pot",
                    "plant": "${contentBlock.identifier.blockResourceLocation}",
                    "sapling": "${contentBlock.identifier.blockResourceLocation}"
                  }
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

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
                          "name": "$MOD_ID:${dyeColor.getName()}_flower_pot"
                        }
                      ],
                      "rolls": 1.0
                    },
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
                          "name": "${contentBlock.identifier}"
                        }
                      ],
                      "rolls": 1.0
                    }
                  ]
                }
            """.trimIndent()
            builder.addBlockLootTable(blockName, lootTable)

            builder.addBlockTag("flower_pots", identifier.toString())
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
    }

    override fun getBlockInfo(): BlockInfo? {
        return null
    }
}