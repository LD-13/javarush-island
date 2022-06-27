package com.javarush.island.animal.carnivores;

import com.javarush.island.animal.*;

public abstract class Carnivores extends Animal {

    public Carnivores(int weight, int energy, String type) {
        super(weight, energy, type, KindOfAnimal.CARNIVORES);
    }
}
