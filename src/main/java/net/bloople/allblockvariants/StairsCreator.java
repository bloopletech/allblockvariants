package net.bloople.allblockvariants;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.bloople.allblockvariants.AllBlockVariantsMod.LOGGER;
import static net.bloople.allblockvariants.AllBlockVariantsMod.MOD_ID;

public class StairsCreator {
    private final ResourcePackBuilder builder;
    private Identifier existingIdentifier;
    private String existingBlockName;
    private String existingBlockBlockId;
    private String blockName;
    private String blockBlockId;
    private Identifier identifier;


    public StairsCreator(ResourcePackBuilder builder) {
        this.builder = builder;
    }

    public void create(Block existingBlock) {
        existingIdentifier = Registry.BLOCK.getId(existingBlock);
        existingBlockName = existingIdentifier.getPath();
        existingBlockBlockId = "minecraft:block/" + existingBlockName;

        blockName = existingBlockName + "_stairs";
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
            new StairsBlock(existingBlock.getDefaultState(), AbstractBlock.Settings.copy(existingBlock))
        );

        Registry.register(
            Registry.ITEM,
            identifier,
            new BlockItem(block, new Item.Settings().group(ItemGroup.DECORATIONS))
        );

        String blockState = interpolate("""
            {
              "variants": {
                "facing=east,half=bottom,shape=inner_left": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "y": 270
                },
                "facing=east,half=bottom,shape=inner_right": {
                  "model": "{block_block_id}_inner"
                },
                "facing=east,half=bottom,shape=outer_left": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "y": 270
                },
                "facing=east,half=bottom,shape=outer_right": {
                  "model": "{block_block_id}_outer"
                },
                "facing=east,half=bottom,shape=straight": {
                  "model": "{block_block_id}"
                },
                "facing=east,half=top,shape=inner_left": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "x": 180
                },
                "facing=east,half=top,shape=inner_right": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 90
                },
                "facing=east,half=top,shape=outer_left": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "x": 180
                },
                "facing=east,half=top,shape=outer_right": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 90
                },
                "facing=east,half=top,shape=straight": {
                  "model": "{block_block_id}",
                  "uvlock": true,
                  "x": 180
                },
                "facing=north,half=bottom,shape=inner_left": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "y": 180
                },
                "facing=north,half=bottom,shape=inner_right": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "y": 270
                },
                "facing=north,half=bottom,shape=outer_left": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "y": 180
                },
                "facing=north,half=bottom,shape=outer_right": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "y": 270
                },
                "facing=north,half=bottom,shape=straight": {
                  "model": "{block_block_id}",
                  "uvlock": true,
                  "y": 270
                },
                "facing=north,half=top,shape=inner_left": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 270
                },
                "facing=north,half=top,shape=inner_right": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "x": 180
                },
                "facing=north,half=top,shape=outer_left": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 270
                },
                "facing=north,half=top,shape=outer_right": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "x": 180
                },
                "facing=north,half=top,shape=straight": {
                  "model": "{block_block_id}",
                  "uvlock": true,
                  "x": 180,
                  "y": 270
                },
                "facing=south,half=bottom,shape=inner_left": {
                  "model": "{block_block_id}_inner"
                },
                "facing=south,half=bottom,shape=inner_right": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "y": 90
                },
                "facing=south,half=bottom,shape=outer_left": {
                  "model": "{block_block_id}_outer"
                },
                "facing=south,half=bottom,shape=outer_right": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "y": 90
                },
                "facing=south,half=bottom,shape=straight": {
                  "model": "{block_block_id}",
                  "uvlock": true,
                  "y": 90
                },
                "facing=south,half=top,shape=inner_left": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 90
                },
                "facing=south,half=top,shape=inner_right": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 180
                },
                "facing=south,half=top,shape=outer_left": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 90
                },
                "facing=south,half=top,shape=outer_right": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 180
                },
                "facing=south,half=top,shape=straight": {
                  "model": "{block_block_id}",
                  "uvlock": true,
                  "x": 180,
                  "y": 90
                },
                "facing=west,half=bottom,shape=inner_left": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "y": 90
                },
                "facing=west,half=bottom,shape=inner_right": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "y": 180
                },
                "facing=west,half=bottom,shape=outer_left": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "y": 90
                },
                "facing=west,half=bottom,shape=outer_right": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "y": 180
                },
                "facing=west,half=bottom,shape=straight": {
                  "model": "{block_block_id}",
                  "uvlock": true,
                  "y": 180
                },
                "facing=west,half=top,shape=inner_left": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 180
                },
                "facing=west,half=top,shape=inner_right": {
                  "model": "{block_block_id}_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 270
                },
                "facing=west,half=top,shape=outer_left": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 180
                },
                "facing=west,half=top,shape=outer_right": {
                  "model": "{block_block_id}_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 270
                },
                "facing=west,half=top,shape=straight": {
                  "model": "{block_block_id}",
                  "uvlock": true,
                  "x": 180,
                  "y": 180
                }
              }
            }
            """);

        builder.addBlockState(blockName, blockState);

        String blockModel = interpolate("""
            {
              "parent": "minecraft:block/stairs",
              "textures": {
                "bottom": "{existing_block_block_id}",
                "side": "{existing_block_block_id}",
                "top": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName, blockModel);

        String innerBlockModel = interpolate("""
            {
              "parent": "minecraft:block/inner_stairs",
              "textures": {
                "bottom": "{existing_block_block_id}",
                "side": "{existing_block_block_id}",
                "top": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName + "_inner", innerBlockModel);

        String outerBlockModel = interpolate("""
            {
              "parent": "minecraft:block/outer_stairs",
              "textures": {
                "bottom": "{existing_block_block_id}",
                "side": "{existing_block_block_id}",
                "top": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName + "_outer", outerBlockModel);

        String itemModel = interpolate("""
            {
              "parent": "{block_block_id}"
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
                  "item": "{existing_identifier}"
                }
              },
              "pattern": [
                "#  ",
                "## ",
                "###"
              ],
              "result": {
                "count": 4,
                "item": "{identifier}"
              }
            }
            """);

        builder.addRecipe(blockName, recipe);

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

        builder.addRecipe(blockName + "_from_cobblestone_stonecutting", stonecuttingRecipe);

        builder.addTag("stairs", identifier.toString());
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
