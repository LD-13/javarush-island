package com.javarush.island.Simulator;

import com.javarush.island.Animal.Carnivores.Bear;
import com.javarush.island.Animal.Carnivores.Carnivores;
import com.javarush.island.Animal.Herbivores.Boar;
import com.javarush.island.Animal.Herbivores.Buffalo;
import com.javarush.island.Animal.Herbivores.Herbivores;
import com.javarush.island.Plant.Herb;
import com.javarush.island.constant.Constants;

import java.util.concurrent.*;

import static com.javarush.island.Island.Island.*;

public class AnimalFactory {
    private final Constants constants;
    private ConcurrentMap<String, Integer> logMap = new ConcurrentHashMap<>();

    public AnimalFactory(Constants constants) {
        this.constants = constants;
    }

    public void start() {
        init();
        //  herbMap.forEach((key, value) -> System.out.println(value.size()));
//        System.out.println("=======");
//        herbivoresAnimalMap.forEach((key, value) -> System.out.println(value.size()));
        //  Cell cell3 = Island.Field[2][2];
//        System.out.println(herbivoresAnimalMap.get(cell3).toString());
//        System.out.println(herbMap.get(cell3).toString());
//        System.out.println("=======");
        // Herbivores boar = new Boar();
        // boar.setLocation(2, 2);
        // herbivoresAnimalMap.get(cell3).add(new Boar(2, 2));
        // herbivoresAnimalMap.get(Field[2][2]).add(new Boar(2, 2));
        // System.out.println(herbivoresAnimalMap.get(Field[2][2]));
//        herbivoresAnimalMap.forEach((key, value) -> System.out.println(value.size()));
        //   System.out.println(herbivoresAnimalMap.get(Field[2][2]));
        //   System.out.println(herbMap.get(Field[2][2]));
        //   System.out.println(carnivoresAnimalMap.get(Field[2][2]));
        logMessage();
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
                log("Herb"); // Знаю, что не правильно, но т.к. трава одна, я решил не создавать в класс Herb имя Herb.
            }
        }
    }

    private void herbivoresInit(int x, int y) {
        for (int i = 0; i < constants.getMaxHerbivoresOnCell().length; i++) {
             int randomHerbivores = ThreadLocalRandom.current().nextInt(constants.getMaxHerbivoreOnCell(i));
            for (int j = 0; j < randomHerbivores; j++) {
                Herbivores herbivores = createHerbivores(i, x, y);
                herbivoresAnimalMap.get(Field[x][y]).add(herbivores);
                log(herbivores.getType());
            }
        }
    }

    private void carnivoresInit(int x, int y) {
        for (int i = 0; i < constants.getMaxCarnivoresOnCell().length; i++) {
            int randomCarnivores = ThreadLocalRandom.current().nextInt(constants.getMaxCarnivoreOnCell(i));
            for (int j = 0; j < randomCarnivores; j++) {
                Carnivores carnivores = createCarnivores(i, x, y);
                carnivoresAnimalMap.get(Field[x][y]).add(carnivores);
                log(carnivores.getType());
            }
        }
    }

    private Herbivores createHerbivores(int numberOFAnimal, int x, int y) {
        switch (numberOFAnimal) {
            case 0:
                return new Boar(x, y);
            case 1:
                return new Buffalo(x, y);
            default:
                return null;
        }
    }

    private Carnivores createCarnivores(int numberOFAnimal, int x, int y) {
        switch (numberOFAnimal) {
            case 0:
                return new Bear(x, y);
            default:
                return null;
        }
    }

    private void log(String type) {
        if (!logMap.containsKey(type)) {
            logMap.put(type, 1);
        } else {
            logMap.put(type, logMap.get(type) + 1);
        }
    }

    private void logMessage() {
        System.out.println("Всего на острове создалось " + logMap);
    }
}


//        Herbivores boar = new Boar();
//        boar.setLocation(2, 2);
//        Herbivores boar2 = new Boar();
//        boar2.setLocation(2, 2);
//
//        Herbivores buffalo = new Buffalo();
//        buffalo.setLocation(2, 2);
//
//        Carnivores bear = new Bear();
//        bear.setLocation(2, 2);
//        Carnivores bear2 = new Bear();
//        bear2.setLocation(2, 2);
//
//        Cell cell3 = Island.Field[2][2];
//
//        herbivoresAnimalMap.get(cell3).add(boar);
//        herbivoresAnimalMap.get(cell3).add(boar2);
//        herbivoresAnimalMap.get(cell3).add(buffalo);
//
//        carnivoresAnimalMap.get(cell3).add(bear);
//        carnivoresAnimalMap.get(cell3).add(bear2);
//
//        System.out.println(herbivoresAnimalMap.get(cell3).toString());
//        System.out.println(carnivoresAnimalMap.get(cell3).toString());
//        System.out.println("-------------------");
//        boar.produce();
//        bear.eat();
//        bear2.produce();
//        System.out.println(herbivoresAnimalMap.get(cell3).toString());
//        System.out.println(carnivoresAnimalMap.get(cell3).toString());
//        bear.move();
//        System.out.println(bear.getLocation());
//        buffalo.eat();
//        boar.eat();
//        boar2.eat();