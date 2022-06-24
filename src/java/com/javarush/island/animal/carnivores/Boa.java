package com.javarush.island.animal.carnivores;

import com.javarush.island.animal.herbivores.Herbivores;
import com.javarush.island.constant.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.javarush.island.island.Island.herbivoresAnimalMap;

public class Boa extends Carnivores {
    private Map<String, Integer> eatMap = Constants.boaEat;
    private List<Herbivores> availableEat = new ArrayList<>();

    private Random chanceToEat = new Random();
    private Herbivores prey;

    public Boa() {
        super(15, 2, "Boa");
    }

    @Override
    public boolean eat() {
        if (herbivoresAnimalMap.get(this.getLocation()).size() > 0) {
            for (Herbivores herbivore : herbivoresAnimalMap.get(this.getLocation())) {
                if (eatMap.containsKey(herbivore.getType())) {
                    availableEat.add(herbivore);
                }
            }
            if (availableEat.size() == 0) {
                return false;
            }
            prey = availableEat.get(chanceToEat.nextInt(availableEat.size()));
            availableEat.clear();
            if (eatMap.get(prey.getType()) > chanceToEat.nextInt(100)) {
                herbivoresAnimalMap.forEach((key, value) -> value.remove(prey));
                setHungryLevel(100);
                return true;
            }
        }
        return false;
    }
}
