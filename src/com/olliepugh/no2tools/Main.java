package com.olliepugh.no2tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import com.olliepugh.no2tools.Main;
import com.olliepugh.no2tools.commands.No2Tools;
import com.olliepugh.no2tools.listeners.InvAddListener;
import com.olliepugh.no2tools.listeners.InvCloseEvent;
import com.olliepugh.no2tools.listeners.PickUpListener;

public class Main extends JavaPlugin {

	private static Main plugin;
	
	public static List<Material> singleItems;
	
	@Override
	public void onEnable() {
		plugin = this;
		loadConfig();
		List<String> singleIds = getPlugin().getConfig().getStringList("one-at-a-time");
		
		singleItems = new ArrayList<Material>();
		
		List<String> illegalItems = new ArrayList<String>();
		
		for (String ids : singleIds) {
			try {
				singleItems.add(Material.valueOf(ids));
			} catch (IllegalArgumentException e) {
				illegalItems.add(ids);
			}
		}
		
		if (!illegalItems.isEmpty()) { // where there any invalid items in the config.yml
			String message = "[No2Tools] Ignoring unknown item types found in config.yml:";
			
			for (String id : illegalItems) {
				message +=  "\n\t\t\t" + id; // display all the invalid ids
			}
			
			System.out.println(message);
		}
		
		new PickUpListener();
		new InvAddListener();
		new InvCloseEvent();
		new No2Tools();
	}
	
	public void onDisable() {
		saveConfig();
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
	public static List<Material> getSingleItems(){
		return singleItems;
	}
}
