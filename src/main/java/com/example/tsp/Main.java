package com.example.tsp;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting TSP solver with hardcoded data...");

        ArrayList<Point> coordinates = new ArrayList<>();
        // Example: A simple square
        coordinates.add(new Point(0, 0));
        coordinates.add(new Point(100, 0));
        coordinates.add(new Point(100, 100));
        coordinates.add(new Point(0, 100));

        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i++) {
            ids.add(i); // Simple 0-based IDs
        }

        LinKernighan lk = new LinKernighan(coordinates, ids);

        long startTime = System.currentTimeMillis();
        lk.runAlgorithm();
        long endTime = System.currentTimeMillis();

        System.out.printf("The solution took: %dms%n", (endTime - startTime));
        System.out.println("The solution is: ");
        System.out.println(lk); // Relies on LinKernighan's toString() method
    }
}
