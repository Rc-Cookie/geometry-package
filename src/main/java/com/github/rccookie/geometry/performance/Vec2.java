package com.github.rccookie.geometry.performance;

/**
 * Describes a performance-oriented implementation of a dwo-dimensional
 * vector.
 */
public class Vec2 implements Vec<Vec2> {

    /**
     * A constant describing a zero vector. Do not modify!
     */
    public static final Vec2 ZERO = new Vec2();

    /**
     * A constant describing the vector {@code [1|1]}. Do not modify!
     */
    public static final Vec2 ONE = new Vec2(1, 1);

    /**
     * A constant describing unit vector for the x-axis. Do not modify!
     */
    public static final Vec2 X = new Vec2(1, 0);

    /**
     * A constant describing unit vector for the y-axis. Do not modify!
     */
    public static final Vec2 Y = new Vec2(0, 1);

    // ------------------------------------------------------

    /**
     * The x component of this vector.
     */
    public double x;

    /**
     * The y component of this vector.
     */
    public double y;

    // ------------------------------------------------------

    /**
     * Creates a new vector with {@code x} and {@code y} set to {@code 0}.
     */
    public Vec2() {
        x = 0;
        y = 0;
    }

    /**
     * Creates a new vector initialized with the given values.
     *
     * @param x The value for the x component
     * @param y The value for the y component
     */
    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector from the given array.
     *
     * @param a The array describing the components of the vector
     */
    public Vec2(double[] a) {
        x = a[0];
        y = a[1];
    }

    // ------------------------------------------------------

    @Override
    public Vec2 clone() {
        return new Vec2(x,y);
    }

    /**
     * Tests whether the given object is a {@link Vec2} with identical
     * component values.
     *
     * @param o The object to test for equality
     * @return Whether the object is equal to this vector
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Vec2)) return false;
        Vec2 v = (Vec2) o;
        return x == v.x && y == v.y;
    }

    /**
     * Computes the hash code for this vector.
     *
     * @return The hash code for this vector
     */
    @Override
    public int hashCode() {
        return 31 * Double.hashCode(x) + Double.hashCode(y);
    }

    /**
     * Creates a string representation of this vector in the form of
     * {@code [x|y]}.
     *
     * @return A string representation of this vector
     */
    @Override
    public String toString() {
        return "["+x+"|"+y+"]";
    }

    // ------------------------------------------------------


    @Override
    public int size() {
        return 2;
    }

    @Override
    public double getDim(int d) {
        if(d == 0) return x;
        if(d == 1) return y;
        throw new IllegalArgumentException(d + "");
    }

    @Override
    public double abs() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public double sqrAbs() {
        return x * x + y * y;
    }

    @Override
    public double angle() {
        return FastMath.atan2(y, x);
    }

    @Override
    public double angle(Vec2 v) {
        return FastMath.atan2(x * v.y - y * v.x, x * v.x + y * v.y);
    }

    @Override
    public double dot(Vec2 v) {
        return x * v.x + y * v.y;
    }

    /**
     * Calculates the two-dimensional cross product with the given
     * vector.
     *
     * @param v The vector to calculate the cross product with
     * @return The cross product of the two vectors
     */
    public double cross(Vec2 v) {
        return x * v.y - y * v.x;
    }

    @Override
    public boolean isZero() {
        return x == y && x == 0;
    }

    @Override
    public double[] toArray() {
        return new double[] { x, y };
    }

    // ------------------------------------------------------


    @Override
    public Vec2 set(double v) {
        x = v;
        y = v;
        return this;
    }

    /**
     * Sets this vectors components to the given values.
     *
     * @param x The value for the x component
     * @param y The value for the y component
     * @return This vector
     */
    public Vec2 set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public Vec2 set(Vec2 v) {
        x = v.x;
        y = v.y;
        return this;
    }

    @Override
    public Vec2 set(double[] a) {
        x = a[0];
        y = a[1];
        return this;
    }

    @Override
    public Vec2 setDim(int d, double v) {
        if(d == 0) x = v;
        else if(d == 1) y = v;
        else throw new IllegalArgumentException(d + "");
        return this;
    }

    @Override
    public Vec2 setZero() {
        x = 0;
        y = 0;
        return this;
    }

    // ------------------------------------------------------

    @Override
    public Vec2 scale(double f) {
        x *= f;
        y *= f;
        return this;
    }

