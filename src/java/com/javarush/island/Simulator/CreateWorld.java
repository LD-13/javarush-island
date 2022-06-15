package com.javarush.island.Simulator;

import com.javarush.island.Island.Island;

public class CreateWorld {
    public void start() {
        Island island = new Island();
        island.initField();
        AnimalFactory animalFactory = new AnimalFactory();
        animalFactory.start();
    }
}
