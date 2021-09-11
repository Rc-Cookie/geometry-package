package com.github.rccookie.geometry;

import com.github.rccookie.data.Saveable;
import com.github.rccookie.util.Arguments;

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
public interface IntVector extends Cloneable, Saveable {



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
    IntVector ZERO = Vectors.immutableVector(IntVector.of());

    /**
     * A constant for a vector that has a value of {@code 1} in every dimension.
     */
    IntVector ONE = new InfiniteIntVector(1);

    /**
     * A constant for a vector that has a value of {@code -1} in every dimension.
     */
    IntVector MINUS_ONE = new InfiniteIntVector(-1);

    /**
     * Unit vector for the X dimension.
     */
    IntVector UNIT_X = Vectors.immutableVector(IntVector.of(1));

    /**
     * Unit vector for the Y dimension.
     */
    IntVector UNIT_Y = Vectors.immutableVector(IntVector.of(0, 1));

    /**
     * Unit vector for the Z dimension.
     */
    IntVector UNIT_Z = Vectors.immutableVector(IntVector.of(0, 0, 1));

    /**
     * Immutable vector equivalent to {@code Vector.of(1)}.
     */
    IntVector RIGHT = UNIT_X;

    /**
     * Immutable vector equivalent to {@code Vector.of(-1)}.
     */
    IntVector LEFT = Vectors.immutableVector(RIGHT.inverted());

    /**
     * Immutable vector equivalent to {@code Vector.of(0, 1)}.
     */
    IntVector DOWN = UNIT_Y;

    /**
     * Immutable vector equivalent to {@code Vector.of(0, -1)}.
     */
    IntVector UP = Vectors.immutableVector(DOWN.inverted());

    /**
     * Immutable vector equivalent to {@code Vector.of(0, 0, 1)}.
     */
    IntVector OUT = UNIT_Z;

    /**
     * Immutable vector equivalent to {@code Vector.of(0, 0, -1)}.
     */
    IntVector IN = Vectors.immutableVector(OUT.inverted());



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
     * @see #setDim(int, int)
     */
    int get(int dimension);

    /**
     * Sets the coordinate in the specified dimension of this vector to the specified value.
     * 
     * @param dimension The dimension to set the coordinate for
     * @param coordinate The new coordinate in the specified dimension of this vector
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #setX(int)
     * @see #setY(int)
     * @see #get(int)
     */
    IntVector setDim(int dimension, int coordinate) throws UnsupportedOperationException, DimensionOutOfBoundsException;

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
    IntVector clone();



    /**
     * Returns a string representation of this vector with the following syntax:
     * <blockquote><pre>"[x|y|...]"</pre></blockquote>
     * For example, {@code new Vector2D(2, 3).toString();} would return
     * <blockquote><pre>"[2.0|3.0]"</pre></blockquote>
     * The method {@link #toString(IntVector)} will generate a string representation
     * for any given vector which should be used in the implementation.
     *
     * @return A string representation of this vector
     * @see #toString(IntVector)
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
    int x();

    /**
     * Returns the y-coordinate of this vector.
     * 
     * @return The y-coordinate of this vector
     * @see #get(int)
     */
    int y();

    /**
     * Returns an array with the length corresponding to the number of dimensions in
     * this vector and filled with the coordinates in each dimension.
     * <p>This array should not be the actual array that may be used to store the
     * dimensions of the vector internally.
     * 
     * @return An array containing the coordinates of this vector
     */
    int[] toArray();

    /**
     * Sets the x-coordinate of this vector to the specified one.
     * 
     * @param x The new x-coordinate for this vector
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @throws DimensionOutOfBoundsException If this vector does not have any dimensions
     * @see #setDim(int, int)
     */
    IntVector setX(int x) throws UnsupportedOperationException, DimensionOutOfBoundsException;

    /**
     * Sets the y-coordinate of this vector to the specified one.
     * 
     * @param y The new y-coordinate for this vector
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @throws DimensionOutOfBoundsException If this vector does not have 2 dimensions
     * @see #setDim(int, int)
     */
    IntVector setY(int y) throws UnsupportedOperationException, DimensionOutOfBoundsException;

    /**
     * Sets all of this vector's coordinates to the coordinates of the given vector. If the given
     * vector has fewer coordinates than this vector, those coordinates will stay unchanged.
     * 
     * @param vector The vector to set this vector's coordinates to
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @throws NullPointerException If the specified vector is {@code null}
     */
    IntVector set(IntVector vector) throws NullPointerException, UnsupportedOperationException;

