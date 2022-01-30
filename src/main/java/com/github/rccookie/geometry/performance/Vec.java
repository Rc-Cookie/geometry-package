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
public interface Vec<V extends Vec<V, IV>, IV extends IVec<IV, V>> extends Cloneable<V>, JsonSerializable {

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
    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    float getDim(int d);

    /**
     * Calculates the length of this vector. Performance-intensive,
     * use with care and consider using {@link #sqrAbs()} instead.
     *
     * @return The length of this vector
     */
    float abs();

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
    float sqrAbs();

    /**
     * Calculates the angle to the x-axis vector. Depending on the
     * implementation the angle may be signed or not.
     *
     * @return The angle between this vector and the x-axis
     */
    float angle();

    /**
     * Calculates the angle to the given vector. Depending on the
     * implementation the angle may be signed or not.
     *
     * @return The angle between this vector and the given one
     */
    float angle(V v);

    /**
     * Calculates the dot product of this vector and the given one.
     *
     * @param v The vector to calculate the dot product with
     * @return The dot product of this and the given vector
     */
    float dot(V v);

    /**
     * Determines whether this vector is a zero vector, meaning all
     * of its components have the value {@code 0}.
     *
     * @return Whether this vector is a zero vector
     */
    boolean isZero();

    /**
     * Determines whether this vector is valid, meaning that all
     * of its components are finite and not NaNs.
     *
     * @return Whether this vector is valid
     */
    boolean isValid();

    /**
     * Creates an array with the components of this vector.
     *
     * @return An array representation of this vector
     */
    float[] toArray();

    // ------------------------------------------------------

    /**
     * Sets each component of this vector to the given value.
     *
     * @param v The value to assign
     * @return This vector
     */
    V set(float v);

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
    V set(float[] a);

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
    V setDim(int d, float v);

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
    V scale(float f);

    /**
     * Divides each component of this vector by the given
     * denominator. {@link #scale(float)} should be preferred over
     * this method if the factor is also available as division takes
     * considerably longer than multiplication.
     *
     * @param d The denominator to divide by
     * @return This vector
     */
    V divide(float d);

    /**
     * Negates each component of this vector.
     *
     * @return This vector
     */
    V negate();

    /**
     * Normalizes this vector so that it's length is equal to {@code 0}
     * but the direction stays unchanged. This method should be used
     * with care as normalizing requires the length of the vector to be
     * calculated which is quite performance intensive due to the use
     * of a square root.
     *
     * @return This vector
     */
    V norm();

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

    /**
     * Lerps this vector towards the given target by the specified
     * amount.
     *
     * @param t The target to lerp towards
     * @param a The process of the lerp; {@code 0} means exactly this
     *          vector, {@code 1} means exactly the target vector
     * @return This vector
     */
    V lerp(V t, float a);

    /**
     * Projects this vector onto the given vector.
     *
     * @param o The vector to project this vector onto
     * @return This vector
     */
    V project(V o);

    /**
     * Applies the given array as matrix to this vector, where each
     * element in the array describes a row in the matrix.
     *
     * @param m The matrix to apply
     * @return This vector
     */
    V apply(V[] m);

    /**
     * Applies the given matrix to this vector.
     *
     * @param m The matrix to apply
     * @return This vector
     */
    V apply(Mat<V,V> m);

    // ------------------------------------------------------

    /**
     * Returns a copy of this vector where each component is scaled
     * by the given factor.
     *
     * @param f The factor to scale by
     * @return The scaled vector
     */
    V scaled(float f);

    /**
     * Returns a copy of this vector where each component is divided
     * by the given denominator. {@link #scaled(float)} should be
     * preferred over this method if the factor is also available as
     * division takes considerably longer than multiplication.
     *
     * @param d The denominator to divide by
     * @return The divided vector
     */
    V divided(float d);

    /**
     * Returns a copy of this vector where each component is
     * negated.
     *
     * @return The inverted vector
     */
    V negated();

    /**
     * Returns a normalized copy of this vector that has a length
     * of {@code 1} in the direction of this vector. This method
     * should be used with care as normalizing requires the length
     * of the vector to be calculated which is quite performance
     * intensive due to the use of a square root.
     *
     * @return The normalized vector
     */
    V normed();

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

    /**
     * Returns a copy of this vector lerped by the given amount towards
     * the specified target.
     *
     * @param t The target to lerp towards
     * @param a The process of the lerp; {@code 0} means exactly this
     *          vector, {@code 1} means exactly the target vector
     * @return The lerped vector
     */
    V lerped(V t, float a);

    /**
     * Returns a copy of this vector projected onto the given vector.
     *
     * @param o The vector to project onto
     * @return The projected vector
     */
    V projected(V o);

    /**
     * Returns a copy of this vector with the given matrix applied.
     *
     * @param m The matrix to apply
     * @return The vector with the matrix applied
     */
    V applied(V[] m);

    /**
     * Returns a copy of this vector with the given matrix applied.
     *
     * @param m The matrix to apply
     * @return The vector with the matrix applied
     */
    V applied(Mat<V,V> m);

    /**
     * Applies the matrix and returns the result vector.
     *
     * @param m The matrix to apply
     * @param <W> The type of vector the matrix produces
     * @return THe result of the matrix transform
     */
    <W extends Vec<W,?>> W transformed(Mat<V,W> m);

    // ------------------------------------------------------

    /**
     * Returns a {@link Vec2} representing this vector as good as
     * possible. Any additional components of this vector will be
     * ignored, and if components are not defined in this vector that
     * are required they will be set to {@code 0}.
     * <p>If this vector is already a {@link Vec2} this will return
     * itself and <b>not</b> create a copy!</p>
     *
     * @return A 2D representation of this vector
     */
    Vec2 to2();

    /**
     * Returns a {@link Vec3} representing this vector as good as
     * possible. Any additional components of this vector will be
     * ignored, and if components are not defined in this vector that
     * are required they will be set to {@code 0}.
     * <p>If this vector is already a {@link Vec3} this will return
     * itself and <b>not</b> create a copy!</p>
     *
     * @return A 2D representation of this vector
     */
    Vec3 to3();

    /**
     * Returns an {@link IVec} representation with properly rounded
     * components. Subclasses are encouraged to give more specific
     * information about the type of vector they return.
     *
     * @return An int representation of this vector
     */
    IV toI();
}
