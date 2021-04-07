/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.scheduler.BukkitRunnable
 */
package com.dark.guilds.tasks;

import com.dark.guilds.Guilds;
import com.dark.guilds.players.BanPlayers;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.scheduler.BukkitRunnable;

public class BanPlayersTask
extends BukkitRunnable {
    public Guilds pl;

    public BanPlayersTask(Guilds guilds) {
        this.pl = guilds;
    }

    public void run() {
        ArrayList<BanPlayers> aDeban = new ArrayList<BanPlayers>();
        if (this.pl.bansPlayers.size() != 0) {
            for (BanPlayers pls : this.pl.bansPlayers) {
                --pls.time;
                if (pls.time != 0) continue;
                aDeban.add(pls);
            }
            if (this.pl.bansPlayers.size() != 0) {
                for (BanPlayers pls : aDeban) {
                    this.pl.bansPlayers.remove(pls);
                }
            }
        }
    }
}

