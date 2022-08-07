package net.bloople.allblockvariants.mixins

import net.bloople.allblockvariants.ResourcePackBuilder.Companion.ITEM_COLOUR_PROVIDERS
import net.minecraft.client.color.block.BlockColors
import net.minecraft.client.color.item.ItemColors
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(ItemColors::class)
abstract class ItemColorsMixin {
    private companion object {
        @Inject(method = ["create"], at = [At("RETURN")], cancellable = true)
        @JvmStatic
        private fun injectItemColorProviders(blockColors: BlockColors, cir: CallbackInfoReturnable<ItemColors>) {
            val itemColors = cir.returnValue
            for((provider, items) in ITEM_COLOUR_PROVIDERS) {
                itemColors.register({ stack, tintIndex -> provider.getColor(stack, tintIndex, blockColors) }, *items)
            }
        }
    }
}