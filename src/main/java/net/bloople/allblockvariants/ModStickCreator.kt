package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.util.Identifier
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry


class ModStickCreator(private val metrics: Metrics) : Creator {
    companion object {
        const val itemName = "mod_stick"
        val identifier = Identifier(MOD_ID, itemName)
    }

    private lateinit var item: Item

    override fun createCommon() {
        item = Registry.register(
            Registries.ITEM,
            identifier,
            ModStickItem(Item.Settings().maxCount(1))
        )
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register {
            it.add(item)
        }
        metrics.common.itemsAdded++
    }

    @Environment(value= EnvType.CLIENT)
    override fun createClient(builder: ResourcePackBuilder) {
        val itemModel = """
            {
              "parent": "minecraft:item/handheld",
              "textures": {
                "layer0": "minecraft:item/stick"
              }
            }
        """.trimIndent()
        builder.addItemModel(itemName, itemModel)

        builder.addTranslation("item.$MOD_ID.$itemName", "$MOD_NAME ${Util.toTitleCase(itemName)}")
    }

    override fun createServer(builder: ResourcePackBuilder) {
        val recipe = """
            {
              "type": "minecraft:crafting_shaped",
              "key": {
                "L": {
                  "item": "minecraft:lapis_lazuli"
                },
                "S": {
                  "item": "minecraft:stick"
                }
              },
              "pattern": [
                "   ",
                " L ",
                " S "
              ],
              "result": {
                "count": 1,
                "item": "$identifier"
              }
            }
        """.trimIndent()
        builder.addRecipe(itemName, recipe)
    }

    override fun getBlockInfo(): BlockInfo? {
        return null
    }
}
