package net.bloople.allblockvariants

import net.fabricmc.api.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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
        val blockCreators: MutableList<BlockCreator> = ArrayList()

        BlockInfos.each { blockCreators.add(FenceCreator(metrics, it)) }
        BlockInfos.each { blockCreators.add(WallCreator(metrics, it)) }
        BlockInfos.each { blockCreators.add(StairsCreator.getCreator(it, metrics)) }
        BlockInfos.each { blockCreators.add(SlabCreator.getCreator(it, metrics)) }
        BlockInfos.each { blockCreators.add(ThinSlabCreator.getCreator(it, metrics)) }
        BlockInfos.each { blockCreators.add(VerticalSlabCreator.getCreator(it, metrics)) }
        BlockInfos.each { blockCreators.add(ThinVerticalSlabCreator.getCreator(it, metrics)) }
        BlockInfos.each { blockCreators.add(ButtonCreator(metrics, it)) }
        BlockInfos.each { blockCreators.add(DoorCreator(metrics, it)) }
        BlockInfos.each { blockCreators.add(TrapdoorCreator.getCreator(it, metrics)) }
        BlockInfos.each { blockCreators.add(FenceGateCreator(metrics, it)) }
        ColourInfos.each { blockCreators.add(DyedWoodCreator(metrics, it)) }
        ColourInfos.each { blockCreators.add(DyedLogsCreator(metrics, it)) }
        ColourInfos.each { blockCreators.add(DyedStrippedLogsCreator(metrics, it)) }
        ColourInfos.each { blockCreators.add(DyedPlanksCreator(metrics, it)) }

        for(blockCreator in blockCreators) blockCreator.createCommon()
        val modStickCreator = ModStickCreator(metrics)
        modStickCreator.doCreateCommon()

        metrics.common.dump()

        ResourcePackBuilder(metrics, environment).use {
            for(blockCreator in blockCreators) {
                if(environment == EnvType.CLIENT) blockCreator.createClient(it)
                blockCreator.createServer(it)
            }
            if(environment == EnvType.CLIENT) modStickCreator.doCreateClient(it)
            modStickCreator.doCreateServer(it)

            if(environment == EnvType.CLIENT) metrics.client.dump()
            metrics.server.dump()
        }
    }

    companion object {
        private val LOGGER: Logger = getLogger(this::class)
    }
}

