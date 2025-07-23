package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry


class ModStickCreator() : Creator {
    companion object {
        const val itemName = "mod_stick"
        val identifier = modId(itemName)
    }

    private lateinit var item: Item

    override fun runCommon() {
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
    override fun runClient(builder: ResourcePackBuilder) {
        val itemModel = """
            {
              "parent": "minecraft:item/handheld",
              "textures": {
                "layer0": "minecraft:item/stick"
              }
            }
        """
        builder.addItemModel(itemName, itemModel)

        builder.addTranslation("item.$MOD_ID.$itemName", "$MOD_NAME ${itemName.toTitleCase()}")
    }

    override fun runServer(builder: ResourcePackBuilder) {
        val recipe = """
            {
              "type": "minecraft:crafting_shaped",
              "category": "misc",
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
                "id": "$identifier"
              }
            }
        """
        builder.addRecipe(itemName, recipe)
    }

    override fun getBlockInfo(): BlockInfo? {
        return null
    }
}
