package net.bloople.allblockvariants.mixins

import net.bloople.allblockvariants.ResourcePackBuilder.Companion.BLOCK_COLOUR_PROVIDERS
import net.minecraft.client.color.block.BlockColors
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(BlockColors::class)
abstract class BlockColorsMixin {
    private companion object {
        @Inject(method = ["create"], at = [At("RETURN")], cancellable = true)
        @JvmStatic
        private fun injectBlockColorProviders(cir: CallbackInfoReturnable<BlockColors>) {
            val blockColors = cir.returnValue
            for((provider, blocks) in BLOCK_COLOUR_PROVIDERS) {
                blockColors.registerColorProvider(provider, *blocks)
            }
        }
    }
}