package com.javarush.island.animal;

public enum KindOfAnimal {
    HERBIVORES {
        public String toString() {
            return "Herbivores";
        }
    },
    CARNIVORES {
        public String toString() {
            return "Carnivores";
        }
    }
}
