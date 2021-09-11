package com.github.rccookie.geometry.performance;

public class Mat2x3 extends Mat<Vec2, Vec3> {

    public Mat2x3() {
        super(Vec2.ZERO, Vec3.ZERO);
    }

    public Mat2x3(Vec2[] r) {
        super(Vec2.ZERO, Vec3.ZERO, r);
    }
}
