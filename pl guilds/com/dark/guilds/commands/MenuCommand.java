/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 */
package com.dark.guilds.commands;

import com.dark.guilds.Guilds;
import com.dark.guilds.utils.Menu;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand
implements CommandExecutor {
    Guilds guilds;

    public MenuCommand(Guilds guilds) {
        this.guilds = guilds;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("menu")) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                if (this.guilds.hasGuild(player)) {
                    Menu menu = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer)player);
                    player.openInventory(menu.getMenu("menu", player));
                } else {
                    Menu menu = new Menu(player, this.guilds, null, (OfflinePlayer)player);
                    player.openInventory(menu.getMenu("menu", player));
                }
                return true;
            }
            return false;
        }
        return false;
    }
}

