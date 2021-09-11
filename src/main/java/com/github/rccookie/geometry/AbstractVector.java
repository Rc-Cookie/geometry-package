package com.github.rccookie.geometry;

import com.github.rccookie.util.Arguments;

/**
 * A common implementation of {@link Vector}.
 * 
 * @see Vector2D
 * @see Vector3D
 */
@SuppressWarnings("unchecked")
public abstract class AbstractVector<V extends AbstractVector<? extends V>> implements Vector {



    // ------------------------------------------------------------------------------------
    // Constants
    // ------------------------------------------------------------------------------------



    private static final long serialVersionUID = -3264753193349068427L;

    @SuppressWarnings("unused")
    static final String SAVE_DIR = "geometry\\vectors";




    // ------------------------------------------------------------------------------------
    // Instance fields
    // ------------------------------------------------------------------------------------




    private final double[] coordinates;

    private String saveName = null;




    // ------------------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------------------




    protected AbstractVector(Vector copy) {
        this(copy.toArray());
    }

    protected AbstractVector(double... initialCoordinates) {
        Arguments.checkNull(initialCoordinates);
        System.arraycopy(
            initialCoordinates, 0,
            coordinates = new double[initialCoordinates.length], 0,
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
    public double get(int dimension) {
        if(dimension < 0) throw new DimensionOutOfBoundsException(dimension, size());
        if(dimension >= coordinates.length) return 0;
        return coordinates[dimension];
    }

    /**
     * @param dimension The dimension to set the coordinate for
     * @param coordinate The new coordinate in the specified dimension of this vector
     */
    @Override
    public V setDim(int dimension, double coordinate) throws UnsupportedOperationException, DimensionOutOfBoundsException {
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
        return Vector.toString(this);
    }

    /**
     * @param obj The object to check for equality
     */
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
        return Vector.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return Vector.hashCode(this);
    }




    // ------------------------------------------------------------------------------------
    // Get / set methods
    // ------------------------------------------------------------------------------------




    @Override
    public double x() throws DimensionOutOfBoundsException {
        return get(X);
    }

    @Override
    public double y() throws DimensionOutOfBoundsException {
        return get(Y);
    }

    @Override
    public double[] toArray() {
        return Vector.toArray(this);
    }

    /**
     * @param x The new x-coordinate for this vector
     */
    @Override
    public V setX(double x) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        return setDim(X, x);
    }

