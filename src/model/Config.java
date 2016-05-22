package model;

import sim.util.Int2D;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JeanV on 21/05/2016.
 */
public class Config {
    public static int GRID_SIZE_H = 100;
    public static int GRID_SIZE_W = 100;

    public static List<Int2D> liveCellsAtStart = new ArrayList<>();

    public static void load() {
        loadRandom();
    }

    private static void loadFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader("res/config"))) {
            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                String[] split = line.split(" ");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);

                if (x >= 0 && x <= GRID_SIZE_W && y >= 0 && y <= GRID_SIZE_H)
                    liveCellsAtStart.add(new Int2D(x, y));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadRandom() {
        // generate random pattern between
        Random r = new Random();
        for (int i = 0; i < 1000; i++)
            liveCellsAtStart.add(new Int2D(r.nextInt(50) + 25, r.nextInt(50) + 25));
    }
}
