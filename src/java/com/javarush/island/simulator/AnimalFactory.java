package com.javarush.island.simulator;

import com.javarush.island.animal.carnivores.*;
import com.javarush.island.animal.herbivores.*;
import com.javarush.island.plant.Herb;
import com.javarush.island.constant.Constants;

import java.util.concurrent.*;

import static com.javarush.island.island.Island.*;

public class AnimalFactory {
    private final Constants constants;
    private ConcurrentMap<String, Integer> logMap = new ConcurrentHashMap<>();

    public AnimalFactory(Constants constants) {
        this.constants = constants;
    }

    public void start() {
        init();
        statisticsMessage();
    }

    private void init() {
        for (int x = 0; x < Field.length; x++) {
            for (int y = 0; y < Field[x].length; y++) {
                    herbInit(x, y);
                    herbivoresInit(x, y);
                    carnivoresInit(x, y);
            }
        }
    }

    private void herbInit(int x, int y) {
        for (int i = 0; i < constants.getMaxHerbsOnCell().length; i++) {
            int randomHerb = ThreadLocalRandom.current().nextInt(constants.getMaxHerbOnCell(i));
            for (int j = 0; j < randomHerb; j++) {
                herbMap.get(Field[x][y]).add(new Herb(Field[x][y]));
                statistics("Herb"); // Знаю, что не правильно, но т.к. трава одна, я решил не создавать в класс Herb имя Herb.
            }
        }
    }

    private void herbivoresInit(int x, int y) {
        for (int i = 0; i < constants.getMaxHerbivoresOnCell().length; i++) {
            int randomHerbivores = ThreadLocalRandom.current().nextInt(constants.getMaxHerbivoreOnCell(i));
            for (int j = 0; j < randomHerbivores; j++) {
                Herbivores herbivores = createHerbivores(i);
                herbivores.setLocation(x, y);
                herbivoresAnimalMap.get(Field[x][y]).add(herbivores);
                statistics(herbivores.getType());
            }
        }
    }

    private void carnivoresInit(int x, int y) {
        for (int i = 0; i < constants.getMaxCarnivoresOnCell().length; i++) {
            int randomCarnivores = ThreadLocalRandom.current().nextInt(constants.getMaxCarnivoreOnCell(i));
            for (int j = 0; j < randomCarnivores; j++) {
                Carnivores carnivores = createCarnivores(i);
                carnivores.setLocation(x, y);
                carnivoresAnimalMap.get(Field[x][y]).add(carnivores);
                statistics(carnivores.getType());
            }
        }
    }

    private Herbivores createHerbivores(int numberOFAnimal) {
        return switch (numberOFAnimal) {
            case 0 -> new Boar();
            case 1 -> new Buffalo();
            case 2 -> new Deer();
            case 3 -> new Duck();
            case 4 -> new Goat();
            case 5 -> new Horse();
            case 6 -> new Mouse();
            case 7 -> new Rabbit();
            case 8 -> new Sheep();
            default -> new Caterpillar();
        };
    }

    private Carnivores createCarnivores(int numberOFAnimal) {
        return switch (numberOFAnimal) {
            case 0 -> new Bear();
            case 1 -> new Boa();
            case 2 -> new Eagle();
            case 3 -> new Fox();
            default -> new Wolf();
        };
    }

    private void statistics(String type) {
        if (!logMap.containsKey(type)) {
            logMap.put(type, 1);
        } else {
            logMap.put(type, logMap.get(type) + 1);
        }
    }

    private void statisticsMessage() {
        System.out.println("Всего на острове создалось " + logMap);
    }
}
