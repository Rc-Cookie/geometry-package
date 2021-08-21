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
public class Vector2D extends AbstractVector<Vector2D> {

    private static final long serialVersionUID = -7930115924262565511L;



    /**
     * A vector with the length zero.
     */
    public static final Vector2D ZERO_VECTOR = new Vector2D();

    /**
     * A vector pointing along the x-axis with the length of 1. Representation
     * of the x-axis.
     */
    public static final Vector2D UNIT_VECTOR_X = new Vector2D(1);

    /**
     * A vector pointing along the y-axis with the length of 1. Representation
     * of the y-axis.
     */
    public static final Vector2D UNIT_VECTOR_Y = new Vector2D(0, 1);




    /**
     * Creates a new zero vector.
     */
    public Vector2D(){
        this(0, 0);
    }

    /**
     * Creates a new vector with the given length parallel to the x-axis.
     * 
     * @param x The x length of the vector
     */
    public Vector2D(double x){
        this(x, 0);
    }

    /**
     * Creates a new vector with the given x and y distances.
     * 
     * @param x The x distance of the new vector
     * @param y The y distance of the new vector
     */
    public Vector2D(double x, double y){
        super(x, y);
    }

    /**
     * Creates a new vector from the given one.
     * The new Vector will be identical with the given one, but is a
     * different object.
     * 
     * @param copy The Vector to create the new vector from
     */
    public Vector2D(Vector copy) {
        // 2D conversion is essential for super class to create a 2 long array
        super(copy.get2D());
    }










    @Override
    public double angle() {
        return Math.toDegrees(Math.atan2(y(), x()));
    }

    @Override
    public Vector2D clone(){
        return new Vector2D(this);
    }

    @Override
    public Vector2D get2D() throws UnsupportedOperationException {
        return this;
    }






    /**
     * Sets this vectors coordinates to the specified ones.
     * 
     * @param x The new x-coordinate for this vector
     * @param y the new y-coordinate for this vector
     * @return This vector
     */
    public Vector2D set(double x, double y) {
        return setX(x).setY(y);
    }








    /**
     * Returns the direction-sensitive angle to the given vector, in degrees.
     * 
     * @param vector The vector to get the angle to
     * @return The angle between the vectors, from -180° to 180°
     */
    public double angleTo(Vector2D vector) {
        return Math.toDegrees(Math.atan2(cross(this, vector), Vector.dot(this, vector)));
    }

    /**
     * Rotates this vector the specified amount of degrees while keeping its initial length.
     * 
     * @param angle The angle to rotate the vector, in degrees
     * @return This vector
     */
    public Vector2D rotate(double angle) {
        if(angle == 0) return this;
        double radians = Math.toRadians(angle);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        double x = x(), y = y();
        return set(
            x * cos - y * sin,
            x * sin + y * cos
        );
    }




    /**
     * Returns a rotated copy of this vector.
     * 
     * @param angle The angle to rotate the vector, in degrees
     * @return A rotated version of this vector
     * @see #rotate(double)
     * @see #clone()
     */
    public Vector2D rotated(double angle) {
        return clone().rotate(angle);
    }








    /**
     * Creates a new vector with the given angle and length in that
     * direction. If the length is negative, the vector will point in
     * the opposite direction.
     * 
     * @param angle The angle of the new Vector
     * @param length The length of the new vector
     * @deprecated Use {@link #angled(double, double)} instead.
     */
    @Deprecated(forRemoval = true)
    public static Vector2D angledVector(double angle, double length){
        return angled(angle, length);
    }

    /**
     * Creates a new vector with the given angle and length in that
     * direction. If the length is negative, the vector will point in
     * the opposite direction.
     *
     * @param angle The angle of the new vector
     * @param length The length of the new vector
     */
    public static Vector2D angled(double angle, double length) {
        return angled(angle).scale(length);
    }

    /**
     * Creates a new vector with the given angle and a length of {@code 1} in that
     * direction.
     *
     * @param angle The angle of the new vector
     */
    public static Vector2D angled(double angle) {
        double radians = Math.toRadians(angle);
        return new Vector2D(Math.cos(radians), Math.sin(radians));
    }

    /**
     * Returns the cross product of the two vectors.
     * 
     * @param v The first vector
     * @param w The second vector
     * @return The cross product of the two vectors
     */
    public static double cross(Vector2D v, Vector2D w) {
        Arguments.checkNull(v, "v");
        Arguments.checkNull(w, "w");
        return v.x() * w.y() - v.y() * w.x();
    }

    /**
     * Returns the angle between the two given vectors. If any of the
     * given vectors is a zero vector, the result will be 0.
     * 
     * @param v The first vector
     * @param w The second vector
     * @return The angle between the vectors
     */
    public static double angleBetween(Vector2D v, Vector2D w) {
        Arguments.checkNull(v, "v");
        Arguments.checkNull(w, "w");
        if(v.isZero() || w.isZero()) return 0;
        double result = w.angle() - v.angle();
        result += result > -180 ? (result <= 180 ? 0 : -360) : 360;
        return result;
    }

    /**
     * Returns a new vector that represents the reflected version of
     * {@code reflect} reflecting of {@code base}.
     * 
     * @param wall The vector to reflect from
     * @param vector The vector that reflects from{@code base}
     * @return A new vector representing the reflection of {@code reflect} form {@code base}
     */
    public static Vector2D reflect(Vector2D wall, Vector2D vector){
        Arguments.checkNull(wall, "wall");
        Arguments.checkNull(vector, "vector");
        return angled(wall.angle() + vector.angleTo(wall), vector.abs());
    }

    /**
     * Returns a vector representing the mirror over the given vector at the given mirror vector.
     * 
     * @param vector The vector to mirror
     * @param mirror The vector that represents the mirror line
     * @return The mirrored version of the vector
     */
    public static Vector2D mirror(Vector2D vector, Vector2D mirror) {
        return reflect(mirror, vector).invert();
    }

    /**
     * Returns a new vector that represents the orthogonal
     * projection of the first vector onto the second one.
     * If the result is zero, the first vector is a zero
     * Vector or the two vectors are orthogonal.
     * 
     * @param vectorToProject The Vector th be projected
     * @param onto The Vector to be projected onto
     * @return The representation of the projection
     * @throws ArithmeticException If the vector to project
     * onto is a zero Vector
     */
    public static Vector2D project(Vector2D vectorToProject, Vector2D onto){
        Arguments.checkNull(vectorToProject, "vectorToProject");
        Arguments.checkNull(onto, "onto");
        return onto.normed().scale(vectorToProject.dot(onto)).divide(onto.abs());
    }

    /**
     * Returns the area that the two given vectors span.
     * 
     * @param v The first vector
     * @param w The second vector
     * @return The area the two vectors span
     */
    public static double area(Vector2D v, Vector2D w) {
        return Math.abs(cross(v, w));
    }

    /**
     * Returns the smallest angle between the two vectors, in degrees between 0° and 180°.
     * 
     * @param v The first vector
     * @param w The second vector
     * @return The smallest angle between the two vectors, in degrees
     */
    public static double smallestAngle(Vector2D v, Vector2D w) {
        Arguments.checkNull(v, "v");
        Arguments.checkNull(w, "w");
        if(v.isZero() || w.isZero()) return 0;
        return Math.toDegrees(Math.acos(Vector.dot(v, w) / (v.abs() * w.abs())));
    }
}
