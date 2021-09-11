package com.github.rccookie.geometry;

import com.github.rccookie.data.Saveable;
import com.github.rccookie.util.Arguments;
import com.github.rccookie.util.Console;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

/**
 * Representation of a generic geometrical vector. The common abstract implementation
 * if this class is {@link AbstractVector}.
 * <p>Different vectors can have different sizes, meaning the number of different
 * dimension. Inside an instance of a vector the size must be constant though.
 * Usually vectors should have at least 2 dimensions, otherwise they don't make a
 * lot of sense. Because of that the methods {@link #x()} and {@link #y()} exist as
 * convenience methods.
 * <p>At any point in time any dimension of a vector can be requested using
 * {@link #get(int)}. If that dimension is negative, a {@link RuntimeException} of
 * some sort will be thrown. If the dimension is not provided by the vector, it will
 * return {@code 0}, as if it was placed in a world with that many dimensions.
 * <p>Vectors offer a lot of methods to mathematically interact with them, like adding
 * or scaling them, or calculating its length. For more specific operations like cross
 * products or angles you need to use the dimension-specific implementations like
 * {@link Vector2D}.
 * <p>Any vector can also safely be converted into a 2D or 3D vector, for convenience.
 * Note that if the vector is already of that format it will return itself (not a copy).
 * <p>A vector may be <i>immutable</i>. This means that while reading methods will work
 * like normal, any method that may change a coordinate of the vector will throw an
 * {@link UnsupportedOperationException}.
 * <p>Additionally, the vector class also contains some static methods to work with
 * vectors.
 * 
 * @author RcCookie
 * 
 * @see AbstractVector
 * @see Vector2D
 * @see Vector3D
 */
public interface Vector extends Cloneable, Saveable {



    // ------------------------------------------------------------------------------------
    // Constants
    // ------------------------------------------------------------------------------------

    /**
     * The index of the coordinate X.
     */
    int X = 0;

    /**
     * The index of the coordinate Y.
     */
    int Y = 1;

    /**
     * The index of the coordinate Z.
     */
    int Z = 2;


    /**
     * A constant for a zero vector.
     */
    Vector ZERO = Vectors.immutableVector(Vector.of());

    /**
     * A constant for a vector that has a value of {@code 1} in every dimension.
     */
    Vector ONE = new InfiniteVector(1);

    /**
     * A constant for a vector that has a value of {@code -1} in every dimension.
     */
    Vector MINUS_ONE = new InfiniteVector(-1);

    /**
     * Unit vector for the X dimension.
     */
    Vector UNIT_X = Vectors.immutableVector(Vector.of(1));

    /**
     * Unit vector for the Y dimension.
     */
    Vector UNIT_Y = Vectors.immutableVector(Vector.of(0, 1));

    /**
     * Unit vector for the Z dimension.
     */
    Vector UNIT_Z = Vectors.immutableVector(Vector.of(0, 0, 1));

    /**
     * Immutable vector equivalent to {@code Vector.of(1)}.
     */
    Vector RIGHT = UNIT_X;

    /**
     * Immutable vector equivalent to {@code Vector.of(-1)}.
     */
    Vector LEFT = Vectors.immutableVector(RIGHT.inverted());

    /**
     * Immutable vector equivalent to {@code Vector.of(0, 1)}.
     */
    Vector DOWN = UNIT_Y;

    /**
     * Immutable vector equivalent to {@code Vector.of(0, -1)}.
     */
    Vector UP = Vectors.immutableVector(DOWN.inverted());

    /**
     * Immutable vector equivalent to {@code Vector.of(0, 0, 1)}.
     */
    Vector OUT = UNIT_Z;

    /**
     * Immutable vector equivalent to {@code Vector.of(0, 0, -1)}.
     */
    Vector IN = Vectors.immutableVector(OUT.inverted());



    // ------------------------------------------------------------------------------------
    // Base methods
    // ------------------------------------------------------------------------------------



