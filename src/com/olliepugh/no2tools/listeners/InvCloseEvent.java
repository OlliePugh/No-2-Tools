package com.olliepugh.no2tools.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.olliepugh.no2tools.Main;
import com.olliepugh.no2tools.checkers.IsSingleItemCheck;

public class InvCloseEvent implements Listener {

	public InvCloseEvent() {
		Bukkit.getPluginManager().registerEvents(this, Main.getPlugin()); // tell the plugin to listen for this event
	}

	@EventHandler (priority = EventPriority.HIGH)
	public void onMove(InventoryCloseEvent event) {
		
		if (event.getPlayer() instanceof Player) {
			Player player = (Player) event.getPlayer();
			
			if (Main.getPlugin().getConfig().getBoolean("enabled") && Main.getPlugin().getConfig().getBoolean("manage-inventory-transfer")) {
				IsSingleItemCheck.dropExcessTools(player);
			}
		}
	}	
}
