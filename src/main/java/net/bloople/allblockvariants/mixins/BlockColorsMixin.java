package net.bloople.allblockvariants.mixins;

import kotlin.Pair;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.block.BlockColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.bloople.allblockvariants.ResourcePackBuilder.BLOCK_COLOUR_PROVIDERS;

@Mixin(BlockColors.class)
abstract class BlockColorsMixin {
    @Inject(method="create", at=@At("RETURN"))
    private static void injectBlockColorProviders(CallbackInfoReturnable<BlockColors> cir) {
        BlockColors blockColors = cir.getReturnValue();
        for(Pair<BlockColorProvider, Block[]> pair : BLOCK_COLOUR_PROVIDERS) {
            blockColors.registerColorProvider(pair.getFirst(), pair.getSecond());
        }
    }
}
