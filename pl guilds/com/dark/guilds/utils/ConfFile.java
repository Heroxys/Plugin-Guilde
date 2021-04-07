/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package com.dark.guilds.utils;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.GuildPermission;
import com.dark.guilds.players.GuildPlayer;
import com.dark.guilds.players.GuildRank;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfFile {
	private Guilds guilds;
	private Guild guild;
	private GuildPlayer player;
	private File folder;
	public static File confFile;
	private File customYml;
	private FileConfiguration customConfig;

	public ConfFile(Guilds guilds, Guild guild, GuildPlayer player) {
		this.guilds = guilds;
		this.guild = guild;
		this.player = player;
	}

	public void build() {
		this.createFolder();
		this.createPlayerDataFolder();
	}

	private void createFolder() {
		this.folder = new File(this.guilds.getDataFolder(), String.valueOf(this.guild.getName()) + "/");
		try {
			if (!this.folder.exists()) {
				this.folder.mkdirs();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		File userdata = new File(this.guilds.getDataFolder(), String.valueOf(this.guild.getName()) + "/");
		File f = new File(userdata, String.valueOf(File.separator) + this.player.getPlayer().getUniqueId() + ".yml");
		if (f.exists()) {
			try {
				if (f.delete()) {
					this.guilds.getLogger().info("La suppression du fichier de " + this.player.getPlayer().getName()
							+ " s'est d\u00e9roul\u00e9e avec succ\u00a8s");
				} else {
					this.guilds.getLogger()
							.severe("Erreur dans la suppression du fichier de " + this.player.getPlayer().getName());
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	public static File getFolder(Guilds g, Guild g1) {
		return new File(g.getDataFolder(), String.valueOf(g1.getName()) + "/");
	}

	public static void deleteFolder(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			File[] arrfile = contents;
			int n = arrfile.length;
			int n2 = 0;
			while (n2 < n) {
				File f = arrfile[n2];
				if (!Files.isSymbolicLink(f.toPath())) {
					ConfFile.deleteFolder(f);
				}
				++n2;
			}
		}
		file.delete();
	}

	private void createPlayerDataFolder() {
		File userdata = new File(this.guilds.getDataFolder(), String.valueOf(this.guild.getName()) + "/");
		File f = new File(userdata, String.valueOf(File.separator) + this.player.getPlayer().getUniqueId() + ".yml");
		YamlConfiguration playerData = YamlConfiguration.loadConfiguration((File) f);
		if (!f.exists()) {
			try {
				playerData.createSection("Rank");
				playerData.set("Rank", (Object) this.player.getRank().toString());
				ArrayList<String> perms = new ArrayList<String>();
				for (GuildPermission perm : this.player.getPlayerPermissions()) {
					perms.add(perm.toString());
				}
				playerData.createSection("Permissions");
				playerData.set("Permissions", perms);
				playerData.createSection("Quota");
				playerData.set("Quota", (Object) this.player.getQuota());
				playerData.save(f);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		} else {
			try {
				playerData.set("Rank", (Object) this.player.getRank().toString());
				ArrayList<String> perms = new ArrayList<String>();
				for (GuildPermission perm : this.player.getPlayerPermissions()) {
					perms.add(perm.toString());
				}
				playerData.set("Permissions", perms);
				playerData.set("Quota", (Object) this.player.getQuota());
				playerData.save(f);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		confFile = f;
	}

	public FileConfiguration getPlayerConfig() {
		File userdata = new File(this.guilds.getDataFolder(), String.valueOf(this.guild.getName()) + "/");
		File f = new File(userdata, String.valueOf(File.separator) + this.player.getPlayer().getUniqueId() + ".yml");
		YamlConfiguration playerData = YamlConfiguration.loadConfiguration((File) f);
		return playerData;
	}

	public boolean fileExist() {
		File userdata = new File(this.guilds.getDataFolder(), String.valueOf(this.guild.getName()) + "/");
		File f = new File(userdata, String.valueOf(File.separator) + this.player.getPlayer().getUniqueId() + ".yml");
		return f.exists();
	}

	public File getFile() {
		File userdata = new File(this.guilds.getDataFolder(), String.valueOf(this.guild.getName()) + "/");
		File f = new File(userdata, String.valueOf(File.separator) + this.player.getPlayer().getUniqueId() + ".yml");
		return f;
	}

	private void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save(FileConfiguration f, File file) {
		try {
			f.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
