package net.bloople.allblockvariants.mixins;

import kotlin.Pair;
import net.minecraft.item.ItemConvertible;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.bloople.allblockvariants.ItemForBlockColorProvider;

import static net.bloople.allblockvariants.ResourcePackBuilder.ITEM_COLOUR_PROVIDERS;

@Mixin(ItemColors.class)
    abstract class ItemColorsMixin {
    @Inject(method="create", at=@At("RETURN"))
    private static void injectItemColorProviders(BlockColors blockColors, CallbackInfoReturnable<ItemColors> cir) {
        ItemColors itemColors = cir.getReturnValue();
        for(Pair<ItemForBlockColorProvider, ItemConvertible[]> pair : ITEM_COLOUR_PROVIDERS) {
            itemColors.register((stack, tintIndex) -> pair.getFirst().getColor(stack, tintIndex, blockColors), pair.getSecond());
        }
    }
}
