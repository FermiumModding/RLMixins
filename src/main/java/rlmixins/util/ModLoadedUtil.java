package rlmixins.util;

import net.minecraftforge.fml.common.Loader;

public abstract class ModLoadedUtil {

	public static final String Baubles_MODID = "baubles";
	public static final String BetterFoliage_MODID = "betterfoliage";
	public static final String BetterQuesting_MODID = "betterquesting";
	public static final String BetterSurvival_MODID = "mujmajnkraftsbettersurvival";
	public static final String CallableHorses_MODID = "callablehorses";
	public static final String Charm_MODID = "charm";
	public static final String ChunkAnimator_MODID = "chunkanimator";
	public static final String DefiledLands_MODID = "defiledlands";
	public static final String EpicSiegeMod_MODID = "epicsiegemod";
	public static final String InFRLCraft_MODID = "iceandfire";
	public static final String Inspirations_MODID = "inspirations";
	public static final String JEI_MODID = "jei";
	public static final String LycanitesMobs_MODID = "lycanitesmobs";
	public static final String MoBends_MODID = "mobends";
	public static final String PotionCore_MODID = "potioncore";
	public static final String Quark_MODID = "quark";
	public static final String RLArtifacts_MODID = "artifacts";
	public static final String RLCombat_MODID = "bettercombatmod";
	public static final String Rustic_MODID = "rustic";
	public static final String ScalingHealth_MODID = "scalinghealth";
	public static final String SoManyEnchantments_MODID = "somanyenchantments";
	public static final String SpartanWeaponry_MODID = "spartanweaponry";
	public static final String SRParasites_MODID = "srparasites";
	public static final String TestDummy_MODID = "testdummy";

	private static Boolean baublesLoaded = null;
	private static Boolean betterSurvivalLoaded = null;
	private static Boolean charmLoaded = null;
	private static Boolean chunkAnimatorLoaded = null;
	private static Boolean potionCoreLoaded = null;
	private static Boolean quarkLoaded = null;
	private static Boolean rlArtifactsLoaded = null;
	private static Boolean rlcombatLoaded = null;
	private static Boolean scalingHealthLoaded = null;
	private static Boolean spartanWeaponryLoaded = null;
	private static Boolean srparasitesLoaded = null;
	
	public static boolean isBaublesLoaded() {
		if(baublesLoaded == null) baublesLoaded = Loader.isModLoaded(Baubles_MODID);
		return baublesLoaded;
	}
	
	public static boolean isBetterSurvivalLoaded() {
		if(betterSurvivalLoaded == null) betterSurvivalLoaded = Loader.isModLoaded(BetterSurvival_MODID);
		return betterSurvivalLoaded;
	}
	
	public static boolean isCharmLoaded() {
		if(charmLoaded == null) charmLoaded = Loader.isModLoaded(Charm_MODID);
		return charmLoaded;
	}
	
	public static boolean isChunkAnimatorLoaded() {
		if(chunkAnimatorLoaded == null) chunkAnimatorLoaded = Loader.isModLoaded(ChunkAnimator_MODID);
		return chunkAnimatorLoaded;
	}
	
	public static boolean isPotionCoreLoaded() {
		if(potionCoreLoaded == null) potionCoreLoaded = Loader.isModLoaded(PotionCore_MODID);
		return potionCoreLoaded;
	}
	
	public static boolean isQuarkLoaded() {
		if(quarkLoaded == null) quarkLoaded = Loader.isModLoaded(Quark_MODID);
		return quarkLoaded;
	}
	
	public static boolean isRLArtifactsLoaded() {
		if(rlArtifactsLoaded == null) rlArtifactsLoaded = Loader.isModLoaded(RLArtifacts_MODID);
		return rlArtifactsLoaded;
	}
	
	public static boolean isRLCombatLoaded() {
		if(rlcombatLoaded == null) rlcombatLoaded = Loader.isModLoaded(RLCombat_MODID);
		return rlcombatLoaded;
	}
	
	public static boolean isScalingHealthLoaded() {
		if(scalingHealthLoaded == null) scalingHealthLoaded = Loader.isModLoaded(ScalingHealth_MODID);
		return scalingHealthLoaded;
	}
	
	public static boolean isSpartanWeaponryLoaded() {
		if(spartanWeaponryLoaded == null) spartanWeaponryLoaded = Loader.isModLoaded(SpartanWeaponry_MODID);
		return spartanWeaponryLoaded;
	}
	
	public static boolean isSRParasitesLoaded() {
		if(srparasitesLoaded == null) srparasitesLoaded = Loader.isModLoaded(SRParasites_MODID);
		return srparasitesLoaded;
	}
}