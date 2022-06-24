package com.javarush.island.animal;

import com.javarush.island.island.Cell;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.javarush.island.island.Island.*;

public abstract class Animal implements Behavior {
    private double weight;
    private int energy;
    private int hungryLevel = 100;

    private String type;
    private KindOfAnimal character;

    /*
        Для простаты ставлю срок беременности 3 дня, для всех животных.
        Хотя можно было сделать мар ключ - животное, значение - дни.
        Но уже время поджимает.
         */
    private int pregnancyPeriod = 3;
    private boolean isSaturation = false;
    private boolean isDead = false;

    private Cell location;

    private Random randomAction = new Random();

    public Animal(double weight, int energy, String type, KindOfAnimal character) {
        this.weight = weight;
        this.energy = energy;
        this.type = type;
        this.character = character;
    }

    @Override
    public boolean produce() {
        var animalMap = this.character.equals(KindOfAnimal.HERBIVORES) ? herbivoresAnimalMap : carnivoresAnimalMap;
        List<Animal> animal = animalMap.get(this.getLocation()).stream()
                .filter(c -> c.getType().equals(this.getType()))
                .filter(c -> !c.isSaturation())
                .collect(Collectors.toList());
        if (animal.size() > 1) {
            if (randomAction.nextInt(100) > 90) {
                animal.get(0).isSaturation = true;
                return true;
            }
        }
        return false;
    }

    public boolean born() {
        if (isSaturation && pregnancyPeriod <= 0) {
            isSaturation = false;
            pregnancyPeriod = 3;
            return true;
        }
        if (isSaturation) {
            pregnancyPeriod--;
        }
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

    public boolean diedOfHunger() {
        if (hungryLevel < 0) {
            isDead = true;
            return true;
        }
        return false;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHungryLevel() {
        return hungryLevel;
    }

    public void setHungryLevel(int hungryLevel) {
        this.hungryLevel = hungryLevel;
    }

    public boolean isSaturation() {
        return isSaturation;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setSaturation(boolean saturation) {
        isSaturation = saturation;
    }

    public void setPregnancyPeriod(int pregnancyPeriod) {
        this.pregnancyPeriod = pregnancyPeriod;
    }

    public KindOfAnimal getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return character + "{" +
                "type='" + type + '\'' +
                ", weight=" + weight +
                ", speed=" + energy +
                ", isSaturation=" + isSaturation +
                ", location=" + location +
                '}' + "\n";
    }
}
