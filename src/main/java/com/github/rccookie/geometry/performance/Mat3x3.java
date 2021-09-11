package com.github.rccookie.geometry.performance;

public class Mat3x3 extends Mat<Vec3, Vec3> {

    public Mat3x3() {
        super(Vec3.ZERO, Vec3.ZERO);
    }

    public Mat3x3(Vec3[] r) {
        super(Vec3.ZERO, Vec3.ZERO, r);
    }
}
