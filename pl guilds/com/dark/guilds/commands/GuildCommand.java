/*
ffffff * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.ClickEvent
 *  net.md_5.bungee.api.chat.ClickEvent$Action
 *  net.md_5.bungee.api.chat.TextComponent
 *  net.milkbowl.vault.economy.Economy
 *  net.milkbowl.vault.economy.EconomyResponse
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Player$Spigot
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.SkullMeta
 *  org.bukkit.plugin.Plugin
 */
package com.dark.guilds.commands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import com.dark.guilds.Guild;
import com.dark.guilds.GuildStatus;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.GuildPermission;
import com.dark.guilds.players.GuildPlayer;
import com.dark.guilds.players.GuildRank;
import com.dark.guilds.utils.ConfFile;
import com.dark.guilds.utils.Menu;
import com.dark.guilds.utils.Utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.EconomyResponse;

public class GuildCommand implements CommandExecutor {
	Guilds guilds;

	public GuildCommand(Guilds guilds) {
		this.guilds = guilds;
	}

	@SuppressWarnings({ "unused", "deprecation" })
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("guilde")) {
			if (sender instanceof Player) {
				Guild guild;
				int i;
				Player player = (Player) sender;
				if (args.length == 0) {
					player.sendMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----");
					player.sendMessage("§6/g help §eVous affiche ce message");
					player.sendMessage("§6/g info [Guilde] §eVous affiche les informations de votre guilde");
					player.sendMessage("§6/g join <Guilde> §eVous permet de rejoindre une guilde, si elle est ouverte");
					player.sendMessage(
							"§6/g invite <Joueur> §eEnvoie une invitation \u00c3\u00a0 rejoindre la guilde au joueur donn\u00e9");
					player.sendMessage("§6/g menu §eOuvre le menu de guilde");
					player.sendMessage("§6/g quota §eOuvre le menu des quotas de guilde");
					player.sendMessage("§6/g players §eOuvre le menu des joueurs d'une guilde");
					player.sendMessage("§6/g list §eVous affiche la liste des guildes actuelles");
					if (guilds.hasGuild(player) && guilds.getGuildPlayer(player).hasPermission(GuildPermission.ALL)) {
						player.sendMessage(
								"§6/g accept [Nom du joueur] §eAccepte la demande du joueur dans votre guilde");
					}
					if (player.isOp()) {
						player.sendMessage(
								"§6/g delete <nom de guilde> §3Supprime une guilde guilde (pour op\u00e9rateur)");
						player.sendMessage(
								"§6/g [xpgive:xptake] <nom de guilde> <nombre d'xp> §3 Ajoute ou enlève de l'expérience à une guilde guilde (pour op\u00e9rateur)");
					}
					player.sendMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----");
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("menu")) {
						if (this.guilds.hasGuild(player)) {
							int suposedLvl;
							int lvl;
							GuildPlayer playerG = Guilds.getInstance().getGuildPlayer((OfflinePlayer) player);
							Guild g = playerG.getGuild();
							if (playerG.getGuild().isCreator(playerG)
									&& (lvl = g.getLevel()) > (suposedLvl = (g.getMaxSize() - 10) / 4
											+ (g.getFieldSide() - 30) / 15)) {
								Menu menu = new Menu(player, this.guilds, this.guilds.getGuild(player),
										(OfflinePlayer) player);
								player.openInventory(menu.getMenu("LvlUp", player));
								return false;
							}
							Menu menu = new Menu(player, this.guilds, this.guilds.getGuild(player),
									(OfflinePlayer) player);
							player.openInventory(menu.getMenu("guilde", player));
						} else {
							Menu menu = new Menu(player, this.guilds, null, (OfflinePlayer) player);
							player.openInventory(menu.getMenu("guilde", player));
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("list")) {
						Guilds.getInstance();
						if (Guilds.guilds.size() == 0) {
							player.sendMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----");
							player.sendMessage("Il n'y a actuellement aucune guilde !");
							player.sendMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----");
							return false;
						}
						String s = "";
						i = 0;
						Guilds.getInstance();
						player.sendMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----§r\n ");
						for (Guild g : Guilds.guilds) {
							s = "§r§a" + g.getName() + " : \n" + "   §r§6Chef de guilde : "
									+ g.getCreator().getPlayer().getName() + "\n   §6Membres : "
									+ g.getGuildMembers().size() + "/" + g.getMaxSize() + "\n   §6Niveau de guilde : "
									+ g.getLevel();
							Guilds.getInstance();

							player.sendMessage(s + "\n ");

							if (++i == Guilds.guilds.size())
								continue;
						}
						player.sendMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----");
					} else if (args[0].equalsIgnoreCase("info")) {
						guild = this.guilds.getGuild(player);
						if (guild == null) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4Vous ne poss\u00e9dez pas de guilde"));
							return false;
						}
						this.sendInfo(guild, player);
					} else if (args[0].equalsIgnoreCase("help")) {
						player.sendMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----");
						player.sendMessage("§6/g help §eVous affiche ce message");
						player.sendMessage("§6/g info [Guilde] §eVous affiche les informations de votre guilde");
						player.sendMessage(
								"§6/g join <Guilde> §eVous permet de rejoindre une guilde, si elle est ouverte");
						player.sendMessage(
								"§6/g invite <Joueur> §eEnvoie une invitation \u00c3\u00a0 rejoindre la guilde au joueur donn\u00e9");
						player.sendMessage("§6/g menu §eOuvre le menu de guilde");
						player.sendMessage("§6/g quota §eOuvre le menu des quotas de guilde");
						if (player.isOp()) {
							player.sendMessage(
									"§6/g delete <nom de guilde> §3Supprime une guilde guilde (pour op\u00e9rateur)");
							player.sendMessage(
									"§6/g [xpgive:xptake] <nom de guilde> <nombre d'xp> §3 Ajoute ou enlève de l'expérience à une guilde guilde (pour op\u00e9rateur)");
						}
						player.sendMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----");
					}
					if (args[0].equalsIgnoreCase("quota")) {
						if (!this.guilds.hasGuild(player)) {
							player.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas de guilde !");
							return false;
						}
						player.openInventory(
								Utils.getQuota(this.guilds.getGuild(player).getLevel(), this.guilds.getGuild(player)));
					}
				} else if (args.length == 2) {
					Player member;
					if (args[0].equalsIgnoreCase("players")) {
						guild = this.guilds.getGuildByName(args[1]);
						if (guild == null) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4La guilde n'existe pas"));
							return false;
						}
						Inventory inv = Bukkit.createInventory(null, (int) 36, (String) "Liste des joueurs");
						int place = 1;
						for (GuildPlayer pls : guild.getGuildMembers()) {
							ItemStack i2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
							SkullMeta iM = (SkullMeta) i2.getItemMeta();
							iM.setOwningPlayer(pls.getPlayer());
							if (pls.getRank() == GuildRank.BOSS) {
								iM.setDisplayName(pls.getPlayer().getName());
							} else {
								iM.setDisplayName((Object) ChatColor.GOLD + "[Boss]" + pls.getPlayer().getName());
							}
							i2.setItemMeta((ItemMeta) iM);
							if (pls.getRank() == GuildRank.BOSS) {
								inv.setItem(0, i2);
								continue;
							}
							inv.setItem(place, i2);
							++place;
						}
						player.openInventory(inv);
					}
					if (args[0].equalsIgnoreCase("accept")) {
						if (guilds.getGuildPlayer(player).hasPermission(GuildPermission.ADMIN)) {
							Guild playerGuild = guilds.getGuild(player);
							if (new File(
									guilds.getDataFolder() + "/" + args[1] + "-" + guilds.getGuild(player).getName())
											.exists()) {

								Player p = guilds.getServer().getPlayer(args[1]);

								if (guilds.getGuild(p) == null) {
									ArrayList<GuildPermission> perms = new ArrayList<GuildPermission>();
									perms.add(GuildPermission.NOTHING);
									playerGuild.addGuildMembers(new GuildPlayer(playerGuild, (OfflinePlayer) player,
											GuildRank.MEMBER, perms));
									this.guilds.loadPlayers();
									player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
											(String) ("&a[&2Guildes&a] &2Le joueur " + args[1]
													+ "a rejoint votre guilde !")));
									Bukkit.dispatchCommand(player, "mail send " + args[1]
											+ " Vous avez été accepté dans la guilde" + playerGuild.getName());

									new File(guilds.getDataFolder() + "/" + args[1] + "-"
											+ guilds.getGuild(player).getName()).delete();
								} else
									player.sendMessage("Ce joueur a déjà été accepté dans une autre guilde !");
							}
						} else
							player.sendMessage("§4Vous n'avez pas les permissions nécessaires !");

					}
					if (args[0].equalsIgnoreCase("invite")) {
						if (args[1].equalsIgnoreCase("accept")) {
							if (this.guilds.invitedPlayers.containsKey((Object) player)) {
								if (Guilds.getInstance()
										.containBanPlayer(Bukkit.getOfflinePlayer((String) player.getName())) != -1) {
									player.sendMessage(String.valueOf(ChatColor.translateAlternateColorCodes((char) '&',
											(String) new StringBuilder(
													"&c[&4Guildes&c] &4Vous ne pourrez rejoindre une guilde que dans ")
															.append(Guilds.getInstance().containBanPlayer(
																	Bukkit.getOfflinePlayer((String) player.getName())))
															.toString()))
											+ " minutes");
									return false;
								}
								Guild inviteGuild = this.guilds.invitedPlayers.get((Object) player);
								for (GuildPlayer guildMember : inviteGuild.getGuildMembers()) {
									if (!guildMember.getPlayer().isOnline())
										continue;
									member = guildMember.getPlayer().getPlayer();
									member.sendMessage("§eLe joueur §l§6" + player.getName()
											+ " §r§e fait d\u00e9sormais partie de votre guilde");
								}
								ArrayList<GuildPermission> perms = new ArrayList<GuildPermission>();
								perms.add(GuildPermission.NOTHING);
								inviteGuild.getGuildMembers().add(
										new GuildPlayer(inviteGuild, (OfflinePlayer) player, GuildRank.MEMBER, perms));
								this.guilds.loadPlayers();
								player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
										(String) ("&a[&2Guildes&a] &2Vous faites d\u00e9sormais partie de la guilde "
												+ inviteGuild.getName())));
								this.guilds.invitedPlayers.remove((Object) player);
								return true;
							}
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4Vous n'avez aucune invitation !"));
							return false;
						}
						if (args[1].equalsIgnoreCase("deny")) {
							if (this.guilds.invitedPlayers.containsKey((Object) player)) {
								player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
										(String) "&a[&2Guildes&a] &2Vous avez d\u00e9clin\u00e9 l'invitation"));
								Guild g = this.guilds.invitedPlayers.get((Object) player);
								for (GuildPlayer gp : g.getGuildMembers()) {
									if (!gp.getPlayer().isOnline())
										continue;
									Player p = (Player) gp.getPlayer();
									p.sendMessage((Object) ChatColor.RED + "[Guild]" + player.getName()
											+ " a refus\u00e9 de rejoindre votre guilde");
								}
								this.guilds.invitedPlayers.remove((Object) player);
								return true;
							}
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4Vous n'avez aucune invitation !"));
							return false;
						}
						Player invitedPlayer = Bukkit.getPlayer((String) args[1]);
						if (invitedPlayer == null) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4Le joueur n'existe pas"));
							return false;
						}
						if (this.guilds.hasGuild(invitedPlayer)) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4Le joueur a d\u00e9j\u00e0\u00a0 une guilde !"));
							return false;
						}
						Guild guild2 = this.guilds.getGuild(player);
						if (guild2.getStatus() == GuildStatus.CLOSED) {
							player.sendMessage(Utils.color(
									"&c[&4Guildes&c] &4Vous ne pouvez inviter personne, la guilde est ferm\u00e9e !"));
							return false;
						}
						if (guild2.getGuildMembers().size() >= guild2.getMaxSize()) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4La guilde contient d\u00e9j\u00e0\u00a0 son maximum de joueur !"));
							return false;
						}
						player.sendMessage("§a[§2Guildes§a] §2Vous avez invit\u00e9 " + invitedPlayer.getName()
								+ " \u00e0 rejoindre votre guilde");
						invitedPlayer.sendMessage("§eLa guilde §6§l" + guild2.getName()
								+ " §r§esouhaiterait vous recruter. Cette guilde est de niveau " + guild2.getLevel()
								+ " et" + " compte " + guild2.getGuildMembers().size() + " membres");
						TextComponent message = new TextComponent("[Accepter] ");
						message.setColor(ChatColor.GREEN);
						message.setBold(Boolean.valueOf(true));
						message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/g invite accept"));
						TextComponent no = new TextComponent("[Refuser]");
						no.setColor(ChatColor.RED);
						no.setBold(Boolean.valueOf(true));
						no.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/g invite deny"));
						message.addExtra((BaseComponent) no);
						invitedPlayer.spigot().sendMessage((BaseComponent) message);
						this.guilds.invitedPlayers.put(invitedPlayer, guild2);
					}
					if (args[0].equalsIgnoreCase("delete")) {
						if (!player.isOp()) {
							player.sendMessage((Object) ChatColor.RED
									+ "Vous n'avez pas la permission d'utilisez cette commande !");
							return false;
						}
						guild = null;
						boolean found = false;
						for (Guild guilds : Guilds.getInstance().getGuilds()) {
							if (!guilds.getName().equals(args[1]))
								continue;
							guild = guilds;
							found = true;
						}
						if (found) {
							player.sendMessage("§4Guilde à effacer : " + guild.getName());
							Guilds.getInstance().refreshGuild();
							Guilds.getInstance().refreshPlayersData();
							Guilds.deleted = true;
							Guilds.getInstance();
							Guilds.guilds.remove(guild);
							File f = new File(Guilds.getInstance().getDataFolder() + "\\" + guild.getName());
							Bukkit.getPluginManager().disablePlugin((Plugin) Guilds.getInstance());
							for (File currentFile : f.listFiles())
								currentFile.delete();

							f.delete();

							YamlConfiguration yc = YamlConfiguration.loadConfiguration(guilds.confCopy);
							yc.set("Guilds." + guild.getUniqueId().toString(), null);
							guilds.saveCustomYml((FileConfiguration) yc, guilds.confCopy);

							player.sendMessage((Object) ChatColor.RED + "La guilde " + guild.getName()
									+ " a été supprimée...\nReload du serveur...");
							Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), (String) "rl");
						} else {
							player.sendMessage((Object) ChatColor.RED + "Cette guilde n'existe pas");
						}
						return false;
					}
					if (args[0].equalsIgnoreCase("join")) {
						guild = this.guilds.getGuildByName(args[1]);
						if (guild == null) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4La guilde n'existe pas"));
							return false;
						}
						if (guild.getStatus() != GuildStatus.OPENED) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4La guilde n'est pas ouverte !"));
							return false;
						}
						if (guild.getGuildMembers().size() >= guild.getMaxSize()) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4La guilde contient d\u00e9j\u00c3\u00a0 son maximum de joueur !"));
							return false;
						}
						if (this.guilds.hasGuild(player)) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4Vous \u00c3\u00aates d\u00e9j\u00c3\u00a0 dans une guilde !"));
							return false;
						}
						for (GuildPlayer gMember : guild.getGuildMembers()) {
							if (!gMember.getPlayer().isOnline())
								continue;
							member = (Player) gMember.getPlayer();
							member.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', (String) ("§6§l"
									+ player.getName() + " §r§efait d\u00e9sormais partie de votre guilde !")));
						}
						ArrayList<GuildPermission> perms = new ArrayList<GuildPermission>();
						perms.add(GuildPermission.NOTHING);
						guild.addGuildMembers(new GuildPlayer(guild, (OfflinePlayer) player, GuildRank.MEMBER, perms));
						this.guilds.loadPlayers();
						player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
								(String) ("&a[&2Guildes&a] &2Vous faites d\u00e9sormais partie de la guilde "
										+ guild.getName())));
					}
					if (args[0].equalsIgnoreCase("info")) {
						guild = this.guilds.getGuildByName(args[1]);
						if (guild == null) {
							OfflinePlayer offline = Bukkit.getOfflinePlayer((String) args[1]);
							if (!offline.hasPlayedBefore()) {
								player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
										(String) "&c[&4Guildes&c] &4Ce joueur n'existe pas"));
								return false;
							}
							if (Guilds.getInstance().getGuildPlayer(offline) == null) {
								player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
										(String) "&c[&4Guildes&c] &4Ce joueur n'a pas de guilde"));
								return false;
							}
							guild = Guilds.getInstance().getGuildPlayer(offline).getGuild();
						}
						this.sendInfo(guild, player);
						return true;
					}
					if (args[0].equalsIgnoreCase("rename")) {
						int price = this.guilds.getConfig().getInt("Guilds.BasicConfiguration.GuildRenameCost");
						if (this.guilds.getEconomy().getBalance((OfflinePlayer) player) >= (double) price) {
							EconomyResponse r = this.guilds.getEconomy().withdrawPlayer((OfflinePlayer) player,
									(double) price);
							String newName = args[1];
							if (this.guilds.nameExist(newName)) {
								player.sendMessage(
										Utils.color("&c[&4Guildes&c] &4Ce nom existe d\u00e9j\u00c3\u00a0 !"));
								return false;
							}
							Guild guild3 = this.guilds.getGuild(player);
							if (!this.guilds.getGuildPlayer((OfflinePlayer) player)
									.hasPermission(GuildPermission.RENAME)) {
								player.sendMessage(Utils.color(
										"&c[&4Guildes&c] &4Vous n'avez pas les permissions pour renommer cette guilde"));
								return false;
							}
							ConfFile.deleteFolder(ConfFile.getFolder(this.guilds, guild3));
							guild3.setName(newName);
							player.sendMessage(Utils.color(
									"&a[&2Guildes&a] &2Vous avez renomm\u00e9 votre guilde avec succ\u00c3\u00a8s !"));
						} else {
							player.sendMessage(
									Utils.color(this.guilds.getConfig().getString("Guilds.Messages.NotEnoughMoney")));
						}
					}
					if (args[0].equalsIgnoreCase("remove")) {
						if (!player.hasPermission("guild.remove")) {
							player.sendMessage(Utils.color(
									"&c[&4Guildes&c] &4Vous n'avez pas la permission d'enlever un joueur de sa guilde"));
							return false;
						}
						Player removedPlayer = Bukkit.getPlayer((String) args[1]);
						if (removedPlayer == null) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4Le joueur n'existe pas"));
							return false;
						}
						if (!this.guilds.hasGuild(removedPlayer)) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4Le joueur n'a pas de guilde !"));
							return false;
						}
						Guild guild4 = this.guilds.getGuild(removedPlayer);
						guild4.removeGuildMember(this.guilds.getGuildPlayer((OfflinePlayer) removedPlayer));
						this.guilds.deletePlayerDataFile(removedPlayer);
						player.sendMessage(Utils.color(
								"&a[&2Guildes&a] &2Vous avez correctement enlev\u00e9 ce joueur de sa guilde !"));
						if (guild4.getGuildMembers().size() == 0) {
							Bukkit.broadcastMessage((String) Utils
									.color("&eLa guilde &6" + guild4.getName() + " &ea \u00e9t\u00e9 dissoute"));
							this.guilds.getGuilds().remove(guild4);
							YamlConfiguration yc = YamlConfiguration.loadConfiguration((File) Guilds.confFile);
							yc.set("Guilds." + guild4.getUniqueId().toString(), null);
							this.guilds.saveCustomYml((FileConfiguration) yc, Guilds.confFile);
							ConfFile.deleteFolder(ConfFile.getFolder(this.guilds, guild4));
						}
					}

					StringBuilder sb;
					if (args[0].equalsIgnoreCase("info")) {
						sb = new StringBuilder();
						i = 1;
						while (i < args.length) {
							if (i != args.length - 1) {
								sb.append(String.valueOf(args[i]) + " ");
							} else {
								sb.append(args[i]);
							}
							++i;
						}
						Guild guild5 = this.guilds.getGuildByName(sb.toString());
						if (guild5 == null) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4La guilde n'existe pas"));
							return false;
						}
						player.sendMessage("§a§m-----------------------------");
						player.sendMessage("§a               " + guild5.getName());
						player.sendMessage("§aNiveau: " + guild5.getLevel() + "           Membres: "
								+ guild5.getGuildMembers().size() + "/" + guild5.getMaxSize());
						player.sendMessage("§aExp\u00e9rience: " + guild5.getXp() + "     Terrain: "
								+ guild5.getFieldSide() + "x" + guild5.getFieldSide());
						player.sendMessage("§a" + Utils.isInConflict(guild5.isInConflict()));
						player.sendMessage("§a§m-----------------------------");
						player.sendMessage("&a Description :" + guild5.getDescription());
						player.sendMessage("§a§m-----------------------------");
					} else if (args[0].equalsIgnoreCase("join")) {
						sb = new StringBuilder();
						i = 1;
						while (i < args.length) {
							if (i != args.length - 1) {
								sb.append(String.valueOf(args[i]) + " ");
							} else {
								sb.append(args[i]);
							}
							++i;
						}
						Guild guild6 = this.guilds.getGuildByName(sb.toString());
						if (guild6 == null) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4La guilde n'existe pas"));
							return false;
						}
						if (guild6.getStatus() != GuildStatus.OPENED) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4La guilde n'est pas ouverte !"));
							return false;
						}
						if (guild6.getGuildMembers().size() >= guild6.getMaxSize()) {
							player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
									(String) "&c[&4Guildes&c] &4La guilde contient d\u00e9j\u00c3\u00a0 son maximum de joueur !"));
							return false;
						}
						for (GuildPlayer gMember : guild6.getGuildMembers()) {
							if (!gMember.getPlayer().isOnline())
								continue;
							Player member2 = (Player) gMember;
							member2.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', (String) ("§6§l"
									+ player.getName() + " §r§efait d\u00e9sormais partie de votre guilde !")));
						}
						ArrayList<GuildPermission> perms = new ArrayList<GuildPermission>();
						perms.add(GuildPermission.NOTHING);
						guild6.addGuildMembers(
								new GuildPlayer(guild6, (OfflinePlayer) player, GuildRank.MEMBER, perms));
						this.guilds.loadPlayers();
						player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
								(String) ("&a[&2Guildes&a] &2Vous faites d\u00e9sormais partie de la guilde "
										+ guild6.getName())));
					}
				} else if (args.length > 2) {
					if (args[0].equalsIgnoreCase("xpgive")) {
						if (!player.isOp()) {
							return false;
						}
						guild = null;
						for (Guild guilds : Guilds.getInstance().getGuilds()) {
							if (!guilds.getName().equals(args[1]))
								continue;
							guild = guilds;
						}
						if (guild == null) {
							player.sendMessage((Object) ChatColor.RED + "Cette guilde n'existe pas");
							return false;
						}
						guild.addXp(Integer.parseInt(args[2]));
						player.sendMessage((Object) ChatColor.GREEN + "Vous avez correctement donn\u00e9 " + args[2]
								+ "xp \u00c3\u00a0 la guilde " + args[1]);
						return false;
					}
					if (args[0].equalsIgnoreCase("xptake")) {
						if (!player.isOp()) {
							return false;
						}
						guild = null;
						for (Guild guilds : Guilds.getInstance().getGuilds()) {
							if (!guilds.getName().equals(args[1]))
								continue;
							guild = guilds;
						}
						if (guild == null) {
							player.sendMessage((Object) ChatColor.RED + "Cette guilde n'existe pas");
							return false;
						}
						guild.removeXp(Integer.parseInt(args[2]));
						player.sendMessage((Object) ChatColor.GREEN + "Vous avez correctement pris " + args[2]
								+ "xp \u00c3\u00a0 la guilde " + args[1]);
						return false;
					}
				}
			} else {
				sender.sendMessage(Utils.color(this.guilds.getConfig().getString("Guilds.Messages.Console")));
			}
		}
		return false;
	}

	private void sendInfo(Guild guild, Player player) {
		player.sendMessage("§a§m-----------------------------");
		player.sendMessage("§a               " + guild.getName());
		player.sendMessage("§aNiveau: " + guild.getLevel() + "           Membres: " + guild.getGuildMembers().size()
				+ "/" + guild.getMaxSize());
		player.sendMessage("§aExp\u00e9rience: " + guild.getXp() + "     Terrain: " + guild.getFieldSide() + "x"
				+ guild.getFieldSide());
		player.sendMessage("§a" + Utils.isInConflict(guild.isInConflict()));
		player.sendMessage("§aDesciption: " + guild.getDescription());
		TextComponent message = new TextComponent((Object) ChatColor.GOLD + "[Liste des membres] ");
		message.setColor(ChatColor.GREEN);
		message.setBold(Boolean.valueOf(true));
		message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/g players " + guild.getName()));
		player.spigot().sendMessage((BaseComponent) message);
		player.sendMessage("§a§m-----------------------------");
	}
}