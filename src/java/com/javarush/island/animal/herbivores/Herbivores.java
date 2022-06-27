package com.javarush.island.animal.herbivores;

import com.javarush.island.animal.*;

import static com.javarush.island.island.Island.herbMap;

public abstract class Herbivores extends Animal {

    public Herbivores(double weight, int speed, String type) {
        super(weight, speed, type, KindOfAnimal.HERBIVORES);
    }

    @Override
    public boolean eat() {
        if (herbMap.get(this.getLocation()).size() > 0) {
            herbMap.get(this.getLocation()).remove(0);
            setHungryLevel(100);
            return true;
        }
        return false;
    }
}
