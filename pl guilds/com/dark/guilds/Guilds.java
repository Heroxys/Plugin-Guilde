/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  com.sk89q.worldguard.bukkit.WorldGuardPlugin
 *  net.milkbowl.vault.chat.Chat
 *  net.milkbowl.vault.economy.Economy
 *  net.milkbowl.vault.permission.Permission
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.Server
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginDescriptionFile
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.plugin.RegisteredServiceProvider
 *  org.bukkit.plugin.ServicesManager
 *  org.bukkit.plugin.java.JavaPlugin
 *  org.bukkit.scheduler.BukkitTask
 */
package com.dark.guilds;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.dark.guilds.commands.Admin;
import com.dark.guilds.commands.BatailleCommand;
import com.dark.guilds.commands.Boutique;
import com.dark.guilds.commands.Claim;
import com.dark.guilds.commands.GuildCommand;
import com.dark.guilds.commands.MenuCommand;
import com.dark.guilds.events.ChatEvent;
import com.dark.guilds.events.DamageEvent;
import com.dark.guilds.events.InteractEvent;
import com.dark.guilds.events.JoinEvent;
import com.dark.guilds.events.SecurityEvent;
import com.dark.guilds.events.SelectZoneProtectionEvent;
import com.dark.guilds.players.BanPlayers;
import com.dark.guilds.players.GuildPermission;
import com.dark.guilds.players.GuildPlayer;
import com.dark.guilds.players.GuildRank;
import com.dark.guilds.tasks.BanPlayersTask;
import com.dark.guilds.tasks.CombatRunnable;
import com.dark.guilds.tasks.PayRunnable;
import com.dark.guilds.tasks.RefreshDataRunnable;
import com.dark.guilds.utils.ConfFile;
import com.dark.guilds.utils.Utils;
import com.dark.guilds.zone.APZone;
import com.dark.guilds.zone.GuildZone;
import com.dark.guilds.zone.Selecteur;
import com.dark.guilds.zone.SpawnZone;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Guilds extends JavaPlugin implements Listener {
	public static List<Guild> guilds = new ArrayList<Guild>();
	private static Guilds instance;
	public List<Player> adminChangeName = new ArrayList<Player>();
	public List<Player> adminChangeDesc = new ArrayList<Player>();
	public List<Player> adminChangeTag = new ArrayList<Player>();
	public List<Player> adminChangeBook = new ArrayList<Player>();
	public List<OfflinePlayer> levelUpReward = new ArrayList<OfflinePlayer>();
	public List<BanPlayers> bansPlayers = new ArrayList<BanPlayers>();
	public Map<Player, String> gspy = new HashMap<Player, String>();
	public List<Player> waitingName = new ArrayList<Player>();
	public static File confFile;
	public List<GuildPlayer> gPlayers = new ArrayList<GuildPlayer>();
	public Map<GuildPlayer, ConfFile> playersFiles = new HashMap<GuildPlayer, ConfFile>();
	public Map<Player, Guild> invitedPlayers = new HashMap<Player, Guild>();
	public static Economy econ;
	private static Chat chat;
	private static Permission perms;
	public WorldGuardPlugin wgp;
	private File customYml = new File(this.getDataFolder() + "/guilds.yml");
	private FileConfiguration customConfig = YamlConfiguration.loadConfiguration(this.customYml);
	public File confCopy = new File(this.getDataFolder() + "/guildsCopy.yml");
	public static boolean deleted;
	public static Logger log = Logger.getLogger("Minecraft");

	static {
		econ = null;
		chat = null;
		perms = null;
		deleted = false;
	}

	public void onEnable() {
		deleted = false;
		instance = this;
		confFile = this.customYml;
		this.saveCustomYml(this.customConfig, this.customYml);
		YamlConfiguration yc = YamlConfiguration.loadConfiguration((File) confFile);
		if (yc.getConfigurationSection("Guilds") == null) {
			yc.createSection("Guilds");
		}
		this.saveCustomYml((FileConfiguration) yc, confFile);
		this.wgp = (WorldGuardPlugin) this.getServer().getPluginManager().getPlugin("WorldGuard");
		if (!this.setupEconomy()) {
			Logger.getLogger("Minecraft").info(String.format("[%s] - Disabled due to no Vault dependency found!",
					this.getDescription().getName()));
			this.getServer().getPluginManager().disablePlugin((Plugin) this);
			return;
		}
		this.setupChat();
		this.setupPermissions();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents((Listener) new InteractEvent(this), (Plugin) this);
		pm.registerEvents((Listener) new ChatEvent(this), (Plugin) this);
		pm.registerEvents((Listener) new DamageEvent(this), (Plugin) this);
		pm.registerEvents((Listener) new JoinEvent(), (Plugin) this);
		pm.registerEvents((Listener) new Selecteur(this), (Plugin) this);
		pm.registerEvents((Listener) new SecurityEvent(), (Plugin) this);
		pm.registerEvents((Listener) new SelectZoneProtectionEvent(), (Plugin) this);
		pm.registerEvents((Listener) new GuildUp(), (Plugin) this);
		pm.registerEvents(new SpawnZone(), this);
		pm.registerEvents(this, this);
		this.getCommand("guilde").setExecutor((CommandExecutor) new GuildCommand(this));
		this.getCommand("menu").setExecutor((CommandExecutor) new MenuCommand(this));
		this.getCommand("bataille").setExecutor((CommandExecutor) new BatailleCommand(this));
		this.getCommand("qverif").setExecutor((CommandExecutor) new Admin());
		this.getCommand("qclear").setExecutor((CommandExecutor) new Admin());
		this.getCommand("gspy").setExecutor((CommandExecutor) new Admin());
		this.getCommand("show").setExecutor((CommandExecutor) new Admin());
		this.getCommand("achat").setExecutor((CommandExecutor) new Boutique());
		this.getCommand("claim").setExecutor((CommandExecutor) new Claim(this));
		this.getCommand("ap").setExecutor((CommandExecutor) new Claim(this));
		this.getCommand("jidesfrnhjbfezsjnfkjde").setExecutor((CommandExecutor) new Admin());
		this.saveDefaultConfig();
		if (this.getConfig().get("banPlayers") instanceof ConfigurationSection) {
			int i = 0;
			while (this.getConfig().get("banPlayers." + i) instanceof ConfigurationSection) {
				UUID uuid = UUID.fromString(this.getConfig().getString("banPlayers." + i + ".uuid"));
				int time = this.getConfig().getInt("banPlayers." + i + ".time");
				BanPlayers pls = new BanPlayers(Bukkit.getOfflinePlayer((UUID) uuid), time);
				this.bansPlayers.add(pls);
				++i;
			}
		} else {
			this.bansPlayers = new ArrayList<BanPlayers>();
		}

		if (!confCopy.exists()) {
			try {
				confCopy.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				copyFiles(confCopy, confFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (this.loadWithClaims()) {
			this.getLogger().info("Chargement de " + this.getGuilds().size() + " Guilde(s) en cours...");
			this.getLogger().info("Toutes les guildes sont actives !");
		} else {
			this.getLogger().log(Level.SEVERE, "Erreur dans le chargement des guildes");
			this.getLogger().log(Level.SEVERE, "Contactez Heroxys sur discord");
			this.getLogger().log(Level.SEVERE, "D\u00e9sactivation du plugin");
			Bukkit.getPluginManager().disablePlugin((Plugin) this);
		}

		PayRunnable task = new PayRunnable(this);
		task.runTaskTimer((Plugin) this, 200L, 1728000L);
		BanPlayersTask pTask = new BanPlayersTask(this);
		pTask.runTaskTimer((Plugin) this, 20L, 20L);
		RefreshDataRunnable rTask = new RefreshDataRunnable(this);
		rTask.runTaskTimer((Plugin) this, 200L, 30000L);
		CombatRunnable combatTask = new CombatRunnable(this);
		combatTask.runTaskTimer((Plugin) this, 400L, 20L);
	}

	public void onDisable() {
		this.refreshPlayersData();
		YamlConfiguration yc = YamlConfiguration.loadConfiguration((File) confFile);
		for (Guild guild : guilds) {
			guild.save((FileConfiguration) yc, confFile);
		}
		int i = 0;
		this.getConfig().set("banPlayers", (Object) "t");
		for (BanPlayers pls : this.bansPlayers) {
			this.getConfig().set("banPlayers." + i + ".uuid", (Object) pls.p.getUniqueId().toString());
			this.getConfig().set("banPlayers." + i + ".time", (Object) pls.time);
			++i;
		}
		this.saveConfig();
		try {
			this.copyFiles(confFile, confCopy);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player killed = e.getEntity().getPlayer();
		if (killed instanceof Player && isInFight(getGuild(killed))) {
			Properties warFile = new Properties();
			try {
				warFile.load(new BufferedInputStream(new FileInputStream(getWarFile(getGuild(killed).getName()))));
				warFile.setProperty(killed.getDisplayName(),
						Integer.parseInt(warFile.getProperty(killed.getDisplayName()) + 1) + "");
				warFile.store(new BufferedOutputStream(new FileOutputStream(getWarFile(getGuild(killed).getName()))),
						"Bataille entre deux guildes !");
				if (warFile.getProperty(killed.getDisplayName()) == 3 + "") {
					Bukkit.dispatchCommand(this.getServer().getConsoleSender(),
							"gamemode 3 " + killed.getDisplayName());
					killed.sendMessage(
							"§6Tu n'as plus de vie pour la guerre, maintenant tu ne peux que regarder les autres.\n"
									+ "Vous pouvez être fiers de ce que vous avez effectué soldat !");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String command = e.getMessage();
		Player player = e.getPlayer();
		Guild gPlayer = getGuild(player);

		if (!player.isOp())
			if (command.toLowerCase().startsWith("/tpyes") || command.toLowerCase().startsWith("/tpaccept")
					|| command.toLowerCase().startsWith("/tpahere") || command.toLowerCase().startsWith("/sethome")) {
				for (Guild g : guilds) {
					if (g.getGuildZone() != null && g.getGuildZone().isInSecureZone(player.getLocation())) {
						if (gPlayer == null) {
							e.setCancelled(true);
						} else if (gPlayer == g) {
							continue;
						}
					}
				}
				
				if(isInFight(player)) {
					e.setCancelled(true);
				}
			}
	}

	public boolean load() {
		guilds.clear();
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		for (String guildUUID : yc.getConfigurationSection("Guilds").getKeys(false)) {
			UUID uuid = UUID.fromString(guildUUID);
			String name = yc.getString("Guilds." + guildUUID + ".name");
			OfflinePlayer creator = Bukkit
					.getOfflinePlayer(UUID.fromString(yc.getString("Guilds." + guildUUID + ".creator")));
			String description = yc.getString("Guilds." + guildUUID + ".description");
			int level = yc.getInt("Guilds." + guildUUID + ".level");
			int xp = yc.getInt("Guilds." + guildUUID + ".xp");
			int maxSize = yc.getInt("Guilds." + guildUUID + ".maxsize");
			boolean conflict = yc.getBoolean("Guilds." + guildUUID + ".conflict");
			int field = yc.getInt("Guilds." + guildUUID + ".field");
			List<String> membersUUID = yc.getStringList("Guilds." + guildUUID + ".members");
			ArrayList<OfflinePlayer> members = new ArrayList<OfflinePlayer>();
			GuildStatus status = GuildStatus.valueOf(yc.getString("Guilds." + guildUUID + ".status"));
			Location spawn = (Location) yc.get("Guilds." + guildUUID + ".spawn");
			String tag = yc.getString("Guilds." + guildUUID + ".tag");
			ItemStack book = (ItemStack) yc.get("Guilds." + guildUUID + ".book");
			int quota = yc.getInt("Guilds." + guildUUID + ".quota");

			if (name == null) {
				return false;
			}
			if (description == null) {
				return false;
			}
			for (String memUUID : membersUUID) {
				members.add(Bukkit.getOfflinePlayer(UUID.fromString(memUUID)));
			}
			if (!members.contains((Object) creator)) {
				members.add(creator);
			}
			Guild guild = new Guild(uuid, name, creator, members, description, level, xp, conflict, maxSize, field);
			guild.setStatus(status);
			guild.setSpawn(spawn);
			guild.setTag(tag);
			guild.setBook(book);
			guild.setQuota(quota);
			this.getGuilds().add(guild);
			this.loadPlayers();
			this.loadPlayersDataFiles();
			for (Map.Entry<GuildPlayer, ConfFile> entry : this.playersFiles.entrySet()) {
				GuildPlayer player = entry.getKey();
				ConfFile file = entry.getValue();
				if (file.fileExist()) {
					FileConfiguration playerData = file.getPlayerConfig();
					List<String> permsNames = playerData.getStringList("Permissions");
					int Quota = playerData.getInt("Quota");
					GuildRank rank = GuildRank.valueOf(playerData.getString("Rank"));
					ArrayList<GuildPermission> perms = new ArrayList<GuildPermission>();
					for (String s : permsNames) {
						perms.add(GuildPermission.valueOf(s));
					}
					player.giveRankAndPermissions(rank, perms);
					player.setQuota(Quota);
				} else
					file.build();
			}
		}
		return true;
	}

	public boolean loadWithClaims() {
		guilds.clear();
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		for (String guildUUID : yc.getConfigurationSection("Guilds").getKeys(false)) {
			UUID uuid = UUID.fromString(guildUUID);
			String name = yc.getString("Guilds." + guildUUID + ".name");
			OfflinePlayer creator = Bukkit
					.getOfflinePlayer(UUID.fromString(yc.getString("Guilds." + guildUUID + ".creator")));
			String description = yc.getString("Guilds." + guildUUID + ".description");
			int level = yc.getInt("Guilds." + guildUUID + ".level");
			int xp = yc.getInt("Guilds." + guildUUID + ".xp");
			int maxSize = yc.getInt("Guilds." + guildUUID + ".maxsize");
			boolean conflict = yc.getBoolean("Guilds." + guildUUID + ".conflict");
			int field = yc.getInt("Guilds." + guildUUID + ".field");
			List<String> membersUUID = yc.getStringList("Guilds." + guildUUID + ".members");
			ArrayList<OfflinePlayer> members = new ArrayList<OfflinePlayer>();
			GuildStatus status = GuildStatus.valueOf(yc.getString("Guilds." + guildUUID + ".status"));
			Location spawn = (Location) yc.get("Guilds." + guildUUID + ".spawn");
			String tag = yc.getString("Guilds." + guildUUID + ".tag");
			ItemStack book = (ItemStack) yc.get("Guilds." + guildUUID + ".book");
			int quota = yc.getInt("Guilds." + guildUUID + ".quota");
			GuildZone gz = null;
			APZone ap1 = null;
			APZone ap2 = null;
			if (yc.get("Guilds." + guildUUID + ".guildezone.loc1") != null) {
				gz = new GuildZone((Location) yc.get("Guilds." + guildUUID + ".guildezone.loc1"),
						(Location) yc.get("Guilds." + guildUUID + ".guildezone.loc2"));
			}
			if (yc.get("Guilds." + guildUUID + ".ap1.loc1") != null) {
				ap1 = new APZone((Location) yc.get("Guilds." + guildUUID + ".ap1.loc1"),
						(Location) yc.get("Guilds." + guildUUID + ".ap1.loc2"));
			}
			if (yc.get("Guilds." + guildUUID + ".ap2.loc1") != null) {
				ap2 = new APZone((Location) yc.get("Guilds." + guildUUID + ".ap2.loc1"),
						(Location) yc.get("Guilds." + guildUUID + ".ap2.loc2"));
			}
			if (name == null) {
				return false;
			}
			if (description == null) {
				return false;
			}
			for (String memUUID : membersUUID) {
				members.add(Bukkit.getOfflinePlayer(UUID.fromString(memUUID)));
			}
			if (!members.contains((Object) creator)) {
				members.add(creator);
			}
			Guild guild = new Guild(uuid, name, creator, members, description, level, xp, conflict, maxSize, field);
			guild.setStatus(status);
			guild.setSpawn(spawn);
			guild.setTag(tag);
			guild.setBook(book);
			guild.setQuota(quota);
			if (gz != null) {
				guild.setGuildZone(gz);
			}
			if (ap1 != null) {
				guild.setAp1(ap1);
			}
			if (ap2 != null) {
				guild.setAp2(ap2);
			}
			this.getGuilds().add(guild);
			this.loadPlayers();
			this.loadPlayersDataFiles();
			for (Map.Entry<GuildPlayer, ConfFile> entry : this.playersFiles.entrySet()) {
				GuildPlayer player = entry.getKey();
				ConfFile file = entry.getValue();
				if (file.fileExist()) {
					FileConfiguration playerData = file.getPlayerConfig();
					List<String> permsNames = playerData.getStringList("Permissions");
					int Quota = playerData.getInt("Quota");
					GuildRank rank = GuildRank.valueOf(playerData.getString("Rank"));
					ArrayList<GuildPermission> perms = new ArrayList<GuildPermission>();
					for (String s : permsNames) {
						perms.add(GuildPermission.valueOf(s));
					}
					player.giveRankAndPermissions(rank, perms);
					player.setQuota(Quota);
				} else
					file.build();
			}
		}
		return true;
	}

	private void loadPlayersDataFiles() {
		this.playersFiles.clear();
		for (GuildPlayer player : this.gPlayers) {
			ConfFile file = new ConfFile(this, player.getGuild(), player);
			this.playersFiles.put(player, file);
		}
	}

	public void refreshPlayersData() {
		this.playersFiles.clear();
		for (GuildPlayer player : this.gPlayers) {
			ConfFile file = new ConfFile(this, player.getGuild(), player);
			file.build();
			this.playersFiles.put(player, file);
		}
	}

	public void loadPlayers() {
		this.gPlayers.clear();
		for (Guild guild : this.getGuilds()) {
			this.gPlayers.addAll(guild.getGuildMembers());
		}
	}

	public void deletePlayerDataFile(Player p) {
		GuildPlayer player = this.getGuildPlayer((OfflinePlayer) p);
		ConfFile file = this.playersFiles.get(player);
		file.delete();
	}

	public GuildPlayer getGuildPlayer(OfflinePlayer player) {
		for (GuildPlayer pl : this.gPlayers) {
			if (!pl.getPlayer().getUniqueId().equals(player.getUniqueId()))
				continue;
			return pl;
		}
		return null;
	}

	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean setupEconomy() {
		if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = this.getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = (Economy) rsp.getProvider();
		if (econ != null) {
			return true;
		}
		return false;
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = this.getServer().getServicesManager().getRegistration(Chat.class);
		if (rsp == null) {
			this.getServer().getPluginManager().disablePlugin((Plugin) this);
			return true;
		}

		chat = rsp.getProvider();
		if (chat != null) {
			return true;
		}
		return false;
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = this.getServer().getServicesManager()
				.getRegistration(Permission.class);
		perms = (Permission) rsp.getProvider();
		if (perms != null) {
			return true;
		}
		return false;
	}

	public List<Guild> getGuilds() {
		return guilds;
	}

	public boolean removeGuild(Guild g) {
		return guilds.remove(g);
	}

	public Economy getEconomy() {
		return econ;
	}

	public Chat getChat() {
		return chat;
	}

	public Permission getPerms() {
		return perms;
	}

	public boolean nameExist(String name) {
		for (Guild guild : guilds) {
			if (!guild.getName().equalsIgnoreCase(name))
				continue;
			return true;
		}
		return false;
	}

	public void remPlayerGuild(Player p) {
		if (this.hasGuild(p)) {
			int i = 0;
			while (i < guilds.size()) {
				if (guilds.get(i).getGuildMembers().contains(this.getGuildPlayer((OfflinePlayer) p))) {
					guilds.get(i).removeGuildMember(this.getGuildPlayer((OfflinePlayer) p));
				}
				++i;
			}
		}
	}

	public void remGuild(Player p) {
		if (this.hasGuild(p)) {
			int i = 0;
			while (i < guilds.size()) {
				if (guilds.get(i).getCreator().equals(this.getGuildPlayer((OfflinePlayer) p))) {
					guilds.remove(i);
				}
				++i;
			}
		}
	}

	public Guild getGuildByName(String name) {
		for (Guild guild : guilds) {
			if (!guild.getName().equals(name))
				continue;
			return guild;
		}
		return null;
	}

	public void createGuild(Player player, String name) {
		ArrayList<OfflinePlayer> members = new ArrayList<OfflinePlayer>();
		members.add((OfflinePlayer) player);
		String description = "Votre guilde ne contient pas encore de description (/g tag)";
		Guild guild = new Guild(UUID.randomUUID(), name, (OfflinePlayer) player, members, description, 1, 0, false, 10,
				30);
		guild.setStatus(GuildStatus.INVITATION);
		guild.setBook(new ItemStack(Material.BOOK_AND_QUILL));
		guilds.add(guild);
		player.sendMessage(Utils.color(this.getConfig().getString("Guilds.Messages.CreationSuccess")));
	}

	public Guild getGuild(Player p) {
		for (Guild guild : guilds) {
			if (!guild.getGuildMembers().contains(this.getGuildPlayer((OfflinePlayer) p)))
				continue;
			return guild;
		}
		return null;
	}

	public boolean hasGuild(Player p) {
		if (this.getGuild(p) != null) {
			return true;
		}
		return false;
	}

	public static Guilds getInstance() {
		return instance;
	}

	public static int getQuotaMax(int level) {
		switch (level) {
		case 1: {
			return 100;
		}
		case 2: {
			return 200;
		}
		case 3: {
			return 300;
		}
		case 4: {
			return 400;
		}
		case 5: {
			return 500;
		}
		case 6: {
			return 600;
		}
		case 7: {
			return 700;
		}
		case 8: {
			return 800;
		}
		case 9: {
			return 900;
		}
		case 10: {
			return 1000;
		}
		case 11: {
			return 1000;
		}
		}
		return 0;
	}

	public void refreshGuild() {
		this.load();
	}

	public void levelUpReward(Guild guild) {
		if (guild.getLevel() == 1) {
			return;
		}
		Inventory rew = Bukkit.createInventory(null, (int) 27, (String) "\u00a78R\u00e9compense de niveau");
		int i = 0;
		while (i < 27) {
			rew.setItem(i, Utils.getItem(Material.STAINED_GLASS_PANE, (byte) 15, " ", null));
			++i;
		}
		rew.setItem(12,
				Utils.getItem(Material.WOOD_SPADE, (byte) 0, "\u00c2\u00a78Augmenter son claim de 20x20 blocs", null));
		rew.setItem(14, Utils.getItem(Material.SKULL_ITEM, (byte) 3,
				"\u00c2\u00a78Augmenter sa capacit\u00e9 de 5 joueurs", null));
		if (guild.getCreator().getPlayer().isOnline()) {
			guild.getCreator().getPlayer().getPlayer().openInventory(rew);
		} else {
			this.levelUpReward.add(guild.getCreator().getPlayer());
		}
	}

	public boolean startBattle(String attaquant, String defenseur, Player player) {
		Guild attGuild = getGuildByName(attaquant);
		Guild defGuild = getGuildByName(defenseur);
		if (attGuild == null || defGuild == null) {
			player.sendMessage("§4Une des deux guildes n'existe pas !");
			return false;
		}

		for (GuildPlayer battlePlayer : attGuild.getGuildMembers()) {
			Bukkit.dispatchCommand(this.getServer().getConsoleSender(), "warp Box1 " + battlePlayer.getPlayer().getPlayer().getDisplayName());
		}

		for (GuildPlayer battlePlayer : defGuild.getGuildMembers()) {
			Bukkit.dispatchCommand(this.getServer().getConsoleSender(), "warp Box2 " + battlePlayer.getPlayer().getPlayer().getDisplayName());
		}

		return true;
	}

	public boolean startWar(String attaquant, String defenseur, Player player) {
		Guild attGuild = getGuildByName(attaquant);
		Guild defGuild = getGuildByName(defenseur);
		if (attGuild == null || defGuild == null) {
			player.sendMessage("§4Une des deux guilde n'existe pas !");
			return false;
		}

		if (attGuild.getLevel() < 3) {
			player.sendMessage(
					"§4La guilde " + attaquant + " n'a pas le niveau nécessaire pour commencer une guerre de guilde !");
			return false;
		}

		if (defGuild.getLevel() < 3) {
			player.sendMessage(
					"§4La guilde " + defenseur + " n'a pas le niveau nécessaire pour commencer une guerre de guilde !");
			return false;
		}

		File[] gdgFiles = this.getDataFolder().listFiles();

		if (gdgFiles == null) {
			player.sendMessage("§4Un problème est survenu, appelez le développeur\nCode 404");
			return false;
		}

		for (File f : gdgFiles) {
			if (f.isDirectory())
				continue;

			if (f.getName().contains(attaquant) || f.getName().contains(defenseur)) {
				player.sendMessage("§4Une des deux guildes est en combat en ce moment !");
				return false;
			}
		}

		Properties gdgFile = new Properties();
		for (GuildPlayer gp : attGuild.getGuildMembers()) {
			if (gp.getPlayer().isOnline())
				gdgFile.setProperty(gp.getPlayer().getName(), "0");
			else
				gdgFile.setProperty(gp.getPlayer().getName(), "3");
		}
		for (GuildPlayer gp : defGuild.getGuildMembers()) {
			if (gp.getPlayer().isOnline())
				gdgFile.setProperty(gp.getPlayer().getName(), "0");
			else
				gdgFile.setProperty(gp.getPlayer().getName(), "3");
		}

		try {
			File gdg = new File(this.getDataFolder() + "/" + attaquant + "-" + defenseur + ".gdg");
			gdg.createNewFile();
			gdgFile.store(new BufferedOutputStream(new FileOutputStream(gdg)), "Bataille entre deux guildes !");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private File getWarFile(String aGuildName) {
		FileFilter ff = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.toString().endsWith(".gdg"))
					return true;

				return false;
			}
		};

		File[] gdgFiles = new File(this.getDataFolder() + "/").listFiles(ff);
		for (File f : gdgFiles) {
			if (f.toString().contains(aGuildName)) {
				return f;
			} else
				continue;
		}
		return null;
	}

	public String getGuildNameByWarFile(String otherGuild) {
		String guildFileName = "";
		FileFilter ff = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.toString().endsWith(".gdg"))
					return true;

				return false;
			}
		};

		File[] gdgFiles = new File(this.getDataFolder() + "/").listFiles(ff);
		for (File f : gdgFiles) {
			if (f.toString().contains(otherGuild)) {
				guildFileName = f.getName();
				break;
			} else
				continue;
		}

		if (guildFileName.equals("")) {
			return "";
		}

		int dot = guildFileName.indexOf(".");
		guildFileName.substring(0, dot);
		if (guildFileName.startsWith(otherGuild))
			return guildFileName.substring(guildFileName.indexOf("-") + 1);
		else
			return guildFileName.substring(0, guildFileName.indexOf("-"));
	}

	public void checkEndWar(Guild g1, Guild g2) {

		if (areInWarTogether(g1, g2) && getWarFile(g1.getName()) == getWarFile(g2.getName())
				&& getWarFile(g1.getName()) != null && getWarFile(g2.getName()) != null) {
			Properties warFile = new Properties();
			try {
				int guildCount1 = 0;
				int guildCount2 = 0;
				warFile.load(new FileInputStream(getWarFile(g1.getName())));

				// Test si les membres de la guilde 1 sont tous morts
				for (GuildPlayer gp : g1.getGuildMembers()) {
					if (warFile.getProperty(gp.getPlayer().getName()) == "3") {
						guildCount1++;
					} else
						break;
				}
				if (guildCount1 == g1.getGuildMembers().size()) {
					Bukkit.broadcastMessage("§6La guerre est finie, la guilde " + g2.getName() + " a gagné !\n"
							+ "Un modérateur arrêtera la guerre et distribuera les récompenses !");
					for (GuildPlayer gp : g1.getGuildMembers()) {
						Bukkit.dispatchCommand(this.getServer().getConsoleSender(),
								"gamemode 0 " + gp.getPlayer().getName());
					}
					for (GuildPlayer gp : g2.getGuildMembers()) {
						Bukkit.dispatchCommand(this.getServer().getConsoleSender(),
								"gamemode 0 " + gp.getPlayer().getName());
					}
				}

				// Test si les membres de la guilde 2 sont tous morts
				for (GuildPlayer gp : g2.getGuildMembers()) {
					if (warFile.getProperty(gp.getPlayer().getName()) == "3") {
						guildCount2++;
					} else
						break;
				}
				if (guildCount2 == g2.getGuildMembers().size()) {
					Bukkit.broadcastMessage("§6La guerre est finie, la guilde " + g1.getName() + " a gagné !\n"
							+ "Un modérateur arrêtera la guerre et distribuera les récompenses !");
					for (GuildPlayer gp : g1.getGuildMembers()) {
						Bukkit.dispatchCommand(this.getServer().getConsoleSender(),
								"gamemode 0 " + gp.getPlayer().getName());
					}
					for (GuildPlayer gp : g2.getGuildMembers()) {
						Bukkit.dispatchCommand(this.getServer().getConsoleSender(),
								"gamemode 0 " + gp.getPlayer().getName());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean endWar(String aGuildName) {
		if (!isInFight(aGuildName)) {
			return false;
		}

		if (getWarFile(aGuildName).delete()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isInFight(Guild guild) {
		FileFilter ff = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.toString().endsWith(".gdg"))
					return true;

				return false;
			}
		};

		File[] gdgFiles = new File(this.getDataFolder() + "/").listFiles(ff);
		for (File f : gdgFiles) {
			if (f.toString().contains(guild.getName())) {
				return true;
			} else
				continue;
		}
		return false;
	}

	public boolean isInFight(String guildName) {
		if (nameExist(guildName) && isInFight(getGuildByName(guildName)))
			return true;

		return false;
	}

	public boolean areInWarTogether(Guild guild1, Guild guild2) {
		if (nameExist(guild1.getName()) && nameExist(guild2.getName())
				&& getWarFile(guild1.getName()) == getWarFile(guild2.getName()))
			return true;

		return false;
	}

	public int containBanPlayer(OfflinePlayer offlinePlayer) {
		if (this.bansPlayers.size() != 0) {
			for (BanPlayers pls : this.bansPlayers) {
				if (pls.p != offlinePlayer)
					continue;
				return pls.time;
			}
		}
		return -1;
	}
	
	private boolean isInFight(Player player) {
		
		
		return false;
	}

	public void copyFiles(File toCopy, File destination) throws IOException {

		File copied = destination;
		try (InputStream in = new BufferedInputStream(new FileInputStream(toCopy));
				OutputStream out = new BufferedOutputStream(new FileOutputStream(copied))) {

			byte[] buffer = new byte[1024];
			int lengthRead;
			while ((lengthRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, lengthRead);
				out.flush();
			}
		}
	}
}
