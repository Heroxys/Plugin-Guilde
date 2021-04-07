/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package com.dark.guilds.commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;
import com.dark.guilds.utils.Menu;
import com.dark.guilds.utils.Show;

public class Admin implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("qverify")) {
			if (!sender.isOp()) {
				return false;
			}
			for (Guild guild : Guilds.getInstance().getGuilds()) {
				if (guild.getQuota() >= Guilds.getQuotaMax(guild.getLevel()))
					continue;
				sender.sendMessage("\u00a7c[ATTENTION] \u00a74La guilde \u00a7c" + guild.getName()
						+ " \u00a74n'a pas remplit son quota !");
			}
			return true;
		}
		if (command.getName().equalsIgnoreCase("jidesfrnhjbfezsjnfkjde")) {
			Player player = (Player) sender;
			Menu menu = new Menu(player, Guilds.getInstance(), null, (OfflinePlayer) player);
			player.openInventory(menu.getMenu("warp", player));
			return false;
		}
		if (command.getName().equalsIgnoreCase("qclear")) {
			if (!sender.isOp()) {
				return false;
			}
			for (Guild guild : Guilds.getInstance().getGuilds()) {
				if (guild.getQuota() < Guilds.getQuotaMax(guild.getLevel()))
					continue;
				guild.setQuota(0);
			}
			sender.sendMessage((Object) ChatColor.GREEN
					+ "Vous venez de remttre \u00c3\u00a0 z\u00e9ros toutes les guildes ayant obtenu leur quota");
			return false;
		}
		if (command.getName().contains("show")) {
			if (!(sender instanceof Player)) {
				return false;
			}

			long seconds = -1;
			if (args.length > 0) {
				if (args[0].equals("claim")) {
					if (args.length == 1) {
						seconds = -1;
					} else {
						try {
							seconds = Long.parseLong(args[1]);
						} catch (NumberFormatException e) {
							sender.sendMessage("§4Vous devez mettre un nombre ici !");
						}
					}
					if (seconds <= 0) {
						seconds = -1;
					}
					Player p = (Player) sender;
					if (Guilds.getInstance().getGuild(p).hasGuildZone()) {
						new Show(Guilds.getInstance().getGuild(p).getGuildZone(), p, seconds);
					}
					return false;
				} else if (args[0].equals("ap1")) {
					if (args.length == 1) {
						seconds = -1;
					} else {
						try {
							seconds = Long.parseLong(args[1]);
						} catch (NumberFormatException e) {
							sender.sendMessage("§4Vous devez mettre un nombre ici !");
						}
					}

					if (seconds <= 0) {
						seconds = -1;
					}
					Player p = (Player) sender;
					if (Guilds.getInstance().getGuild(p).hasAp1()) {
						new Show(Guilds.getInstance().getGuild(p).getAp1(), p, seconds);
					}
					return false;
				} else if (args[0].equals("ap2")) {
					if (args.length == 1) {
						seconds = -1;
					} else {
						try {
							seconds = Long.parseLong(args[1]);
						} catch (NumberFormatException e) {
							sender.sendMessage("§4Vous devez mettre un nombre ici !");
						}
					}

					if (seconds <= 0) {
						seconds = -1;
					}
					Player p = (Player) sender;
					if (Guilds.getInstance().getGuild(p).hasAp2()) {
						new Show(Guilds.getInstance().getGuild(p).getAp2(), p, seconds);
					}
					return false;
				} else {
					try {
						seconds = Long.parseLong(args[0]);
					} catch (NumberFormatException e) {
						sender.sendMessage("§4Vous devez mettre un nombre ici, ou ap1, ou ap2, ou claim !");
					}

					if (seconds <= 0) {
						seconds = -1;
					}
					Player p = (Player) sender;
					if (Guilds.getInstance().getGuild(p).hasGuildZone()) {
						new Show(Guilds.getInstance().getGuild(p).getGuildZone(), p, seconds);
					}
					if (Guilds.getInstance().getGuild(p).hasAp1()) {
						new Show(Guilds.getInstance().getGuild(p).getAp1(), p, seconds);
					}
					if (Guilds.getInstance().getGuild(p).hasAp2()) {
						new Show(Guilds.getInstance().getGuild(p).getAp2(), p, seconds);
					}
				}
			} else {
				seconds = -1;
				Player p = (Player) sender;
				if (Guilds.getInstance().getGuild(p).hasGuildZone()) {
					new Show(Guilds.getInstance().getGuild(p).getGuildZone(), p, seconds);
				}
				if (Guilds.getInstance().getGuild(p).hasAp1()) {
					new Show(Guilds.getInstance().getGuild(p).getAp1(), p, seconds);
				}
				if (Guilds.getInstance().getGuild(p).hasAp2()) {
					new Show(Guilds.getInstance().getGuild(p).getAp2(), p, seconds);
				}
			}
			return false;
		}
		if (command.getName().equalsIgnoreCase("gspy")) {
			if (!(sender instanceof Player)) {
				return false;
			}
			Player player = (Player) sender;
			if (!player.isOp()) {
				return false;
			}
			if (args.length == 0) {
				player.sendMessage("\u00a74Mauvais usage: \u00a7c/gspy <all / nom de la guilde / disable>");
				return false;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("all")) {
					Guilds.getInstance().gspy.put(player, "all");
					player.sendMessage(
							"\u00a7a[\u00a72GSpy\u00a7a] \u00a72Vous venez d'activer l'espionnage des chats de toutes les guildes");
					return true;
				}
				if (args[0].equalsIgnoreCase("disable")) {
					Guilds.getInstance().gspy.remove((Object) player);
					player.sendMessage(
							"\u00a7a[\u00a72GSpy\u00a7a] \u00a72Vous venez de d\u00e9sactiver l'espionnage des chats des guildes");
					return true;
				}
				Guild guild = Guilds.getInstance().getGuildByName(args[0]);
				if (guild == null) {
					player.sendMessage("\u00a7c[\u00a74GSpy\u00a7c] \u00a74Cette guilde n'existe pas");
					return false;
				}
				Guilds.getInstance().gspy.put(player, guild.getName());
				player.sendMessage(
						"\u00a7a[\u00a72GSpy\u00a7a] \u00a72Vous venez d'activer l'espionnage des chats de la guilde \u00a7a"
								+ guild.getName());
				return true;
			}
			StringBuilder sb = new StringBuilder();
			int i = 0;
			while (i < args.length) {
				if (i != args.length - 1) {
					sb.append(String.valueOf(args[i]) + " ");
				} else {
					sb.append(args[i]);
				}
				++i;
			}
			Guild guild = Guilds.getInstance().getGuildByName(sb.toString());
			if (guild == null) {
				player.sendMessage("\u00a7c[\u00a74GSpy\u00a7c] \u00a74Cette guilde n'existe pas");
				return false;
			}
			Guilds.getInstance().gspy.put(player, guild.getName());
			player.sendMessage(
					"\u00a7a[\u00a72GSpy\u00a7a] \u00a72Vous venez d'activer l'espionnage des chats de la guilde \u00a7a"
							+ guild.getName());
			return true;
		}
		return false;
	}
}
