/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 */
package com.dark.guilds.zone;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerSelection {
    private Location Loc1;
    private Location Loc2;
    private Player p;

    public PlayerSelection(Player p, Location Loc1, Location Loc2) {
        this.p = p;
        this.Loc1 = Loc1;
        this.Loc2 = Loc2;
    }

    public Location getLoc1() {
        return this.Loc1;
    }

    public void setLoc1(Location loc1) {
        this.Loc1 = loc1;
    }

    public Location getLoc2() {
        return this.Loc2;
    }

    public void setLoc2(Location loc2) {
        this.Loc2 = loc2;
    }

    public Player getP() {
        return this.p;
    }

    public void setP(Player p) {
        this.p = p;
    }
}

