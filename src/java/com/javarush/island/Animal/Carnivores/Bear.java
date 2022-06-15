package com.javarush.island.Animal.Carnivores;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.javarush.island.Island.Island.herbivoresAnimalMap;

public class Bear extends Carnivores {

    Map<String, Integer> eatMap = new HashMap<>();

    private Random chanceToEat = new Random();


    public Bear() {
        super(500, 3, "Bear");
        eatMap.put("Boar", 70);
    }

    @Override
    public void eat() {
        if (herbivoresAnimalMap.get(this.getLocation()).size() > 0) {
            if (eatMap.containsKey(herbivoresAnimalMap.get(this.getLocation()).get(0).getType())) {
                if (eatMap.get("Boar") > chanceToEat.nextInt(100)) {
                    herbivoresAnimalMap.get(this.getLocation()).remove(0);
                }
            }
        }
    }
}
