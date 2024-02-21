package net.bloople.allblockvariants

import net.fabricmc.api.*
import net.minecraft.util.DyeColor
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

        customCreators += DyeColor.entries.map { DyedWoodCreator(metrics, it) }
        customCreators += DyeColor.entries.map { DyedLogCreator(metrics, it) }
        customCreators += DyeColor.entries.map { DyedStrippedLogCreator(metrics, it) }
        customCreators += DyeColor.entries.map { DyedPlanksCreator(metrics, it) }
        customCreators += DyeColor.entries.map { DyedBricksCreator(metrics, it) }
        customCreators += DyeColor.entries.map { DyedSignCreator(metrics, it) }
        customCreators += DyeColor.entries.map { DyedFlowerPotCreator(metrics, it) }
        customCreators += DyeColor.entries.map { DyedTargetCreator(metrics, it) }
        customCreators += DyeColor.entries.map { DyedRedstoneLampCreator(metrics, it) }
        customCreators += buildDyedFlowerPotCreators(metrics)
        customCreators.forEach { it.createCommon() }

        val customBlockInfos = customCreators.mapNotNull { it.getBlockInfo() }

        val derivedCreators: MutableList<Creator> = ArrayList()
        val blockInfos = BLOCK_INFOS.values + customBlockInfos

        derivedCreators += blockInfos.map { FenceCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { WallCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { StairsCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { SlabCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { ThinSlabCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { VerticalSlabCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { ThinVerticalSlabCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { ButtonCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { DoorCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { TrapdoorCreator.getCreator(it, metrics) }
        derivedCreators += blockInfos.map { FenceGateCreator.getCreator(it, metrics) }
        derivedCreators += ModStickCreator(metrics)
        derivedCreators.forEach { it.createCommon() }

        return customCreators + derivedCreators
    }

    private fun buildDyedFlowerPotCreators(metrics: Metrics): List<BlockCreator> {
        return POTTED_BLOCK_INFOS.flatMap { blockInfo ->
            DyeColor.entries.map { DyedPottedContentCreator(metrics, blockInfo, it) }
        }
    }

    companion object {
        val LOGGER: Logger = getLogger(this::class)
    }
}

