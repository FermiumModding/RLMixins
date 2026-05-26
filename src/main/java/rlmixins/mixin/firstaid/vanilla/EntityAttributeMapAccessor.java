package rlmixins.mixin.firstaid.vanilla;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityLivingBase.class)
public interface EntityAttributeMapAccessor {

	@Accessor("attributeMap")
	void rlmixins$setAttributeMap(AbstractAttributeMap attributeMap);
}
