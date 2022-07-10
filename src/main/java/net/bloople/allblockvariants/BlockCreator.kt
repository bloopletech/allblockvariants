package net.bloople.allblockvariants

import net.bloople.allblockvariants.AllBlockVariantsMod.Companion.LOGGER
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Block
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

abstract class BlockCreator(private val builder: ResourcePackBuilder) {
    fun createAll() {
        for(blockInfo in BlockInfos.BLOCK_INFOS) create(blockInfo)
    }

    abstract fun create(blockInfo: BlockInfo)

    fun applyBlockInfo(blockInfo: BlockInfo, block: Block, identifier: Identifier) {
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

fun vanillaBlockExists(blockName: String): Boolean {
    val result = Registry.BLOCK.getOrEmpty(Identifier(blockName)).isPresent
    LOGGER.info("vanillaBlockExists? blockName: {}, result: {}", blockName, result)
    return result
}

fun transformBlockName(blockName: String): String {
    return blockName.removeSuffix("_planks")
}
