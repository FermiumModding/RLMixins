package rlmixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rlmixins.mixin.firstaid.vanilla.EntityAttributeMapAccessor;
import rlmixins.mixin.firstaid.vanilla.EntityPlayerMP_EndExitMixin;

/**
 * Fix by XXMA16
 * <p>
 * This needs to wrap before {@link EntityPlayerMP_EndExitMixin}
 */
@Mixin(value = EntityPlayerMP.class, priority = 500)
public abstract class EntityPlayerMP_EndAttributesMixin extends EntityPlayer {

	public EntityPlayerMP_EndAttributesMixin(World worldIn, GameProfile gameProfileIn) {
		super(worldIn, gameProfileIn);
	}

	@WrapOperation(method = "copyFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayerMP;setHealth(F)V"))
	private void rlmixins_firstAidEntityPlayerMP_copyFrom(EntityPlayerMP instance, float hp, Operation<Void> original, EntityPlayerMP that, boolean keepEverything) {
		((EntityAttributeMapAccessor) this).rlmixins$setAttributeMap(that.getAttributeMap());
		// The client gets a fresh player with default attributes and vanilla only syncs dirty ones,
		// so mark all watched attributes dirty to resend them (otherwise e.g. jump/swim speed modifiers desync)
		AbstractAttributeMap map = that.getAttributeMap();
		if(map instanceof AttributeMap) {
			((AttributeMap) map).getDirtyInstances().addAll(((AttributeMap) map).getWatchedAttributes());
		}
		original.call(instance, hp);
	}
}
