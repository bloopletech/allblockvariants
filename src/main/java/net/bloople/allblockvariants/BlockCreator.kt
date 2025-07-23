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

    protected abstract fun common()
    protected open fun vanillaCommon() {}
    @Environment(value= EnvType.CLIENT)
    protected abstract fun client(builder: ResourcePackBuilder)
    @Environment(value= EnvType.CLIENT)
    protected open fun vanillaBlockClient(builder: ResourcePackBuilder) {}
    protected abstract fun server(builder: ResourcePackBuilder)
    protected open fun vanillaBlockServer(builder: ResourcePackBuilder) {}

    override fun runCommon() {
        if(!shouldCreate()) return

        if(dbi.vanillaBlockExists) return vanillaCommon()
        common()
    }

    @Environment(value= EnvType.CLIENT)
    override fun runClient(builder: ResourcePackBuilder) {
        if(!shouldCreate()) return

        if(dbi.vanillaBlockExists) return vanillaBlockClient(builder)
        Registration.clientCommon(builder, dbi, block)
        client(builder)
    }

    override fun runServer(builder: ResourcePackBuilder) {
        if(!shouldCreate()) return

        if(dbi.vanillaBlockExists) return vanillaBlockServer(builder)
        Registration.serverCommon(builder, dbi, block)
        server(builder)
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
        this.block = Registration.registerBlock(identifier, block)
    }

    protected fun registerItem(item: Item, registryKey: RegistryKey<ItemGroup>)
        = registerItem(dbi.identifier, item, registryKey)
    protected fun registerItem(identifier: Identifier, item: Item, registryKey: RegistryKey<ItemGroup>) {
        this.item = Registration.registerItem(identifier, item, registryKey)
    }
}