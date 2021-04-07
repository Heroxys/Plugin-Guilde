package com.dark.guilds.commands;

import com.dark.guilds.utils.Menu;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Boutique implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (command.getName().equalsIgnoreCase("achat") && sender instanceof Player) {
			Player player = (Player) sender;
			Menu menu = new Menu(player, null, null, (OfflinePlayer) player);
			player.openInventory(menu.getMenu("boutique", player));
		}
		return false;
	}
}
