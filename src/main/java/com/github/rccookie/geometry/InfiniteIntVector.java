package com.github.rccookie.geometry;

import com.github.rccookie.util.Arguments;

/**
 * Represents a vector that has an infinite number of dimensions that each have the
 * same constant value.
 * <p>To work properly with other classes {@link #size()} always returns {@code 1}.
 */
public class InfiniteIntVector implements IntVector {

    /**
     * The constant size of this vector in every dimension.
     */
    private final int value;


    public InfiniteIntVector(int value) {
        this.value = value;
    }

    @Override
    public int get(int dimension) {
        if(dimension < 0) throw new DimensionOutOfBoundsException(dimension, Integer.MAX_VALUE);
        return value;
    }

    @Override
    public IntVector setDim(int dimension, int coordinate) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        if(dimension < 0) throw new DimensionOutOfBoundsException(dimension, Integer.MAX_VALUE);
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public IntVector clone() {
        return new InfiniteIntVector(value);
    }

    @Override
    public int x() {
        return value;
    }

    @Override
    public int y() {
        return value;
    }

    @Override
    public int[] toArray() {
        return new int[] { value };
    }

    @Override
    public IntVector setX(int x) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        return setDim(X, x);
    }

    @Override
    public IntVector setY(int y) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        return setDim(Y, y);
    }

    @Override
    public IntVector set(IntVector vector) throws NullPointerException, UnsupportedOperationException {
        Arguments.checkNull(vector);
        throw new UnsupportedOperationException();
    }

    @Override
    public IntVector set(int... coordinates) {
        Arguments.checkNull(coordinates);
        throw new UnsupportedOperationException();
    }

    @Override
    public int dot(IntVector vector) throws NullPointerException {
        Arguments.checkNull(vector);
        int dot = 0;
        int size = vector.size();
        for(int i=0; i<size; i++)
            dot += vector.get(i) * value;
        return dot;
    }

    @Override
    public boolean isZero() {
        return value == 0;
    }

    @Override
    public IntVector scale(int scalar) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public IntVector divide(int denominator) throws ArithmeticException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public IntVector invert() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public IntVector setZero() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public IntVector add(IntVector vector) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IntVector subtract(IntVector vector) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IntVector multiply(IntVector vector) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IntVector scaled(int scalar) {
        return new InfiniteIntVector(value * scalar);
    }

    @Override
    public IntVector divided(int denominator) throws ArithmeticException {
        return new InfiniteIntVector(value / denominator);
    }

    @Override
    public IntVector inverted() {
        return new InfiniteIntVector(-value);
    }

    @Override
    public IntVector added(IntVector vector) {
        Arguments.checkNull(vector);
        int[] coordinates = new int[vector.size()];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = value + vector.get(i);
        return IntVector.of(coordinates);
    }

    @Override
    public IntVector subtracted(IntVector vector) {
        Arguments.checkNull(vector);
        int[] coordinates = new int[vector.size()];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = value - vector.get(i);
        return IntVector.of(coordinates);
    }

    @Override
    public IntVector multiplied(IntVector vector) {
        Arguments.checkNull(vector);
        int[] coordinates = new int[vector.size()];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = value * vector.get(i);
        return IntVector.of(coordinates);
    }

    @Override
    public IntVector2D get2D() throws UnsupportedOperationException {
        return IntVector.of(value, value);
    }

    @Override
    public IntVector3D get3D() throws UnsupportedOperationException {
        return IntVector.of(value, value, value);
    }

    @Override
    public InfiniteVector getVector() {
        return new InfiniteVector(value);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        return IntVector.equals(this, o);
    }

    @Override
    public int hashCode() {
        return IntVector.hashCode(this);
    }

    @Override
    public String toString() {
        return "[" + value + "...]";
    }
}
