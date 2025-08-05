package rlmixins.mixin.vanilla;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import rlmixins.wrapper.IMerchantRecipeMixin;

/**
 * Patch by Nischhelm
 */
@Mixin(MerchantRecipe.class)
public abstract class MerchantRecipe_KeepTradeMixin implements IMerchantRecipeMixin {
    
    @Shadow private ItemStack itemToBuy;
    @Shadow private ItemStack itemToSell;
    @Shadow private ItemStack secondItemToBuy;
    @Shadow public abstract boolean hasSecondItemToBuy();
    @Shadow private boolean rewardsExp;

    @Override
    @Unique
    public void rlmixins$increasePrices() {
        if(this.itemToBuy.getItem().equals(Items.EMERALD)) {
            this.itemToBuy.setCount((int)Math.min(this.itemToBuy.getCount() * 1.25F, 64));
        }

        if(this.hasSecondItemToBuy() && this.secondItemToBuy.getItem().equals(Items.EMERALD)) {
            this.secondItemToBuy.setCount((int)Math.min(this.secondItemToBuy.getCount() * 1.25F, 64));
        }

        if(this.itemToSell.getItem().equals(Items.EMERALD)) {
            this.itemToSell.setCount((int)Math.max(this.itemToSell.getCount() * 0.8F, 1));
        }
    }

    @Override
    @Unique
    public void rlmixins$denyXP(){
        this.rewardsExp = false;
    }
}