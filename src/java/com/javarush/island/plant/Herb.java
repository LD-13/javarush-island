package com.javarush.island.plant;

import com.javarush.island.island.Cell;

import static com.javarush.island.island.Island.Field;

public class Herb {
    private Cell location;
    private static volatile int quantity = 0;

    public Herb() {
    }

    public Herb(Cell location) {
        this.location = location;
    }

    public Cell getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        location = Field[x][y];
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        Herb.quantity = quantity;
    }
}
