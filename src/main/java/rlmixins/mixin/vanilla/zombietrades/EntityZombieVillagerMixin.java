package rlmixins.mixin.vanilla.zombietrades;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rlmixins.wrapper.IEntityVillagerMixin;

@Mixin(EntityZombieVillager.class)
public abstract class EntityZombieVillagerMixin {
    @Inject(
            method = "finishConversion",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z")
    )
    public void rlmixins_vanillaEntityZombieVillager_finishConversion(CallbackInfo ci, @Local(name = "entityvillager") EntityVillager newVillager) {
        EntityZombieVillager deadZombieVill = (EntityZombieVillager) (Object) this;
        if (deadZombieVill.getEntityData().hasKey("villagerTags")) {
            NBTTagCompound villagerTags = (NBTTagCompound) deadZombieVill.getEntityData().getTag("villagerTags");
            ((IEntityVillagerMixin) newVillager).rlmixins$setTradesFromNBT(villagerTags);
        }
    }
}