package rlmixins.mixin.rustic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rlmixins.config.RusticConfig;
import rlmixins.handlers.ConfigHandler;
import rustic.common.blocks.fluids.FluidBooze;
import rustic.common.blocks.fluids.ModFluids;

import java.util.Arrays;

@Mixin(ModFluids.class)
public abstract class ModFluids_ConfigMixin {

    @Shadow(remap = false)
    public static Fluid ALE;
    
    @Shadow(remap = false)
    public static Fluid CIDER;
    
    @Shadow(remap = false)
    public static Fluid IRON_WINE;
    
    @Shadow(remap = false)
    public static Fluid MEAD;
    
    @Shadow(remap = false)
    public static Fluid WILDBERRY_WINE;
    
    @Shadow(remap = false)
    public static Fluid WINE;

    @Redirect(
            method = "init",
            at = @At(value = "FIELD", target = "Lrustic/common/blocks/fluids/ModFluids;ALE:Lnet/minecraftforge/fluids/Fluid;", opcode = Opcodes.PUTSTATIC),
            remap = false
    )
    private static void rlmixins_rusticModFluids_initAle(Fluid value) {
        ALE = new FluidBooze("ale", new ResourceLocation("rustic:blocks/fluids/booze/ale_still"), new ResourceLocation("rustic:blocks/fluids/booze/ale_flow")) {
            @Override
            protected void affectPlayer(World world, EntityPlayer player, float quality) {
                if(quality > 0.5F) {
                    float saturation = 4.0F * quality;
                    player.getFoodStats().addStats(2, saturation);
                    int duration = (int)(ConfigHandler.RUSTIC_CONFIG.aleMaxDurationPositive * (quality - 0.5F) * 2.0F);
                    player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(ConfigHandler.RUSTIC_CONFIG.aleEffect), duration));
                }
                else {
                    int duration = (int)(ConfigHandler.RUSTIC_CONFIG.aleMaxDurationHunger * Math.max(1.0F - quality, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, duration));
                    duration = (int)(ConfigHandler.RUSTIC_CONFIG.aleMaxDurationNausea * Math.max(1.0F - quality, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, duration));
                }
            }
        }.setInebriationChance(ConfigHandler.RUSTIC_CONFIG.aleInebriationChance).setDensity(1004).setViscosity(1016);
    }

    @Redirect(
            method = "init",
            at = @At(value = "FIELD", target = "Lrustic/common/blocks/fluids/ModFluids;CIDER:Lnet/minecraftforge/fluids/Fluid;", opcode = Opcodes.PUTSTATIC),
            remap = false
    )
    private static void rlmixins_rusticModFluids_initCider(Fluid value) {
        CIDER = new FluidBooze("cider", new ResourceLocation("rustic:blocks/fluids/booze/cider_still"), new ResourceLocation("rustic:blocks/fluids/booze/cider_flow")) {
            @Override
            protected void affectPlayer(World world, EntityPlayer player, float quality) {
                if(quality > 0.5F) {
                    float saturation = 2.0F * quality;
                    player.getFoodStats().addStats(1, saturation);
                    int duration = (int)(ConfigHandler.RUSTIC_CONFIG.ciderMaxDurationPositive * (quality - 0.5F) * 2.0F);
                    player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(ConfigHandler.RUSTIC_CONFIG.ciderEffect), duration));
                }
                else {
                    int duration = (int)(ConfigHandler.RUSTIC_CONFIG.ciderMaxDurationPoison * Math.max(1.0F - quality, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.POISON, duration));
                    duration = (int)(ConfigHandler.RUSTIC_CONFIG.ciderMaxDurationNausea * Math.max(1.0F - quality, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, duration));
                }
            }
        }.setInebriationChance(ConfigHandler.RUSTIC_CONFIG.ciderInebriationChance).setDensity(1004).setViscosity(1400);
    }

    @Redirect(
            method = "init",
            at = @At(value = "FIELD", target = "Lrustic/common/blocks/fluids/ModFluids;IRON_WINE:Lnet/minecraftforge/fluids/Fluid;", opcode = Opcodes.PUTSTATIC),
            remap = false
    )
    private static void rlmixins_rusticModFluids_initIronWine(Fluid value) {
        IRON_WINE = new FluidBooze("ironwine", new ResourceLocation("rustic:blocks/fluids/booze/iron_wine_still"), new ResourceLocation("rustic:blocks/fluids/booze/iron_wine_flow")) {
           @Override
           protected void affectPlayer(World world, EntityPlayer player, float quality) {
               if(quality > 0.5F) {
                   float saturation = 2.0F * quality;
                   player.getFoodStats().addStats(1, saturation);
                   float absorption = ConfigHandler.RUSTIC_CONFIG.ironWineMaxAbsorption * (quality - 0.5F) * 2.0F;
                   player.setAbsorptionAmount(Math.max(Math.min(player.getAbsorptionAmount() + absorption, 20.0F), player.getAbsorptionAmount()));
               }
               else {
                   int duration = (int)(ConfigHandler.RUSTIC_CONFIG.ironWineMaxDurationNausea * Math.max(1.0F - quality, 0.0F));
                   player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, duration));
                   float damage = ConfigHandler.RUSTIC_CONFIG.ironWineMaxDamage * 2.0F * Math.max(0.5F - quality, 0) + 1.0F;
                   player.attackEntityFrom(DamageSource.MAGIC, damage);
               }
            }
        }.setInebriationChance(ConfigHandler.RUSTIC_CONFIG.ironWineInebriationChance).setDensity(1034).setViscosity(1400);
    }

    @Redirect(
            method = "init",
            at = @At(value = "FIELD", target = "Lrustic/common/blocks/fluids/ModFluids;MEAD:Lnet/minecraftforge/fluids/Fluid;", opcode = Opcodes.PUTSTATIC),
            remap = false
    )
    private static void rlmixins_rusticModFluids_initMead(Fluid value) {
        MEAD = new FluidBooze("mead", new ResourceLocation("rustic:blocks/fluids/booze/mead_still"), new ResourceLocation("rustic:blocks/fluids/booze/mead_flow")) {
            @Override
            protected void affectPlayer(World world, EntityPlayer player, float quality) {
                if(quality > 0.5F) {
                    float saturation = 2F * quality;
                    player.getFoodStats().addStats(1, saturation);
                    int duration = (int)(ConfigHandler.RUSTIC_CONFIG.meadMaxDurationPositive * (quality - 0.5F) * 2.0F);
                    player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(ConfigHandler.RUSTIC_CONFIG.meadEffect), duration));
                }
                else {
                    int duration = (int)(ConfigHandler.RUSTIC_CONFIG.meadMaxDurationWither * Math.max(1.0F - quality, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.WITHER, duration));
                    duration = (int)(ConfigHandler.RUSTIC_CONFIG.meadMaxDurationNausea * Math.max(1.0F - quality, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, duration));
                }
            }
        }.setInebriationChance(ConfigHandler.RUSTIC_CONFIG.meadInebriationChance).setDensity(1034).setViscosity(1500);
    }

    @Redirect(
            method = "init",
            at = @At(value = "FIELD", target = "Lrustic/common/blocks/fluids/ModFluids;WILDBERRY_WINE:Lnet/minecraftforge/fluids/Fluid;", opcode = Opcodes.PUTSTATIC),
            remap = false
    )
    private static void rlmixins_rusticModFluids_initWildberryWine(Fluid value) {
        WILDBERRY_WINE = new FluidBooze("wildberrywine", new ResourceLocation("rustic:blocks/fluids/booze/wildberry_wine_still"), new ResourceLocation("rustic:blocks/fluids/booze/wildberry_wine_flow")) {
            @Override
            protected void affectPlayer(World world, EntityPlayer player, float quality) {
                if(quality > 0.5F) {
                    float saturation = 2.0F * quality;
                    player.getFoodStats().addStats(1, saturation);
                    PotionEffect[] effects = player.getActivePotionEffects().toArray(new PotionEffect[0]);
                    for(PotionEffect potionEffect : effects) {
                        if(potionEffect.getPotion().isBadEffect()) continue;
                        if(RusticConfig.isUnwineable(potionEffect.getPotion())) continue;

                        if(potionEffect.getAmplifier() < ConfigHandler.RUSTIC_CONFIG.wildberryWineMaxAmplifier) {
                            if(world.rand.nextFloat() <= (quality - 0.5F) * 2.0F) {
                                player.addPotionEffect(new PotionEffect(potionEffect.getPotion(), potionEffect.getDuration(), Math.min(potionEffect.getAmplifier() + ConfigHandler.RUSTIC_CONFIG.wildberryWineMaxAmplifierIncrease, ConfigHandler.RUSTIC_CONFIG.wildberryWineMaxAmplifier), potionEffect.getIsAmbient(), potionEffect.doesShowParticles()));
                            }
                        }
                    }
                }
                else {
                    PotionEffect[] effects = player.getActivePotionEffects().toArray(new PotionEffect[0]);
                    for(PotionEffect effect : effects) {
                        if(effect.getPotion().isBadEffect()) continue;
                        if(RusticConfig.isUnwineable(effect.getPotion())) continue;

                        int amp = effect.getAmplifier() - ConfigHandler.RUSTIC_CONFIG.wildberryWineAmplifierDecrease;
                        player.removePotionEffect(effect.getPotion());
                        if(amp >= 0) {
                            player.addPotionEffect(new PotionEffect(effect.getPotion(), effect.getDuration(), amp, effect.getIsAmbient(), effect.doesShowParticles()));
                        }
                    }
                    int duration = (int)(ConfigHandler.RUSTIC_CONFIG.wildberryWineMaxDurationNausea * Math.max(1.0F - quality, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, duration));
                }

            }
        }.setInebriationChance(ConfigHandler.RUSTIC_CONFIG.wildberryWineInebriationChance).setDensity(1034).setViscosity(1500);
    }

    @Redirect(
            method = "init",
            at = @At(value = "FIELD", target = "Lrustic/common/blocks/fluids/ModFluids;WINE:Lnet/minecraftforge/fluids/Fluid;", opcode = Opcodes.PUTSTATIC),
            remap = false
    )
    private static void rlmixins_rusticModFluids_initWine(Fluid value) {
        WINE = new FluidBooze("wine", new ResourceLocation("rustic:blocks/fluids/booze/wine_still"), new ResourceLocation("rustic:blocks/fluids/booze/wine_flow")) {
            protected void affectPlayer(World world, EntityPlayer player, float quality) {
                if(quality > 0.5F) {
                    float saturation = 2.0F * quality;
                    player.getFoodStats().addStats(1, saturation);
                    PotionEffect[] effects = player.getActivePotionEffects().toArray(new PotionEffect[0]);
                    for(PotionEffect effect : effects) {
                        if(RusticConfig.isUnwineable(effect.getPotion())) continue;

                        int durationIncrease = (int)(ConfigHandler.RUSTIC_CONFIG.wineMaximumDurationIncrease * (quality - 0.5F) * 2.0F);
                        if(!effect.getPotion().isBadEffect() && effect.getDuration() < (int)ConfigHandler.RUSTIC_CONFIG.wineMaximumDuration) {
                            int duration = Math.max(Math.min(effect.getDuration() + durationIncrease, (int)ConfigHandler.RUSTIC_CONFIG.wineMaximumDuration), effect.getDuration());
                            player.addPotionEffect(new PotionEffect(effect.getPotion(), duration, effect.getAmplifier(), effect.getIsAmbient(), effect.doesShowParticles()));
                        }
                    }
                }
                else {
                    PotionEffect[] effects = player.getActivePotionEffects().toArray(new PotionEffect[0]);
                    for(PotionEffect effect : effects) {
                        if(RusticConfig.isUnwineable(effect.getPotion())) continue;

                        int durationDecrease = (int)(ConfigHandler.RUSTIC_CONFIG.wineMaximumDurationDecrease * Math.max(0.5F - quality, 0));
                        if(!effect.getPotion().isBadEffect()) {
                            int duration = effect.getDuration() - durationDecrease;
                            player.removePotionEffect(effect.getPotion());
                            if(duration > 0) {
                                player.addPotionEffect(new PotionEffect(effect.getPotion(), duration, effect.getAmplifier(), effect.getIsAmbient(), effect.doesShowParticles()));
                            }
                        }
                    }
                    int duration = (int)(ConfigHandler.RUSTIC_CONFIG.wineMaxDurationNausea * Math.max(1.0F - quality, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, duration));
                }
            }
        }.setInebriationChance(ConfigHandler.RUSTIC_CONFIG.wineInebriationChance).setDensity(1034).setViscosity(1500);
    }
}