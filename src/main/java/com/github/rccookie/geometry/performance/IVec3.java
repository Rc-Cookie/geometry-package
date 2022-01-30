package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonArray;
import com.github.rccookie.json.JsonCtor;
import com.github.rccookie.json.Type;

public class IVec3 implements IVec<IVec3, Vec3> {

    public static final IVec3 ZERO = new IVec3();
    public static final IVec3 ONE = new IVec3(1, 1, 1);
    public static final IVec3 X = new IVec3(1, 0, 0);
    public static final IVec3 Y = new IVec3(0, 1, 0);
    public static final IVec3 Z = new IVec3(0, 0, 1);

    // ------------------------------------------------------

    public int x;
    public int y;
    public int z;

    // ------------------------------------------------------

    public IVec3() {
        x = 0;
        y = 0;
        z = 0;
    }

    @JsonCtor(type = Type.ARRAY)
    public IVec3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public IVec3(int[] a) {
        x = a[0];
        y = a[1];
        z = a[2];
    }

    // ------------------------------------------------------

    @Override
    public IVec3 clone() {
        return new IVec3(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        IVec3 v = (IVec3) o;
        return x == v.x && y == v.y && z == v.z;
    }

    @Override
    public int hashCode() {
        return 31 * (31 * x + y) + z;
    }

    @Override
    public String toString() {
        return "["+x+"|"+y+"|"+z+"]";
    }

    @Override
    public Object toJson() {
        return new JsonArray(x, y, z);
    }

    // ------------------------------------------------------


    @Override
    public int size() {
        return 3;
    }

    @Override
    public int getDim(int d) {
        if(d == 0) return x;
        if(d == 1) return y;
        if(d == 2) return z;
        throw new IllegalArgumentException(d + "");
    }

    @Override
    public int sqrAbs() {
        return x * x + y * y + z * z;
    }

    @Override
    public int dot(IVec3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    @Override
    public boolean isZero() {
        return (x == y) == (y == z) && x == 0;
    }

    @Override
    public int[] toArray() {
        return new int[] { x, y, z };
    }

    // ------------------------------------------------------


    @Override
    public IVec3 set(int v) {
        x = v;
        y = v;
        z = v;
        return this;
    }

    public IVec3 set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    @Override
    public IVec3 set(IVec3 v) {
        x = v.x;
        y = v.y;
        z = v.z;
        return this;
    }

    @Override
    public IVec3 set(int[] a) {
        x = a[0];
        y = a[1];
        z = a[2];
        return this;
    }

    @Override
    public IVec3 setDim(int d, int v) {
        if(d == 0) x = v;
        else if(d == 1) y = v;
        else if(d == 2) z = v;
        else throw new IllegalArgumentException(d + "");
        return this;
    }

    @Override
    public IVec3 setZero() {
        x = 0;
        y = 0;
        z = 0;
        return this;
    }

    // ------------------------------------------------------

    @Override
    public IVec3 scale(int f) {
        x *= f;
        y *= f;
        z *= f;
        return this;
    }

    @Override
    public IVec3 divide(int d) {
        x /= d;
        y /= d;
        z /= d;
        return this;
    }

    @Override
    public IVec3 negate() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    // ------------------------------------------------------

    @Override
    public IVec3 add(IVec3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    /**
     * Adds the given amount onto the x, y and z component of the
     * vector.
     *
     * @param x The value to add to the x component
     * @param y The value to add to the y component
     * @param z The value to add to the z component
     * @return This vector
     */
    public IVec3 add(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    @Override
    public IVec3 subtract(IVec3 v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    @Override
    public IVec3 multiply(IVec3 v) {
        x *= v.x;
        y *= v.y;
        z -= v.z;
        return this;
    }

    // ------------------------------------------------------

    @Override
    public IVec3 scaled(int f) {
        return new IVec3(x * f, y * f, z * f);
    }

    @Override
    public Vec3 scaled(float f) {
        return new Vec3(x * f, y * f, z * f);
    }

    @Override
    public IVec3 divided(int d) {
        return new IVec3(x / d, y / d, z / d);
    }

    @Override
    public Vec3 divided(float d) {
        return new Vec3(x / d, y / d, z / d);
    }

    @Override
    public IVec3 negated() {
        return new IVec3(-x, -y, -z);
    }

    // ------------------------------------------------------

    @Override
    public IVec3 added(IVec3 v) {
        return new IVec3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * Returns a vector with the given values added onto the
     * corresponding component of this vector.
     *
     * @param x The value to add to the x component
     * @param y The value to add to the y component
     * @param z The value to add to the z component
     * @return The vector with the values added
     */
    public IVec3 added(int x, int y, int z) {
        return new IVec3(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public IVec3 subtracted(IVec3 v) {
        return new IVec3(x - v.x, y - v.y, z - v.z);
    }

    @Override
    public IVec3 multiplied(IVec3 v) {
        return new IVec3(x * v.x, y * v.y, z * v.z);
    }

    // ------------------------------------------------------

    @Override
    public IVec2 to2() {
        return new IVec2(x, y);
    }

    @Override
    public IVec3 to3() {
        return this;
    }

    @Override
    public Vec3 toF() {
        return new Vec3(x, y, z);
    }
}
