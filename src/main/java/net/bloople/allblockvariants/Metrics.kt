package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

class Metrics {
    class Common {
        var blocksAdded: Int = 0
        var itemsAdded: Int = 0

        fun dump() {
            LOGGER.info("$MOD_NAME mod common metrics:")
            LOGGER.info("$blocksAdded blocks added")
            LOGGER.info("$itemsAdded items added")
        }

        companion object {
            private val LOGGER = getLogger(this::class)
        }
    }

    @Environment(value=EnvType.CLIENT)
    class Client {
        var translationsAdded: Int = 0
        var blockStatesAdded: Int = 0
        var blockModelsAdded: Int = 0
        var itemModelsAdded: Int = 0
        var blockTexturesAdded: Int = 0
        var itemTexturesAdded: Int = 0
        var blockColorProvidersAdded: Int = 0
        var itemColorProvidersAdded: Int = 0

        fun dump() {
            LOGGER.info("$MOD_NAME mod client metrics:")
            LOGGER.info("$translationsAdded translations added")
            LOGGER.info("$blockStatesAdded blockstates added")
            LOGGER.info("$blockModelsAdded block models added")
            LOGGER.info("$itemModelsAdded item models added")
            LOGGER.info("$blockTexturesAdded block textures added")
            LOGGER.info("$itemTexturesAdded item textures added")
            LOGGER.info("$blockColorProvidersAdded block color providers added")
            LOGGER.info("$itemColorProvidersAdded item color providers added")
        }

        companion object {
            private val LOGGER = getLogger(this::class)
        }
    }

    class Server {
        var tagsAdded: Int = 0
        var mineableTagsAdded: Int = 0
        var needsToolTagsAdded: Int = 0
        var blockLootTablesAdded: Int = 0
        var recipesAdded: Int = 0

        fun dump() {
            LOGGER.info("$MOD_NAME mod server metrics:")
            LOGGER.info("$tagsAdded tags added")
            LOGGER.info("$mineableTagsAdded mineable category tags added")
            LOGGER.info("$needsToolTagsAdded needs_tool category tags added")
            LOGGER.info("$blockLootTablesAdded block loot tables added")
            LOGGER.info("$recipesAdded recipes added")
        }

        companion object {
            private val LOGGER = getLogger(this::class)
        }
    }

    val common = Common()

    @Environment(value=EnvType.CLIENT)
    var client = Client()
        private set

    var server = Server()
        private set

    fun clear(environment: EnvType) {
        if(environment == EnvType.CLIENT) client = Client()
        server = Server()
    }
}