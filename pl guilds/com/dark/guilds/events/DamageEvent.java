package com.dark.guilds.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;

public class DamageEvent implements Listener {
	private final Guilds guilds;
	
	public static ArrayList<Player> fighters = new ArrayList<Player>();

	public DamageEvent(Guilds guilds) {
		this.guilds = guilds;
	}

	/*
	 * Enabled force condition propagation Lifted jumps to return sites
	 */
	@EventHandler(priority = EventPriority.HIGH)
	public void onDamage(EntityDamageByEntityEvent e) {
		Guild vGuild;
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player victim = (Player) e.getEntity();
		Player damager = null;
		if (!(e.getDamager() instanceof Player)) {
			if (!(e.getDamager() instanceof Arrow))
				return;
			if (((Arrow) e.getDamager()).getShooter() instanceof Player) {
				damager = (Player) ((Arrow) e.getDamager()).getShooter();
			}
		} else {
			damager = (Player) e.getDamager();
			fighters.add(victim);
			fighters.add(damager);
			if (Guilds.getInstance().getGuildPlayer((OfflinePlayer) damager).getCombat() == 0) {
				damager.sendMessage((Object) ChatColor.DARK_RED + "[Guild] Vous \u00eates maintenant en combat");
			}
			Guilds.getInstance().getGuildPlayer((OfflinePlayer) damager).setCombat(20);
			if (Guilds.getInstance().getGuildPlayer((OfflinePlayer) victim).getCombat() == 0) {
				victim.sendMessage((Object) ChatColor.DARK_RED + "[Guild] Vous \u00eates maintenant en combat");
			}
			Guilds.getInstance().getGuildPlayer((OfflinePlayer) victim).setCombat(20);
		}
		if (!this.guilds.hasGuild(victim)) {
			return;
		}
		if (!this.guilds.hasGuild(damager)) {
			return;
		}
		Guild dGuild = this.guilds.getGuild(damager);
		if (dGuild == (vGuild = this.guilds.getGuild(victim))) {
			e.setCancelled(true);
			return;
		}
		if (victim.getHealth() > e.getDamage())
			return;
		if (victim.getUniqueId().equals(vGuild.getCreator().getPlayer().getUniqueId())) {
			dGuild.addXp(GainExperience.KILL_BOSS_DIFF_GUILD.getGain());
		}
		for (Player pls : Bukkit.getOnlinePlayers()) {
			pls.sendMessage((Object) ChatColor.YELLOW + "[Guild]" + (Object) ChatColor.RED + victim.getName()
					+ (Object) ChatColor.YELLOW + " \u00e0 \u00e9t\u00e9 tu\u00e9 par " + (Object) ChatColor.GOLD
					+ damager.getName());
		}
		victim.sendMessage("\u00a7eVous avez \u00e9t\u00e9 tu\u00e9 par le membre d'une guilde adverse ! Attention");
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if (!Guilds.getInstance().hasGuild(p)) {
			return;
		}
		Guild g = Guilds.getInstance().getGuild(p);
		if (g.getSpawn() != null) {
			p.teleport(g.getSpawn());
		}
	}
}
