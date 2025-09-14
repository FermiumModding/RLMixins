package rlmixins.handlers.somanyenchantments;

import com.shultrea.rin.enchantments.base.EnchantmentBase;
import com.shultrea.rin.registry.EnchantmentRegistry;
import com.shultrea.rin.util.compat.CompatUtil;
import com.shultrea.rin.util.compat.RLCombatCompat;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yeelp.distinctdamagedescriptions.api.DDDDamageType;
import yeelp.distinctdamagedescriptions.api.impl.DDDBuiltInDamageType;
import yeelp.distinctdamagedescriptions.event.classification.DetermineDamageEvent;

import java.util.Set;

public class SubjectGeographyHandler {
    @SubscribeEvent
    public static void onDetermineDamage(DetermineDamageEvent event) {
        if (!EnchantmentRegistry.subjectGeography.isEnabled()) return;
        if (!EnchantmentBase.isDamageSourceAllowed(event.getSource())) return;
        EntityLivingBase attacker = (EntityLivingBase) event.getTrueAttacker();
        int lvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.subjectGeography, attacker.getHeldItemMainhand());
        if (lvl <= 0) return;

        float strengthMulti = CompatUtil.isRLCombatLoaded() ? RLCombatCompat.getAttackEntityFromStrength() : 1.0F;
        float addedDmg = (5.0F * strengthMulti * lvl) / EnchantmentRegistry.subjectGeography.getMaxLevel(); //caps out at +5 for max lvl and full hit

        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(attacker.world.getBiome(attacker.getPosition()));

        if (types.contains(BiomeDictionary.Type.HOT) || types.contains(BiomeDictionary.Type.NETHER))
            addDmgForType(event, DDDBuiltInDamageType.FIRE, addedDmg);
        else if (types.contains(BiomeDictionary.Type.COLD) || types.contains(BiomeDictionary.Type.SNOWY))
            addDmgForType(event, DDDBuiltInDamageType.COLD, addedDmg);
        else if (types.contains(BiomeDictionary.Type.SWAMP))
            addDmgForType(event, DDDBuiltInDamageType.POISON, addedDmg);
        else if (types.contains(BiomeDictionary.Type.WASTELAND))
            addDmgForType(event, DDDBuiltInDamageType.ACID, addedDmg);
        else if (types.contains(BiomeDictionary.Type.SPOOKY) || types.contains(BiomeDictionary.Type.DEAD))
            addDmgForType(event, DDDBuiltInDamageType.NECROTIC, addedDmg);
        else if (types.contains(BiomeDictionary.Type.MAGICAL))
            addDmgForType(event, DDDBuiltInDamageType.RADIANT, addedDmg);
        else
            addDmgForType(event, DDDBuiltInDamageType.NORMAL, addedDmg);
    }

    private static void addDmgForType(DetermineDamageEvent event, DDDDamageType type, float amount){
        event.setDamage(type, event.getDamage(type) + amount);
    }
}
