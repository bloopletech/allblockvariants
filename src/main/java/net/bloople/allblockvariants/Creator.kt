package net.bloople.allblockvariants

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

interface Creator {
    fun createCommon()
    @Environment(value= EnvType.CLIENT)
    fun createClient(builder: ResourcePackBuilder)
    fun createServer(builder: ResourcePackBuilder)
    fun getBlockInfo(): BlockInfo?
}