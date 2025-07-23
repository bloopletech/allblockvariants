package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

abstract class BlockCreator() : Creator {
    abstract val dbi: DerivedBlockInfo
    lateinit var block: Block
    lateinit var item: Item

    protected abstract fun doCreateCommon()
    protected open fun doVanillaCreateCommon() {}
    @Environment(value= EnvType.CLIENT)
    protected abstract fun doCreateClient(builder: ResourcePackBuilder)
    @Environment(value= EnvType.CLIENT)
    protected open fun doVanillaCreateClient(builder: ResourcePackBuilder) {}
    protected abstract fun doCreateServer(builder: ResourcePackBuilder)
    protected open fun doVanillaCreateServer(builder: ResourcePackBuilder) {}

    override fun createCommon() {
        if(!shouldCreate()) return

        if(vanillaBlockMissing()) doCreateCommon()
        else doVanillaCreateCommon()
    }

    @Environment(value= EnvType.CLIENT)
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

    protected fun createClientCommon(builder: ResourcePackBuilder) = RegisterUtil.createClientCommon(builder, dbi)
    protected fun createServerCommon(builder: ResourcePackBuilder) = RegisterUtil.createServerCommon(builder, dbi, block)

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
        this.block = RegisterUtil.registerBlock(identifier, block)
    }

    protected fun registerItem(item: Item, registryKey: RegistryKey<ItemGroup>)
        = registerItem(dbi.identifier, item, registryKey)
    protected fun registerItem(identifier: Identifier, item: Item, registryKey: RegistryKey<ItemGroup>) {
        this.item = RegisterUtil.registerItem(identifier, item, registryKey)
    }
}