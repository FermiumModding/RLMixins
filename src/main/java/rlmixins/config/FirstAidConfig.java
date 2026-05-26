package rlmixins.config;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import rlmixins.RLMixins;
import rlmixins.util.ModLoadedUtil;

@MixinConfig(name = RLMixins.MODID)
public class FirstAidConfig {

	@Config.Comment("Properly preserve health when using the Exit End Portal")
	@Config.Name("End Exit Health Patch (FirstAid)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(
			earlyMixin = "mixins.rlmixins.early.firstaid.endexithealth.json",
			lateMixin = "mixins.rlmixins.late.firstaid.endexithealth.json",
			defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.FirstAid_MOID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean endExitHealthPatch = false;

	@Config.Comment("Compatibility patch for the End Exit Health Patch when PotionCore is present")
	@Config.Name("End Exit Health Patch with PotionCore Compat (FirstAid/PotionCore)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.firstaid.potioncore.endexithealth.json", defaultValue = false)
	@MixinConfig.CompatHandlingContainer({
			@MixinConfig.CompatHandling(
					modid = ModLoadedUtil.FirstAid_MOID,
					desired = true,
					reason = "Requires mod to properly function"
			),
			@MixinConfig.CompatHandling(
					modid = ModLoadedUtil.PotionCore_MODID,
					desired = true,
					reason = "Requires mod to properly function"
			)
	})
	public boolean endExitHealthPatchPotionCoreCompat = false;

	@Config.Comment("Properly preserve health when logging in")
	@Config.Name("Login Health Patch (FirstAid)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.firstaid.login.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.FirstAid_MOID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean loginHealthPatch = false;
}
