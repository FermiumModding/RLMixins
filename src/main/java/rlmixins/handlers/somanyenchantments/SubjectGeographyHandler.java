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
import yeelp.distinctdamagedescriptions.registries.DDDRegistries;

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
        float percentage = strengthMulti * lvl / EnchantmentRegistry.subjectGeography.getMaxLevel(); //caps out at 100% for max lvl and full hit

        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(attacker.world.getBiome(attacker.getPosition()));

        if (types.contains(BiomeDictionary.Type.HOT) || types.contains(BiomeDictionary.Type.NETHER))
            setDmgToType(event, DDDBuiltInDamageType.FIRE, percentage);
        else if (types.contains(BiomeDictionary.Type.COLD) || types.contains(BiomeDictionary.Type.SNOWY))
            setDmgToType(event, DDDBuiltInDamageType.COLD, percentage);
        else if (types.contains(BiomeDictionary.Type.SWAMP))
            setDmgToType(event, DDDBuiltInDamageType.POISON, percentage);
        else if (types.contains(BiomeDictionary.Type.WASTELAND))
            setDmgToType(event, DDDBuiltInDamageType.ACID, percentage);
        else if (types.contains(BiomeDictionary.Type.SPOOKY) || types.contains(BiomeDictionary.Type.DEAD))
            setDmgToType(event, DDDBuiltInDamageType.NECROTIC, percentage);
        else if (types.contains(BiomeDictionary.Type.MAGICAL))
            setDmgToType(event, DDDBuiltInDamageType.RADIANT, percentage);
        //Otherwise no effect
    }

    private static void setDmgToType(DetermineDamageEvent event, DDDDamageType type, float percentage) {
        double totalDmg = DDDRegistries.damageTypes.getAll().stream().mapToDouble(event::getDamage).sum();
        double dmgNewType = percentage * totalDmg;
        //reduce all
        DDDRegistries.damageTypes.getAll().forEach(dmgType ->
                event.setDamage(dmgType, event.getDamage(dmgType) * (1 - percentage))
        );
        //increase specific
        event.setDamage(type, (float) (event.getDamage(type) + dmgNewType));
    }
}
