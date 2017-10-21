package org.dash14.gis;

import junit.framework.TestCase;

public class ZoneTest extends TestCase {

    public void testGetNumber() throws Exception {
        assertEquals(1, Zone._1.getNumber());
        assertEquals(2, Zone._2.getNumber());
        assertEquals(3, Zone._3.getNumber());
        assertEquals(4, Zone._4.getNumber());
        assertEquals(5, Zone._5.getNumber());
        assertEquals(6, Zone._6.getNumber());
        assertEquals(7, Zone._7.getNumber());
        assertEquals(8, Zone._8.getNumber());
        assertEquals(9, Zone._9.getNumber());
        assertEquals(10, Zone._10.getNumber());
        assertEquals(11, Zone._11.getNumber());
        assertEquals(12, Zone._12.getNumber());
        assertEquals(13, Zone._13.getNumber());
        assertEquals(14, Zone._14.getNumber());
        assertEquals(15, Zone._15.getNumber());
        assertEquals(16, Zone._16.getNumber());
        assertEquals(17, Zone._17.getNumber());
        assertEquals(18, Zone._18.getNumber());
        assertEquals(19, Zone._19.getNumber());
    }

    public void testGet() throws Exception {
        assertEquals(Zone._1, Zone.getByIndex(0));
        assertEquals(Zone._2, Zone.getByIndex(1));
        assertEquals(Zone._3, Zone.getByIndex(2));
        assertEquals(Zone._4, Zone.getByIndex(3));
        assertEquals(Zone._5, Zone.getByIndex(4));
        assertEquals(Zone._6, Zone.getByIndex(5));
        assertEquals(Zone._7, Zone.getByIndex(6));
        assertEquals(Zone._8, Zone.getByIndex(7));
        assertEquals(Zone._9, Zone.getByIndex(8));
        assertEquals(Zone._10, Zone.getByIndex(9));
        assertEquals(Zone._11, Zone.getByIndex(10));
        assertEquals(Zone._12, Zone.getByIndex(11));
        assertEquals(Zone._13, Zone.getByIndex(12));
        assertEquals(Zone._14, Zone.getByIndex(13));
        assertEquals(Zone._15, Zone.getByIndex(14));
        assertEquals(Zone._16, Zone.getByIndex(15));
        assertEquals(Zone._17, Zone.getByIndex(16));
        assertEquals(Zone._18, Zone.getByIndex(17));
        assertEquals(Zone._19, Zone.getByIndex(18));
    }
}