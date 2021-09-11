package com.github.rccookie.geometry;

/**
 * An exception indicating that a dimension of a vector was requested that was either negative
 * or out of range for the vectors dimensions.
 */
public class DimensionOutOfBoundsException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = -7930115924262565512L;

    /**
     * Creates a new DimensionOutOfBoundsException.
     *
     * @param dimension The dimension that was requested
     * @param size      The actual size of the vector
     */
    DimensionOutOfBoundsException(int dimension, int size) {
        super("The dimension " + dimension + " is not available for a vector with " + size + " dimensions");
    }
}
