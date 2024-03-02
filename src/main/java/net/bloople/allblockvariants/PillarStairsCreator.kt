package net.bloople.allblockvariants

import net.bloople.allblockvariants.blocks.PillarStairsBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups


class PillarStairsCreator(metrics: Metrics, blockInfo: BlockInfo) : BlockCreator(metrics) {
    override val dbi = DerivedBlockInfo(blockInfo) { "${transformedExistingBlockName}_stairs" }

    override fun doCreateCommon() {
        with(dbi) {
            registerBlock(PillarStairsBlock(Blocks.AIR.defaultState, existingBlock.copySettings()))
            registerItem(BlockItem(block, Item.Settings()), ItemGroups.BUILDING_BLOCKS)
        }
    }

    @Environment(value=EnvType.CLIENT)
    override fun doCreateClient(builder: ResourcePackBuilder) {
        with(dbi) {
            builder.addBlockTexture("${blockName}_z_north") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture90)
            }

            builder.addBlockTexture("${blockName}_z_south") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture270)
            }

            builder.addBlockTexture("${blockName}_z_bottom") { ->
                return@addBlockTexture ClientUtil.createPackDerivedTexture(
                    builder,
                    "textures/block/$existingBlockTextureName.png",
                    ClientUtil::rotateTexture180)
            }

            val blockState = """
                {
                  "variants": {
                    "facing=east,half=bottom,shape=inner_left,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=inner_left,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=inner_left,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=inner_right,axis=x": {
                      "model": "${blockBlockId}_inner_x"
                    },
                    "facing=east,half=bottom,shape=inner_right,axis=y": {
                      "model": "${blockBlockId}_inner"
                    },
                    "facing=east,half=bottom,shape=inner_right,axis=z": {
                      "model": "${blockBlockId}_inner_z"
                    },
                    "facing=east,half=bottom,shape=outer_left,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=outer_left,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=outer_left,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,half=bottom,shape=outer_right,axis=x": {
                      "model": "${blockBlockId}_outer_x"
                    },
                    "facing=east,half=bottom,shape=outer_right,axis=y": {
                      "model": "${blockBlockId}_outer"
                    },
                    "facing=east,half=bottom,shape=outer_right,axis=z": {
                      "model": "${blockBlockId}_outer_z"
                    },
                    "facing=east,half=bottom,shape=straight,axis=x": {
                      "model": "${blockBlockId}_x"
                    },
                    "facing=east,half=bottom,shape=straight,axis=y": {
                      "model": "$blockBlockId"
                    },
                    "facing=east,half=bottom,shape=straight,axis=z": {
                      "model": "${blockBlockId}_z"
                    },
                    "facing=east,half=top,shape=inner_left,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=inner_left,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=inner_left,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=inner_right,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=inner_right,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=inner_right,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=outer_left,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=outer_left,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=outer_left,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=outer_right,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=outer_right,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=outer_right,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=east,half=top,shape=straight,axis=x": {
                      "model": "${blockBlockId}_x",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=straight,axis=y": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=east,half=top,shape=straight,axis=z": {
                      "model": "${blockBlockId}_z",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=bottom,shape=inner_left,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=inner_left,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=inner_left,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=inner_right,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=inner_right,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=inner_right,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=outer_left,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=outer_left,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=outer_left,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,half=bottom,shape=outer_right,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=outer_right,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=outer_right,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=straight,axis=x": {
                      "model": "${blockBlockId}_x",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=straight,axis=y": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=bottom,shape=straight,axis=z": {
                      "model": "${blockBlockId}_z",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,half=top,shape=inner_left,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=inner_left,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=inner_left,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=inner_right,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=top,shape=inner_right,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=top,shape=inner_right,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=top,shape=outer_left,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=outer_left,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=outer_left,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=outer_right,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=top,shape=outer_right,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=top,shape=outer_right,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "x": 180
                    },
                    "facing=north,half=top,shape=straight,axis=x": {
                      "model": "${blockBlockId}_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=straight,axis=y": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=north,half=top,shape=straight,axis=z": {
                      "model": "${blockBlockId}_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=south,half=bottom,shape=inner_left,axis=x": {
                      "model": "${blockBlockId}_inner_x"
                    },
                    "facing=south,half=bottom,shape=inner_left,axis=y": {
                      "model": "${blockBlockId}_inner"
                    },
                    "facing=south,half=bottom,shape=inner_left,axis=z": {
                      "model": "${blockBlockId}_inner_z"
                    },
                    "facing=south,half=bottom,shape=inner_right,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=inner_right,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=inner_right,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=outer_left,axis=x": {
                      "model": "${blockBlockId}_outer_x"
                    },
                    "facing=south,half=bottom,shape=outer_left,axis=y": {
                      "model": "${blockBlockId}_outer"
                    },
                    "facing=south,half=bottom,shape=outer_left,axis=z": {
                      "model": "${blockBlockId}_outer_z"
                    },
                    "facing=south,half=bottom,shape=outer_right,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=outer_right,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=outer_right,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=straight,axis=x": {
                      "model": "${blockBlockId}_x",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=straight,axis=y": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=bottom,shape=straight,axis=z": {
                      "model": "${blockBlockId}_z",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=south,half=top,shape=inner_left,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=inner_left,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=inner_left,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=inner_right,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=inner_right,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=inner_right,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=outer_left,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=outer_left,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=outer_left,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=outer_right,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=outer_right,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=outer_right,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=south,half=top,shape=straight,axis=x": {
                      "model": "${blockBlockId}_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=straight,axis=y": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=south,half=top,shape=straight,axis=z": {
                      "model": "${blockBlockId}_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_left,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_left,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_left,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_right,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=inner_right,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=inner_right,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=outer_left,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=outer_left,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=outer_left,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,half=bottom,shape=outer_right,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=outer_right,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=outer_right,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=straight,axis=x": {
                      "model": "${blockBlockId}_x",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=straight,axis=y": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=bottom,shape=straight,axis=z": {
                      "model": "${blockBlockId}_z",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=west,half=top,shape=inner_left,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=inner_left,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=inner_left,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=inner_right,axis=x": {
                      "model": "${blockBlockId}_inner_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=inner_right,axis=y": {
                      "model": "${blockBlockId}_inner",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=inner_right,axis=z": {
                      "model": "${blockBlockId}_inner_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=outer_left,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=outer_left,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=outer_left,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=outer_right,axis=x": {
                      "model": "${blockBlockId}_outer_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=outer_right,axis=y": {
                      "model": "${blockBlockId}_outer",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=outer_right,axis=z": {
                      "model": "${blockBlockId}_outer_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 270
                    },
                    "facing=west,half=top,shape=straight,axis=x": {
                      "model": "${blockBlockId}_x",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=straight,axis=y": {
                      "model": "$blockBlockId",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    },
                    "facing=west,half=top,shape=straight,axis=z": {
                      "model": "${blockBlockId}_z",
                      "uvlock": true,
                      "x": 180,
                      "y": 180
                    }
                  }
                }
            """.trimIndent()
            builder.addBlockState(blockName, blockState)

            val blockModel = """
                {   "parent": "block/block",
                    "display": {
                        "gui": {
                            "rotation": [ 30, 135, 0 ],
                            "translation": [ 0, 0, 0],
                            "scale":[ 0.625, 0.625, 0.625 ]
                        },
                        "head": {
                            "rotation": [ 0, -90, 0 ],
                            "translation": [ 0, 0, 0 ],
                            "scale": [ 1, 1, 1 ]
                        },
                        "thirdperson_lefthand": {
                            "rotation": [ 75, -135, 0 ],
                            "translation": [ 0, 2.5, 0],
                            "scale": [ 0.375, 0.375, 0.375 ]
                        }
                    },
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
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel(blockName, blockModel)

            val xBlockModel = """
                {   "parent": "block/block",
                    "display": {
                        "gui": {
                            "rotation": [ 30, 135, 0 ],
                            "translation": [ 0, 0, 0],
                            "scale":[ 0.625, 0.625, 0.625 ]
                        },
                        "head": {
                            "rotation": [ 0, -90, 0 ],
                            "translation": [ 0, 0, 0 ],
                            "scale": [ 1, 1, 1 ]
                        },
                        "thirdperson_lefthand": {
                            "rotation": [ 75, -135, 0 ],
                            "translation": [ 0, 2.5, 0],
                            "scale": [ 0.375, 0.375, 0.375 ]
                        }
                    },
                    "textures": {
                        "top": "${blockBlockId}_z_north",
                        "north": "${blockBlockId}_z_south",
                        "east": "$existingBlockEndTextureId",
                        "south": "${blockBlockId}_z_north",
                        "west": "$existingBlockEndTextureId",
                        "bottom": "${blockBlockId}_z_north",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_x", xBlockModel)

            val zBlockModel = """
                {   "parent": "block/block",
                    "display": {
                        "gui": {
                            "rotation": [ 30, 135, 0 ],
                            "translation": [ 0, 0, 0],
                            "scale":[ 0.625, 0.625, 0.625 ]
                        },
                        "head": {
                            "rotation": [ 0, -90, 0 ],
                            "translation": [ 0, 0, 0 ],
                            "scale": [ 1, 1, 1 ]
                        },
                        "thirdperson_lefthand": {
                            "rotation": [ 75, -135, 0 ],
                            "translation": [ 0, 2.5, 0],
                            "scale": [ 0.375, 0.375, 0.375 ]
                        }
                    },
                    "textures": {
                        "top": "$existingBlockSideTextureId",
                        "north": "${blockBlockId}_z_north",
                        "east": "$existingBlockEndTextureId",
                        "south": "${blockBlockId}_z_south",
                        "west": "$existingBlockEndTextureId",
                        "bottom": "${blockBlockId}_z_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_z", zBlockModel)

            val innerBlockModel = """
                {
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
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 0, 8, 8 ],
                            "to": [ 8, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 0, 8,  8, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 8, 0, 16,  8 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0,  8,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#west", "cullface": "west" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_inner", innerBlockModel)

            val xInnerBlockModel = """
                {
                    "textures": {
                        "top": "${blockBlockId}_z_north",
                        "north": "${blockBlockId}_z_south",
                        "east": "$existingBlockEndTextureId",
                        "south": "${blockBlockId}_z_north",
                        "west": "$existingBlockEndTextureId",
                        "bottom": "${blockBlockId}_z_north",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 0, 8, 8 ],
                            "to": [ 8, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 0, 8,  8, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 8, 0, 16,  8 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0,  8,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#west", "cullface": "west" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_inner_x", xInnerBlockModel)

            val zInnerBlockModel = """
                {
                    "textures": {
                        "top": "$existingBlockSideTextureId",
                        "north": "${blockBlockId}_z_north",
                        "east": "$existingBlockEndTextureId",
                        "south": "${blockBlockId}_z_south",
                        "west": "$existingBlockEndTextureId",
                        "bottom": "${blockBlockId}_z_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 0 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 0, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0, 16,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 0, 8, 8 ],
                            "to": [ 8, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 0, 8,  8, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 8, 0, 16,  8 ], "texture": "#north" },
                                "south": { "uv": [ 0, 0,  8,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#west", "cullface": "west" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_inner_z", zInnerBlockModel)

            val outerBlockModel = """
                {
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
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 8, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0,  8,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_outer", outerBlockModel)

            val xOuterBlockModel = """
                {
                    "textures": {
                        "top": "${blockBlockId}_z_north",
                        "north": "${blockBlockId}_z_south",
                        "east": "$existingBlockEndTextureId",
                        "south": "${blockBlockId}_z_north",
                        "west": "$existingBlockEndTextureId",
                        "bottom": "${blockBlockId}_z_north",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 8, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0,  8,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_outer_x", xOuterBlockModel)

            val zOuterBlockModel = """
                {
                    "textures": {
                        "top": "$existingBlockSideTextureId",
                        "north": "${blockBlockId}_z_north",
                        "east": "$existingBlockEndTextureId",
                        "south": "${blockBlockId}_z_south",
                        "west": "$existingBlockEndTextureId",
                        "bottom": "${blockBlockId}_z_bottom",
                        "particle": "$existingBlockParticleTextureId"
                    },
                    "elements": [
                        {   "from": [ 0, 0, 0 ],
                            "to": [ 16, 8, 16 ],
                            "faces": {
                                "down":  { "uv": [ 0, 0, 16, 16 ], "texture": "#bottom", "cullface": "down" },
                                "up":    { "uv": [ 0, 0, 16, 16 ], "texture": "#top" },
                                "north": { "uv": [ 0, 8, 16, 16 ], "texture": "#north", "cullface": "north" },
                                "south": { "uv": [ 0, 8, 16, 16 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 0, 8, 16, 16 ], "texture": "#west", "cullface": "west" },
                                "east":  { "uv": [ 0, 8, 16, 16 ], "texture": "#east", "cullface": "east" }
                            }
                        },
                        {   "from": [ 8, 8, 8 ],
                            "to": [ 16, 16, 16 ],
                            "faces": {
                                "up":    { "uv": [ 8, 8, 16, 16 ], "texture": "#top", "cullface": "up" },
                                "north": { "uv": [ 0, 0,  8,  8 ], "texture": "#north" },
                                "south": { "uv": [ 8, 0, 16,  8 ], "texture": "#south", "cullface": "south" },
                                "west":  { "uv": [ 8, 0, 16,  8 ], "texture": "#west" },
                                "east":  { "uv": [ 0, 0,  8,  8 ], "texture": "#east", "cullface": "east" }
                            }
                        }
                    ]
                }
            """.trimIndent()
            builder.addBlockModel("${blockName}_outer_z", zOuterBlockModel)

            val itemModel = """
                {
                  "parent": "$blockBlockId"
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
                      "conditions": [
                        {
                          "condition": "minecraft:survives_explosion"
                        }
                      ],
                      "entries": [
                        {
                          "type": "minecraft:item",
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
                      "item": "$existingIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.identifier}"
                    }
                  },
                  "pattern": [
                    "# !",
                    "## ",
                    "###"
                  ],
                  "result": {
                    "count": 4,
                    "item": "$identifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)

            val stonecuttingRecipe = """
                {
                  "type": "minecraft:stonecutting",
                  "count": 1,
                  "ingredient": {
                    "item": "$existingIdentifier"
                  },
                  "result": "$identifier"
                }
            """.trimIndent()
            builder.addRecipe("${blockName}_from_stonecutting", stonecuttingRecipe)

            builder.addBlockTag("stairs", identifier.toString())
            builder.addItemTag("stairs", identifier.toString())
        }
    }

    override fun doVanillaCreateServer(builder: ResourcePackBuilder) {
        with(dbi) {
            val recipe = """
                {
                  "type": "minecraft:crafting_shaped",
                  "key": {
                    "#": {
                      "item": "$existingIdentifier"
                    },
                    "!": {
                      "item": "${ModStickCreator.identifier}"
                    }
                  },
                  "pattern": [
                    "# !",
                    "## ",
                    "###"
                  ],
                  "result": {
                    "count": 4,
                    "item": "$vanillaIdentifier"
                  }
                }
            """.trimIndent()
            builder.addRecipe(blockName, recipe)
        }
    }
}