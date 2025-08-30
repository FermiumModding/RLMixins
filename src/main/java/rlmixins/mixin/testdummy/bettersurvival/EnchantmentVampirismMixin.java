package rlmixins.mixin.testdummy.bettersurvival;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mujmajnkraft.bettersurvival.enchantments.EnchantmentVampirism;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import testdummy.entity.EntityDummy;

@Mixin(EnchantmentVampirism.class)
public abstract class EnchantmentVampirismMixin {
    @WrapWithCondition(
            method = "onEntityDamaged",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;heal(F)V")
    )
    private boolean rlmixins_betterSurvivalVampirism_onEntityDamaged(EntityLivingBase instance, float healAmount, @Local(argsOnly = true) Entity target){
        return !(target instanceof EntityDummy);
    }
}
