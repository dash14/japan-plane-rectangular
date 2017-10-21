package org.dash14.gis;

import junit.framework.TestCase;
import org.dash14.dimension.Point;

public class JapanPlaneRectangularTest extends TestCase {

    public void testGetOrigin() throws Exception {
        LatLon latlon = JapanPlaneRectangular.getOrigin(Zone._1);
        assertEquals(new LatLon(33.00000, 129.5000), latlon);

        latlon = JapanPlaneRectangular.getOrigin(Zone._9);
        assertEquals(new LatLon(36.00000, 139.833333333333334), latlon);

        latlon = JapanPlaneRectangular.getOrigin(Zone._19);
        assertEquals(new LatLon(26.00000, 154.000000), latlon);
    }

    public void testGetNearestOriginZone() throws Exception {
        Zone zone = JapanPlaneRectangular.getNearestOriginZone(new LatLon(33.190977, 129.917483));
        assertEquals(Zone._1, zone);

        zone = JapanPlaneRectangular.getNearestOriginZone(new LatLon(36.103774791666666, 140.08785504166664));
        assertEquals(Zone._9, zone);

        zone = JapanPlaneRectangular.getNearestOriginZone(new LatLon(26.160347222222224, 127.70422777777777));
        assertEquals(Zone._15, zone);
    }

    public void testToXY() throws Exception {
        // calculate at http://vldb.gsi.go.jp/sokuchi/surveycalc/surveycalc/bl2xyf.html
        Point point = JapanPlaneRectangular.toXY(new LatLon(33.190977, 129.917483), Zone._1);
        assertEquals(point.x, 21256.0449, 0.01);
        assertEquals(point.y, 38927.1043, 0.01);

        point = JapanPlaneRectangular.toXY(new LatLon(36.103774791666666, 140.08785504166664), Zone._9);
        assertEquals(point.x, 11543.6883, 0.01);
        assertEquals(point.y, 22916.2436, 0.01);

        point = JapanPlaneRectangular.toXY(new LatLon(26.160347222222224, 127.70422777777777), Zone._15);
        assertEquals(point.x, 17779.0104, 0.01);
        assertEquals(point.y, 20416.9652, 0.01);
// 変換対象の緯度・経度
        LatLon latlon = LatLon.of(36.103774791666666, 140.08785504166664);

// 近い系を取得
        Zone zone = JapanPlaneRectangular.getNearestOriginZone(latlon);
        System.out.println(zone.getNumber());

// XY値に変換
         point = JapanPlaneRectangular.toXY(latlon, zone);
        System.out.println(point);

    }
}