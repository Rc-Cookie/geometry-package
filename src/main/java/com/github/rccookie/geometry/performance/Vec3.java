package com.github.rccookie.geometry.performance;

public class Vec3 implements Vec<Vec3> {

    public static final Vec3 ZERO = new Vec3();
    public static final Vec3 ONE = new Vec3(1, 1, 1);
    public static final Vec3 X = new Vec3(1, 0, 0);
    public static final Vec3 Y = new Vec3(0, 1, 0);
    public static final Vec3 Z = new Vec3(0, 0, 1);

    // ------------------------------------------------------

    public double x;
    public double y;
    public double z;

    // ------------------------------------------------------

    public Vec3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(double[] a) {
        x = a[0];
        y = a[1];
        z = a[2];
    }

    // ------------------------------------------------------

    @Override
    public Vec3 clone() {
        return new Vec3(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Vec3 v = (Vec3) o;
        return x == v.x && y == v.y && z == v.z;
    }

    @Override
    public int hashCode() {
        return 31 * (31 * Double.hashCode(x) + Double.hashCode(y)) + Double.hashCode(z);
    }

    @Override
    public String toString() {
        return "["+x+"|"+y+"|"+z+"]";
    }

    // ------------------------------------------------------


    @Override
    public int size() {
        return 3;
    }

    @Override
    public double getDim(int d) {
        if(d == 0) return x;
        if(d == 1) return y;
        if(d == 2) return z;
        throw new IllegalArgumentException(d + "");
    }

    @Override
    public double abs() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double sqrAbs() {
        return x * x + y * y + z * z;
    }

    @Override
    public double angle() {
        return FastMath.acos(x / (Math.sqrt(x * x + y * y + z * z)));
    }

    @Override
    public double angle(Vec3 v) {
        return FastMath.acos(
                (x * v.x + y * v.y + z * v.z) /
                (Math.sqrt(x * x + y * y + z * z) *
                Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z))
        );
    }

