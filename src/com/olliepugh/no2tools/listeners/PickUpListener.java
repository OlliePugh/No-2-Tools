package com.olliepugh.no2tools.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.Inventory;

import com.olliepugh.no2tools.Main;
import com.olliepugh.no2tools.checkers.IsSingleItemCheck;

public class PickUpListener implements Listener {

	public PickUpListener() {
		Bukkit.getPluginManager().registerEvents(this, Main.getPlugin()); // tell the plugin to listen for this event
	}

	@EventHandler (priority = EventPriority.HIGH)
	public void onPickup(EntityPickupItemEvent event) {
		
		if (Main.getPlugin().getConfig().getBoolean("enabled") && Main.getPlugin().getConfig().getBoolean("manage-pick-up")) {
			if (!(event.getEntity() instanceof Player)) { // if it was not a player that picked the item up
				return;
			}
			
			if  (!Main.getSingleItems().contains(event.getItem().getItemStack().getType())) { // if the item being picked up is not something that must be one at a time
				return;
			}
			
			Inventory inv = ((Player)event.getEntity()).getInventory();
			
			event.setCancelled(IsSingleItemCheck.isSingle(inv));
			
		}
	}	
}
