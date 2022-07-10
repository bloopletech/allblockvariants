package net.bloople.allblockvariants

import net.minecraft.block.Block

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
    val itemFuel: Int = 0
)
