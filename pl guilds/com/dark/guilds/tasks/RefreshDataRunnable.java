/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.scheduler.BukkitRunnable
 */
package com.dark.guilds.tasks;

import com.dark.guilds.Guilds;
import java.util.logging.Logger;
import org.bukkit.scheduler.BukkitRunnable;

public class RefreshDataRunnable
extends BukkitRunnable {
    Guilds guilds;

    public RefreshDataRunnable(Guilds guilds) {
        this.guilds = guilds;
    }

    public void run() {
        this.guilds.getLogger().info("Tous les fichiers de joueurs sont rafra\u00eechis !");
        this.guilds.refreshPlayersData();
    }
}

