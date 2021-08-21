package com.github.rccookie.geometry;

import com.github.rccookie.data.Saveable;
import java.util.Objects;

public class Ray implements Border, Saveable {

    public final Vector root;
    public final Vector direction;


    public Ray(Vector direction) {
        this(Vector.ZERO, direction);
    }

    public Ray(Vector root, Vector direction) {
        this.root = root;
        this.direction = direction;
    }


    @Override
    public double[] rayIntersection(Ray ray) {
        return intersection(this, ray);
    }

    public Vector get(double index) {
        return root.added(direction.scaled(index));
    }

    @Override
    public Vector getNormal(double index) {
        return null;
    }

    @Override
    public double length() {
        return direction.isZero() ? 0 : Double.POSITIVE_INFINITY;
    }



    @Override
    public String toString() {
        return "Ray from " + root + " towards " + direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(root.hashCode(), direction.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Ray)) return false;
        return Objects.equals(root, ((Ray)obj).root) && Objects.equals(direction, ((Ray)obj).direction);
    }



    /**
     * Calculates weather and if so where the two given rays intersect.
     * 
     * @param a The first ray
     * @param b The second ray
     * @return A two-dimensional array containing the two distances from the rays' roots measured
     * in their direction vectors, or {@code null} if they are parallel
     */
    static double[] intersection(Ray a, Ray b) {
        double aHit = a.direction.x() * a.root.y() - a.direction.y() * a.root.x();
        aHit += a.direction.y() * b.root.x() - a.direction.x() * b.root.y();
        aHit /= a.direction.x() * b.direction.y() - a.direction.y() * b.direction.x();

        if(Double.isNaN(aHit)) return null;

        double bHit = b.direction.x() * b.root.y() - b.direction.y() * b.root.x();
        bHit += b.direction.y() * a.root.x() - b.direction.x() * a.root.y();
        bHit /= b.direction.x() * a.direction.y() - b.direction.y() * a.direction.x();

        return new double[] {bHit, aHit};
    }
}
