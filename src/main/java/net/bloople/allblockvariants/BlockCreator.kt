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

        if(dbi.vanillaBlockExists) return doVanillaCreateCommon()
        doCreateCommon()
    }

    @Environment(value= EnvType.CLIENT)
    override fun createClient(builder: ResourcePackBuilder) {
        if(!shouldCreate()) return

        if(dbi.vanillaBlockExists) return doVanillaCreateClient(builder)
        RegisterUtil.createClientCommon(builder, dbi, block)
        doCreateClient(builder)
    }

    override fun createServer(builder: ResourcePackBuilder) {
        if(!shouldCreate()) return

        if(dbi.vanillaBlockExists) return doVanillaCreateServer(builder)
        RegisterUtil.createServerCommon(builder, dbi, block)
        doCreateServer(builder)
    }

    protected open fun shouldCreate(): Boolean {
        return true
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
        this.block = RegisterUtil.registerBlock(identifier, block)
    }

    protected fun registerItem(item: Item, registryKey: RegistryKey<ItemGroup>)
        = registerItem(dbi.identifier, item, registryKey)
    protected fun registerItem(identifier: Identifier, item: Item, registryKey: RegistryKey<ItemGroup>) {
        this.item = RegisterUtil.registerItem(identifier, item, registryKey)
    }
}