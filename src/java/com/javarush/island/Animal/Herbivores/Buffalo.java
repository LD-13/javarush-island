package com.javarush.island.Animal.Herbivores;

public class Buffalo extends Herbivores {
    private final int maxQuantity = 10;

    public Buffalo() {
        super(80, 4, "Buffalo");
    }

    public Buffalo(int x, int y) {
        super(60, 3, "Buffalo");
        setLocation(x, y);
    }

    @Override
    public int maxQuantity() {
        return maxQuantity;
    }
}
