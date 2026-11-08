package rlmixins.config;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import rlmixins.RLMixins;
import rlmixins.util.ModLoadedUtil;

@MixinConfig(name = RLMixins.MODID)
public class BetterSurvivalConfig {
	
	@Config.Comment("Force EntityLivingBase#attemptTeleport to cancel under the effects of AntiWarp")
	@Config.Name("AntiWarp Handling (BetterSurvival)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.rlmixins.early.bettersurvival.antiwarp.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterSurvival_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean antiWarpHandling = false;
	
	@Config.Comment("Rewrites BetterSurvival handlers to improve performance and fix crashes with TickDynamic")
	@Config.Name("Handler Optimizations (BetterSurvival)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(
			earlyMixin = "mixins.rlmixins.early.bettersurvival.optimization.json",
			lateMixin = "mixins.rlmixins.late.bettersurvival.optimization.json",
			defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterSurvival_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean handlerOptimizations = false;
	
	@Config.Comment("Rebalances the values of the Education enchantment and adds optional compat to make Blights not affected by Education")
	@Config.Name("Education Balance (BetterSurvival/ScalingHealth)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.bettersurvival.education.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterSurvival_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean educationBalance = false;
	
	@Config.Comment("Makes the Education enchantment also work when the weapon is held in the off hand, like SME's Adept")
	@Config.Name("Education Offhand (BetterSurvival)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.bettersurvival.educationoffhand.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterSurvival_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean educationOffhand = false;
	
	@Config.Comment("Allows for modifying the combo multiplier of Nunchaku")
	@Config.Name("Modifiable Nunchaku Combo (BetterSurvival)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.bettersurvival.combo.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterSurvival_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean modifiableNunchakuCombo = false;
	
	@Config.Comment("Maximum Modifier for Nunchaku (Damage * (1 + this))" + "\n" +
			"Requires \"Modifiable Nunchaku Combo (BetterSurvival)\" enabled")
	@Config.Name("Nunchaku Combo Max Modifier")
	public float nunchakuComboMaxModifier = 1.0F;
	
	@Config.Comment("Fixes Vampirism healing the player when hitting non-living entities")
	@Config.Name("Vampirism Fix (BetterSurvival)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.bettersurvival.vampirism.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterSurvival_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean vampirismFix = false;
	
	@Config.Comment("Fixes Multishot enchantment causing the DragonBone bow to dupe arrows")
	@Config.Name("DragonBone Bow Multishot Patch (BetterSurvival/InFRLCraft)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.bettersurvival.multishot.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterSurvival_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.InFRLCraft_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean dragonBoneBowMultishotPatch = false;
	
	@Config.Comment("Modifies penetration enchant to use SpartanWeaponry's penetration handling with RLCombat to fix damage distribution")
	@Config.Name("Penetration Damage Fix (BetterSurvival/SpartanWeaponry/RLCombat)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.bettersurvival.penetration.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterSurvival_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SpartanWeaponry_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.RLCombat_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean penetrationDamageFix = false;
}