/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 */
package com.dark.guilds.zone;

import org.bukkit.Location;

public class APZone {
    private int xMin;
    private int xMax;
    private int zMin;
    private int zMax;
    private Location loc1;
    private Location loc2;

    public APZone(Location loc1, Location loc2) {
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
        if (loc.getBlockX() >= this.xMin && loc.getBlockX() <= this.xMax && loc.getBlockZ() >= this.zMin && loc.getBlockZ() <= this.zMax) {
            return true;
        }
        return false;
    }
}

