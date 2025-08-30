package rlmixins.config;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import rlmixins.RLMixins;
import rlmixins.util.ModLoadedUtil;

@MixinConfig(name = RLMixins.MODID)
public class TestDummyConfig {
    @Config.Comment("Hitting the dummy will not heal the attacker when using SME Lifesteal, BS Vampirism or Lycanites Leech effect")
    @Config.Name("Deny Self Heal On Dummy (MmmMmmMmmMmm/SoManyEnchantments/BetterSurvival/LycanitesMobs)")
    @MixinConfig.MixinToggle(lateMixin = "mixins.rlmixins.late.testdummy.nohealondummy.json", defaultValue = false)
    @Config.RequiresMcRestart
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.TestDummy_MODID,
            desired = true,
            reason = "Requires mod to properly function"
    )
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.BetterSurvival_MODID,
            desired = true,
            reason = "Requires mod to properly function"
    )
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.LycanitesMobs_MODID,
            desired = true,
            reason = "Requires mod to properly function"
    )
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.SoManyEnchantments_MODID,
            desired = true,
            reason = "Requires mod to properly function"
    )
    public boolean dummyNoHeal = false;
}
