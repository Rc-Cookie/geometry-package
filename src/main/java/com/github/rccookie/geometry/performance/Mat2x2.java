package com.github.rccookie.geometry.performance;

public class Mat2x2 extends Mat<Vec2, Vec2> {

    public Mat2x2() {
        super(Vec2.ZERO, Vec2.ZERO);
    }

    public Mat2x2(Vec2[] r) {
        super(Vec2.ZERO, Vec2.ZERO, r);
    }
}
