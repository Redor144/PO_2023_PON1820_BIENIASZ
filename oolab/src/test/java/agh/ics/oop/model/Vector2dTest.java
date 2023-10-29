package agh.ics.oop.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class Vector2dTest {
    @Test
    void testToString() {
        Vector2d v = new Vector2d(1,1);
        assertEquals("(1,1)", v.toString());
    }

    @Test
    void precedes() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Vector2d v3 = new Vector2d(0, 1);

        assertTrue(v3.precedes(v2));
        assertFalse(v1.precedes(v3));
    }

    @Test
    void follows() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Vector2d v3 = new Vector2d(0, 1);

        assertTrue(v1.follows(v3));
        assertFalse(v3.follows(v2));
    }

    @Test
    void add() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,3);
        Vector2d v3 = new Vector2d(3,5);

        assertEquals(v3, v1.add(v2));
    }

    @Test
    void subtract() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,3);
        Vector2d v3 = new Vector2d(-1,-1);

        assertEquals(v3, v1.subtract(v2));
    }

    @Test
    void upperRight() {
        Vector2d v1 = new Vector2d(10,10);
        Vector2d v2 = new Vector2d(0,0);
        Vector2d v3 = new Vector2d(5,5);

        assertEquals(v1, v1.upperRight(v2));
        assertEquals(v3, v2.upperRight(v3));

    }

    @Test
    void lowerLeft() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Vector2d v3 = new Vector2d(0, 1);

        assertEquals(v1, v1.lowerLeft(v2));
        assertEquals(v3, v1.lowerLeft(v3));
    }

    @Test
    void opposite() {
        Vector2d v1 = new Vector2d(1,1);

        assertEquals(v1.opposite(), new Vector2d(-1,-1));
    }

    @Test
    void equals() {
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(1,1);
        Vector2d v3 = new Vector2d(0,0);

        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
    }
}
