package org.dash14.dimension;

import junit.framework.TestCase;

public class PointTest extends TestCase {

    public void testGetX() throws Exception {
        Point point = new Point(1.0, 2.0);
        assertEquals(1.0, point.x);
        assertEquals(1.0, point.getX());
    }

    public void testGetY() throws Exception {
        Point point = new Point(1.0, 2.0);
        assertEquals(2.0, point.y);
        assertEquals(2.0, point.getY());
    }

    public void testHashCode() throws Exception {
        Point point1 = new Point(1.0, 2.0);
        Point point2 = new Point(2.0, 1.0);
        assertNotSame(point1.hashCode(), point2.hashCode());

        Point point3 = new Point(1.0, 2.0);
        assertEquals(point1.hashCode(), point3.hashCode());
    }

    public void testEquals() throws Exception {
        Point point1 = new Point(1.0, 2.0);
        Point point2 = new Point(2.0, 1.0);
        assertFalse(point1.equals(point2));
        assertFalse(point2.equals(point1));

        Point point3 = new Point(1.0, 2.0);
        assertTrue(point1.equals(point3));
        assertTrue(point3.equals(point1));
    }

    public void testOf() throws Exception {
        Point p = Point.of(1.0, 2.0);
        assertEquals(1.0, p.x);
        assertEquals(2.0, p.y);
    }
}