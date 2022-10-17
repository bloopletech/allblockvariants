package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


abstract class BlockCreator : Creator {
    abstract val dbi: DerivedBlockInfo
    lateinit var block: Block
    lateinit var item: Item

    protected abstract fun doCreateCommon()
    protected open fun doVanillaCreateCommon() {}
    @Environment(value=EnvType.CLIENT)
    protected abstract fun doCreateClient(builder: ResourcePackBuilder)
    @Environment(value=EnvType.CLIENT)
    protected open fun doVanillaCreateClient(builder: ResourcePackBuilder) {}
    protected abstract fun doCreateServer(builder: ResourcePackBuilder)
    protected open fun doVanillaCreateServer(builder: ResourcePackBuilder) {}

    override fun createCommon() {
        if(!shouldCreate()) return

        if(vanillaBlockMissing()) doCreateCommon()
        else doVanillaCreateCommon()
    }

    @Environment(value=EnvType.CLIENT)
    override fun createClient(builder: ResourcePackBuilder) {
        if(!shouldCreate()) return

        if(vanillaBlockMissing()) doCreateClient(builder)
        else doVanillaCreateClient(builder)
    }

    override fun createServer(builder: ResourcePackBuilder) {
        if(!shouldCreate()) return

        if(vanillaBlockMissing()) doCreateServer(builder)
        else doVanillaCreateServer(builder)
    }

    protected open fun shouldCreate(): Boolean {
        return true
    }

    private fun vanillaBlockMissing(): Boolean {
        return !dbi.vanillaBlockExists
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

    override fun getBlockInfo(): BlockInfo? {
        return BlockInfo(
            block,
            dbi.blockInfo.preferredTool,
            dbi.blockInfo.needsToolLevel,
            dbi.blockInfo.flammabilityBurnChance,
            dbi.blockInfo.flammabilitySpreadChance,
            dbi.blockInfo.itemCompostability,
            dbi.blockInfo.itemFuel,
            transformDerived = false
        )
    }
}

fun blockExists(identifier: Identifier): Boolean {
    return Registry.BLOCK.getOrEmpty(identifier).isPresent
}


