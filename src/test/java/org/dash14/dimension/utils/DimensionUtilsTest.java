package org.dash14.dimension.utils;

import junit.framework.TestCase;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.dash14.dimension.Point;

public class DimensionUtilsTest extends TestCase {

    public void testCalculateDistance() throws Exception {
        double distance = DimensionUtils.calculateDistance(Point.of(1.0, 1.0), Point.of(2.0, 1.0));
        assertEquals(1.0, distance);

        distance = DimensionUtils.calculateDistance(Point.of(1.0, 1.0), Point.of(1.0, 2.0));
        assertEquals(1.0, distance);

        distance = DimensionUtils.calculateDistance(Point.of(0.0, 0.0), Point.of(3.0, 4.0));
        assertEquals(5.0, distance);

        distance = DimensionUtils.calculateDistance(Point.of(3.0, 4.0), Point.of(0.0, 0.0));
        assertEquals(5.0, distance);

        distance = DimensionUtils.calculateDistance(Point.of(1.0, 1.0), Point.of(4.0, 5.0));
        assertEquals(5.0, distance);
    }

    public void testCalclateRelativeCoord() throws Exception {
        Point relatives = DimensionUtils.calclateRelativeCoord(Point.of(1.0, 0.0), Point.of(0.0, 0.0));
        assertEquals(Point.of(-1.0, 0.0), relatives);

        relatives = DimensionUtils.calclateRelativeCoord(Point.of(0.0, 5.0), Point.of(1.0, 2.0));
        assertEquals(Point.of(1.0, -3.0), relatives);
    }

    public void testCalculatePerpendicular() throws Exception {
        // 典型的な構成
        ImmutablePair pair = DimensionUtils.calculatePerpendicular(Point.of(1.0, 3.0), Point.of(3.0, 1.0), Point.of(1.0, 1.0));
        assertEquals(Point.of(2.0, 2.0), pair.getLeft());
        assertEquals(Math.sqrt(2.0), pair.getRight());

        pair = DimensionUtils.calculatePerpendicular(Point.of(1.0, 3.0), Point.of(3.0, 1.0), Point.of(3.0, 3.0));
        assertEquals(Point.of(2.0, 2.0), pair.getLeft());
        assertEquals(Math.sqrt(2.0), pair.getRight());

        // 水平線
        pair = DimensionUtils.calculatePerpendicular(Point.of(1.0, 1.0), Point.of(3.0, 1.0), Point.of(2.0, 0.0));
        assertEquals(Point.of(2.0, 1.0), pair.getLeft());
        assertEquals(1.0, pair.getRight());

        pair = DimensionUtils.calculatePerpendicular(Point.of(1.0, 1.0), Point.of(3.0, 1.0), Point.of(2.0, 2.0));
        assertEquals(Point.of(2.0, 1.0), pair.getLeft());
        assertEquals(1.0, pair.getRight());

        // 垂直線
        pair = DimensionUtils.calculatePerpendicular(Point.of(2.0, 3.0), Point.of(2.0, 1.0), Point.of(1.0, 2.0));
        assertEquals(Point.of(2.0, 2.0), pair.getLeft());
        assertEquals(1.0, pair.getRight());

        pair = DimensionUtils.calculatePerpendicular(Point.of(2.0, 3.0), Point.of(2.0, 1.0), Point.of(3.0, 2.0));
        assertEquals(Point.of(2.0, 2.0), pair.getLeft());
        assertEquals(1.0, pair.getRight());

        // 垂線の長さが0 (直線上の点)
        pair = DimensionUtils.calculatePerpendicular(Point.of(1.0, 3.0), Point.of(3.0, 1.0), Point.of(2.0, 2.0));
        assertEquals(Point.of(2.0, 2.0), pair.getLeft());
        assertEquals(0.0, pair.getRight());

        // 線の外側
        pair = DimensionUtils.calculatePerpendicular(Point.of(1.0, 3.0), Point.of(3.0, 1.0), Point.of(3.0, -1.0));
        assertNull(pair);

        pair = DimensionUtils.calculatePerpendicular(Point.of(1.0, 3.0), Point.of(3.0, 1.0), Point.of(1.0, 4.0));
        assertNull(pair);

    }
}