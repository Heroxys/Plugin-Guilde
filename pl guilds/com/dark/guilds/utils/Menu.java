package com.dark.guilds.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.dark.guilds.Guild;
import com.dark.guilds.GuildStatus;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.GuildPermission;
import com.dark.guilds.players.GuildPlayer;

public class Menu {
	Player player;
	Guilds core;
	Guild guild;
	OfflinePlayer target;

	public Menu(Player player, Guilds guilds, Guild guild, OfflinePlayer target) {
		this.player = player;
		this.core = guilds;
		this.guild = guild;
		this.target = target;
	}

	@SuppressWarnings("deprecation")
	public Inventory getMenu(String type, Player p) {
		String[] args = type.split(" ");
		String var4;
		Inventory aGuild;
		Integer size;
		switch ((var4 = args[0]).hashCode()) {
		case -2109606044:
			if (var4.equals("boutique")) {
				Inventory boutique = Bukkit.createInventory((InventoryHolder) null, 27, ChatColor.YELLOW + "Boutique");
				ArrayList<String> lore = new ArrayList<String>();

				// Rangée 1
				lore.add("1 000£");
				boutique.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				boutique.setItem(1, Utils.getItem(Material.IRON_SWORD, (byte) 0, "Kit Barbare", lore));
				// boutique.setItem(2, Utils.getItem(Material.AIR, (byte) 0, " ", (List<String>)
				// null));
				boutique.setItem(3, Utils.getItem(Material.BOW, (byte) 0, "Kit Archer", lore));
				boutique.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				boutique.setItem(5, Utils.getItem(Material.IRON_PICKAXE, (byte) 0, "Kit Mineur", lore));
				// boutique.setItem(6, Utils.getItem(Material.AIR, (byte) 0, " ", (List<String>)
				// null));
				boutique.setItem(7, Utils.getItem(Material.IRON_HOE, (byte) 0, "Kit Fermier", lore));
				boutique.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lore.clear();

				// Rangée 2
				lore.add("10 000£");
				boutique.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				// boutique.setItem(10, Utils.getItem(Material.AIR, (byte) 0, " ",
				// (List<String>) null));
				boutique.setItem(11,
						Utils.getItem(Material.DIAMOND_SWORD, (byte) 0, ChatColor.AQUA + "Kit Guerrier", lore));
				boutique.setItem(12, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				boutique.setItem(13, Utils.getItem(Material.GOLDEN_APPLE, (byte) 0, "Kit Paladin", lore));
				boutique.setItem(14, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				boutique.setItem(15, Utils.getItem(Material.BRICK, (byte) 0, ChatColor.AQUA + "Kit Architecte", lore));
				// boutique.setItem(16, Utils.getItem(Material.AIR, (byte) 0, " ",
				// (List<String>) null));
				boutique.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lore.clear();

				// Rangée 3
				lore.add("30 000£");
				boutique.setItem(18, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				// boutique.setItem(19, Utils.getItem(Material.AIR, (byte) 0, " ",
				// (List<String>) null));
				boutique.setItem(20, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				boutique.setItem(21,
						Utils.getItem(Material.TOTEM, (byte) 0, ChatColor.DARK_RED + "Kit Seigneur", lore));
				// boutique.setItem(22, Utils.getItem(Material.AIR, (byte) 0, " ",
				// (List<String>) null));
				boutique.setItem(23,
						Utils.getItem(Material.DIAMOND_AXE, (byte) 0, ChatColor.DARK_RED + "Kit Viking", lore));
				boutique.setItem(24, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				// boutique.setItem(25, Utils.getItem(Material.AIR, (byte) 0, " ",
				// (List<String>) null));
				boutique.setItem(26, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lore.clear();

				return boutique;
			}
			break;

		case -1034433877:
			if (var4.equals("batailles")) {
				Inventory battle = Bukkit.createInventory((InventoryHolder) null, 9, "§7Menu des batailles !");
				battle.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				// battle.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ",
				// (List<String>) null));
				// battle.setItem(2, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ",
				// (List<String>) null));
				battle.setItem(3,
						Utils.getItem(Material.IRON_SWORD, (byte) 0, "Bataille de guilde", (List<String>) null));
				// battle.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ",
				// (List<String>) null));
				battle.setItem(5,
						Utils.getItem(Material.DIAMOND_SWORD, (byte) 0, "§4Guerre de guilde", (List<String>) null));
				// battle.setItem(6, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ",
				// (List<String>) null));
				// battle.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ",
				// (List<String>) null));
				battle.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));

				return battle;
			}
			break;

		case 1389790564:
			if (var4.equals("guildChoise")) {
				int guildsNumber = Guilds.guilds.size();
				Inventory choise = null;
				if (guildsNumber <= 9) {
					choise = Bukkit.createInventory((InventoryHolder) null, 9, "Quelle guilde choisir ?");
				}

				else if (guildsNumber <= 18) {
					choise = Bukkit.createInventory((InventoryHolder) null, 18, "Quelle guilde choisir ?");
				}

				else if (guildsNumber <= 27) {
					choise = Bukkit.createInventory((InventoryHolder) null, 27, "Quelle guilde choisir ?");
				}

				else if (guildsNumber <= 36) {
					choise = Bukkit.createInventory((InventoryHolder) null, 36, "Quelle guilde choisir ?");
				}

				else if (guildsNumber <= 45) {
					choise = Bukkit.createInventory((InventoryHolder) null, 45, "Quelle guilde choisir ?");
				}

				else if (guildsNumber <= 54) {
					choise = Bukkit.createInventory((InventoryHolder) null, 54, "Quelle guilde choisir ?");
				}

				int i = 0;
				ArrayList<String> lore = new ArrayList<String>();
				for (Guild guild : Guilds.guilds) {
					lore.add(guild.getCreator().getPlayer().getName());
					choise.setItem(i, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, guild.getName(), lore));
					lore.clear();
					i++;
				}

				return choise;
			}
			break;

		case 3641992:
			if (var4.equals("warp")) {
				Inventory warp = Bukkit.createInventory((InventoryHolder) null, 9, "Où souhaitez-vous aller ?");

				warp.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, "", (List<String>) null));
				warp.setItem(1, Utils.getItem(Material.GRASS, (byte) 0, "Monde ressources", (List<String>) null));
				warp.setItem(2, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, "", (List<String>) null));
				warp.setItem(3, Utils.getItem(Material.NETHER_BRICK, (byte) 0, "Nether", (List<String>) null));
				warp.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, "", (List<String>) null));
				warp.setItem(5, Utils.getItem(Material.ENDER_STONE, (byte) 0, "End", (List<String>) null));
				warp.setItem(6, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, "", (List<String>) null));
				warp.setItem(7, Utils.getItem(Material.IRON_SWORD, (byte) 0, "Arène", (List<String>) null));
				warp.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, "", (List<String>) null));

				return warp;
			}

			break;

		case -625374323:
			if (var4.equals("guildInviteChoise")) {
				ArrayList<Guild> guildsInviting = new ArrayList<Guild>();
				for (Guild g : Guilds.guilds) {
					if (g.getStatus().equals(GuildStatus.INVITATION) || g.getStatus().equals(GuildStatus.OPENED)) {
						guildsInviting.add(g);
					} else
						continue;
				}
				int guildsNumber = guildsInviting.size();
				Inventory choise = null;
				if (guildsNumber <= 9) {
					choise = Bukkit.createInventory((InventoryHolder) null, 9, "Quelle guilde rejoindre ?");
				}

				else if (guildsNumber <= 18) {
					choise = Bukkit.createInventory((InventoryHolder) null, 18, "Quelle guilde rejoindre ?");
				}

				else if (guildsNumber <= 27) {
					choise = Bukkit.createInventory((InventoryHolder) null, 27, "Quelle guilde rejoindre ?");
				}

				else if (guildsNumber <= 36) {
					choise = Bukkit.createInventory((InventoryHolder) null, 36, "Quelle guilde rejoindre ?");
				}

				else if (guildsNumber <= 45) {
					choise = Bukkit.createInventory((InventoryHolder) null, 45, "Quelle guilde rejoindre ?");
				}

				else if (guildsNumber <= 54) {
					choise = Bukkit.createInventory((InventoryHolder) null, 54, "Quelle guilde rejoindre ?");
				}

				int i = 0;
				int color = 15;
				ArrayList<String> lore = new ArrayList<String>();
				for (Guild guild : guildsInviting) {
					lore.add(guild.getCreator().getPlayer().getName());
					if (guild.getStatus().equals(GuildStatus.OPENED)) {
						color = 13;
					}
					if (guild.getStatus().equals(GuildStatus.INVITATION)) {
						color = 1;
					}

					choise.setItem(i, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) color, guild.getName(), lore));
					lore.clear();
					i++;
				}

				return choise;
			}
			break;

		case 99460980:
			if (var4.equals("homes")) {
				Inventory homes = Bukkit.createInventory((InventoryHolder) null, 9, "Achat des homes");
				ArrayList<String> lore = new ArrayList<String>();
				homes.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lore.add("4 000£");
				homes.setItem(3, Utils.getItem(Material.PAPER, (byte) 0, "5 homes", lore));
				lore.clear();
				lore.add("15 000£");
				homes.setItem(5, Utils.getItem(Material.PAPER, (byte) 0, "10 homes", lore));

				homes.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));

				return homes;
			}
			break;
		case 1133704324:
			if (var4.equals("permissions")) {
				Inventory permissions = Bukkit.createInventory((InventoryHolder) null, 9, "§bPermissions");
				// permissions.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15,
				// " ", (List<String>) null));
				// permissions.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15,
				// " ", (List<String>) null));
				// permissions.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15,
				// " ", (List<String>) null));
				// permissions.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15,
				// " ", (List<String>) null));
				// permissions.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15,
				// " ", (List<String>) null));
				// permissions.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15,
				// " ", (List<String>) null));
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("5 000£");
				permissions.setItem(0,
						Utils.getItem(Material.WORKBENCH, (byte) 0, ChatColor.GREEN + "Permission /craft", lore));
				lore.clear();
				lore.add("15 000£");
				permissions.setItem(2,
						Utils.getItem(Material.CAKE, (byte) 0, ChatColor.GREEN + "Permission /feed", lore));
				lore.clear();
				lore.add("50 000£");
				permissions.setItem(4,
						Utils.getItem(Material.FEATHER, (byte) 0, ChatColor.GREEN + "Permission /fly", lore));
				lore.clear();
				permissions.setItem(6, Utils.getItem(Material.DIAMOND_CHESTPLATE, (byte) 0, ChatColor.GREEN + "Kits",
						(List<String>) null));
				permissions.setItem(8,
						Utils.getItem(Material.PAPER, (byte) 0, ChatColor.GREEN + "Homes", (List<String>) null));

				return permissions;
			}
			break;
		case -1994546114:
			if (var4.equals("optionsClaim")) {
				Inventory options = Bukkit.createInventory((InventoryHolder) null, 9, "Options des claim");
				options.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				options.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				options.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				options.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				options.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				Integer maxClaim = Guilds.getInstance().getGuild(p).getMaxSize();
				if (Guilds.getInstance().getGuild(p).hasGuildZone()) {
					size = maxClaim * maxClaim - Guilds.getInstance().getGuild(p).getGuildZone().getSize();
				} else {
					size = maxClaim * maxClaim;
				}

				ArrayList<String> arrayClaim = new ArrayList<String>();
				arrayClaim.add(size.toString());
				options.setItem(2, Utils.getItem(Material.GOLD_AXE, (byte) 0, "§6Redimentionné le claim", arrayClaim));
				options.setItem(4, Utils.getItem(Material.IRON_INGOT, (byte) 0, "§eDéfinir le premier avant-poste",
						(List<String>) null));
				options.setItem(6, Utils.getItem(Material.GOLD_INGOT, (byte) 0, "§eDéfinir le second avant-poste",
						(List<String>) null));
				options.setItem(8, Utils.getItem(Material.ARROW, (byte) 0, "§eRetour", (List<String>) null));
				return options;
			}
			break;
		case -1234877742:
			if (var4.equals("guilde")) {
				if (this.core.hasGuild(this.player)) {
					aGuild = Bukkit.createInventory((InventoryHolder) null, 54, "§8Menu - Guildes");
					aGuild.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(2, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(6, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(10, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(16, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(18, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(26, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(27, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(35, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(36, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(37, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(43, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(44, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(46, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(47, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(48, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(49, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(50, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(51, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(52, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					aGuild.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
					aGuild.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
					aGuild.setItem(45, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
					aGuild.setItem(13, Utils.getItem(Material.ENDER_PEARL, (byte) 0, "§aTéléportation au spawn",
							(List<String>) null));
					aGuild.setItem(21,
							Utils.getItem(Material.IRON_SWORD, (byte) 0, "§7Menu des batailles", (List<String>) null));
					aGuild.setItem(23,
							Utils.getItem(Material.EMPTY_MAP, (byte) 0, "§7Liste des membres", (List<String>) null));
					aGuild.setItem(28, Utils.getItem(Material.ENCHANTED_BOOK, (byte) 0, "§7Cahier des Charges",
							(List<String>) null));
					aGuild.setItem(34,
							Utils.getItem(Material.COMMAND, (byte) 0, "§7Administration", (List<String>) null));
					aGuild.setItem(39,
							Utils.getItem(Material.END_CRYSTAL, (byte) 0, "§8Informations de la guilde",
									Arrays.asList(" §7Nom: §c" + this.guild.getName(),
											" §7Niveau: §c" + this.guild.getLevel(),
											" §7Expérience: §c" + this.guild.getXp())));
					aGuild.setItem(41, Utils.getItem(Material.EXP_BOTTLE, (byte) 0, "§7Quota", (List<String>) null));
					aGuild.setItem(53,
							Utils.getItem(Material.WOOD_DOOR, (byte) 0, "§7Quitter la guilde", (List<String>) null));
					return aGuild;
				}

				aGuild = Bukkit.createInventory((InventoryHolder) null, 27, "§8Menu - Guildes");
				aGuild.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(13, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(18, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(19, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(21, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(22, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(23, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(25, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(26, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(2, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				aGuild.setItem(6, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				aGuild.setItem(10, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				aGuild.setItem(12, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				aGuild.setItem(14, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				aGuild.setItem(16, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				aGuild.setItem(20, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				aGuild.setItem(24, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				aGuild.setItem(11,
						Utils.getItem(Material.BOOK, (byte) 0, "§8Postulez dans une guilde !", (List<String>) null));
				aGuild.setItem(15, Utils.getItem(Material.EMERALD, (byte) 0, "§aCréer sa propre guilde",
						Arrays.asList(" §7Prix: 50 000£")));
				return aGuild;
			}
			break;
		case -995427962:
			if (var4.equals("params")) {
				Inventory params = Bukkit.createInventory((InventoryHolder) null, 18, "§8Paramètres de la guilde");
				params.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				params.setItem(2, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				params.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				params.setItem(6, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				params.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				params.setItem(1, Utils.getItem(Material.PAPER, (byte) 0, "§8Changer la description",
						Arrays.asList("§8Description actuelle: ", "§7" + this.guild.getDescription())));
				if (this.guild.getStatus() == GuildStatus.OPENED) {
					params.setItem(3, Utils.getItem(Material.STAINED_CLAY, (byte) 13,
							"§8Statut: " + GuildStatus.OPENED.getStatus(), (List<String>) null));
				} else if (this.guild.getStatus() == GuildStatus.CLOSED) {
					params.setItem(3, Utils.getItem(Material.STAINED_CLAY, (byte) 14,
							"§8Statut: " + GuildStatus.CLOSED.getStatus(), (List<String>) null));
				} else if (this.guild.getStatus() == GuildStatus.INVITATION) {
					params.setItem(3, Utils.getItem(Material.STAINED_CLAY, (byte) 1,
							"§8Statut: " + GuildStatus.INVITATION.getStatus(), (List<String>) null));
				}

				params.setItem(5,
						Utils.getItem(Material.NAME_TAG, (byte) 0, "§8Modifier le nom de la guilde (coût: 10 000£)",
								Arrays.asList("§7Nom actuel: " + this.guild.getName())));
				params.setItem(7, Utils.getItem(Material.BANNER, (byte) 0, "§8Modifier la couleur de la team",
						Arrays.asList("§7Couleur actuelle: " + this.guild.getTag() + "Couleur")));

				for (int g = 9; g < 18; ++g) {
					params.setItem(g, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				}

				params.setItem(13, Utils.getItem(Material.ARROW, (byte) 0, "§8Retour", (List<String>) null));
				return params;
			}
			break;
		case -493567566:
			if (var4.equals("players")) {
				Inventory players = Bukkit.createInventory((InventoryHolder) null, 45,
						"§8Membres de la guilde - Admin");
				int i = 0;
				Iterator<?> var35 = this.guild.getGuildMembers().iterator();

				while (var35.hasNext()) {
					GuildPlayer pl = (GuildPlayer) var35.next();
					if (!pl.getPlayer().getUniqueId().equals(this.guild.getCreator().getPlayer().getUniqueId())) {
						players.setItem(i,
								Utils.getHead(pl.getPlayer().getName(), "§8Joueur: §c" + pl.getPlayer().getName(),
										Arrays.asList(" §8Grade: " + Utils.prettifyText(pl.getRank().toString()))));
						++i;
					}
				}

				for (int y = 36; y < 45; ++y) {
					players.setItem(y, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				}

				players.setItem(40, Utils.getItem(Material.ARROW, (byte) 0, "§8Retour", (List<String>) null));
				return players;
			}
			break;
		case -155014397:
			if (var4.equals("admin_guild")) {
				aGuild = Bukkit.createInventory((InventoryHolder) null, 18, "§8Menu d'administration");
				aGuild.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				aGuild.setItem(0,
						Utils.getItem(Material.NAME_TAG, (byte) 0, "§8Paramètres de la guilde", (List<String>) null));
				aGuild.setItem(2, Utils.getItem(Material.TRIPWIRE_HOOK, (byte) 0, "§8Gestion des permissions",
						(List<String>) null));
				aGuild.setItem(4, Utils.getItem(Material.BOOK_AND_QUILL, (byte) 0, "§8Modifier le Cahier des Charges",
						(List<String>) null));
				aGuild.setItem(6, Utils.getItem(Material.COMPASS, (byte) 0, "§8Définir le spawn de la guilde",
						(List<String>) null));
				size = 0;
				Integer max = Guilds.getInstance().getGuild(p).getMaxSize();
				if (Guilds.getInstance().getGuild(p).hasGuildZone()) {
					size = max * max - Guilds.getInstance().getGuild(p).getGuildZone().getSize();
				} else {
					size = max * max;
				}

				ArrayList<String> array = new ArrayList<String>();
				array.add(size.toString());
				if (!Guilds.getInstance().getGuildPlayer(p).getGuild().hasGuildZone()) {
					aGuild.setItem(8, Utils.getItem(Material.GOLD_AXE, (byte) 0, "§8Définir le claim", array));
				} else {
					aGuild.setItem(8,
							Utils.getItem(Material.GOLD_BLOCK, (byte) 0, "§8Option du claim", (List<String>) null));
				}

				for (int i = 9; i < 18; ++i) {
					aGuild.setItem(i, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				}

				aGuild.setItem(13, Utils.getItem(Material.ARROW, (byte) 0, "§8Retour", (List<String>) null));
				return aGuild;
			}
			break;
		case 3029737:
			if (var4.equals("book")) {
				Inventory inventory = Bukkit.createInventory((InventoryHolder) null, 27,
						"§8Gestion du Cahier des Charges");

				for (int f = 0; f < 27; ++f) {
					if (f != 13) {
						inventory.setItem(f,
								Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					}
				}

				inventory.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inventory.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inventory.setItem(26, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inventory.setItem(18, Utils.getItem(Material.ARROW, (byte) 0, "§8Retour", (List<String>) null));
				inventory.setItem(14, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 13, "§aCharger le Cahier",
						(List<String>) null));
				inventory.setItem(12,
						Utils.getHead("MHF_Question", "§8Comment §a marche ?",
								Arrays.asList("§7Mettez votre livre édité dans le slot vide a",
										"§7ma droite, puis cliquez sur la vitre verte !")));
				return inventory;
			}
			break;
		case 3291718:
			if (var4.equals("kick")) {
				Inventory kick = Bukkit.createInventory((InventoryHolder) null, 9, "kick " + args[1] + " ?");
				kick.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				kick.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				kick.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				kick.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				kick.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				kick.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				kick.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				kick.setItem(2,
						Utils.getItem(Material.WOOL, (byte) 5, ChatColor.GREEN + "Valider", (List<String>) null));
				kick.setItem(6, Utils.getItem(Material.WOOL, (byte) 14, ChatColor.RED + "Annulé", (List<String>) null));
				return kick;
			}
			break;
		case 3347807:
			if (var4.equals("menu")) {
				Inventory inv = Bukkit.createInventory((InventoryHolder) null, 54, "§4Menu - AltyCraft");
				inv.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(2, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(6, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(10, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(16, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(18, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(19, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(20, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(21, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(23, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(24, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(25, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(26, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(27, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(29, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(30, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(31, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(32, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(33, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(35, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(45, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(46, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(48, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(50, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(52, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(53, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				inv.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(12, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(14, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(22, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(36, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(37, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(38, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(39, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(40, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(41, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(42, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(43, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(44, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 3, " ", (List<String>) null));
				inv.setItem(1, Utils.getItem(Material.DIAMOND, (byte) 0, "§bPermissions", (List<String>) null));
				inv.setItem(7, Utils.getItem(Material.CHEST, (byte) 0, "§6Shop joueurs", (List<String>) null));
				ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta sMeta = (SkullMeta) skull.getItemMeta();
				sMeta.setOwner("MHF_Question");
				sMeta.setDisplayName("Warp aide");
				skull.setItemMeta(sMeta);
				inv.setItem(11, skull);
				inv.setItem(13, Utils.getItem(Material.TOTEM, (byte) 0, "§aGuilde", (List<String>) null));
				inv.setItem(15, Utils.getItem(Material.CHORUS_FRUIT, (byte) 0, "§5TP - Warps", (List<String>) null));
				inv.setItem(28, Utils.getItem(Material.ELYTRA, (byte) 0, "§7Jump", (List<String>) null));
				inv.setItem(34, Utils.getItem(Material.WATCH, (byte) 0, "§eMini-jeux", (List<String>) null));
				inv.setItem(47, Utils.getItem(Material.BOOK, (byte) 0, "§7Site", (List<String>) null));
				inv.setItem(49, Utils.getItem(Material.PAPER, (byte) 0, "§7Nos Réseaux", (List<String>) null));
				inv.setItem(51, Utils.getItem(Material.GOLD_INGOT, (byte) 0, "§6Boutique", (List<String>) null));
				return inv;
			}
			break;
		case 3437296:
			if (var4.equals("perm")) {
				Inventory perms = Bukkit.createInventory((InventoryHolder) null, 27,
						"§8Permissions de: " + this.target.getName());
				GuildPlayer guildPlayer = this.core.getGuildPlayer(this.target);
				List<Integer> slots = Arrays.asList(0, 1, 2, 3, 4, 5, 12, 14, 21, 22, 23, 24, 25, 26);
				GuildPermission[] gPerms = GuildPermission.values();

				int z;
				for (z = 0; z < slots.size(); ++z) {
					if (z < gPerms.length) {
						if (guildPlayer.hasRealPermission(gPerms[z])) {
							perms.setItem(slots.get(z), Utils.getItem(Material.TORCH, (byte) 0,
									gPerms[z].getPerm() + "§a (Activée)", (List<String>) null));
						} else {
							perms.setItem(slots.get(z), Utils.getItem(Material.REDSTONE_TORCH_ON, (byte) 0,
									gPerms[z].getPerm() + "§c (Désactivée)", (List<String>) null));
						}
					} else {
						perms.setItem(slots.get(z),
								Utils.getItem(Material.BEDROCK, (byte) 0, "§6Permission   venir", (List<String>) null));
					}
				}

				for (z = 0; z < 27; ++z) {
					if (!slots.contains(z)) {
						perms.setItem(z,
								Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
					}
				}

				perms.setItem(18, Utils.getItem(Material.ARROW, (byte) 0, "§8Retour", (List<String>) null));
				perms.setItem(13, Utils.getItem(Material.ANVIL, (byte) 0,
						"§8Grade: " + Utils.getRank(guildPlayer.getRank()), (List<String>) null));
				return perms;
			}
			break;
		case 73809469:
			if (var4.equals("LvlUp")) {
				Inventory lvlup = Bukkit.createInventory((InventoryHolder) null, 9, "§8Récompense de niveau");
				lvlup.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lvlup.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lvlup.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lvlup.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lvlup.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lvlup.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lvlup.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				lvlup.setItem(2, Utils.getItem(Material.SKULL_ITEM, (byte) 0,
						ChatColor.GREEN + "Augmenté le nombre de slot de 5", (List<String>) null));
				lvlup.setItem(6, Utils.getItem(Material.GOLD_AXE, (byte) 0,
						ChatColor.GREEN + "Augmenté la taille des claims de 20x20", (List<String>) null));
				return lvlup;
			}
			break;
		case 102846135:
			if (var4.equals("leave")) {
				Inventory leave = Bukkit.createInventory((InventoryHolder) null, 9, "Quitter votre guilde ?");
				leave.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				leave.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				leave.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				leave.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				leave.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				leave.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				leave.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				leave.setItem(2,
						Utils.getItem(Material.WOOL, (byte) 5, ChatColor.GREEN + "Valider", (List<String>) null));
				leave.setItem(6,
						Utils.getItem(Material.WOOL, (byte) 14, ChatColor.RED + "Annulé", (List<String>) null));
				return leave;
			}
			break;
		case 107953784:
			if (var4.equals("quota")) {
				Inventory quota = Bukkit.createInventory((InventoryHolder) null, 9,
						"§8Quota (" + this.guild.getQuota() + "/" + Guilds.getQuotaMax(this.guild.getLevel()) + ")");
				quota.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				quota.setItem(2, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				quota.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				quota.setItem(6, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				quota.setItem(1, Utils.getItem(Material.DIRT, (byte) 0, "§864 blocs de terre", (List<String>) null));
				quota.setItem(3, Utils.getItem(Material.STONE, (byte) 0, "§864 blocs de stone", (List<String>) null));
				quota.setItem(5, Utils.getItem(Material.SAND, (byte) 0, "§864 blocs de sable", (List<String>) null));
				quota.setItem(7,
						Utils.getItem(Material.GRAVEL, (byte) 0, "§864 blocs de gravier", (List<String>) null));
				quota.setItem(8, Utils.getItem(Material.ARROW, (byte) 0, "§8Retour", (List<String>) null));
				return quota;
			}
			break;
		case 565321746:
			if (var4.equals("look_players")) {
				Inventory lPlayers = Bukkit.createInventory((InventoryHolder) null, 45, "§8Membres de la guilde");
				int r = 0;

				for (Iterator<?> var27 = this.guild.getGuildMembers().iterator(); var27.hasNext(); ++r) {
					GuildPlayer pl = (GuildPlayer) var27.next();
					lPlayers.setItem(r,
							Utils.getHead(pl.getPlayer().getName(), "§8Joueur:§c " + pl.getPlayer().getName(),
									Arrays.asList("§7 Quota: " + pl.getQuota(),
											" §7Grade: " + Utils.prettifyText(pl.getRank().toString()))));
				}

				for (int y = 36; y < 45; ++y) {
					lPlayers.setItem(y,
							Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", (List<String>) null));
				}

				lPlayers.setItem(40, Utils.getItem(Material.ARROW, (byte) 0, "§8Retour", (List<String>) null));
				return lPlayers;
			}
		}

		return null;
	}
}