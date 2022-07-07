package net.bloople.allblockvariants;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.bloople.allblockvariants.AllBlockVariantsMod.LOGGER;
import static net.bloople.allblockvariants.AllBlockVariantsMod.MOD_ID;

public class FenceGateCreator {
    private final ResourcePackBuilder builder;
    private Identifier existingIdentifier;
    private String existingBlockName;
    private String existingBlockBlockId;
    private String blockName;
    private String blockBlockId;
    private Identifier identifier;


    public FenceGateCreator(ResourcePackBuilder builder) {
        this.builder = builder;
    }

    public void create(Block existingBlock) {
        existingIdentifier = Registry.BLOCK.getId(existingBlock);
        existingBlockName = existingIdentifier.getPath();
        existingBlockBlockId = "minecraft:block/" + existingBlockName;

        blockName = existingBlockName + "_fence_gate";
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
            new FenceGateBlock(AbstractBlock.Settings.copy(existingBlock))
        );

        Registry.register(
            Registry.ITEM,
            identifier,
            new BlockItem(block, new Item.Settings().group(ItemGroup.REDSTONE))
        );

        String blockState = interpolate("""
            {
              "variants": {
                "facing=east,in_wall=false,open=false": {
                  "model": "{block_block_id}",
                  "uvlock": true,
                  "y": 270
                },
                "facing=east,in_wall=false,open=true": {
                  "model": "{block_block_id}_open",
                  "uvlock": true,
                  "y": 270
                },
                "facing=east,in_wall=true,open=false": {
                  "model": "{block_block_id}_wall",
                  "uvlock": true,
                  "y": 270
                },
                "facing=east,in_wall=true,open=true": {
                  "model": "{block_block_id}_wall_open",
                  "uvlock": true,
                  "y": 270
                },
                "facing=north,in_wall=false,open=false": {
                  "model": "{block_block_id}",
                  "uvlock": true,
                  "y": 180
                },
                "facing=north,in_wall=false,open=true": {
                  "model": "{block_block_id}_open",
                  "uvlock": true,
                  "y": 180
                },
                "facing=north,in_wall=true,open=false": {
                  "model": "{block_block_id}_wall",
                  "uvlock": true,
                  "y": 180
                },
                "facing=north,in_wall=true,open=true": {
                  "model": "{block_block_id}_wall_open",
                  "uvlock": true,
                  "y": 180
                },
                "facing=south,in_wall=false,open=false": {
                  "model": "{block_block_id}",
                  "uvlock": true
                },
                "facing=south,in_wall=false,open=true": {
                  "model": "{block_block_id}_open",
                  "uvlock": true
                },
                "facing=south,in_wall=true,open=false": {
                  "model": "{block_block_id}_wall",
                  "uvlock": true
                },
                "facing=south,in_wall=true,open=true": {
                  "model": "{block_block_id}_wall_open",
                  "uvlock": true
                },
                "facing=west,in_wall=false,open=false": {
                  "model": "{block_block_id}",
                  "uvlock": true,
                  "y": 90
                },
                "facing=west,in_wall=false,open=true": {
                  "model": "{block_block_id}_open",
                  "uvlock": true,
                  "y": 90
                },
                "facing=west,in_wall=true,open=false": {
                  "model": "{block_block_id}_wall",
                  "uvlock": true,
                  "y": 90
                },
                "facing=west,in_wall=true,open=true": {
                  "model": "{block_block_id}_wall_open",
                  "uvlock": true,
                  "y": 90
                }
              }
            }
            """);

        builder.addBlockState(blockName, blockState);

        String blockModel = interpolate("""
            {
              "parent": "minecraft:block/template_fence_gate",
              "textures": {
                "texture": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName, blockModel);

        String openBlockModel = interpolate("""
            {
              "parent": "minecraft:block/template_fence_gate_open",
              "textures": {
                "texture": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName + "_open", openBlockModel);

        String wallBlockModel = interpolate("""
            {
              "parent": "minecraft:block/template_fence_gate_wall",
              "textures": {
                "texture": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName + "_wall", wallBlockModel);

        String wallOpenBlockModel = interpolate("""
            {
              "parent": "minecraft:block/template_fence_gate_wall_open",
              "textures": {
                "texture": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName + "_wall_open", wallOpenBlockModel);

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
                  "item": "minecraft:stick"
                },
                "W": {
                  "item": "{existing_identifier}"
                }
              },
              "pattern": [
                "#W#",
                "#W#"
              ],
              "result": {
                "item": "{identifier}"
              }
            }
            """);

        builder.addRecipe(blockName, recipe);

        builder.addTag("fence_gates", identifier.toString());
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

