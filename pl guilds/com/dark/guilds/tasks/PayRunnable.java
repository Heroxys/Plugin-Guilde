/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.milkbowl.vault.economy.Economy
 *  net.milkbowl.vault.economy.EconomyResponse
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.scheduler.BukkitRunnable
 */
package com.dark.guilds.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.GuildPlayer;

import net.milkbowl.vault.economy.EconomyResponse;

public class PayRunnable extends BukkitRunnable {
	Guilds guilds;

	public PayRunnable(Guilds guilds) {
		this.guilds = guilds;
	}

	public void run() {
		block13: for (Guild guild : this.guilds.getGuilds()) {
			EconomyResponse rp;
			switch (guild.getLevel()) {
			case 1: {
				for (GuildPlayer player : guild.getGuildMembers()) {
					if (guild.isCreator(player)) {
						rp = Guilds.econ.depositPlayer(player.getPlayer(), 10.0);
						if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
							continue;
						player.getPlayer().getPlayer().sendMessage(
								"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
						continue;
					}
					rp = Guilds.econ.depositPlayer(player.getPlayer(), 10.0);
					if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
						continue;
					player.getPlayer().getPlayer().sendMessage(
							"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
				}
				continue block13;
			}
			case 2: {
				for (GuildPlayer player : guild.getGuildMembers()) {
					if (guild.isCreator(player)) {
						rp = Guilds.econ.depositPlayer(player.getPlayer(), 50.0);
						if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
							continue;
						player.getPlayer().getPlayer().sendMessage(
								"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
						continue;
					}
					rp = Guilds.econ.depositPlayer(player.getPlayer(), 30.0);
					if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
						continue;
					player.getPlayer().getPlayer().sendMessage(
							"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
				}
				continue block13;
			}
			case 3: {
				for (GuildPlayer player : guild.getGuildMembers()) {
					if (guild.isCreator(player)) {
						rp = Guilds.econ.depositPlayer(player.getPlayer(), 70.0);
						if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
							continue;
						player.getPlayer().getPlayer().sendMessage(
								"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
						continue;
					}
					rp = Guilds.econ.depositPlayer(player.getPlayer(), 50.0);
					if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
						continue;
					player.getPlayer().getPlayer().sendMessage(
							"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
				}
				continue block13;
			}
			case 4: {
				for (GuildPlayer player : guild.getGuildMembers()) {
					if (guild.isCreator(player)) {
						rp = Guilds.econ.depositPlayer(player.getPlayer(), 100.0);
						if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
							continue;
						player.getPlayer().getPlayer().sendMessage(
								"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
						continue;
					}
					rp = Guilds.econ.depositPlayer(player.getPlayer(), 70.0);
					if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
						continue;
					player.getPlayer().getPlayer().sendMessage(
							"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
				}
				continue block13;
			}
			case 5: {
				for (GuildPlayer player : guild.getGuildMembers()) {
					if (guild.isCreator(player)) {
						rp = Guilds.econ.depositPlayer(player.getPlayer(), 130.0);
						if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
							continue;
						player.getPlayer().getPlayer().sendMessage(
								"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
						continue;
					}
					rp = Guilds.econ.depositPlayer(player.getPlayer(), 100.0);
					if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
						continue;
					player.getPlayer().getPlayer().sendMessage(
							"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
				}
				continue block13;
			}
			case 6: {
				for (GuildPlayer player : guild.getGuildMembers()) {
					if (guild.isCreator(player)) {
						rp = Guilds.econ.depositPlayer(player.getPlayer(), 160.0);
						if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
							continue;
						player.getPlayer().getPlayer().sendMessage(
								"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
						continue;
					}
					rp = Guilds.econ.depositPlayer(player.getPlayer(), 130.0);
					if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
						continue;
					player.getPlayer().getPlayer().sendMessage(
							"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
				}
				continue block13;
			}
			case 7: {
				for (GuildPlayer player : guild.getGuildMembers()) {
					if (guild.isCreator(player)) {
						rp = Guilds.econ.depositPlayer(player.getPlayer(), 200.0);
						if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
							continue;
						player.getPlayer().getPlayer().sendMessage(
								"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
						continue;
					}
					rp = Guilds.econ.depositPlayer(player.getPlayer(), 160.0);
					if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
						continue;
					player.getPlayer().getPlayer().sendMessage(
							"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
				}
				continue block13;
			}
			case 8: {
				for (GuildPlayer player : guild.getGuildMembers()) {
					if (guild.isCreator(player)) {
						rp = Guilds.econ.depositPlayer(player.getPlayer(), 250.0);
						if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
							continue;
						player.getPlayer().getPlayer().sendMessage(
								"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
						continue;
					}
					rp = Guilds.econ.depositPlayer(player.getPlayer(), 200.0);
					if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
						continue;
					player.getPlayer().getPlayer().sendMessage(
							"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
				}
				continue block13;
			}
			case 9: {
				for (GuildPlayer player : guild.getGuildMembers()) {
					if (guild.isCreator(player)) {
						rp = Guilds.econ.depositPlayer(player.getPlayer(), 300.0);
						if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
							continue;
						player.getPlayer().getPlayer().sendMessage(
								"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
						continue;
					}
					rp = Guilds.econ.depositPlayer(player.getPlayer(), 250.0);
					if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
						continue;
					player.getPlayer().getPlayer().sendMessage(
							"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
				}
				continue block13;
			}
			case 10: {
				for (GuildPlayer player : guild.getGuildMembers()) {
					if (guild.isCreator(player)) {
						rp = Guilds.econ.depositPlayer(player.getPlayer(), 350.0);
						if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
							continue;
						player.getPlayer().getPlayer().sendMessage(
								"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
						continue;
					}
					rp = Guilds.econ.depositPlayer(player.getPlayer(), 300.0);
					if (!rp.transactionSuccess() || !player.getPlayer().isOnline())
						continue;
					player.getPlayer().getPlayer().sendMessage(
							"\u00a7c\u00a7a[\u00a7c\u00a72Guildes\u00a7c\u00a7a] \u00a7c\u00a72Vous avez recu votre salaire quotidien !");
				}
				continue block13;
			}
			}
		}
	}
}
