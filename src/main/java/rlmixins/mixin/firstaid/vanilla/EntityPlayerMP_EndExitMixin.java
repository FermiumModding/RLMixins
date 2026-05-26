package rlmixins.mixin.firstaid.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rlmixins.wrapper.FirstAidWrapper;

/**
 * Fix by XXMA16
 */
@Mixin(value = EntityPlayerMP.class, priority = 6000)
public abstract class EntityPlayerMP_EndExitMixin extends EntityPlayer {

	public EntityPlayerMP_EndExitMixin(World worldIn, GameProfile gameProfileIn) {
		super(worldIn, gameProfileIn);
	}

	@WrapOperation(method = "copyFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayerMP;setHealth(F)V"))
	private void rlmixins_firstAidEntityPlayerMP_copyFrom(EntityPlayerMP instance, float hp, Operation<Void> original, EntityPlayerMP that, boolean keepEverything) {
		EntityDataManager dataManager = instance.getDataManager();
		FirstAidWrapper.toggleTracking(dataManager, false);
		original.call(instance, hp);
		FirstAidWrapper.copyPlayerDamageModel(that, instance);
		FirstAidWrapper.toggleTracking(dataManager, true);
	}
}
