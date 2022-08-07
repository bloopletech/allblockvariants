package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


abstract class BlockCreator(val dbi: DerivedBlockInfo) {
    lateinit var block: Block
    lateinit var item: Item

    protected abstract fun doCreateCommon()
    @Environment(value=EnvType.CLIENT)
    protected abstract fun doCreateClient(builder: ResourcePackBuilder)
    protected abstract fun doCreateServer(builder: ResourcePackBuilder)

    fun createCommon() {
        if(shouldCreate()) doCreateCommon()
    }

    @Environment(value=EnvType.CLIENT)
    fun createClient(builder: ResourcePackBuilder) {
        if(shouldCreate()) doCreateClient(builder)
    }

    fun createServer(builder: ResourcePackBuilder) {
        if(shouldCreate()) doCreateServer(builder)
    }

    protected open fun shouldCreate(): Boolean {
        return !vanillaBlockExists(dbi.blockName)
    }

    fun registerBlockCommon(builder: ResourcePackBuilder) {
        with(dbi) {
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

fun vanillaBlockExists(blockName: String): Boolean {
    val result = Registry.BLOCK.getOrEmpty(Identifier(blockName)).isPresent
    //LOGGER.info("vanillaBlockExists? blockName: {}, result: {}", blockName, result)
    return result
}

fun transformBlockName(blockName: String): String {
    return blockName.removeSuffix("_planks").replace("bricks", "brick")
}
