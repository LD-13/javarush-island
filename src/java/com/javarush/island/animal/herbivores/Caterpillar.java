package com.javarush.island.animal.herbivores;

public class Caterpillar extends Herbivores {
    public Caterpillar() {
        super(0.01, 1, "Caterpillar");
    }

    @Override
    public boolean eat() {
        setHungryLevel(100);
        return true;
    }
}
