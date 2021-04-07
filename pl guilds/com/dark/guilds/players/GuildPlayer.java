/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.OfflinePlayer
 */
package com.dark.guilds.players;

import com.dark.guilds.Guild;
import com.dark.guilds.players.GuildPermission;
import com.dark.guilds.players.GuildRank;
import java.util.List;
import org.bukkit.OfflinePlayer;

public class GuildPlayer {
	Guild guild;
	final OfflinePlayer player;
	GuildRank rank;
	List<GuildPermission> playerPermissions;
	int Quota;
	int combat;

	public int getQuota() {
		return this.Quota;
	}

	public void addQuota(int quota) {
		this.Quota += quota;
	}

	public void setQuota(int quota) {
		this.Quota = quota;
	}

	public GuildPlayer(Guild guild, OfflinePlayer player, GuildRank rank, List<GuildPermission> permissions) {
		this.guild = guild;
		this.player = player;
		this.rank = rank;
		this.playerPermissions = permissions;
		this.combat = 0;
	}

	public void giveRankAndPermissions(GuildRank rank, List<GuildPermission> perms) {
		this.setRank(rank);
		this.setPlayerPermissions(perms);
	}

	public Guild getGuild() {
		return this.guild;
	}

	public OfflinePlayer getPlayer() {
		return this.player;
	}

	public GuildRank getRank() {
		return this.rank;
	}

	public void setGuild(Guild guild) {
		this.guild = guild;
	}

	public boolean hasPermission(GuildPermission perm) {
		if (!this.getPlayerPermissions().contains((Object) GuildPermission.ALL)
				&& !this.getPlayerPermissions().contains((Object) perm)) {
			return false;
		}
		return true;
	}

	public boolean hasRealPermission(GuildPermission perm) {
		return this.getPlayerPermissions().contains((Object) perm);
	}

	public void addPlayerPermission(GuildPermission perm) {
		this.playerPermissions.add(perm);
	}

	public void removePlayerPermission(GuildPermission perm) {
		this.playerPermissions.remove((Object) perm);
	}

	public List<GuildPermission> getPlayerPermissions() {
		return this.playerPermissions;
	}

	public void setPlayerPermissions(List<GuildPermission> playerPermissions) {
		this.playerPermissions = playerPermissions;
	}

	public void setRank(GuildRank rank) {
		this.rank = rank;
	}

	public void setCombat(int combat) {
		this.combat = combat;
	}

	public int getCombat() {
		return this.combat;
	}
}
