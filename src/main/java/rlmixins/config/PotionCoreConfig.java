package rlmixins.config;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import rlmixins.RLMixins;
import rlmixins.util.ModLoadedUtil;

@MixinConfig(name = RLMixins.MODID)
public class PotionCoreConfig {
	
	@Config.Comment("Replaces the Launch effect from PotionCore with Delayed Launch, for compatibility with knockback effects")
	@Config.Name("Delayed Launch (PotionCore)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.potioncore.launch.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.PotionCore_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean delayedLaunch = false;
	
	@Config.Comment("Halves the effect of the Reach potion")
	@Config.Name("Half Reach (PotionCore)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.potioncore.reach.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.PotionCore_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean halfReach = false;
	
	@Config.Comment("Adds and registers the Encumbered potion effect")
	@Config.Name("Encumbered Potion Effect")
	@Config.RequiresMcRestart
	public boolean encumberedPotionEffect = false;
	
	@Config.Comment("Makes the Cure effect apply during attacks")
	@Config.Name("Cure Applies On Attack")
	@Config.RequiresMcRestart
	public boolean cureAppliesOnAttack = false;

	@Config.Comment("Makes health persist when using the Exit End Portal\nThere is a similar issue with ScalingHealth)")
	@Config.Name("Persistent Health Fix")
	@Config.RequiresMcRestart
	public boolean persistentHealthFix = false;
}