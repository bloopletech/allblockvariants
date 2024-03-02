package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.RedstoneLampThinVerticalSlabBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup


class RedstoneLampThinVerticalSlabCreator(metrics: Metrics, blockInfo: BlockInfo) : BlockCreator(metrics) {
    override val dbi = AdvancedDerivedBlockInfo(blockInfo) {
        Pair(
            "${transformedExistingBlockName}_thin_vertical_slab",
            "${transformedExistingBlockName}_vertical_slab"
        )
    }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(RedstoneLampThinVerticalSlabBlock(blockSettings))
            registerItem(BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS)))
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            val blockState = """
                {
                   "variants": {
                     "facing=east,shape=straight,type=left,lit=false": {
                       "model": "$blockBlockId"
                     },
                     "facing=east,shape=straight,type=right,lit=false": {
                       "model": "${blockBlockId}_right"
                     },
                     "facing=east,shape=north_west,lit=false": {
                       "model": "${blockBlockId}_north_west"
                     },
                     "facing=east,shape=north_east,lit=false": {
                       "model": "${blockBlockId}_north_east"
                     },
                     "facing=east,shape=south_east,lit=false": {
                       "model": "${blockBlockId}_south_east"
                     },
                     "facing=east,shape=south_west,lit=false": {
                       "model": "${blockBlockId}_south_west"
                     },
                     "facing=south,shape=straight,type=left,lit=false": {
                       "model": "$blockBlockId",
                       "y": 90
                     },
                     "facing=south,shape=straight,type=right,lit=false": {
                       "model": "${blockBlockId}_right",
                       "y": 90
                     },
                     "facing=south,shape=north_west,lit=false": {
                       "model": "${blockBlockId}_north_west",
                       "y": 90
                     },
                     "facing=south,shape=north_east,lit=false": {
                       "model": "${blockBlockId}_north_east",
                       "y": 90
                     },
                     "facing=south,shape=south_east,lit=false": {
                       "model": "${blockBlockId}_south_east",
                       "y": 90
                     },
                     "facing=south,shape=south_west,lit=false": {
                       "model": "${blockBlockId}_south_west",
                       "y": 90
                     },
                     "facing=west,shape=straight,type=left,lit=false": {
                       "model": "$blockBlockId",
                       "y": 180
                     },
                     "facing=west,shape=straight,type=right,lit=false": {
                       "model": "${blockBlockId}_right",
                       "y": 180
                     },
                     "facing=west,shape=north_west,lit=false": {
                       "model": "${blockBlockId}_north_west",
                       "y": 180
                     },
                     "facing=west,shape=north_east,lit=false": {
                       "model": "${blockBlockId}_north_east",
                       "y": 180
                     },
                     "facing=west,shape=south_east,lit=false": {
                       "model": "${blockBlockId}_south_east",
                       "y": 180
                     },
                     "facing=west,shape=south_west,lit=false": {
                       "model": "${blockBlockId}_south_west",
                       "y": 180
                     },
                     "facing=north,shape=straight,type=left,lit=false": {
                       "model": "$blockBlockId",
                       "y": 270
                     },
                     "facing=north,shape=straight,type=right,lit=false": {
                       "model": "${blockBlockId}_right",
                       "y": 270
                     },
                     "facing=north,shape=north_west,lit=false": {
                       "model": "${blockBlockId}_north_west",
                       "y": 270
                     },
                     "facing=north,shape=north_east,lit=false": {
                       "model": "${blockBlockId}_north_east",
                       "y": 270
                     },
                     "facing=north,shape=south_east,lit=false": {
                       "model": "${blockBlockId}_south_east",
                       "y": 270
                     },
                     "facing=north,shape=south_west,lit=false": {
                       "model": "${blockBlockId}_south_west",
                       "y": 270
                     },
                     "facing=east,shape=straight,type=left,lit=true": {
                       "model": "${blockBlockId}_on"
                     },
                     "facing=east,shape=straight,type=right,lit=true": {
                       "model": "${blockBlockId}_right_on"
                     },
                     "facing=east,shape=north_west,lit=true": {
                       "model": "${blockBlockId}_north_west_on"
                     },
                     "facing=east,shape=north_east,lit=true": {
                       "model": "${blockBlockId}_north_east_on"
                     },
                     "facing=east,shape=south_east,lit=true": {
                       "model": "${blockBlockId}_south_east_on"
                     },
                     "facing=east,shape=south_west,lit=true": {
                       "model": "${blockBlockId}_south_west_on"
                     },
                     "facing=south,shape=straight,type=left,lit=true": {
                       "model": "${blockBlockId}_on",
                       "y": 90
                     },
                     "facing=south,shape=straight,type=right,lit=true": {
                       "model": "${blockBlockId}_right_on",
                       "y": 90
                     },
                     "facing=south,shape=north_west,lit=true": {
                       "model": "${blockBlockId}_north_west_on",
                       "y": 90
                     },
                     "facing=south,shape=north_east,lit=true": {
                       "model": "${blockBlockId}_north_east_on",
                       "y": 90
                     },
                     "facing=south,shape=south_east,lit=true": {
                       "model": "${blockBlockId}_south_east_on",
                       "y": 90
                     },
                     "facing=south,shape=south_west,lit=true": {
                       "model": "${blockBlockId}_south_west_on",
                       "y": 90
                     },
                     "facing=west,shape=straight,type=left,lit=true": {
                       "model": "${blockBlockId}_on",
                       "y": 180
                     },
                     "facing=west,shape=straight,type=right,lit=true": {
                       "model": "${blockBlockId}_right_on",
                       "y": 180
                     },
                     "facing=west,shape=north_west,lit=true": {
                       "model": "${blockBlockId}_north_west_on",
                       "y": 180
                     },
                     "facing=west,shape=north_east,lit=true": {
                       "model": "${blockBlockId}_north_east_on",
                       "y": 180
                     },
                     "facing=west,shape=south_east,lit=true": {
                       "model": "${blockBlockId}_south_east_on",
                       "y": 180
                     },
                     "facing=west,shape=south_west,lit=true": {
                       "model": "${blockBlockId}_south_west_on",
                       "y": 180
                     },
                     "facing=north,shape=straight,type=left,lit=true": {
                       "model": "${blockBlockId}_on",
                       "y": 270
                     },
                     "facing=north,shape=straight,type=right,lit=true": {
                       "model": "${blockBlockId}_right_on",
                       "y": 270
                     },
                     "facing=north,shape=north_west,lit=true": {
                       "model": "${blockBlockId}_north_west_on",
                       "y": 270
                     },
                     "facing=north,shape=north_east,lit=true": {
                       "model": "${blockBlockId}_north_east_on",
                       "y": 270
                     },
                     "facing=north,shape=south_east,lit=true": {
                       "model": "${blockBlockId}_south_east_on",
                       "y": 270
                     },
                     "facing=north,shape=south_west,lit=true": {
                       "model": "${blockBlockId}_south_west_on",
                       "y": 270
                     }
                   }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 16, 4, 0, 0 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val rightBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 16, 16, 0, 12 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_right", rightBlockModel)

            val northWestBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 0, 12, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 4 ],
                            "to": [ 4, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 4, 12 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 4, 4, 16 ], "texture": "#top" },
                                "south": { "uv": [ 0, 0, 4, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 4, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 12, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_west", northWestBlockModel)

            val northEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 0, 12, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 12, 0, 4 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 12, 0, 16, 12 ], "texture": "#bottom" },
                                "up":    { "uv": [ 12, 4, 16, 16 ], "texture": "#top" },
                                "south": { "uv": [ 12, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 4, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 12, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_east", northEastBlockModel)

            val southEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 12, 0, 0 ],
                            "to": [ 16, 16, 12 ],
                            "faces": {
                                "down":  { "uv": [ 12, 4, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 12, 0, 16, 12 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 4, 16 ], "texture": "#north" },
                                "west":  { "uv": [ 0, 0, 12, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 4, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 4 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_south_east", southEastBlockModel)

            val southWestBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "$existingBlockTopTextureId",
                        "north": "$existingBlockNorthTextureId",
                        "east": "$existingBlockEastTextureId",
                        "south": "$existingBlockSouthTextureId",
                        "west": "$existingBlockWestTextureId",
                        "bottom": "$existingBlockBottomTextureId",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 4, 16, 12 ],
                            "faces": {
                                "down":  { "uv": [ 0, 4, 4, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 4, 12 ], "texture": "#top" },
                                "north": { "uv": [ 12, 0, 16, 16 ], "texture": "#north" },
                                "west":  { "uv": [ 0, 0, 12, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 4, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 4 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_south_west", southWestBlockModel)

            val onBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${existingBlockTopTextureId}_on",
                        "north": "${existingBlockNorthTextureId}_on",
                        "east": "${existingBlockEastTextureId}_on",
                        "south": "${existingBlockSouthTextureId}_on",
                        "west": "${existingBlockWestTextureId}_on",
                        "bottom": "${existingBlockBottomTextureId}_on",
                        "particle": "${existingBlockParticleTextureId}_on"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 16, 4, 0, 0 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_on", onBlockModel)

            val onRightBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${existingBlockTopTextureId}_on",
                        "north": "${existingBlockNorthTextureId}_on",
                        "east": "${existingBlockEastTextureId}_on",
                        "south": "${existingBlockSouthTextureId}_on",
                        "west": "${existingBlockWestTextureId}_on",
                        "bottom": "${existingBlockBottomTextureId}_on",
                        "particle": "${existingBlockParticleTextureId}_on"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 16, 16, 0, 12 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_right_on", onRightBlockModel)

            val onNorthWestBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${existingBlockTopTextureId}_on",
                        "north": "${existingBlockNorthTextureId}_on",
                        "east": "${existingBlockEastTextureId}_on",
                        "south": "${existingBlockSouthTextureId}_on",
                        "west": "${existingBlockWestTextureId}_on",
                        "bottom": "${existingBlockBottomTextureId}_on",
                        "particle": "${existingBlockParticleTextureId}_on"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 0, 12, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 4 ],
                            "to": [ 4, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 4, 12 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 4, 4, 16 ], "texture": "#top" },
                                "south": { "uv": [ 0, 0, 4, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 4, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 12, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_west_on", onNorthWestBlockModel)

            val onNorthEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${existingBlockTopTextureId}_on",
                        "north": "${existingBlockNorthTextureId}_on",
                        "east": "${existingBlockEastTextureId}_on",
                        "south": "${existingBlockSouthTextureId}_on",
                        "west": "${existingBlockWestTextureId}_on",
                        "bottom": "${existingBlockBottomTextureId}_on",
                        "particle": "${existingBlockParticleTextureId}_on"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 16, 4 ],
                            "faces": {
                                "down":  { "uv": [ 0, 12, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 16, 4 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 0, 0, 4, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 12, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 12, 0, 4 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 12, 0, 16, 12 ], "texture": "#bottom" },
                                "up":    { "uv": [ 12, 4, 16, 16 ], "texture": "#top" },
                                "south": { "uv": [ 12, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 4, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 12, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_north_east_on", onNorthEastBlockModel)

            val onSouthEastBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${existingBlockTopTextureId}_on",
                        "north": "${existingBlockNorthTextureId}_on",
                        "east": "${existingBlockEastTextureId}_on",
                        "south": "${existingBlockSouthTextureId}_on",
                        "west": "${existingBlockWestTextureId}_on",
                        "bottom": "${existingBlockBottomTextureId}_on",
                        "particle": "${existingBlockParticleTextureId}_on"
                    },
                    "elements": [
                        {   "from": [ 12, 0, 0 ],
                            "to": [ 16, 16, 12 ],
                            "faces": {
                                "down":  { "uv": [ 12, 4, 16, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 12, 0, 16, 12 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 4, 16 ], "texture": "#north" },
                                "west":  { "uv": [ 0, 0, 12, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 4, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 4 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_south_east_on", onSouthEastBlockModel)

            val onSouthWestBlockModel = """
                {   "parent": "block/block",
                    "textures": {
                        "top": "${existingBlockTopTextureId}_on",
                        "north": "${existingBlockNorthTextureId}_on",
                        "east": "${existingBlockEastTextureId}_on",
                        "south": "${existingBlockSouthTextureId}_on",
                        "west": "${existingBlockWestTextureId}_on",
                        "bottom": "${existingBlockBottomTextureId}_on",
                        "particle": "${existingBlockParticleTextureId}_on"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 4, 16, 12 ],
                            "faces": {
                                "down":  { "uv": [ 0, 4, 4, 16 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 0, 4, 12 ], "texture": "#top" },
                                "north": { "uv": [ 12, 0, 16, 16 ], "texture": "#north" },
                                "west":  { "uv": [ 0, 0, 12, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 4, 0, 16, 16 ], "texture": "#east" }
                            }
                        },
                        {   "from": [ 0, 0, 12 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 4 ], "texture": "#bottom" },
                                "up":    { "uv": [ 0, 12, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#south" },
                                "west":  { "uv": [ 12, 0, 16, 16 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 4, 16 ], "texture": "#east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_south_west_on", onSouthWestBlockModel)

            val itemModel = """
                {
                  "parent": "$blockBlockId",
                  "display": {
                    "gui": {
                      "rotation": [30, 45, 0],
                      "translation": [0, 0, 0],
                      "scale": [0.625, 0.625, 0.625]
                    }
                  }
                }
            """.trimIndent()
            builder.addItemModel(blockName, itemModel)

            builder.addTranslation("block.$MOD_ID.$blockName", Util.toTitleCase(blockName))
        }
    }

    override fun doCreateServer(builder: ResourcePackBuilder) {
        registerBlockCommon(builder)

        with(dbi) {
            val lootTable = """
                {
                  "type": "minecraft:block",
                  "pools": [
                    {
                      "bonus_rolls": 0.0,
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "functions": [
                            {
                              "add": false,
                              "conditions": [
                                {
                                  "block": "$identifier",
                                  "condition": "minecraft:block_state_property",
                                  "properties": {
                                    "type": "double"
                                  }
                                }
                              ],
                              "count": 2.0,
                              "function": "minecraft:set_count"
                            },
                            {
                              "function": "minecraft:explosion_decay"
                            }
                          ],
                          "name": "$identifier"
                        }
                      ],
                      "rolls": 1.0
                    }
                  ]
                }
            """.trimIndent()
            builder.addBlockLootTable(blockName, lootTable)

            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "key": {
                    "#": {
                      "item": "$parentIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.identifier}"
                    }
                  },
                  "pattern": [
                    "# ",
                    "# ",
                    "#!"
                  ],
                  "result": {
                    "count": 6,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            val stonecuttingRecipe = """
                {
                  "type": "minecraft:stonecutting",
                  "count": 4,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_existing_stonecutting", stonecuttingRecipe)

            val parentStonecuttingRecipe = """
                {
                  "type": "minecraft:stonecutting",
                  "count": 4,
                  "ingredient": {
                    "item": "$parentIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_parent_stonecutting", parentStonecuttingRecipe)
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "key": {
                    "#": {
                      "item": "$parentIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.identifier}"
                    }
                  },
                  "pattern": [
                    "# ",
                    "# ",
                    "#!"
                  ],
                  "result": {
                    "count": 6,
                    "item": "$vanillaIdentifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)
        }
    }
}