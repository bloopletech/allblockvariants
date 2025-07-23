package net.bloople.allblockvariants

import net.minecraft.util.Identifier

class AdvancedDerivedBlockInfo(
    blockInfo: BlockInfo,
    blockNameBuilder: DerivedBlockInfo.() -> Pair<String, String>)
    : DerivedBlockInfo(blockInfo, { blockNameBuilder(this).first }) {
    private val blockNameBuilderResult = blockNameBuilder(this)

    val parentBlockName = blockNameBuilderResult.second
    private val modParentIdentifier = modId(parentBlockName)
    private val vanillaParentIdentifier = id(parentBlockName)
    val parentIdentifier: Identifier? by lazy {
        if(Registration.blockExists(modParentIdentifier)) modParentIdentifier
        else if(Registration.blockExists(vanillaParentIdentifier)) vanillaParentIdentifier
        else null
    }
}