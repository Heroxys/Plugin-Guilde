package com.dark.guilds.zone;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;

public class SpawnZone implements Listener {

	public SpawnZone() {

	}

	private int xMin;
	private int xMax;
	private int zMin;
	private int zMax;
	private Location loc1;
	private Location loc2;

	public SpawnZone(Location loc1, Location loc2) {
		int xLoc1 = loc1.getBlockX();
		int zLoc1 = loc1.getBlockZ();
		int xLoc2 = loc2.getBlockX();
		int zLoc2 = loc2.getBlockZ();
		this.loc1 = loc1;
		this.loc2 = loc2;
		if (xLoc1 > xLoc2) {
			if (zLoc1 > zLoc2) {
				this.xMin = xLoc2;
				this.xMax = xLoc1;
				this.zMin = zLoc2;
				this.zMax = zLoc1;
			} else {
				this.xMin = xLoc2;
				this.xMax = xLoc1;
				this.zMin = zLoc1;
				this.zMax = zLoc2;
			}
		} else if (zLoc1 > zLoc2) {
			this.xMin = xLoc1;
			this.xMax = xLoc2;
			this.zMin = zLoc2;
			this.zMax = zLoc1;
		} else {
			this.xMin = xLoc1;
			this.xMax = xLoc2;
			this.zMin = zLoc1;
			this.zMax = zLoc2;
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (e instanceof Player)
			for (Guild g : Guilds.getInstance().getGuilds()) {
				if (g.hasSpawn() && g.getSpawnZone().isInto(e.getTo()) && !g.getSpawnZone().isInto(e.getFrom())) {
					if (g == Guilds.getInstance().getGuild(p))
						p.sendMessage("§6Vous entrez dans votre spawn");
					if (Guilds.getInstance().areInWarTogether(g, Guilds.getInstance().getGuild(p))) {
						Guilds.getInstance().checkEndWar(g, Guilds.getInstance().getGuild(p));
						p.sendMessage("§6Vous entrez dans le spawn adverse");
					}
				}
				if (g.hasSpawn() && !g.getSpawnZone().isInto(e.getTo()) && g.getSpawnZone().isInto(e.getFrom()))
					if (g == Guilds.getInstance().getGuild(p))
						p.sendMessage("§6Vous sortez de votre spawn");

			}
	}

	public Location getLoc1() {
		return this.loc1;
	}

	public Location getLoc2() {
		return this.loc2;
	}

	public int getxMin() {
		return this.xMin;
	}

	public int getxMax() {
		return this.xMax;
	}

	public int getzMin() {
		return this.zMin;
	}

	public int getzMax() {
		return this.zMax;
	}

	public boolean isInto(Location loc) {
		if (loc.getBlockX() >= this.xMin && loc.getBlockX() <= this.xMax && loc.getBlockZ() >= this.zMin
				&& loc.getBlockZ() <= this.zMax) {
			return true;
		}
		return false;
	}
}