    /**
     * Returns the coordinate in the specified dimension of this vector. If this vector does not specify
     * the given dimension, {@code 0} will be returned.
     * 
     * @param dimension The dimension to get the coordinate for
     * @return The coordinate in the specified dimension
     * @see #x()
     * @see #y()
     * @see #setDim(int, double)
     */
    double get(int dimension);

    /**
     * Sets the coordinate in the specified dimension of this vector to the specified value.
     * 
     * @param dimension The dimension to set the coordinate for
     * @param coordinate The new coordinate in the specified dimension of this vector
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #setX(double)
     * @see #setY(double)
     * @see #get(int)
     */
    Vector setDim(int dimension, double coordinate) throws UnsupportedOperationException, DimensionOutOfBoundsException;

    /**
     * Returns the number of dimensions stored in this vector.
     * 
     * @return The number of dimensions supported by this vector
     */
    int size();

    /**
     * Returns a copy of this vector so that for a vector {@code v} the statement
     * <blockquote>
     * <pre>v.equals(v.clone())</pre>
     * </blockquote>
     * is {@code true} and
     * <blockquote>
     * <pre>v == v.clone()</pre>
     * </blockquote>
     * is {@code false}.
     * 
     * @return A copy of this vector
     */
    Vector clone();



    /**
     * Returns a string representation of this vector with the following syntax:
     * <blockquote><pre>"[x|y|...]"</pre></blockquote>
     * For example, {@code new Vector2D(2, 3).toString();} would return
     * <blockquote><pre>"[2.0|3.0]"</pre></blockquote>
     * The method {@link #toString(Vector)} will generate a string representation
     * for any given vector which should be used in the implementation.
     * 
     * @return A string representation of this vector
     * @see #toString(Vector)
     */
    @Override
    String toString();

    /**
     * Returns, weather the given object is equal to this vector.
     * <p>An object is considered to be equal to a vector if that object ia a subclass of
     * {@link AbstractVector}, they have the same coordinates for every dimension they share and
     * any other dimension of a possibly differently sized vector is {@code 0}.
     * 
     * @param obj The object to check for equality
     * @return Weather the object is equal to this vector
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     * <p>
     * The hashcode of a vector is defined as the return of
     * {@link Objects#hashCode(Object)} with its coordinates passed as an array.
     * 
     * @return A hash code value for this vector
     */
    @Override
    int hashCode();




    // ------------------------------------------------------------------------------------
    // Get / set methods
    // ------------------------------------------------------------------------------------




    /**
     * Returns the x-coordinate of this vector.
     * 
     * @return The x-coordinate of this vector
     * @see #get(int)
     */
    double x();

    /**
     * Returns the y-coordinate of this vector.
     * 
     * @return The y-coordinate of this vector
     * @see #get(int)
     */
    double y();

    /**
     * Returns an array with the length corresponding to the number of dimensions in
     * this vector and filled with the coordinates in each dimension.
     * <p>This array should not be the actual array that may be used to store the
     * dimensions of the vector internally.
     * 
     * @return An array containing the coordinates of this vector
     */
    double[] toArray();

    /**
     * Sets the x-coordinate of this vector to the specified one.
     * 
     * @param x The new x-coordinate for this vector
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @throws DimensionOutOfBoundsException If this vector does not have any dimensions
     * @see #setDim(int, double)
     */
    Vector setX(double x) throws UnsupportedOperationException, DimensionOutOfBoundsException;

    /**
     * Sets the y-coordinate of this vector to the specified one.
     * 
     * @param y The new y-coordinate for this vector
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @throws DimensionOutOfBoundsException If this vector does not have 2 dimensions
     * @see #setDim(int, double)
     */
    Vector setY(double y) throws UnsupportedOperationException, DimensionOutOfBoundsException;

    /**
     * Sets all of this vector's coordinates to the coordinates of the given vector. If the given
     * vector has fewer coordinates than this vector, those coordinates will stay unchanged.
     * 
     * @param vector The vector to set this vector's coordinates to
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @throws NullPointerException If the specified vector is {@code null}
     */
    Vector set(Vector vector) throws NullPointerException, UnsupportedOperationException;

