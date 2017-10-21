package org.dash14.gis;

import junit.framework.TestCase;

public class LatLonTest extends TestCase {

    public void testGetLatitude() throws Exception {
        LatLon latlon = new LatLon(1.0, 2.0);
        assertEquals(1.0, latlon.latitude);
        assertEquals(1.0, latlon.getLatitude());
    }

    public void testGetLongitude() throws Exception {
        LatLon latlon = new LatLon(1.0, 2.0);
        assertEquals(2.0, latlon.longitude);
        assertEquals(2.0, latlon.getLongitude());
    }

    public void testHashCode() throws Exception {
        LatLon latlon1 = new LatLon(1.0, 2.0);
        LatLon latlon2 = new LatLon(2.0, 1.0);
        assertNotSame(latlon1.hashCode(), latlon2.hashCode());

        LatLon latlon3 = new LatLon(1.0, 2.0);
        assertEquals(latlon1.hashCode(), latlon3.hashCode());
    }

    public void testEquals() throws Exception {
        LatLon latlon1 = new LatLon(1.0, 2.0);
        LatLon latlon2 = new LatLon(2.0, 1.0);
        assertFalse(latlon1.equals(latlon2));
        assertFalse(latlon2.equals(latlon1));

        LatLon latlon3 = new LatLon(1.0, 2.0);
        assertTrue(latlon1.equals(latlon3));
        assertTrue(latlon3.equals(latlon1));
    }

    public void testOf() throws Exception {
        LatLon latlon = LatLon.of(1.0, 2.0);
        assertEquals(1.0, latlon.latitude);
        assertEquals(2.0, latlon.longitude);
    }
}