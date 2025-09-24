package rlmixins.mixin.somanyenchantments;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import c4.champions.common.capability.CapabilityChampionship;
import c4.champions.common.capability.IChampionship;
import c4.champions.common.util.ChampionHelper;
import com.llamalad7.mixinextras.sugar.Local;
import com.shultrea.rin.enchantments.weapon.subject.EnchantmentSubjectEnchantments;
import com.shultrea.rin.registry.EnchantmentRegistry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.silentchaos512.scalinghealth.event.BlightHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentSubjectEnchantments.class)
public abstract class EnchantmentSubjectEnchantmentsMixin {
    @Shadow(remap = false) @Final private int damageType;
    @Shadow(remap = false) @Final private static int BIOLOGY;
    @Shadow(remap = false) @Final private static int HISTORY;

    @Unique private static final String KEY_SUBJHISTORY_POS = "rlmixins_SME_SubjHistory_LastPosition";
    @Unique private static final String KEY_SUBJHISTORY_CNTR = "rlmixins_SME_SubjHistory_Counter";

    @Unique
    //copied from FermiumMixins
    private static boolean fermiummixins$isEntityChampion(EntityLiving entity) {
        IChampionship championship = CapabilityChampionship.getChampionship(entity);
        return championship != null && ChampionHelper.isElite(championship.getRank());
    }

    @Inject(
            method = "onLivingHurtEvent",
            at = @At(value = "FIELD", target = "Lcom/shultrea/rin/enchantments/weapon/subject/EnchantmentSubjectEnchantments;damageType:I", ordinal = 0),
            remap = false,
            cancellable = true
    )
    private void rlmixins_soManyEnchantmentsSubjectEnchantments_onLivingHurt(
            LivingHurtEvent event,
            CallbackInfo ci,
            @Local(name = "attacker") EntityLivingBase attacker,
            @Local(name = "victim") EntityLivingBase defender,
            @Local(name = "level") int level,
            @Local(name = "strengthMulti") float strengthMulti
    ){
        if(this.damageType == BIOLOGY) {
            // More dmg on infernal/blight/champion
            float dmg = 0;

            boolean isInfernal = InfernalMobsCore.getIsRareEntity(defender);
            boolean isBlight = BlightHandler.isBlight(defender);
            boolean isChampion = defender instanceof EntityLiving && fermiummixins$isEntityChampion((EntityLiving) defender);

            if(isInfernal) dmg += 5;
            if(isBlight) dmg += 5;
            if(isChampion) dmg += 5;
            dmg *= level / (float) EnchantmentRegistry.subjectBiology.getMaxLevel() * strengthMulti;
            event.setAmount(event.getAmount() + dmg);
            ci.cancel();

        } else if(this.damageType == HISTORY) {
            //Standing in same spot for longer makes you deal more dmg
            int[] posList = attacker.getEntityData().getIntArray(KEY_SUBJHISTORY_POS); //int[0] if not found
            int attacksWithoutMoving = attacker.getEntityData().getInteger(KEY_SUBJHISTORY_CNTR); //0 if not found

            boolean playerHasMoved = true;
            BlockPos currPos = attacker.getPosition();

            if(posList.length == 3)
                playerHasMoved = currPos.distanceSqToCenter(posList[0], posList[1], posList[2]) > 16; //player is allowed to move 4 blocks from initial position

            //save position if never saved before or if player moved
            if(playerHasMoved) {
                if(posList.length == 0) posList = new int[3];
                posList[0] = currPos.getX();
                posList[1] = currPos.getY();
                posList[2] = currPos.getZ();
                attacker.getEntityData().setIntArray(KEY_SUBJHISTORY_POS, posList);
                attacksWithoutMoving = 0;
            }
            //set counter to 1 for first attack or increment, position stays the one from first attack during most recent streak
            attacker.getEntityData().setInteger(KEY_SUBJHISTORY_CNTR, attacksWithoutMoving + 1);
            //for dmg the counter before is used (starts at 0)
            float dmg = Math.min(attacksWithoutMoving / 10F * 7.5F, 7.5F) * level / (float) EnchantmentRegistry.subjectHistory.getMaxLevel() * strengthMulti; //caps out at 7.5F at 10+1 attacks without moving
            event.setAmount(event.getAmount() + dmg);
            ci.cancel();
        }
    }

    //Subject Geography x DDD handled in rlmixins.handlers.somanyenchantments.SubjectGeographyHandler
}
