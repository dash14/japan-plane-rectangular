package org.dash14.gis.utils;

import junit.framework.TestCase;
import org.dash14.gis.JapanPlaneRectangular;
import org.dash14.gis.LatLon;
import org.dash14.gis.Zone;

public class LocationUtilsTest extends TestCase {
    public void testCalculateDistance() throws Exception {
        // 50m
        LatLon origin = LatLon.of(35.714313, 139.490869);
        Zone zone = JapanPlaneRectangular.getNearestOriginZone(origin);
        double distance = LocationUtils.calculateDistance(origin,
                LatLon.of(35.714201, 139.491405), zone);

        assertEquals(50.068, distance, 0.1);

        // 1km
        origin = LatLon.of(35.711118, 139.507445);
        zone = JapanPlaneRectangular.getNearestOriginZone(origin);
        distance = LocationUtils.calculateDistance(origin,
                LatLon.of(35.711132, 139.496366), zone);

        assertEquals(1002.555, distance, 0.1);
    }
}