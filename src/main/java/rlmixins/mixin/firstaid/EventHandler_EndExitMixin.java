package rlmixins.mixin.firstaid;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import ichttt.mods.firstaid.api.damagesystem.AbstractDamageablePart;
import ichttt.mods.firstaid.api.damagesystem.AbstractPlayerDamageModel;
import ichttt.mods.firstaid.common.EventHandler;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

/**
 * Fix by XXMA16
 */
@Mixin(EventHandler.class)
public abstract class EventHandler_EndExitMixin {
	@WrapOperation(
			method = "onPlayerRespawn",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/gameevent/PlayerEvent$PlayerRespawnEvent;isEndConquered()Z"),
			remap = false
	)
	private static boolean rlmixins_firstAidModEventHandler_onPlayerRespawn(PlayerEvent.PlayerRespawnEvent instance, Operation<Boolean> original) {
		return false;
	}

	@WrapWithCondition(
			method = "onPlayerRespawn",
			at = @At(value = "INVOKE", target = "Lichttt/mods/firstaid/api/damagesystem/AbstractPlayerDamageModel;forEach(Ljava/util/function/Consumer;)V"),
			remap = false
	)
	private static boolean rlmixins_firstAidModEventHandler_onPlayerRespawn(AbstractPlayerDamageModel instance, Consumer<AbstractDamageablePart> consumer, PlayerEvent.PlayerRespawnEvent event) {
		return !event.isEndConquered();
	}
}
