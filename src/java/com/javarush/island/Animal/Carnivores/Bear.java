package com.javarush.island.Animal.Carnivores;

import com.javarush.island.constant.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.javarush.island.Island.Island.herbivoresAnimalMap;

public class Bear extends Carnivores {

    Map<String, Integer> eatMap = Constants.bearEat;

    private Random chanceToEat = new Random();


    public Bear() {
        super(500, 3, "Bear");
    }

    public Bear(int x, int y) {
        super(500, 3, "Bear");
        setLocation(x, y);
    }

    @Override
    public boolean eat() {
        if (herbivoresAnimalMap.get(this.getLocation()).size() > 0) {
            if (eatMap.containsKey(herbivoresAnimalMap.get(this.getLocation()).get(0).getType())) {
                //не обращайте внимание на eatMap.get("Boar") пока в работе.
                if (eatMap.get("Boar") > chanceToEat.nextInt(100)) {
                   herbivoresAnimalMap.get(this.getLocation()).remove(0);
                   return true;
                }
            }
        }
        return false;
    }
}
