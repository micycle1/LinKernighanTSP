package com.example.tsp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    @Test
    void testGetters() {
        Edge e = new Edge(1, 2);
        assertEquals(2, e.get1()); // Greater value
        assertEquals(1, e.get2()); // Smaller value

        Edge eReverse = new Edge(2, 1);
        assertEquals(2, eReverse.get1());
        assertEquals(1, eReverse.get2());
    }

    @Test
    void testEquals() {
        Edge e1 = new Edge(1, 2);
        Edge e2 = new Edge(2, 1);
        Edge e3 = new Edge(1, 3);
        Edge e4 = new Edge(1, 2);

        assertTrue(e1.equals(e2));
        assertTrue(e2.equals(e1));
        assertTrue(e1.equals(e4));
        assertFalse(e1.equals(e3));
        assertFalse(e1.equals(null));
    }

    @Test
    void testCompareTo() {
        Edge e1 = new Edge(1, 2); // internally (2,1)
        Edge e2 = new Edge(2, 1); // internally (2,1)
        Edge e3 = new Edge(1, 3); // internally (3,1)
        Edge e4 = new Edge(3, 2); // internally (3,2)

        assertEquals(0, e1.compareTo(e2));
        assertTrue(e1.compareTo(e3) < 0); // (2,1) vs (3,1)
        assertTrue(e3.compareTo(e1) > 0); // (3,1) vs (2,1)
        assertTrue(e3.compareTo(e4) < 0); // (3,1) vs (3,2)
    }
}
