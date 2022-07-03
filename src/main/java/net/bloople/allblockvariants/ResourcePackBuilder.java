package net.bloople.allblockvariants;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.util.Identifier;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.bloople.allblockvariants.AllBlockVariantsMod.MOD_ID;

public class ResourcePackBuilder {
    private final RuntimeResourcePack resourcePack;
    private final HashMap<String, List<String>> tags = new HashMap<>();
    private final HashMap<String, String> translations = new HashMap<>();

    public ResourcePackBuilder(RuntimeResourcePack resourcePack) {
        this.resourcePack = resourcePack;
    }

    public void create() {
        createMetadata();
        RRPCallback.EVENT.register(a -> a.add(resourcePack));
    }

    private void createMetadata() {
        createTags();
        createTranslations();
    }

    private void createTags() {
        for(Map.Entry<String, List<String>> entry : tags.entrySet()) {
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

        for(Map.Entry<String, String> entry : translations.entrySet()) {
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

        resourcePack.addAsset(new Identifier(MOD_ID, "lang/en_us.json"),
            translations.getBytes(StandardCharsets.UTF_8));
    }

    public void addTag(String category, String identifier) {
        tags.putIfAbsent(category, new ArrayList<>());
        tags.get(category).add(identifier);
    }

    public void addTranslation(String identifier, String translation) {
        translations.put(identifier, translation);
    }

    public void addBlockState(String blockName, String blockState) {
        resourcePack.addAsset(
            new Identifier(MOD_ID, "blockstates/" + blockName + ".json"),
            blockState.getBytes(StandardCharsets.UTF_8));
    }

    public void addBlockModel(String blockName, String blockModel) {
        resourcePack.addAsset(
            new Identifier(MOD_ID, "models/block/" + blockName + ".json"),
            blockModel.getBytes(StandardCharsets.UTF_8));
    }

    public void addItemModel(String itemName, String itemModel) {
        resourcePack.addAsset(
            new Identifier(MOD_ID, "models/item/" + itemName + ".json"),
            itemModel.getBytes(StandardCharsets.UTF_8));
    }

    public void addBlockLootTable(String blockName, String lootTable) {
        resourcePack.addData(
            new Identifier(MOD_ID, "loot_tables/blocks/" + blockName + ".json"),
            lootTable.getBytes(StandardCharsets.UTF_8));
    }


    public void addRecipe(String blockName, String recipe) {
        resourcePack.addData(
            new Identifier(MOD_ID, "recipes/" + blockName + ".json"),
            recipe.getBytes(StandardCharsets.UTF_8));
    }

}
