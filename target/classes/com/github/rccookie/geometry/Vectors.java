package com.github.rccookie.geometry;

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
        public Vector set(int dimension, double coordinate)
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
        public Vector2D set(int dimension, double coordinate)
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
        public Vector3D set(int dimension, double coordinate)
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
}