    @Override
    public double dot(Vec3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    @Override
    public boolean isZero() {
        return (x == y) == (y == z) && x == 0;
    }

    @Override
    public double[] toArray() {
        return new double[] { x, y, z };
    }

    // ------------------------------------------------------


    @Override
    public Vec3 set(double v) {
        x = v;
        y = v;
        z = v;
        return this;
    }

    public Vec3 set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    @Override
    public Vec3 set(Vec3 v) {
        x = v.x;
        y = v.y;
        z = v.z;
        return this;
    }

    @Override
    public Vec3 set(double[] a) {
        x = a[0];
        y = a[1];
        z = a[2];
        return this;
    }

    @Override
    public Vec3 setDim(int d, double v) {
        if(d == 0) x = v;
        else if(d == 1) y = v;
        else if(d == 2) z = v;
        else throw new IllegalArgumentException(d + "");
        return this;
    }

    @Override
    public Vec3 setZero() {
        x = 0;
        y = 0;
        z = 0;
        return this;
    }

    // ------------------------------------------------------

    @Override
    public Vec3 scale(double f) {
        x *= f;
        y *= f;
        z *= f;
        return this;
    }

    @Override
    public Vec3 divide(double d) {
        double factor = 1 / d;
        x *= factor;
        y *= factor;
        z *= factor;
        return this;
    }

    @Override
    public Vec3 invert() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    @Override
    public Vec3 norm() {
        double factor = 1 / Math.sqrt(x * x + y * y + z * z);
        x *= factor;
        y *= factor;
        z *= factor;
        return this;
    }

    // ------------------------------------------------------

    @Override
    public Vec3 add(Vec3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    @Override
    public Vec3 subtract(Vec3 v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    @Override
    public Vec3 multiply(Vec3 v) {
        x *= v.x;
        y *= v.y;
        z -= v.z;
        return this;
    }

    @Override
    public Vec3 lerp(Vec3 t, double a) {
        double ia = 1 - a;
        x = x * ia + t.x * a;
        y = y * ia + t.y * a;
        z = z * ia + t.z * a;
        return this;
    }

    @Override
    public Vec3 project(Vec3 o) {
        double f = (x * o.x + y * o.y + z * o.z) / (o.x * o.x + o.y * o.y + o.z * o.z);
        x = o.y * f;
        y = o.y * f;
        z = o.z * f;
        return this;
    }

    @Override
    public Vec3 apply(Vec3[] m) {
        double oldX = x, oldY = y;
        x = x * m[0].x + y * m[0].y + z * m[0].z;
        y = oldX * m[1].x + y * m[1].y + z * m[1].z;
        z = oldX * m[2].x + oldY * m[2].y + z * m[2].z;
        return this;
    }

    @Override
    public Vec3 apply(Mat<Vec3, Vec3> m) {
        double oldX = x, oldY = y;
        x = x * m.r[0].x + y * m.r[0].y + z * m.r[0].z;
        y = oldX * m.r[1].x + y * m.r[1].y + z * m.r[1].z;
        z = oldX * m.r[2].x + oldY * m.r[2].y + z * m.r[2].z;
        return this;
    }

    // ------------------------------------------------------

    @Override
    public Vec3 scaled(double f) {
        return new Vec3(x * f, y * f, z * f);
    }

    @Override
    public Vec3 divided(double d) {
        double f = 1 / d;
        return new Vec3(x * f, y * f, z * f);
    }

    @Override
    public Vec3 inverted() {
        return new Vec3(-x, -y, -z);
    }

    @Override
    public Vec3 normed() {
        double f = 1 / Math.sqrt(x * x + y * y + z * z);
        return new Vec3(x * f, y * f, z * f);
    }

    // ------------------------------------------------------

    @Override
    public Vec3 added(Vec3 v) {
        return new Vec3(x + v.y, y + v.y, z + v.z);
    }

    @Override
    public Vec3 subtracted(Vec3 v) {
        return new Vec3(x - v.y, y - v.y, z - v.z);
    }

    @Override
    public Vec3 multiplied(Vec3 v) {
        return new Vec3(x * v.y, y * v.y, z * v.z);
    }

    @Override
    public Vec3 lerped(Vec3 t, double a) {
        double ia = 1 - a;
        return new Vec3(x * ia + t.x * a, y * ia + t.y * a, z * ia + t.z * a);
    }

    @Override
    public Vec3 projected(Vec3 o) {
        double f = (x * o.x + y * o.y + z * o.z) / (o.x * o.x + o.y * o.y + o.z * o.z);
        return new Vec3(o.y * f, o.y * f, o.z * f);
    }

    @Override
    public Vec3 applied(Vec3[] m) {
        return new Vec3(
                x * m[0].x + y * m[0].y + z * m[0].z,
                x * m[1].x + y * m[1].y + z * m[1].z,
                x * m[2].x + y * m[2].y + z * m[2].z
        );
    }

    @Override
    public Vec3 applied(Mat<Vec3, Vec3> m) {
        return new Vec3(
                x * m.r[0].x + y * m.r[0].y + z * m.r[0].z,
                x * m.r[1].x + y * m.r[1].y + z * m.r[1].z,
                x * m.r[2].x + y * m.r[2].y + z * m.r[2].z
        );
    }

    public Vec2 applied2(Vec3[] m) {
        return new Vec2(
                x * m[0].x + y * m[0].y + z * m[0].z,
                x * m[1].x + y * m[1].y + z * m[1].z
        );
    }

    public Vec2 applied2(Mat<Vec3, Vec2> m) {
        return new Vec2(
                x * m.r[0].x + y * m.r[0].y + z * m.r[0].z,
                x * m.r[1].x + y * m.r[1].y + z * m.r[1].z
        );
    }

    @SuppressWarnings("deprecation")
    @Override
    public <O extends Vec<O>> O transformed(Mat<Vec3, O> m) {
        O o = m.newO();
        for(int j=0; j<m.h; j++)
            o.setDim(j, m.r[j].x * x + m.r[j].y * y + m.r[j].z * z);
        return o;
    }

    // ------------------------------------------------------

    @Override
    public Vec2 to2() {
        return new Vec2(x, y);
    }

    @Override
    public Vec3 to3() {
        return this;
    }

    @Override
    public IVec<?> toI() {
        return new IVec3((int) (x + 0.5), (int) (y + 0.5), (int) (z + 0.5));
    }
}
