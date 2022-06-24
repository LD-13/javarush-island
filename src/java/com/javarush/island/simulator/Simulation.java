package com.javarush.island.simulator;

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.carnivores.Carnivores;
import com.javarush.island.animal.herbivores.Herbivores;
import com.javarush.island.island.Cell;
import com.javarush.island.plant.Herb;
import com.javarush.island.constant.Constants;

import java.util.*;

import static com.javarush.island.island.Island.*;

public class Simulation {

    private static int days = 0;
    private int eatenAnimals = 0;
    private int eatenHerb = 0;
    private int saturation = 0;
    private int grownHerbQuantity = 0;
    private int bornAnimal = 0;
    private int deadFromHunger = 0;

    private Constants constants;

    private Map<Cell, Integer> growHerb = new HashMap<>();
    private ArrayList<Herbivores> pregnancyHerbivores = new ArrayList<>();
    private ArrayList<Carnivores> pregnancyCarnivores = new ArrayList<>();
    //private Map<Cell, String> pregnancyHerbivores = new HashMap<>();
    // ExecutorService executorService = Executors.newFixedThreadPool(1);

    public Simulation(Constants constants) {
        this.constants = constants;
    }

    public void start() {
        while (!stop()) {
            days++;
            eatenAnimals = 0;
            eatenHerb = 0;
            saturation = 0;
            grownHerbQuantity = 0;
            bornAnimal = 0;
            deadFromHunger = 0;
//        int k = 0;
//        for (Map.Entry<Cell, ArrayList<Herbivores>> entry : herbivoresAnimalMap.entrySet()) {
//            k += entry.getValue().size();
//        }
//        System.out.println("животные " + k);
            carnivoresAnimal();
            herbivoresAnimal();
            growHerb();
            bornCarnivores();
            bornHerbivores();
            deadCarnivoresAnimal();
            deadHerbivoresAnimal();
            System.out.println(statisticsMessage());
        }
       // executorService.shutdown();

    }

    private void carnivoresAnimal() {
        for (Map.Entry<Cell, ArrayList<Carnivores>> entry : carnivoresAnimalMap.entrySet()) {
                for (Carnivores carnivores : entry.getValue()) {
                    int energy = carnivores.getEnergy();
                    while (energy > 0) {
                        if (carnivores.getHungryLevel() < 90) {
                            statisticsEatMeat(carnivores.eat());
                            energy--;
                        }
                        if (!carnivores.isSaturation() && energy > 0) {
                            if (carnivores.produce()) {
                                statisticsSaturation();
                            }
                            energy--;
                        }
                        if (energy > 0) {
                            carnivores.move();
                            energy--;
                        }
                    }
                    carnivores.setHungryLevel(carnivores.getHungryLevel() - 30);
                }
        }
    }

    private void herbivoresAnimal() {
        for (Map.Entry<Cell, ArrayList<Herbivores>> entry : herbivoresAnimalMap.entrySet()) {
            for (Herbivores herbivores : entry.getValue()) {
                int energy = herbivores.getEnergy();
                while (energy > 0) {
                    if (herbivores.getHungryLevel() < 90) {
                        statisticsEatHerb(herbivores.eat());
                        energy--;
                    }
                    if (!herbivores.isSaturation() && energy > 0) {
                        if (herbivores.produce()) {
                            statisticsSaturation();
                        }
                        energy--;
                    }
                    if (energy > 0) {
                        herbivores.move();
                        energy--;
                    }
                }
                herbivores.setHungryLevel(herbivores.getHungryLevel() - 30);
            }
        }
    }

    private void growHerb() {
        for (Map.Entry<Cell, ArrayList<Herb>> entry : herbMap.entrySet()) {
            if (entry.getValue().size() < constants.getMaxHerbOnCell(0)) {
                for (int i = 0; i < entry.getValue().size(); i++) {
                    growHerb.put(entry.getKey(), entry.getValue().size() * 4);
                }
                grownHerbQuantity += entry.getValue().size() * 4;
            }
        }
        addGrownHerb();
    }

