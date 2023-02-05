package com.lahusa.technoblade_pigs.mixin;

import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PigEntityRenderer.class)
public class PigEntityRendererMixin {
    private static final Identifier TECHNOBLADE_PIG_TEXTURE = new Identifier("technoblade_pigs","textures/entity/pig/technoblade_pig.png");

    @Inject(method = "getTexture(Lnet/minecraft/entity/passive/PigEntity;)Lnet/minecraft/util/Identifier;", at = @At(value = "HEAD"), cancellable = true)
    private void injectTechnoTexture(PigEntity pigEntity, CallbackInfoReturnable<Identifier> cir) {
        if(pigEntity != null && pigEntity.hasCustomName()) {
            String customName = Objects.requireNonNull(pigEntity.getCustomName()).getString().toLowerCase();

            if(customName.equals("techno") || customName.equals("technoblade")) {
                cir.setReturnValue(TECHNOBLADE_PIG_TEXTURE);
                cir.cancel();
            }
        }
    }
}