    /**
     * Set's the coordinates of this vector to the given ones. If the array has more elements
     * than the vector has dimensions, a {@link DimensionOutOfBoundsException} will be thrown.
     *
     * @param coordinates The coordinates to assign
     * @return This vector
     */
    Vector set(double... coordinates);




    // ------------------------------------------------------------------------------------
    // Property get methods
    // ------------------------------------------------------------------------------------




    /**
     * Returns the absolute length of this vector, also known as its magnitude.
     * 
     * @return The length of this vector
     * @see #sqrAbs()
     */
    double abs();

    /**
     * Returns the squared length of this vector. This method is intended for comparing
     * the sizes of two vectors without having to take a square root.
     * 
     * @return The squared/ un-rooted length of this vector
     * @see #abs()
     */
    double sqrAbs();

    /**
     * Returns the angle between this vector and the x-axis, in degrees. This is
     * direction-sensitive so the result should have a range from -180° to 180°.
     * 
     * @return The angle between this vector and the x-axis
     */
    double angle();

    /**
     * Returns the dot product of this and the given vector.
     * 
     * @param vector The other vector
     * @return The dot product of this and the other vector
     * @throws NullPointerException If the given vector is null
     */
    double dot(Vector vector) throws NullPointerException;

    /**
     * Returns {@code true} if and only if all coordinates of this vector and thus
     * also its magnitude is {@code 0}.
     * 
     * @return Weather this vector is a zero vector
     */
    boolean isZero();




    
    // ------------------------------------------------------------------------------------
    // Vector operation methods
    // ------------------------------------------------------------------------------------




    /**
     * Scales this vector by the given scalar.
     * 
     * @param scalar The factor to scale by
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #scaled(double)
     */
    Vector scale(double scalar) throws UnsupportedOperationException;

    /**
     * Divides this vector by the given denominator and throws an ArithmeticException
     * if the denominator is {@code 0}.
     * 
     * @param denominator The denominator to divide by
     * @return This vector
     * @throws ArithmeticException If the denominator is {@code 0}
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #divided(double)
     */
    Vector divide(double denominator) throws ArithmeticException, UnsupportedOperationException;

    /**
     * Inverts this vector so that its length stays identical but all of its coordinates
     * are scaled by {@code -1}.
     * 
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #inverted()
     */
    Vector invert() throws UnsupportedOperationException;

    /**
     * Normalizes this vector so that its orientation stays unchanged and its length is
     * set to {@code 1}. If this vector is a zero vector than it will stay a zero vector.
     * 
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #isZero()
     * @see #normed()
     */
    Vector norm() throws UnsupportedOperationException;

    /**
     * Sets all of this vector's coordinates to {@code 0}. Has the same effect as scaling
     * by {@code 0}.
     * 
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #scale(double)
     */
    Vector setZero() throws UnsupportedOperationException;




    /**
     * Adds the given vector onto this one by adding each of the other vector's coordinates
     * onto this vector's coordinates. If a given vector has fewer dimensions than this
     * vector, this vector's corresponding coordinate will stay unchanged.
     * 
     * @param vector The vector to add
     * @return This vector
     * @see #added(Vector)
     */
    Vector add(Vector vector);

    /**
     * Subtracts the given vector from this one by subtracting each of the other vector's
     * coordinates from this vector's coordinates. If a given vector has fewer dimensions than
     * this vector, this vector's corresponding coordinate will stay unchanged.
     * 
     * @param vector The vector to subtract
     * @return This vector
     * @see #subtracted(Vector)
     */
    Vector subtract(Vector vector);

    /**
     * Multiplies each coordinate of this vector with the corresponding coordinate of the other
     * vector.
     *
     * @param vector The vector to multiply with
     * @return This vector
     */
    Vector multiply(Vector vector);



    /**
     * Floors all components of this vector.
     * 
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #floored()
     */
    Vector floor() throws UnsupportedOperationException;

    /**
     * Ceils all components of this vector.
     * 
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #ceiled()
     */
    Vector ceil() throws UnsupportedOperationException;

    /**
     * Rounds all components of this vector.
     * 
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #rounded()
     */
    Vector round() throws UnsupportedOperationException;




