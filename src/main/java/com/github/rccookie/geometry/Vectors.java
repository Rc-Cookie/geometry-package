package com.github.rccookie.geometry;

import com.github.rccookie.util.Arguments;
import com.github.rccookie.util.Console;

public final class Vectors {
    private Vectors() {
        throw new UnsupportedOperationException();
    }

    public static Vector immutableVector(Vector base) {
        return new ImmutableVector(base);
    }

    public static Vector2D immutableVector(Vector2D base) {
        return new ImmutableVector2D(base);
    }

    public static Vector3D immutableVector(Vector3D base) {
        return new ImmutableVector3D(base);
    }

    public static IntVector immutableVector(IntVector base) {
        return new ImmutableIntVector(base);
    }

    public static IntVector2D immutableVector(IntVector2D base) {
        return new ImmutableIntVector2D(base);
    }

    public static IntVector3D immutableVector(IntVector3D base) {
        return new ImmutableIntVector3D(base);
    }



    private static class ImmutableVector implements Vector {

        private static final long serialVersionUID = -3209379792678205620L;

        private final Vector base;

        public ImmutableVector(Vector base) {
            this.base = base;
        }

        @Override
        public double get(int dimension) {
            return base.get(dimension);
        }

        @Override
        public Vector setDim(int dimension, double coordinate)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return base.size();
        }

        @Override
        public Vector clone() {
            return base.clone();
        }

        @Override
        public double x() {
            return base.x();
        }

        @Override
        public double y() {
            return base.y();
        }

        @Override
        public double[] toArray() {
            return base.toArray();
        }

        @Override
        public Vector setX(double x) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector setY(double y) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector set(Vector vector) throws NullPointerException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector set(double... coordinates) {
            throw new UnsupportedOperationException();
        }

        @Override
        public double abs() {
            return base.abs();
        }

        @Override
        public double sqrAbs() {
            return base.sqrAbs();
        }

        @Override
        public double angle() {
            return base.angle();
        }

        @Override
        public double dot(Vector vector) throws NullPointerException {
            return base.dot(vector);
        }

        @Override
        public boolean isZero() {
            return base.isZero();
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
            return base.scaled(scalar);
        }

        @Override
        public Vector divided(double denominator) throws ArithmeticException {
            return base.divided(denominator);
        }

        @Override
        public Vector inverted() {
            return base.inverted();
        }

        @Override
        public Vector normed() {
            return base.normed();
        }

        @Override
        public Vector added(Vector vector) {
            return base.added(vector);
        }

        @Override
        public Vector subtracted(Vector vector) {
            return base.subtracted(vector);
        }

        @Override
        public Vector multiplied(Vector vector) {
            return base.multiplied(vector);
        }

        @Override
        public Vector floored() {
            return base.floored();
        }

        @Override
        public Vector ceiled() {
            return base.ceiled();
        }

        @Override
        public Vector rounded() {
            return base.rounded();
        }

        @Override
        public Vector2D get2D() throws UnsupportedOperationException {
            return base.get2D();
        }

        @Override
        public Vector3D get3D() throws UnsupportedOperationException {
            return base.get3D();
        }

        @Override
        public IntVector getInt() {
            return base.getInt();
        }

        @Override
        public String toString() {
            return Vector.toString(this);
        }

        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
        @Override
        public boolean equals(Object obj) {
            return Vector.equals(base, obj);
        }

