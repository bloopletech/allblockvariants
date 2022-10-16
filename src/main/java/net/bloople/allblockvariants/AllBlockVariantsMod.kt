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

        val customBlockCreators: MutableList<BlockCreator> = ArrayList()

        ColourInfos.each { customBlockCreators.add(DyedWoodCreator(metrics, it)) }
        ColourInfos.each { customBlockCreators.add(DyedLogCreator(metrics, it)) }
        ColourInfos.each { customBlockCreators.add(DyedStrippedLogCreator(metrics, it)) }
        ColourInfos.each { customBlockCreators.add(DyedPlanksCreator(metrics, it)) }

        for(blockCreator in customBlockCreators) blockCreator.createCommon()
        val customBlockInfos = customBlockCreators.map { it.getBlockInfo() }

        val blockCreators: MutableList<BlockCreator> = ArrayList()
        val blockInfos = BlockInfos.BLOCK_INFOS + customBlockInfos

        blockInfos.forEach { blockCreators.add(FenceCreator(metrics, it)) }
        blockInfos.forEach { blockCreators.add(WallCreator(metrics, it)) }
        blockInfos.forEach { blockCreators.add(StairsCreator.getCreator(it, metrics)) }
        blockInfos.forEach { blockCreators.add(SlabCreator.getCreator(it, metrics)) }
        blockInfos.forEach { blockCreators.add(ThinSlabCreator.getCreator(it, metrics)) }
        blockInfos.forEach { blockCreators.add(VerticalSlabCreator.getCreator(it, metrics)) }
        blockInfos.forEach { blockCreators.add(ThinVerticalSlabCreator.getCreator(it, metrics)) }
        blockInfos.forEach { blockCreators.add(ButtonCreator(metrics, it)) }
        blockInfos.forEach { blockCreators.add(DoorCreator(metrics, it)) }
        blockInfos.forEach { blockCreators.add(TrapdoorCreator.getCreator(it, metrics)) }
        blockInfos.forEach { blockCreators.add(FenceGateCreator(metrics, it)) }

        for(blockCreator in blockCreators) blockCreator.createCommon()
        val modStickCreator = ModStickCreator(metrics)
        modStickCreator.doCreateCommon()

        metrics.common.dump()

        ResourcePackBuilder(metrics, environment).use {
            for(blockCreator in customBlockCreators) {
                if(environment == EnvType.CLIENT) blockCreator.createClient(it)
                blockCreator.createServer(it)
            }
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

