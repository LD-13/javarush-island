package com.javarush.island.Animal.Herbivores;

import com.javarush.island.Animal.*;

import static com.javarush.island.Island.Island.herbMap;

public abstract class Herbivores extends Animal {

    public Herbivores(int weight, int speed, String type) {
        super(weight, speed, type, KindOfAnimal.HERBIVORES);
    }

    public abstract int maxQuantity();

    @Override
    public boolean eat() {
        if (herbMap.get(this.getLocation()).size() > 0) {
            herbMap.get(this.getLocation()).remove(0);
            return true;
        }
        return false;
    }
}
