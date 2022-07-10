package net.bloople.allblockvariants

import net.bloople.allblockvariants.AllBlockVariantsMod.Companion.LOGGER
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.ItemConvertible
import net.minecraft.util.Identifier

class Util {
    companion object {
        fun dumpTags() {
            LOGGER.info("dumping tags for SPRUCE_PLANKDS")
            Blocks.SPRUCE_PLANKS.getRegistryEntry().streamTags().forEach { LOGGER.info("tag: {}", it.toString()) }
        }

        // Based on https://stackoverflow.com/a/1086134
        @JvmStatic
        fun toTitleCase(input: String): String {
            val titleCase = StringBuilder(input.length)
            var nextTitleCase = true

            for(c in input.toCharArray()) {
                var d = c
                if(d == '_') {
                    d = ' '
                    nextTitleCase = true
                }
                else if(nextTitleCase) {
                    d = d.titlecaseChar()
                    nextTitleCase = false
                }
                titleCase.append(d)
            }

            return titleCase.toString()
        }

        fun applyBlockInfo(builder: ResourcePackBuilder, blockInfo: BlockInfo, block: Block, identifier: Identifier) {
            builder.addMineableTag(blockInfo.preferredTool, identifier.toString())

            blockInfo.needsToolLevel?.let { builder.addNeedsToolTag(it, identifier.toString()) }

            if(blockInfo.itemCompostability > 0) {
                CompostingChanceRegistry.INSTANCE.add(block.asItem(), blockInfo.itemCompostability)
            }

            if(blockInfo.itemFuel > 0) {
                FuelRegistry.INSTANCE.add(block.asItem(), blockInfo.itemFuel)
            }

            // LootEntryTypeRegistry

            if(blockInfo.flammabilityBurnChance > 0 || blockInfo.flammabilitySpreadChance > 0) {
                FlammableBlockRegistry.getDefaultInstance().add(
                    block,
                    blockInfo.flammabilityBurnChance,
                    blockInfo.flammabilitySpreadChance)
            }

            // FlattenableBlockRegistry
            // OxadizableBlockRegistry
            // StrippableBlockRegistry
            // TillableBlockRegistry
            // VillagerInteractionRegistries
        }
    }
}


