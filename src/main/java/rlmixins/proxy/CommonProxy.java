package rlmixins.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import rlmixins.handlers.ConfigHandler;
import rlmixins.handlers.bettersurvival.PenetrationHandler;
import rlmixins.handlers.potioncore.CureHandler;
import rlmixins.handlers.quark.PirateHatHandler;
import rlmixins.handlers.rlartifacts.AntidoteHandler;
import rlmixins.handlers.rlcombat.NetherBaneHandler;
import rlmixins.handlers.rlmixins.LesserFireResistanceHandler;
import rlmixins.handlers.rlmixins.MobNauseaHandler;
import rlmixins.handlers.srparasites.ArmorCureHandler;
import rlmixins.handlers.srparasites.WeaponAOEHandler;
import rlmixins.handlers.vanilla.SlimeProjectileBounceHandler;
import rlmixins.util.ModLoadedUtil;

import java.util.Map;

public class CommonProxy {
    
    public void registerSubscribers() {
        if(ConfigHandler.BETTERSURVIVAL_CONFIG.penetrationDamageFix &&
                ModLoadedUtil.isBetterSurvivalLoaded() &&
                ModLoadedUtil.isSpartanWeaponryLoaded() &&
                ModLoadedUtil.isRLCombatLoaded()) {
            MinecraftForge.EVENT_BUS.register(PenetrationHandler.class);
        }
        if(ConfigHandler.POTIONCORE_CONFIG.cureAppliesOnAttack && ModLoadedUtil.isPotionCoreLoaded()) {
            MinecraftForge.EVENT_BUS.register(CureHandler.class);
        }
        if(ConfigHandler.QUARK_CONFIG.pirateHatLooting && ModLoadedUtil.isQuarkLoaded()) {
            MinecraftForge.EVENT_BUS.register(PirateHatHandler.class);
        }
        if(ConfigHandler.RLARTIFACTS_CONFIG.antidoteVesselEnhancement && ModLoadedUtil.isRLArtifactsLoaded()) {
            MinecraftForge.EVENT_BUS.register(AntidoteHandler.class);
        }
        if(ConfigHandler.RLCOMBAT_CONFIG.enableNetherBane && ModLoadedUtil.isRLCombatLoaded()) {
            MinecraftForge.EVENT_BUS.register(NetherBaneHandler.class);
        }
        if(ConfigHandler.RLMIXINS_CONFIG.lesserFireResistancePotionEffect) {
            MinecraftForge.EVENT_BUS.register(LesserFireResistanceHandler.class);
        }
        if(ConfigHandler.RLMIXINS_CONFIG.mobNauseaMovementEffects) {
            MinecraftForge.EVENT_BUS.register(MobNauseaHandler.class);
        }
        if(ConfigHandler.RLMIXINS_CONFIG.slimeBouncySides) {
            MinecraftForge.EVENT_BUS.register(SlimeProjectileBounceHandler.class);
        }
        if((ConfigHandler.SRPARASITES_CONFIG.armorFearCure || ConfigHandler.SRPARASITES_CONFIG.armorViralCure) && ModLoadedUtil.isSRParasitesLoaded()) {
            MinecraftForge.EVENT_BUS.register(ArmorCureHandler.class);
        }
        if(ConfigHandler.SRPARASITES_CONFIG.rehandleAOEWeapons && ModLoadedUtil.isSRParasitesLoaded() && ModLoadedUtil.isRLCombatLoaded()) {
            MinecraftForge.EVENT_BUS.register(WeaponAOEHandler.class);
        }
    }
    
    public Map<Item, ModelBiped> getSteelArmor() {
        return null;
    }
    
    public Map<Item, ModelBiped> getScarliteArmor() {
        return null;
    }
    
    public Map<Item, ModelBiped> getLivingArmor() {
        return null;
    }
    
    public Map<Item, ModelBiped> getSentientArmor() {
        return null;
    }
}