package com.javarush.island.island;

import com.javarush.island.animal.carnivores.Carnivores;
import com.javarush.island.animal.herbivores.Herbivores;
import com.javarush.island.plant.Herb;
import com.javarush.island.constant.Constants;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Island {
    private static int xField = Constants.XField;
    private static int yField = Constants.YField;
    public static Cell[][] Field = new Cell[xField][yField];

    public static ConcurrentMap<Cell, ArrayList<Herbivores>> herbivoresAnimalMap = new ConcurrentHashMap<>();
    public static ConcurrentMap<Cell, ArrayList<Carnivores>> carnivoresAnimalMap = new ConcurrentHashMap<>();
    public static ConcurrentMap<Cell, ArrayList<Herb>> herbMap = new ConcurrentHashMap<>();

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
}
