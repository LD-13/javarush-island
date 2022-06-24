package com.javarush.island.constant;

import com.javarush.island.animal.carnivores.*;
import com.javarush.island.animal.herbivores.*;

import java.util.Map;

public class Constants {
    /*
    Для простаты ставлю срок беременности 3 дня, для всех животных.
    Хотя можно было сделать мар ключ - животное, значение - дни.
    Но уже время поджимает.
     */
    private int pregnancyPeriod = 3;

    public static final int XField = 80;
    public static final int YField = 5;

    private final int[] maxHerbivoresOnCell = new int[10];
    private final int[] maxCarnivoresOnCell = new int[5];
    private final int[] maxHerbsOnCell = new int[1];

    public static final Map<String, Integer> bearEat = Map.ofEntries(
            Map.entry("Boa", 80),
            Map.entry("Horse", 40),
            Map.entry("Boar", 50),
            Map.entry("Deer", 80),
            Map.entry("Rabbit", 80),
            Map.entry("Mouse", 90),
            Map.entry("Goat", 70),
            Map.entry("Sheep", 70),
            Map.entry("Buffalo", 20),
            Map.entry("Duck", 10)
    );

    public static final Map<String, Integer> boaEat = Map.ofEntries(
            Map.entry("Fox", 15),
            Map.entry("Rabbit", 20),
            Map.entry("Mouse", 40),
            Map.entry("Duck", 10)
    );

    public static final Map<String, Integer> eagleEat = Map.ofEntries(
            Map.entry("Fox", 10),
            Map.entry("Rabbit", 90),
            Map.entry("Mouse", 90),
            Map.entry("Duck", 80)
    );

    public static final Map<String, Integer> foxEat = Map.ofEntries(
            Map.entry("Rabbit", 70),
            Map.entry("Mouse", 90),
            Map.entry("Duck", 60),
            Map.entry("Caterpillar", 40)
    );

    public static final Map<String, Integer> wolfEat = Map.ofEntries(
            Map.entry("Horse", 10),
            Map.entry("Deer", 15),
            Map.entry("Rabbit", 60),
            Map.entry("Goat", 60),
            Map.entry("Mouse", 80),
            Map.entry("Sheep", 70),
            Map.entry("Boar", 15),
            Map.entry("Buffalo", 10),
            Map.entry("Duck", 40)
    );

    public int getMaxHerbivoreOnCell(int i) {
        maxHerbivoresOnCell[0] = 50; // boar
        maxHerbivoresOnCell[1] = 10; // buffalo
        maxHerbivoresOnCell[2] = 20; // deer
        maxHerbivoresOnCell[3] = 200; // duck
        maxHerbivoresOnCell[4] = 60; // goat
        maxHerbivoresOnCell[5] = 20; // Horse
        maxHerbivoresOnCell[6] = 500; // Mouse
        maxHerbivoresOnCell[7] = 150; // rabbit
        maxHerbivoresOnCell[8] = 140; // sheep
        maxHerbivoresOnCell[9] = 1000; // caterpillar
        return maxHerbivoresOnCell[i];
    }

    public int[] getMaxHerbivoresOnCell() {
        return maxHerbivoresOnCell;
    }

    public int getMaxCarnivoreOnCell(int i) {
        maxCarnivoresOnCell[0] = 5; // bear
        maxCarnivoresOnCell[1] = 30; // boa
        maxCarnivoresOnCell[2] = 20; // eagle
        maxCarnivoresOnCell[3] = 30; // fox
        maxCarnivoresOnCell[4] = 30; // wolf
        return maxCarnivoresOnCell[i];
    }

    public int[] getMaxCarnivoresOnCell() {
        return maxCarnivoresOnCell;
    }

    public int getMaxHerbOnCell(int i) {
        maxHerbsOnCell[0] = 200; // herb
        return maxHerbsOnCell[i];
    }

    public int[] getMaxHerbsOnCell() {
        return maxHerbsOnCell;
    }

    public int getPregnancyPeriod() {
        return pregnancyPeriod;
    }

    public Carnivores returnCarnivores(String type) {
        return switch (type) {
            case "Bear" -> new Bear();
            case "Boa" -> new Boa();
            case "Eagle" -> new Eagle();
            case "Fox" -> new Fox();
            default -> new Wolf();
        };
    }

    public Herbivores returnHerbivores(String type) {
        return switch (type) {
            case "Boar" -> new Boar();
            case "Buffalo" -> new Buffalo();
            case "Deer" -> new Deer();
            case "Duck" -> new Duck();
            case "Goat" -> new Goat();
            case "Horse" -> new Horse();
            case "Mouse" -> new Mouse();
            case "Rabbit" -> new Rabbit();
            case "Sheep" -> new Sheep();
            default -> new Caterpillar();
        };
    }
}
