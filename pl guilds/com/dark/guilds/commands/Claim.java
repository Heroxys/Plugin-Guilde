/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 */
package com.dark.guilds.commands;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.GuildPermission;
import com.dark.guilds.players.GuildPlayer;
import com.dark.guilds.zone.APZone;
import com.dark.guilds.zone.GuildZone;
import com.dark.guilds.zone.PlayerSelection;
import com.dark.guilds.zone.Selecteur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Claim implements CommandExecutor {
	Guilds guilds;

	public Claim(Guilds guilds) {
		this.guilds = guilds;
	}

	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		if (command.getName().equalsIgnoreCase("ap")) {
			Player p = (Player) sender;
			GuildPlayer gP = Guilds.getInstance().getGuildPlayer((OfflinePlayer) p);
			if (Selecteur.SelectionneursAP1.contains((Object) p)) {
				if (gP.getGuild().hasAp1()) {
					for (PlayerSelection pls : Selecteur.PlayerSelections) {
						if (pls.getP() != p)
							continue;
						APZone gZ = new APZone(pls.getLoc1(), pls.getLoc2());
						boolean isInto = false;
						for (Guild gls : Guilds.getInstance().getGuilds()) {
							if (!gls.hasGuildZone() || gls == gP.getGuild())
								continue;
							GuildZone gz1 = gls.getGuildZone();
							if (gz1.isInSecureZone(gZ.getLoc1()) || gz1.isInSecureZone(gZ.getLoc2())) {
								isInto = true;
							}
							if (gz1.getxMax() >= gZ.getxMax() || gz1.getxMin() <= gZ.getxMin()
									|| gz1.getzMax() >= gZ.getzMax() || gz1.getzMin() <= gZ.getzMin())
								continue;
							isInto = true;
						}
						if (isInto) {
							p.sendMessage((Object) ChatColor.RED
									+ "Vous avez une partie de la zone dans une zone d'une autre guilde");
							return false;
						}
						if (!gP.getGuild().getGuildZone().isInSecureZone(gZ.getLoc1())
								|| !gP.getGuild().getGuildZone().isInSecureZone(gZ.getLoc2())) {
							p.sendMessage((Object) ChatColor.DARK_RED
									+ "Votre avant-poste doit \u00eatre dans la zone de protection de votre zone de guilde");
							return false;
						}
						if ((gZ.getxMax() - gZ.getxMin()) * (gZ.getzMax() - gZ.getzMin()) > 900) {
							p.sendMessage((Object) ChatColor.DARK_RED
									+ "La zone s\u00e9l\u00e9ctionn\u00e9e est trop grande");
							return false;
						}
						gP.getGuild().setAp1(gZ);
						p.sendMessage((Object) ChatColor.DARK_GREEN
								+ "Votre avant poste 1 \u00e0\u00a0 \u00e9t\u00e9 d\u00e9fini");
						Selecteur.SelectionneursAP1.remove((Object) p);
						p.getInventory().removeItem(new ItemStack[] { p.getInventory().getItemInHand() });
						p.updateInventory();
						return false;
					}
					p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas s\u00e9l\u00e9ctionn\u00e9 de zone");
					return false;
				}
				if (!gP.hasPermission(GuildPermission.CHANGE_CLAIM)) {
					p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas les permissions requises");
					return false;
				}
				for (PlayerSelection pls : Selecteur.PlayerSelections) {
					if (pls.getP() != p)
						continue;
					APZone gZ = new APZone(pls.getLoc1(), pls.getLoc2());
					if ((gZ.getxMax() - gZ.getxMin()) * (gZ.getzMax() - gZ.getzMin()) > 900) {
						p.sendMessage(
								(Object) ChatColor.DARK_RED + "La zone s\u00e9l\u00e9ctionn\u00e9e est trop grande");
						return false;
					}
					gP.getGuild().setAp1(gZ);
					p.sendMessage((Object) ChatColor.DARK_GREEN
							+ "Votre avant poste 1 \u00e0\u00a0 \u00e9t\u00e9 redimentionn\u00e9e");
					Selecteur.SelectionneursAP1.remove((Object) p);
					p.getInventory().removeItem(new ItemStack[] { p.getInventory().getItemInHand() });
					p.updateInventory();
					return false;
				}
				p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas s\u00e9l\u00e9ctionn\u00e9 de zone");
				return false;
			}
			if (Selecteur.SelectionneursAP2.contains((Object) p)) {
				if (gP.getGuild().hasAp2()) {
					for (PlayerSelection pls : Selecteur.PlayerSelections) {
						if (pls.getP() != p)
							continue;
						APZone gZ = new APZone(pls.getLoc1(), pls.getLoc2());
						boolean isInto = false;
						for (Guild gls : Guilds.getInstance().getGuilds()) {
							if (!gls.hasGuildZone() || gls == gP.getGuild())
								continue;
							GuildZone gz1 = gls.getGuildZone();
							if (gz1.isInSecureZone(gZ.getLoc1()) || gz1.isInSecureZone(gZ.getLoc2())) {
								isInto = true;
							}
							if (gz1.getxMax() >= gZ.getxMax() || gz1.getxMin() <= gZ.getxMin()
									|| gz1.getzMax() >= gZ.getzMax() || gz1.getzMin() <= gZ.getzMin())
								continue;
							isInto = true;
						}
						if (isInto) {
							p.sendMessage((Object) ChatColor.RED
									+ "Vous avez une partie de la zone dans une zone d'une autre guilde");
							return false;
						}
						if (!gP.getGuild().getGuildZone().isInSecureZone(gZ.getLoc1())
								|| !gP.getGuild().getGuildZone().isInSecureZone(gZ.getLoc2())) {
							p.sendMessage((Object) ChatColor.DARK_RED
									+ "Votre avant-poste doit \u00eatre dans la zone de protection de votre zone de guilde");
							return false;
						}
						if ((gZ.getxMax() - gZ.getxMin()) * (gZ.getzMax() - gZ.getzMin()) > 900) {
							p.sendMessage((Object) ChatColor.DARK_RED
									+ "La zone s\u00e9l\u00e9ctionn\u00e9e est trop grande");
							return false;
						}
						gP.getGuild().setAp2(gZ);
						p.sendMessage((Object) ChatColor.DARK_GREEN + "Avant poste d\u00e9fini !");
						Selecteur.SelectionneursAP2.remove((Object) p);
						p.getInventory().getItemInHand().setType(Material.AIR);
						p.updateInventory();
						return false;
					}
					p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas s\u00e9l\u00e9ctionn\u00e9 de zone");
					return false;
				}
				if (!gP.hasPermission(GuildPermission.CHANGE_CLAIM)) {
					p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas les permissions requises");
					return false;
				}
				for (PlayerSelection pls : Selecteur.PlayerSelections) {
					if (pls.getP() != p)
						continue;
					APZone gZ = new APZone(pls.getLoc1(), pls.getLoc2());
					if ((gZ.getxMax() - gZ.getxMin()) * (gZ.getzMax() - gZ.getzMin()) > 900) {
						p.sendMessage(
								(Object) ChatColor.DARK_RED + "La zone s\u00e9l\u00e9ctionn\u00e9e est trop grande");
						return false;
					}
					p.getItemInHand().setType(Material.AIR);
					if (Selecteur.Selectionneurs.contains((Object) p)) {
						Selecteur.Selectionneurs.remove((Object) p);
					}
					gP.getGuild().setAp2(gZ);
					p.sendMessage((Object) ChatColor.DARK_GREEN
							+ "Votre avant poste 2 \u00e0 \u00e9t\u00e9 redimentionn\u00e9e");
					Selecteur.SelectionneursAP2.remove((Object) p);
					p.getInventory().getItemInHand().setType(Material.AIR);
					p.updateInventory();
					return false;
				}
				p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas s\u00e9l\u00e9ctionn\u00e9 de zone");
				return false;
			}
			p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'\u00eates pas en s\u00e9l\u00e9ction");
			return false;
		}
		
		if (command.getName().equalsIgnoreCase("claim")) {
			if (!(sender instanceof Player)) {
				return false;
			}
			Player p = (Player) sender;
			GuildPlayer gP = Guilds.getInstance().getGuildPlayer((OfflinePlayer) p);
			if (!Selecteur.Selectionneurs.contains((Object) p)) {
				p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'\u00eates pas en s\u00e9l\u00e9ction");
				return false;
			}
			if (!gP.getGuild().hasGuildZone()) {
				for (PlayerSelection pls : Selecteur.PlayerSelections) {
					if (pls.getP() != p)
						continue;
					GuildZone gZ = new GuildZone(pls.getLoc1(), pls.getLoc2());
					boolean isInto = false;
					for (Guild gls : Guilds.getInstance().getGuilds()) {
						if (!gls.hasGuildZone() || gls == gP.getGuild())
							continue;
						GuildZone gz1 = gls.getGuildZone();
						if (gz1.isInto(gZ.getLoc1()) || gz1.isInto(gZ.getLoc2())) {
							isInto = true;
						}
						if (gz1.getxMax() >= gZ.getxMax() || gz1.getxMin() <= gZ.getxMin()
								|| gz1.getzMax() >= gZ.getzMax() || gz1.getzMin() <= gZ.getzMin())
							continue;
						isInto = true;
					}
					if (isInto) {
						p.sendMessage((Object) ChatColor.RED
								+ "Vous avez une partie de la zone dans une zone d'une autre guilde");
						return false;
					}
					if ((gZ.getxMax() - gZ.getxMin()) * (gZ.getzMax() - gZ.getzMin()) > gP.getGuild().getFieldSide()
							* gP.getGuild().getFieldSide()) {
						p.sendMessage(
								(Object) ChatColor.DARK_RED + "La zone s\u00e9l\u00e9ctionn\u00e9e est trop grande");
						return false;
					}
					gP.getGuild().setGuildZone(gZ);
					p.sendMessage((Object) ChatColor.DARK_GREEN + "Zone de guilde d\u00e9finie !");
					Selecteur.Selectionneurs.remove((Object) p);
					p.getInventory().removeItem(new ItemStack[] { p.getInventory().getItemInHand() });
					p.updateInventory();
					return false;
				}
				p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas s\u00e9l\u00e9ctionn\u00e9 de zone");
				return false;
			}
			if (!gP.hasPermission(GuildPermission.CHANGE_CLAIM)) {
				p.sendMessage((Object) ChatColor.DARK_RED + "VOus n'avez pas les permissions requises");
				return false;
			}
			for (PlayerSelection pls : Selecteur.PlayerSelections) {
				if (pls.getP() != p)
					continue;
				GuildZone gZ = new GuildZone(pls.getLoc1(), pls.getLoc2());
				if ((gZ.getxMax() - gZ.getxMin()) * (gZ.getzMax() - gZ.getzMin()) > gP.getGuild().getFieldSide()
						* gP.getGuild().getFieldSide()) {
					p.sendMessage((Object) ChatColor.DARK_RED + "La zone s\u00e9l\u00e9ctionn\u00e9e est trop grande");
					return false;
				}
				gP.getGuild().setGuildZone(gZ);
				p.sendMessage((Object) ChatColor.DARK_GREEN
						+ "Votre zonne de guilde \u00e0 \u00e9t\u00e9 redimentionn\u00e9e");
				Selecteur.Selectionneurs.remove((Object) p);
				p.getInventory().removeItem(new ItemStack[] { p.getInventory().getItemInHand() });
				p.updateInventory();
				return false;
			}
			p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas s\u00e9l\u00e9ctionn\u00e9 de zone");
			return false;
		}
		return false;
	}
}
