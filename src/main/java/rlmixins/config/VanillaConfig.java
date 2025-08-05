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

	@Config.Comment("Chance on curing zombie villagers to increase their prices and/or remove xp from some of their trades, per trade" + "\n" +
			"Requires \"Cured Zombie Villagers Keep Trades (Vanilla)\" enabled")
	@Config.Name("Curing Trauma Chance (Vanilla)")
	public float tradeChancePriceIncrease = 0.0F;
}