    // ------------------------------------------------------------------------------------
    // Cloned-operation methods
    // ------------------------------------------------------------------------------------




    /**
     * Returns a scaled copy of this vector.
     * 
     * @param scalar The factor to scale by
     * @return A scaled version of this vector
     * @see #scale(double)
     * @see #clone()
     */
    Vector scaled(double scalar);

    /**
     * Returns a divided copy of this vector.
     * 
     * @param denominator The denominator to divide by
     * @return A divided version of this vector
     * @throws ArithmeticException If the denominator is {@code 0}
     * @see #divide(double)
     * @see #clone()
     */
    Vector divided(double denominator) throws ArithmeticException;

    /**
     * Returns an inverted copy of this vector.
     * 
     * @return An inverted version of this vector
     * @see #invert()
     * @see #clone()
     */
    Vector inverted();

    /**
     * Returns a normalized copy of this vector. If this vector is a zero vector, then the
     * result will be another zero vector.
     * 
     * @return A normalized version of this vector
     * @see #isZero()
     * @see #norm()
     * @see #clone()
     */
    Vector normed();

    /**
     * Returns a copy of this vector and all the given vector added together.
     * 
     * @param vector The vector to add
     * @return A copy of this vector with the vector added
     * @see #add(Vector)
     * @see #clone()
     */
    Vector added(Vector vector);

    /**
     * Returns a copy of this vector with all the given vector subtracted from it.
     * 
     * @param vector The vector to subtract
     * @return A copy of this vector with the vector subtracted
     * @see #subtract(Vector)
     * @see #clone()
     */
    Vector subtracted(Vector vector);

    /**
     * Returns a copy of this vector with all the given vector multiplied with it.
     *
     * @param vector The vector to subtract
     * @return A copy of this vector with the vector subtracted
     * @see #multiply(Vector)
     * @see #clone()
     */
    Vector multiplied(Vector vector);

    /**
     * Returns a floored copy of this vector.
     * 
     * @return A floored version of this vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #floor()
     * @see #clone()
     */
    Vector floored();

    /**
     * Returns a ceiled copy of this vector.
     * 
     * @return A ceiled version of this vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #ceil()
     * @see #clone()
     */
    Vector ceiled();

    /**
     * Returns a rounded copy of this vector.
     * 
     * @return A rounded version of this vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #round()
     * @see #clone()
     */
    Vector rounded();




    
    // ------------------------------------------------------------------------------------
    // Conversion methods
    // ------------------------------------------------------------------------------------





    /**
     * Returns a Vector2D representing this vector as good as possible.
     * 
     * @return A Vector2D representing this vector
     * @throws UnsupportedOperationException If this vector does not support 2D conversion
     * @see Vector2D
     */
    Vector2D get2D();

    /**
     * Returns a Vector3D representing this vector as good as possible.
     * 
     * @return A Vector3D representing this vector
     * @throws UnsupportedOperationException If this vector does not support 3D conversion
     * @see Vector3D
     */
    Vector3D get3D();

    IntVector getInt();




    // ------------------------------------------------------------------------------------
    // Statics
    // ------------------------------------------------------------------------------------





    /**
     * Returns a new vector describing the sum of the vectors.
     *
     * @param a The first vector
     * @param b The second vector
     * @return The sum of {@code a} and {@code b}
     */
    static Vector added(Vector a, Vector b) {
        Arguments.checkNull(a, "a");
        Arguments.checkNull(b, "b");

        double[] coordinates = new double[Math.max(a.size(), b.size())];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = a.get(i) + b.get(i);
        return Vector.of(coordinates);
    }

    /**
     * Returns a new vector describing the subtraction of the vectors.
     *
     * @param a The base vector
     * @param b The vector to subtract
     * @return The result of subtracting {@code b} from {@code a}
     */
    static Vector subtracted(Vector a, Vector b) {
        Arguments.checkNull(a, "a");
        Arguments.checkNull(b, "b");

        double[] coordinates = new double[Math.max(a.size(), b.size())];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = a.get(i) - b.get(i);
        return Vector.of(coordinates);
    }

