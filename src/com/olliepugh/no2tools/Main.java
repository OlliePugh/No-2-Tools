package com.olliepugh.no2tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import com.olliepugh.no2tools.Main;
import com.olliepugh.no2tools.commands.No2Tools;
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
		
		for (String ids : singleIds) {
			singleItems.add(Material.valueOf(ids));
		}
		
		new PickUpListener();
		new No2Tools();
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
