package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Block
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.item.ItemConvertible
import net.minecraft.resource.ResourcePack
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import pers.solid.brrp.v1.api.RuntimeResourcePack
import pers.solid.brrp.v1.fabric.api.RRPCallback
import java.io.InputStream
import java.nio.charset.StandardCharsets

class ResourcePackBuilder(private val environment: EnvType) {
    companion object {
        @JvmField
        val BLOCK_COLOUR_PROVIDERS: MutableList<Pair<BlockColorProvider, Array<Block>>> = ArrayList()
        @JvmField
        val ITEM_COLOUR_PROVIDERS: MutableList<Pair<ItemForBlockColorProvider, Array<ItemConvertible>>> = ArrayList()
    }

    private val resourcePack = RuntimeResourcePack.create(modId("pack"))
    private val blockTags = HashMap<String, MutableList<String>>()
    private val mineableTags = HashMap<MiningTool, MutableList<String>>()
    private val needsToolTags = HashMap<MiningToolLevel, MutableList<String>>()
    private val itemTags = HashMap<String, MutableList<String>>()
    private val translations: HashMap<String, String> = HashMap()

    private fun addAsset(path: Identifier, content: String) {
        resourcePack.addAsset(path, content.trimIndent().toByteArray(StandardCharsets.UTF_8))
    }

    private fun addAsset(identifier: String, content: String) {
        addAsset(id(identifier), content)
    }

    private fun addData(path: Identifier, content: String) {
        resourcePack.addData(path, content.trimIndent().toByteArray(StandardCharsets.UTF_8))
    }

    private fun addData(identifier: String, content: String) {
        addData(id(identifier), content)
    }

    private fun addResource(path: Identifier, callback: () -> ByteArray) {
        resourcePack.addLazyResource(
            ResourceType.CLIENT_RESOURCES,
            path
        ) { _: RuntimeResourcePack, _: Identifier -> return@addLazyResource callback() }
    }

    private fun addResource(path: String, callback: () -> ByteArray) {
        addResource(id(path), callback)
    }

    fun use(block: (ResourcePackBuilder) -> Unit) {
        RRPCallback.BEFORE_VANILLA.register(RRPCallback { resourcePacks: MutableList<ResourcePack?> ->
            metrics.clear(environment)
            resourcePack.clearResources()
            block(this)
            createMetadata()
            resourcePacks.add(resourcePack)
        })
    }

    private fun createMetadata() {
        createBlockTags()
        createItemTags()
        createMineableTags()
        createNeedsToolTags()
        if(environment == EnvType.CLIENT) createTranslations()
    }

    private fun buildTags(identifiers: List<String>): String {
        val tagValues = identifiers.joinToString { "\"$it\"" }
        return """
            {
              "replace": false,
              "values": [
                $tagValues
              ]
            }
        """
    }

    private fun createBlockTags() {
        for((category, identifiers) in blockTags) addData("tags/block/$category.json", buildTags(identifiers))
    }

    private fun createMineableTags() {
        for((tool, identifiers) in mineableTags) {
            addData("tags/block/mineable/${tool.toString().lowercase()}.json", buildTags(identifiers))
        }
    }

    private fun createNeedsToolTags() {
        for((needsTool, identifiers) in needsToolTags) {
            addData("tags/block/needs_${needsTool.toString().lowercase()}_tool.json", buildTags(identifiers))
        }
    }

    private fun createItemTags() {
        for((category, identifiers) in itemTags) addData("tags/item/$category.json", buildTags(identifiers))
    }

    @Environment(value=EnvType.CLIENT)
    private fun createTranslations() {
        val translationKeysValues = translations.map { (k, v) -> "\"$k\": \"$v\"" }.joinToString()

        val translations = """
            {
                $translationKeysValues
            }
        """

        addAsset("lang/en_us.json", translations)
    }

    fun addBlockTag(category: String, identifier: String) {
        blockTags.getOrPut(category) { ArrayList() }.add(identifier)
        metrics.server.blockTagsAdded++
    }

    fun addBlockTag(category: String, identifier: Identifier) = addBlockTag(category, identifier.toString())

    fun addMineableTag(category: MiningTool, identifier: String) {
        mineableTags.getOrPut(category) { ArrayList() }.add(identifier)
        metrics.server.mineableTagsAdded++
    }

    fun addMineableTag(category: MiningTool, identifier: Identifier) = addMineableTag(category, identifier.toString())

