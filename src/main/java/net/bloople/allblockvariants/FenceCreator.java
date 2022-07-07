package net.bloople.allblockvariants;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.bloople.allblockvariants.AllBlockVariantsMod.LOGGER;
import static net.bloople.allblockvariants.AllBlockVariantsMod.MOD_ID;

public class FenceCreator {
    private final ResourcePackBuilder builder;
    private Identifier existingIdentifier;
    private String existingBlockName;
    private String existingBlockBlockId;
    private String blockName;
    private String blockBlockId;
    private Identifier identifier;


    public FenceCreator(ResourcePackBuilder builder) {
        this.builder = builder;
    }

    public void create(Block existingBlock) {
        existingIdentifier = Registry.BLOCK.getId(existingBlock);
        existingBlockName = existingIdentifier.getPath();
        existingBlockBlockId = "minecraft:block/" + existingBlockName;

        blockName = existingBlockName + "_fence";
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
            new FenceBlock(AbstractBlock.Settings.copy(existingBlock))
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
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side",
                    "uvlock": true
                  },
                  "when": {
                    "north": "true"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side",
                    "uvlock": true,
                    "y": 90
                  },
                  "when": {
                    "east": "true"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side",
                    "uvlock": true,
                    "y": 180
                  },
                  "when": {
                    "south": "true"
                  }
                },
                {
                  "apply": {
                    "model": "{block_block_id}_side",
                    "uvlock": true,
                    "y": 270
                  },
                  "when": {
                    "west": "true"
                  }
                }
              ]
            }
            """);

        builder.addBlockState(blockName, blockState);

        String inventoryBlockModel = interpolate("""
            {
              "parent": "minecraft:block/fence_inventory",
              "textures": {
                "texture": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName + "_inventory", inventoryBlockModel);

        String postBlockModel = interpolate("""
            {
              "parent": "minecraft:block/fence_post",
              "textures": {
                "texture": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName + "_post", postBlockModel);

        String sideBlockModel = interpolate("""
            {
              "parent": "minecraft:block/fence_side",
              "textures": {
                "texture": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName + "_side", sideBlockModel);

        String itemModel = interpolate("""
            {
              "parent": "{block_block_id}_inventory"
            }
            """);

        builder.addItemModel(blockName, itemModel);

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

        builder.addBlockLootTable(blockName, lootTable);

        String recipe = interpolate("""
            {
              "type": "minecraft:crafting_shaped",
              "key": {
                "#": {
                  "item": "minecraft:stick"
                },
                "W": {
                  "item": "{existing_identifier}"
                }
              },
              "pattern": [
                "W#W",
                "W#W"
              ],
              "result": {
                "count": 3,
                "item": "{identifier}"
              }
            }
            """);

        builder.addRecipe(blockName, recipe);

        builder.addTag("fences", identifier.toString());
        builder.addTranslation("block." + MOD_ID + "." + blockName, Util.toTitleCase(blockName));
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
}
