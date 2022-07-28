package net.bloople.allblockvariants

import net.fabricmc.api.*
import net.minecraft.block.AbstractGlassBlock
import org.slf4j.Logger
import org.slf4j.LoggerFactory

const val MOD_ID = "allblockvariants"

@EnvironmentInterface(value=EnvType.CLIENT, itf=ClientModInitializer::class)
class AllBlockVariantsMod : ClientModInitializer, DedicatedServerModInitializer {
    @Environment(value=EnvType.CLIENT)
    override fun onInitializeClient() {
        LOGGER.info("AllBlockVariantsMod onInitializeClient()")
        initialize(EnvType.CLIENT)
        LOGGER.info("AllBlockVariantsMod end onInitializeClient()")
    }

    override fun onInitializeServer() {
        LOGGER.info("AllBlockVariantsMod onInitializeServer()")
        initialize(EnvType.SERVER)
        LOGGER.info("AllBlockVariantsMod end onInitializeServer()")
    }

    private fun initialize(environment: EnvType) {
        val blockCreators: MutableList<BlockCreator> = ArrayList()

        BlockInfos.each { blockCreators.add(FenceCreator(it)) }
        BlockInfos.each { blockCreators.add(WallCreator(it)) }
        BlockInfos.each {
            blockCreators.add(if(it.block is AbstractGlassBlock) GlassStairsCreator(it) else StairsCreator(it))
        }
        BlockInfos.each {
            blockCreators.add(if(it.block is AbstractGlassBlock) GlassSlabCreator(it) else SlabCreator(it))
        }
        BlockInfos.each {
            blockCreators.add(if(it.block is AbstractGlassBlock) GlassVerticalSlabCreator(it) else VerticalSlabCreator(it))
        }
        BlockInfos.each {
            blockCreators.add(if(it.block is AbstractGlassBlock) GlassThinVerticalSlabCreator(it) else ThinVerticalSlabCreator(it))
        }
        BlockInfos.each { blockCreators.add(ButtonCreator(it)) }
        BlockInfos.each { blockCreators.add(DoorCreator(it)) }
        BlockInfos.each { blockCreators.add(FenceGateCreator(it)) }

        for(blockCreator in blockCreators) blockCreator.createCommon()

        ResourcePackBuilder().use {
            if(environment == EnvType.CLIENT) {
                VerticalSlabCreator.createClient(it)
                ThinVerticalSlabCreator.createClient(it)
            }

            for(blockCreator in blockCreators) {
                if(environment == EnvType.CLIENT) blockCreator.createClient(it)
                blockCreator.createServer(it)
            }
        }
    }

    companion object {
        // This logger is used to write text to the console and the log file.
        // It is considered best practice to use your mod id as the logger's name.
        // That way, it's clear which mod wrote info, warnings, and errors.
        @JvmField
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    }
}

