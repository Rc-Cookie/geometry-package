package com.github.rccookie.geometry;

public class ZeroDimIntVector extends AbstractIntVector<ZeroDimIntVector> {

    private static final long serialVersionUID = -7810951969992884041L;

    private static final ZeroDimIntVector INSTANCE = new ZeroDimIntVector();

    private ZeroDimIntVector() { }

    @Override
    public ZeroDimIntVector clone() {
        return get();
    }

    @Override
    public ZeroDimIntVector add(IntVector vector) {
        return this;
    }

    @Override
    public int dot(IntVector vector) throws NullPointerException {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Vector)) return false;
        return ((Vector)obj).isZero();
    }

    @Override
    public int get(int dimension) {
        return 0;
    }

    @Override
    public IntVector2D get2D() {
        return new IntVector2D();
    }

    @Override
    public IntVector3D get3D() {
        return new IntVector3D();
    }

    @Override
    public Vector getVector() {
        return ZeroDimVector.get();
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public ZeroDimIntVector invert() {
        return this;
    }

    @Override
    public boolean isZero() {
        return true;
    }


    @Override
    public ZeroDimIntVector scale(int scalar) {
        return this;
    }

    @Override
    public ZeroDimIntVector divide(int denominator) {
        return this;
    }

    @Override
    public ZeroDimIntVector set(IntVector vector) {
        return this;
    }

    @Override
    public ZeroDimIntVector setDim(int dimension, int coordinate)
            throws UnsupportedOperationException, DimensionOutOfBoundsException {
        throw new DimensionOutOfBoundsException(dimension, 0);
    }

    @Override
    public ZeroDimIntVector setX(int x) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        throw new DimensionOutOfBoundsException(X, 0);
    }

    @Override
    public ZeroDimIntVector setY(int y) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        throw new DimensionOutOfBoundsException(Y, 0);
    }

    @Override
    public ZeroDimIntVector setZero() throws UnsupportedOperationException {
        return this;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ZeroDimIntVector subtract(IntVector vectors) {
        return this;
    }

    @Override
    public int[] toArray() {
        return new int[0];
    }

    @Override
    public int x() {
        return 0;
    }

    @Override
    public int y() {
        return 0;
    }

    @Override
    public String toString() {
        return "[]";
    }



    public static ZeroDimIntVector get() {
        return INSTANCE;
    }
}
