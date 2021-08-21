package com.github.rccookie.geometry;

import com.github.rccookie.util.Arguments;

/**
 * Represents a vector that has an infinite number of dimensions that each have the
 * same constant value.
 * <p>To work properly with other classes {@link #size()} always returns {@code 1}.
 */
public class InfiniteVector implements Vector {

    /**
     * The constant size of this vector in every dimension.
     */
    private final double value;


    public InfiniteVector(double value) {
        this.value = value;
    }

    @Override
    public double get(int dimension) {
        if(dimension < 0) throw new DimensionOutOfBoundsException(dimension, Integer.MAX_VALUE);
        return value;
    }

    @Override
    public Vector set(int dimension, double coordinate) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        if(dimension < 0) throw new DimensionOutOfBoundsException(dimension, Integer.MAX_VALUE);
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Vector clone() {
        return new InfiniteVector(value);
    }

    @Override
    public double x() {
        return value;
    }

    @Override
    public double y() {
        return value;
    }

    @Override
    public double[] toArray() {
        return new double[] { value };
    }

    @Override
    public Vector setX(double x) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        return set(X, x);
    }

    @Override
    public Vector setY(double y) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        return set(Y, y);
    }

    @Override
    public Vector set(Vector vector) throws NullPointerException, UnsupportedOperationException {
        Arguments.checkNull(vector);
        if(vector.size() == 0) return this;
        throw new UnsupportedOperationException();
    }

    @Override
    public double abs() {
        return value;
    }

    @Override
    public double sqrAbs() {
        return value * value;
    }

    @Override
    public double angle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double dot(Vector vector) throws NullPointerException {
        Arguments.checkNull(vector);
        double dot = 0;
        int size = vector.size();
        for(int i=0; i<size; i++) dot += vector.get(i) * value;
        return dot;
    }

    @Override
    public boolean isZero() {
        return value == 0;
    }

    @Override
    public Vector scale(double scalar) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector divide(double denominator) throws ArithmeticException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector invert() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector norm() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector setZero() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector add(Vector vector) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector subtract(Vector vector) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector multiply(Vector vector) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector floor() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector ceil() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector round() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector scaled(double scalar) {
        return new InfiniteVector(value * scalar);
    }

    @Override
    public Vector divided(double denominator) throws ArithmeticException {
        return new InfiniteVector(value / denominator);
    }

    @Override
    public Vector inverted() {
        return new InfiniteVector(-value);
    }

    @Override
    public Vector normed() {
        return new InfiniteVector(value == 0 ? 0 : value > 0 ? 1 : -1);
    }

    @Override
    public Vector added(Vector vector) {
        Arguments.checkNull(vector);
        double[] coordinates = new double[vector.size()];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = value + vector.get(i);
        return Vector.of(coordinates);
    }

    @Override
    public Vector subtracted(Vector vector) {
        Arguments.checkNull(vector);
        double[] coordinates = new double[vector.size()];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = value - vector.get(i);
        return Vector.of(coordinates);
    }

    @Override
    public Vector multiplied(Vector vector) {
        Arguments.checkNull(vector);
        double[] coordinates = new double[vector.size()];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = value * vector.get(i);
        return Vector.of(coordinates);
    }

    @Override
    public Vector floored() {
        return new InfiniteVector(Math.floor(value));
    }

    @Override
    public Vector ceiled() {
        return new InfiniteVector(Math.ceil(value));
    }

    @Override
    public Vector rounded() {
        return new InfiniteVector(Math.round(value));
    }

    @Override
    public Vector2D get2D() throws UnsupportedOperationException {
        return Vector.of(value, value);
    }

    @Override
    public Vector3D get3D() throws UnsupportedOperationException {
        return Vector.of(value, value, value);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        return Vector.equals(this, o);
    }

    @Override
    public int hashCode() {
        return Vector.hashCode(this);
    }

    @Override
    public String toString() {
        return "[" + value + "...]";
    }
}
