package net.bloople.allblockvariants

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.SlabBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class SlabCreator(private val builder: ResourcePackBuilder) : BlockCreator(builder) {
    override fun create(blockInfo: BlockInfo) {
        val existingBlock = blockInfo.block
        val existingIdentifier = Registry.BLOCK.getId(existingBlock)
        val existingBlockName = existingIdentifier.path
        val existingBlockBlockId = "minecraft:block/$existingBlockName"
        val blockName = "${transformBlockName(existingBlockName)}_slab"
        val blockBlockId = "$MOD_ID:block/$blockName"
        val identifier = Identifier(MOD_ID, blockName)

        if(vanillaBlockExists(blockName)) return

        val block: Block = Registry.register(
            Registry.BLOCK,
            identifier,
            SlabBlock(AbstractBlock.Settings.copy(existingBlock))
        )

        Registry.register(
            Registry.ITEM,
            identifier,
            BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
        )

        applyBlockInfo(blockInfo, block, identifier)

        val blockState = """
            {
               "variants": {
                 "type=bottom": {
                   "model": "$blockBlockId"
                 },
                 "type=double": {
                   "model": "$existingBlockBlockId"
                 },
                 "type=top": {
                   "model": "${blockBlockId}_top"
                 }
               }
            }
        """.trimIndent()
        builder.addBlockState(blockName, blockState)

        val blockModel = """
            {
              "parent": "minecraft:block/slab",
              "textures": {
                "bottom": "$existingBlockBlockId",
                "side": "$existingBlockBlockId",
                "top": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel(blockName, blockModel)

        val topBlockModel = """
            {
              "parent": "minecraft:block/slab_top",
              "textures": {
                "bottom": "$existingBlockBlockId",
                "side": "$existingBlockBlockId",
                "top": "$existingBlockBlockId"
              }
            }
        """.trimIndent()
        builder.addBlockModel("${blockName}_top", topBlockModel)

        val itemModel = """
            {
              "parent": "$blockBlockId"
            }
        """.trimIndent()

        builder.addItemModel(blockName, itemModel)
        val lootTable = """
            {
              "type": "minecraft:block",
              "pools": [
                {
                  "bonus_rolls": 0.0,
                  "entries": [
                    {
                      "type": "minecraft:item",
                      "functions": [
                        {
                          "add": false,
                          "conditions": [
                            {
                              "block": "$identifier",
                              "condition": "minecraft:block_state_property",
                              "properties": {
                                "type": "double"
                              }
                            }
                          ],
                          "count": 2.0,
                          "function": "minecraft:set_count"
                        },
                        {
                          "function": "minecraft:explosion_decay"
                        }
                      ],
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
              "type": "minecraft:crafting_shaped",
              "key": {
                "#": {
                  "item": "$existingIdentifier"
                }
              },
              "pattern": [
                "###"
              ],
              "result": {
                "count": 6,
                "item": "$identifier"
              }
            }
        """.trimIndent()
        builder.addRecipe(blockName, recipe)

        val stonecuttingRecipe = """
            {
              "type": "minecraft:stonecutting",
              "count": 2,
              "ingredient": {
                "item": "$existingIdentifier"
              },
              "result": "$identifier"
            }
        """.trimIndent()
        builder.addRecipe("${blockName}_from_cobblestone_stonecutting", stonecuttingRecipe)

        builder.addTag("slabs", identifier.toString())
        builder.addTranslation("block.$MOD_ID.$blockName", Util.toTitleCase(blockName))
    }
}