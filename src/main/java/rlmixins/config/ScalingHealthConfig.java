package rlmixins.config;

import fermiumbooter.annotations.MixinConfig;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import rlmixins.RLMixins;
import rlmixins.util.ModLoadedUtil;

import java.util.HashMap;
import java.util.Map;

@MixinConfig(name = RLMixins.MODID)
public class ScalingHealthConfig {
	
	@Config.Comment("Attempts to fix a desync caused by ScalingHealth when a mob dies in the same tick it is spawned")
	@Config.Name("Death Health Desync (ScalingHealth)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.scalinghealth.desync.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ScalingHealth_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean deathHealthDesync = false;
	
	@Config.Comment("Fixes the bandaged effect not having an icon")
	@Config.Name("Bandaged Icon Fix (ScalingHealth)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.scalinghealth.icon.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ScalingHealth_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean bandagedIconFix = false;

	@Config.Comment("Allows giving blights additional constantly active potion effects")
	@Config.Name("Additional Blight Potion Effects (ScalingHealth)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.scalinghealth.blighteffects.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ScalingHealth_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean enableAdditionalBlightPotionEffects = false;

	@Config.Comment("The potion effects to apply on blights (Format: modid:potionname, amplifier)" + "\n" +
			"Requires \"Additional Blight Potion Effects (ScalingHealth)\" enabled")
	@Config.Name("Additional Blight Potion Effects List")
	public String[] additionalBlightffects = {};

	private Map<Potion, Integer> blightEffects = null;

	public Map<Potion, Integer> getBlightEffects() {
		if(this.blightEffects == null) {
			this.blightEffects = new HashMap<>();
			for(String line : this.additionalBlightffects) {
				String[] split = line.split(",");
				Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(split[0].trim()));
				try {
					int amplifier = Integer.parseInt(split[1].trim());
					if(potion != null) this.blightEffects.put(potion, amplifier);
				} catch (Exception e){
					RLMixins.LOGGER.error("RLMixins unable to parse Blight Effect Line, expected number for amplifier in: {}", line);
				}
			}
		}
		return this.blightEffects;
	}

	public void refreshConfig() {
		blightEffects.clear();
	}
}