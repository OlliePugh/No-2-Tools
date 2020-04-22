package com.olliepugh.no2tools.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import com.olliepugh.no2tools.Main;
import com.olliepugh.no2tools.checkers.IsSingleItemCheck;

public class InvAddListener implements Listener {

	public InvAddListener() {
		Bukkit.getPluginManager().registerEvents(this, Main.getPlugin()); // tell the plugin to listen for this event
	}

	@EventHandler (priority = EventPriority.HIGH)
	public void onMove(InventoryClickEvent event) {
		
		if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) { // if the user has clicked outside of their inventory
			return;
		}
		
		if (Main.getPlugin().getConfig().getBoolean("enabled") && Main.getPlugin().getConfig().getBoolean("manage-inventory-transfer")) {
			if (event.getClickedInventory().getHolder() instanceof Player) { // if the player is clicking in their inventory
				if  (!Main.getSingleItems().contains(event.getCursor().getType())) { // if the item being picked up is not something that must be one at a time
					return;
				}
				
				if (!(event.getAction() == InventoryAction.PICKUP_ALL || event.getAction() == InventoryAction.PICKUP_HALF || event.getAction() == InventoryAction.PICKUP_ONE ||
						event.getAction() == InventoryAction.PICKUP_SOME || event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY)) { // if the user is not picking them out of their inventory
					
					
					event.setCancelled(!(IsSingleItemCheck.hasNoTools(event.getWhoClicked().getInventory())));
					return;
				}
			}
			else {
				if  (!Main.getSingleItems().contains(event.getCurrentItem().getType())) { // if the item being picked up is not something that must be one at a time
					return;
				}
				if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.NUMBER_KEY) { // stop a user from using their numbers or shift clicking items
					event.setCancelled(IsSingleItemCheck.isSingle(event.getWhoClicked().getInventory()));
					return;
				}
			}
		}
	}	
}
