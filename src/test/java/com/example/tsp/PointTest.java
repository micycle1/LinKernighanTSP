package com.example.tsp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    void testGetters() {
        Point p = new Point(10.5, 20.3);
        assertEquals(10.5, p.getX(), 0.001);
        assertEquals(20.3, p.getY(), 0.001);
    }
}
