package org.dash14.dimension.utils;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.dash14.dimension.Point;

public class DimensionUtils {

    public static double calculateDistance(Point origin, Point target) {
        return calculateDistance(origin.x, origin.y, target.x, target.y);
    }

    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0));
    }

    public static Point calclateRelativeCoord(Point origin, Point target) {
        return new Point(target.x - origin.x, target.y - origin.y);
    }

    /**
     * 直線 l1-l2 に対して点 p から下ろした垂線の交点と長さを求める
     * @param l1 直線の点1
     * @param l2 直線の点2
     * @param p  直線に対して垂線を下ろす始点
     * @return 直線との交点および垂線の長さ (垂線が引けない場合はnull)
     */
    public static ImmutablePair<Point, Double> calculatePerpendicular(Point l1, Point l2, Point p) {
        double x, y;

        if (l1.x == l2.x) {
            x = l1.x;
            y = p.y;
        } else if (l1.y == l2.y) {
            x = p.x;
            y = l1.y;
        } else {
            double a = (l1.y - l2.y) / (l1.x - l2.x);
            double b = l1.y - a * l1.x;
            x = (a * (p.y - b) + p.x) / (Math.pow(a, 2.0) + 1.0);
            y = a * x + b;
        }

        // 交点が直線の内部かどうかを判別する
        if (((l1.x <= x && x <= l2.x) || (l2.x <= x && x <= l1.x))
                && ((l1.y <= y && y <= l2.y) || (l2.y <= y && y <= l1.y))) {
            Point intersection = new Point(x, y);
            double distance = calculateDistance(p, intersection);
            return new ImmutablePair<>(intersection, distance);
        }
        return null;
    }
}
