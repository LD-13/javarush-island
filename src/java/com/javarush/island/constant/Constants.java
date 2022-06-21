package com.javarush.island.constant;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    private final int[] maxHerbivoresOnCell = new int[2];
    private final int[] maxCarnivoresOnCell = new int[1];
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

    public int getMaxHerbivoreOnCell(int i) {
        maxHerbivoresOnCell[0] = 50; // boar
        maxHerbivoresOnCell[1] = 10; // buffalo
        return maxHerbivoresOnCell[i];
    }

    public int[] getMaxHerbivoresOnCell() {
        return maxHerbivoresOnCell;
    }

    public int getMaxCarnivoreOnCell(int i) {
        maxCarnivoresOnCell[0] = 5; // bear
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

    private Map<String, Integer> setBearEat() {
        bearEat.put("Boa", 80);
        bearEat.put("Horse", 40);
        bearEat.put("Boar", 50);
        bearEat.put("Deer", 80);
        bearEat.put("Rabbit", 80);
        bearEat.put("Mouse", 90);
        bearEat.put("Goat", 70);
        bearEat.put("Sheep", 70);
        bearEat.put("Buffalo", 20);
        bearEat.put("Duck", 10);
        return bearEat;
    }
}
