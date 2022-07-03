package net.bloople.allblockvariants;

import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllBlockVariantsMod implements ModInitializer {
    public static final String MOD_ID = "allblockvariants";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private final RuntimeResourcePack resourcePack = RuntimeResourcePack.create(MOD_ID);

    private final ResourcePackBuilder builder = new ResourcePackBuilder(resourcePack);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");

        new WallBlocksCreator(builder).create();

        builder.create();
    }
}
