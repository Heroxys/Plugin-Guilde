/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.SkullMeta
 */
package com.dark.guilds.utils;

import com.dark.guilds.Guild;
import com.dark.guilds.Guilds;
import com.dark.guilds.players.GuildRank;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Utils {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$dark$guilds$players$GuildRank;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$bukkit$Material;

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes((char)'&', (String)s);
    }

    public static ItemStack getItem(Material mat, byte data, String dispName, List<String> lore) {
        if (lore == null) {
            ItemStack it = new ItemStack(mat, 1, (short)data);
            ItemMeta meta = it.getItemMeta();
            meta.setDisplayName(Utils.color(dispName));
            it.setItemMeta(meta);
            return it;
        }
        ItemStack it = new ItemStack(mat, 1, (short)data);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(Utils.color(dispName));
        ArrayList<String> coloredLore = new ArrayList<String>();
        for (String s : lore) {
            coloredLore.add(Utils.color(s));
        }
        meta.setLore(coloredLore);
        it.setItemMeta(meta);
        return it;
    }

    public static ItemStack getItem(Material mat, int amount, String dispName, List<String> lore) {
        if (lore == null) {
            ItemStack it = new ItemStack(mat, amount);
            ItemMeta meta = it.getItemMeta();
            meta.setDisplayName(Utils.color(dispName));
            it.setItemMeta(meta);
            return it;
        }
        ItemStack it = new ItemStack(mat, amount);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(Utils.color(dispName));
        ArrayList<String> coloredLore = new ArrayList<String>();
        for (String s : lore) {
            coloredLore.add(Utils.color(s));
        }
        meta.setLore(coloredLore);
        it.setItemMeta(meta);
        return it;
    }

    @SuppressWarnings("deprecation")
	public static ItemStack getHead(String owner, String dispName, List<String> lore) {
        if (lore == null) {
            ItemStack it = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta meta = (SkullMeta)it.getItemMeta();
            meta.setOwner(owner);
            meta.setDisplayName(Utils.color(dispName));
            it.setItemMeta((ItemMeta)meta);
            return it;
        }
        ItemStack it = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta)it.getItemMeta();
        meta.setOwner(owner);
        meta.setDisplayName(Utils.color(dispName));
        ArrayList<String> coloredLore = new ArrayList<String>();
        for (String s : lore) {
            coloredLore.add(Utils.color(s));
        }
        meta.setLore(coloredLore);
        it.setItemMeta((ItemMeta)meta);
        return it;
    }

    public static String prettifyText(String ugly) {
        if (!ugly.contains("_") && !ugly.equals(ugly.toUpperCase())) {
            return ugly;
        }
        String fin = "";
        if ((ugly = ugly.toLowerCase()).contains("_")) {
            String[] splt = ugly.split("_");
            int i = 0;
            String[] arrstring = splt;
            int n = arrstring.length;
            int n2 = 0;
            while (n2 < n) {
                String s = arrstring[n2];
                fin = String.valueOf(fin) + Character.toUpperCase(s.charAt(0)) + s.substring(1);
                if (++i < splt.length) {
                    fin = String.valueOf(fin) + " ";
                }
                ++n2;
            }
        } else {
            fin = String.valueOf(fin) + Character.toUpperCase(ugly.charAt(0)) + ugly.substring(1);
        }
        return fin;
    }

    public static String isInConflict(boolean bool) {
        if (bool) {
            return "Est en conflit";
        }
        return "N'est pas en conflit";
    }

    public static String getAliases(String s) {
        Pattern p = Pattern.compile("\\b[a-zA-Z]");
        Matcher m = p.matcher(s);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (m.find()) {
            sb.append(m.group());
        }
        sb.append("] \u00a77\u00bb\u00a7r ");
        return sb.toString();
    }

    public static String getRank(GuildRank rank) {
        switch (Utils.$SWITCH_TABLE$com$dark$guilds$players$GuildRank()[rank.ordinal()]) {
            case 1: {
                return "\u00a76Chef";
            }
            case 2: {
                return "\u00a7eAdjoint";
            }
            case 3: {
                return "\u00a77Membre";
            }
        }
        return "";
    }

    public static Inventory getQuota(int level, Guild guild) {
        if (level == 1) {
            Inventory quota = Bukkit.createInventory(null, (int)9, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(8, Utils.getItem(Material.ARROW, (byte)0, "\u00a78Retour", null));
            quota.setItem(2, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(4, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(6, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            return quota;
        }
        if (level == 2) {
            Inventory quota = Bukkit.createInventory(null, (int)9, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(0, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(2, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(4, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(6, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(8, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(1, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(3, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(5, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            quota.setItem(7, Utils.getItem(Material.STONE, 64, "\u00a782 point de quota", null));
            quota.setItem(0, Utils.getItem(Material.ARROW, (byte)0, "\u00a78Retour", null));
            return quota;
        }
        if (level == 3) {
            Inventory quota = Bukkit.createInventory(null, (int)9, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(0, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(2, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(4, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            quota.setItem(6, Utils.getItem(Material.STONE, 64, "\u00a782 point de quota", null));
            quota.setItem(8, Utils.getItem(Material.LOG, 64, "\u00a783 point de quota", null));
            quota.setItem(0, Utils.getItem(Material.ARROW, (byte)0, "\u00a78Retour", null));
            return quota;
        }
        if (level == 4) {
            Inventory quota = Bukkit.createInventory(null, (int)18, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(11, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(12, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(14, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(15, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(16, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(0, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(2, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(4, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            quota.setItem(6, Utils.getItem(Material.STONE, 64, "\u00a782 point de quota", null));
            quota.setItem(8, Utils.getItem(Material.LOG, 64, "\u00a783 point de quota", null));
            quota.setItem(13, Utils.getItem(Material.WOOL, 64, "\u00a782 point de quota", null));
            quota.setItem(0, Utils.getItem(Material.ARROW, (byte)0, "\u00a78Retour", null));
            return quota;
        }
        if (level == 5) {
            Inventory quota = Bukkit.createInventory(null, (int)18, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(11, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(13, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(15, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(16, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(0, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(2, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(4, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            quota.setItem(6, Utils.getItem(Material.STONE, 64, "\u00a782 point de quota", null));
            quota.setItem(8, Utils.getItem(Material.LOG, 64, "\u00a783 point de quota", null));
            quota.setItem(12, Utils.getItem(Material.WOOL, 64, "\u00a782 point de quota", null));
            quota.setItem(14, Utils.getItem(Material.SANDSTONE, 64, "\u00a782 point de quota", null));
            quota.setItem(0, Utils.getItem(Material.ARROW, (byte)0, "\u00a78Retour", null));
            return quota;
        }
        if (level == 6) {
            Inventory quota = Bukkit.createInventory(null, (int)18, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(12, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(14, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(16, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(0, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(2, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(4, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            quota.setItem(6, Utils.getItem(Material.STONE, 64, "\u00a782 point de quota", null));
            quota.setItem(8, Utils.getItem(Material.LOG, 64, "\u00a783 point de quota", null));
            quota.setItem(11, Utils.getItem(Material.WOOL, 64, "\u00a782 point de quota", null));
            quota.setItem(13, Utils.getItem(Material.SANDSTONE, 64, "\u00a782 point de quota", null));
            quota.setItem(15, Utils.getItem(Material.NETHERRACK, 64, "\u00a781 point de quota", null));
            quota.setItem(0, Utils.getItem(Material.ARROW, (byte)0, "\u00a78Retour", null));
            return quota;
        }
        if (level == 7) {
            Inventory quota = Bukkit.createInventory(null, (int)18, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(11, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(13, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(15, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(0, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(2, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(4, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            quota.setItem(6, Utils.getItem(Material.STONE, 64, "\u00a782 point de quota", null));
            quota.setItem(8, Utils.getItem(Material.LOG, 64, "\u00a783 point de quota", null));
            quota.setItem(10, Utils.getItem(Material.WOOL, 64, "\u00a782 point de quota", null));
            quota.setItem(12, Utils.getItem(Material.SANDSTONE, 64, "\u00a782 point de quota", null));
            quota.setItem(14, Utils.getItem(Material.NETHERRACK, 64, "\u00a781 point de quota", null));
            quota.setItem(16, Utils.getItem(Material.BRICK, 64, "\u00a784 point de quota", null));
            quota.setItem(0, Utils.getItem(Material.ARROW, (byte)0, "\u00a78Retour", null));
            return quota;
        }
        if (level == 8) {
            Inventory quota = Bukkit.createInventory(null, (int)27, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(11, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(13, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(15, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(18, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(19, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(20, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(21, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(23, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(24, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(25, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(26, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(0, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(2, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(4, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            quota.setItem(6, Utils.getItem(Material.STONE, 64, "\u00a782 point de quota", null));
            quota.setItem(8, Utils.getItem(Material.LOG, 64, "\u00a783 point de quota", null));
            quota.setItem(10, Utils.getItem(Material.WOOL, 64, "\u00a782 point de quota", null));
            quota.setItem(12, Utils.getItem(Material.SANDSTONE, 64, "\u00a782 point de quota", null));
            quota.setItem(14, Utils.getItem(Material.NETHERRACK, 64, "\u00a781 point de quota", null));
            quota.setItem(16, Utils.getItem(Material.BRICK, 64, "\u00a784 point de quota", null));
            quota.setItem(22, Utils.getItem(Material.PRISMARINE, 64, "\u00a786 point de quota", null));
            quota.setItem(0, Utils.getItem(Material.ARROW, (byte)0, "\u00a78Retour", null));
            return quota;
        }
        if (level == 9) {
            Inventory quota = Bukkit.createInventory(null, (int)27, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(11, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(13, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(15, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(18, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(19, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(20, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(22, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(24, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(25, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(26, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(0, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(2, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(4, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            quota.setItem(6, Utils.getItem(Material.STONE, 64, "\u00a782 point de quota", null));
            quota.setItem(8, Utils.getItem(Material.LOG, 64, "\u00a783 point de quota", null));
            quota.setItem(10, Utils.getItem(Material.WOOL, 64, "\u00a782 point de quota", null));
            quota.setItem(12, Utils.getItem(Material.SANDSTONE, 64, "\u00a782 point de quota", null));
            quota.setItem(14, Utils.getItem(Material.NETHERRACK, 64, "\u00a781 point de quota", null));
            quota.setItem(16, Utils.getItem(Material.BRICK, 64, "\u00a784 point de quota", null));
            quota.setItem(21, Utils.getItem(Material.PRISMARINE, 64, "\u00a786 point de quota", null));
            quota.setItem(23, Utils.getItem(Material.IRON_BLOCK, 64, "\u00a788 point de quota", null));
            quota.setItem(0, Utils.getItem(Material.ARROW, (byte)0, "\u00a78Retour", null));
            return quota;
        }
        if (level == 10) {
            Inventory quota = Bukkit.createInventory(null, (int)27, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(11, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(13, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(15, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(18, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(19, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(21, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(23, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(25, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(26, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(0, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(2, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(4, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            quota.setItem(6, Utils.getItem(Material.STONE, 64, "\u00a782 point de quota", null));
            quota.setItem(8, Utils.getItem(Material.LOG, 64, "\u00a783 point de quota", null));
            quota.setItem(10, Utils.getItem(Material.WOOL, 64, "\u00a782 point de quota", null));
            quota.setItem(12, Utils.getItem(Material.SANDSTONE, 64, "\u00a782 point de quota", null));
            quota.setItem(14, Utils.getItem(Material.NETHERRACK, 64, "\u00a781 point de quota", null));
            quota.setItem(16, Utils.getItem(Material.BRICK, 64, "\u00a784 point de quota", null));
            quota.setItem(20, Utils.getItem(Material.PRISMARINE, 64, "\u00a786 point de quota", null));
            quota.setItem(22, Utils.getItem(Material.IRON_BLOCK, 64, "\u00a788 point de quota", null));
            quota.setItem(24, Utils.getItem(Material.DRAGON_EGG, 1, "\u00a78100 point de quota", null));
            return quota;
        }
        if (level == 11) {
            Inventory quota = Bukkit.createInventory(null, (int)27, (String)("\u00a78Quota (" + guild.getQuota() + "/" + Guilds.getQuotaMax(guild.getLevel()) + ")"));
            quota.setItem(1, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(3, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(5, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(7, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(9, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(11, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(13, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(15, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(17, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(18, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(19, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(21, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(23, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(25, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(26, Utils.getItem(Material.STAINED_GLASS_PANE, (byte)15, " ", null));
            quota.setItem(0, Utils.getItem(Material.DIRT, 64, "\u00a781 point de quota", null));
            quota.setItem(2, Utils.getItem(Material.COBBLESTONE, 64, "\u00a781 point de quota", null));
            quota.setItem(4, Utils.getItem(Material.SAND, 64, "\u00a781 point de quota", null));
            quota.setItem(6, Utils.getItem(Material.STONE, 64, "\u00a782 point de quota", null));
            quota.setItem(8, Utils.getItem(Material.LOG, 64, "\u00a783 point de quota", null));
            quota.setItem(10, Utils.getItem(Material.WOOL, 64, "\u00a782 point de quota", null));
            quota.setItem(12, Utils.getItem(Material.SANDSTONE, 64, "\u00a782 point de quota", null));
            quota.setItem(14, Utils.getItem(Material.NETHERRACK, 64, "\u00a781 point de quota", null));
            quota.setItem(16, Utils.getItem(Material.BRICK, 64, "\u00a784 point de quota", null));
            quota.setItem(20, Utils.getItem(Material.PRISMARINE, 64, "\u00a786 point de quota", null));
            quota.setItem(22, Utils.getItem(Material.IRON_BLOCK, 64, "\u00a788 point de quota", null));
            quota.setItem(24, Utils.getItem(Material.DRAGON_EGG, 1, "\u00a78100 point de quota", null));
            quota.setItem(0, Utils.getItem(Material.ARROW, (byte)0, "\u00a78Retour", null));
            return quota;
        }
        return null;
    }

    public static int getQuotaValue(Material mat) {
        switch (Utils.$SWITCH_TABLE$org$bukkit$Material()[mat.ordinal()]) {
            case 5: {
                return 1;
            }
            case 4: {
                return 1;
            }
            case 13: {
                return 1;
            }
            case 2: {
                return 2;
            }
            case 163: {
                return 3;
            }
            case 18: {
                return 3;
            }
            case 36: {
                return 2;
            }
            case 25: {
                return 2;
            }
            case 88: {
                return 1;
            }
            case 46: {
                return 4;
            }
            case 169: {
                return 6;
            }
            case 43: {
                return 8;
            }
            case 123: {
                return 100;
            }
        }
        return 0;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$dark$guilds$players$GuildRank() {
        int[] arrn;
        int[] arrn2 = $SWITCH_TABLE$com$dark$guilds$players$GuildRank;
        if (arrn2 != null) {
            return arrn2;
        }
        arrn = new int[GuildRank.values().length];
        try {
            arrn[GuildRank.BOSS.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[GuildRank.MEMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[GuildRank.MODERATOR.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        $SWITCH_TABLE$com$dark$guilds$players$GuildRank = arrn;
        return $SWITCH_TABLE$com$dark$guilds$players$GuildRank;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$bukkit$Material() {
        int[] arrn;
        int[] arrn2 = $SWITCH_TABLE$org$bukkit$Material;
        if (arrn2 != null) {
            return arrn2;
        }
        arrn = new int[Material.values().length];
        try {
            arrn[Material.ACACIA_DOOR.ordinal()] = 197;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ACACIA_DOOR_ITEM.ordinal()] = 429;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ACACIA_FENCE.ordinal()] = 193;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ACACIA_FENCE_GATE.ordinal()] = 188;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ACACIA_STAIRS.ordinal()] = 164;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ACTIVATOR_RAIL.ordinal()] = 158;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.AIR.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ANVIL.ordinal()] = 146;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.APPLE.ordinal()] = 259;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ARMOR_STAND.ordinal()] = 415;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ARROW.ordinal()] = 261;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BAKED_POTATO.ordinal()] = 392;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BANNER.ordinal()] = 424;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BARRIER.ordinal()] = 167;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BEACON.ordinal()] = 139;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BED.ordinal()] = 354;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BEDROCK.ordinal()] = 8;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BED_BLOCK.ordinal()] = 27;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BEETROOT.ordinal()] = 433;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BEETROOT_BLOCK.ordinal()] = 208;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BEETROOT_SEEDS.ordinal()] = 434;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BEETROOT_SOUP.ordinal()] = 435;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BIRCH_DOOR.ordinal()] = 195;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BIRCH_DOOR_ITEM.ordinal()] = 427;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BIRCH_FENCE.ordinal()] = 190;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BIRCH_FENCE_GATE.ordinal()] = 185;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BIRCH_WOOD_STAIRS.ordinal()] = 136;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BLACK_GLAZED_TERRACOTTA.ordinal()] = 251;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BLACK_SHULKER_BOX.ordinal()] = 235;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BLAZE_POWDER.ordinal()] = 376;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BLAZE_ROD.ordinal()] = 368;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BLUE_GLAZED_TERRACOTTA.ordinal()] = 247;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BLUE_SHULKER_BOX.ordinal()] = 231;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOAT.ordinal()] = 332;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOAT_ACACIA.ordinal()] = 446;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOAT_BIRCH.ordinal()] = 444;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOAT_DARK_OAK.ordinal()] = 447;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOAT_JUNGLE.ordinal()] = 445;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOAT_SPRUCE.ordinal()] = 443;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BONE.ordinal()] = 351;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BONE_BLOCK.ordinal()] = 217;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOOK.ordinal()] = 339;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOOKSHELF.ordinal()] = 48;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOOK_AND_QUILL.ordinal()] = 385;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOW.ordinal()] = 260;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BOWL.ordinal()] = 280;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BREAD.ordinal()] = 296;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BREWING_STAND.ordinal()] = 118;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BREWING_STAND_ITEM.ordinal()] = 378;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BRICK.ordinal()] = 46;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BRICK_STAIRS.ordinal()] = 109;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BROWN_GLAZED_TERRACOTTA.ordinal()] = 248;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BROWN_MUSHROOM.ordinal()] = 40;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BROWN_SHULKER_BOX.ordinal()] = 232;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BUCKET.ordinal()] = 324;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.BURNING_FURNACE.ordinal()] = 63;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CACTUS.ordinal()] = 82;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CAKE.ordinal()] = 353;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CAKE_BLOCK.ordinal()] = 93;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CARPET.ordinal()] = 172;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CARROT.ordinal()] = 142;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CARROT_ITEM.ordinal()] = 390;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CARROT_STICK.ordinal()] = 397;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CAULDRON.ordinal()] = 119;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CAULDRON_ITEM.ordinal()] = 379;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CHAINMAIL_BOOTS.ordinal()] = 304;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CHAINMAIL_CHESTPLATE.ordinal()] = 302;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CHAINMAIL_HELMET.ordinal()] = 301;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CHAINMAIL_LEGGINGS.ordinal()] = 303;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CHEST.ordinal()] = 55;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CHORUS_FLOWER.ordinal()] = 201;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CHORUS_FRUIT.ordinal()] = 431;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CHORUS_FRUIT_POPPED.ordinal()] = 432;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CHORUS_PLANT.ordinal()] = 200;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CLAY.ordinal()] = 83;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CLAY_BALL.ordinal()] = 336;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CLAY_BRICK.ordinal()] = 335;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COAL.ordinal()] = 262;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COAL_BLOCK.ordinal()] = 174;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COAL_ORE.ordinal()] = 17;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COBBLESTONE.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COBBLESTONE_STAIRS.ordinal()] = 68;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COBBLE_WALL.ordinal()] = 140;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COCOA.ordinal()] = 128;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COMMAND.ordinal()] = 138;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COMMAND_CHAIN.ordinal()] = 212;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COMMAND_MINECART.ordinal()] = 421;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COMMAND_REPEATING.ordinal()] = 211;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COMPASS.ordinal()] = 344;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CONCRETE.ordinal()] = 252;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CONCRETE_POWDER.ordinal()] = 253;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COOKED_BEEF.ordinal()] = 363;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COOKED_CHICKEN.ordinal()] = 365;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COOKED_FISH.ordinal()] = 349;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COOKED_MUTTON.ordinal()] = 423;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COOKED_RABBIT.ordinal()] = 411;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.COOKIE.ordinal()] = 356;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CROPS.ordinal()] = 60;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CYAN_GLAZED_TERRACOTTA.ordinal()] = 245;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.CYAN_SHULKER_BOX.ordinal()] = 229;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DARK_OAK_DOOR.ordinal()] = 198;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DARK_OAK_DOOR_ITEM.ordinal()] = 430;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DARK_OAK_FENCE.ordinal()] = 192;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DARK_OAK_FENCE_GATE.ordinal()] = 187;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DARK_OAK_STAIRS.ordinal()] = 165;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DAYLIGHT_DETECTOR.ordinal()] = 152;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DAYLIGHT_DETECTOR_INVERTED.ordinal()] = 179;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DEAD_BUSH.ordinal()] = 33;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DETECTOR_RAIL.ordinal()] = 29;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND.ordinal()] = 263;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_AXE.ordinal()] = 278;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_BARDING.ordinal()] = 418;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_BLOCK.ordinal()] = 58;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_BOOTS.ordinal()] = 312;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_CHESTPLATE.ordinal()] = 310;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_HELMET.ordinal()] = 309;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_HOE.ordinal()] = 292;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_LEGGINGS.ordinal()] = 311;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_ORE.ordinal()] = 57;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_PICKAXE.ordinal()] = 277;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_SPADE.ordinal()] = 276;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIAMOND_SWORD.ordinal()] = 275;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIODE.ordinal()] = 355;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIODE_BLOCK_OFF.ordinal()] = 94;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIODE_BLOCK_ON.ordinal()] = 95;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DIRT.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DISPENSER.ordinal()] = 24;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DOUBLE_PLANT.ordinal()] = 176;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DOUBLE_STEP.ordinal()] = 44;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DOUBLE_STONE_SLAB2.ordinal()] = 182;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DRAGONS_BREATH.ordinal()] = 436;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DRAGON_EGG.ordinal()] = 123;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.DROPPER.ordinal()] = 159;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.EGG.ordinal()] = 343;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ELYTRA.ordinal()] = 442;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.EMERALD.ordinal()] = 387;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.EMERALD_BLOCK.ordinal()] = 134;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.EMERALD_ORE.ordinal()] = 130;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.EMPTY_MAP.ordinal()] = 394;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ENCHANTED_BOOK.ordinal()] = 402;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ENCHANTMENT_TABLE.ordinal()] = 117;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ENDER_CHEST.ordinal()] = 131;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ENDER_PEARL.ordinal()] = 367;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ENDER_PORTAL.ordinal()] = 120;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ENDER_PORTAL_FRAME.ordinal()] = 121;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ENDER_STONE.ordinal()] = 122;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.END_BRICKS.ordinal()] = 207;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.END_CRYSTAL.ordinal()] = 425;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.END_GATEWAY.ordinal()] = 210;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.END_ROD.ordinal()] = 199;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.EXPLOSIVE_MINECART.ordinal()] = 406;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.EXP_BOTTLE.ordinal()] = 383;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.EYE_OF_ENDER.ordinal()] = 380;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FEATHER.ordinal()] = 287;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FENCE.ordinal()] = 86;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FENCE_GATE.ordinal()] = 108;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FERMENTED_SPIDER_EYE.ordinal()] = 375;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FIRE.ordinal()] = 52;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FIREBALL.ordinal()] = 384;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FIREWORK.ordinal()] = 400;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FIREWORK_CHARGE.ordinal()] = 401;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FISHING_ROD.ordinal()] = 345;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FLINT.ordinal()] = 317;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FLINT_AND_STEEL.ordinal()] = 258;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FLOWER_POT.ordinal()] = 141;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FLOWER_POT_ITEM.ordinal()] = 389;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FROSTED_ICE.ordinal()] = 213;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.FURNACE.ordinal()] = 62;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GHAST_TEAR.ordinal()] = 369;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GLASS.ordinal()] = 21;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GLASS_BOTTLE.ordinal()] = 373;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GLOWING_REDSTONE_ORE.ordinal()] = 75;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GLOWSTONE.ordinal()] = 90;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GLOWSTONE_DUST.ordinal()] = 347;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLDEN_APPLE.ordinal()] = 321;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLDEN_CARROT.ordinal()] = 395;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_AXE.ordinal()] = 285;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_BARDING.ordinal()] = 417;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_BLOCK.ordinal()] = 42;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_BOOTS.ordinal()] = 316;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_CHESTPLATE.ordinal()] = 314;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_HELMET.ordinal()] = 313;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_HOE.ordinal()] = 293;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_INGOT.ordinal()] = 265;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_LEGGINGS.ordinal()] = 315;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_NUGGET.ordinal()] = 370;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_ORE.ordinal()] = 15;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_PICKAXE.ordinal()] = 284;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_PLATE.ordinal()] = 148;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_RECORD.ordinal()] = 452;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_SPADE.ordinal()] = 283;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GOLD_SWORD.ordinal()] = 282;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GRASS.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GRASS_PATH.ordinal()] = 209;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GRAVEL.ordinal()] = 14;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GRAY_GLAZED_TERRACOTTA.ordinal()] = 243;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GRAY_SHULKER_BOX.ordinal()] = 227;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GREEN_GLAZED_TERRACOTTA.ordinal()] = 249;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GREEN_RECORD.ordinal()] = 453;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GREEN_SHULKER_BOX.ordinal()] = 233;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.GRILLED_PORK.ordinal()] = 319;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.HARD_CLAY.ordinal()] = 173;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.HAY_BLOCK.ordinal()] = 171;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.HOPPER.ordinal()] = 155;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.HOPPER_MINECART.ordinal()] = 407;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.HUGE_MUSHROOM_1.ordinal()] = 100;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.HUGE_MUSHROOM_2.ordinal()] = 101;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ICE.ordinal()] = 80;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.INK_SACK.ordinal()] = 350;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_AXE.ordinal()] = 257;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_BARDING.ordinal()] = 416;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_BLOCK.ordinal()] = 43;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_BOOTS.ordinal()] = 308;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_CHESTPLATE.ordinal()] = 306;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_DOOR.ordinal()] = 329;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_DOOR_BLOCK.ordinal()] = 72;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_FENCE.ordinal()] = 102;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_HELMET.ordinal()] = 305;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_HOE.ordinal()] = 291;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_INGOT.ordinal()] = 264;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_LEGGINGS.ordinal()] = 307;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_NUGGET.ordinal()] = 450;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_ORE.ordinal()] = 16;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_PICKAXE.ordinal()] = 256;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_PLATE.ordinal()] = 149;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_SPADE.ordinal()] = 255;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_SWORD.ordinal()] = 266;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.IRON_TRAPDOOR.ordinal()] = 168;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ITEM_FRAME.ordinal()] = 388;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.JACK_O_LANTERN.ordinal()] = 92;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.JUKEBOX.ordinal()] = 85;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.JUNGLE_DOOR.ordinal()] = 196;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.JUNGLE_DOOR_ITEM.ordinal()] = 428;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.JUNGLE_FENCE.ordinal()] = 191;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.JUNGLE_FENCE_GATE.ordinal()] = 186;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.JUNGLE_WOOD_STAIRS.ordinal()] = 137;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.KNOWLEDGE_BOOK.ordinal()] = 451;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LADDER.ordinal()] = 66;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LAPIS_BLOCK.ordinal()] = 23;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LAPIS_ORE.ordinal()] = 22;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LAVA.ordinal()] = 11;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LAVA_BUCKET.ordinal()] = 326;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LEASH.ordinal()] = 419;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LEATHER.ordinal()] = 333;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LEATHER_BOOTS.ordinal()] = 300;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LEATHER_CHESTPLATE.ordinal()] = 298;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LEATHER_HELMET.ordinal()] = 297;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LEATHER_LEGGINGS.ordinal()] = 299;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LEAVES.ordinal()] = 19;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LEAVES_2.ordinal()] = 162;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LEVER.ordinal()] = 70;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LIGHT_BLUE_GLAZED_TERRACOTTA.ordinal()] = 239;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LIGHT_BLUE_SHULKER_BOX.ordinal()] = 223;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LIME_GLAZED_TERRACOTTA.ordinal()] = 241;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LIME_SHULKER_BOX.ordinal()] = 225;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LINGERING_POTION.ordinal()] = 440;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LOG.ordinal()] = 18;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LOG_2.ordinal()] = 163;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.LONG_GRASS.ordinal()] = 32;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MAGENTA_GLAZED_TERRACOTTA.ordinal()] = 238;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MAGENTA_SHULKER_BOX.ordinal()] = 222;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MAGMA.ordinal()] = 214;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MAGMA_CREAM.ordinal()] = 377;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MAP.ordinal()] = 357;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MELON.ordinal()] = 359;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MELON_BLOCK.ordinal()] = 104;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MELON_SEEDS.ordinal()] = 361;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MELON_STEM.ordinal()] = 106;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MILK_BUCKET.ordinal()] = 334;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MINECART.ordinal()] = 327;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MOB_SPAWNER.ordinal()] = 53;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MONSTER_EGG.ordinal()] = 382;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MONSTER_EGGS.ordinal()] = 98;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MOSSY_COBBLESTONE.ordinal()] = 49;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MUSHROOM_SOUP.ordinal()] = 281;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MUTTON.ordinal()] = 422;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.MYCEL.ordinal()] = 111;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NAME_TAG.ordinal()] = 420;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NETHERRACK.ordinal()] = 88;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NETHER_BRICK.ordinal()] = 113;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NETHER_BRICK_ITEM.ordinal()] = 404;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NETHER_BRICK_STAIRS.ordinal()] = 115;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NETHER_FENCE.ordinal()] = 114;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NETHER_STALK.ordinal()] = 371;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NETHER_STAR.ordinal()] = 398;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NETHER_WARTS.ordinal()] = 116;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NETHER_WART_BLOCK.ordinal()] = 215;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.NOTE_BLOCK.ordinal()] = 26;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.OBSERVER.ordinal()] = 219;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.OBSIDIAN.ordinal()] = 50;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ORANGE_GLAZED_TERRACOTTA.ordinal()] = 237;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ORANGE_SHULKER_BOX.ordinal()] = 221;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PACKED_ICE.ordinal()] = 175;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PAINTING.ordinal()] = 320;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PAPER.ordinal()] = 338;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PINK_GLAZED_TERRACOTTA.ordinal()] = 242;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PINK_SHULKER_BOX.ordinal()] = 226;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PISTON_BASE.ordinal()] = 34;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PISTON_EXTENSION.ordinal()] = 35;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PISTON_MOVING_PIECE.ordinal()] = 37;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PISTON_STICKY_BASE.ordinal()] = 30;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.POISONOUS_POTATO.ordinal()] = 393;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PORK.ordinal()] = 318;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PORTAL.ordinal()] = 91;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.POTATO.ordinal()] = 143;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.POTATO_ITEM.ordinal()] = 391;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.POTION.ordinal()] = 372;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.POWERED_MINECART.ordinal()] = 342;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.POWERED_RAIL.ordinal()] = 28;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PRISMARINE.ordinal()] = 169;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PRISMARINE_CRYSTALS.ordinal()] = 409;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PRISMARINE_SHARD.ordinal()] = 408;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PUMPKIN.ordinal()] = 87;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PUMPKIN_PIE.ordinal()] = 399;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PUMPKIN_SEEDS.ordinal()] = 360;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PUMPKIN_STEM.ordinal()] = 105;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PURPLE_GLAZED_TERRACOTTA.ordinal()] = 246;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PURPLE_SHULKER_BOX.ordinal()] = 230;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PURPUR_BLOCK.ordinal()] = 202;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PURPUR_DOUBLE_SLAB.ordinal()] = 205;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PURPUR_PILLAR.ordinal()] = 203;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PURPUR_SLAB.ordinal()] = 206;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.PURPUR_STAIRS.ordinal()] = 204;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.QUARTZ.ordinal()] = 405;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.QUARTZ_BLOCK.ordinal()] = 156;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.QUARTZ_ORE.ordinal()] = 154;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.QUARTZ_STAIRS.ordinal()] = 157;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RABBIT.ordinal()] = 410;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RABBIT_FOOT.ordinal()] = 413;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RABBIT_HIDE.ordinal()] = 414;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RABBIT_STEW.ordinal()] = 412;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RAILS.ordinal()] = 67;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RAW_BEEF.ordinal()] = 362;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RAW_CHICKEN.ordinal()] = 364;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RAW_FISH.ordinal()] = 348;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RECORD_10.ordinal()] = 461;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RECORD_11.ordinal()] = 462;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RECORD_12.ordinal()] = 463;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RECORD_3.ordinal()] = 454;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RECORD_4.ordinal()] = 455;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RECORD_5.ordinal()] = 456;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RECORD_6.ordinal()] = 457;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RECORD_7.ordinal()] = 458;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RECORD_8.ordinal()] = 459;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RECORD_9.ordinal()] = 460;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE.ordinal()] = 330;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE_BLOCK.ordinal()] = 153;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE_COMPARATOR.ordinal()] = 403;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE_COMPARATOR_OFF.ordinal()] = 150;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE_COMPARATOR_ON.ordinal()] = 151;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE_LAMP_OFF.ordinal()] = 124;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE_LAMP_ON.ordinal()] = 125;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE_ORE.ordinal()] = 74;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE_TORCH_OFF.ordinal()] = 76;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE_TORCH_ON.ordinal()] = 77;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.REDSTONE_WIRE.ordinal()] = 56;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RED_GLAZED_TERRACOTTA.ordinal()] = 250;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RED_MUSHROOM.ordinal()] = 41;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RED_NETHER_BRICK.ordinal()] = 216;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RED_ROSE.ordinal()] = 39;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RED_SANDSTONE.ordinal()] = 180;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RED_SANDSTONE_STAIRS.ordinal()] = 181;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.RED_SHULKER_BOX.ordinal()] = 234;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.ROTTEN_FLESH.ordinal()] = 366;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SADDLE.ordinal()] = 328;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SAND.ordinal()] = 13;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SANDSTONE.ordinal()] = 25;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SANDSTONE_STAIRS.ordinal()] = 129;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SAPLING.ordinal()] = 7;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SEA_LANTERN.ordinal()] = 170;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SEEDS.ordinal()] = 294;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SHEARS.ordinal()] = 358;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SHIELD.ordinal()] = 441;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SHULKER_SHELL.ordinal()] = 449;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SIGN.ordinal()] = 322;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SIGN_POST.ordinal()] = 64;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SILVER_GLAZED_TERRACOTTA.ordinal()] = 244;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SILVER_SHULKER_BOX.ordinal()] = 228;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SKULL.ordinal()] = 145;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SKULL_ITEM.ordinal()] = 396;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SLIME_BALL.ordinal()] = 340;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SLIME_BLOCK.ordinal()] = 166;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SMOOTH_BRICK.ordinal()] = 99;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SMOOTH_STAIRS.ordinal()] = 110;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SNOW.ordinal()] = 79;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SNOW_BALL.ordinal()] = 331;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SNOW_BLOCK.ordinal()] = 81;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SOIL.ordinal()] = 61;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SOUL_SAND.ordinal()] = 89;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SPECKLED_MELON.ordinal()] = 381;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SPECTRAL_ARROW.ordinal()] = 438;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SPIDER_EYE.ordinal()] = 374;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SPLASH_POTION.ordinal()] = 437;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SPONGE.ordinal()] = 20;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SPRUCE_DOOR.ordinal()] = 194;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SPRUCE_DOOR_ITEM.ordinal()] = 426;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SPRUCE_FENCE.ordinal()] = 189;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SPRUCE_FENCE_GATE.ordinal()] = 184;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SPRUCE_WOOD_STAIRS.ordinal()] = 135;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STAINED_CLAY.ordinal()] = 160;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STAINED_GLASS.ordinal()] = 96;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STAINED_GLASS_PANE.ordinal()] = 161;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STANDING_BANNER.ordinal()] = 177;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STATIONARY_LAVA.ordinal()] = 12;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STATIONARY_WATER.ordinal()] = 10;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STEP.ordinal()] = 45;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STICK.ordinal()] = 279;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STONE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STONE_AXE.ordinal()] = 274;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STONE_BUTTON.ordinal()] = 78;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STONE_HOE.ordinal()] = 290;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STONE_PICKAXE.ordinal()] = 273;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STONE_PLATE.ordinal()] = 71;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STONE_SLAB2.ordinal()] = 183;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STONE_SPADE.ordinal()] = 272;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STONE_SWORD.ordinal()] = 271;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STORAGE_MINECART.ordinal()] = 341;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STRING.ordinal()] = 286;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STRUCTURE_BLOCK.ordinal()] = 254;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.STRUCTURE_VOID.ordinal()] = 218;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SUGAR.ordinal()] = 352;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SUGAR_CANE.ordinal()] = 337;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SUGAR_CANE_BLOCK.ordinal()] = 84;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.SULPHUR.ordinal()] = 288;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.THIN_GLASS.ordinal()] = 103;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.TIPPED_ARROW.ordinal()] = 439;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.TNT.ordinal()] = 47;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.TORCH.ordinal()] = 51;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.TOTEM.ordinal()] = 448;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.TRAPPED_CHEST.ordinal()] = 147;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.TRAP_DOOR.ordinal()] = 97;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.TRIPWIRE.ordinal()] = 133;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.TRIPWIRE_HOOK.ordinal()] = 132;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.VINE.ordinal()] = 107;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WALL_BANNER.ordinal()] = 178;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WALL_SIGN.ordinal()] = 69;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WATCH.ordinal()] = 346;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WATER.ordinal()] = 9;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WATER_BUCKET.ordinal()] = 325;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WATER_LILY.ordinal()] = 112;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WEB.ordinal()] = 31;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WHEAT.ordinal()] = 295;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WHITE_GLAZED_TERRACOTTA.ordinal()] = 236;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WHITE_SHULKER_BOX.ordinal()] = 220;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOODEN_DOOR.ordinal()] = 65;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_AXE.ordinal()] = 270;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_BUTTON.ordinal()] = 144;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_DOOR.ordinal()] = 323;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_DOUBLE_STEP.ordinal()] = 126;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_HOE.ordinal()] = 289;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_PICKAXE.ordinal()] = 269;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_PLATE.ordinal()] = 73;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_SPADE.ordinal()] = 268;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_STAIRS.ordinal()] = 54;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_STEP.ordinal()] = 127;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOD_SWORD.ordinal()] = 267;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WOOL.ordinal()] = 36;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WORKBENCH.ordinal()] = 59;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.WRITTEN_BOOK.ordinal()] = 386;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.YELLOW_FLOWER.ordinal()] = 38;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.YELLOW_GLAZED_TERRACOTTA.ordinal()] = 240;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Material.YELLOW_SHULKER_BOX.ordinal()] = 224;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        $SWITCH_TABLE$org$bukkit$Material = arrn;
        return $SWITCH_TABLE$org$bukkit$Material;
    }
}

