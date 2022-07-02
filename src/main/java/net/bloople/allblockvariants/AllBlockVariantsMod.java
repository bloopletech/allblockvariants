package net.bloople.allblockvariants;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
// import static net.devtech.arrp.json.animation.JAnimation.*;
// import static net.devtech.arrp.json.blockstate.JState.*;
// import static net.devtech.arrp.json.lang.JLang.*;
// import static net.devtech.arrp.json.loot.JLootTable.*;
// import static net.devtech.arrp.json.models.JModel.*;
// import static net.devtech.arrp.json.tags.JTags.*;

public class AllBlockVariantsMod implements ModInitializer {
    public static final String MOD_ID = "allblockvariants";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private final RuntimeResourcePack resourcePack = RuntimeResourcePack.create(MOD_ID);
    private final Metadata metadata = new Metadata();

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");

        new WallBlockCreator(resourcePack, metadata, Blocks.ORANGE_CONCRETE).create();

        createMetadata();

        RRPCallback.EVENT.register(a -> a.add(resourcePack));
    }

    private void createMetadata() {
        createTags();
        createTranslations();
    }

    private void createTags() {
        for(Map.Entry<String, List<String>> entry : metadata.getTags().entrySet()) {
            String category = entry.getKey();
            List<String> identifiers = entry.getValue();

            String tagValues = identifiers.stream().map(identifier -> "\"" + identifier + "\"")
                .collect(Collectors.joining(", "));

            String tags = """
            {
              "replace": false,
              "values": [
                {tag_values}
              ]
            }
            """;
            tags = tags.replace("{tag_values}", tagValues);

            resourcePack.addData(
                new Identifier("minecraft", "tags/blocks/" + category + ".json"),
                tags.getBytes(StandardCharsets.UTF_8));
        }
    }

    private void createTranslations() {
        List<String> translationKeysValues = new ArrayList<>();

        for(Map.Entry<String, String> entry : metadata.getTranslations().entrySet()) {
            String identifier = entry.getKey();
            String translation = entry.getValue();

            translationKeysValues.add("\"" + identifier + "\": \"" + translation + "\"");
        }

        String translations = """
            {
                {translation_keys_values}
            }
            """;
        translations = translations.replace("{translation_keys_values}",
            String.join(", ", translationKeysValues));

        LOGGER.info("translations: {}", translations);

        resourcePack.addAsset(new Identifier(MOD_ID, "lang/en_us.json"),
            translations.getBytes(StandardCharsets.UTF_8));
    }
}
