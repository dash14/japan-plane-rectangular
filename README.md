# japan-plane-rectangular

世界測地系の緯度・経度から、日本の平面直角座標系のXY値に変換するJavaのライブラリです。
逆変換(XY値から緯度・経度への変換)は対応していません。

## サンプルコード

```java
// 変換対象の緯度・経度
LatLon latlon = LatLon.of(36.103774791666666, 140.08785504166664);

// 近い系を取得
Zone zone = JapanPlaneRectangular.getNearestOriginZone(latlon);
System.out.println(zone.getNumber()); // 9

// XY値に変換
Point point = JapanPlaneRectangular.toXY(latlon, zone);
System.out.println(point); // Point[x=11543.688321485752,y=22916.2435543211]
```

## 参考

* http://vldb.gsi.go.jp/sokuchi/surveycalc/surveycalc/bl2xyf.html
* https://github.com/KMR-zoar/cblxy

## ライセンス
Apache-2.0
