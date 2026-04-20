package rlmixins.mixin.firstaid.potioncore;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.tmtravlr.potioncore.PotionCoreEventHandler;
import ichttt.mods.firstaid.common.DataManagerWrapper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Fix by XXMA16
 */
@Mixin(PotionCoreEventHandler.class)
public abstract class PotionCoreEventHandler_EndExitMixin {

	@WrapOperation(
			method = "updateEntityModifiers",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/EntityLivingBase;setHealth(F)V",
					ordinal = 0,
					remap = true
			),
			remap = false
	)
	private static void rlmixins_firstAidModEventHandler_updateEntityModifiers(EntityLivingBase instance, float health, Operation<Void> original) {
		if (!(instance instanceof EntityPlayerMP)) {
			return;
		}
		DataManagerWrapper wrapper = (DataManagerWrapper) instance.getDataManager();
		wrapper.toggleTracking(false);
		original.call(instance, health);
		wrapper.toggleTracking(true);
	}
}
