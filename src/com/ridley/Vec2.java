package com.ridley;

import java.io.Serializable;

public final class Vec2 implements Serializable
{
    private int x;
    private int y;

    public Vec2(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Overriding this will ensure use of ArrayList.contains(e) in Pathfinder correctly compares values.
    @Override
    public boolean equals(Object o) {
        Vec2 other = (Vec2) o;
        return other.getX() == this.getX() && other.getY() == this.getY();
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    //Used for indicating when a tile is not currently on the board (the tile is currently being manipulated).
    public boolean isNull() {
        return this.x == -1 || this.y == -1;
    }

    public static Vec2 nullVec() {
        return new Vec2(-1, -1);
    }

    public double dist(Vec2 other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }
}
