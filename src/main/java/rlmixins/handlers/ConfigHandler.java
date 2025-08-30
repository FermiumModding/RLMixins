package rlmixins.handlers;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rlmixins.RLMixins;
import rlmixins.config.*;

@Config(modid = RLMixins.MODID)
public class ConfigHandler {
	
	@Config.Name("BetterFoliage Config")
	public static final BetterFoliageConfig BETTERFOLIAGE_CONFIG = new BetterFoliageConfig();
	
	@Config.Name("BetterQuesting Config")
	public static final BetterQuestingConfig BETTERQUESTING_CONFIG = new BetterQuestingConfig();
	
	@Config.Name("BetterSurvival Config")
	public static final BetterSurvivalConfig BETTERSURVIVAL_CONFIG = new BetterSurvivalConfig();
	
	@Config.Name("Charm Config")
	public static final CharmConfig CHARM_CONFIG = new CharmConfig();
	
	@Config.Name("ChunkAnimator Config")
	public static final ChunkAnimatorConfig CHUNKANIMATOR_CONFIG = new ChunkAnimatorConfig();
	
	@Config.Name("DefiledLands Config")
	public static final DefiledLandsConfig DEFILEDLANDS_CONFIG = new DefiledLandsConfig();
	
	@Config.Name("EpicSiegeMod Config")
	public static final EpicSiegeModConfig EPICSIEGEMOD_CONFIG = new EpicSiegeModConfig();
	
	@Config.Name("InFRLCraft Config")
	public static final InFRLCraftConfig INFRLCRAFT_CONFIG = new InFRLCraftConfig();
	
	@Config.Name("Inspirations Config")
	public static final InspirationsConfig INSPIRATIONS_CONFIG = new InspirationsConfig();
	
	@Config.Name("JEI Config")
	public static final JEIConfig JEI_CONFIG = new JEIConfig();
	
	@Config.Name("LycanitesMobs Config")
	public static final LycanitesMobsConfig LYCANITESMOBS_CONFIG = new LycanitesMobsConfig();
	
	@Config.Name("MoBends Config")
	public static final MoBendsConfig MOBENDS_CONFIG = new MoBendsConfig();
	
	@Config.Name("PotionCore Config")
	public static final PotionCoreConfig POTIONCORE_CONFIG = new PotionCoreConfig();
	
	@Config.Name("Quark Config")
	public static final QuarkConfig QUARK_CONFIG = new QuarkConfig();
	
	@Config.Name("RLArtifacts Config")
	public static final RLArtifactsConfig RLARTIFACTS_CONFIG = new RLArtifactsConfig();
	
	@Config.Name("RLCombat Config")
	public static final RLCombatConfig RLCOMBAT_CONFIG = new RLCombatConfig();
	
	@Config.Name("RLMixins Config")
	public static final RLMixinsConfig RLMIXINS_CONFIG = new RLMixinsConfig();
	
	@Config.Name("Rustic Config")
	public static final RusticConfig RUSTIC_CONFIG = new RusticConfig();
	
	@Config.Name("ScalingHealth Config")
	public static final ScalingHealthConfig SCALINGHEALTH_CONFIG = new ScalingHealthConfig();
	
	@Config.Name("SRParasites Config")
	public static final SRParasitesConfig SRPARASITES_CONFIG = new SRParasitesConfig();

	@Config.Name("TestDummy Config")
	public static final TestDummyConfig TESTDUMMY_CONFIG = new TestDummyConfig();
	
	@Config.Name("Vanilla Config")
	public static final VanillaConfig VANILLA_CONFIG = new VanillaConfig();
	
	@Mod.EventBusSubscriber(modid = RLMixins.MODID)
	private static class ConfigSyncHandler {
		
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(RLMixins.MODID)) {
				ConfigManager.sync(RLMixins.MODID, Config.Type.INSTANCE);
				ConfigHandler.DEFILEDLANDS_CONFIG.refreshConfig();
				ConfigHandler.LYCANITESMOBS_CONFIG.refreshConfig();
				ConfigHandler.RLCOMBAT_CONFIG.refreshConfig();
				ConfigHandler.RLMIXINS_CONFIG.refreshConfig();
				ConfigHandler.SCALINGHEALTH_CONFIG.refreshConfig();
				ConfigHandler.SRPARASITES_CONFIG.refreshConfig();
			}
		}
	}
}