package net.bloople.allblockvariants

import net.devtech.arrp.api.RuntimeResourcePack
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

const val MOD_ID = "allblockvariants"

class AllBlockVariantsMod : ModInitializer {
    private val resourcePack = RuntimeResourcePack.create(MOD_ID)
    private val builder = ResourcePackBuilder(resourcePack)

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Hello Fabric world!")

        FenceAndFenceGateGroupCreator(builder).create()
        WallGroupCreator(builder).create()
        StairsGroupCreator(builder).create()
        SlabsGroupCreator(builder).create()
        ButtonGroupCreator(builder).create()

        builder.create()
    }

    companion object {
        // This logger is used to write text to the console and the log file.
        // It is considered best practice to use your mod id as the logger's name.
        // That way, it's clear which mod wrote info, warnings, and errors.
        @JvmField
        val LOGGER = LoggerFactory.getLogger(MOD_ID)
    }
}

