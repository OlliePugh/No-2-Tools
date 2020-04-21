package com.olliepugh.no2tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.olliepugh.no2tools.Main;

public class No2Tools implements CommandExecutor {
	
	public No2Tools() {
		Main.getPlugin().getCommand("no2tools").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {;
		boolean toggleType;
		
		if (args.length == 0) { // if on player was specified
			return false;
		}
		
		if (args[0].equalsIgnoreCase("enable")) {
			toggleType = true;
		}
		else if (args[0].equalsIgnoreCase("disable")) {
			toggleType = false;
		}
		else {
			return false;
		}
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("no2tools.toggle")) { // if the player has permission
				toggleEnable(toggleType);
				return true;
			}
		}
		else { // it is from the console or a command block
			toggleEnable(toggleType);
			return true;
		}
		return false;
	}
	
	public void toggleEnable(boolean state) {
		Main.getPlugin().getConfig().set("enabled", state);
		if (state) {
			Bukkit.broadcastMessage("No 2 Tools is now enabled");
		}
		else {
			Bukkit.broadcastMessage("No 2 Tools has been disabled");
		}
	}
}
