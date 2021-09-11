package com.github.rccookie.geometry.performance;

public interface Collider<V extends Vec<V>> {

    double length();

    double sqrLength();

    V get(double i);

    V getNormal(double i);

    Coll coll(Ray<V> r, double maxSqrL);

    default Coll coll(Ray<V> r) {
        return coll(r, Double.POSITIVE_INFINITY);
    }

    boolean contains(V p);
}
