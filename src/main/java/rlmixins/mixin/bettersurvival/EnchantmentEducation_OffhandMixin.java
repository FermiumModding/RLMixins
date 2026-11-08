package rlmixins.mixin.bettersurvival;

import com.google.common.collect.Maps;
import com.mujmajnkraft.bettersurvival.enchantments.EnchantmentEducation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Education is registered for the main hand only, so EnchantmentHelper#getMaxEnchantmentLevel never
 * checks the off hand. Also checking the off hand matches enchantments like SME's Adept, which are
 * registered for both hands.
 */
@Mixin(EnchantmentEducation.class)
public abstract class EnchantmentEducation_OffhandMixin extends Enchantment {
	
	@Unique
	private static final EntityEquipmentSlot[] rlmixins$HANDS = {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND};
	
	protected EnchantmentEducation_OffhandMixin(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	@Override
	@Nonnull
	public Map<EntityEquipmentSlot, ItemStack> getEntityEquipment(@Nonnull EntityLivingBase entityIn) {
		Map<EntityEquipmentSlot, ItemStack> map = Maps.newEnumMap(EntityEquipmentSlot.class);
		for(EntityEquipmentSlot slot : rlmixins$HANDS) {
			ItemStack stack = entityIn.getItemStackFromSlot(slot);
			if(!stack.isEmpty()) map.put(slot, stack);
		}
		return map;
	}
}
