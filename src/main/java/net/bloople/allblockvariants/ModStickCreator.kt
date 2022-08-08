package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


class ModStickCreator {
    companion object {
        val itemName = "mod_stick"
        val identifier = Identifier(MOD_ID, itemName)
    }

    private lateinit var item: Item

    fun doCreateCommon() {
        item = Registry.register(
            Registry.ITEM,
            identifier,
            ModStickItem(Item.Settings().maxCount(1).group(ItemGroup.MISC))
        )
    }

    @Environment(value= EnvType.CLIENT)
    fun doCreateClient(builder: ResourcePackBuilder) {
        val itemModel = """
            {
              "parent": "minecraft:item/handheld",
              "textures": {
                "layer0": "minecraft:item/stick"
              }
            }
        """.trimIndent()
        builder.addItemModel(itemName, itemModel)

        builder.addTranslation("item.$MOD_ID.$itemName", "AllBlockVariants ${Util.toTitleCase(itemName)}")
    }

    fun doCreateServer(builder: ResourcePackBuilder) {
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
}
