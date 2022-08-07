package net.bloople.allblockvariants

import net.minecraft.block.Block
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

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
    val identifier: Identifier = Registry.BLOCK.getId(block),
    val modelIdentifier: Identifier = identifier,
    val textureInfo: BlockTextureInfo = BlockTextureInfo(modelIdentifier)
)

data class BlockTextureInfo(
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
    constructor(full: Identifier) : this(full, full, full)
    constructor(top_bottom: String, side: String) : this(Identifier(top_bottom), Identifier(side))
    constructor(top_bottom: Identifier, side: Identifier) : this(top_bottom, side, top_bottom)
    constructor(top: String, side: String, bottom: String)
        : this(Identifier(top), Identifier(side), Identifier(bottom))
    constructor(top: Identifier, side: Identifier, bottom: Identifier)
        : this(top, side, side, side, side, bottom, side, side)
    constructor(top: String, north: String, east: String, south: String, west: String, bottom: String, particle: String)
        : this(
        Identifier(top),
        Identifier(north),
        Identifier(east),
        Identifier(south),
        Identifier(west),
        Identifier(bottom),
        Identifier(particle)
    )
    constructor(
        top: Identifier,
        north: Identifier,
        east: Identifier,
        south: Identifier,
        west: Identifier,
        bottom: Identifier,
        particle: Identifier
    ) : this(top, north, east, south, west, bottom, particle, particle)
}
