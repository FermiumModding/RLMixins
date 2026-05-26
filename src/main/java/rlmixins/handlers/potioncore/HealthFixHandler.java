package rlmixins.handlers.potioncore;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HealthFixHandler {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerClone(PlayerEvent.Clone event) {
		if(event.isWasDeath()) {
			return;
		}
		NBTTagCompound old = event.getOriginal().getEntityData();
		if(old.hasKey("Potion Core - Health Fix")) {
			float oldHealth = old.getFloat("Potion Core - Health Fix");
			event.getEntityPlayer().getEntityData().setFloat("Potion Core - Health Fix", oldHealth);
		}
	}
}
