package com.javarush.island.Animal.Herbivores;

public class Boar extends Herbivores {
    private final int maxQuantity = 50;

    public Boar() {
        super(60, 3, "Boar");
    }

    public Boar(int x, int y) {
        super(60, 3, "Boar");
        setLocation(x, y);
    }

    public int maxQuantity() {
        return maxQuantity;
    }
}
