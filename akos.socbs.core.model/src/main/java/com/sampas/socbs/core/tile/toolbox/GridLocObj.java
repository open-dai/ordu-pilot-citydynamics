package com.sampas.socbs.core.tile.toolbox;

/**
 * This is an annoying object that exists solely because Java doesn't play nice
 * with int[] in hashmaps
 * 
 * @author ak
 */
public class GridLocObj {
    private int x;

    private int y;

    private int z;

    public GridLocObj(int[] gridLoc) {
        x = gridLoc[0];
        y = gridLoc[1];
        z = gridLoc[2];
    }

    public boolean equals(Object obj) {
        if (obj instanceof GridLocObj) {
            GridLocObj other = (GridLocObj) obj;
            return x == other.x && y == other.y && z == other.z;
        }

        return false;
    }

    public int hashCode() {
        return (int) ((x * 433 + y * 73 + z) % Integer.MAX_VALUE);
    }

    public String toString() {
        return "{" + x + "," + y + "," + z + "}";
    }
}
