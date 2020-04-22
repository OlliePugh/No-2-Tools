package com.olliepugh.no2tools.checkers;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.olliepugh.no2tools.Main;

public abstract class IsSingleItemCheck {

	public static boolean isSingle(Inventory inv) {
		for (ItemStack item : inv) {
			if (item != null) {
				if (Main.singleItems.contains(item.getType())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean hasNoTools(Inventory inv) {
		int tools = 0;
		for (ItemStack item : inv) {
			if (item != null) {
				if (Main.singleItems.contains(item.getType())) {
					tools++; // a tool was found in the inv
				}
			}
		}
		if (tools < 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void dropExcessTools(Player player) {
		int tools = 0;
		for (ItemStack itemStack : player.getInventory()) {
			if (itemStack != null) {
				if (Main.getSingleItems().contains(itemStack.getType())) {
			    	if (++tools > 1) {
			    		player.getInventory().removeItem(itemStack);
			    		Item itemDropped = player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
			    		itemDropped.setPickupDelay(40);
			    	}
			    }
			}
		}
	}
}
