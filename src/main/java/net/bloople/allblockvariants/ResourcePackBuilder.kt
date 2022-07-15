package net.bloople.allblockvariants

import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack
import net.minecraft.resource.ResourcePack
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import java.nio.charset.StandardCharsets

class ResourcePackBuilder() {
    private val resourcePack = RuntimeResourcePack.create(MOD_ID)
    private var tags = HashMap<String, MutableList<String>>()
    private var mineableTags = HashMap<MiningTool, MutableList<String>>()
    private var needsToolTags = HashMap<MiningToolLevel, MutableList<String>>()
    private var translations: HashMap<String, String> = HashMap()

    fun use(block: (ResourcePackBuilder) -> Unit) {
        RRPCallback.BEFORE_VANILLA.register(RRPCallback { resourcePacks: MutableList<ResourcePack?> ->
            block(this)
            createMetadata()
            resourcePacks.add(resourcePack)
        })
    }

    private fun createMetadata() {
        createTags()
        createMineableTags()
        createNeedsToolTags()
        createTranslations()
    }

    private fun createTags() {
        for((category, identifiers) in tags) {
            val tagValues = identifiers.joinToString { "\"$it\"" }
            val tags = """
                {
                  "replace": false,
                  "values": [
                    $tagValues
                  ]
                }
            """.trimIndent()

            resourcePack.addData(
                Identifier("minecraft", "tags/blocks/$category.json"),
                tags.toByteArray(StandardCharsets.UTF_8))
        }
    }

    private fun createMineableTags() {
        for((tool, identifiers) in mineableTags) {
            val tagValues = identifiers.joinToString { "\"$it\"" }
            val tags = """
                {
                  "replace": false,
                  "values": [
                    $tagValues
                  ]
                }
            """.trimIndent()

            resourcePack.addData(
                Identifier("minecraft", "tags/blocks/mineable/${tool.toString().lowercase()}.json"),
                tags.toByteArray(StandardCharsets.UTF_8))
        }
    }

    private fun createNeedsToolTags() {
        for((needsTool, identifiers) in needsToolTags) {
            val tagValues = identifiers.joinToString { "\"$it\"" }
            val tags = """
                {
                  "replace": false,
                  "values": [
                    $tagValues
                  ]
                }
            """.trimIndent()

            resourcePack.addData(
                Identifier("minecraft", "tags/blocks/needs_${needsTool.toString().lowercase()}_tool.json"),
                tags.toByteArray(StandardCharsets.UTF_8))
        }
    }

    private fun createTranslations() {
        val translationKeysValues = translations.map { (k, v) -> "\"$k\": \"$v\"" }.joinToString()

        val translations = """
            {
                $translationKeysValues
            }
        """.trimIndent()

        resourcePack.addAsset(
            Identifier(MOD_ID, "lang/en_us.json"),
            translations.toByteArray(StandardCharsets.UTF_8))
    }

    fun addTag(category: String, identifier: String) {
        tags.getOrPut(category) { ArrayList() }.add(identifier)
    }

    fun addMineableTag(category: MiningTool, identifier: String) {
        mineableTags.getOrPut(category) { ArrayList() }.add(identifier)
    }

    fun addNeedsToolTag(needsTool: MiningToolLevel, identifier: String) {
        needsToolTags.getOrPut(needsTool) { ArrayList() }.add(identifier)
    }

    fun addTranslation(identifier: String, translation: String) {
        translations[identifier] = translation
    }

    fun addBlockState(blockName: String, blockState: String) {
        resourcePack.addAsset(
            Identifier(MOD_ID, "blockstates/$blockName.json"),
            blockState.toByteArray(StandardCharsets.UTF_8))
    }

    fun addBlockModel(blockName: String, blockModel: String) {
        resourcePack.addAsset(
            Identifier(MOD_ID, "models/block/$blockName.json"),
            blockModel.toByteArray(StandardCharsets.UTF_8))
    }

    fun addItemModel(itemName: String, itemModel: String) {
        resourcePack.addAsset(
            Identifier(MOD_ID, "models/item/$itemName.json"),
            itemModel.toByteArray(StandardCharsets.UTF_8))
    }

    fun addBlockLootTable(blockName: String, lootTable: String) {
        resourcePack.addData(
            Identifier(MOD_ID, "loot_tables/blocks/$blockName.json"),
            lootTable.toByteArray(StandardCharsets.UTF_8))
    }

    fun addRecipe(blockName: String, recipe: String) {
        resourcePack.addData(
            Identifier(MOD_ID, "recipes/$blockName.json"),
            recipe.toByteArray(StandardCharsets.UTF_8))
    }

    fun addItemTexture(itemName: String, callback: (RuntimeResourcePack, Identifier) -> ByteArray) {
        resourcePack.addLazyResource(
            ResourceType.CLIENT_RESOURCES,
            Identifier(MOD_ID, "textures/item/$itemName.png"),
            callback
        )
    }
}