package rlmixins.mixin.scalinghealth;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.silentchaos512.scalinghealth.event.BlightHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rlmixins.handlers.ConfigHandler;

import java.util.Map;

/**
 * Patch by Nischhelm
 */
@Mixin(BlightHandler.class)
public abstract class BlightHandlerMixin {
    
    @Inject(
            method = "applyBlightPotionEffects",
            at = @At("TAIL"),
            remap = false
    )
    private static void rlmixins_scalingHealthBllightHandler_applyBlightPotionEffects(EntityLivingBase entityLiving, CallbackInfo ci, @Local int duration) {
        for(Map.Entry<Potion, Integer> entry : ConfigHandler.SCALINGHEALTH_CONFIG.getBlightEffects().entrySet()) {
            entityLiving.addPotionEffect(new PotionEffect(entry.getKey(), duration, entry.getValue(), true, false));
        }
    }
}