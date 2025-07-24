package net.bloople.allblockvariants

import net.bloople.allblockvariants.creators.*
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
        metrics = Metrics()

        val creators = createCommon()

        metrics.common.dump()

        ResourcePackBuilder(environment).use {
            for(creator in creators) {
                if(environment == EnvType.CLIENT) creator.runClient(it)
                creator.runServer(it)
            }

            if(environment == EnvType.CLIENT) metrics.client.dump()
            metrics.server.dump()
        }
    }

    private fun createCommon(): List<Creator> {
        val customCreators: MutableList<Creator> = ArrayList()

        customCreators += DyeColor.entries.map { DyedWoodCreator(it) }
        customCreators += DyeColor.entries.map { DyedLogCreator(it) }
        customCreators += DyeColor.entries.map { DyedStrippedLogCreator(it) }
        customCreators += DyeColor.entries.map { DyedPlanksCreator(it) }
        customCreators += DyeColor.entries.map { DyedBricksCreator(it) }
        customCreators += DyeColor.entries.map { DyedSignCreator(it) }
        customCreators += DyeColor.entries.map { DyedFlowerPotCreator(it) }
        customCreators += DyeColor.entries.map { DyedTargetCreator(it) }
        customCreators += DyeColor.entries.map { DyedRedstoneLampCreator(it) }
        customCreators += buildDyedFlowerPotCreators()
        customCreators.forEach { it.runCommon() }

        val customBlockInfos = customCreators.mapNotNull { it.getBlockInfo() }

        val derivedCreators: MutableList<Creator> = ArrayList()
        val blockInfos = BLOCK_INFOS.values + customBlockInfos

        derivedCreators += blockInfos.map { FenceCreator.getCreator(it) }
        derivedCreators += blockInfos.map { WallCreator.getCreator(it) }
        derivedCreators += blockInfos.map { StairsCreator.getCreator(it) }
        derivedCreators += blockInfos.map { SlabCreator.getCreator(it) }
        derivedCreators += blockInfos.map { ThinSlabCreator.getCreator(it) }
        derivedCreators += blockInfos.map { VerticalSlabCreator.getCreator(it) }
        derivedCreators += blockInfos.map { ThinVerticalSlabCreator.getCreator(it) }
        derivedCreators += blockInfos.map { ButtonCreator.getCreator(it) }
        derivedCreators += blockInfos.map { DoorCreator.getCreator(it) }
        derivedCreators += blockInfos.map { TrapdoorCreator.getCreator(it) }
        derivedCreators += blockInfos.map { FenceGateCreator.getCreator(it) }
        derivedCreators += ModStickCreator()
        derivedCreators.forEach { it.runCommon() }

        return customCreators + derivedCreators
    }

    private fun buildDyedFlowerPotCreators(): List<BlockCreator> {
        return POTTED_BLOCK_INFOS.flatMap { blockInfo ->
            DyeColor.entries.map { DyedPottedContentCreator(blockInfo, it) }
        }
    }

    companion object {
        val LOGGER: Logger = getLogger(this::class)
    }
}

