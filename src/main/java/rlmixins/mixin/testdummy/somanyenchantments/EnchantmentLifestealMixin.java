package rlmixins.mixin.testdummy.somanyenchantments;

import com.shultrea.rin.enchantments.weapon.selfheal.EnchantmentLifesteal;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import testdummy.entity.EntityDummy;

@Mixin(EnchantmentLifesteal.class)
public abstract class EnchantmentLifestealMixin {
    @Inject(
            method = "onLivingHurtEvent",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private void rlmixins_soManyEnchantmentsLifesteal_onLivingHurtEvent(LivingHurtEvent event, CallbackInfo ci){
        if(event.getEntityLiving() instanceof EntityDummy) ci.cancel();
    }
}
