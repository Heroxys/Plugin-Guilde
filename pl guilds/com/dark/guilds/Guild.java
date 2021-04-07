/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.BookMeta
 *  org.bukkit.inventory.meta.ItemMeta
 */
package com.dark.guilds;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.dark.guilds.players.GuildPermission;
import com.dark.guilds.players.GuildPlayer;
import com.dark.guilds.players.GuildRank;
import com.dark.guilds.zone.APZone;
import com.dark.guilds.zone.GuildZone;
import com.dark.guilds.zone.SpawnZone;

public class Guild {
	private final UUID uuid;
	private String name;
	private String description;
	private List<GuildPlayer> guildMembers;
	private final GuildPlayer creator;
	private boolean hasConflict;
	private int maxSize;
	private int level;
	private int fieldSide;
	private int xp;
	private GuildStatus status;
	private Location spawn;
	private String tag;
	private ItemStack book;
	private int quota;
	private GuildZone GuildZone;
	private SpawnZone spawnZone;
	private APZone ap1;
	private APZone ap2;

	public Guild(UUID uuid, String name, OfflinePlayer creator, List<OfflinePlayer> members, String description,
			int level, int xp, boolean conflict, int size, int field) {
		this.uuid = uuid;
		this.name = name;
		this.description = description;
		this.level = level;
		if (level > 10)
			this.level = 10;
		this.xp = xp;
		this.hasConflict = conflict;
		this.maxSize = size;
		this.fieldSide = field;
		this.status = GuildStatus.INVITATION;
		ArrayList<GuildPermission> perms = new ArrayList<GuildPermission>();
		perms.add(GuildPermission.ALL);
		this.creator = new GuildPlayer(this, creator, GuildRank.BOSS, perms);
		ArrayList<GuildPlayer> gMembers = new ArrayList<GuildPlayer>();
		for (OfflinePlayer pl : members) {
			if (pl.getUniqueId().equals(this.getCreator().getPlayer().getUniqueId()))
				continue;
			ArrayList<GuildPermission> perms2 = new ArrayList<GuildPermission>();
			perms2.add(GuildPermission.NOTHING);
			gMembers.add(new GuildPlayer(this, pl, GuildRank.MEMBER, perms2));
		}
		gMembers.add(this.getCreator());
		this.guildMembers = gMembers;
	}

	public APZone getAp1() {
		return this.ap1;
	}

	public void setAp1(APZone ap1) {
		this.ap1 = ap1;
	}

	public APZone getAp2() {
		return this.ap2;
	}

	public void setAp2(APZone ap2) {
		this.ap2 = ap2;
	}

	public int getMaxSize() {
		return this.maxSize;
	}

	public int getFieldSide() {
		return this.fieldSide;
	}

	public void setStatus(GuildStatus status) {
		this.status = status;
	}

	public void setSpawn(Location loc) {
		this.spawn = loc;
	}

	public Location getSpawn() {
		return this.spawn;
	}

	public SpawnZone getSpawnZone() {
		return new SpawnZone(new Location(spawn.getWorld(), spawn.getX() - 2, spawn.getY(), spawn.getZ() - 2),
				new Location(spawn.getWorld(), spawn.getX() + 2, spawn.getY(), spawn.getZ() + 2));
	}

	public boolean hasSpawn() {
		if (spawn == null) {
			return false;
		}
		return true;
	}

	public GuildStatus getStatus() {
		return this.status;
	}

