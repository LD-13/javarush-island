package com.javarush.island.Island;

import com.javarush.island.Animal.Carnivores.Carnivores;
import com.javarush.island.Animal.Herbivores.Herbivores;
import com.javarush.island.Plant.Herb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Island {
    private static int xField = 20;
    private static int yField = 5;
    public static Cell[][] Field = new Cell[xField][yField];

    public static Map<Cell, ArrayList<Herbivores>> herbivoresAnimalMap = new HashMap<>();
    public static Map<Cell, ArrayList<Carnivores>> carnivoresAnimalMap = new HashMap<>();
   // public static Map<Cell, ArrayList<Herb>> herbMap = new HashMap<>();
    public static Map<Cell, ArrayList<Herb>> herbMap = new ConcurrentHashMap<>();

    public void initField() {
        for (int i = 0; i < Field.length; i++) {
            for (int j = 0; j < Field[i].length; j++) {
                Field[i][j] = new Cell(i, j);
                herbivoresAnimalMap.put(Field[i][j], new ArrayList<>());
                carnivoresAnimalMap.put(Field[i][j], new ArrayList<>());
                herbMap.put(Field[i][j], new ArrayList<>());
            }
        }
    }

//    public void show() {
//        Cell cell;
//        for (int i = 0; i < Field.length; i++) {
//            for (int j = 0; j < Field[i].length; j++) {
//                cell = Field[i][j];
//                System.out.print("| " + cell.x + " " + cell.y + " |");
//            }
//            System.out.println();
//        }
//    }
}
