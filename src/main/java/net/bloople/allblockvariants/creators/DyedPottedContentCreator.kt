package net.bloople.allblockvariants.creators

import net.bloople.allblockvariants.*
import net.bloople.allblockvariants.blocks.DyedFlowerPotBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.FlowerPotBlock
import net.minecraft.util.DyeColor

class DyedPottedContentCreator(blockInfo: BlockInfo, private val dyeColor: DyeColor) : BlockCreator() {
    override val dbi = DerivedBlockInfo(blockInfo) { "${dyeColor.getName()}_${transformedExistingBlockName}" }
    private val contentBlock = (dbi.existingBlock as FlowerPotBlock).content

    override fun common() {
        registerBlock(DyedFlowerPotBlock(contentBlock, dbi.blockSettings.mapColor(dyeColor), dyeColor.mapColor))
    }

    @Environment(value=EnvType.CLIENT)
    override fun client(builder: ResourcePackBuilder) {
        with(dbi) {
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
                  "parent": "$existingBlockBlockId",
                  "textures": {
                    "particle": "${MOD_ID}:block/${dyeColor.getName()}_flower_pot",
                    "flowerpot": "${MOD_ID}:block/${dyeColor.getName()}_flower_pot"
                  }
                }
            """
            builder.addBlockModel(blockName, blockModel)
        }
    }

    override fun server(builder: ResourcePackBuilder) {
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
                          "name": "${MOD_ID}:${dyeColor.getName()}_flower_pot"
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
            """
            builder.addBlockLootTable(blockName, lootTable)

            builder.addBlockTag("flower_pots", identifier)
        }
    }

    override fun vanillaBlockServer(builder: ResourcePackBuilder) {
    }

    override fun getBlockInfo(): BlockInfo? {
        return null
    }
}