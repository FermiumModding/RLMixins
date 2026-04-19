package rlmixins.config;

import fermiumbooter.annotations.MixinConfig;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import rlmixins.RLMixins;
import rlmixins.handlers.ConfigHandler;
import rlmixins.util.ModLoadedUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@MixinConfig(name = RLMixins.MODID)
public class RusticConfig {
	
	@Config.Comment("Makes Coffee from Charm reduce the effects of Inebriation from Rustic instead of water")
	@Config.Name("Coffee Cures Inebriation (Rustic/Charm)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.rustic.coffee.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Rustic_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean coffeeCuresInebriation = false;
	
	@Config.Comment("Allows for defining the effects of Ale, Cider, Iron Wine, Mead, Wildberry Wine, and Wine")
	@Config.Name("Config Alcohol Effects (Rustic)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.rustic.alcohol.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Rustic_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean configAlcoholEffects = false;
	
	@Config.Comment("Effect that drinking Ale should give")
	@Config.Name("Ale Effect")
	public String aleEffect = "lycanitesmobs:immunization";
	
	@Config.Comment("Maximum duration of the positive effect given by ale at quality 1.0")
	@Config.Name("Ale Maximum Duration Positive")
	public float aleMaxDurationPositive = 12000;
	
	@Config.Comment("Maximum duration of the hunger effect given by ale below quality 0.5")
	@Config.Name("Ale Maximum Duration Hunger")
	public float aleMaxDurationHunger = 6000;
	
	@Config.Comment("Maximum duration of the nausea effect given by ale below quality 0.5")
	@Config.Name("Ale Maximum Duration Nausea")
	public float aleMaxDurationNausea = 6000;
	
	@Config.Comment("Inebriation chance when drinking ale")
	@Config.Name("Ale Inebriation Chance")
	public float aleInebriationChance = 0.5F;
	
	@Config.Comment("Effect that drinking Cider should give")
	@Config.Name("Cider Effect")
	public String ciderEffect = "potioncore:magic_shield";
	
	@Config.Comment("Maximum duration of the positive effect given by cider at quality 1.0")
	@Config.Name("Cider Maximum Duration Positive")
	public float ciderMaxDurationPositive = 12000;
	
	@Config.Comment("Maximum duration of the poison effect given by cider below quality 0.5")
	@Config.Name("Cider Maximum Duration Poison")
	public float ciderMaxDurationPoison = 1200;
	
	@Config.Comment("Maximum duration of the nausea effect given by cider below quality 0.5")
	@Config.Name("Cider Maximum Duration Nausea")
	public float ciderMaxDurationNausea = 6000;
	
	@Config.Comment("Inebriation chance when drinking cider")
	@Config.Name("Cider Inebriation Chance")
	public float ciderInebriationChance = 0.5F;
	
	@Config.Comment("Maximum absorption given by iron wine at quality 1.0")
	@Config.Name("Iron Wine Maximum Absorption")
	public float ironWineMaxAbsorption = 10.0F;
	
	@Config.Comment("Maximum duration of the nausea effect given by iron wine below quality 0.5")
	@Config.Name("Iron Wine Maximum Duration Nausea")
	public float ironWineMaxDurationNausea = 6000;
	
	@Config.Comment("Maximum damage given by iron wine below quality 0.5 (Plus 1)")
	@Config.Name("Iron Wine Maximum Damage")
	public float ironWineMaxDamage = 5.0F;
	
	@Config.Comment("Inebriation chance when drinking iron wine")
	@Config.Name("Iron Wine Inebriation Chance")
	public float ironWineInebriationChance = 0.5F;
	
	@Config.Comment("Effect that drinking Mead should give")
	@Config.Name("Mead Effect")
	public String meadEffect = "lycanitesmobs:rejuvenation";
	
	@Config.Comment("Maximum duration of the positive effect given by mead at quality 1.0")
	@Config.Name("Mead Maximum Duration Positive")
	public float meadMaxDurationPositive = 6000;
	
	@Config.Comment("Maximum duration of the wither effect given by mead below quality 0.5")
	@Config.Name("Mead Maximum Duration Wither")
	public float meadMaxDurationWither = 800;
	
	@Config.Comment("Maximum duration of the nausea effect given by mead below quality 0.5")
	@Config.Name("Mead Maximum Duration Nausea")
	public float meadMaxDurationNausea = 6000;
	
	@Config.Comment("Inebriation chance when drinking mead")
	@Config.Name("Mead Inebriation Chance")
	public float meadInebriationChance = 0.5F;
	
	@Config.Comment("Maximum amplifier of the positive effect increased by wildberry wine above quality 0.5")
	@Config.Name("Wildberry Wine Maximum Amplifier")
	public int wildberryWineMaxAmplifier = 2;
	
	@Config.Comment("Maximum amplifier increase of the positive effect increased by wildberry wine above quality 0.5")
	@Config.Name("Wildberry Wine Maximum Amplifier Increase")
	public int wildberryWineMaxAmplifierIncrease = 1;
	
	@Config.Comment("Amplifier decrease of positive effects by wildberry wine below quality 0.5")
	@Config.Name("Wildberry Wine Amplifier Decrease")
	public int wildberryWineAmplifierDecrease = 1;
	
	@Config.Comment("Maximum duration of the nausea effect given by wildberry wine below quality 0.5")
	@Config.Name("Wildberry Wine Maximum Duration Nausea")
	public float wildberryWineMaxDurationNausea = 6000;
	
	@Config.Comment("Inebriation chance when drinking wildberry wine")
	@Config.Name("Wildberry Wine Inebriation Chance")
	public float wildberryWineInebriationChance = 0.5F;
	
	@Config.Comment("Maximum duration increase of the positive effect increased by wine above quality 0.5")
	@Config.Name("Wine Maximum Duration Increase")
	public float wineMaximumDurationIncrease = 2400;
	
	@Config.Comment("Maximum duration of the positive effect increased by wine above quality 0.5")
	@Config.Name("Wine Maximum Duration")
	public float wineMaximumDuration = 12000;
	
	@Config.Comment("Maximum duration decrease of the positive effect decreased by wine below quality 0.5")
	@Config.Name("Wine Maximum Duration Decrease")
	public float wineMaximumDurationDecrease = 2400;
	
	@Config.Comment("Maximum duration of the nausea effect given by wine below quality 0.5")
	@Config.Name("Wine Maximum Duration Nausea")
	public float wineMaxDurationNausea = 6000;
	
	@Config.Comment("Inebriation chance when drinking wine")
	@Config.Name("Wine Inebriation Chance")
	public float wineInebriationChance = 0.5F;

	@Config.Comment("List of modid:potionid potions that are not affected by wine and wildberry wine")
	@Config.Name("Unwineable Potions")
	public String[] unwineablePotions = {};

	private static Set<String> unwineablePots = null;

	public static boolean isUnwineable(Potion potion) {
		if(unwineablePots == null) unwineablePots = Stream.of(ConfigHandler.RUSTIC_CONFIG.unwineablePotions).collect(Collectors.toSet());
		ResourceLocation loc = potion.getRegistryName();
		if(loc == null) return false;
		return unwineablePots.contains(loc.toString());
	}
}