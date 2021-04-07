/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.BookMeta
 */
package com.dark.guilds.utils;

import java.lang.reflect.Method;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookUtil {
    private static boolean initialised = false;
    private static Method getHandle;
    private static Method openBook;

    static {
        try {
            getHandle = ReflectionUtils.getMethod("CraftPlayer", ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY, "getHandle", new Class[0]);
            openBook = ReflectionUtils.getMethod("EntityPlayer", ReflectionUtils.PackageType.MINECRAFT_SERVER, "a", ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ItemStack"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EnumHand"));
            initialised = true;
        }
        catch (ReflectiveOperationException e) {
            e.printStackTrace();
            Bukkit.getServer().getLogger().warning("Cannot force open book!");
            initialised = false;
        }
    }

    public static boolean isInitialised() {
        return initialised;
    }

    public static boolean openBook(ItemStack i, Player p) {
        if (!initialised) {
            return false;
        }
        ItemStack held = p.getInventory().getItemInMainHand();
        try {
            p.getInventory().setItemInMainHand(i);
            BookUtil.sendPacket(i, p);
        }
        catch (ReflectiveOperationException e) {
            e.printStackTrace();
            initialised = false;
        }
        p.getInventory().setItemInMainHand(held);
        return initialised;
    }

    private static void sendPacket(ItemStack i, Player p) throws ReflectiveOperationException {
        Object entityplayer = getHandle.invoke((Object)p, new Object[0]);
        Class<?> enumHand = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EnumHand");
        Object[] enumArray = enumHand.getEnumConstants();
        openBook.invoke(entityplayer, BookUtil.getItemStack(i), enumArray[0]);
    }

    public static Object getItemStack(ItemStack item) {
        try {
            Method asNMSCopy = ReflectionUtils.getMethod(ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asNMSCopy", ItemStack.class);
            return asNMSCopy.invoke(ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), new Object[]{item});
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
	public static void setPages(BookMeta metadata, List<String> pages) {
        try {
            List<Object> p = (List<Object>)ReflectionUtils.getField(ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftMetaBook"), true, "pages").get((Object)metadata);
            for (String text : pages) {
                Object page = ReflectionUtils.invokeMethod(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("IChatBaseComponent$ChatSerializer").newInstance(), "a", text);
                p.add(page);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

