package net.bloople.allblockvariants

import net.devtech.arrp.api.RuntimeResourcePack
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

const val MOD_ID = "allblockvariants"

class AllBlockVariantsMod : ModInitializer {
    private val resourcePack = RuntimeResourcePack.create(MOD_ID)

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("AllBlockVariantsMod onInitialize()")

        val builder = ResourcePackBuilder(resourcePack)

        val creators = arrayOf(
            FenceAndFenceGateCreator(builder),
            WallCreator(builder),
            StairsCreator(builder),
            SlabCreator(builder),
            ButtonCreator(builder)
        )
        for(creator in creators) creator.createAll()

        builder.create()

        LOGGER.info("AllBlockVariantsMod end onInitialize()")
    }

    companion object {
        // This logger is used to write text to the console and the log file.
        // It is considered best practice to use your mod id as the logger's name.
        // That way, it's clear which mod wrote info, warnings, and errors.
        @JvmField
        val LOGGER = LoggerFactory.getLogger(MOD_ID)
    }
}

