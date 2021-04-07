package com.dark.guilds.events;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import com.dark.guilds.Guild;
import com.dark.guilds.GuildStatus;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.BanPlayers;
import com.dark.guilds.players.GuildPermission;
import com.dark.guilds.players.GuildPlayer;
import com.dark.guilds.players.GuildRank;
import com.dark.guilds.utils.BookUtil;
import com.dark.guilds.utils.Menu;
import com.dark.guilds.utils.Utils;
import com.dark.guilds.zone.Selecteur;

public class InteractEvent implements Listener {
	Guilds guilds;
	private static /* synthetic */ int[] $SWITCH_TABLE$org$bukkit$Material;
	private String battle;

	public InteractEvent(Guilds guilds) {
		this.guilds = guilds;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		block199: {
			Menu menu;
			GuildPlayer gP;
			Menu m;
			Player player;
			Menu menu2;
			block202: {
				block201: {
					block200: {
						if (e.getInventory() == null) {
							return;
						}
						if (e.getCurrentItem() == null) {
							return;
						}
						if (!(e.getWhoClicked() instanceof Player))
							break block199;
						player = (Player) e.getWhoClicked();
						String[] args = e.getInventory().getName().split(" ");
						if (args[0].equalsIgnoreCase("Kick") && args[2].equalsIgnoreCase("?")) {
							e.setCancelled(true);
							if (e.getCurrentItem().getItemMeta().getDisplayName()
									.equalsIgnoreCase((Object) ChatColor.GREEN + "Valider")) {
								OfflinePlayer aKickPlayer = Bukkit.getOfflinePlayer((String) args[1]);
								GuildPlayer aKickG = Guilds.getInstance().getGuildPlayer(aKickPlayer);
								GuildPlayer gP2 = Guilds.getInstance().getGuildPlayer((OfflinePlayer) player);
								if (aKickG.hasPermission(GuildPermission.MANAGE)) {
									player.sendMessage((Object) ChatColor.DARK_RED
											+ "[Guild] Vous ne pouvez pas \u00e9j\u00e9cter ce joueur");
									return;
								}
								if (aKickPlayer instanceof Player) {
									((Player) aKickPlayer).sendMessage((Object) ChatColor.DARK_RED
											+ "[Guild] Vous avez \u00e9t\u00e9 \u00e9j\u00e9ct\u00e9 de votre Guilde");
								}
								Guilds.getInstance().bansPlayers.add(new BanPlayers(aKickPlayer, 864000));
								gP2.getGuild().removeGuildMember(aKickG);
								for (GuildPlayer pls : gP2.getGuild().getGuildMembers()) {
									if (pls == gP2 || !(pls.getPlayer() instanceof Player))
										continue;
									Player p = (Player) pls.getPlayer();
									p.sendMessage((Object) ChatColor.GOLD + "[Guild]" + (Object) ChatColor.RED + args[1]
											+ " a \u00e9t\u00e9 \u00e9j\u00e9ct\u00e9 de votre guilde");
								}
								player.sendMessage((Object) ChatColor.GREEN + "Vous venez d'\u00e9j\u00e9cter "
										+ args[1] + " avec succ\u00e9s");
								player.closeInventory();
							}
							if (e.getCurrentItem().getItemMeta().getDisplayName()
									.equalsIgnoreCase((Object) ChatColor.RED + "Annuler")) {
								menu2 = new Menu(player, this.guilds, this.guilds.getGuild(player),
										(OfflinePlayer) player);
								player.closeInventory();
								player.openInventory(menu2.getMenu("guilde", player));
							}
						}
						if (e.getInventory().getName().equals("Liste des joueurs")) {
							e.setCancelled(true);
						}
						if (e.getInventory().getName().equals((Object) (ChatColor.YELLOW + "Boutique"))) {
							e.setCancelled(true);
							switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType()
									.ordinal()]) {
							case 266: {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 1000.0
										&& !player.hasPermission("essentials.kits.barbare")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 1000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.kits.barbare");
									Bukkit.broadcastMessage((String) ("§6§l" + player.getName()
											+ " §a§lvient d'acheter le kit Barbare !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
								break;
							}

							case 260: {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 1000.0
										&& !player.hasPermission("essentials.kits.archer")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 1000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.kits.archer");
									Bukkit.broadcastMessage((String) ("§6§l" + player.getName()
											+ " §a§lvient d'acheter le kit Archer !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
								break;
							}

							case 256: {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 1000.0
										&& !player.hasPermission("essentials.kits.mineur")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 1000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.kits.mineur");
									Bukkit.broadcastMessage((String) ("§6§l" + player.getName()
											+ " §a§lvient d'acheter le kit Mineur !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
								break;
							}

							case 291: {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 1000.0
										&& !player.hasPermission("essentials.kits.fermier")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 1000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.kits.fermier");
									Bukkit.broadcastMessage((String) ("§6§l" + player.getName()
											+ " §a§lvient d'acheter le kit Fermier !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
								break;
							}

							case 275: {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 10000.0
										&& !player.hasPermission("essentials.kits.guerrier")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 10000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.kits.guerrier");
									Bukkit.broadcastMessage((String) ("§6§l" + player.getName()
											+ " §a§lvient d'acheter le kit Guerrier !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
								break;
							}

							case 321: {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 10000.0
										&& !player.hasPermission("essentials.kits.paladin")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 10000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.kits.paladin");
									Bukkit.broadcastMessage((String) ("§6§l" + player.getName()
											+ " §a§lvient d'acheter le kit Paladin !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
								break;
							}

							case 46: {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 10000.0
										&& !player.hasPermission("essentials.kits.architecte")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 10000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.kits.architecte");
									Bukkit.broadcastMessage((String) ("§6§l" + player.getName()
											+ " §a§lvient d'acheter le kit Architecte !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");

								}
								break;
							}

							case 448: {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 30000.0
										&& !player.hasPermission("essentials.kits.seigneur")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 30000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.kits.seigneur");
									Bukkit.broadcastMessage((String) ("§6§l" + player.getName()
											+ " §a§lvient d'acheter le kit Seigneur !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
								break;
							}

							case 278: {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 30000.0
										&& !player.hasPermission("essentials.kits.viking")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 30000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.kits.viking");
									Bukkit.broadcastMessage((String) ("§6§l" + player.getName()
											+ " §a§lvient d'acheter le kit Viking !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
								break;
							}
							}
						}
						if (e.getInventory().getName().equals("§bPermissions")) {
							e.setCancelled(true);
							if (e.getCurrentItem().getType() == Material.FEATHER) {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 50000.0
										&& !player.hasPermission("essentials.fly")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 50000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.fly");
									Bukkit.broadcastMessage(
											(String) ("§6§l" + player.getName() + " §a§lvient d'acheter le /fly !§r"));

								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
							}

							if (e.getCurrentItem().getType() == Material.WORKBENCH) {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 5000.0
										&& !player.hasPermission("essentials.workbench")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 5000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.workbench");
									Bukkit.broadcastMessage((String) ("§6§l" + player.getName()
											+ " §a§lvient d'acheter le /craft !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
							}

							if (e.getCurrentItem().getType() == Material.CAKE) {
								Guilds.getInstance();
								if (Guilds.econ.getBalance((OfflinePlayer) player) >= 15000.0
										&& !player.hasPermission("essentials.feed")) {
									Guilds.getInstance();
									Guilds.econ.withdrawPlayer((OfflinePlayer) player, 15000.0);
									Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
											"essentials.feed");
									Bukkit.broadcastMessage(
											(String) ("§6§l" + player.getName() + " §a§lvient d'acheter le /feed !§r"));
								} else {
									player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
								}
							}

							if (e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
								player.closeInventory();
								player.chat("/achat");
							}

							if (e.getCurrentItem().getType() == Material.PAPER) {
								Menu homes = new Menu(player, guilds, null, player);
								player.openInventory(homes.getMenu("homes", player));
							}

						}
						if (e.getInventory().getName().equals("Quitter votre guilde ?")) {
							e.setCancelled(true);
							if (e.getCurrentItem().getItemMeta().getDisplayName()
									.equalsIgnoreCase((Object) ChatColor.GREEN + "Valider")) {
								if (!Guilds.getInstance().hasGuild(player)) {
									return;
								}
								gP = Guilds.getInstance().getGuildPlayer((OfflinePlayer) player);
								if (gP.getGuild().getCreator() == gP) {
									player.sendMessage(
											this.guilds.getConfig().getString("Guilds.Messages.CreatorLeave"));
									return;
								}
								for (GuildPlayer pls : gP.getGuild().getGuildMembers()) {
									if (pls == gP || !(pls.getPlayer() instanceof Player))
										continue;
									Player p = (Player) pls.getPlayer();
									p.sendMessage((Object) ChatColor.GOLD + "[Guild]" + (Object) ChatColor.RED
											+ player.getName() + " \u00e0\u00a0 quitt\u00e9 votre guilde");
								}
								Guilds.getInstance().bansPlayers.add(
										new BanPlayers(Bukkit.getOfflinePlayer((String) player.getName()), 864000));
								player.sendMessage(this.guilds.getConfig().getString("Guilds.Messages.MessageLeave"));
								gP.getGuild().removeGuildMember(gP);
								player.closeInventory();
							}
							if (e.getCurrentItem().getItemMeta().getDisplayName()
									.equalsIgnoreCase((Object) ChatColor.RED + "Annul\u00e9")) {
								menu2 = new Menu(player, this.guilds, this.guilds.getGuild(player),
										(OfflinePlayer) player);
								player.closeInventory();
								player.openInventory(menu2.getMenu("guilde", player));
							}
						}
						if (!e.getInventory().getName().equals("§4Menu - AltyCraft"))
							break block200;
						switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType()
								.ordinal()]) {
						case 448: {
							if (this.guilds.hasGuild(player)) {
								menu2 = new Menu(player, this.guilds, this.guilds.getGuild(player),
										(OfflinePlayer) player);
								player.closeInventory();
								player.openInventory(menu2.getMenu("guilde", player));
								break;
							}
							menu2 = new Menu(player, this.guilds, null, (OfflinePlayer) player);
							player.closeInventory();
							player.openInventory(menu2.getMenu("guilde", player));
							break;
						}
						case 338: {
							if (!e.getCurrentItem().getItemMeta().getDisplayName()
									.equalsIgnoreCase("§7Nos r\u00e9seaux"))
								break;
							player.chat("/discord");
							player.chat("/youtube");
							break;
						}
						case 396: {
							player.chat("/warp aide");
							break;
						}
						case 265: {
							player.chat("/boutique");
							break;
						}
						case 55: {
							player.chat("/plot menu");
							break;
						}
						case 339: {
							player.chat("/site");
							break;
						}
						case 442: {
							player.chat("/warp jump");
							break;
						}
						case 346: {
							player.chat("/warp event");
							break;
						}
						case 263: {
							player.closeInventory();
							menu = new Menu(player, null, null, (OfflinePlayer) player);
							player.openInventory(menu.getMenu("permissions", player));
							break;
						}
						}
						e.setCancelled(true);
						break block199;
					}

					if (!e.getInventory().getName().equals("§8Menu - Guildes"))
						break block201;
					switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
					case 387: {
						if (!player.hasPermission("guild.create")) {
							player.sendMessage(Utils.color(
									"§c[§4Guilde§c] §4Vous n'avez pas la permission de cr\u00e9er une guilde !"));
							break;
						}
						int price = this.guilds.getConfig().getInt("Guilds.BasicConfiguration.GuildCreationCost");
						if (this.guilds.getEconomy().getBalance((OfflinePlayer) player) >= (double) price) {
							player.closeInventory();
							this.guilds.waitingName.add(player);
							player.sendMessage(
									Utils.color(this.guilds.getConfig().getString("Guilds.Messages.WaitingName")));
							Guilds.getInstance();
							Guilds.econ.withdrawPlayer((OfflinePlayer) player, 50000.0);
							break;
						}
						player.sendMessage(
								Utils.color(this.guilds.getConfig().getString("Guilds.Messages.NotEnoughMoney")));
						break;
					}
					case 339: {
						Menu guildChoise = new Menu(player, guilds, null, player);
						player.openInventory(guildChoise.getMenu("guildInviteChoise", player));
						break;
					}
					case 323: {
						GuildPlayer gPlayer = this.guilds.getGuildPlayer((OfflinePlayer) player);
						Menu quitter = new Menu(player, this.guilds, gPlayer.getGuild(), (OfflinePlayer) player);
						player.openInventory(quitter.getMenu("leave", player));
						break;
					}
					case 266: {
						e.setCancelled(true);
						GuildPlayer gPlayer = this.guilds.getGuildPlayer(player);
						if (!gPlayer.hasPermission(GuildPermission.ADMIN)) {
							player.sendMessage("§4Tu ne peux pas faire ça !");
							break;
						}
						player.closeInventory();
						Menu bataille = new Menu(player, this.guilds, this.guilds.getGuild(player),
								(OfflinePlayer) player);
						player.openInventory(bataille.getMenu("batailles", player));
						break;
					}
					case 138: {
						GuildPlayer gP3 = this.guilds.getGuildPlayer((OfflinePlayer) player);
						if (!gP3.hasPermission(GuildPermission.ADMIN)) {
							player.sendMessage(Utils.color(
									"§c[§4Guilde§c] §4Vous n'avez pas la permission d'acc\u00e9der \u00e0\u00a0 ce menu !"));
							break;
						}
						Menu admin = new Menu(player, this.guilds, gP3.getGuild(), (OfflinePlayer) player);
						player.openInventory(admin.getMenu("admin_guild", player));
						break;
					}
					case 383: {
						player.closeInventory();
						player.openInventory(
								Utils.getQuota(this.guilds.getGuild(player).getLevel(), this.guilds.getGuild(player)));
						break;
					}
					case 367: {
						try {
							player.teleport(this.guilds.getGuild(player).getSpawn());
						} catch (Exception e1) {
							System.out.println(e1);
							player.sendMessage(
									"§c[§4Guildes§c] §4Probl\u00e2me inconnu avec le spawn, veuillez le red\u00e9finir");
							break;
						}
						player.sendMessage(Utils.color(
								"§a[§2Guildes§a] §2Vous avez \u00e9t\u00e9 t\u00e9l\u00e9port\u00e9 au spawn de votre guilde"));
						break;
					}
					case 402: {
						Guild guild = this.guilds.getGuild(player);
						if (guild.getBook() == null) {
							player.sendMessage("§c[§4Guildes§c] §4Votre guilde n'a pas encore de cahier des charges !");
							break;
						}
						player.closeInventory();
						BookUtil.openBook(guild.getBook(), player);
						break;
					}
					case 394: {
						Menu players = new Menu(player, this.guilds, this.guilds.getGuild(player),
								(OfflinePlayer) player);
						player.openInventory(players.getMenu("look_players", player));
						break;
					}
					}
					e.setCancelled(true);
					break block199;
				}
				if (!e.getInventory().getName().equals("Options des claim"))
					break block202;
				this.guilds.getGuildPlayer((OfflinePlayer) player);
				e.setCancelled(true);
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 285: {
					player.closeInventory();
					if (Selecteur.SelectionneursAP2.contains((Object) player)
							|| Selecteur.SelectionneursAP1.contains((Object) player)) {
						player.sendMessage((Object) ChatColor.DARK_RED
								+ "Vous \u00eates d\u00e9j\u00e0\u00a0 en s\u00e9l\u00e9ction");
						return;
					}
					if (!Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
							.hasPermission(GuildPermission.CHANGE_CLAIM)) {
						player.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas les permissions requises");
						return;
					}
					if (player.getInventory().getItemInHand().getType() != Material.AIR) {
						player.sendMessage((Object) ChatColor.DARK_RED + "Votre main doit \u00eatre vide");
						return;
					}
					player.getInventory()
							.setItemInHand(Utils.getItem(Material.GOLD_AXE, (byte) 0, "§8Outil de Claim", null));
					player.sendMessage(
							"§a[§2Guildes§a] §2Cette hache vous permettra de redéfinir le claim de votre guilde.");
					player.sendMessage((Object) ChatColor.GREEN
							+ "Apr\u00e2s avoir d\u00e9fini les deux \u00e9xtr\u00e9mit\u00e9 de votre zone de guilde faites "
							+ (Object) ChatColor.YELLOW + "/claim " + (Object) ChatColor.GREEN
							+ " pour la d\u00e9finir");
					player.sendMessage((Object) ChatColor.GRAY + "Pour " + (Object) ChatColor.RED + " Annuler"
							+ (Object) ChatColor.GRAY + " la s\u00e9l\u00e9ction, veuillez jeter la hache");
					Selecteur.Selectionneurs.add(player);
					break block199;
				}
				case 264: {
					player.closeInventory();
					if (Selecteur.SelectionneursAP2.contains((Object) player)
							|| Selecteur.Selectionneurs.contains((Object) player)) {
						player.sendMessage((Object) ChatColor.DARK_RED
								+ "Vous \u00eates d\u00e9j\u00e0\u00a0 en s\u00e9l\u00e9ction");
						return;
					}
					if (!Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
							.hasPermission(GuildPermission.CHANGE_CLAIM)) {
						player.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas les permissions requises");
						return;
					}
					if (player.getInventory().getItemInHand().getType() != Material.AIR) {
						player.sendMessage((Object) ChatColor.DARK_RED + "Votre main doit \u00eatre vide");
						return;
					}
					if (Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getGuild().getLevel() < 5) {
						player.sendMessage((Object) ChatColor.DARK_RED
								+ "Votre guilde n'est pas d'un niveau suffisant pour cr\u00e9er votre Avant Poste 1 "
								+ (Object) ChatColor.RED + "Niveau Requis : 5");
						return;
					}
					player.getInventory()
							.setItemInHand(Utils.getItem(Material.GOLD_AXE, (byte) 0, "§8Outil de Claim", null));
					player.sendMessage(
							"§a[§2Guildes§a] §2Cette hache vous permettra de redéfinir le premier avant-poste de votre guilde.");
					player.sendMessage((Object) ChatColor.GREEN
							+ "Apr\u00e2s avoir d\u00e9finis les deux \u00e9xtr\u00e9mit\u00e9 de votre avant-poste faites "
							+ (Object) ChatColor.YELLOW + "/ap " + (Object) ChatColor.GREEN + " pour le d\u00e9finir");
					player.sendMessage((Object) ChatColor.GRAY + "Pour " + (Object) ChatColor.RED + " Annuler"
							+ (Object) ChatColor.GRAY + " la s\u00e9l\u00e9ction, veuillez jeter la hache");
					Selecteur.SelectionneursAP1.add(player);
					break block199;
				}
				case 261: {
					player.closeInventory();
					gP = this.guilds.getGuildPlayer((OfflinePlayer) player);
					if (!gP.hasPermission(GuildPermission.ADMIN)) {
						player.sendMessage(Utils.color(
								"§c[§4Guilde§c] §4Vous n'avez pas la permission d'acc\u00e9der \u00e0\u00a0 ce menu !"));
					} else {
						Menu admin = new Menu(player, this.guilds, gP.getGuild(), (OfflinePlayer) player);
						player.openInventory(admin.getMenu("admin_guild", player));
					}
					break block199;
				}
				case 265: {
					player.closeInventory();
					if (Selecteur.SelectionneursAP1.contains((Object) player)
							|| Selecteur.Selectionneurs.contains((Object) player)) {
						player.sendMessage((Object) ChatColor.DARK_RED
								+ "Vous \u00eates d\u00e9j\u00e0\u00a0 en s\u00e9l\u00e9ction");
						return;
					}
					if (!Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
							.hasPermission(GuildPermission.CHANGE_CLAIM)) {
						player.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas les permissions requises");
						return;
					}
					if (player.getInventory().getItemInHand().getType() != Material.AIR) {
						player.sendMessage((Object) ChatColor.DARK_RED + "Votre main doit \u00eatre vide");
						return;
					}
					if (Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getGuild().getLevel() < 10) {
						player.sendMessage((Object) ChatColor.DARK_RED
								+ "Votre guilde n'est pas d'un niveau suffisant pour cr\u00e9er votre Avant Poste 1 "
								+ (Object) ChatColor.RED + "Niveau Requis : 10");
						return;
					}
					player.getInventory()
							.setItemInHand(Utils.getItem(Material.GOLD_AXE, (byte) 0, "§8Outil de Claim", null));
					player.sendMessage(
							"§a[§2Guildes§a] §2Cette ahche vous permettra de redéfinir le premier avant-poste de votre guilde.");
					player.sendMessage((Object) ChatColor.GREEN
							+ "Apr\u00e2s avoir d\u00e9fini les deux \u00e9xtr\u00e9mit\u00e9 de votre avant-poste faites "
							+ (Object) ChatColor.YELLOW + "/ap " + (Object) ChatColor.GREEN + " pour le d\u00e9finir");
					player.sendMessage((Object) ChatColor.GRAY + "Pour " + (Object) ChatColor.RED + " Annuler"
							+ (Object) ChatColor.GRAY + " la s\u00e9l\u00e9ction, veuillez jeter la hache");
					Selecteur.SelectionneursAP2.add(player);
					break block199;
				}
				default: {
					return;
				}
				}
			}
			if (e.getInventory().getName().equals("§8Menu d'administration")) {
				GuildPlayer guildPlayer = this.guilds.getGuildPlayer((OfflinePlayer) player);
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 42: {
					player.closeInventory();
					if (!Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
							.hasPermission(GuildPermission.CHANGE_CLAIM)) {
						player.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas les permissions requises");
						return;
					}
					Menu optionsClaim = new Menu(player, this.guilds, this.guilds.getGuild(player),
							(OfflinePlayer) player);
					player.openInventory(optionsClaim.getMenu("optionsClaim", player));
					break;
				}
				case 285: {
					player.closeInventory();
					if (!Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
							.hasPermission(GuildPermission.CHANGE_CLAIM)) {
						player.sendMessage((Object) ChatColor.DARK_RED + "Vous n'avez pas les permissions requises");
						return;
					}
					if (player.getInventory().getItemInHand().getType() != Material.AIR) {
						player.sendMessage((Object) ChatColor.DARK_RED + "Votre main doit \u00eatre vide");
						return;
					}
					player.getInventory()
							.setItemInHand(Utils.getItem(Material.GOLD_AXE, (byte) 0, "§8Outil de Claim", null));
					player.sendMessage(
							"§a[§2Guildes§a] §2Cette hache vous permettra de d\u00e9finir le claim de votre guilde.");
					player.sendMessage((Object) ChatColor.GREEN
							+ "Apr\u00e2s avoir d\u00e9finis les deux \u00e9xtr\u00e9mit\u00e9 de votre zone de guilde faites "
							+ (Object) ChatColor.YELLOW + "/claim " + (Object) ChatColor.GREEN
							+ "pour la d\u00e9finir");
					player.sendMessage((Object) ChatColor.GRAY + "Pour " + (Object) ChatColor.RED + " Annuler"
							+ (Object) ChatColor.GRAY + " la s\u00e9l\u00e9ction, veuillez jeter la hache");
					Selecteur.Selectionneurs.add(player);
					break;
				}
				case 385: {
					if (!guildPlayer.hasPermission(GuildPermission.CHANGE_CDC)) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous n'avez pas la permission de changer le cahier des charges");
						break;
					}
					if (!this.guilds.adminChangeBook.contains((Object) player)) {
						player.closeInventory();
						player.sendMessage(
								"§§[§2Guildes§§] §2Une fois que vous avez fini de modifier ce livre, veuillez retourner dans le menu");
						if (this.guilds.getGuild(player).getAdminBook() == null) {
							player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BOOK_AND_QUILL) });
						} else {
							player.getInventory()
									.addItem(new ItemStack[] { this.guilds.getGuild(player).getAdminBook() });
						}
						this.guilds.adminChangeBook.add(player);
						break;
					}
					Menu book = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.openInventory(book.getMenu("book", player));
					break;
				}
				case 420: {
					if (!guildPlayer.hasPermission(GuildPermission.MANAGE)) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous n'avez pas la permission d'acc\u00e9der aux param\u00eatres principaux");
						break;
					}
					Menu params = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.openInventory(params.getMenu("params", player));
					break;
				}
				case 132: {
					if (!guildPlayer.hasPermission(GuildPermission.MANAGE_PERMS)) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous n'avez pas la permission de g\u00e9rer les permissions");
						break;
					}
					menu = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.openInventory(menu.getMenu("players", player));
					break;
				}
				case 344: {
					if (!guildPlayer.hasPermission(GuildPermission.CHANGE_SPAWN)) {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas la permission de changer le spawn");
						break;
					}
					if (!guildPlayer.getGuild().hasGuildZone()) {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de zone de guilde");
						break;
					}
					if (!guildPlayer.getGuild().getGuildZone().isInto(player.getLocation())) {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'\u00eates pas dans la zone de guilde");
						break;
					}
					this.guilds.getGuild(player).setSpawn(player.getLocation());
					player.sendMessage(
							Utils.color("§a[§2Guildes§a] §2Vous avez chang\u00e9 votre spawn avec succ\u00e2s"));
					break;
				}
				case 261: {
					Menu m2 = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.openInventory(m2.getMenu("guilde", player));
					break;
				}
				}
				e.setCancelled(true);
			} else if (e.getInventory().getName().equals("§8Membres de la guilde - Admin")) {
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 396: {
					String name = ChatColor
							.stripColor((String) e.getCurrentItem().getItemMeta().getDisplayName().split(" ")[1]);
					OfflinePlayer target = Bukkit.getOfflinePlayer((String) name);
					m = new Menu(player, this.guilds, this.guilds.getGuild(player), target);
					player.openInventory(m.getMenu("perm", player));
					break;
				}
				case 261: {
					menu = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.openInventory(menu.getMenu("admin_guild", player));
					break;
				}
				}
				e.setCancelled(true);
			} else if (e.getInventory().getName().equals("§7Menu des batailles !")) {
				e.setCancelled(true);
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 266: {
					Menu chooseGuild = new Menu(player, guilds, guilds.getGuild(player), player);
					battle = "battle";
					player.closeInventory();
					player.openInventory(chooseGuild.getMenu("guildChoise", player));
					break;
				}
				case 275: {
					Menu chooseGuild = new Menu(player, guilds, guilds.getGuild(player), player);
					battle = "war";
					player.closeInventory();
					player.openInventory(chooseGuild.getMenu("guildChoise", player));
					break;
				}

				}
			} else if (e.getInventory().getName().equals("Quelle guilde choisir ?")) {
				e.setCancelled(true);
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 161: {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals(guilds.getGuild(player).getName())) {
						player.sendMessage("§4Vous ne pouvez pas choisir votre guilde !");
						// break;
					}
					if (battle.equals("battle")) {
						player.sendMessage("§6Un message va être envoyé à un staff, merci de patienter...\n ");
						Player p = null;
						for (Player seekPlayer : Bukkit.getOnlinePlayers()) {
							if (seekPlayer.isOp()) {
								p = seekPlayer;
								seekPlayer.sendMessage("§6La guilde " + guilds.getGuild(player).getName()
										+ " cherche à lancer une bataille contre "
										+ e.getCurrentItem().getItemMeta().getDisplayName()
										+ "\nAcceptez-vous de lancer le combat ?\n ");
								break;
							} else
								continue;
						}
						if (p == null) {
							player.sendMessage(
									"§4Aucun membre du staff n'est connecté actuellement, réessaye plus tard !");
						}
						player.closeInventory();
					}
					if (battle.equals("war")) {
						player.sendMessage("§6Un message va être envoyé à un staff, merci de patienter...\n ");
						Player p = null;
						for (Player seekPlayer : Bukkit.getOnlinePlayers()) {
							if (seekPlayer.isOp()) {
								p = seekPlayer;
								seekPlayer.sendMessage("§6La guilde " + guilds.getGuild(player).getName()
										+ " cherche à lancer une guerre contre "
										+ e.getCurrentItem().getItemMeta().getDisplayName()
										+ "\nAcceptez-vous de lancer le combat ?\n ");
								break;
							} else
								continue;
						}
						if (p == null) {
							player.sendMessage(
									"§4Aucun membre du staff n'est connecté actuellement, réessaye plus tard !");
						}
						player.closeInventory();
					}
					battle = null;
					break;
				}
				}
			} else if (e.getInventory().getName().equals("§8Paramètres de la guilde")) {
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 261: {
					menu2 = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.openInventory(menu2.getMenu("admin_guild", player));
					break;
				}
				case 424: {
					player.closeInventory();
					player.sendMessage("§a[§2Guildes§a] §2Veuillez entrer une couleur dans le chat");
					player.sendMessage("§a&a§7, " + "§r§b&b§7, " + "§r§c&c§7, " + "§r§d&d§7, " + "§r§e&e§7, "
							+ "§r§f&f§7, " + "§r§0&0§7, " + "§r§1&1§7, " + "§r§2&2§7, " + "§r§3&3§7, " + "§r§4&4§7, "
							+ "§r§5&5§7, " + "§r§6&6§7, " + "§r§7&7, " + "§r§8&8§7, " + "§r§9&9§7");
					this.guilds.adminChangeTag.add(player);
					break;
				}
				case 338: {
					player.closeInventory();
					player.sendMessage("§a[§2Guildes§a] §2Veuillez entrer une description dans le chat");
					this.guilds.adminChangeDesc.add(player);
					break;
				}
				case 420: {
					if (this.guilds.getEconomy().getBalance((OfflinePlayer) player) >= 10000.0) {
						player.closeInventory();
						player.sendMessage("§a[§2Guildes§a] §2Veuillez entrer un nom dans le chat");
						this.guilds.adminChangeName.add(player);
						break;
					}
					player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas les moyens de renommer votre guilde !");
					break;
				}
				case 160: {
					Menu persm;
					Guild guild;
					if (e.getCurrentItem().getDurability() == 13) {
						guild = this.guilds.getGuild(player);
						guild.setStatus(GuildStatus.INVITATION);
						player.sendMessage(
								Utils.color("§a[§2Guildes§a] §2La guilde est d\u00e9sormais sur invitation"));
						persm = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
						player.openInventory(persm.getMenu("params", player));
						break;
					}
					if (e.getCurrentItem().getDurability() == 1) {
						guild = this.guilds.getGuild(player);
						guild.setStatus(GuildStatus.CLOSED);
						player.sendMessage(Utils.color("§a[§2Guildes§a] §2La guilde est d\u00e9sormais ferm\u00e9e"));
						persm = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
						player.openInventory(persm.getMenu("params", player));
						break;
					}
					if (e.getCurrentItem().getDurability() != 14)
						break;
					guild = this.guilds.getGuild(player);
					guild.setStatus(GuildStatus.OPENED);
					player.sendMessage(
							Utils.color("§a[§2Guildes§a] §2La guilde est d\u00e9sormais ouverte \u00e0\u00a0 tous !"));
					persm = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.openInventory(persm.getMenu("params", player));
					break;
				}
				}
				e.setCancelled(true);
			} else if (e.getInventory().getName().equals("Achat des homes")) {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("5")) {
					Guilds.getInstance();
					if (Guilds.getInstance().getPerms().has(player, " essentials.sethome.multiple.empereur")) {
						if (Guilds.econ.getBalance((OfflinePlayer) player) >= 4000.0) {
							Guilds.getInstance();
							Guilds.econ.withdrawPlayer((OfflinePlayer) player, 4000.0);
							Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
									" essentials.sethome.multiple.conquerant");
							player.sendMessage("Tu as acheté 5 homes !");
						} else {
							player.sendMessage((Object) ChatColor.RED + "Tu ne peux pas acheter ça !");
						}
					} else {
						player.sendMessage((Object) ChatColor.RED + "Tu ne peux plus acheter de homes");
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("10")) {
					Guilds.getInstance();
					if (Guilds.getInstance().getPerms().has(player, " essentials.sethome.multiple.conquerant")) {
						if (Guilds.econ.getBalance((OfflinePlayer) player) >= 15000.0) {
							Guilds.getInstance();
							Guilds.econ.withdrawPlayer((OfflinePlayer) player, 15000.0);
							Guilds.getInstance().getPerms().playerAdd(null, (OfflinePlayer) player,
									" essentials.sethome.multiple.empereur");
							player.sendMessage("Tu as acheté 10 homes !");
						} else
							player.sendMessage((Object) ChatColor.RED + "Tu dois acheter les 5 homes avant !");
					} else {
						player.sendMessage((Object) ChatColor.RED + "Tu dois acheter les 5 homes avant !");
					}
				}
			} else if (e.getInventory().getName().contains("§8Permissions de: ")) {
				OfflinePlayer target = Bukkit.getOfflinePlayer(
						(String) ChatColor.stripColor((String) e.getInventory().getName().split(" ")[2]));
				GuildPlayer gTarget = this.guilds.getGuildPlayer(target);
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 51: {
					if (gTarget.getPlayer().getUniqueId()
							.equals(gTarget.getGuild().getCreator().getPlayer().getUniqueId())
							&& !player.getUniqueId()
									.equals(gTarget.getGuild().getCreator().getPlayer().getUniqueId())) {
						player.sendMessage(Utils.color(
								"§c[§4Guildes§c] §4Vous ne pouvez pas modifier les permissions du chef de la guilde"));
						break;
					}
					GuildPermission pem = GuildPermission
							.getPermFromString(e.getCurrentItem().getItemMeta().getDisplayName().split("§a")[0]);
					gTarget.removePlayerPermission(pem);
					player.sendMessage(Utils.color("§a[§2Guildes§a] §2Le membre §a" + target.getName()
							+ " §2ne poss\u00e2de plus cette permission !"));
					Menu persm = new Menu(player, this.guilds, this.guilds.getGuild(player), target);
					player.openInventory(persm.getMenu("perm", player));
					break;
				}
				case 77: {
					GuildPermission pm = GuildPermission
							.getPermFromString(e.getCurrentItem().getItemMeta().getDisplayName().split("§c")[0]);
					System.out.println((Object) pm);
					System.out.println(gTarget);
					gTarget.addPlayerPermission(GuildPermission.valueOf(pm.toString()));
					player.sendMessage(Utils.color("§a[§2Guildes§a] §2Le membre §a" + target.getName()
							+ " §2poss\u00e2de d\u00e9sormais cette permission !"));
					Menu perm = new Menu(player, this.guilds, this.guilds.getGuild(player), target);
					player.openInventory(perm.getMenu("perm", player));
					break;
				}
				case 261: {
					Menu menu3 = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.openInventory(menu3.getMenu("players", player));
					break;
				}
				case 146: {
					if (gTarget.getRank() == GuildRank.MEMBER) {
						gTarget.setRank(GuildRank.MODERATOR);
						player.sendMessage(Utils.color("§a[§2Guildes§a] §2Le membre §a" + target.getName()
								+ " §2est d\u00e9sormais adjoint !"));
						Menu perm2 = new Menu(player, this.guilds, this.guilds.getGuild(player), target);
						player.openInventory(perm2.getMenu("perm", player));
						break;
					}
					if (gTarget.getRank() == GuildRank.MODERATOR) {
						gTarget.setRank(GuildRank.BOSS);
						player.sendMessage(Utils.color(
								"§a[§2Guildes§a] §2Le membre §a" + target.getName() + " §2est d\u00e9sormais chef !"));
						Menu perm2 = new Menu(player, this.guilds, this.guilds.getGuild(player), target);
						player.openInventory(perm2.getMenu("perm", player));
						break;
					}
					if (gTarget.getRank() != GuildRank.BOSS)
						break;
					if (!gTarget.getPlayer().getUniqueId()
							.equals(gTarget.getGuild().getCreator().getPlayer().getUniqueId())) {
						gTarget.setRank(GuildRank.MEMBER);
						player.sendMessage(Utils.color("§a[§2Guildes§a] §2Le membre §a" + target.getName()
								+ " §2est d\u00e9sormais membre !"));
						Menu perm2 = new Menu(player, this.guilds, this.guilds.getGuild(player), target);
						player.openInventory(perm2.getMenu("perm", player));
						break;
					}
					player.sendMessage(Utils.color("§c[§4Guildes§c] §4Le membre §c" + target.getName()
							+ " §4est le cr\u00e9ateur de la guilde, vous ne pouvez pas le d\u00e9rank !"));
					break;
				}
				}
				e.setCancelled(true);
			} else if (e.getInventory().getName().equals("§8Gestion du Cahier des Charges")) {
				block52: switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType()
						.ordinal()]) {
				case 396: {
					e.setCancelled(true);
					break;
				}
				case 261: {
					e.setCancelled(true);
					Menu back = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.openInventory(back.getMenu("admin_guild", player));
					break;
				}
				case 161: {
					e.setCancelled(true);
					switch (e.getRawSlot()) {
					case 14: {
						ItemStack book = e.getInventory().getItem(13);
						if (book == null || book.getType() == Material.AIR) {
							player.sendMessage(
									"§c[§4Guildes§c] §4Vous ne pouvez pas ne rien d\u00e9finir comme cahier des charges !");
							break block52;
						}
						if (book.getType() == Material.WRITTEN_BOOK) {
							player.sendMessage(
									"§c[§4Guildes§c] §4Vous ne pouvez pas d\u00e9finir comme cahier des charges un livre sign\u00e9!");
							break block52;
						}
						player.closeInventory();
						player.sendMessage(
								"§a[§2Guildes§§] §2Vous avez d\u00e9fini votre cahier des charges avec succ\u00e2s !");
						this.guilds.getGuild(player).setBook(book);
						this.guilds.adminChangeBook.remove((Object) player);
					}
					}
					break;
				}
				default: {
					break;
				}
				}
			} else if (e.getInventory().getName().equals("§8Membres de la guilde")) {
				gP = Guilds.getInstance().getGuildPlayer((OfflinePlayer) player);
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 261: {
					Menu back = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.openInventory(back.getMenu("guilde", player));
					break;
				}
				case 396: {
					if (!gP.hasPermission(GuildPermission.MANAGE)) {
						player.sendMessage((Object) ChatColor.DARK_RED
								+ "[Guildes]Vous n'avez pas la permissions d'\u00e9j\u00e9cter ce joueur de la guilde");
						break;
					}
					m = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					String[] name = e.getCurrentItem().getItemMeta().getDisplayName().split(" ");
					player.sendMessage("kick " + name[1]);
					player.openInventory(m.getMenu("kick " + name[1], player));
					break;
				}
				}
				e.setCancelled(true);
			} else if (e.getInventory().getName().contains("§8R\u00e9compense de niveau")) {
				Guild guild = this.guilds.getGuild(player);
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 396: {
					player.closeInventory();
					guild.setMaxSize(guild.getMaxSize() + 5);
					player.sendMessage("§a[§2Guildes§§] §2Vous avez augment\u00e9 de 5 la capacit\u00e9 de joueur");
					break;
				}
				case 285: {
					player.closeInventory();
					guild.setFieldSide(guild.getFieldSide() + 20);
					player.sendMessage("§a[§2Guildes§§] §2Vous avez augment\u00e9 de 20x20 la surface de claim");
					break;
				}
				}
				e.setCancelled(true);
			} else if (e.getInventory().getName().equals("Où souhaitez-vous aller ?")) {
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 3:
					Bukkit.dispatchCommand(guilds.getServer().getConsoleSender(),
							"warp ressources " + player.getDisplayName());
					break;

				case 113:
					Bukkit.dispatchCommand(guilds.getServer().getConsoleSender(),
							"warp nether " + player.getDisplayName());
					break;

				case 122:
					Bukkit.dispatchCommand(guilds.getServer().getConsoleSender(),
							"warp end " + player.getDisplayName());
					break;

				case 266:
					Bukkit.dispatchCommand(guilds.getServer().getConsoleSender(),
							"warp arena " + player.getDisplayName());
					break;
				}
				e.setCancelled(true);
			} else if (e.getInventory().getName().equals("Quelle guilde rejoindre ?")) {
				e.setCancelled(true);
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 161: {
					String guildName = e.getCurrentItem().getItemMeta().getDisplayName();
					Guild guildToJoin = guilds.getGuildByName(guildName);
					if (guildToJoin.getStatus().equals(GuildStatus.OPENED)) {
						ArrayList<GuildPermission> perms = new ArrayList<GuildPermission>();
						perms.add(GuildPermission.NOTHING);
						guildToJoin.addGuildMembers(
								new GuildPlayer(guildToJoin, (OfflinePlayer) player, GuildRank.MEMBER, perms));
						this.guilds.loadPlayers();
						player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
								(String) ("&a[&2Guildes&a] &2Vous faites d\u00e9sormais partie de la guilde "
										+ guildToJoin.getName())));
						Bukkit.dispatchCommand(player, "mail send " + guildToJoin.getCreator().getPlayer().getName()
								+ " Le joueur " + player.getDisplayName() + " a rejoint votre guilde");
					} else if (guildToJoin.getStatus().equals(GuildStatus.INVITATION)) {
						File f = new File(
								guilds.getDataFolder() + "/" + player.getDisplayName() + "-" + guildToJoin.getName());
						try {
							if (f.createNewFile()) {
								player.sendMessage("Votre demande d'admission a bien été envoyée");

								Bukkit.dispatchCommand(player,
										"mail send " + guildToJoin.getCreator().getPlayer().getName() + " Le joueur "
												+ player.getDisplayName() + " souhaite rejoindre votre guilde.\n"
												+ "Pour l'accepter, faites /g accept " + player.getDisplayName());
							} else
								player.sendMessage("§4Un problème est survenu, réessayez plus tard !");
						} catch (CommandException | IOException e1) {
							e1.printStackTrace();
						}
					}

					break;
				}
				}

			} else if (e.getInventory().getName().contains("§8Quota")) {
				Guild g = this.guilds.getGuild(player);
				e.setCancelled(true);
				switch (InteractEvent.$SWITCH_TABLE$org$bukkit$Material()[e.getCurrentItem().getType().ordinal()]) {
				case 5: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.COBBLESTONE));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.COBBLESTONE));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 4: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.DIRT), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIRT, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.DIRT));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.DIRT));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 13: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.SAND), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.SAND, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.SAND));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.SAND));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 2: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.STONE), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STONE, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.STONE));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.STONE));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 18: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.LOG), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.LOG, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.LOG));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.LOG));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else if (player.getInventory().containsAtLeast(new ItemStack(Material.LOG_2), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.LOG_2, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.LOG_2));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 36: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOL), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOL, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.WOOL));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.WOOL));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 25: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.SANDSTONE), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.SANDSTONE, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.SANDSTONE));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.SANDSTONE));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 88: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.NETHERRACK), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.NETHERRACK, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.NETHERRACK));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.NETHERRACK));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 46: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.BRICK), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.BRICK, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.BRICK));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.BRICK));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 169: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.PRISMARINE), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.PRISMARINE, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.PRISMARINE));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.PRISMARINE));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 43: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_BLOCK), 64)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_BLOCK, 64) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.IRON_BLOCK));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.IRON_BLOCK));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 123: {
					if (g.getQuota() >= Guilds.getQuotaMax(g.getLevel())) {
						player.sendMessage(
								"§c[§4Guildes§c] §4Vous avez d\u00e9j\u00e0\u00a0 remplit votre quota pour ce mois !");
						break;
					}
					if (player.getInventory().containsAtLeast(new ItemStack(Material.DRAGON_EGG), 1)) {
						player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DRAGON_EGG, 1) });
						g.setQuota(g.getQuota() + Utils.getQuotaValue(Material.DRAGON_EGG));
						Guilds.getInstance().getGuildPlayer((OfflinePlayer) player)
								.setQuota(Guilds.getInstance().getGuildPlayer((OfflinePlayer) player).getQuota()
										+ Utils.getQuotaValue(Material.DRAGON_EGG));
						player.sendMessage("§a[§2Guildes§§] §2Vous avez remplit une partie du quota ! Merci !");
					} else {
						player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de quoi remplir une partie du quota");
					}
					player.openInventory(Utils.getQuota(g.getLevel(), g));
					break;
				}
				case 261: {
					Menu menu4 = new Menu(player, this.guilds, this.guilds.getGuild(player), (OfflinePlayer) player);
					player.closeInventory();
					player.openInventory(menu4.getMenu("guilde", player));
				}
				}
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if (this.guilds.waitingName.contains((Object) player)) {
			String name = e.getMessage();
			if (this.guilds.nameExist(name)) {
				player.sendMessage(Utils.color(this.guilds.getConfig().getString("Guilds.Messages.NameExist")));
				e.setCancelled(true);
			} else {
				this.guilds.createGuild(player, name);
				this.guilds.loadPlayers();
				this.guilds.refreshPlayersData();
				this.guilds.getGuild(player).setSpawn(player.getLocation());
				this.guilds.getGuild(player).setTag("§a");
				this.guilds.waitingName.remove((Object) player);
				Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
						(String) ("pex user " + player.getName() + " add guild.manage.all"));
				Bukkit.broadcastMessage((String) ("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----"));
				Bukkit.broadcastMessage((String) ("§6§l" + player.getName() + " §r§a§lvient de créer la guilde §r§6§l"
						+ name + "§r§a§l !!!\n" + "N'hésitez pas à la rejoindre !"));
				Bukkit.broadcastMessage((String) ("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----"));
				e.setCancelled(true);
				Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
						(String) ("mail send Heroxys " + e.getPlayer().getName() + " \u00e0 cr\u00e9er la guilde "
								+ Guilds.getInstance().getGuild(e.getPlayer()).getName()));
			}
		}
	}

	static /* synthetic */ int[] $SWITCH_TABLE$org$bukkit$Material() {
		int[] arrn;
		int[] arrn2 = $SWITCH_TABLE$org$bukkit$Material;
		if (arrn2 != null) {
			return arrn2;
		}
		arrn = new int[Material.values().length];
		try {
			arrn[Material.ACACIA_DOOR.ordinal()] = 197;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ACACIA_DOOR_ITEM.ordinal()] = 429;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ACACIA_FENCE.ordinal()] = 193;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ACACIA_FENCE_GATE.ordinal()] = 188;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ACACIA_STAIRS.ordinal()] = 164;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ACTIVATOR_RAIL.ordinal()] = 158;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.AIR.ordinal()] = 1;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ANVIL.ordinal()] = 146;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.APPLE.ordinal()] = 259;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ARMOR_STAND.ordinal()] = 415;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ARROW.ordinal()] = 261;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BAKED_POTATO.ordinal()] = 392;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BANNER.ordinal()] = 424;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BARRIER.ordinal()] = 167;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BEACON.ordinal()] = 139;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BED.ordinal()] = 354;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BEDROCK.ordinal()] = 8;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BED_BLOCK.ordinal()] = 27;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BEETROOT.ordinal()] = 433;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BEETROOT_BLOCK.ordinal()] = 208;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BEETROOT_SEEDS.ordinal()] = 434;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BEETROOT_SOUP.ordinal()] = 435;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BIRCH_DOOR.ordinal()] = 195;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BIRCH_DOOR_ITEM.ordinal()] = 427;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BIRCH_FENCE.ordinal()] = 190;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BIRCH_FENCE_GATE.ordinal()] = 185;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BIRCH_WOOD_STAIRS.ordinal()] = 136;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BLACK_GLAZED_TERRACOTTA.ordinal()] = 251;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BLACK_SHULKER_BOX.ordinal()] = 235;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BLAZE_POWDER.ordinal()] = 376;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BLAZE_ROD.ordinal()] = 368;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BLUE_GLAZED_TERRACOTTA.ordinal()] = 247;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BLUE_SHULKER_BOX.ordinal()] = 231;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOAT.ordinal()] = 332;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOAT_ACACIA.ordinal()] = 446;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOAT_BIRCH.ordinal()] = 444;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOAT_DARK_OAK.ordinal()] = 447;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOAT_JUNGLE.ordinal()] = 445;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOAT_SPRUCE.ordinal()] = 443;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BONE.ordinal()] = 351;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BONE_BLOCK.ordinal()] = 217;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOOK.ordinal()] = 339;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOOKSHELF.ordinal()] = 48;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOOK_AND_QUILL.ordinal()] = 385;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOW.ordinal()] = 260;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BOWL.ordinal()] = 280;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BREAD.ordinal()] = 296;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BREWING_STAND.ordinal()] = 118;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BREWING_STAND_ITEM.ordinal()] = 378;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BRICK.ordinal()] = 46;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BRICK_STAIRS.ordinal()] = 109;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BROWN_GLAZED_TERRACOTTA.ordinal()] = 248;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BROWN_MUSHROOM.ordinal()] = 40;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BROWN_SHULKER_BOX.ordinal()] = 232;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BUCKET.ordinal()] = 324;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.BURNING_FURNACE.ordinal()] = 63;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CACTUS.ordinal()] = 82;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CAKE.ordinal()] = 353;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CAKE_BLOCK.ordinal()] = 93;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CARPET.ordinal()] = 172;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CARROT.ordinal()] = 142;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CARROT_ITEM.ordinal()] = 390;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CARROT_STICK.ordinal()] = 397;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CAULDRON.ordinal()] = 119;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CAULDRON_ITEM.ordinal()] = 379;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CHAINMAIL_BOOTS.ordinal()] = 304;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CHAINMAIL_CHESTPLATE.ordinal()] = 302;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CHAINMAIL_HELMET.ordinal()] = 301;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CHAINMAIL_LEGGINGS.ordinal()] = 303;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CHEST.ordinal()] = 55;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CHORUS_FLOWER.ordinal()] = 201;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CHORUS_FRUIT.ordinal()] = 431;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CHORUS_FRUIT_POPPED.ordinal()] = 432;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CHORUS_PLANT.ordinal()] = 200;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CLAY.ordinal()] = 83;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CLAY_BALL.ordinal()] = 336;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CLAY_BRICK.ordinal()] = 335;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COAL.ordinal()] = 262;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COAL_BLOCK.ordinal()] = 174;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COAL_ORE.ordinal()] = 17;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COBBLESTONE.ordinal()] = 5;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COBBLESTONE_STAIRS.ordinal()] = 68;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COBBLE_WALL.ordinal()] = 140;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COCOA.ordinal()] = 128;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COMMAND.ordinal()] = 138;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COMMAND_CHAIN.ordinal()] = 212;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COMMAND_MINECART.ordinal()] = 421;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COMMAND_REPEATING.ordinal()] = 211;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COMPASS.ordinal()] = 344;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CONCRETE.ordinal()] = 252;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CONCRETE_POWDER.ordinal()] = 253;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COOKED_BEEF.ordinal()] = 363;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COOKED_CHICKEN.ordinal()] = 365;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COOKED_FISH.ordinal()] = 349;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COOKED_MUTTON.ordinal()] = 423;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COOKED_RABBIT.ordinal()] = 411;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.COOKIE.ordinal()] = 356;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CROPS.ordinal()] = 60;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CYAN_GLAZED_TERRACOTTA.ordinal()] = 245;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.CYAN_SHULKER_BOX.ordinal()] = 229;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DARK_OAK_DOOR.ordinal()] = 198;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DARK_OAK_DOOR_ITEM.ordinal()] = 430;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DARK_OAK_FENCE.ordinal()] = 192;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DARK_OAK_FENCE_GATE.ordinal()] = 187;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DARK_OAK_STAIRS.ordinal()] = 165;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DAYLIGHT_DETECTOR.ordinal()] = 152;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DAYLIGHT_DETECTOR_INVERTED.ordinal()] = 179;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DEAD_BUSH.ordinal()] = 33;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DETECTOR_RAIL.ordinal()] = 29;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND.ordinal()] = 263;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_AXE.ordinal()] = 278;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_BARDING.ordinal()] = 418;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_BLOCK.ordinal()] = 58;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_BOOTS.ordinal()] = 312;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_CHESTPLATE.ordinal()] = 310;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_HELMET.ordinal()] = 309;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_HOE.ordinal()] = 292;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_LEGGINGS.ordinal()] = 311;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_ORE.ordinal()] = 57;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_PICKAXE.ordinal()] = 277;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_SPADE.ordinal()] = 276;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIAMOND_SWORD.ordinal()] = 275;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIODE.ordinal()] = 355;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIODE_BLOCK_OFF.ordinal()] = 94;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIODE_BLOCK_ON.ordinal()] = 95;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DIRT.ordinal()] = 4;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DISPENSER.ordinal()] = 24;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DOUBLE_PLANT.ordinal()] = 176;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DOUBLE_STEP.ordinal()] = 44;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DOUBLE_STONE_SLAB2.ordinal()] = 182;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DRAGONS_BREATH.ordinal()] = 436;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DRAGON_EGG.ordinal()] = 123;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.DROPPER.ordinal()] = 159;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.EGG.ordinal()] = 343;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ELYTRA.ordinal()] = 442;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.EMERALD.ordinal()] = 387;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.EMERALD_BLOCK.ordinal()] = 134;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.EMERALD_ORE.ordinal()] = 130;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.EMPTY_MAP.ordinal()] = 394;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ENCHANTED_BOOK.ordinal()] = 402;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ENCHANTMENT_TABLE.ordinal()] = 117;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ENDER_CHEST.ordinal()] = 131;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ENDER_PEARL.ordinal()] = 367;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ENDER_PORTAL.ordinal()] = 120;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ENDER_PORTAL_FRAME.ordinal()] = 121;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ENDER_STONE.ordinal()] = 122;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.END_BRICKS.ordinal()] = 207;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.END_CRYSTAL.ordinal()] = 425;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.END_GATEWAY.ordinal()] = 210;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.END_ROD.ordinal()] = 199;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.EXPLOSIVE_MINECART.ordinal()] = 406;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.EXP_BOTTLE.ordinal()] = 383;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.EYE_OF_ENDER.ordinal()] = 380;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FEATHER.ordinal()] = 287;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FENCE.ordinal()] = 86;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FENCE_GATE.ordinal()] = 108;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FERMENTED_SPIDER_EYE.ordinal()] = 375;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FIRE.ordinal()] = 52;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FIREBALL.ordinal()] = 384;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FIREWORK.ordinal()] = 400;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FIREWORK_CHARGE.ordinal()] = 401;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FISHING_ROD.ordinal()] = 345;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FLINT.ordinal()] = 317;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FLINT_AND_STEEL.ordinal()] = 258;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FLOWER_POT.ordinal()] = 141;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FLOWER_POT_ITEM.ordinal()] = 389;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FROSTED_ICE.ordinal()] = 213;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.FURNACE.ordinal()] = 62;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GHAST_TEAR.ordinal()] = 369;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GLASS.ordinal()] = 21;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GLASS_BOTTLE.ordinal()] = 373;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GLOWING_REDSTONE_ORE.ordinal()] = 75;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GLOWSTONE.ordinal()] = 90;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GLOWSTONE_DUST.ordinal()] = 347;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLDEN_APPLE.ordinal()] = 321;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLDEN_CARROT.ordinal()] = 395;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_AXE.ordinal()] = 285;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_BARDING.ordinal()] = 417;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_BLOCK.ordinal()] = 42;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_BOOTS.ordinal()] = 316;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_CHESTPLATE.ordinal()] = 314;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_HELMET.ordinal()] = 313;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_HOE.ordinal()] = 293;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_INGOT.ordinal()] = 265;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_LEGGINGS.ordinal()] = 315;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_NUGGET.ordinal()] = 370;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_ORE.ordinal()] = 15;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_PICKAXE.ordinal()] = 284;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_PLATE.ordinal()] = 148;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_RECORD.ordinal()] = 452;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_SPADE.ordinal()] = 283;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GOLD_SWORD.ordinal()] = 282;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GRASS.ordinal()] = 3;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GRASS_PATH.ordinal()] = 209;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GRAVEL.ordinal()] = 14;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GRAY_GLAZED_TERRACOTTA.ordinal()] = 243;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GRAY_SHULKER_BOX.ordinal()] = 227;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GREEN_GLAZED_TERRACOTTA.ordinal()] = 249;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GREEN_RECORD.ordinal()] = 453;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GREEN_SHULKER_BOX.ordinal()] = 233;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.GRILLED_PORK.ordinal()] = 319;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.HARD_CLAY.ordinal()] = 173;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.HAY_BLOCK.ordinal()] = 171;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.HOPPER.ordinal()] = 155;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.HOPPER_MINECART.ordinal()] = 407;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.HUGE_MUSHROOM_1.ordinal()] = 100;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.HUGE_MUSHROOM_2.ordinal()] = 101;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ICE.ordinal()] = 80;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.INK_SACK.ordinal()] = 350;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_AXE.ordinal()] = 257;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_BARDING.ordinal()] = 416;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_BLOCK.ordinal()] = 43;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_BOOTS.ordinal()] = 308;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_CHESTPLATE.ordinal()] = 306;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_DOOR.ordinal()] = 329;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_DOOR_BLOCK.ordinal()] = 72;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_FENCE.ordinal()] = 102;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_HELMET.ordinal()] = 305;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_HOE.ordinal()] = 291;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_INGOT.ordinal()] = 264;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_LEGGINGS.ordinal()] = 307;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_NUGGET.ordinal()] = 450;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_ORE.ordinal()] = 16;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_PICKAXE.ordinal()] = 256;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_PLATE.ordinal()] = 149;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_SPADE.ordinal()] = 255;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_SWORD.ordinal()] = 266;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.IRON_TRAPDOOR.ordinal()] = 168;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ITEM_FRAME.ordinal()] = 388;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.JACK_O_LANTERN.ordinal()] = 92;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.JUKEBOX.ordinal()] = 85;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.JUNGLE_DOOR.ordinal()] = 196;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.JUNGLE_DOOR_ITEM.ordinal()] = 428;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.JUNGLE_FENCE.ordinal()] = 191;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.JUNGLE_FENCE_GATE.ordinal()] = 186;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.JUNGLE_WOOD_STAIRS.ordinal()] = 137;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.KNOWLEDGE_BOOK.ordinal()] = 451;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LADDER.ordinal()] = 66;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LAPIS_BLOCK.ordinal()] = 23;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LAPIS_ORE.ordinal()] = 22;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LAVA.ordinal()] = 11;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LAVA_BUCKET.ordinal()] = 326;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LEASH.ordinal()] = 419;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LEATHER.ordinal()] = 333;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LEATHER_BOOTS.ordinal()] = 300;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LEATHER_CHESTPLATE.ordinal()] = 298;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LEATHER_HELMET.ordinal()] = 297;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LEATHER_LEGGINGS.ordinal()] = 299;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LEAVES.ordinal()] = 19;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LEAVES_2.ordinal()] = 162;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LEVER.ordinal()] = 70;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LIGHT_BLUE_GLAZED_TERRACOTTA.ordinal()] = 239;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LIGHT_BLUE_SHULKER_BOX.ordinal()] = 223;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LIME_GLAZED_TERRACOTTA.ordinal()] = 241;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LIME_SHULKER_BOX.ordinal()] = 225;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LINGERING_POTION.ordinal()] = 440;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LOG.ordinal()] = 18;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LOG_2.ordinal()] = 163;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.LONG_GRASS.ordinal()] = 32;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MAGENTA_GLAZED_TERRACOTTA.ordinal()] = 238;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MAGENTA_SHULKER_BOX.ordinal()] = 222;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MAGMA.ordinal()] = 214;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MAGMA_CREAM.ordinal()] = 377;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MAP.ordinal()] = 357;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MELON.ordinal()] = 359;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MELON_BLOCK.ordinal()] = 104;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MELON_SEEDS.ordinal()] = 361;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MELON_STEM.ordinal()] = 106;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MILK_BUCKET.ordinal()] = 334;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MINECART.ordinal()] = 327;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MOB_SPAWNER.ordinal()] = 53;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MONSTER_EGG.ordinal()] = 382;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MONSTER_EGGS.ordinal()] = 98;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MOSSY_COBBLESTONE.ordinal()] = 49;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MUSHROOM_SOUP.ordinal()] = 281;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MUTTON.ordinal()] = 422;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.MYCEL.ordinal()] = 111;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NAME_TAG.ordinal()] = 420;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NETHERRACK.ordinal()] = 88;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NETHER_BRICK.ordinal()] = 113;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NETHER_BRICK_ITEM.ordinal()] = 404;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NETHER_BRICK_STAIRS.ordinal()] = 115;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NETHER_FENCE.ordinal()] = 114;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NETHER_STALK.ordinal()] = 371;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NETHER_STAR.ordinal()] = 398;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NETHER_WARTS.ordinal()] = 116;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NETHER_WART_BLOCK.ordinal()] = 215;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.NOTE_BLOCK.ordinal()] = 26;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.OBSERVER.ordinal()] = 219;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.OBSIDIAN.ordinal()] = 50;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ORANGE_GLAZED_TERRACOTTA.ordinal()] = 237;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ORANGE_SHULKER_BOX.ordinal()] = 221;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PACKED_ICE.ordinal()] = 175;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PAINTING.ordinal()] = 320;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PAPER.ordinal()] = 338;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PINK_GLAZED_TERRACOTTA.ordinal()] = 242;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PINK_SHULKER_BOX.ordinal()] = 226;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PISTON_BASE.ordinal()] = 34;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PISTON_EXTENSION.ordinal()] = 35;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PISTON_MOVING_PIECE.ordinal()] = 37;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PISTON_STICKY_BASE.ordinal()] = 30;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.POISONOUS_POTATO.ordinal()] = 393;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PORK.ordinal()] = 318;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PORTAL.ordinal()] = 91;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.POTATO.ordinal()] = 143;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.POTATO_ITEM.ordinal()] = 391;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.POTION.ordinal()] = 372;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.POWERED_MINECART.ordinal()] = 342;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.POWERED_RAIL.ordinal()] = 28;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PRISMARINE.ordinal()] = 169;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PRISMARINE_CRYSTALS.ordinal()] = 409;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PRISMARINE_SHARD.ordinal()] = 408;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PUMPKIN.ordinal()] = 87;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PUMPKIN_PIE.ordinal()] = 399;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PUMPKIN_SEEDS.ordinal()] = 360;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PUMPKIN_STEM.ordinal()] = 105;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PURPLE_GLAZED_TERRACOTTA.ordinal()] = 246;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PURPLE_SHULKER_BOX.ordinal()] = 230;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PURPUR_BLOCK.ordinal()] = 202;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PURPUR_DOUBLE_SLAB.ordinal()] = 205;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PURPUR_PILLAR.ordinal()] = 203;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PURPUR_SLAB.ordinal()] = 206;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.PURPUR_STAIRS.ordinal()] = 204;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.QUARTZ.ordinal()] = 405;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.QUARTZ_BLOCK.ordinal()] = 156;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.QUARTZ_ORE.ordinal()] = 154;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.QUARTZ_STAIRS.ordinal()] = 157;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RABBIT.ordinal()] = 410;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RABBIT_FOOT.ordinal()] = 413;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RABBIT_HIDE.ordinal()] = 414;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RABBIT_STEW.ordinal()] = 412;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RAILS.ordinal()] = 67;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RAW_BEEF.ordinal()] = 362;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RAW_CHICKEN.ordinal()] = 364;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RAW_FISH.ordinal()] = 348;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RECORD_10.ordinal()] = 461;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RECORD_11.ordinal()] = 462;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RECORD_12.ordinal()] = 463;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RECORD_3.ordinal()] = 454;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RECORD_4.ordinal()] = 455;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RECORD_5.ordinal()] = 456;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RECORD_6.ordinal()] = 457;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RECORD_7.ordinal()] = 458;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RECORD_8.ordinal()] = 459;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RECORD_9.ordinal()] = 460;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE.ordinal()] = 330;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE_BLOCK.ordinal()] = 153;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE_COMPARATOR.ordinal()] = 403;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE_COMPARATOR_OFF.ordinal()] = 150;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE_COMPARATOR_ON.ordinal()] = 151;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE_LAMP_OFF.ordinal()] = 124;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE_LAMP_ON.ordinal()] = 125;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE_ORE.ordinal()] = 74;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE_TORCH_OFF.ordinal()] = 76;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE_TORCH_ON.ordinal()] = 77;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.REDSTONE_WIRE.ordinal()] = 56;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RED_GLAZED_TERRACOTTA.ordinal()] = 250;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RED_MUSHROOM.ordinal()] = 41;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RED_NETHER_BRICK.ordinal()] = 216;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RED_ROSE.ordinal()] = 39;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RED_SANDSTONE.ordinal()] = 180;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RED_SANDSTONE_STAIRS.ordinal()] = 181;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.RED_SHULKER_BOX.ordinal()] = 234;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.ROTTEN_FLESH.ordinal()] = 366;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SADDLE.ordinal()] = 328;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SAND.ordinal()] = 13;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SANDSTONE.ordinal()] = 25;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SANDSTONE_STAIRS.ordinal()] = 129;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SAPLING.ordinal()] = 7;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SEA_LANTERN.ordinal()] = 170;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SEEDS.ordinal()] = 294;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SHEARS.ordinal()] = 358;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SHIELD.ordinal()] = 441;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SHULKER_SHELL.ordinal()] = 449;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SIGN.ordinal()] = 322;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SIGN_POST.ordinal()] = 64;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SILVER_GLAZED_TERRACOTTA.ordinal()] = 244;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SILVER_SHULKER_BOX.ordinal()] = 228;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SKULL.ordinal()] = 145;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SKULL_ITEM.ordinal()] = 396;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SLIME_BALL.ordinal()] = 340;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SLIME_BLOCK.ordinal()] = 166;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SMOOTH_BRICK.ordinal()] = 99;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SMOOTH_STAIRS.ordinal()] = 110;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SNOW.ordinal()] = 79;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SNOW_BALL.ordinal()] = 331;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SNOW_BLOCK.ordinal()] = 81;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SOIL.ordinal()] = 61;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SOUL_SAND.ordinal()] = 89;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SPECKLED_MELON.ordinal()] = 381;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SPECTRAL_ARROW.ordinal()] = 438;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SPIDER_EYE.ordinal()] = 374;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SPLASH_POTION.ordinal()] = 437;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SPONGE.ordinal()] = 20;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SPRUCE_DOOR.ordinal()] = 194;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SPRUCE_DOOR_ITEM.ordinal()] = 426;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SPRUCE_FENCE.ordinal()] = 189;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SPRUCE_FENCE_GATE.ordinal()] = 184;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SPRUCE_WOOD_STAIRS.ordinal()] = 135;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STAINED_CLAY.ordinal()] = 160;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STAINED_GLASS.ordinal()] = 96;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STAINED_GLASS_PANE.ordinal()] = 161;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STANDING_BANNER.ordinal()] = 177;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STATIONARY_LAVA.ordinal()] = 12;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STATIONARY_WATER.ordinal()] = 10;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STEP.ordinal()] = 45;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STICK.ordinal()] = 279;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STONE.ordinal()] = 2;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STONE_AXE.ordinal()] = 274;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STONE_BUTTON.ordinal()] = 78;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STONE_HOE.ordinal()] = 290;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STONE_PICKAXE.ordinal()] = 273;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STONE_PLATE.ordinal()] = 71;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STONE_SLAB2.ordinal()] = 183;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STONE_SPADE.ordinal()] = 272;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STONE_SWORD.ordinal()] = 271;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STORAGE_MINECART.ordinal()] = 341;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STRING.ordinal()] = 286;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STRUCTURE_BLOCK.ordinal()] = 254;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.STRUCTURE_VOID.ordinal()] = 218;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SUGAR.ordinal()] = 352;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SUGAR_CANE.ordinal()] = 337;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SUGAR_CANE_BLOCK.ordinal()] = 84;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.SULPHUR.ordinal()] = 288;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.THIN_GLASS.ordinal()] = 103;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.TIPPED_ARROW.ordinal()] = 439;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.TNT.ordinal()] = 47;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.TORCH.ordinal()] = 51;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.TOTEM.ordinal()] = 448;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.TRAPPED_CHEST.ordinal()] = 147;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.TRAP_DOOR.ordinal()] = 97;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.TRIPWIRE.ordinal()] = 133;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.TRIPWIRE_HOOK.ordinal()] = 132;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.VINE.ordinal()] = 107;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WALL_BANNER.ordinal()] = 178;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WALL_SIGN.ordinal()] = 69;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WATCH.ordinal()] = 346;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WATER.ordinal()] = 9;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WATER_BUCKET.ordinal()] = 325;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WATER_LILY.ordinal()] = 112;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WEB.ordinal()] = 31;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WHEAT.ordinal()] = 295;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WHITE_GLAZED_TERRACOTTA.ordinal()] = 236;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WHITE_SHULKER_BOX.ordinal()] = 220;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD.ordinal()] = 6;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOODEN_DOOR.ordinal()] = 65;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_AXE.ordinal()] = 270;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_BUTTON.ordinal()] = 144;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_DOOR.ordinal()] = 323;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_DOUBLE_STEP.ordinal()] = 126;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_HOE.ordinal()] = 289;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_PICKAXE.ordinal()] = 269;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_PLATE.ordinal()] = 73;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_SPADE.ordinal()] = 268;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_STAIRS.ordinal()] = 54;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_STEP.ordinal()] = 127;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOD_SWORD.ordinal()] = 267;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WOOL.ordinal()] = 36;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WORKBENCH.ordinal()] = 59;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.WRITTEN_BOOK.ordinal()] = 386;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.YELLOW_FLOWER.ordinal()] = 38;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.YELLOW_GLAZED_TERRACOTTA.ordinal()] = 240;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		try {
			arrn[Material.YELLOW_SHULKER_BOX.ordinal()] = 224;
		} catch (NoSuchFieldError noSuchFieldError) {
		}
		$SWITCH_TABLE$org$bukkit$Material = arrn;
		return $SWITCH_TABLE$org$bukkit$Material;
	}
}
