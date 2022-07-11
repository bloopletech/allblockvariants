package net.bloople.allblockvariants

import net.devtech.arrp.api.RuntimeResourcePack
import net.fabricmc.api.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

const val MOD_ID = "allblockvariants"

@EnvironmentInterface(value=EnvType.CLIENT, itf=ClientModInitializer::class)
class AllBlockVariantsMod : ModInitializer, ClientModInitializer, DedicatedServerModInitializer {
    companion object {
        // This logger is used to write text to the console and the log file.
        // It is considered best practice to use your mod id as the logger's name.
        // That way, it's clear which mod wrote info, warnings, and errors.
        @JvmField
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

        private val resourcePack = RuntimeResourcePack.create(MOD_ID)
        private val builder = ResourcePackBuilder(resourcePack)
        private val blockCreators: MutableList<BlockCreator> = ArrayList()
    }

    override fun onInitialize() {
        LOGGER.info("AllBlockVariantsMod onInitialize()")

        BlockInfos.each { blockCreators.add(FenceCreator(builder, it)) }
        BlockInfos.each { blockCreators.add(WallCreator(builder, it)) }
        BlockInfos.each { blockCreators.add(StairsCreator(builder, it)) }
        BlockInfos.each { blockCreators.add(SlabCreator(builder, it)) }
        BlockInfos.each { blockCreators.add(ButtonCreator(builder, it)) }
        BlockInfos.each { blockCreators.add(DoorCreator(builder, it)) }
        BlockInfos.each { blockCreators.add(FenceGateCreator(builder, it)) }

        for(blockCreator in blockCreators) blockCreator.createCommon()

        LOGGER.info("AllBlockVariantsMod end onInitialize()")
    }

    @Environment(value=EnvType.CLIENT)
    override fun onInitializeClient() {
        LOGGER.info("AllBlockVariantsMod onInitializeClient()")

        for(blockCreator in blockCreators) {
            blockCreator.createClient()
            blockCreator.createServer()
        }

        builder.create()

        LOGGER.info("AllBlockVariantsMod end onInitializeClient()")
    }

    override fun onInitializeServer() {
        LOGGER.info("AllBlockVariantsMod onInitializeServer()")

        for(blockCreator in blockCreators) blockCreator.createServer()

        builder.create()

        LOGGER.info("AllBlockVariantsMod end onInitializeServer()")
    }
}

