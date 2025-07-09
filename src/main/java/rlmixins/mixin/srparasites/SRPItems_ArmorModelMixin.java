package rlmixins.mixin.srparasites;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rlmixins.RLMixins;

@Mixin(SRPItems.class)
public abstract class SRPItems_ArmorModelMixin {

    @Redirect(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/util/EnumHelper;addArmorMaterial(Ljava/lang/String;Ljava/lang/String;I[IILnet/minecraft/util/SoundEvent;F)Lnet/minecraft/item/ItemArmor$ArmorMaterial;", remap = false)
    )
    private static ItemArmor.ArmorMaterial rlmixins_srparasitesSRPItems_addArmorMaterial(String name, String textureName, int durability, int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness) {
        if(name.equals("armor_livings")) {
            RLMixins.LOGGER.log(Level.INFO, "RLMixins replacing Parasites Living Armor Material");
            return EnumHelper.addArmorMaterial(
                    name,
                    RLMixins.MODID + ":living_armor",
                    durability,
                    reductionAmounts,
                    enchantability,
                    soundOnEquip,
                    toughness
            );
        }
        else if(name.equals("armor_sentients")) {
            RLMixins.LOGGER.log(Level.INFO, "RLMixins replacing Parasites Sentient Armor Material");
            return EnumHelper.addArmorMaterial(
                    name,
                    RLMixins.MODID + ":sentient_armor",
                    durability,
                    reductionAmounts,
                    enchantability,
                    soundOnEquip,
                    toughness
            );
        }
        else {
            RLMixins.LOGGER.log(Level.ERROR, "RLMixins failed to modify SRParasites armor material: " + name);
            return null;
        }
    }
}