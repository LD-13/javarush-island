package com.javarush.island.Animal;

import com.javarush.island.Island.Cell;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.javarush.island.Island.Island.*;

public abstract class Animal implements Behavior {
    private int weight;
    private int energy;
    private int HP = 100;
    private String type;
    private KindOfAnimal Character;

    private boolean isSaturation = false;
    private boolean isAlive = true;

    private Cell location;

    private Random randomAction = new Random();

    public Animal(int weight, int energy, String type, KindOfAnimal character) {
        this.weight = weight;
        this.energy = energy;
        this.type = type;
        this.Character = character;
    }

    @Override
    public boolean produce() {
      //  System.out.println(this.Character.equals(KindOfAnimal.HERBIVORES));
       // if (!this.isSaturation()) {
            var animalMap = this.Character.equals(KindOfAnimal.HERBIVORES) ? herbivoresAnimalMap : carnivoresAnimalMap;
            List<Animal> animal = animalMap.get(this.getLocation()).stream()
                    .filter(c -> c.getType().equals(this.getType()))
                    .filter(c -> !c.isSaturation())
                    .collect(Collectors.toList());
            if (animal.size() > 1) {
                if (randomAction.nextInt(100) > 70) {
                    animal.get(0).setSaturation(true);
                    return true;
                }
            }
       // }
        return false;
    }

    @Override
    public void move() {
        switch (randomAction.nextInt(4)) {
            case 0:
                if (location.getX() > 0) {
                    location.setX(location.getX() - 1);
                    break;
                }
            case 1:
                if (location.getY() > 0) {
                    location.setY(location.getY() - 1);
                    break;
                }
            case 2:
                if (location.getX() < Field.length - 1) {
                    location.setX(location.getX() + 1);
                    break;
                }
            case 3:
                if (location.getY() < Field[0].length - 1) {
                    location.setY(location.getY() + 1);
                    break;
                } else {
                    location.setY(location.getY() - 1);
                }
        }
    }

    public String getType() {
        return type;
    }

    public Cell getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        location = Field[x][y];
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean isSaturation() {
        return isSaturation;
    }

    public void setSaturation(boolean saturation) {
        isSaturation = saturation;
    }

    @Override
    public String toString() {
        return Character + "{" +
                "type='" + type + '\'' +
                ", weight=" + weight +
                ", speed=" + energy +
                ", isSaturation=" + isSaturation +
                ", location=" + location +
                '}' + "\n";
    }
}
