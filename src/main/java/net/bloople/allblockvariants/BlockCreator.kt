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


abstract class BlockCreator(val metrics: Metrics) : Creator {
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

    protected fun registerBlockCommon(builder: ResourcePackBuilder) {
        registerBlockCommon(builder, dbi, block)
    }

    protected fun registerBlockCommon(builder: ResourcePackBuilder, dbi: DerivedBlockInfo, block: Block)
    {
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
            dbi.blockInfo.blockSetType,
            transformDerived = false
        )
    }

    protected fun registerBlock(block: Block) = registerBlock(dbi.identifier, block)
    protected fun registerBlock(identifier: Identifier, block: Block) {
        this.block = customRegisterBlock(identifier, block)
    }

    protected fun customRegisterBlock(identifier: Identifier, block: Block): Block {
        return Registry.register(
            Registries.BLOCK,
            identifier,
            block
        ).also { metrics.common.blocksAdded++ }
    }

    protected fun registerItem(item: Item, registryKey: RegistryKey<ItemGroup>)
        = registerItem(dbi.identifier, item, registryKey)
    protected fun registerItem(identifier: Identifier, item: Item, registryKey: RegistryKey<ItemGroup>) {
        this.item = customRegisterItem(identifier, item, registryKey)
    }

    protected fun customRegisterItem(
        identifier: Identifier,
        item: Item,
        registryKey: RegistryKey<ItemGroup>
    ): Item {
        return Registry.register(
            Registries.ITEM,
            identifier,
            item
        ).also {
            ItemGroupEvents.modifyEntriesEvent(registryKey).register { entries -> entries.add(it) }
            metrics.common.itemsAdded++
        }
    }
}

fun blockExists(identifier: Identifier): Boolean {
    return Registries.BLOCK.getOrEmpty(identifier).isPresent
}


