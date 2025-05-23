package com.example.tsp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class LinKernighanTest {

    private LinKernighan createSquareInstance() {
        ArrayList<Point> coordinates = new ArrayList<>();
        coordinates.add(new Point(0, 0));    // City 0
        coordinates.add(new Point(10, 0));   // City 1
        coordinates.add(new Point(10, 10));  // City 2
        coordinates.add(new Point(0, 10));   // City 3

        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i++) {
            ids.add(i);
        }
        return new LinKernighan(coordinates, ids);
    }

    @Test
    void testGetDistanceSquare() {
        LinKernighan lk = createSquareInstance();
        // Test with a known tour, e.g., 0-1-2-3-0
        lk.tour = new int[]{0, 1, 2, 3};
        double distance = lk.getDistance();
        // Expected: 10 (0-1) + 10 (1-2) + 10 (2-3) + 10 (3-0) = 40
        assertEquals(40.0, distance, 0.001);
    }
    
    @Test
    void testGetDistanceTriangle() {
        ArrayList<Point> coordinates = new ArrayList<>();
        coordinates.add(new Point(0, 0));    // City 0
        coordinates.add(new Point(3, 0));   // City 1
        coordinates.add(new Point(0, 4));  // City 2

        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i++) {
            ids.add(i);
        }
        LinKernighan lk = new LinKernighan(coordinates, ids);
        lk.tour = new int[]{0,1,2}; // Order 0-1-2-0
        // Expected: Dist(0,1)=3, Dist(1,2)=5 (3-4-5 triangle), Dist(2,0)=4. Total = 3+5+4 = 12
        assertEquals(12.0, lk.getDistance(), 0.001);
    }


    @Test
    void testRunAlgorithmSimpleSquare() {
        LinKernighan lk = createSquareInstance();
        
        // Initial distance can be anything due to random tour
        // System.out.println("Initial tour: " + Arrays.toString(lk.tour) + " distance: " + lk.getDistance());

        lk.runAlgorithm();
        double finalDistance = lk.getDistance();
        
        // System.out.println("Final tour: " + Arrays.toString(lk.tour) + " distance: " + finalDistance);

        // For a 4-city square, the optimal distance is 40.
        // The algorithm should find this or something very close for such a simple case.
        assertEquals(40.0, finalDistance, 0.001, "Algorithm should find the optimal tour for a simple square.");

        // Verify tour validity
        assertEquals(4, lk.tour.length, "Tour should have 4 cities.");
        HashSet<Integer> citySet = new HashSet<>();
        for (int cityId : lk.tour) {
            citySet.add(cityId);
        }
        assertEquals(4, citySet.size(), "Tour should contain all unique cities.");
        for(int i=0; i<4; ++i) {
            assertTrue(citySet.contains(i), "City " + i + " should be in the tour.");
        }
    }

    @Test
    void testIsTour() {
        LinKernighan lk = createSquareInstance(); // Size is 4
        assertTrue(lk.isTour(new int[]{0,1,2,3}));
        assertTrue(lk.isTour(new int[]{3,2,1,0}));
        assertFalse(lk.isTour(new int[]{0,1,2,2}), "Duplicate city");
        assertFalse(lk.isTour(new int[]{0,1,2}), "Too short");
        assertFalse(lk.isTour(new int[]{0,1,2,3,0}), "Too long");
    }
}
