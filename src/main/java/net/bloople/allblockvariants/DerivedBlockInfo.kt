package net.bloople.allblockvariants

import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class DerivedBlockInfo(val blockInfo: BlockInfo, blockNameBuilder: DerivedBlockInfo.() -> String) {
    val existingBlock = blockInfo.block
    val existingIdentifier = Registry.BLOCK.getId(existingBlock)
    val existingBlockName: String = existingIdentifier.path
    val existingBlockBlockId = "minecraft:block/$existingBlockName"
    val blockName = blockNameBuilder(this)
    val blockBlockId = "$MOD_ID:block/$blockName"
    val identifier = Identifier(MOD_ID, blockName)
    val itemItemId = "$MOD_ID:item/$blockName"
}