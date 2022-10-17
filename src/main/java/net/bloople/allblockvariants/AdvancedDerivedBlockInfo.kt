package net.bloople.allblockvariants

import net.minecraft.util.Identifier

class AdvancedDerivedBlockInfo(
    blockInfo: BlockInfo,
    blockNameBuilder: DerivedBlockInfo.() -> Pair<String, String>)
    : DerivedBlockInfo(blockInfo, { blockNameBuilder(this).first }) {
    private val blockNameBuilderResult = blockNameBuilder(this)

    val parentBlockName = blockNameBuilderResult.second
    private val modParentIdentifier = Identifier(MOD_ID, parentBlockName)
    private val vanillaParentIdentifier = Identifier(parentBlockName)
    val parentIdentifier: Identifier? by lazy {
        if(blockExists(modParentIdentifier)) modParentIdentifier
        else if(blockExists(vanillaParentIdentifier)) vanillaParentIdentifier
        else null
    }
}