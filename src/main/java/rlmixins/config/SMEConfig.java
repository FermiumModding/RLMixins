package rlmixins.config;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import rlmixins.RLMixins;
import rlmixins.util.ModLoadedUtil;

@MixinConfig(name = RLMixins.MODID)
public class SMEConfig {

    @Config.Comment("Changes how some subject enchantments work (History/Biology/Geography). Requires mods DDD (only for geography), ScalingHealth, InfernalMobs and Champions (those three for history/biology)")
    @Config.Name("Change Subject Enchants (SME)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.sme.subjectenchants.json", defaultValue = false)
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.SoManyEnchantments_MODID,
            desired = true,
            reason = "Requires mod to properly function"
    )
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.InfernalMobs_MODID,
            desired = true,
            reason = "Requires mod to properly function"
    )
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.Champions_MODID,
            desired = true,
            reason = "Requires mod to properly function"
    )
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.ScalingHealth_MODID,
            desired = true,
            reason = "Requires mod to properly function"
    )
    public boolean changeSubjectEnhants = false;
}

