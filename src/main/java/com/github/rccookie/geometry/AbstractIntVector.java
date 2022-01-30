
package com.github.rccookie.geometry;

import com.github.rccookie.util.Arguments;

/**
 * A common implementation of {@link IntVector}.
 * 
 * @see IntVector2D
 * @see IntVector3D
 */

public abstract class AbstractIntVector<V extends AbstractIntVector<? extends V>> implements IntVector {



    // ------------------------------------------------------------------------------------
    // Constants
    // ------------------------------------------------------------------------------------



    private static final long serialVersionUID = -3264753193349068427L;

    @SuppressWarnings("unused")
    static final String SAVE_DIR = "geometry\\intVectors";




    // ------------------------------------------------------------------------------------
    // Instance fields
    // ------------------------------------------------------------------------------------




    private final int[] coordinates;

    private String saveName = null;




    // ------------------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------------------




    protected AbstractIntVector(IntVector copy) {
        this(copy.toArray());
    }

    protected AbstractIntVector(int... initialCoordinates) {
        Arguments.checkNull(initialCoordinates);
        System.arraycopy(
            initialCoordinates, 0,
            coordinates = new int[initialCoordinates.length], 0,
            initialCoordinates.length
        );
    }





    // ------------------------------------------------------------------------------------
    // Base methods
    // ------------------------------------------------------------------------------------



    /**
     * @param dimension The dimension to get the coordinate for
     */
    @Override
    public int get(int dimension) {
        if(dimension < 0) throw new DimensionOutOfBoundsException(dimension, size());
        if(dimension >= coordinates.length) return 0;
        return coordinates[dimension];
    }

