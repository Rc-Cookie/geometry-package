package com.github.rccookie.geometry;

/**
 * A vector represents a change in location, often also expressed as
 * an arrow or a movement. Every vector consists of a <i>x</i>, <i>y</i> and
 * <i>z</i> coordinate which represent the difference in the corresponding
 * direction.
 * <p>Vectors allow easy manipulation and share all common mathmatical
 * interactions such as absolute(length), normalization, addition and subtraction,
 * scalar and vector multiplication and more. Also the angle of the vector can
 * be measured relative to another vector or the x axis in form of a rotation
 * object.
 * <p>Vectors are commonly used for any kind of movement representation, but
 * also for other geometric effects. One example is to save the location of
 * an object using a vector which represents the distance from the point of
 * origin to the object.
 * 
 * @version 2.0
 */
public class IntVector3D extends AbstractIntVector<IntVector3D> {

    private static final long serialVersionUID = -4497796665392993666L;




    /**
     * Creates a new zero vector.
     */
    public IntVector3D() {
        this(0, 0, 0);
    }

    /**
     * Creates a new vector with the length {@code x} in the direction of the x axis.
     * <p>This does exactly the same as invoking {@code new Vector(x, 0, 0);}.
     *
     * @param x The length of the vector in x direction
     */
    public IntVector3D(int x) {
        this(x, 0, 0);
    }

    /**
     * Creates a new vector with the given x and y magnitude. This essentially creates
     * a 2D vector.
     * <p>This does exactly the same as invoking {@code new Vector(x, y, 0);}.
     *
     * @param x The length of the vector in x direction
     * @param y The length of the vector in y direction
     */
    public IntVector3D(int x, int y) {
        this(x, y, 0);
    }

    /**
     * Creates a new vector eith the given x, y and z magnitude.
     *
     * @param x The length of the vector in x direction
     * @param y The length of the vector in y direction
     * @param z The length of the vector in z direction
     */
    public IntVector3D(int x, int y, int z) {
        super(x, y, z);
    }

    /**
     * Creates a new Vector from the given vector. In other words, this
     * creates a copy of the given vector so that for {@code Vector v}
     * {@code v.equals(new Vector(v))} will be true and {@code v == new Vector(v)}
     * will be false.
     *
     * @param copy The vector to create a copy from
     */
    public IntVector3D(AbstractIntVector<?> copy) {
        // 3D conversion is essential for super class to create a 3 long array
        super(copy.get3D());
    }




    @Override
    public IntVector3D clone() {
        return new IntVector3D(this);
    }

    @Override
    public IntVector3D get3D() throws UnsupportedOperationException {
        return this;
    }

    @Override
    public Vector3D getVector() {
        return new Vector3D(x(), y(), z());
    }



    public int z() { return get(Z); }

    public IntVector3D setZ(int z) {
        return setDim(Z, z);
    }

    public IntVector3D set(int x, int y, int z) {
        return setX(x).setY(y).setZ(z);
    }




    public static IntVector3D cross(IntVector3D v, IntVector3D w) {
        return new IntVector3D(
            v.y() * w.z() - v.z() * w.y(),
            v.z() * w.x() - v.x() * w.z(),
            v.x() * w.y() - v.y() * w.x()
        );
    }
}
