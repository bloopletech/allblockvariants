package net.bloople.allblockvariants;

import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.nio.charset.StandardCharsets;

import static net.bloople.allblockvariants.AllBlockVariantsMod.LOGGER;
import static net.bloople.allblockvariants.AllBlockVariantsMod.MOD_ID;

public class WallBlockCreator {
    private final RuntimeResourcePack resourcePack;
    private final Metadata metadata;
    private final Block existingBlock;
    private Identifier existingIdentifier;
    private String existingBlockName;
    private String existingBlockBlockId;
    private String blockName;
    private String blockBlockId;
    private Identifier identifier;


    public WallBlockCreator(
        RuntimeResourcePack resourcePack,
        Metadata metadata,
        Block existingBlock) {
        this.resourcePack = resourcePack;
        this.metadata = metadata;
        this.existingBlock = existingBlock;
    }

    public void create() {
        existingIdentifier = Registry.BLOCK.getId(existingBlock);
        existingBlockName = existingIdentifier.getPath();
        existingBlockBlockId = "minecraft:block/" + existingBlockName;

        blockName = existingBlockName + "_wall";
        blockBlockId = MOD_ID + ":block/" + blockName;
        identifier = new Identifier(MOD_ID, blockName);

        LOGGER.info("existingIdentifier: {}", existingIdentifier);
        LOGGER.info("existingBlockName: {}", existingBlockName);
        LOGGER.info("existingBlockBlockId: {}", existingBlockBlockId);
        LOGGER.info("blockName: {}", blockName);
        LOGGER.info("blockBlockId: {}", blockBlockId);
        LOGGER.info("identifier: {}", identifier);

        Block block = Registry.register(
            Registry.BLOCK,
            identifier,
            new WallBlock(AbstractBlock.Settings.copy(existingBlock))
        );

        Registry.register(
            Registry.ITEM,
            identifier,
            new BlockItem(block, new Item.Settings().group(ItemGroup.DECORATIONS))
        );

        String blockState = interpolate("""
            {
              "multipart": [
                {
                  "apply": {
                    "model": "{block_block_id}_post"
                  },
                  "when": {
                    "up": "true"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side",
                    "uvlock": true
                  },
                  "when": {
                    "north": "low"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side",
                    "uvlock": true,
                    "y": 90
                  },
                  "when": {
                    "east": "low"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side",
                    "uvlock": true,
                    "y": 180
                  },
                  "when": {
                    "south": "low"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side",
                    "uvlock": true,
                    "y": 270
                  },
                  "when": {
                    "west": "low"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side_tall",
                    "uvlock": true
                  },
                  "when": {
                    "north": "tall"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side_tall",
                    "uvlock": true,
                    "y": 90
                  },
                  "when": {
                    "east": "tall"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side_tall",
                    "uvlock": true,
                    "y": 180
                  },
                  "when": {
                    "south": "tall"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side_tall",
                    "uvlock": true,
                    "y": 270
                  },
                  "when": {
                    "west": "tall"
                  }
                }
              ]
            }
            """);

        resourcePack.addAsset(
            new Identifier(MOD_ID, "blockstates/" + blockName + ".json"),
            blockState.getBytes(StandardCharsets.UTF_8));

        String inventoryBlockModel = interpolate("""
            {
              "parent": "minecraft:block/wall_inventory",
              "textures": {
                "wall": "{existing_block_block_id}"
              }
            }
            """);

        resourcePack.addAsset(
            new Identifier(MOD_ID, "models/block/" + blockName + "_inventory.json"),
            inventoryBlockModel.getBytes(StandardCharsets.UTF_8));

        String postBlockModel = interpolate("""
            {
              "parent": "minecraft:block/template_wall_post",
              "textures": {
                "wall": "{existing_block_block_id}"
              }
            }
            """);

        resourcePack.addAsset(
            new Identifier(MOD_ID, "models/block/" + blockName + "_post.json"),
            postBlockModel.getBytes(StandardCharsets.UTF_8));

        String sideBlockModel = interpolate("""
            {
              "parent": "minecraft:block/template_wall_side",
              "textures": {
                "wall": "{existing_block_block_id}"
              }
            }
            """);

        resourcePack.addAsset(
            new Identifier(MOD_ID, "models/block/" + blockName + "_side.json"),
            sideBlockModel.getBytes(StandardCharsets.UTF_8));

        String sideTallBlockModel = interpolate("""
            {
              "parent": "minecraft:block/template_wall_side_tall",
              "textures": {
                "wall": "{existing_block_block_id}"
              }
            }
            """);

        resourcePack.addAsset(
            new Identifier(MOD_ID, "models/block/" + blockName + "_side_tall.json"),
            sideTallBlockModel.getBytes(StandardCharsets.UTF_8));

        String itemModel = interpolate("""
            {
              "parent": "{block_block_id}_inventory"
            }
            """);

        resourcePack.addAsset(
            new Identifier(MOD_ID, "models/item/" + blockName + ".json"),
            itemModel.getBytes(StandardCharsets.UTF_8));

        String lootTable = interpolate("""
            {
              "type": "minecraft:block",
              "pools": [
                {
                  "bonus_rolls": 0.0,
                  "conditions": [
                    {
                      "condition": "minecraft:survives_explosion"
                    }
                  ],
                  "entries": [
                    {
                      "type": "minecraft:item",
                      "name": "{identifier}"
                    }
                  ],
                  "rolls": 1.0
                }
              ]
            }
            """);

        resourcePack.addData(
            new Identifier(MOD_ID, "loot_tables/blocks/" + blockName + ".json"),
            lootTable.getBytes(StandardCharsets.UTF_8));

        String recipe = interpolate("""
            {
              "type": "minecraft:crafting_shaped",
              "key": {
                "#": {
                  "item": "{existing_identifier}"
                }
              },
              "pattern": [
                "###",
                "###"
              ],
              "result": {
                "count": 6,
                "item": "{identifier}"
              }
            }
            """);

        resourcePack.addData(
            new Identifier(MOD_ID, "recipes/" + blockName + ".json"),
            recipe.getBytes(StandardCharsets.UTF_8));

        String stonecuttingRecipe = interpolate("""
            {
              "type": "minecraft:stonecutting",
              "count": 1,
              "ingredient": {
                "item": "{existing_identifier}"
              },
              "result": "{identifier}"
            }
            """);

        resourcePack.addData(
            new Identifier(MOD_ID, "recipes/" + blockName + "_from_cobblestone_stonecutting.json"),
            stonecuttingRecipe.getBytes(StandardCharsets.UTF_8));

        metadata.addTag("walls", identifier.toString());
        metadata.addTranslation("block." + MOD_ID + "." + blockName, toTitleCase(blockName));
    }

    private String interpolate(String input) {
        return input.replace("{mod_id}", MOD_ID)
            .replace("{existing_block_block_id}", existingBlockBlockId)
            .replace("{existing_block_name}", existingBlockName)
            .replace("{existing_identifier}", existingBlockName)
            .replace("{block_block_id}", blockBlockId)
            .replace("{block_name}", blockName)
            .replace("{identifier}", identifier.toString());
    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for(char c : input.toCharArray()) {
            if (c == '_') {
                c = ' ';
                nextTitleCase = true;
            }
            else if(nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
