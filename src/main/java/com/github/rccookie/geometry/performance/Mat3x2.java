package com.github.rccookie.geometry.performance;

public class Mat3x2 extends Mat<Vec3, Vec2> {

    public Mat3x2() {
        super(Vec3.ZERO, Vec2.ZERO);
    }

    public Mat3x2(Vec3[] r) {
        super(Vec3.ZERO, Vec2.ZERO, r);
    }
}
