package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

object RegisterUtil {
    @Environment(value= EnvType.CLIENT)
    fun createClientCommon(builder: ResourcePackBuilder, dbi: DerivedBlockInfo) {
        builder.addBlockTranslation(dbi.blockName)
    }

    fun createServerCommon(builder: ResourcePackBuilder, dbi: DerivedBlockInfo, block: Block) {
        with(dbi) {
            builder.addMineableTag(blockInfo.preferredTool, identifier)

            blockInfo.needsToolLevel?.let { builder.addNeedsToolTag(it, identifier) }

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

            // FabricBrewingRecipeRegistryBuilder
            // FlattenableBlockRegistry
            // OxadizableBlockRegistry
            // StrippableBlockRegistry
            // TillableBlockRegistry
            // VillagerInteractionRegistries
        }
    }

    fun registerBlock(identifier: Identifier, block: Block): Block {
        return Registry.register(Registries.BLOCK, identifier, block).also { metrics.common.blocksAdded++ }
    }

    fun registerItem(identifier: Identifier, item: Item, registryKey: RegistryKey<ItemGroup>): Item {
        return Registry.register(Registries.ITEM, identifier, item).also {
            ItemGroupEvents.modifyEntriesEvent(registryKey).register { entries -> entries.add(it) }
            metrics.common.itemsAdded++
        }
    }

    fun blockExists(identifier: Identifier): Boolean {
        return Registries.BLOCK.getOrEmpty(identifier).isPresent
    }
}