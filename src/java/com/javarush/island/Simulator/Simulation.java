package com.javarush.island.Simulator;

import com.javarush.island.Animal.Carnivores.Carnivores;
import com.javarush.island.Island.Cell;
import com.javarush.island.Plant.Herb;
import com.javarush.island.constant.Constants;

import java.util.ArrayList;
import java.util.Map;

import static com.javarush.island.Island.Island.*;

public class Simulation {

    private int eatenAnimals = 0;
    private int saturation = 0;

    private Constants constants;

    public Simulation(Constants constants) {
        this.constants = constants;
    }

    public void start() {
        carnivoresAnimal();
        herbGrow();
        System.out.println(logMessage());
    }

    private void carnivoresAnimal() {
//        for (int x = 0; x < Field.length; x++) {
//            for (int y = 0; y < Field[x].length; y++) {
        for (Map.Entry<Cell, ArrayList<Carnivores>> entry : carnivoresAnimalMap.entrySet()) {
            for (Carnivores carnivores : entry.getValue()) {
                int energy = carnivores.getEnergy();
                while (energy > 0) {
                    if (carnivores.getHP() < 90) {
                        logEat(carnivores.eat());
                        energy--;
                    }
                    if (!carnivores.isSaturation() && energy > 0) {
                        logSaturation(carnivores.produce());
                        energy--;
                    }
                    if (energy > 0) {
                        carnivores.move();
                        energy--;
                    }
                }
            }
        }
    }

    private void herbGrow() {
        for (Map.Entry<Cell, ArrayList<Herb>> entry : herbMap.entrySet()) {
            if (entry.getValue().size() < constants.getMaxHerbOnCell(0)) {
               // for (Herb herb : entry.getValue()) {
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        herbMap.get(entry.getKey()).add(new Herb());
                //    }
                }
            }
        }
    }

    private void logEat(boolean isEat) {
        if (isEat) {
            eatenAnimals++;
        }
    }

    private void logSaturation(boolean isSaturation) {
        if (isSaturation) {
            saturation++;
        }
    }

    private String logMessage() {
        return "Всего животных было съедено " + eatenAnimals + ". Всего животных забеременело " + saturation;
    }
//        }
//    }
}
