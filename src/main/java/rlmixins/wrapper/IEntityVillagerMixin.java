package rlmixins.wrapper;

import net.minecraft.nbt.NBTTagCompound;

public interface IEntityVillagerMixin {
    void rlmixins$setTradesFromNBT(NBTTagCompound compound);
}