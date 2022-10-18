package net.bloople.allblockvariants

import net.fabricmc.api.*
import org.slf4j.Logger

const val MOD_ID = "allblockvariants"
const val MOD_NAME = "AllBlockVariants"

@EnvironmentInterface(value=EnvType.CLIENT, itf=ClientModInitializer::class)
class AllBlockVariantsMod : ClientModInitializer, DedicatedServerModInitializer {
    @Environment(value=EnvType.CLIENT)
    override fun onInitializeClient() {
        LOGGER.info("$MOD_NAME onInitializeClient()")
        initialize(EnvType.CLIENT)
        LOGGER.info("$MOD_NAME end onInitializeClient()")
    }

    override fun onInitializeServer() {
        LOGGER.info("$MOD_NAME onInitializeServer()")
        initialize(EnvType.SERVER)
        LOGGER.info("$MOD_NAME end onInitializeServer()")
    }

    private fun initialize(environment: EnvType) {
        val metrics = Metrics()

        val creators = createCommon(metrics)

        metrics.common.dump()

        ResourcePackBuilder(metrics, environment).use {
            for(creator in creators) {
                if(environment == EnvType.CLIENT) creator.createClient(it)
                creator.createServer(it)
            }

            if(environment == EnvType.CLIENT) metrics.client.dump()
            metrics.server.dump()
        }
    }

    private fun createCommon(metrics: Metrics): List<Creator> {
        val customCreators: MutableList<Creator> = ArrayList()

        customCreators += COLOUR_INFOS.map { DyedWoodCreator(metrics, it) }
        customCreators += COLOUR_INFOS.map { DyedLogCreator(metrics, it) }
        customCreators += COLOUR_INFOS.map { DyedStrippedLogCreator(metrics, it) }
        customCreators += COLOUR_INFOS.map { DyedPlanksCreator(metrics, it) }
        customCreators += COLOUR_INFOS.map { DyedFlowerPotCreator(metrics, it) }
        customCreators += buildDyedFlowerPotCreators(metrics)
        customCreators.forEach { it.createCommon() }

        val customBlockInfos = customCreators.mapNotNull { it.getBlockInfo() }

        val derivedCreators: MutableList<Creator> = ArrayList()
        val blockInfos = BLOCK_INFOS.values + customBlockInfos

        derivedCreators += blockInfos.map { FenceCreator(metrics, it) }
        derivedCreators += blockInfos.map { WallCreator(metrics, it) }
        derivedCreators += blockInfos.map { StairsCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { SlabCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { ThinSlabCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { VerticalSlabCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { ThinVerticalSlabCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { ButtonCreator(metrics, it) }
        derivedCreators += blockInfos.map { DoorCreator(metrics, it) }
        derivedCreators += blockInfos.map { TrapdoorCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { FenceGateCreator(metrics, it) }
        derivedCreators += ModStickCreator(metrics)
        derivedCreators.forEach { it.createCommon() }

        return customCreators + derivedCreators
    }

    private fun buildDyedFlowerPotCreators(metrics: Metrics): List<BlockCreator> {
        return POTTED_BLOCK_INFOS.flatMap { blockInfo ->
            COLOUR_INFOS.map { DyedPottedContentCreator(metrics, blockInfo, it) }
        }
    }

    companion object {
        private val LOGGER: Logger = getLogger(this::class)
    }
}

