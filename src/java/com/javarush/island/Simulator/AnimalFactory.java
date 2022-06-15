package com.javarush.island.Simulator;

import com.javarush.island.Animal.Carnivores.Bear;
import com.javarush.island.Animal.Carnivores.Carnivores;
import com.javarush.island.Animal.Herbivores.Boar;
import com.javarush.island.Animal.Herbivores.Buffalo;
import com.javarush.island.Animal.Herbivores.Herbivores;
import com.javarush.island.Island.*;
import com.javarush.island.Plant.Herb;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.island.Island.Island.*;

public class AnimalFactory {
    public void start() {
        herbInit();
       // herbMap.forEach((key, value) -> System.out.println(value.size()));

        Herbivores boar = new Boar();
        boar.setLocation(2, 2);
        Herbivores boar2 = new Boar();
        boar2.setLocation(2, 2);

        Herbivores buffalo = new Buffalo();
        buffalo.setLocation(2, 2);

        Carnivores bear = new Bear();
        bear.setLocation(2, 2);
        Carnivores bear2 = new Bear();
        bear2.setLocation(2, 2);

        Cell cell3 = Island.Field[2][2];

        herbivoresAnimalMap.get(cell3).add(boar);
        herbivoresAnimalMap.get(cell3).add(boar2);
        herbivoresAnimalMap.get(cell3).add(buffalo);

        carnivoresAnimalMap.get(cell3).add(bear);
        carnivoresAnimalMap.get(cell3).add(bear2);

        System.out.println(herbivoresAnimalMap.get(cell3).toString());
        System.out.println(carnivoresAnimalMap.get(cell3).toString());
        System.out.println("-------------------");
        boar.produce();
        bear.eat();
        bear2.produce();
        System.out.println(herbivoresAnimalMap.get(cell3).toString());
        System.out.println(carnivoresAnimalMap.get(cell3).toString());
        bear.move();
        System.out.println(bear.getLocation());
        buffalo.eat();
        boar.eat();
        boar2.eat();
    }

    private void herbInit() {
        Random randomHerb = new Random();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < Field.length; i++) {
            for (int j = 0; j < Field[i].length; j++) {
                int boundedRandomValue = ThreadLocalRandom.current().nextInt(0, 200);
                for (int k = 0; k < boundedRandomValue; k++) {
                    int finalI = i;
                    int finalJ = j;
                    executorService.execute(() -> herbMap.get(Field[finalI][finalJ]).add(new Herb(Field[finalI][finalJ])));
                   // herbMap.get(Field[i][j]).add(new Herb(Field[i][j]));
                }
                //System.out.println(herbMap.get(Field[i][j]).size());
            }
        }
        executorService.shutdown();
    }
}
