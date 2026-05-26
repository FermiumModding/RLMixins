package rlmixins.handlers.firstaid;

import ichttt.mods.firstaid.FirstAid;
import ichttt.mods.firstaid.api.CapabilityExtendedHealthSystem;
import ichttt.mods.firstaid.api.damagesystem.AbstractPlayerDamageModel;
import ichttt.mods.firstaid.common.network.MessageSyncDamageModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

/**
 * Fix by XXMA16
 */
public class EndExitHandler {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerClone(PlayerEvent.Clone event) {
		if(event.isWasDeath()) {
			return;
		}
		EntityPlayer player = event.getEntityPlayer();
		if(!player.world.isRemote && player instanceof EntityPlayerMP) {
			// damage model was already copied in the mixin, firing the event from there is too early
			AbstractPlayerDamageModel cap = player.getCapability(CapabilityExtendedHealthSystem.INSTANCE, null);
			FirstAid.NETWORKING.sendTo(new MessageSyncDamageModel(Objects.requireNonNull(cap), true), (EntityPlayerMP)player);
		}
	}
}
