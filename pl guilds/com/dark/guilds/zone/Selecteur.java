/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  com.sk89q.worldguard.bukkit.WorldGuardPlugin
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.event.player.PlayerMoveEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.ItemMeta
 */
package com.dark.guilds.zone;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.GuildPermission;
import com.dark.guilds.players.GuildPlayer;

public class Selecteur implements Listener {
	public Guilds guilds;
	public static ArrayList<PlayerSelection> PlayerSelections = new ArrayList<PlayerSelection>();
	public static ArrayList<Player> Selectionneurs = new ArrayList<Player>();
	public static ArrayList<Player> SelectionneursAP1 = new ArrayList<Player>();
	public static ArrayList<Player> SelectionneursAP2 = new ArrayList<Player>();

	public Selecteur(Guilds guilds) {
		this.guilds = guilds;
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onClick(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() != Material.GOLD_AXE) {
			return;
		}
		if (!e.getItem().hasItemMeta()) {
			return;
		}
		if (!e.getItem().getItemMeta().hasDisplayName()) {
			return;
		}
		if (!e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("\u00a78Outil de claim")) {
			return;
		}
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK) {
			return;
		}
		e.setCancelled(true);
		Player p = e.getPlayer();
		GuildPlayer gP = this.guilds.getGuildPlayer((OfflinePlayer) p);
		if (!Guilds.getInstance().wgp.canBuild(p, e.getClickedBlock().getLocation())) {
			p.sendMessage((Object) ChatColor.RED + "Ce block est prot\u00e9g\u00e9 par le serveur");
			return;
		}
		if (!Guilds.getInstance().hasGuild(p)) {
			return;
		}
		for (Guild g : Guilds.getInstance().getGuilds()) {
			if (!g.hasGuildZone()
					|| !(g.getGuildZone().isInClaimable(e.getClickedBlock().getLocation()) & g != gP.getGuild()))
				continue;
			p.sendMessage((Object) ChatColor.RED
					+ "Ce block est dans une zone de s\u00e9curit\u00e9 d'une zone de guilde \u00e9nemie");
			return;
		}
		if (!gP.hasPermission(GuildPermission.CHANGE_CLAIM)) {
			p.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas les permissions de changer le claim");
			p.getInventory().getItemInHand().setType(Material.AIR);
			Selectionneurs.remove((Object) p);
			return;
		}
		for (Guild g : Guilds.getInstance().getGuilds()) {
			GuildZone gz = g.getGuildZone();
			if (gz == null || !gz.isInto(e.getClickedBlock().getLocation()) || g == gP.getGuild())
				continue;
			p.sendMessage(
					"\u00a7c[\u00a74Claims\u00a7c] \u00a74Votre s\u00e9l\u00e9ction contient des blocs s\u00e9curis\u00e9s par une autre guilde !");
			return;
		}
		
		if(SelectionneursAP1.contains(p)) {
			if(!gP.getGuild().getGuildZone().isInClaimable(e.getClickedBlock().getLocation())) {
				p.sendMessage("§4Ce bloc n'est pas dans la zone de votre guilde !");
				return;
			}
		}
		
		if(SelectionneursAP2.contains(p)) {
			if(!gP.getGuild().getGuildZone().isInClaimable(e.getClickedBlock().getLocation())) {
				p.sendMessage("§4Ce bloc n'est pas dans la zone de votre guilde !");
				return;
			}
		}
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			p.sendMessage((Object) ChatColor.GREEN + "Block 1 s\u00e9l\u00e9ctionn\u00e9");
		} else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			p.sendMessage((Object) ChatColor.GREEN + "Block 2 s\u00e9l\u00e9ctionn\u00e9");
		}
		if (PlayerSelections.size() != 0) {
			for (PlayerSelection pls : PlayerSelections) {
				if (pls.getP() != p)
					continue;
				if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					pls.setLoc1(e.getClickedBlock().getLocation());
				} else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
					pls.setLoc2(e.getClickedBlock().getLocation());
				}
				return;
			}
		}
		PlayerSelection pS = null;
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			pS = new PlayerSelection(p, e.getClickedBlock().getLocation(), null);
		} else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			pS = new PlayerSelection(p, null, e.getClickedBlock().getLocation());
		}
		PlayerSelections.add(pS);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		for (Guild g : Guilds.getInstance().getGuilds()) {
			if (g.hasGuildZone() && g.getGuildZone().isInto(e.getTo()) && !g.getGuildZone().isInto(e.getFrom())) {
				p.sendMessage((Object) ChatColor.YELLOW + " Vous entrez dans la zone de guilde de "
						+ (Object) ChatColor.GOLD + g.getName());
			}
			if (g.hasAp1() && g.getAp1().isInto(e.getTo()) && !g.getAp1().isInto(e.getFrom())) {
				p.sendMessage((Object) ChatColor.YELLOW + " Vous entrez dans l'avant poste 1 de la guilde "
						+ (Object) ChatColor.GOLD + g.getName());
			}
			if (!g.hasAp2() || !g.getAp2().isInto(e.getTo()) || g.getAp2().isInto(e.getFrom()))
				continue;
			p.sendMessage((Object) ChatColor.YELLOW + " Vous entrez dans l'avant poste 2 de la guilde "
					+ (Object) ChatColor.GOLD + g.getName());
		}
	}
}
