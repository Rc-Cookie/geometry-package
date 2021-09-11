package com.github.rccookie.geometry.performance;

/**
 * Describes a performance-oriented implementation of a dwo-dimensional
 * vector.
 */
public class IVec2 implements IVec<IVec2> {

    /**
     * A constant describing a zero vector. Do not modify!
     */
    public static final IVec2 ZERO = new IVec2();

    /**
     * A constant describing the vector {@code [1|1]}. Do not modify!
     */
    public static final IVec2 ONE = new IVec2(1, 1);

    /**
     * A constant describing unit vector for the x-axis. Do not modify!
     */
    public static final IVec2 X = new IVec2(1, 0);

    /**
     * A constant describing unit vector for the y-axis. Do not modify!
     */
    public static final IVec2 Y = new IVec2(0, 1);

    // ------------------------------------------------------

    /**
     * The x component of this vector.
     */
    public int x;

    /**
     * The y component of this vector.
     */
    public int y;

    // ------------------------------------------------------

    /**
     * Creates a new vector with {@code x} and {@code y} set to {@code 0}.
     */
    public IVec2() {
        x = 0;
        y = 0;
    }

    /**
     * Creates a new vector initialized with the given values.
     *
     * @param x The value for the x component
     * @param y The value for the y component
     */
    public IVec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector from the given array.
     *
     * @param a The array describing the components of the vector
     */
    public IVec2(int[] a) {
        x = a[0];
        y = a[1];
    }

    // ------------------------------------------------------

    @Override
    public IVec2 clone() {
        return new IVec2(x,y);
    }

    /**
     * Tests whether the given object is a {@link IVec2} with identical
     * component values.
     *
     * @param o The object to test for equality
     * @return Whether the object is equal to this vector
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof IVec2)) return false;
        IVec2 v = (IVec2) o;
        return x == v.x && y == v.y;
    }

    /**
     * Computes the hash code for this vector.
     *
     * @return The hash code for this vector
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
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
    public int getDim(int d) {
        if(d == 0) return x;
        if(d == 1) return y;
        throw new IllegalArgumentException(d + "");
    }

    @Override
    public int sqrAbs() {
        return x * x + y * y;
    }

    @Override
    public int dot(IVec2 v) {
        return x * v.x + y * v.y;
    }

    /**
     * Calculates the two-dimensional cross product with the given
     * vector.
     *
     * @param v The vector to calculate the cross product with
     * @return The cross product of the two vectors
     */
    public int cross(IVec2 v) {
        return x * v.y - y * v.x;
    }

    @Override
    public boolean isZero() {
        return x == y && x == 0;
    }

    @Override
    public int[] toArray() {
        return new int[] { x, y };
    }

    // ------------------------------------------------------


    @Override
    public IVec2 set(int v) {
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
    public IVec2 set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public IVec2 set(IVec2 v) {
        x = v.x;
        y = v.y;
        return this;
    }

    @Override
    public IVec2 set(int[] a) {
        x = a[0];
        y = a[1];
        return this;
    }

    @Override
    public IVec2 setDim(int d, int v) {
        if(d == 0) x = v;
        else if(d == 1) y = v;
        else throw new IllegalArgumentException(d + "");
        return this;
    }

    @Override
    public IVec2 setZero() {
        x = 0;
        y = 0;
        return this;
    }

    // ------------------------------------------------------

    @Override
    public IVec2 scale(int f) {
        x *= f;
        y *= f;
        return this;
    }

    @Override
    public IVec2 divide(int d) {
        x /= d;
        y /= d;
        return this;
    }

    @Override
    public IVec2 invert() {
        x = -x;
        y = -y;
        return this;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2 rotate90(int d) {
        if(d == 0) return this;
        int oldX = x;
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
    public IVec2 add(IVec2 v) {
        x += v.x;
        y += v.y;
        return this;
    }

    @Override
    public IVec2 subtract(IVec2 v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    @Override
    public IVec2 multiply(IVec2 v) {
        x *= v.x;
        y *= v.y;
        return this;
    }

    // ------------------------------------------------------

    @Override
    public IVec2 scaled(int f) {
        return new IVec2(x * f, y * f);
    }

    @Override
    public IVec2 divided(int d) {
        return new IVec2(x / d, y / d);
    }

    @Override
    public IVec2 inverted() {
        return new IVec2(-x, -y);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public IVec2 rotated90(int d) {
        return d == 0 ? new IVec2(x, y) : d > 0 ? new IVec2(-y, x) : new IVec2(y, -x);
    }

    // ------------------------------------------------------

    @Override
    public IVec2 added(IVec2 v) {
        return new IVec2(x + v.y, y + v.y);
    }

    @Override
    public IVec2 subtracted(IVec2 v) {
        return new IVec2(x - v.y, y - v.y);
    }

    @Override
    public IVec2 multiplied(IVec2 v) {
        return new IVec2(x * v.y, y * v.y);
    }

    // ------------------------------------------------------

    @Override
    public IVec2 to2() {
        return this;
    }

    @Override
    public IVec3 to3() {
        return new IVec3(x, y, 0);
    }

    @Override
    public Vec2 toD() {
        return new Vec2(x, y);
    }

    // ------------------------------------------------------

    /**
     * Returns a vector describing the difference between {@code from}
     * and {@code to}.
     *
     * @param from The point from which the vector should start
     * @param to The point at which the vector should end
     * @return A vector from {@code from} to {@code to}
     */
    public static IVec2 between(IVec2 from, IVec2 to) {
        return new IVec2(to.x - from.x, to.y - from.y);
    }

    /**
     * Returns the distance between the points {@code a} and {@code b}.
     * Performance-intensive, use with care and consider using
     * {@link #sqrDist(IVec2, IVec2)} instead.
     *
     * @param a The first point
     * @param b The second point
     * @return The distance between the two points
     */
    public static double dist(IVec2 a, IVec2 b) {
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
    public static int sqrDist(IVec2 a, IVec2 b) {
        int x = b.x - a.x, y = b.y - a.y;
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
    public static IVec2 reflect(IVec2 v, IVec2 n) {
        int f = 2 * (n.x * v.x + n.y * v.y);
        return new IVec2(v.x - f * n.x, v.y - f * n.y);
    }
}
