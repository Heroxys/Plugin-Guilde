/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.scheduler.BukkitRunnable
 */
package com.dark.guilds.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.dark.guilds.Guilds;
import com.dark.guilds.events.DamageEvent;
import com.dark.guilds.players.GuildPlayer;

import net.md_5.bungee.api.ChatColor;

public class CombatRunnable extends BukkitRunnable {
	Guilds guilds;

	public CombatRunnable(Guilds guilds) {
		this.guilds = guilds;
	}

	public void run() {

		for (Player player : DamageEvent.fighters) {
			GuildPlayer gp = guilds.getGuildPlayer(player);
			if (gp.getCombat() == 0) {
				player.sendMessage((Object) ChatColor.GREEN + "[Guild] Vous n'êtes plus en combat");
				DamageEvent.fighters.remove(player);
			} else {
				gp.setCombat(gp.getCombat() - 1);
			}
		}
	}
}
