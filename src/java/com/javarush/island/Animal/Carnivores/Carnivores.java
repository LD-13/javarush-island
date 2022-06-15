package com.javarush.island.Animal.Carnivores;

import com.javarush.island.Animal.*;

public abstract class Carnivores extends Animal {

    public Carnivores(int weight, int speed, String type) {
        super(weight, speed, type, KindOfAnimal.CARNIVORES);
    }
}
