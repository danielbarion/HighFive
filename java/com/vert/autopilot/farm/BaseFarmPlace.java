package com.vert.autopilot.farm;

import com.vert.autopilot.FakePlayer;

public class BaseFarmPlace {
    private String name = "BaseFarmPlace";
    private int X = 0;
    private int Y = 0;
    private int Z = 0;
    private int range = 0;
    private int minLevel = 1;
    private int maxLevel = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getZ() {
        return Z;
    }

    public void setZ(int z) {
        Z = z;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public boolean playerCanFarmInThisPlace(FakePlayer player) {
        return player.getLevel() >= getMinLevel() && player.getLevel() <= getMaxLevel();
    }

    public boolean isPlayerHere(FakePlayer player) {
        return player.isInsideRadius3D(getX(), getY(), getZ(), getRange());
    }
}