    /**
     * @param dimension The dimension to set the coordinate for
     * @param coordinate The new coordinate in the specified dimension of this vector
     */
    @Override
    public V setDim(int dimension, int coordinate) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        checkDim(dimension);
        coordinates[dimension] = coordinate;
        return getThis();
    }

    @Override
    public int size() {
        return coordinates.length;
    }

    @Override
    public abstract V clone();

    @Override
    public String toString() {
        return IntVector.toString(this);
    }

    /**
     * @param obj The object to check for equality
     */
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
        return IntVector.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return IntVector.hashCode(this);
    }




    // ------------------------------------------------------------------------------------
    // Get / set methods
    // ------------------------------------------------------------------------------------




    @Override
    public int x() throws DimensionOutOfBoundsException {
        return get(X);
    }

    @Override
    public int y() throws DimensionOutOfBoundsException {
        return get(Y);
    }

    @Override
    public int[] toArray() {
        return IntVector.toArray(this);
    }

    /**
     * @param x The new x-coordinate for this vector
     */
    @Override
    public V setX(int x) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        return setDim(X, x);
    }

    /**
     * @param y The new y-coordinate for this vector
     */
    @Override
    public V setY(int y) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        return setDim(Y, y);
    }

    /**
     * @param vector The vector to set this vector's coordinates to
     */
    @Override
    public V set(IntVector vector) throws NullPointerException {
        Arguments.checkNull(vector);
        final int size = size();
        for(int i=0; i<size; i++) setDim(i, vector.get(i));
        return getThis();
    }

    @Override
    public V set(int... coordinates) {
        Arguments.checkNull(coordinates);
        final int size = size();
        for(int i=0; i<size; i++) setDim(i, coordinates[i]);
        return getThis();
    }




    // ------------------------------------------------------------------------------------
    // Property get methods
    // ------------------------------------------------------------------------------------




    /**
     * @param vector The other vector
     */
    @Override
    public int dot(IntVector vector) throws NullPointerException {
        return IntVector.dot(this, vector);
    }

    @Override
    public boolean isZero() {
        final int size = size();
        for(int i=0; i<size; i++)
            if(get(i) != 0) return false;
        return true;
    }




    
    // ------------------------------------------------------------------------------------
    // IntVector operation methods
    // ------------------------------------------------------------------------------------




    /**
     * @param scalar The factor to scale by
     */
    @Override
    public V scale(int scalar) throws UnsupportedOperationException {
        if(scalar == 1) return getThis();
        final int size = size();
        for(int i=0; i<size; i++) setDim(i, get(i) * scalar);
        return getThis();
    }

    /**
     * @param denominator The denominator to divide by
     */
    @Override
    public V divide(int denominator) throws ArithmeticException, UnsupportedOperationException {
        if(denominator == 0) throw new ArithmeticException("Division by 0");
        if(denominator == 1) return getThis();
        final int size = size();
        for(int i=0; i<size; i++) setDim(i, get(i) / denominator);
        return getThis();
    }

    @Override
    public V invert() throws UnsupportedOperationException {
        return scale(-1);
    }

    @Override
    public V setZero() throws UnsupportedOperationException {
        return scale(0);
    }




    /**
     * @param vector The vector to add
     */
    @Override
    public V add(IntVector vector) {
        Arguments.checkNull(vector);
        final int size = size();
        for(int i=0; i<size; i++)
            setDim(i, get(i) + vector.get(i));
        return getThis();
    }

    /**
     * @param vector The vector to subtract
     */
    @Override
    public V subtract(IntVector vector) {
        Arguments.checkNull(vector);
        final int size = size();
        for(int i=0; i<size; i++)
            setDim(i, get(i) - vector.get(i));
        return getThis();
    }

    @Override
    public V multiply(IntVector vector) {
        Arguments.checkNull(vector);
        final int size = size();
        for(int i=0; i<size; i++)
            setDim(i, get(i) * vector.get(i));
        return getThis();
    }




    // ------------------------------------------------------------------------------------
    // Cloned-operation methods
    // ------------------------------------------------------------------------------------




    /**
     * @param scalar The factor to scale by
     */
    @SuppressWarnings("unchecked")
    @Override
    public V scaled(int scalar) {
        return clone().scale(scalar);
    }

    /**
     * @param denominator The denominator to divide by
     */
    @SuppressWarnings("unchecked")
    @Override
    public V divided(int denominator) throws ArithmeticException {
        return clone().divide(denominator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public V inverted() {
        return clone().invert();
    }

    /**
     * @param vector The vector to add
     */
    @Override
    public IntVector added(IntVector vector) {
        return IntVector.added(this, vector);
    }

    /**
     * Returns a copy of this vector and all the given vector added together.
     *
     * @param vector The vector to add
     * @return A copy of this vector with the vector added
     * @see #add(IntVector)
     * @see #clone()
     */
    @SuppressWarnings("unchecked")
    public V added(V vector) {
        return clone().add(vector);
    }

    /**
     * @param vector The vector to subtract
     */
    @Override
    public IntVector subtracted(IntVector vector) {
        return IntVector.subtracted(this, vector);
    }

    /**
     * Returns a copy of this vector with all the given vector subtracted from it.
     *
     * @param vector The vector to subtract
     * @return A copy of this vector with the vector subtracted
     * @see #subtract(IntVector)
     * @see #clone()
     */
    @SuppressWarnings("unchecked")
    public V subtracted(V vector) {
        return clone().subtract(vector);
    }

    @Override
    public IntVector multiplied(IntVector vector) {
        return IntVector.multiplied(this, vector);
    }

    /**
     * Returns a copy of this vector with all the given vector multiplied with it.
     *
     * @param vector The vector to subtract
     * @return A copy of this vector with the vector subtracted
     * @see #multiply(IntVector)
     * @see #clone()
     */
    @SuppressWarnings("unchecked")
    public V multiplied(V vector) {
        return clone().multiply(vector);
    }




    
    // ------------------------------------------------------------------------------------
    // Conversion methods
    // ------------------------------------------------------------------------------------





    @Override
    public IntVector2D get2D() throws UnsupportedOperationException {
        return new IntVector2D(get(X), get(Y));
    }

    @Override
    public IntVector3D get3D() throws UnsupportedOperationException {
        return new IntVector3D(get(X), get(Y), get(Z));
    }

    @Override
    public Vector getVector() {
        double[] doubleCoordinates = new double[coordinates.length];
        for (int i = 0; i < coordinates.length; i++)
            doubleCoordinates[i] = coordinates[i];
        return Vector.of(doubleCoordinates);
    }



    
    // ------------------------------------------------------------------------------------
    // Internals
    // ------------------------------------------------------------------------------------



    private void checkDim(int dimension) {
        final int size = size();
        if(dimension < 0 || dimension >= size) throw new DimensionOutOfBoundsException(dimension, size);
    }

    @SuppressWarnings("unchecked")
    private V getThis() {
        return (V)this;
    }
}