    fun addNeedsToolTag(needsTool: MiningToolLevel, identifier: String) {
        needsToolTags.getOrPut(needsTool) { ArrayList() }.add(identifier)
        metrics.server.needsToolTagsAdded++
    }

    fun addNeedsToolTag(needsTool: MiningToolLevel, identifier: Identifier) = addNeedsToolTag(needsTool, identifier.toString())

    fun addItemTag(category: String, identifier: String) {
        itemTags.getOrPut(category) { ArrayList() }.add(identifier)
        metrics.server.itemTagsAdded++
    }

    fun addItemTag(category: String, identifier: Identifier) = addItemTag(category, identifier.toString())

    @Environment(value=EnvType.CLIENT)
    fun addTranslation(identifier: String, translation: String) {
        translations[identifier] = translation
        metrics.client.translationsAdded++
    }

    @Environment(value=EnvType.CLIENT)
    fun addBlockTranslation(blockName: String) {
        addTranslation("block.$MOD_ID.$blockName", blockName.toTitleCase())
    }

    @Environment(value=EnvType.CLIENT)
    fun addBlockState(blockName: String, blockState: String) {
        addAsset(modId("blockstates/$blockName.json"), blockState)
        metrics.client.blockStatesAdded++
    }

    @Environment(value=EnvType.CLIENT)
    fun addBlockModel(blockName: String, blockModel: String) {
        addAsset(modId("models/block/$blockName.json"), blockModel)
        metrics.client.blockModelsAdded++
    }

    @Environment(value=EnvType.CLIENT)
    fun addItemModel(itemName: String, itemModel: String) {
        addAsset(modId("models/item/$itemName.json"), itemModel)
        metrics.client.itemModelsAdded++
    }

    fun addBlockLootTable(blockName: String, lootTable: String) {
        addData(modId("loot_table/blocks/$blockName.json"), lootTable)
        metrics.server.blockLootTablesAdded++
    }

    fun addRecipe(blockName: String, recipe: String) {
        addData(modId("recipe/$blockName.json"), recipe)
        metrics.server.recipesAdded++
    }

    @Environment(value=EnvType.CLIENT)
    fun addBlockTexture(blockName: String, callback: () -> ByteArray) {
        addResource(modId("textures/block/$blockName.png"), callback)
        metrics.client.blockTexturesAdded++
    }

    @Environment(value=EnvType.CLIENT)
    fun addEntityTexture(group: String, blockName: String, callback: () -> ByteArray) {
        addResource(modId("textures/entity/$group/$blockName.png"), callback)
        metrics.client.blockTexturesAdded++
    }

    @Environment(value=EnvType.CLIENT)
    fun addItemTexture(itemName: String, callback: () -> ByteArray) {
        addResource(modId("textures/item/$itemName.png"), callback)
        metrics.client.itemTexturesAdded++
    }

    @Environment(value=EnvType.CLIENT)
    fun containsClientResource(identifier: Identifier): Boolean {
        return resourcePack.open(ResourceType.CLIENT_RESOURCES, identifier) != null
    }

    @Environment(value=EnvType.CLIENT)
    fun openClientResource(identifier: Identifier): InputStream {
        return resourcePack.open(ResourceType.CLIENT_RESOURCES, identifier)!!.get()
    }

    @Environment(value=EnvType.CLIENT)
    fun getBlockTexture(blockName: String): InputStream {
        return openClientResource(modId("textures/block/$blockName.png"))
    }

    @Environment(value= EnvType.CLIENT)
    fun addBlockColorProvider(provider: BlockColorProvider, blocks: Array<Block>) {
        BLOCK_COLOUR_PROVIDERS.add(Pair(provider, blocks))
        metrics.client.blockColorProvidersAdded++
    }

    @Environment(value= EnvType.CLIENT)
    fun addItemColorProvider(provider: ItemForBlockColorProvider, items: Array<ItemConvertible>) {
        ITEM_COLOUR_PROVIDERS.add(Pair(provider, items))
        metrics.client.itemColorProvidersAdded++
    }

    fun addStonecuttingRecipe(recipeName: String, count: Int, input: Identifier, output: Identifier) {
        val recipe = """
            {
              "type": "minecraft:stonecutting",
              "ingredient": {
                "item": "$input"
              },
              "result": {
                "count": $count,
                "id": "$output"
              }
            }
        """
        addRecipe(recipeName, recipe)
    }

    fun addStonecuttingRecipe(dbi: DerivedBlockInfo, count: Int) {
        addStonecuttingRecipe("${dbi.blockName}_from_stonecutting", count, dbi.existingIdentifier, dbi.identifier)
    }
}