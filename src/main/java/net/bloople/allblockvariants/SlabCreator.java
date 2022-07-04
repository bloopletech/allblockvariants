package net.bloople.allblockvariants;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.bloople.allblockvariants.AllBlockVariantsMod.LOGGER;
import static net.bloople.allblockvariants.AllBlockVariantsMod.MOD_ID;

public class SlabCreator {
    private final ResourcePackBuilder builder;
    private Identifier existingIdentifier;
    private String existingBlockName;
    private String existingBlockBlockId;
    private String blockName;
    private String blockBlockId;
    private Identifier identifier;


    public SlabCreator(ResourcePackBuilder builder) {
        this.builder = builder;
    }

    public void create(Block existingBlock) {
        existingIdentifier = Registry.BLOCK.getId(existingBlock);
        existingBlockName = existingIdentifier.getPath();
        existingBlockBlockId = "minecraft:block/" + existingBlockName;

        blockName = existingBlockName + "_slab";
        blockBlockId = MOD_ID + ":block/" + blockName;
        identifier = new Identifier(MOD_ID, blockName);

//        LOGGER.info("existingIdentifier: {}", existingIdentifier);
//        LOGGER.info("existingBlockName: {}", existingBlockName);
//        LOGGER.info("existingBlockBlockId: {}", existingBlockBlockId);
//        LOGGER.info("blockName: {}", blockName);
//        LOGGER.info("blockBlockId: {}", blockBlockId);
//        LOGGER.info("identifier: {}", identifier);

        Block block = Registry.register(
            Registry.BLOCK,
            identifier,
            new SlabBlock(AbstractBlock.Settings.copy(existingBlock))
        );

        Registry.register(
            Registry.ITEM,
            identifier,
            new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
        );

        String blockState = interpolate("""
            {
               "variants": {
                 "type=bottom": {
                   "model": "{block_block_id}"
                 },
                 "type=double": {
                   "model": "{existing_block_block_id}"
                 },
                 "type=top": {
                   "model": "{block_block_id}_top"
                 }
               }
            }
            """);

        builder.addBlockState(blockName, blockState);

        String blockModel = interpolate("""
            {
              "parent": "minecraft:block/slab",
              "textures": {
                "bottom": "{existing_block_block_id}",
                "side": "{existing_block_block_id}",
                "top": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName, blockModel);

        String topBlockModel = interpolate("""
            {
              "parent": "minecraft:block/slab_top",
              "textures": {
                "bottom": "{existing_block_block_id}",
                "side": "{existing_block_block_id}",
                "top": "{existing_block_block_id}"
              }
            }
            """);

        builder.addBlockModel(blockName + "_top", topBlockModel);

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
                  "entries": [
                    {
                      "type": "minecraft:item",
                      "functions": [
                        {
                          "add": false,
                          "conditions": [
                            {
                              "block": "{identifier}",
                              "condition": "minecraft:block_state_property",
                              "properties": {
                                "type": "double"
                              }
                            }
                          ],
                          "count": 2.0,
                          "function": "minecraft:set_count"
                        },
                        {
                          "function": "minecraft:explosion_decay"
                        }
                      ],
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
                "###"
              ],
              "result": {
                "count": 6,
                "item": "{identifier}"
              }
            }
            """);

        builder.addRecipe(blockName, recipe);

        String stonecuttingRecipe = interpolate("""
            {
              "type": "minecraft:stonecutting",
              "count": 2,
              "ingredient": {
                "item": "{existing_identifier}"
              },
              "result": "{identifier}"
            }
            """);

        builder.addRecipe(blockName + "_from_cobblestone_stonecutting", stonecuttingRecipe);

        builder.addTag("slabs", identifier.toString());
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
