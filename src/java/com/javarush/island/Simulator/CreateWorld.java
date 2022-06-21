package com.javarush.island.Simulator;

import com.javarush.island.Island.Island;
import com.javarush.island.constant.Constants;

public class CreateWorld {
    public void start() {
        Constants constants = new Constants();
        Island island = new Island();
        island.initField();
        AnimalFactory animalFactory = new AnimalFactory(constants);
        animalFactory.start();
        Simulation simulation = new Simulation(constants);
        simulation.start();
    }
}
