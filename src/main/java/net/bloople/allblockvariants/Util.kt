package net.bloople.allblockvariants

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item

class Util {
    companion object {
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

        fun copyCompostability(existingItem: Item, item: Item) {
            val registry = CompostingChanceRegistry.INSTANCE
            val existingEntry = registry.get(existingItem)
            if(existingEntry != null && existingEntry > 0) registry.add(item, existingEntry)
        }

        fun copyFlammability(existingBlock: Block, block: Block) {
            val registry = FlammableBlockRegistry.getDefaultInstance()
            val existingBlockEntry = registry.get(existingBlock)
            if(existingBlockEntry.burnChance > 0 || existingBlockEntry.spreadChance > 0) {
                registry.add(block, existingBlockEntry.burnChance, existingBlockEntry.spreadChance)
            }
        }

        fun copyFuel(existingItem: Item, item: Item) {
            val registry = FuelRegistry.INSTANCE
            val existingEntry = registry.get(existingItem)
            if(existingEntry != null && existingEntry > 0) registry.add(item, existingEntry)
        }

        fun copySpecialProperties(existingItem: Item, item: Item) {
            copyCompostability(existingItem, item)
            copyFuel(existingItem, item)
            // LootEntryTypeRegistry
        }

        fun copySpecialProperties(existingBlock: Block, block: Block) {
            copyFlammability(existingBlock, block)
            // FlattenableBlockRegistry
            // OxadizableBlockRegistry
            // StrippableBlockRegistry
            // TillableBlockRegistry
            // VillagerInteractionRegistries
        }
    }
}


