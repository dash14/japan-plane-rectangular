package org.dash14.gis;

import org.dash14.dimension.Point;
import org.dash14.gis.utils.LocationUtils;

/**
 * Japan Plane Rectangular 平面直角座標系
 * http://www.gsi.go.jp/LAW/heimencho.html
 *
 * 式の参考: https://github.com/KMR-zoar/cblxy
 */
public class JapanPlaneRectangular {

	/** GRS80楕円体 長半径 */
	private static final double GRS80_a = 6378137.0;

	/** GRS80楕円体 逆扁平率 */
	private static final double GRS80_F = 298.257222101;

	/** 原点における縮尺係数 */
	private static final double m0 = 0.9999;

	/** 離心率 (eccentricity) */
	static final double e = Math.sqrt(2.0 * GRS80_F - 1.0) / GRS80_F;

	/** 原点の座標 */
	private static final LatLon[] origins = {
			new LatLon(33.00000, 129.5000),
			new LatLon(33.00000, 131.00000),
			new LatLon(36.00000, 132.166666666666669),
			new LatLon(33.00000, 133.50000),
			new LatLon(36.00000, 134.333333333333334),
			new LatLon(36.00000, 136.00000),
			new LatLon(36.00000, 137.166666666666666),
			new LatLon(36.00000, 138.5),
			new LatLon(36.00000, 139.833333333333334),
			new LatLon(40.00000, 140.833333333333334),
			new LatLon(44.00000, 140.25000),
			new LatLon(44.00000, 142.25000),
			new LatLon(44.00000, 144.25000),
			new LatLon(26.00000, 142.00000),
			new LatLon(26.00000, 127.50000),
			new LatLon(26.00000, 124.00000),
			new LatLon(26.00000, 131.00000),
			new LatLon(20.00000, 136.00000),
			new LatLon(26.00000, 154.000000) };

	/**
	 * 平面直角座標系の原点座標を取得する
	 * @param zone 座標系
	 * @return 座標
	 */
	public static final LatLon getOrigin(Zone zone) {
		return origins[zone.getNumber() - 1];
	}

	/**
	 * 指定した位置に近い平面直角座標系を取得する
	 * @param latlon 位置座標
	 * @return 指定した位置座標に近い座標系
	 */
	public static Zone getNearestOriginZone(LatLon latlon) {
		int minIndex = 0;
		double minVal = Double.MAX_VALUE;
		for (int i = 0; i < origins.length; i++) {
			double dist = LocationUtils.calculateDistance(origins[i], latlon, Zone._7);
			if (dist < minVal) {
				minIndex = i;
				minVal = dist;
			}
		}
		Zone z = Zone.getByIndex(minIndex);
		return z;
	}

	/**
	 * 緯度・経度から指定した平面直角座標系におけるXY値を取得する
	 * @param latlon 緯度・経度
	 * @param zone 平面直角座標系
	 * @return XY値
	 */
	public static Point toXY(LatLon latlon, Zone zone) {
		LatLon origin = getOrigin(zone);
		return toXY(latlon.getLatitude(), latlon.getLongitude(),
				origin.getLatitude(), origin.getLongitude());
	}

	private static Point toXY(double latitudeDegree, double longitudeDegree,
						   double originLatitudeDegree, double originLongitudeDegree) {
		double phi0 = toRadian(originLatitudeDegree);
		double lambda0 = toRadian(originLongitudeDegree);

		double phi1 = toRadian(latitudeDegree);
		double lambda1 = toRadian(longitudeDegree);

		double s0 = calculateMeridianArcLength(phi0);
		double s1 = calculateMeridianArcLength(phi1);

		double ut = GRS80_a / Math.sqrt(1.0 - Math.pow(e, 2.0) * Math.pow(Math.sin(phi1), 2.0));
		double conp = Math.cos(phi1);
		double t1 = Math.tan(phi1);
		double dlambda = lambda1 - lambda0;
		double eta2 = (Math.pow(e, 2.0) / (1.0 - Math.pow(e, 2.0))) * Math.pow(conp, 2.0);

		// X座標値の算出
		double v1 = 5.0 - Math.pow(t1, 2.0) + 9.0 * eta2 + 4.0 * Math.pow(eta2, 2.0);
		double v2 = -61.0 + 58.0 * Math.pow(t1, 2.0) - Math.pow(t1, 4.0) - 270.0 * eta2 + 330.0 * Math.pow(t1, 2.0) * eta2;
		double v3 = -1385.0 + 3111.0 * Math.pow(t1, 2.0) - 543.0 * Math.pow(t1, 4.0) + Math.pow(t1, 6.0);

		double x = ((s1 - s0)
				+ ut * Math.pow(conp, 2.0) * t1 * Math.pow(dlambda, 2.0) / 2.0
				+ ut * Math.pow(conp, 4.0) * t1 * v1 * Math.pow(dlambda, 4.0) / 24.0
				- ut * Math.pow(conp, 6.0) * t1 * v2 * Math.pow(dlambda, 6.0) / 720.0
				- ut * Math.pow(conp, 8.0) * t1 * v3 * Math.pow(dlambda, 8.0) / 40320.0) * m0;

		// Y座標値の算出
		v1 = -1.0 + Math.pow(t1, 2.0) - eta2;
		v2 = -5.0 + 18.0 * Math.pow(t1, 2.0) - Math.pow(t1, 4.0) - 14.0 * eta2 + 58.0 * Math.pow(t1, 2.0) * eta2;
		v3 = -61.0 + 479.0 * Math.pow(t1, 2.0) - 179.0 * Math.pow(t1, 4.0) + Math.pow(t1, 6.0);

		double y = (ut * conp * dlambda
				- ut * Math.pow(conp, 3.0) * v1 * Math.pow(dlambda, 3.0) / 6.0
				- ut * Math.pow(conp, 5.0) * v2 * Math.pow(dlambda, 5.0) / 120.0
				- ut * Math.pow(conp, 7.0) * v3 * Math.pow(dlambda, 7.0) / 5040.0) * m0;

		return new Point(x, y);
	}

