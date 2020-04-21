package com.olliepugh.no2tools.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import com.olliepugh.no2tools.Main;

public class PickUpListener implements Listener{

	public PickUpListener() {
		Bukkit.getPluginManager().registerEvents(this, Main.getPlugin()); // tell the plugin to listen for this event
	}

	@EventHandler (priority = EventPriority.HIGH)
	public void onPickup(EntityPickupItemEvent event) {
		
		if (Main.getPlugin().getConfig().getBoolean("enabled")) {
			if (!(event.getEntity() instanceof Player)) { // if it was a player that picked the item up
				return;
			}
			
			if  (!Main.getSingleItems().contains(event.getItem().getItemStack().getType())) { // if the item being picked up is something that must be one at a time
				return;
			}
			
			Player player = (Player) event.getEntity();
			
			for (ItemStack item : player.getInventory()) {
				if (item != null) {
					if (Main.singleItems.contains(item.getType())) {
						event.setCancelled(true); // the user already has a tool therefore they can not pick up another tool
						return;
					}
				}
			}
			event.setCancelled(false);
			
		}
	}	
}
