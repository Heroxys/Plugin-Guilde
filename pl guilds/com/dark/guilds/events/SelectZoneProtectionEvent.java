/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.player.PlayerCommandPreprocessEvent
 *  org.bukkit.event.player.PlayerDropItemEvent
 *  org.bukkit.event.player.PlayerItemHeldEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.ItemMeta
 */
package com.dark.guilds.events;

import com.dark.guilds.zone.Selecteur;
import java.util.ArrayList;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class SelectZoneProtectionEvent
implements Listener {
    @EventHandler(priority=EventPriority.HIGH)
    public void onChangeSlot(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        if (Selecteur.Selectionneurs.contains((Object)p) || Selecteur.SelectionneursAP1.contains((Object)p) || Selecteur.SelectionneursAP2.contains((Object)p)) {
            e.setCancelled(true);
            if (p.getInventory().getItem(e.getNewSlot()).getType() != Material.AIR && p.getInventory().getItem(e.getNewSlot()).getItemMeta().getDisplayName().equals("\u00a78Outil de Claim")) {
                return;
            }
            int i = 0;
            while (i != 10) {
                p.sendMessage(" ");
                ++i;
            }
            p.sendMessage("1");
            p.sendMessage((Object)ChatColor.RED + "Vous ne pouvez pas faire cette action avant d'avoir s\u00e9l\u00e9ctionn\u00e9 votre zone de guilde");
            p.sendMessage((Object)ChatColor.GRAY + "Pour annuler la s\u00e9l\u00e9ction de la zone de guilde, veuillez jeter la pelle");
            return;
        }
    }

    @EventHandler(priority=EventPriority.HIGH)
    public void onCmd(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String[] args = e.getMessage().split(" ");
        if (Selecteur.Selectionneurs.contains((Object)p) || Selecteur.SelectionneursAP1.contains((Object)p) || Selecteur.SelectionneursAP2.contains((Object)p)) {
            if (args[0].equalsIgnoreCase("/claim")) {
                return;
            }
            if (args[0].equalsIgnoreCase("/ap")) {
                return;
            }
            int i = 0;
            while (i != 10) {
                p.sendMessage(" ");
                ++i;
            }
            p.sendMessage((Object)ChatColor.RED + "Vous ne pouvez pas faire cette action avant d'avoir s\u00e9l\u00e9ctionn\u00e9 votre zone de guilde");
            p.sendMessage((Object)ChatColor.GRAY + "Pour annuler la s\u00e9l\u00e9ction de la zone de guilde, veuillez jeter la pelle");
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler(priority=EventPriority.HIGH)
    public void onInteract(InventoryClickEvent e) {
        if (e.getInventory().getName().equals("\u00a78Menu d'administration")) {
            return;
        }
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player p = (Player)e.getWhoClicked();
        if (Selecteur.Selectionneurs.contains((Object)p) || Selecteur.SelectionneursAP1.contains((Object)p) || Selecteur.SelectionneursAP2.contains((Object)p)) {
            int i = 0;
            while (i != 10) {
                p.sendMessage(" ");
                ++i;
            }
            p.sendMessage((Object)ChatColor.RED + "Vous ne pouvez pas faire cette action avant d'avoir s\u00e9l\u00e9ctionn\u00e9 votre zone de guilde");
            p.sendMessage((Object)ChatColor.GRAY + "Pour annuler la s\u00e9l\u00e9ction de la zone de guilde, veuillez jeter la pelle");
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler(priority=EventPriority.HIGH)
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (Selecteur.Selectionneurs.contains((Object)p) || Selecteur.SelectionneursAP1.contains((Object)p) || Selecteur.SelectionneursAP2.contains((Object)p)) {
            if (e.getItemDrop().getItemStack().getType() != Material.GOLD_AXE) {
                return;
            }
            if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals("\u00a78Outil de Claim")) {
                p.sendMessage((Object)ChatColor.GREEN + "Vous venez d'annuler la s\u00e9l\u00e9ction");
                e.getItemDrop().remove();
                p.getItemInHand().setType(Material.AIR);
                Selecteur.Selectionneurs.remove((Object)p);
                Selecteur.SelectionneursAP1.remove((Object)p);
                Selecteur.SelectionneursAP2.remove((Object)p);
                return;
            }
        }
    }
}

