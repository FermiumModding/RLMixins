package rlmixins.mixin.scalinghealth;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.silentchaos512.scalinghealth.event.ScalingHealthCommonEvents;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Fix by XXMA16
 */
@Mixin(ScalingHealthCommonEvents.class)
public abstract class CommonEvents_EndHealthMixin {
	@ModifyExpressionValue(
			method = "onPlayerRespawn",
			at = @At(
					value = "FIELD",
					target = "Lnet/silentchaos512/scalinghealth/config/Config$Player$Health;allowModify:Z",
					opcode = Opcodes.GETSTATIC
			),
			remap = false
	)
	private boolean rlmixins_scalingHealthCommonEvents_onPlayerRespawn(boolean original, PlayerEvent.PlayerRespawnEvent event) {
		return original && !event.isEndConquered();
	}
}
