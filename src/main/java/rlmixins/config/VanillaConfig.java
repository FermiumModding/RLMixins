package rlmixins.config;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import rlmixins.RLMixins;

@MixinConfig(name = RLMixins.MODID)
public class VanillaConfig {

	@Config.Comment("Skips BlockConcretePowder from running onBlockAdded during worldgen for performance")
	@Config.Name("ConcretePowder Skip OnBlockAdded (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.rlmixins.early.vanilla.concrete.json", defaultValue = false)
	public boolean concretePowderSkipOnBlockAdded = false;

	@Config.Comment("Makes zombie villagers keep their trades throughout zombification and curing")
	@Config.Name("Cured Zombie Villagers Keep Trades (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.rlmixins.early.vanilla.zombietrades.json", defaultValue = false)
	public boolean zombieVillagersKeepTrades = false;

	@Config.Comment("Fixes MC-6431: potion effects not carrying over when using the exit End Portal")
	@Config.Name("Keep Potion Effects on End Exit")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.rlmixins.early.vanilla.endportaleffects.json", defaultValue = false)
	public boolean keepEffectsOnEndExit = false;

	@Config.Comment("Copies the player's attributes when using the Exit End Portal")
	@Config.Name("Keep Attributes on End Exit")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.rlmixins.early.vanilla.endportalattributes.json", defaultValue = false)
	public boolean keepAttributesOnEndExit = false;

	@Config.Comment("Chance on curing zombie villagers to increase their prices and/or remove xp from some of their trades, per trade" + "\n" +
			"Requires \"Cured Zombie Villagers Keep Trades (Vanilla)\" enabled")
	@Config.Name("Curing Trauma Chance (Vanilla)")
	public float tradeChancePriceIncrease = 0.0F;
}