package net.bloople.allblockvariants

import net.devtech.arrp.api.RuntimeResourcePack
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

const val MOD_ID = "allblockvariants"

class AllBlockVariantsMod : ModInitializer, ClientModInitializer, DedicatedServerModInitializer {
    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("AllBlockVariantsMod onInitialize()")

        LOGGER.info("AllBlockVariantsMod end onInitialize()")
    }

    override fun onInitializeClient() {
        LOGGER.info("AllBlockVariantsMod onInitializeClient()")

        val resourcePack = RuntimeResourcePack.create(MOD_ID)

        val builder = ResourcePackBuilder(resourcePack)

        BlockInfos.each { FenceCreator(builder, it).createClient() }
        BlockInfos.each { FenceGateCreator(builder, it).createClient() }
        BlockInfos.each { WallCreator(builder, it).createClient() }
        BlockInfos.each { StairsCreator(builder, it).createClient() }
        BlockInfos.each { SlabCreator(builder, it).createClient() }
        BlockInfos.each { ButtonCreator(builder, it).createClient() }
        BlockInfos.each { DoorCreator(builder, it).createClient() }

        builder.create()

        LOGGER.info("AllBlockVariantsMod end onInitializeClient()")
    }

    override fun onInitializeServer() {
        LOGGER.info("AllBlockVariantsMod onInitializeServer()")

        val resourcePack = RuntimeResourcePack.create(MOD_ID)

        val builder = ResourcePackBuilder(resourcePack)

        BlockInfos.each { FenceCreator(builder, it).createServer() }
        BlockInfos.each { FenceGateCreator(builder, it).createServer() }
        BlockInfos.each { WallCreator(builder, it).createServer() }
        BlockInfos.each { StairsCreator(builder, it).createServer() }
        BlockInfos.each { SlabCreator(builder, it).createServer() }
        BlockInfos.each { ButtonCreator(builder, it).createServer() }
        BlockInfos.each { DoorCreator(builder, it).createServer() }

        builder.create()

        LOGGER.info("AllBlockVariantsMod end onInitializeServer()")
    }

    companion object {
        // This logger is used to write text to the console and the log file.
        // It is considered best practice to use your mod id as the logger's name.
        // That way, it's clear which mod wrote info, warnings, and errors.
        @JvmField
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    }
}

