package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

interface Creator {
    fun runCommon()
    @Environment(value= EnvType.CLIENT)
    fun runClient(builder: ResourcePackBuilder)
    fun runServer(builder: ResourcePackBuilder)
    fun getBlockInfo(): BlockInfo?
}