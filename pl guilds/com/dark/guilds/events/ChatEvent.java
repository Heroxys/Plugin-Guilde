/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.milkbowl.vault.chat.Chat
 *  net.milkbowl.vault.economy.Economy
 *  net.milkbowl.vault.economy.EconomyResponse
 *  net.milkbowl.vault.permission.Permission
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.World
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.AsyncPlayerChatEvent
 */
package com.dark.guilds.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.GuildPlayer;
import com.dark.guilds.utils.ConfFile;
import com.dark.guilds.utils.Utils;

public class ChatEvent implements Listener {
	Guilds guilds;

	public ChatEvent(Guilds guilds) {
		this.guilds = guilds;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		String tag;
		Guild guild;
		if (this.guilds.adminChangeTag.contains((Object) e.getPlayer())) {
			tag = e.getMessage();
			guild = this.guilds.getGuild(e.getPlayer());
			guild.setTag(tag);
			e.getPlayer().sendMessage(
					"\u00a7a[\u00a72Guildes\u00a7a] \u00a72Vous avez chang\u00e9 la couleur de votre team ! ("
							+ ChatColor.translateAlternateColorCodes((char) '&', (String) e.getMessage())
							+ "Couleur\u00a72)");
			e.setCancelled(true);
			this.guilds.adminChangeTag.remove((Object) e.getPlayer());
		}
		if (this.guilds.adminChangeDesc.contains((Object) e.getPlayer())) {
			tag = e.getMessage();
			guild = this.guilds.getGuild(e.getPlayer());
			guild.setDescription(tag);
			e.getPlayer().sendMessage(
					"\u00a7a[\u00a72Guildes\u00a7a] \u00a72Vous avez chang\u00e9 la description de votre team: ");
			e.getPlayer().sendMessage("\u00a77" + e.getMessage());
			e.setCancelled(true);
			this.guilds.adminChangeDesc.remove((Object) e.getPlayer());
			Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
					(String) ("mail Heroxys " + e.getPlayer().getName()
							+ " \u00e0 chang\u00e9 la description de sa guilde "
							+ Guilds.getInstance().getGuild(e.getPlayer()).getName()));
		}
		if (this.guilds.adminChangeName.contains((Object) e.getPlayer())) {
			tag = e.getMessage();
			guild = this.guilds.getGuild(e.getPlayer());
			ConfFile.deleteFolder(ConfFile.getFolder(this.guilds, guild));
			guild.setName(tag);
			e.getPlayer()
					.sendMessage("\u00a7a[\u00a72Guildes\u00a7a] \u00a72Vous avez chang\u00e9 le nom de votre team ! ("
							+ e.getMessage() + ")");
			e.getPlayer().sendMessage(
					"\u00a7a[\u00a72Guildes\u00a7a] &4Vous avez \u00e9t\u00e9 pr\u00e9lev\u00e9 de 10 000\u00a3");
			e.setCancelled(true);
			Guilds.getInstance();
			Guilds.econ.withdrawPlayer((OfflinePlayer) e.getPlayer(), 10000.0);
			this.guilds.adminChangeName.remove((Object) e.getPlayer());
			Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
					(String) ("mail Heroxys " + e.getPlayer().getName() + " \u00e0 chang\u00e9 le nom de la guilde "
							+ Guilds.getInstance().getGuild(e.getPlayer()).getName()));
		}
		if (!e.getMessage().startsWith("*")) {
			if (this.guilds.hasGuild(e.getPlayer())) {
				System.out.println(this.guilds.getGuild(e.getPlayer()).getTag());
				e.setFormat(String
						.valueOf(this.guilds.getChat()
								.getGroupPrefix(e.getPlayer().getWorld(),
										this.guilds.getPerms().getPrimaryGroup(e.getPlayer()))
								.replace('&', '\u00a7'))
						+ "\u00a77" + e.getPlayer().getName() + " " + this.guilds.getGuild(e.getPlayer()).getTag()
						+ Utils.getAliases(this.guilds.getGuild(e.getPlayer()).getName())
						+ e.getMessage().replace("%", "%%"));
			} else {
				e.setFormat(String
						.valueOf(this.guilds.getChat()
								.getGroupPrefix(e.getPlayer().getWorld(),
										this.guilds.getPerms().getPrimaryGroup(e.getPlayer()))
								.replace('&', '\u00a7'))
						+ "\u00a77" + e.getPlayer().getName() + " \u00bb \u00a7r" + e.getMessage());
			}
		} else {
			Player player = e.getPlayer();
			e.setCancelled(true);
			if (!this.guilds.hasGuild(player)) {
				player.sendMessage(
						"\u00a7c[\u00a74Guildes\u00a7c] \u00a74Vous ne pouvez pas utiliser le chat de guilde sans en avoir !");
				return;
			}
			guild = this.guilds.getGuild(player);
			GuildPlayer gPlayer = this.guilds.getGuildPlayer((OfflinePlayer) player);
			for (Player pl : Bukkit.getOnlinePlayers()) {
				String message;
				if (!pl.isOp() || !Guilds.getInstance().gspy.containsKey((Object) pl))
					continue;
				String s = Guilds.getInstance().gspy.get((Object) pl);
				if (s.equalsIgnoreCase("all")) {
					message = e.getMessage().replace("*", "\u00a7c[" + guild.getName() + "] "
							+ gPlayer.getRank().getPrefix() + " &7" + player.getName() + " \u00bb&r ");
					pl.getPlayer().getPlayer()
							.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', (String) message));
					continue;
				}
				if (!guild.getName().equalsIgnoreCase(s))
					continue;
				message = e.getMessage().replace("*", "\u00a7c[" + guild.getName() + "] "
						+ gPlayer.getRank().getPrefix() + " &7" + player.getName() + " \u00bb&r ");
				pl.getPlayer().getPlayer()
						.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', (String) message));
			}
			for (GuildPlayer pl : guild.getGuildMembers()) {
				if (!pl.getPlayer().isOnline())
					continue;
				String message = e.getMessage().replace("*", "");
				pl.getPlayer().getPlayer()
						.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',
								(String) (String.valueOf(gPlayer.getRank().getPrefix()) + " &7" + player.getName()
										+ " \u00bb&r " + message)));
			}
		}
	}
}
