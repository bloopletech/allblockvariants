package net.bloople.allblockvariants

import net.minecraft.block.Block
import net.minecraft.block.BlockSetType
import net.minecraft.block.WoodType
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

enum class MiningTool {
    Axe,
    Hoe,
    Pickaxe,
    Shovel
}

enum class MiningToolLevel {
    Stone,
    Iron,
    Diamond
}

data class BlockInfo(
    val block: Block,
    val preferredTool: MiningTool = MiningTool.Pickaxe,
    val needsToolLevel: MiningToolLevel? = null,
    // LootEntryTypeRegistry
    val flammabilityBurnChance: Int = 0,
    val flammabilitySpreadChance: Int = 0,
    // FlattenableBlockRegistry
    // OxadizableBlockRegistry
    // StrippableBlockRegistry
    // TillableBlockRegistry
    // VillagerInteractionRegistries
    val itemCompostability: Float = 0.0F,
    val itemFuel: Int = 0,
    val blockSetType: BlockSetType = BlockSetType.IRON,
    val woodType: WoodType = WoodType.OAK,
    val identifier: Identifier = Registries.BLOCK.getId(block),
    val modelIdentifier: Identifier = identifier,
    val horizontalModelIdentifier: Identifier = modelIdentifier,
    val textureInfo: BlockTextureInfo = BlockTextureInfo(modelIdentifier),
    val transformDerived: Boolean = true
)

data class BlockTextureInfo(
    val end: Identifier,
    val side: Identifier,
    val top: Identifier,
    val north: Identifier,
    val east: Identifier,
    val south: Identifier,
    val west: Identifier,
    val bottom: Identifier,
    val particle: Identifier,
    val default: Identifier
) {
    constructor(full: String) : this(Identifier(full))
    constructor(full: Identifier) : this(full, full)
    constructor(end: String, side: String) : this(Identifier(end), Identifier(side))
    constructor(end: Identifier, side: Identifier) : this(end, side, end, side, side, side, side, end, side, side)
    constructor(top: String, side: String, bottom: String)
        : this(Identifier(top), Identifier(side), Identifier(bottom))
    constructor(top: Identifier, side: Identifier, bottom: Identifier)
        : this(top, side, top, side, side, side, side, bottom, side, side)
    constructor(
        end: String,
        side: String,
        top: String,
        north: String,
        east: String,
        south: String,
        west: String,
        bottom: String,
        particle: String,
        default: String
    ) : this(
        Identifier(end),
        Identifier(side),
        Identifier(top),
        Identifier(north),
        Identifier(east),
        Identifier(south),
        Identifier(west),
        Identifier(bottom),
        Identifier(particle),
        Identifier(default)
    )
}