    /**
     * @param y The new y-coordinate for this vector
     */
    @Override
    public V setY(double y) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        return setDim(Y, y);
    }

    /**
     * @param vector The vector to set this vector's coordinates to
     */
    @Override
    public V set(Vector vector) throws NullPointerException {
        Arguments.checkNull(vector);
        final int size = size();
        for(int i=0; i<size; i++) setDim(i, vector.get(i));
        return getThis();
    }

    @Override
    public V set(double... coordinates) {
        Arguments.checkNull(coordinates);
        final int size = size();
        for(int i=0; i<size; i++) setDim(i, coordinates[i]);
        return getThis();
    }




    // ------------------------------------------------------------------------------------
    // Property get methods
    // ------------------------------------------------------------------------------------




    @Override
    public double abs() {
        return Math.sqrt(sqrAbs());
    }

    @Override
    public double sqrAbs() {
        return dot(getThis());
    }

    @Override
    public abstract double angle();

    /**
     * @param vector The other vector
     */
    @Override
    public double dot(Vector vector) throws NullPointerException {
        return Vector.dot(this, vector);
    }

    @Override
    public boolean isZero() {
        final int size = size();
        for(int i=0; i<size; i++) if(get(i) != 0) return false;
        return true;
    }




    
    // ------------------------------------------------------------------------------------
    // Vector operation methods
    // ------------------------------------------------------------------------------------




    /**
     * @param scalar The factor to scale by
     */
    @Override
    public V scale(double scalar) throws UnsupportedOperationException {
        if(scalar == 1) return getThis();
        final int size = size();
        for(int i=0; i<size; i++) setDim(i, get(i) * scalar);
        return getThis();
    }

    /**
     * @param denominator The denominator to divide by
     */
    @Override
    public V divide(double denominator) throws ArithmeticException, UnsupportedOperationException {
        if(denominator == 0) throw new ArithmeticException("Division by 0");
        return scale(1 / denominator);
    }

    @Override
    public V invert() throws UnsupportedOperationException {
        return scale(-1);
    }

    @Override
    public V norm() throws UnsupportedOperationException {
        if(isZero()) return getThis();
        double abs = abs();
        if(abs == 0) return getThis(); // Even though not every coordinate is 0 the calculated distance may be
        return divide(abs);
    }

    @Override
    public V setZero() throws UnsupportedOperationException {
        return scale(0);
    }




    /**
     * @param vector The vector to add
     */
    @Override
    public V add(Vector vector) {
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
    public V subtract(Vector vector) {
        Arguments.checkNull(vector);
        final int size = size();
        for(int i=0; i<size; i++)
            setDim(i, get(i) - vector.get(i));
        return getThis();
    }

    @Override
    public V multiply(Vector vector) {
        Arguments.checkNull(vector);
        final int size = size();
        for(int i=0; i<size; i++)
            setDim(i, get(i) * vector.get(i));
        return getThis();
    }

    @Override
    public V floor() throws UnsupportedOperationException {
        final int size = size();
        for(int i=0; i<size; i++)
            setDim(i, Math.floor(get(i)));
        return getThis();
    }

    @Override
    public V ceil() throws UnsupportedOperationException {
        final int size = size();
        for(int i=0; i<size; i++)
            setDim(i, Math.ceil(get(i)));
        return getThis();
    }

    @Override
    public V round() throws UnsupportedOperationException {
        final int size = size();
        for(int i=0; i<size; i++)
            setDim(i, Math.round(get(i)));
        return getThis();
    }




    // ------------------------------------------------------------------------------------
    // Cloned-operation methods
    // ------------------------------------------------------------------------------------




    /**
     * @param scalar The factor to scale by
     */
    @Override
    public V scaled(double scalar) {
        return clone().scale(scalar);
    }

    /**
     * @param denominator The denominator to divide by
     */
    @Override
    public V divided(double denominator) throws ArithmeticException {
        return clone().divide(denominator);
    }

    @Override
    public V inverted() {
        return clone().invert();
    }

    @Override
    public V normed() {
        return clone().norm();
    }

    /**
     * @param vector The vector to add
     */
    @Override
    public Vector added(Vector vector) {
        return Vector.added(this, vector);
    }

    /**
     * Returns a copy of this vector and all the given vector added together.
     *
     * @param vector The vector to add
     * @return A copy of this vector with the vector added
     * @see #add(Vector)
     * @see #clone()
     */
    public V added(V vector) {
        return clone().add(vector);
    }

    /**
     * @param vector The vector to subtract
     */
    @Override
    public Vector subtracted(Vector vector) {
        return Vector.subtracted(this, vector);
    }

    /**
     * Returns a copy of this vector with all the given vector subtracted from it.
     *
     * @param vector The vector to subtract
     * @return A copy of this vector with the vector subtracted
     * @see #subtract(Vector)
     * @see #clone()
     */
    public V subtracted(V vector) {
        return clone().subtract(vector);
    }

    @Override
    public Vector multiplied(Vector vector) {
        return Vector.multiplied(this, vector);
    }

    /**
     * Returns a copy of this vector with all the given vector multiplied with it.
     *
     * @param vector The vector to subtract
     * @return A copy of this vector with the vector subtracted
     * @see #multiply(Vector)
     * @see #clone()
     */
    public V multiplied(V vector) {
        return clone().multiply(vector);
    }

    @Override
    public V floored() {
        return clone().floor();
    }

    @Override
    public V ceiled() {
        return clone().ceil();
    }

    @Override
    public V rounded() {
        return clone().round();
    }




    
    // ------------------------------------------------------------------------------------
    // Conversion methods
    // ------------------------------------------------------------------------------------





    @Override
    public Vector2D get2D() throws UnsupportedOperationException {
        return new Vector2D(get(X), get(Y));
    }

    @Override
    public Vector3D get3D() throws UnsupportedOperationException {
        return new Vector3D(get(X), get(Y), get(Z));
    }

    @Override
    public IntVector getInt() {
        int[] intCoordinates = new int[coordinates.length];
        for (int i = 0; i < coordinates.length; i++)
            intCoordinates[i] = (int) Math.round(coordinates[i]);
        return IntVector.of(intCoordinates);
    }




    // ------------------------------------------------------------------------------------
    // Saving methods
    // ------------------------------------------------------------------------------------



    
    @Override
    public String getSaveName() {
        return saveName != null ? saveName : getClass().getSimpleName().toLowerCase() + hashCode();
    }

    /**
     * @param name The new save name
     */
    @Override
    public void setSaveName(String name) {
        saveName = name;
    }



    
    // ------------------------------------------------------------------------------------
    // Internals
    // ------------------------------------------------------------------------------------



    private void checkDim(int dimension) {
        final int size = size();
        if(dimension < 0 || dimension >= size) throw new DimensionOutOfBoundsException(dimension, size);
    }

    private V getThis() {
        return (V)this;
    }
}