        @Override
        public int hashCode() {
            return Vector.hashCode(base);
        }
    }


    private static class ImmutableVector2D extends Vector2D {

        private static final long serialVersionUID = -570640519112991578L;

        private final Vector2D base;

        public ImmutableVector2D(Vector2D base) {
            this.base = base;
        }

        @Override
        public double get(int dimension) {
            return base.get(dimension);
        }

        @Override
        public Vector2D setDim(int dimension, double coordinate)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return base.size();
        }

        @Override
        public Vector2D clone() {
            return base.clone();
        }

        @Override
        public double x() {
            return base.x();
        }

        @Override
        public double y() {
            return base.y();
        }

        @Override
        public double[] toArray() {
            return base.toArray();
        }

        @Override
        public Vector2D setX(double x) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D setY(double y) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public double abs() {
            return base.abs();
        }

        @Override
        public double sqrAbs() {
            return base.sqrAbs();
        }

        @Override
        public double angle() {
            return base.angle();
        }

        @Override
        public double dot(Vector vector) throws NullPointerException {
            return base.dot(vector);
        }

        @Override
        public boolean isZero() {
            return base.isZero();
        }

        @Override
        public Vector2D scale(double scalar) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D divide(double denominator) throws ArithmeticException, UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D invert() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D norm() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D setZero() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D add(Vector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D subtract(Vector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D multiply(Vector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D floor() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D ceil() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D round() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector2D scaled(double scalar) {
            return base.scaled(scalar);
        }

        @Override
        public Vector2D divided(double denominator) throws ArithmeticException {
            return base.divided(denominator);
        }

        @Override
        public Vector2D inverted() {
            return base.inverted();
        }

        @Override
        public Vector2D normed() {
            return base.normed();
        }

        @Override
        public Vector added(Vector vector) {
            return base.added(vector);
        }

        @Override
        public Vector subtracted(Vector vector) {
            return base.subtracted(vector);
        }

        @Override
        public Vector multiplied(Vector vector) {
            return base.multiplied(vector);
        }

        @Override
        public Vector2D floored() {
            return base.floored();
        }

        @Override
        public Vector2D ceiled() {
            return base.ceiled();
        }

        @Override
        public Vector2D rounded() {
            return base.rounded();
        }

        @Override
        public Vector2D get2D() throws UnsupportedOperationException {
            return base.get2D();
        }

        @Override
        public Vector3D get3D() throws UnsupportedOperationException {
            return base.get3D();
        }
    }


    private static class ImmutableVector3D extends Vector3D {

        private static final long serialVersionUID = -570640519112991578L;

        private final Vector3D base;

        public ImmutableVector3D(Vector3D base) {
            this.base = base;
        }

        @Override
        public double get(int dimension) {
            return base.get(dimension);
        }

        @Override
        public Vector3D setDim(int dimension, double coordinate)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return base.size();
        }

        @Override
        public Vector3D clone() {
            return base.clone();
        }

        @Override
        public double x() {
            return base.x();
        }

        @Override
        public double y() {
            return base.y();
        }

        @Override
        public double[] toArray() {
            return base.toArray();
        }

        @Override
        public Vector3D setX(double x) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D setY(double y) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D set(Vector vector) throws NullPointerException {
            throw new UnsupportedOperationException();
        }

        @Override
        public double abs() {
            return base.abs();
        }

        @Override
        public double sqrAbs() {
            return base.sqrAbs();
        }

        @Override
        public double angle() {
            return base.angle();
        }

        @Override
        public double dot(Vector vector) throws NullPointerException {
            return base.dot(vector);
        }

        @Override
        public boolean isZero() {
            return base.isZero();
        }

        @Override
        public Vector3D scale(double scalar) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D divide(double denominator) throws ArithmeticException, UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D invert() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D norm() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D setZero() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D add(Vector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D subtract(Vector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D multiply(Vector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D floor() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D ceil() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D round() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Vector3D scaled(double scalar) {
            return base.scaled(scalar);
        }

        @Override
        public Vector3D divided(double denominator) throws ArithmeticException {
            return base.divided(denominator);
        }

        @Override
        public Vector3D inverted() {
            return base.inverted();
        }

        @Override
        public Vector3D normed() {
            return base.normed();
        }

        @Override
        public Vector added(Vector vector) {
            return base.added(vector);
        }

        @Override
        public Vector subtracted(Vector vector) {
            return base.subtracted(vector);
        }

        @Override
        public Vector multiplied(Vector vector) {
            return base.multiplied(vector);
        }

        @Override
        public Vector3D floored() {
            return base.floored();
        }

        @Override
        public Vector3D ceiled() {
            return base.ceiled();
        }

        @Override
        public Vector3D rounded() {
            return base.rounded();
        }

        @Override
        public Vector2D get2D() throws UnsupportedOperationException {
            return base.get2D();
        }

        @Override
        public Vector3D get3D() throws UnsupportedOperationException {
            return base.get3D();
        }
    }


    private static class ImmutableIntVector implements IntVector {

        private static final long serialVersionUID = -3209379792678205620L;

        private final IntVector base;

        public ImmutableIntVector(IntVector base) {
            this.base = base;
        }

        @Override
        public int get(int dimension) {
            return base.get(dimension);
        }

        @Override
        public IntVector setDim(int dimension, int coordinate)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return base.size();
        }

        @Override
        public IntVector clone() {
            return base.clone();
        }

        @Override
        public int x() {
            return base.x();
        }

        @Override
        public int y() {
            return base.y();
        }

        @Override
        public int[] toArray() {
            return base.toArray();
        }

        @Override
        public IntVector setX(int x) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector setY(int y) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector set(IntVector vector) throws NullPointerException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector set(int... coordinates) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int dot(IntVector vector) throws NullPointerException {
            return base.dot(vector);
        }

        @Override
        public boolean isZero() {
            return base.isZero();
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
            return base.scaled(scalar);
        }

        @Override
        public IntVector divided(int denominator) throws ArithmeticException {
            return base.divided(denominator);
        }

        @Override
        public IntVector inverted() {
            return base.inverted();
        }

        @Override
        public IntVector added(IntVector vector) {
            return base.added(vector);
        }

        @Override
        public IntVector subtracted(IntVector vector) {
            return base.subtracted(vector);
        }

        @Override
        public IntVector multiplied(IntVector vector) {
            return base.multiplied(vector);
        }

        @Override
        public IntVector2D get2D() throws UnsupportedOperationException {
            return base.get2D();
        }

        @Override
        public IntVector3D get3D() throws UnsupportedOperationException {
            return base.get3D();
        }

        @Override
        public Vector getVector() {
            return base.getVector();
        }

        @Override
        public String toString() {
            return IntVector.toString(this);
        }

        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
        @Override
        public boolean equals(Object obj) {
            return IntVector.equals(base, obj);
        }

        @Override
        public int hashCode() {
            return IntVector.hashCode(base);
        }
    }


    private static class ImmutableIntVector2D extends IntVector2D {

        private static final long serialVersionUID = -570640519112991578L;

        private final IntVector2D base;

        public ImmutableIntVector2D(IntVector2D base) {
            this.base = base;
        }

        @Override
        public int get(int dimension) {
            return base.get(dimension);
        }

        @Override
        public IntVector2D setDim(int dimension, int coordinate)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return base.size();
        }

        @Override
        public IntVector2D clone() {
            return base.clone();
        }

        @Override
        public int x() {
            return base.x();
        }

        @Override
        public int y() {
            return base.y();
        }

        @Override
        public int[] toArray() {
            return base.toArray();
        }

        @Override
        public IntVector2D setX(int x) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector2D setY(int y) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public int dot(IntVector vector) throws NullPointerException {
            return base.dot(vector);
        }

        @Override
        public boolean isZero() {
            return base.isZero();
        }

        @Override
        public IntVector2D scale(int scalar) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector2D divide(int denominator) throws ArithmeticException, UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector2D invert() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector2D setZero() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector2D add(IntVector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector2D subtract(IntVector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector2D multiply(IntVector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector2D scaled(int scalar) {
            return base.scaled(scalar);
        }

        @Override
        public IntVector2D divided(int denominator) throws ArithmeticException {
            return base.divided(denominator);
        }

        @Override
        public IntVector2D inverted() {
            return base.inverted();
        }

        @Override
        public IntVector added(IntVector vector) {
            return base.added(vector);
        }

        @Override
        public IntVector subtracted(IntVector vector) {
            return base.subtracted(vector);
        }

        @Override
        public IntVector multiplied(IntVector vector) {
            return base.multiplied(vector);
        }

        @Override
        public IntVector2D get2D() throws UnsupportedOperationException {
            return base.get2D();
        }

        @Override
        public IntVector3D get3D() throws UnsupportedOperationException {
            return base.get3D();
        }

        @Override
        public Vector2D getVector() {
            return base.getVector();
        }
    }


    private static class ImmutableIntVector3D extends IntVector3D {

        private static final long serialVersionUID = -570640519112991578L;

        private final IntVector3D base;

        public ImmutableIntVector3D(IntVector3D base) {
            this.base = base;
        }

        @Override
        public int get(int dimension) {
            return base.get(dimension);
        }

        @Override
        public IntVector3D setDim(int dimension, int coordinate)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return base.size();
        }

        @Override
        public IntVector3D clone() {
            return base.clone();
        }

        @Override
        public int x() {
            return base.x();
        }

        @Override
        public int y() {
            return base.y();
        }

        @Override
        public int[] toArray() {
            return base.toArray();
        }

        @Override
        public IntVector3D setX(int x) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector3D setY(int y) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector3D set(IntVector vector) throws NullPointerException {
            throw new UnsupportedOperationException();
        }

        @Override
        public int dot(IntVector vector) throws NullPointerException {
            return base.dot(vector);
        }

        @Override
        public boolean isZero() {
            return base.isZero();
        }

        @Override
        public IntVector3D scale(int scalar) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector3D divide(int denominator) throws ArithmeticException, UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector3D invert() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector3D setZero() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector3D add(IntVector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector3D subtract(IntVector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector3D multiply(IntVector vector) {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntVector3D scaled(int scalar) {
            return base.scaled(scalar);
        }

        @Override
        public IntVector3D divided(int denominator) throws ArithmeticException {
            return base.divided(denominator);
        }

        @Override
        public IntVector3D inverted() {
            return base.inverted();
        }

        @Override
        public IntVector added(IntVector vector) {
            return base.added(vector);
        }

        @Override
        public IntVector subtracted(IntVector vector) {
            return base.subtracted(vector);
        }

        @Override
        public IntVector multiplied(IntVector vector) {
            return base.multiplied(vector);
        }

        @Override
        public IntVector2D get2D() throws UnsupportedOperationException {
            return base.get2D();
        }

        @Override
        public IntVector3D get3D() throws UnsupportedOperationException {
            return base.get3D();
        }

        @Override
        public Vector3D getVector() {
            return base.getVector();
        }
    }


    public static void main(String[] args) {
        IntVector x = IntVector.of(1, 2);
        Console.info(x);
        Console.info(x.added(IntVector.of(2, 3)));
        Console.info(x);
        Console.info(x.scale(2));
        Console.info(x.divide(2));
        Console.info(x.setZero());
        Console.info(IntVector.of(Math.round(-1.5f), Math.round(-2.4f)));
    }
}