    private void addGrownHerb() {
        for (Map.Entry<Cell, Integer> entry : growHerb.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                herbMap.get(entry.getKey()).add(new Herb(entry.getKey()));
            }
        }
        growHerb.clear();
    }

    private void bornCarnivores() {
        for (Map.Entry<Cell, ArrayList<Carnivores>> carnivores : carnivoresAnimalMap.entrySet()) {
            for (Carnivores carnivore : carnivores.getValue()) {
                if (carnivore.born()) {
                    pregnancyCarnivores.add(carnivore);
                }
            }
        }
        addBornCarnivores();
    }

    private void addBornCarnivores() {
        for (Carnivores pregnancyCarnivore : pregnancyCarnivores) {
            /*
            Тут должно быть обращение к Constants и там должно храниться сколько детенышей должно быть у каждого вида.
            Не успеваю, поэтому для простаты у каждого вида приплод по 5.
             */
            for (int i = 0; i < 5; i++) {
                Carnivores carnivores = constants.returnCarnivores(pregnancyCarnivore.getType());
                carnivores.setLocation(pregnancyCarnivore.getLocation().getX(), pregnancyCarnivore.getLocation().getY());
                carnivoresAnimalMap.get(pregnancyCarnivore.getLocation()).add(carnivores);
                statisticsBorn();
            }
        }
        pregnancyCarnivores.clear();
    }

    private void bornHerbivores() {
        for (Map.Entry<Cell, ArrayList<Herbivores>> herbivores : herbivoresAnimalMap.entrySet()) {
            for (Herbivores herbivore : herbivores.getValue()) {
                if (herbivore.born()) {
                    pregnancyHerbivores.add(herbivore);
                }
            }
        }
        addBornHerbivores();
    }

    private void addBornHerbivores() {
        for (Herbivores pregnancyHerbivore : pregnancyHerbivores) {
            /*
            Тут должно быть обращение к Constants и там должно храниться сколько детенышей должно быть у каждого вида.
            Не успеваю, поэтому для простаты у каждого вида приплод по 5.
             */
            for (int i = 0; i < 5; i++) {
                Herbivores herbivores = constants.returnHerbivores(pregnancyHerbivore.getType());
                herbivores.setLocation(pregnancyHerbivore.getLocation().getX(), pregnancyHerbivore.getLocation().getY());
                herbivoresAnimalMap.get(pregnancyHerbivore.getLocation()).add(herbivores);
                statisticsBorn();
            }
        }
        pregnancyHerbivores.clear();
    }

    private void deadHerbivoresAnimal() {
        for (Map.Entry<Cell, ArrayList<Herbivores>> entry : herbivoresAnimalMap.entrySet()) {
            for (Herbivores herbivore : entry.getValue()) {
                if (herbivore.diedOfHunger()) {
                    statisticsDeadHunger();
                }
            }
        }
        herbivoresAnimalMap.forEach((key, value) -> value.removeIf(Animal::isDead));
    }

    private void deadCarnivoresAnimal() {
        for (Map.Entry<Cell, ArrayList<Carnivores>> entry : carnivoresAnimalMap.entrySet()) {
            for (Carnivores carnivore : entry.getValue()) {
                if (carnivore.diedOfHunger()) {
                    statisticsDeadHunger();
                }
            }
        }
        carnivoresAnimalMap.forEach((key, value) -> value.removeIf(Animal::isDead));
    }

    private void statisticsEatMeat(boolean isEat) {
        if (isEat) {
            eatenAnimals++;
        }
    }

    private void statisticsEatHerb(boolean isEat) {
        if (isEat) {
            eatenHerb++;
        }
    }

    private void statisticsSaturation() {
        saturation++;
    }

    private void statisticsBorn() {
        bornAnimal++;
    }

    private void statisticsDeadHunger() {
        deadFromHunger++;
    }

    private String statisticsMessage() {
        return "День " + days + ". Всего животных было съедено " + eatenAnimals +
                ". Всего травы было съедено " + eatenHerb +
                ". Всего животных забеременело " + saturation +
                ". Всего травы выросло " + grownHerbQuantity +
                ". Всего животных родилось " + bornAnimal +
                ". Всего умерло от голода " + deadFromHunger;
    }

    private boolean stop() {
        int herbQuantity = 0;
        for (Map.Entry<Cell, ArrayList<Herb>> entry : herbMap.entrySet()) {
            herbQuantity += entry.getValue().size();
        }
        //System.out.println(herbQuantity + " herbQuantity");
        if (herbQuantity <= 0) {
            System.out.println("Закончилась трава");
            return true;
        }
        int herbivoresQuantity = 0;
        for (Map.Entry<Cell, ArrayList<Herbivores>> entry : herbivoresAnimalMap.entrySet()) {
            herbivoresQuantity += entry.getValue().size();
        }
        //System.out.println(herbivoresQuantity + " herbivoresQuantity");
        if (herbivoresQuantity <= 0) {
            System.out.println("Закончились травоядные");
            return true;
        }
        int carnivoresQuantity = 0;
        for (Map.Entry<Cell, ArrayList<Carnivores>> entry : carnivoresAnimalMap.entrySet()) {
            carnivoresQuantity += entry.getValue().size();
        }
        //System.out.println(carnivoresQuantity + " carnivoresQuantity");
        if (carnivoresQuantity <= 0) {
            System.out.println("Закончились плотоядные");
            return true;
        }
        return false;
    }
}
