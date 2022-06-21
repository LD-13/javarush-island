package com.javarush.island.Animal.Carnivores;

import com.javarush.island.Animal.*;

public abstract class Carnivores extends Animal {

    public Carnivores(int weight, int energy, String type) {
        super(weight, energy, type, KindOfAnimal.CARNIVORES);
    }
}
