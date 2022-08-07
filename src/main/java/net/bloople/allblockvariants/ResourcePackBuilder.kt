package net.bloople.allblockvariants

import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Block
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.item.ItemConvertible
import net.minecraft.resource.ResourcePack
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import java.io.InputStream
import java.nio.charset.StandardCharsets

class ResourcePackBuilder(private val environment: EnvType) {
    companion object {
        @JvmField
        val BLOCK_COLOUR_PROVIDERS: MutableList<Pair<BlockColorProvider, Array<Block>>> = ArrayList()
        @JvmField
        val ITEM_COLOUR_PROVIDERS: MutableList<Pair<ItemForBlockColorProvider, Array<ItemConvertible>>> = ArrayList()
    }

    private val resourcePack = RuntimeResourcePack.create(MOD_ID)
    private val tags = HashMap<String, MutableList<String>>()
    private val mineableTags = HashMap<MiningTool, MutableList<String>>()
    private val needsToolTags = HashMap<MiningToolLevel, MutableList<String>>()
    private val translations: HashMap<String, String> = HashMap()

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
        if(environment == EnvType.CLIENT) createTranslations()
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

    @Environment(value=EnvType.CLIENT)
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

    @Environment(value=EnvType.CLIENT)
    fun addTranslation(identifier: String, translation: String) {
        translations[identifier] = translation
    }

    @Environment(value=EnvType.CLIENT)
    fun addBlockState(blockName: String, blockState: String) {
        resourcePack.addAsset(
            Identifier(MOD_ID, "blockstates/$blockName.json"),
            blockState.toByteArray(StandardCharsets.UTF_8))
    }

    @Environment(value=EnvType.CLIENT)
    fun addBlockModel(blockName: String, blockModel: String) {
        resourcePack.addAsset(
            Identifier(MOD_ID, "models/block/$blockName.json"),
            blockModel.toByteArray(StandardCharsets.UTF_8))
    }

    @Environment(value=EnvType.CLIENT)
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

    @Environment(value=EnvType.CLIENT)
    fun addBlockTexture(blockName: String, callback: (RuntimeResourcePack, Identifier) -> ByteArray) {
        resourcePack.addLazyResource(
            ResourceType.CLIENT_RESOURCES,
            Identifier(MOD_ID, "textures/block/$blockName.png"),
            callback
        )
    }

    @Environment(value=EnvType.CLIENT)
    fun addBlockTexture(blockName: String, callback: () -> ByteArray) {
        resourcePack.addLazyResource(
            ResourceType.CLIENT_RESOURCES,
            Identifier(MOD_ID, "textures/block/$blockName.png")
        ) { _: RuntimeResourcePack, _: Identifier -> return@addLazyResource callback() }
    }

    @Environment(value=EnvType.CLIENT)
    fun addItemTexture(itemName: String, callback: (RuntimeResourcePack, Identifier) -> ByteArray) {
        resourcePack.addLazyResource(
            ResourceType.CLIENT_RESOURCES,
            Identifier(MOD_ID, "textures/item/$itemName.png"),
            callback
        )
    }

    @Environment(value=EnvType.CLIENT)
    fun addItemTexture(itemName: String, callback: () -> ByteArray) {
        resourcePack.addLazyResource(
            ResourceType.CLIENT_RESOURCES,
            Identifier(MOD_ID, "textures/item/$itemName.png")
        ) { _: RuntimeResourcePack, _: Identifier -> return@addLazyResource callback() }
    }

    @Environment(value=EnvType.CLIENT)
    fun getBlockTexture(blockName: String): InputStream {
        return resourcePack.open(ResourceType.CLIENT_RESOURCES, Identifier(MOD_ID, "textures/block/$blockName.png"))
    }

    @Environment(value= EnvType.CLIENT)
    fun addBlockColorProvider(provider: BlockColorProvider, blocks: Array<Block>) {
        BLOCK_COLOUR_PROVIDERS.add(Pair(provider, blocks))
    }

    @Environment(value= EnvType.CLIENT)
    fun addItemColorProvider(provider: ItemForBlockColorProvider, items: Array<ItemConvertible>) {
        ITEM_COLOUR_PROVIDERS.add(Pair(provider, items))
    }
}