package org.dash14.gis.utils;

import org.dash14.dimension.utils.DimensionUtils;
import org.dash14.gis.LatLon;
import org.dash14.gis.JapanPlaneRectangular;
import org.dash14.dimension.Point;
import org.dash14.gis.Zone;

public class LocationUtils {
    public static double calculateDistance(LatLon origin, LatLon target, Zone zone) {
        Point originPoint = JapanPlaneRectangular.toXY(origin, zone);
        Point targetPoint = JapanPlaneRectangular.toXY(target, zone);
        return DimensionUtils.calculateDistance(
                originPoint.x, originPoint.y, targetPoint.x, targetPoint.y);
    }
}