    /**
     * Returns a new vector describing the multiplication of each coordinate
     * of the vectors.
     *
     * @param a The first vector
     * @param b The second vector
     * @return The multiplication of {@code a} and {@code b}
     */
    static Vector multiplied(Vector a, Vector b) {
        Arguments.checkNull(a, "a");
        Arguments.checkNull(b, "b");

        double[] coordinates = new double[Math.max(a.size(), b.size())];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = a.get(i) * b.get(i);
        return Vector.of(coordinates);
    }

    /**
     * Returns the dot product for the two vectors.
     *
     * @param a The first vector
     * @param b The second vector
     * @return The dot product of {@code a} and {@code b}
     */
    static double dot(Vector a, Vector b) {
        Arguments.checkNull(a, "a");
        Arguments.checkNull(b, "b");

        int size = Math.max(a.size(), b.size());
        double result = 0;
        for(int i=0; i<size; i++)
            result += a.get(i) * b.get(i);
        return result;
    }

    /**
     * Returns the vector that starts at the location of {@code from} and points at the location
     * of {@code to}.
     * 
     * @param from The starting location
     * @param to The ending location
     * @return The vector connecting the two vectors
     */
    static Vector between(Vector from, Vector to) {
        return subtracted(to, from);
    }

    /**
     * Returns a new vector describing the average value of both vectors
     * defined by having the average value in each coordinate.
     *
     * @param a The first vector
     * @param b The second vector
     * @return The average of {@code a} and {@code b}
     */
    static Vector average(Vector a, Vector b) {
        return added(a, b).scale(0.5);
    }

    /**
     * Returns the distance between the points that {@code a} and {@code b} point at.
     * This is equivalent to {@code between(a, b).abs()}.
     *
     * @param a The first vector
     * @param b The second vector
     * @return The distance between the two vectors
     */
    static double distance(Vector a, Vector b) {
        return between(a, b).abs();
    }

    /**
     * Returns the squared distance between the points that {@code a} and {@code b}
     * point at. This is equivalent to {@code between(a, b).sqrAbs()}.
     *
     * @param a The first vector
     * @param b The second vector
     * @return The distance between the two vectors
     */
    static double sqrDistance(Vector a, Vector b) {
        return between(a, b).sqrAbs();
    }

    /**
     * Returns an array with the length corresponding to the number of dimensions in
     * the given vector and filled with the coordinates in each dimension.
     *
     * @param vector The vector to express as an array
     * @return An array describing {@code vector}
     */
    static double[] toArray(Vector vector) {
        Arguments.checkNull(vector);

        int size = vector.size();
        double[] array = new double[size];
        for(int i=0; i<size; i++) array[i] = vector.get(i);
        return array;
    }



    static Vector of(IntVector intVector) {
        Arguments.checkNull(intVector);
        return of(intVector.getVector());
    }

    /**
     * Creates a vector with the specified coordinates and a size defined by the number of
     * coordinates specified.
     * <p>If there is an implementation of vector inside this package that is specifically
     * designed for these number of coordinates, an instance of that vector will be returned.
     * 
     * @param coordinates The coordinates of the vector
     * @return A vector with the given coordinates
     */
    static Vector of(double... coordinates) {
        Arguments.checkNull(coordinates);
        if(coordinates.length == 0) return ZeroDimVector.get();
        if(coordinates.length == 1) return new Vector1D(coordinates[0]);
        if(coordinates.length == 2) return new Vector2D(coordinates[0], coordinates[1]);
        if(coordinates.length == 3) return new Vector3D(coordinates[0], coordinates[1], coordinates[2]);
        return new VariableSizeVector(coordinates);
    }

    /**
     * Creates a vector with the specified coordinates and a size defined by the number of
     * coordinates specified.
     * <p>If there is an implementation of vector inside this package that is specifically
     * designed for these number of coordinates, an instance of that vector will be returned.
     *
     * @param x The x coordinate of the vector
     * @param y The y coordinate of the vector
     * @return A vector with the given coordinates
     */
    static Vector2D of(double x, double y) {
        return new Vector2D(x, y);
    }

