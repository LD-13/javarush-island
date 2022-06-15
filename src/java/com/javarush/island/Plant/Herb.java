package com.javarush.island.Plant;

import com.javarush.island.Island.Cell;

import static com.javarush.island.Island.Island.Field;

public class Herb {
    private Cell location;

    public Herb(Cell location) {
        this.location = location;
    }

    public Cell getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        location = Field[x][y];
    }
}