	public void setFieldSide(int fieldSide) {
		this.fieldSide = fieldSide;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public GuildPlayer getCreator() {
		return this.creator;
	}

	public void setGuildZone(GuildZone gz) {
		this.GuildZone = gz;
	}

	public boolean isCreator(GuildPlayer player) {
		return this.getCreator().equals(player);
	}

	@SuppressWarnings("unused")
	private void setGuildMembers(List<GuildPlayer> guildMembers) {
		this.guildMembers = guildMembers;
	}

	public UUID getUniqueId() {
		return this.uuid;
	}

	public int getLevel() {
		return this.level;
	}

	public String getName() {
		return this.name;
	}

	public boolean hasGuildZone() {
		if (this.GuildZone != null) {
			return true;
		}
		return false;
	}

	public boolean hasAp1() {
		if (this.ap1 != null) {
			return true;
		}
		return false;
	}

	public boolean hasAp2() {
		if (this.ap2 != null) {
			return true;
		}
		return false;
	}

	public List<GuildPlayer> getGuildMembers() {
		return this.guildMembers;
	}

	public int getXp() {
		return this.xp;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void levelUp() {
		++this.level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addXp(int xpPlus) {
		this.xp += xpPlus;
		checkNewLevel();
	}

	private void checkNewLevel() {
		if (this.xp >= 4000) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 est passée au niveau 10 !\n" + "N'hésitez pas à les féliciter !\n§r"
					+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 10;
		} else if (this.xp >= 3500) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 est passée au niveau 9 !\n" + "N'hésitez pas à les féliciter !\n§r"
					+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 9;
		} else if (this.xp >= 3000) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 est passée au niveau 8 !\n" + "N'hésitez pas à les féliciter !\n§r"
					+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 8;
		} else if (this.xp >= 2500) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 est passée au niveau 7 !\n" + "N'hésitez pas à les féliciter !\n§r"
					+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 7;
		} else if (this.xp >= 2000) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 est passée au niveau 6 !\n" + "N'hésitez pas à les féliciter !\n§r"
					+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 6;
		} else if (this.xp >= 1600) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 est passée au niveau 5 !\n" + "N'hésitez pas à les féliciter !\n§r"
					+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 5;
		} else if (this.xp >= 1200) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 est passée au niveau 4 !\n" + "N'hésitez pas à les féliciter !\n§r"
					+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 4;
		} else if (this.xp >= 800) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 est passée au niveau 3 !\n" + "N'hésitez pas à les féliciter !\n§r"
					+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 3;
		} else if (this.xp >= 400) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 est passée au niveau 2 !\n" + "N'hésitez pas à les féliciter !\n§r"
					+ "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 2;
		}
	}

	private void checkLowerLevel() {

		if (this.xp < 400 && this.level != 1) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 a été rétrogradée au niveau 1 !\n" + "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 1;
		} else if (this.xp < 800) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 a été rétrogradée au niveau 2 !\n" + "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 2;
		} else if (this.xp < 1200) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 a été rétrogradée au niveau 3 !\n" + "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 3;
		} else if (this.xp < 1600) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 a été rétrogradée au niveau 4 !\n" + "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 4;
		} else if (this.xp < 2000) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 a été rétrogradée au niveau 5 !\n" + "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 5;
		} else if (this.xp < 2500) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 a été rétrogradée au niveau 6 !\n" + "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 6;
		} else if (this.xp < 3000) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 a été rétrogradée au niveau 7 !\n" + "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 7;
		} else if (this.xp < 3500) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 a été rétrogradée au niveau 8 !\n" + "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 8;
		} else if (this.xp < 4000) {
			Bukkit.broadcastMessage("§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n" + "§r§2La guilde §r§6§l" + this.name
					+ "§r§2 est passée au niveau 10 !\n" + "§9§m-----§r§9\u00bb§6Guildes§9\u00ab§m-----\n");
			this.level = 9;
		}
	}

	public boolean isInConflict() {
		return this.hasConflict;
	}

	public void setConflict(boolean hasConflict) {
		this.hasConflict = hasConflict;
	}

	public boolean addGuildMembers(GuildPlayer member) {
		if (this.getGuildMembers().contains(member)) {
			return false;
		}
		this.getGuildMembers().add(member);
		return true;
	}

	public void removeGuildMember(GuildPlayer player) {
		this.getGuildMembers().remove(player);
	}

	public void save(FileConfiguration yc, File ymlFile) {
		ArrayList<String> members = new ArrayList<String>();
		for (GuildPlayer pl : this.getGuildMembers()) {
			members.add(pl.getPlayer().getUniqueId().toString());
		}
		for (GuildPlayer pl : this.getGuildMembers()) {
			yc.set("Guilds." + this.getUniqueId().toString() + "." + pl.getPlayer().getUniqueId().toString() + ".quota",
					(Object) pl.getQuota());
		}
		yc.set("Guilds." + this.getUniqueId().toString() + ".creator",
				(Object) this.getCreator().getPlayer().getUniqueId().toString());
		yc.set("Guilds." + this.getUniqueId().toString() + ".name", (Object) this.getName());
		yc.set("Guilds." + this.getUniqueId().toString() + ".description", (Object) this.getDescription());
		yc.set("Guilds." + this.getUniqueId().toString() + ".members", members);
		yc.set("Guilds." + this.getUniqueId().toString() + ".level", (Object) this.getLevel());
		yc.set("Guilds." + this.getUniqueId().toString() + ".xp", (Object) this.getXp());
		yc.set("Guilds." + this.getUniqueId().toString() + ".conflict", (Object) this.isInConflict());
		yc.set("Guilds." + this.getUniqueId().toString() + ".maxsize", (Object) this.getMaxSize());
		yc.set("Guilds." + this.getUniqueId().toString() + ".field", (Object) this.getFieldSide());
		yc.set("Guilds." + this.getUniqueId().toString() + ".status", (Object) this.getStatus().toString());
		yc.set("Guilds." + this.getUniqueId().toString() + ".spawn", (Object) this.getSpawn());
		yc.set("Guilds." + this.getUniqueId().toString() + ".tag", (Object) this.tag);
		yc.set("Guilds." + this.getUniqueId().toString() + ".book", (Object) this.getAdminBook());
		yc.set("Guilds." + this.getUniqueId().toString() + ".quota", (Object) this.getQuota());
		if (this.getGuildZone() != null) {
			yc.set("Guilds." + this.getUniqueId().toString() + ".guildezone.loc1",
					(Object) this.getGuildZone().getLoc1());
			yc.set("Guilds." + this.getUniqueId().toString() + ".guildezone.loc2",
					(Object) this.getGuildZone().getLoc2());
		}
		if (this.getAp1() != null) {
			yc.set("Guilds." + this.getUniqueId().toString() + ".ap1.loc1", (Object) this.getAp1().getLoc1());
			yc.set("Guilds." + this.getUniqueId().toString() + ".ap1.loc2", (Object) this.getAp1().getLoc2());
		}
		if (this.getAp2() != null) {
			yc.set("Guilds." + this.getUniqueId().toString() + ".ap2.loc1", (Object) this.getAp2().getLoc1());
			yc.set("Guilds." + this.getUniqueId().toString() + ".ap2.loc2", (Object) this.getAp2().getLoc2());
		}
		this.saveCustomYml(yc, ymlFile);
	}

	public GuildZone getGuildZone() {
		return this.GuildZone;
	}

	public void setTag(String s) {
		this.tag = s;
	}

	public String getTag() {
		return ChatColor.translateAlternateColorCodes((char) '&', (String) this.tag);
	}

	public void setBook(ItemStack book) {
		this.book = book;
	}

	public ItemStack getAdminBook() {
		return this.book;
	}

	public BookMeta getAdminPages() {
		return (BookMeta) this.getAdminBook().getItemMeta();
	}

	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ItemStack getBook() {
		if (this.getAdminBook() == null) {
			return null;
		}
		ItemStack i = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) i.getItemMeta();
		meta.setPages(this.getAdminPages().getPages());
		meta.setAuthor(this.getAdminPages().getAuthor());
		i.setItemMeta((ItemMeta) meta);
		return i;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getQuota() {
		return this.quota;
	}

	public void removeXp(int i) {
		this.xp -= i;
		if (this.xp < 0) {
			this.xp = 0;
		}

		checkLowerLevel();
	}
}
