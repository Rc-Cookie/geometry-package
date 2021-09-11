package com.github.rccookie.geometry;

import com.github.rccookie.util.Arguments;

/**
 * A 2-dimensional vector based on {@link Vector}.
 * <p>2-dimensional vectors add the option to measure angles and calculate
 * cross products. With that the class also contains useful methods to use
 * this information to interact with 2D vectors.
 * 
 * @author RcCookie
 */
public class IntVector2D extends AbstractIntVector<IntVector2D> {

    private static final long serialVersionUID = -7930115924262565511L;




    /**
     * Creates a new zero vector.
     */
    public IntVector2D(){
        this(0, 0);
    }

    /**
     * Creates a new vector with the given length parallel to the x-axis.
     *
     * @param x The x length of the vector
     */
    public IntVector2D(int x){
        this(x, 0);
    }

    /**
     * Creates a new vector with the given x and y distances.
     *
     * @param x The x distance of the new vector
     * @param y The y distance of the new vector
     */
    public IntVector2D(int x, int y){
        super(x, y);
    }

    /**
     * Creates a new vector from the given one.
     * The new Vector will be identical with the given one, but is a
     * different object.
     *
     * @param copy The Vector to create the new vector from
     */
    public IntVector2D(AbstractIntVector<?> copy) {
        // 2D conversion is essential for super class to create a 2 long array
        super(copy.get2D());
    }




    @Override
    public IntVector2D clone(){
        return new IntVector2D(this);
    }

    @Override
    public IntVector2D get2D() throws UnsupportedOperationException {
        return this;
    }

    @Override
    public Vector2D getVector() {
        return new Vector2D(x(), y());
    }

    /**
     * Sets this vectors coordinates to the specified ones.
     * 
     * @param x The new x-coordinate for this vector
     * @param y the new y-coordinate for this vector
     * @return This vector
     */
    public IntVector2D set(int x, int y) {
        return setX(x).setY(y);
    }




    /**
     * Returns the cross product of the two vectors.
     * 
     * @param v The first vector
     * @param w The second vector
     * @return The cross product of the two vectors
     */
    public static int cross(IntVector2D v, IntVector2D w) {
        Arguments.checkNull(v, "v");
        Arguments.checkNull(w, "w");
        return v.x() * w.y() - v.y() * w.x();
    }

    /**
     * Returns the area that the two given vectors span.
     * 
     * @param v The first vector
     * @param w The second vector
     * @return The area the two vectors span
     */
    public static double area(IntVector2D v, IntVector2D w) {
        return Math.abs(cross(v, w));
    }
}
