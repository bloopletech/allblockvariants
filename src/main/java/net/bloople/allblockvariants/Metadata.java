package net.bloople.allblockvariants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Metadata {
    private HashMap<String, List<String>> tags = new HashMap<>();
    private HashMap<String, String> translations = new HashMap<>();

    public Metadata() {
    }

    public HashMap<String, List<String>> getTags() {
        return tags;
    }

    public HashMap<String, String> getTranslations() {
        return translations;
    }

    public void addTag(String category, String identifier) {
        tags.putIfAbsent(category, new ArrayList<>());
        tags.get(category).add(identifier);
    }

    public void addTranslation(String identifier, String translation) {
        translations.put(identifier, translation);
    }
}
