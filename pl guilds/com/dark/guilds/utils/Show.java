/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitTask
 */
package com.dark.guilds.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import com.dark.guilds.Guilds;
import com.dark.guilds.zone.APZone;
import com.dark.guilds.zone.GuildZone;

public class Show {
	static ArrayList<Block> blocks = new ArrayList<Block>();
	private BukkitTask particles;

	public Show(final GuildZone gz, final Player p, long seconds) {
		if (seconds == -1)
			seconds = 140L;
		else
			seconds = seconds * 20;
		this.showParcelle(gz);
		Bukkit.getScheduler().runTaskLater((Plugin) Guilds.getInstance(), new Runnable() {

			@Override
			public void run() {
				hideParcelle();
			}
		}, seconds);
	}

	public Show(final APZone gz, final Player p, long seconds) {
		if (seconds == -1)
			seconds = 140L;
		else
			seconds = seconds * 20;
		this.showParcelle(gz);
		Bukkit.getScheduler().runTaskLater((Plugin) Guilds.getInstance(), new Runnable() {

			@Override
			public void run() {
				hideParcelle();
			}
		}, seconds);
	}

	public void showParcelle(GuildZone gz) {
		Location locMax;
		Location locMin;
		int xMax = gz.getxMax() + 1;
		int zMax = gz.getzMax() + 1;
		int xMin = gz.getxMin();
		int zMin = gz.getzMin();
		World w = gz.getLoc1().getWorld();
		int i = xMin;
		while (i <= xMax) {
			locMin = new Location(w, (double) i, (double) this.getHigherBlock(i, zMin, w).intValue(), (double) zMin);
			locMax = new Location(w, (double) i, (double) this.getHigherBlock(i, zMax, w).intValue(), (double) zMax);
			blocks.add(locMax.getBlock());
			blocks.add(locMin.getBlock());
			++i;
		}
		i = zMin;
		while (i <= zMax) {
			locMin = new Location(w, (double) xMin, (double) this.getHigherBlock(xMin, i, w).intValue(), (double) i);
			locMax = new Location(w, (double) xMax, (double) this.getHigherBlock(xMax, i, w).intValue(), (double) i);
			blocks.add(locMax.getBlock());
			blocks.add(locMin.getBlock());
			++i;
		}

		showParcelle(w);
	}

	public void showParcelle(APZone gz) {
		Location locMax;
		Location locMin;
		int xMax = gz.getxMax();
		int zMax = gz.getzMax();
		int xMin = gz.getxMin();
		int zMin = gz.getzMin();
		World w = gz.getLoc1().getWorld();
		int i = xMin;
		while (i <= xMax + 1) {
			locMin = new Location(w, (double) i, (double) this.getHigherBlock(i, zMin, w).intValue(), (double) zMin);
			locMax = new Location(w, (double) i, (double) this.getHigherBlock(i, zMax, w).intValue(), (double) zMax);
			blocks.add(locMax.getBlock());
			blocks.add(locMin.getBlock());
			++i;
		}
		i = zMin;
		while (i <= zMax) {
			locMin = new Location(w, (double) xMin, (double) this.getHigherBlock(xMin, i, w).intValue(), (double) i);
			locMax = new Location(w, (double) xMax, (double) this.getHigherBlock(xMax, i, w).intValue(), (double) i);
			blocks.add(locMax.getBlock());
			blocks.add(locMin.getBlock());
			++i;
		}

		showParcelle(w);
	}

	private void showParcelle(World world) {
		particles = Bukkit.getScheduler().runTaskTimer((Plugin) Guilds.getInstance(), new Runnable() {

			@Override
			public void run() {
				for (Block b : blocks) {
					world.spawnParticle(Particle.FLAME, b.getX(), b.getY(), b.getZ(), 0, 0.0, 0.0, 0.0, 0.0, null);
				}
			}
		}, 0L, 10L);
	}

	public void hideParcelle() {
		particles.cancel();
	}

	private Integer getHigherBlock(int x, int z, World w) {
		int i = 256;
		while (i > 0) {
			Location loc = new Location(w, (double) x, (double) i, (double) z);
			if (loc.getBlock().getType() != Material.AIR) {
				return i + 1;
			}
			--i;
		}
		return 0;
	}

}