    /**
     * Creates a vector with the specified coordinates and a size defined by the number of
     * coordinates specified.
     * <p>If there is an implementation of vector inside this package that is specifically
     * designed for these number of coordinates, an instance of that vector will be returned.
     *
     * @param x The x coordinate of the vector
     * @param y The y coordinate of the vector
     * @param z The z coordinate of the vector
     * @return A vector with the given coordinates
     */
    static Vector3D of(double x, double y, double z) {
        return new Vector3D(x, y, z);
    }

    /**
     * Creates a mutable copy of the given vector. The new vector is not necessarily from the same
     * class as the old one, but has as many dimensions and will return {@code true} on an
     * equals test, if the given vector follows the implementation rules for the {@link #equals(Object)}
     * method.
     * <p>If there is an implementation of vector inside this package that is specifically
     * designed for these number of coordinates, an instance of that vector will be returned.
     * 
     * @param copied The vector to copy
     * @return A copy of the given vector
     */
    static Vector of(Vector copied) {
        Arguments.checkNull(copied);
        return of(copied.toArray());
    }

    /**
     * Returns a string representation of this vector in the form
     * of {@code [x|y|z|...]}. All vector implementations should include
     * the string generated by this method in their toString output.
     *
     * @param vector The vector to get the string representation for.
     *               {@code null} will return "null"
     * @return The string representation for the given vector
     */
    static String toString(Vector vector) {
        if(vector == null) return "null";
        int size = vector.size();
        if(size == 0) return "[]";
        StringBuilder string = new StringBuilder().append('[');
        FloatDisplayMode displayMode = Config.getDisplayMode();
        for(int i=0; i<size; i++) {
            displayMode.format(vector.get(i), string);
            if(i < size - 1) string.append('|');
        }
        return string.append(']').toString();
    }

    /**
     * Returns weather the given object is equal to the vector.
     * <p>An object is considered to be equal to a vector if that object ia a subclass of
     * {@link Vector}, they have the same coordinates for every dimension they share and
     * any other dimension of a possibly differently sized vector is {@code 0}.
     *
     * @param obj The object to check for equality
     * @return Weather the object is equal to this vector
     */
    static boolean equals(Vector vector, Object obj) {
        if(vector == obj) return true;
        if(!(obj instanceof Vector)) return false;

        Vector v = (Vector)obj;
        int vectorSize = vector.size(), vSize = v.size();

        if(vectorSize == vSize) {
            for(int i=0; i<vectorSize; i++)
                if(vector.get(i) != v.get(i)) return false;
            return true;
        }
        if(vectorSize < vSize) {
            for(int i=0; i<vectorSize; i++)
                if(vector.get(i) != v.get(i)) return false;
            for(int i=vectorSize; i<vSize; i++)
                if(v.get(i) != 0) return false;
            return true;
        }
        for(int i=0; i<vSize; i++)
            if(vector.get(i) != v.get(i)) return false;
        for(int i=v.size(); i<vectorSize; i++)
            if(vector.get(i) != 0) return false;
        return true;
    }

    /**
     * Returns a hash code value for the vector. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     * <p>
     * The hashcode of a vector is defined as the return of
     * {@link Objects#hashCode(Object)} with its coordinates passed as an array.
     *
     * @return A hash code value for the vector
     */
    static int hashCode(Vector vector) {
        return Arrays.hashCode(vector.toArray());
    }

    static void main(String[] args) {
        DecimalFormat formatter = new DecimalFormat("#");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        Console.info(formatter.format(-0.1));
        Console.info(formatter.format(-0.4));
        Console.info(formatter.format(-0.5));
        Console.info(formatter.format(-0.9));
        Console.info(formatter.format(-1));
    }

    final class Config {
        private static FloatDisplayMode displayMode = FloatDisplayMode.ROUNDED_INDICATED;

        public static void setDisplayMode(FloatDisplayMode displayMode) {
            Config.displayMode = Arguments.checkNull(displayMode);
        }

        public static FloatDisplayMode getDisplayMode() {
            return displayMode;
        }
    }
}
