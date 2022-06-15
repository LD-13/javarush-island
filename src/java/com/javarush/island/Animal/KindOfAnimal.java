package com.javarush.island.Animal;

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
