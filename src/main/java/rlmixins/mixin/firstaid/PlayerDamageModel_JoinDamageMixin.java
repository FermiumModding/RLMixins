package rlmixins.mixin.firstaid;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import ichttt.mods.firstaid.api.damagesystem.AbstractDamageablePart;
import ichttt.mods.firstaid.common.damagesystem.PlayerDamageModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Fix by XXMA16
 */
@Mixin(PlayerDamageModel.class)
public abstract class PlayerDamageModel_JoinDamageMixin {

	@Inject(
			method = "runScaleLogic",
			at = @At(
					value = "INVOKE",
					target = "Lichttt/mods/firstaid/common/damagesystem/PlayerDamageModel;iterator()Ljava/util/Iterator;",
					ordinal = 0),
			remap = false
	)
	private void rlmixins_firstAidModEventHandler_runScaleLogic_init(CallbackInfo ci, @Share("partsHp") LocalRef<Map<AbstractDamageablePart, Float>> hps) {
		hps.set(new HashMap<>());
	}

	@Inject(method = "runScaleLogic",
			at = @At(value = "INVOKE",
					target = "Lichttt/mods/firstaid/api/damagesystem/AbstractDamageablePart;setMaxHealth(I)V",
					ordinal = 0),
			remap = false
	)
	private void rlmixins_firstAidModEventHandler_runScaleLogic_populate(CallbackInfo ci, @Local AbstractDamageablePart part, @Share("partsHp") LocalRef<Map<AbstractDamageablePart, Float>> hps) {
		hps.get().put(part, part.currentHealth);
	}

	@Inject(method = "runScaleLogic",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/profiler/Profiler;endSection()V",
					ordinal = 0, remap = true),
			remap = false
	)
	private void rlmixins_firstAidModEventHandler_runScaleLogic_restore(CallbackInfo ci, @Share("partsHp") LocalRef<Map<AbstractDamageablePart, Float>> hps) {
		hps.get().forEach((part, hp) -> part.currentHealth = Math.min(hp, part.getMaxHealth()));
	}
}
