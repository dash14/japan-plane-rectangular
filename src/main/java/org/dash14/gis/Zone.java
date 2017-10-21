package org.dash14.gis;

public enum Zone {
    _1(1), _2(2), _3(3), _4(4), _5(5),
    _6(6), _7(7), _8(8), _9(9), _10(10),
    _11(11), _12(12), _13(13), _14(14), _15(15),
    _16(16), _17(17), _18(18), _19(19);

    private int number;

    Zone(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public static Zone getByIndex(int index) {
        return values()[index];
    }
}
