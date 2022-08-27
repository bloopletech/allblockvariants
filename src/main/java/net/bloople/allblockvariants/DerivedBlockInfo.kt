package net.bloople.allblockvariants

import net.minecraft.util.Identifier

open class DerivedBlockInfo(val blockInfo: BlockInfo, blockNameBuilder: DerivedBlockInfo.() -> String) {
    val existingBlock = blockInfo.block

    val existingIdentifier = blockInfo.identifier
    val existingBlockName: String = existingIdentifier.path
    val existingBlockBlockId = blockInfo.modelIdentifier.blockResourceLocation

    val blockName by lazy { blockNameBuilder(this) }
    val blockBlockId = Identifier(MOD_ID, blockName).blockResourceLocation

    val vanillaIdentifier = Identifier(blockName)
    val vanillaBlockExists by lazy { blockExists(vanillaIdentifier) }

    val identifier = Identifier(MOD_ID, blockName)
    val itemItemId = identifier.itemResourceLocation

    val blockTextureInfo = blockInfo.textureInfo
    val existingBlockEndTextureId = blockTextureInfo.end.blockResourceLocation
    val existingBlockSideTextureId = blockTextureInfo.side.blockResourceLocation
    val existingBlockTopTextureId = blockTextureInfo.top.blockResourceLocation
    val existingBlockNorthTextureId = blockTextureInfo.north.blockResourceLocation
    val existingBlockEastTextureId = blockTextureInfo.east.blockResourceLocation
    val existingBlockSouthTextureId = blockTextureInfo.south.blockResourceLocation
    val existingBlockWestTextureId = blockTextureInfo.west.blockResourceLocation
    val existingBlockBottomTextureId = blockTextureInfo.bottom.blockResourceLocation
    val existingBlockParticleTextureId = blockTextureInfo.particle.blockResourceLocation
    val existingBlockTextureIdentifier = blockTextureInfo.default
    val existingBlockTextureName: String = blockTextureInfo.default.path
    val existingBlockTextureId = blockTextureInfo.default.blockResourceLocation
}