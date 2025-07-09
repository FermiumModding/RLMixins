package rlmixins.mixin.potioncore;

import com.tmtravlr.potioncore.potion.PotionLaunch;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rlmixins.potion.PotionDelayedLaunch;

@Mixin(PotionLaunch.class)
public abstract class PotionLaunch_DelayedMixin {
	
	@Inject(
			method = "performEffect",
			at = @At("HEAD"),
			cancellable = true
	)
	private void rlmixins_potionCorePotionLaunch_performEffect(EntityLivingBase entity, int amplifier, CallbackInfo ci) {
		entity.addPotionEffect(new PotionEffect(PotionDelayedLaunch.INSTANCE, 2, amplifier, false, false));
		ci.cancel();
	}
}