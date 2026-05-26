package rlmixins.wrapper;

import ichttt.mods.firstaid.api.CapabilityExtendedHealthSystem;
import ichttt.mods.firstaid.api.damagesystem.AbstractPlayerDamageModel;
import ichttt.mods.firstaid.common.DataManagerWrapper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.EntityDataManager;

import java.util.Objects;

public class FirstAidWrapper {

	public static void toggleTracking(EntityDataManager dataManager, boolean track) {
		((DataManagerWrapper) dataManager).toggleTracking(track);
	}

	public static void copyPlayerDamageModel(EntityPlayerMP source, EntityPlayerMP target) {
		AbstractPlayerDamageModel oldCap = source.getCapability(CapabilityExtendedHealthSystem.INSTANCE, null);
		AbstractPlayerDamageModel newCap = target.getCapability(CapabilityExtendedHealthSystem.INSTANCE, null);
		Objects.requireNonNull(newCap).deserializeNBT(Objects.requireNonNull(oldCap).serializeNBT());
	}
}
