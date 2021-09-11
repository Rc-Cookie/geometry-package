package com.github.rccookie.geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class AbstractVectorTest {

    final double x1 = 1.1, y1 = 2.3, x2 = 3.5, y2 = 4.7;

    @BeforeEach
    public void before() {
        Vector.Config.setDisplayMode(FloatDisplayMode.FULL);
    }

    @Test
    void added() {
        assertEqualsNoChange(new TestVector(x1 + x2, y1 + y2), AbstractVector::added);
    }

    @Test
    void subtracted() {
        assertEqualsNoChange(new TestVector(x1 - x2, y1 - y2), AbstractVector::subtracted);
    }

    @Test
    void multiplied() {
        assertEqualsNoChange(new TestVector(x1 * x2, y1 * y2), AbstractVector::multiplied);
    }

    @Test
    void dot() {
        assertEqualsNoChange(x1 * x2 + y1 * y2, AbstractVector::dot);
    }

    @Test
    void toArray() {
        Vector v = new TestVector(x1, y1);
        assertArrayEquals(new double[] { x1, y1 }, v.toArray());
        assertEquals(new TestVector(x1, y1), v);
    }

    @Test
    void testToString() {
        assertEqualsNoChange("[]", TestVector::new, AbstractVector::toString);
        assertEqualsNoChange("[" + x1 + "]", () ->  new TestVector(x1), AbstractVector::toString);
        assertEqualsNoChange("[" + x2 + "|" + y2 + "]", () ->  new TestVector(x2, y2), AbstractVector::toString);
    }

    @Test
    void testEquals() {
        assertEquals(new TestVector(x1, y1), new TestVector(x1, y1));
    }

    @Test
    void testHashCode() {
        assertEqualsNoChange(Vector.hashCode(new TestVector(x1, y1)), TestVector::hashCode);
    }

    @Test
    void get() {
        assertEqualsNoChange(x1, v -> v.get(Vector.X));
        assertEqualsNoChange(y1, v -> v.get(Vector.Y));

        assertDoesNotThrow(() -> new TestVector().get(Vector.X));
        assertThrows(DimensionOutOfBoundsException.class, () -> new TestVector().get(-1));
    }

    @Test
    void set() {
        Vector v = new TestVector(x1, y1);
        v.setDim(Vector.X, x2);
        assertEquals(new TestVector(x2, y1), v);
        v.setDim(Vector.Y, y2);
        assertEquals(new TestVector(x2, y2), v);

        assertThrows(DimensionOutOfBoundsException.class, () -> v.setDim(Vector.Z, 1));
        assertThrows(DimensionOutOfBoundsException.class, () -> v.setDim(-1, 1));
    }

    @Test
    void size() {
        assertEqualsNoChange(0, TestVector::new, TestVector::size);
        assertEqualsNoChange(1, () -> new TestVector(x1), TestVector::size);
        assertEqualsNoChange(2, () -> new TestVector(x1, x2), TestVector::size);
    }

    @Test
    void testClone() {
        Vector v = new TestVector(x1, y1);
        Vector clone = v.clone();
        assertEquals(v, clone);
        assertNotSame(v, clone);
    }

    @Test
    void x() {
        assertEqualsNoChange(x1, Vector::x);
    }

    @Test
    void y() {
        assertEqualsNoChange(y1, Vector::y);
    }

    @Test
    void setX() {
        Vector v = new TestVector(x1, y1);
        assertEquals(v.clone().setDim(Vector.X, x2), v.setX(x2));
    }

    @Test
    void setY() {
        Vector v = new TestVector(x1, y1);
        assertEquals(v.clone().setDim(Vector.Y, y2), v.setY(y2));
    }

    @Test
    void testSet() {
        Vector a = new TestVector(x1, y1);
        Vector b = new TestVector(x2, y2);
        assertEquals(b, a.set(b));
        assertEquals(new TestVector(x2, y2), b);
    }

    @Test
    void abs() {
    }

    @Test
    void sqrAbs() {
    }

    @Test
    void angle() {
    }

    @Test
    void testDot() {
    }

    @Test
    void isZero() {
    }

    @Test
    void scale() {
    }

    @Test
    void divide() {
    }

    @Test
    void invert() {
    }

    @Test
    void norm() {
    }

    @Test
    void setZero() {
    }

    @Test
    void add() {
    }

    @Test
    void subtract() {
    }

    @Test
    void multiply() {
    }

    @Test
    void floor() {
    }

    @Test
    void ceil() {
    }

    @Test
    void round() {
    }

    @Test
    void scaled() {
    }

    @Test
    void divided() {
    }

    @Test
    void inverted() {
    }

    @Test
    void normed() {
    }

    @Test
    void testAdded() {
    }

    @Test
    void testAdded1() {
    }

    @Test
    void testSubtracted() {
    }

    @Test
    void testSubtracted1() {
    }

    @Test
    void testMultiplied() {
    }

    @Test
    void testMultiplied1() {
    }

    @Test
    void floored() {
    }

    @Test
    void ceiled() {
    }

    @Test
    void rounded() {
    }

    @Test
    void get2D() {
    }

    @Test
    void get3D() {
    }

    @Test
    void getInt() {
    }


    <T> void assertEqualsNoChange(T expected, VectorTestDouble<T> generator) {
        TestVector a = new TestVector(x1, y1);
        TestVector b = new TestVector(x2, y2);

        assertEquals(expected, generator.generateResult(a, b));

        assertEquals(new TestVector(x1, y1), a);
        assertEquals(new TestVector(x2, y2), b);
    }

    <T> void assertEqualsNoChange(T expected, VectorTestSingle<T> generator) {
        assertEqualsNoChange(expected, () -> new TestVector(x1, y1), generator);
    }

    <T> void assertEqualsNoChange(T expected, Supplier<TestVector> vectorSupplier, VectorTestSingle<T> generator) {
        TestVector v = vectorSupplier.get();

        assertEquals(expected, generator.generateResult(v));

        assertEquals(vectorSupplier.get(), v);
    }



    @FunctionalInterface
    interface VectorTestDouble<R> {
        R generateResult(TestVector a, TestVector b);
    }

    @FunctionalInterface
    interface VectorTestSingle<R> {
        R generateResult(TestVector v);
    }


    static class TestVector extends AbstractVector<TestVector> {

        public TestVector(double... coordinates) {
            super(coordinates);
        }

        @Override
        public TestVector clone() {
            return new TestVector(toArray());
        }

        @Override
        public double angle() {
            return 0;
        }
    }
}