	// 緯度から赤道への子午線弧長を計算
	private static double calculateMeridianArcLength(double latitudeRadian) {
		double e0 = JapanPlaneRectangular.e;
		double e2 = Math.pow(e0, 2.0);
		double e4 = Math.pow(e0, 4.0);
		double e6 = Math.pow(e0, 6.0);
		double e8 = Math.pow(e0, 8.0);
		double e10 = Math.pow(e0, 10.0);
		double e12 = Math.pow(e0, 12.0);
		double e14 = Math.pow(e0, 14.0);
		double e16 = Math.pow(e0, 16.0);

		double a = 1.0 + 3.0 / 4.0 * e2 + 45.0 / 64.0 * e4 + 175.0 / 256.0 * e6 + 11025.0 / 16384.0 * e8 + 43659.0 / 65536.0 * e10 + 693693.0 / 1048576.0 * e12 + 19324305.0 / 29360128.0 * e14 + 4927697775.0 / 7516192768.0 * e16;
		double b = 3.0 / 4.0 * e2 + 15.0 / 16.0 * e4 + 525.0 / 512.0 * e6 + 2205.0 / 2048.0 * e8 + 72765.0 / 65536.0 * e10 + 297297.0 / 262144.0 * e12 + 135270135.0 / 117440512.0 * e14 + 547521975.0 / 469762048.0 * e16;
		double c = 15.0 / 64.0 * e4 + 105.0 / 256.0 * e6 + 2205.0 / 4096.0 * e8 + 10395.0 / 16384.0 * e10 + 1486485.0 / 2097152.0 * e12 + 45090045.0 / 58720256.0 * e14 + 766530765.0 / 939524096.0 * e16;
		double d = 35.0 / 512.0 * e6 + 315.0 / 2048.0 * e8 + 31185.0 / 131072.0 * e10 + 165165.0 / 524288.0 * e12 + 45090045.0 / 117440512.0 * e14 + 209053845.0 / 469762048.0 * e16;
		double e = 315.0 / 16384.0 * e8 + 3465.0 / 65536.0 * e10 + 99099.0 / 1048576.0 * e12 + 4099095.0 / 29360128.0 * e14 + 348423075.0 / 1879048192.0 * e16;
		double f = 693.0 / 131072 * e10 + 9009.0 / 524288.0 * e12 + 4099095.0 / 117440512.0 * e14 + 26801775.0 / 469762048.0 * e16;
		double g = 3003 / 2097152.0 * e12 + 315315.0 / 58720256.0 * e14 + 11486475.0 / 939524096.0 * e16;
		double h = 45045.0 / 117440512.0 * e14 + 765765.0 / 469762048.0 * e16;
		double i = 765765.0 / 7516192768.0 * e16;

		double meridian = GRS80_a * (1.0 - e2) * (a * latitudeRadian
				- b * Math.sin(latitudeRadian * 2.0) / 2.0
				+ c * Math.sin(latitudeRadian * 4.0) / 4.0
				- d * Math.sin(latitudeRadian * 6.0) / 6.0
				+ e * Math.sin(latitudeRadian * 8.0) / 8.0
				- f * Math.sin(latitudeRadian * 10.0) / 10.0
				+ g * Math.sin(latitudeRadian * 12.0) / 12.0
				- h * Math.sin(latitudeRadian * 14.0) / 14.0
				+ i * Math.sin(latitudeRadian * 16.0) / 16.0);
		return meridian;
	}

	// 十進法度表記(ddd.dddd)を経緯度（ラジアン）に変換する
	private static double toRadian(double degree) {
		return degree * Math.PI / 180.0;
	}
}
