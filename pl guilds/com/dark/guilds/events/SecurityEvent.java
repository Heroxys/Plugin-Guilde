/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.event.player.PlayerCommandPreprocessEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.event.player.PlayerTeleportEvent
 *  org.bukkit.inventory.ItemStack
 */
package com.dark.guilds.events;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.GuildPlayer;
import com.dark.guilds.zone.APZone;
import com.dark.guilds.zone.GuildZone;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

public class SecurityEvent
implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (this.MsgProtec(e.getBlock().getLocation(), p, true) != 4 && this.MsgProtec(e.getBlock().getLocation(), p, true) != 0) {
            e.setCancelled(true);
            p.sendMessage((Object)ChatColor.DARK_RED + "[Guilds]" + (Object)ChatColor.RED + "Ce block ne vous apartiens pas");
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (this.MsgProtec(e.getBlock().getLocation(), p, true) < 4 && this.MsgProtec(e.getBlock().getLocation(), p, true) != 0) {
            e.setCancelled(true);
            p.sendMessage((Object)ChatColor.DARK_RED + "[Guilds]" + (Object)ChatColor.RED + "Ce block ne vous apartiens pas");
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (this.MsgProtec(p.getLocation(), p, false) > 0 && (p.getItemInHand().getType() == Material.FIREWORK || p.getItemInHand().getType() == Material.ENDER_PEARL || p.getItemInHand().getType() == Material.CHORUS_FRUIT)) {
            e.setCancelled(true);
            p.sendMessage((Object)ChatColor.DARK_RED + "Vous ne pouvez pas effectuer cette action dans la zone de protection d'une guilde");
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p;
        String[] args = e.getMessage().split(" ");
        if ((args[0].equalsIgnoreCase("/sethome") || args[0].equalsIgnoreCase("/tphere") || args[0].equalsIgnoreCase("/tpyes")) && this.MsgProtec((p = e.getPlayer()).getLocation(), p, false) > 4) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (Guilds.getInstance().getGuildPlayer((OfflinePlayer)p) == null) {
            return;
        }
        if (Guilds.getInstance().getGuildPlayer((OfflinePlayer)p).getCombat() != 0) {
            p.sendTitle((Object)ChatColor.RED + "La t\u00e9l\u00e9portation est interdite", (Object)ChatColor.DARK_RED + "Attendez de ne plus \u00eatre dans un combat pour vous t\u00e9l\u00e9portez");
        }
        if (this.MsgProtec(e.getTo(), p, false) > 0) {
            e.setCancelled(true);
        }
    }

    private Integer MsgProtec(Location loc, Player p, boolean block) {
        if (p.isOp() || Guilds.getInstance().getGuilds().size() == 0) {
            return 0;
        }
        for (Guild g : Guilds.getInstance().getGuilds()) {
            if (!g.hasGuildZone() || g == Guilds.getInstance().getGuild(p)) continue;
            if (g.hasAp1() && g.getAp1().isInto(loc)) {
                return 1;
            }
            if (g.hasAp2() && g.getAp2().isInto(loc)) {
                return 2;
            }
            if (g.getGuildZone().isInto(loc)) {
                return 3;
            }
            if (!g.getGuildZone().isInSecureZone(loc) || block) continue;
            return 4;
        }
        return 0;
    }
}

