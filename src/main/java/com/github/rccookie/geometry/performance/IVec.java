package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonSerializable;
import com.github.rccookie.util.Cloneable;

/**
 * A performance oriented implementation of a vector by
 * dropping some precision and features, especially a generic
 * class structure and immutability.
 *
 * @param <V> The type of the implementing class
 */
public interface IVec<V extends IVec<V, FV>, FV extends Vec<FV, V>> extends Cloneable<V>, JsonSerializable {

    /**
     * Creates and returns copy of this vector.
     *
     * @return A new, equal vector
     */
    @Override
    V clone();

    // ------------------------------------------------------

    /**
     * Returns the number of dimensions of this vector.
     *
     * @return The number of components in this vector.
     */
    int size();

    /**
     * Returns the component in the given dimension.
     *
     * @param d The dimension of the component to get
     * @return The component in that dimension
     * @deprecated Getters and setters create unnecessary overhead,
     *             try accessing the field of the implementation
     *             directly instead
     */
    @Deprecated
    int getDim(int d);

    /**
     * Calculates the squared length of this vector by not taking
     * the square root in the calculation. This hugely increases
     * performance. This method should be used if the length of
     * the vector is only needed to compare to some value - it's
     * much cheaper to square the other value than to take the
     * square root of this value.
     *
     * @return The squared length of this vector
     */
    int sqrAbs();

    /**
     * Calculates the dot product of this vector and the given one.
     *
     * @param v The vector to calculate the dot product with
     * @return The dot product of this and the given vector
     */
    int dot(V v);

    /**
     * Determines whether this vector is a zero vector, meaning all
     * of its components have the value {@code 0}.
     *
     * @return Whether this vector is a zero vector
     */
    boolean isZero();

    /**
     * Creates an array with the components of this vector.
     *
     * @return An array representation of this vector
     */
    int[] toArray();

    // ------------------------------------------------------

    /**
     * Sets each component of this vector to the given value.
     *
     * @param v The value to assign
     * @return This vector
     */
    V set(int v);

    /**
     * Sets this vector to be equal to the given vector.
     *
     * @param v The vector to set this vector to
     * @return This vector
     */
    V set(V v);

    /**
     * Sets this vector's components to the corresponding values
     * from the given array.
     *
     * @param a The array describing the values to set
     * @return This vector
     */
    V set(int[] a);

    /**
     * Sets the component in the given dimension.
     *
     * @param d The dimension of the component to set
     * @param v The value for the component
     * @return This vector
     * @deprecated Getters and setters create unnecessary overhead,
     *             try accessing the field of the implementation
     *             directly instead
     */
    @Deprecated
    V setDim(int d, int v);

    /**
     * Sets all components of this vector to {@code 0}.
     *
     * @return This vector
     */
    V setZero();

    // ------------------------------------------------------

    /**
     * Scales each component of this vector by the given factor.
     *
     * @param f The factor to scale by
     * @return This vector
     */
    V scale(int f);

    /**
     * Divides each component of this vector by the given
     * denominator. {@link #scale(int)} should be preferred over
     * this method if the factor is also available as division takes
     * considerably longer than multiplication.
     *
     * @param d The denominator to divide by
     * @return This vector
     */
    V divide(int d);

    /**
     * Negates each component of this vector.
     *
     * @return This vector
     */
    V negate();

    // ------------------------------------------------------

    /**
     * Adds the given vector onto this vector by adding each component
     * of the given vector to the corresponding component in this
     * vector.
     *
     * @param v The vector to add
     * @return This vector
     */
    V add(V v);

    /**
     * Subtracts the given vector from this vector by subtracting each
     * component of the given vector from the corresponding component
     * in this vector.
     *
     * @param v The vector to subtract
     * @return This vector
     */
    V subtract(V v);

    /**
     * Multiplies this vector by the given vector by multiplying each
     * component of this vector by the corresponding component in the
     * given vector.
     *
     * @param v The vector to multiply by
     * @return This vector
     */
    V multiply(V v);

    // ------------------------------------------------------

    /**
     * Returns a copy of this vector where each component is scaled
     * by the given factor.
     *
     * @param f The factor to scale by
     * @return The scaled vector
     */
    V scaled(int f);

    /**
     * Returns a copy of this vector where each component is scaled
     * by the given factor.
     *
     * @param f The factor to scale by
     * @return The scaled vector
     */
    FV scaled(float f);

    /**
     * Returns a copy of this vector where each component is divided
     * by the given denominator. {@link #scaled(int)} should be
     * preferred over this method if the factor is also available as
     * division takes considerably longer than multiplication.
     *
     * @param d The denominator to divide by
     * @return The divided vector
     */
    V divided(int d);

    /**
     * Returns a copy of this vector where each component is divided
     * by the given denominator. {@link #scaled(int)} should be
     * preferred over this method if the factor is also available as
     * division takes considerably longer than multiplication.
     *
     * @param d The denominator to divide by
     * @return The divided vector
     */
    FV divided(float d);

    /**
     * Returns a copy of this vector where each component is
     * negated.
     *
     * @return The inverted vector
     */
    V negated();

    // ------------------------------------------------------

    /**
     * Returns a vector representing the sum of this and the given
     * vector.
     *
     * @param v The vector to add
     * @return The sum of the vectors
     */
    V added(V v);

    /**
     * Returns a vector representing the subtraction of this and
     * the given vector.
     *
     * @param v The vector to subtracted
     * @return The subtraction of the vectors
     */
    V subtracted(V v);

    /**
     * Returns a vector representing the product of this and the given
     * vector.
     *
     * @param v The vector to multiply with
     * @return The product of the vectors
     */
    V multiplied(V v);

    // ------------------------------------------------------

    /**
     * Returns a {@link IVec2} representing this vector as good as
     * possible. Any additional components of this vector will be
     * ignored, and if components are not defined in this vector that
     * are required they will be set to {@code 0}.
     * <p>If this vector is already a {@link IVec2} this will return
     * itself and <b>not</b> create a copy!</p>
     *
     * @return A 2D representation of this vector
     */
    IVec2 to2();

    /**
     * Returns a {@link IVec3} representing this vector as good as
     * possible. Any additional components of this vector will be
     * ignored, and if components are not defined in this vector that
     * are required they will be set to {@code 0}.
     * <p>If this vector is already a {@link IVec3} this will return
     * itself and <b>not</b> create a copy!</p>
     *
     * @return A 2D representation of this vector
     */
    IVec3 to3();

    /**
     * Returns a new vector that represents this int vector. Subclasses
     * are encouraged to give more specific information about the type
     * of vector they return.
     *
     * @return A float based version of this vector
     */
    FV toF();
}
