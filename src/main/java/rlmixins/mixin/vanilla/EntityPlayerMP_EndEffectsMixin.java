package rlmixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Fix by XXMA16
 */
@Mixin(value = EntityPlayerMP.class, priority = 500)
public abstract class EntityPlayerMP_EndEffectsMixin extends EntityPlayer {

	public EntityPlayerMP_EndEffectsMixin(World worldIn, GameProfile gameProfileIn) {
		super(worldIn, gameProfileIn);
	}

	/**
	 * Fixes MC-6431: potion effects not carrying over when using the exit End Portal
	 */
	@WrapOperation(
			method = "copyFrom",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayerMP;setHealth(F)V")
	)
	private void rlmixins_vanillaEntityPlayerMP_copyFrom_inject(EntityPlayerMP instance, float hp, Operation<Void> original, EntityPlayerMP that, boolean keepEverything) {
		getActivePotionMap().putAll(that.getActivePotionMap());
		original.call(instance, hp);
	}
}