    /**
     * Set's the coordinates of this vector to the given ones. If the array has more elements
     * than the vector has dimensions, a {@link DimensionOutOfBoundsException} will be thrown.
     *
     * @param coordinates The coordinates to assign
     * @return This vector
     */
    IntVector set(int... coordinates);




    // ------------------------------------------------------------------------------------
    // Property get methods
    // ------------------------------------------------------------------------------------




    /**
     * Returns the dot product of this and the given vector.
     *
     * @param vector The other vector
     * @return The dot product of this and the other vector
     * @throws NullPointerException If the given vector is null
     */
    int dot(IntVector vector) throws NullPointerException;

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
     * @see #scaled(int)
     */
    IntVector scale(int scalar) throws UnsupportedOperationException;

    /**
     * Divides this vector by the given denominator and throws an ArithmeticException
     * if the denominator is {@code 0}.
     *
     * @param denominator The denominator to divide by
     * @return This vector
     * @throws ArithmeticException If the denominator is {@code 0}
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #divided(int)
     */
    IntVector divide(int denominator) throws ArithmeticException, UnsupportedOperationException;

    /**
     * Inverts this vector so that its length stays identical but all of its coordinates
     * are scaled by {@code -1}.
     *
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #inverted()
     */
    IntVector invert() throws UnsupportedOperationException;

    /**
     * Sets all of this vector's coordinates to {@code 0}. Has the same effect as scaling
     * by {@code 0}.
     *
     * @return This vector
     * @throws UnsupportedOperationException If this vector is immutable
     * @see #scale(int)
     */
    IntVector setZero() throws UnsupportedOperationException;




    /**
     * Adds the given vector onto this one by adding each of the other vector's coordinates
     * onto this vector's coordinates. If a given vector has fewer dimensions than this
     * vector, this vector's corresponding coordinate will stay unchanged.
     *
     * @param vector The vector to add
     * @return This vector
     * @see #added(IntVector)
     */
    IntVector add(IntVector vector);

    /**
     * Subtracts the given vector from this one by subtracting each of the other vector's
     * coordinates from this vector's coordinates. If a given vector has fewer dimensions than
     * this vector, this vector's corresponding coordinate will stay unchanged.
     *
     * @param vector The vector to subtract
     * @return This vector
     * @see #subtracted(IntVector)
     */
    IntVector subtract(IntVector vector);

    /**
     * Multiplies each coordinate of this vector with the corresponding coordinate of the other
     * vector.
     *
     * @param vector The vector to multiply with
     * @return This vector
     */
    IntVector multiply(IntVector vector);




    // ------------------------------------------------------------------------------------
    // Cloned-operation methods
    // ------------------------------------------------------------------------------------




    /**
     * Returns a scaled copy of this vector.
     *
     * @param scalar The factor to scale by
     * @return A scaled version of this vector
     * @see #scale(int)
     * @see #clone()
     */
    IntVector scaled(int scalar);

    /**
     * Returns a divided copy of this vector.
     *
     * @param denominator The denominator to divide by
     * @return A divided version of this vector
     * @throws ArithmeticException If the denominator is {@code 0}
     * @see #divide(int)
     * @see #clone()
     */
    IntVector divided(int denominator) throws ArithmeticException;

    /**
     * Returns an inverted copy of this vector.
     *
     * @return An inverted version of this vector
     * @see #invert()
     * @see #clone()
     */
    IntVector inverted();

    /**
     * Returns a copy of this vector and all the given vector added together.
     *
     * @param vector The vector to add
     * @return A copy of this vector with the vector added
     * @see #add(IntVector)
     * @see #clone()
     */
    IntVector added(IntVector vector);

    /**
     * Returns a copy of this vector with all the given vector subtracted from it.
     *
     * @param vector The vector to subtract
     * @return A copy of this vector with the vector subtracted
     * @see #subtract(IntVector)
     * @see #clone()
     */
    IntVector subtracted(IntVector vector);

    /**
     * Returns a copy of this vector with all the given vector multiplied with it.
     *
     * @param vector The vector to subtract
     * @return A copy of this vector with the vector subtracted
     * @see #multiply(IntVector)
     * @see #clone()
     */
    IntVector multiplied(IntVector vector);




    
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
    IntVector2D get2D();

