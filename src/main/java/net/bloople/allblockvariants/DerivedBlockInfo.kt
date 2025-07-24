package net.bloople.allblockvariants

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

open class DerivedBlockInfo(val blockInfo: BlockInfo, blockNameBuilder: DerivedBlockInfo.() -> String) {
    val existingBlock = blockInfo.block

    val existingIdentifier = blockInfo.identifier
    val existingBlockName: String = existingIdentifier.path
    val existingBlockBlockId = blockInfo.modelIdentifier.blockResourceLocation
    val existingBlockHorizontalBlockId = blockInfo.horizontalModelIdentifier.blockResourceLocation

    val transformedExistingBlockName = if(blockInfo.transformDerived) {
        existingBlockName.removeSuffix("_planks").replace("bricks", "brick")
    }
    else {
        existingBlockName
    }

    val blockName by lazy { blockNameBuilder(this) }
    val identifier = modId(blockName)
    val vanillaIdentifier = id(blockName)
    val vanillaBlockExists by lazy { Registration.blockExists(vanillaIdentifier) }

    val registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier)!!
    val itemRegistryKey = RegistryKey.of(RegistryKeys.ITEM, identifier)!!

    val blockBlockId = identifier.blockResourceLocation
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

    val blockSettings = existingBlock.copySettings().registryKey(registryKey)!!
    val itemSettings = Item.Settings().useBlockPrefixedTranslationKey().registryKey(itemRegistryKey)!!
}