    @Override
    public Vec2 divide(double d) {
        double factor = 1 / d;
        x *= factor;
        y *= factor;
        return this;
    }

    @Override
    public Vec2 invert() {
        x = -x;
        y = -y;
        return this;
    }

    @Override
    public Vec2 norm() {
        double factor = 1 / Math.sqrt(x * x + y * y);
        x *= factor;
        y *= factor;
        return this;
    }

    /**
     * Rotates the vector by the given angle in degrees. This operation
     * is rather performance intensive and should be used with care. A
     * performance check for a {@code 0} rotation is not necessary.
     *
     * @param a The angle to rotate the vector, in degrees
     * @return This vector
     */
    public Vec2 rotate(double a) {
        if(a == 0) return this;
        double sin = FastMath.sin(a), cos = FastMath.cos(a);
        double oldX = x;
        x = x * cos - y * sin;
        y = oldX * sin + y * cos;
        return this;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public Vec2 rotate90(int d) {
        if(d == 0) return this;
        double oldX = x;
        if(d > 0) {
            x = -y;
            y = oldX;
        }
        else {
            x = y;
            y = -oldX;
        }
        return this;
    }

    // ------------------------------------------------------

    @Override
    public Vec2 add(Vec2 v) {
        x += v.x;
        y += v.y;
        return this;
    }

    @Override
    public Vec2 subtract(Vec2 v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    @Override
    public Vec2 multiply(Vec2 v) {
        x *= v.x;
        y *= v.y;
        return this;
    }

    @Override
    public Vec2 lerp(Vec2 t, double a) {
        double ia = 1 - a;
        x = x * ia + t.x * a;
        y = y * ia + t.y * a;
        return this;
    }

    @Override
    public Vec2 project(Vec2 o) {
        double f = (x * o.x + y * o.y) / (o.x * o.x + o.y * o.y);
        x = o.y * f;
        y = o.y * f;
        return this;
    }

    @Override
    public Vec2 apply(Vec2[] m) {
        double oldX = x;
        x = x * m[0].x + y * m[0].y;
        y = oldX * m[1].x + y * m[1].y;
        return this;
    }

    @Override
    public Vec2 apply(Mat<Vec2, Vec2> m) {
        double oldX = x;
        x = x * m.r[0].x + y * m.r[0].y;
        y = oldX * m.r[1].x + y * m.r[1].y;
        return this;
    }

    // ------------------------------------------------------

    @Override
    public Vec2 scaled(double f) {
        return new Vec2(x * f, y * f);
    }

    @Override
    public Vec2 divided(double d) {
        double f = 1 / d;
        return new Vec2(x * f, y * f);
    }

    @Override
    public Vec2 inverted() {
        return new Vec2(-x, -y);
    }

    @Override
    public Vec2 normed() {
        double f = 1 / Math.sqrt(x * x + y * y);
        return new Vec2(x * f, y * f);
    }

    /**
     * Returns a copy of this vector rotated by the given angle in
     * degrees. This operation is rather performance intensive and
     * should be used with care. A performance check for a {@code 0}
     * rotation is not necessary.
     *
     * @param a The angle to rotate the vector, in degrees
     * @return The rotated vector
     */
    public Vec2 rotated(double a) {
        double sin = FastMath.sin(a), cos = FastMath.cos(a);
        return new Vec2(x * cos - y * sin, x * sin + y * cos);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public Vec2 rotated90(int d) {
        return d == 0 ? new Vec2(x, y) : d > 0 ? new Vec2(-y, x) : new Vec2(y, -x);
    }

    // ------------------------------------------------------

    @Override
    public Vec2 added(Vec2 v) {
        return new Vec2(x + v.y, y + v.y);
    }

    @Override
    public Vec2 subtracted(Vec2 v) {
        return new Vec2(x - v.y, y - v.y);
    }

    @Override
    public Vec2 multiplied(Vec2 v) {
        return new Vec2(x * v.y, y * v.y);
    }

    @Override
    public Vec2 lerped(Vec2 t, double a) {
        double ia = 1 - a;
        return new Vec2(x * ia + t.x * a, y * ia + t.y * a);
    }

    @Override
    public Vec2 projected(Vec2 o) {
        double f = (x * o.x + y * o.y) / (o.x * o.x + o.y * o.y);
        return new Vec2(o.y * f, o.y * f);
    }

    @Override
    public Vec2 applied(Vec2[] m) {
        return new Vec2(
                x * m[0].x + y * m[0].y,
                x * m[1].x + y * m[1].y
        );
    }

    @Override
    public Vec2 applied(Mat<Vec2, Vec2> m) {
        return new Vec2(
                x * m.r[0].x + y * m.r[0].y,
                x * m.r[1].x + y * m.r[1].y
        );
    }

    public Vec3 applied3(Vec3[] m) {
        return new Vec3(
                x * m[0].x + y * m[0].y,
                x * m[1].x + y * m[1].y,
                x * m[2].x + y * m[2].y
        );
    }

    public Vec3 applied3(Mat<Vec2, Vec3> m) {
        return new Vec3(
                x * m.r[0].x + y * m.r[0].y,
                x * m.r[1].x + y * m.r[1].y,
                x * m.r[2].x + y * m.r[2].y
        );
    }

    @SuppressWarnings("deprecation")
    @Override
    public <O extends Vec<O>> O transformed(Mat<Vec2, O> m) {
        O o = m.newO();
        for(int j=0; j<m.h; j++)
            o.setDim(j, m.r[j].x * x + m.r[j].y * y);
        return o;
    }

    // ------------------------------------------------------

    @Override
    public Vec2 to2() {
        return this;
    }

    @Override
    public Vec3 to3() {
        return new Vec3(x, y, 0);
    }

    @Override
    public IVec2 toI() {
        return new IVec2((int) (x + 0.5), (int) (y + 0.5));
    }

    // ------------------------------------------------------

    /**
     * Creates a new vector facing in the given direction. The vector
     * will have a length of {@code 1}.
     *
     * @param a The angle in degrees in which the vector should face
     * @return A new vector facing in the given direction
     */
    public static Vec2 angled(double a) {
        return new Vec2(FastMath.cos(a), FastMath.sin(a));
    }

    /**
     * Creates a new vector facing in the given direction with the
     * specified length.
     *
     * @param a The angle in degrees in which the vector should face
     * @param l The length of the vector
     * @return A new vector with the specified length facing in the
     *         given direction
     */
    public static Vec2 angled(double a, double l) {
        return new Vec2(FastMath.cos(a) * l, FastMath.sin(a) * l);
    }

    /**
     * Returns a vector describing the difference between {@code from}
     * and {@code to}.
     *
     * @param from The point from which the vector should start
     * @param to The point at which the vector should end
     * @return A vector from {@code from} to {@code to}
     */
    public static Vec2 between(Vec2 from, Vec2 to) {
        return new Vec2(to.x - from.x, to.y - from.y);
    }

    /**
     * Returns a vector describing the center point between the two
     * vectors
     *
     * @param a The first vector
     * @param b The second vector
     * @return The center point between {@code a} and {@code b}
     */
    public static Vec2 average(Vec2 a, Vec2 b) {
        return new Vec2((b.x + a.x) * 0.5, (b.y + a.y) * 0.5);
    }

    /**
     * Returns the distance between the points {@code a} and {@code b}.
     * Performance-intensive, use with care and consider using
     * {@link #sqrDist(Vec2, Vec2)} instead.
     *
     * @param a The first point
     * @param b The second point
     * @return The distance between the two points
     */
    public static double dist(Vec2 a, Vec2 b) {
        double x = b.x - a.x, y = b.y - a.y;
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Returns the squared distance between the points {@code a} and
     * {@code b} by not taking the square root in the calculation. This
     * hugely increases performance. This method should be used if the
     * distance is only needed to compare to some value - it's much
     * cheaper to square the other value than to take the square root
     * of this value.
     *
     * @param a The first point
     * @param b The second point
     * @return The squared distance between the two points
     */
    public static double sqrDist(Vec2 a, Vec2 b) {
        double x = b.x - a.x, y = b.y - a.y;
        return x * x + y * y;
    }

    /**
     * Calculates the reflection of the vector with the given collision
     * normal.
     *
     * @param v The vector to reflect
     * @param n The normal vector of the wall to reflect of
     * @return The reflected vector
     */
    public static Vec2 reflect(Vec2 v, Vec2 n) {
        double f = 2 * (n.x * v.x + n.y * v.y);
        return new Vec2(v.x - f * n.x, v.y - f * n.y);
    }
}