    /**
     * Returns a Vector3D representing this vector as good as possible.
     * 
     * @return A Vector3D representing this vector
     * @throws UnsupportedOperationException If this vector does not support 3D conversion
     * @see Vector3D
     */
    IntVector3D get3D();

    Vector getVector();




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
    static IntVector added(IntVector a, IntVector b) {
        Arguments.checkNull(a, "a");
        Arguments.checkNull(b, "b");

        int[] coordinates = new int[Math.max(a.size(), b.size())];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = a.get(i) + b.get(i);
        return IntVector.of(coordinates);
    }

    /**
     * Returns a new vector describing the subtraction of the vectors.
     *
     * @param a The base vector
     * @param b The vector to subtract
     * @return The result of subtracting {@code b} from {@code a}
     */
    static IntVector subtracted(IntVector a, IntVector b) {
        Arguments.checkNull(a, "a");
        Arguments.checkNull(b, "b");

        int[] coordinates = new int[Math.max(a.size(), b.size())];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = a.get(i) - b.get(i);
        return IntVector.of(coordinates);
    }

    /**
     * Returns a new vector describing the multiplication of each coordinate
     * of the vectors.
     *
     * @param a The first vector
     * @param b The second vector
     * @return The multiplication of {@code a} and {@code b}
     */
    static IntVector multiplied(IntVector a, IntVector b) {
        Arguments.checkNull(a, "a");
        Arguments.checkNull(b, "b");

        int[] coordinates = new int[Math.max(a.size(), b.size())];
        for(int i=0; i<coordinates.length; i++)
            coordinates[i] = a.get(i) * b.get(i);
        return IntVector.of(coordinates);
    }

    /**
     * Returns the dot product for the two vectors.
     *
     * @param a The first vector
     * @param b The second vector
     * @return The dot product of {@code a} and {@code b}
     */
    static int dot(IntVector a, IntVector b) {
        Arguments.checkNull(a, "a");
        Arguments.checkNull(b, "b");

        int size = Math.max(a.size(), b.size());
        int result = 0;
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
    static IntVector between(IntVector from, IntVector to) {
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
    static IntVector average(IntVector a, IntVector b) {
        return added(a, b).divide(2);
    }

    /**
     * Returns an array with the length corresponding to the number of dimensions in
     * the given vector and filled with the coordinates in each dimension.
     *
     * @param vector The vector to express as an array
     * @return An array describing {@code vector}
     */
    static int[] toArray(IntVector vector) {
        Arguments.checkNull(vector);

        int size = vector.size();
        int[] array = new int[size];
        for(int i=0; i<size; i++) array[i] = vector.get(i);
        return array;
    }



    static IntVector of(Vector vector) {
        Arguments.checkNull(vector);
        return of(vector.getInt());
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
    static IntVector of(int... coordinates) {
        Arguments.checkNull(coordinates);
        if(coordinates.length == 0) return ZeroDimIntVector.get();
        if(coordinates.length == 1) return new IntVector1D(coordinates[0]);
        if(coordinates.length == 2) return new IntVector2D(coordinates[0], coordinates[1]);
        if(coordinates.length == 3) return new IntVector3D(coordinates[0], coordinates[1], coordinates[2]);
        return new VariableSizeIntVector(coordinates);
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
    static IntVector2D of(int x, int y) {
        return new IntVector2D(x, y);
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
    static IntVector3D of(int x, int y, int z) {
        return new IntVector3D(x, y, z);
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
    static IntVector of(IntVector copied) {
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
    static String toString(IntVector vector) {
        if(vector == null) return "null";
        int size = vector.size();
        if(size == 0) return "[]";
        StringBuilder string = new StringBuilder().append('[');
        for(int i=0; i<size; i++) {
            string.append(vector.get(i));
            if(i < size - 1) string.append('|');
        }
        return string.append(']').toString();
    }

    /**
     * Returns weather the given object is equal to the vector.
     * <p>An object is considered to be equal to a vector if that object ia a subclass of
     * {@link IntVector}, they have the same coordinates for every dimension they share and
     * any other dimension of a possibly differently sized vector is {@code 0}.
     *
     * @param obj The object to check for equality
     * @return Weather the object is equal to this vector
     */
    static boolean equals(IntVector vector, Object obj) {
        if(vector == obj) return true;
        if(!(obj instanceof IntVector)) return false;

        IntVector v = (IntVector)obj;
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
    static int hashCode(IntVector vector) {
        return Arrays.hashCode(Arguments.checkNull(vector).toArray());
    }
}
