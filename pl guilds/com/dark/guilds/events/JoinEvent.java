/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitTask
 */
package com.dark.guilds.events;

import com.dark.guilds.Guilds;
import com.dark.guilds.utils.Utils;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class JoinEvent implements Listener {
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		if (Guilds.getInstance().levelUpReward.contains((Object) e.getPlayer())) {
			Bukkit.getScheduler().runTaskLater((Plugin) Guilds.getInstance(), new Runnable() {

				@Override
				public void run() {
					e.getPlayer().sendMessage(
							"\u00a76Votre guilde est pass\u00e9e \u00c3\u00a0 un niveau sup\u00e9rieur, veuillez choisir l'am\u00e9lioration que vous d\u00e9sirez !");
					Inventory rew = Bukkit.createInventory(null, (int) 27, (String) "\u00a78R\u00e9compense de niveau");
					int i = 0;
					while (i < 27) {
						rew.setItem(i, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", null));
						++i;
					}
					rew.setItem(12, Utils.getItem(Material.WOOD_SPADE, (byte) 0,
							"\u00a78Augmenter son claim de 20x20 blocs", null));
					rew.setItem(14, Utils.getItem(Material.SKULL_ITEM, (byte) 3,
							"\u00a78Augmenter sa capacit\u00e9 de 5 joueurs", null));
					e.getPlayer().openInventory(rew);
					Guilds.getInstance().levelUpReward.remove((Object) e.getPlayer());
				}
			}, 20L);
		}
	}

}
