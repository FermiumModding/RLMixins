package rlmixins.mixin.testdummy.lycanitesmobs;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.lycanitesmobs.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import testdummy.entity.EntityDummy;

@Mixin(PotionEffects.class)
public abstract class PotionEffectLeechMixin {
    @WrapWithCondition(
            method = "onLivingHurt",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;heal(F)V")
    )
    private boolean rlmixins_lycanitesMobsPotionEffects_onLivingHurt(EntityLivingBase instance, float healAmount, @Local(argsOnly = true) LivingHurtEvent event){
        return !(event.getEntityLiving() instanceof EntityDummy);
    }
}
