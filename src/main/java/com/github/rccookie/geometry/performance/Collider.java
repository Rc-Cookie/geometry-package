package com.github.rccookie.geometry.performance;

public interface Collider<V extends Vec<V,?>> {

    float length();

    float sqrLength();

    V get(float i);

    V getNormal(float i);

    Coll<V> coll(Ray<V> r, float maxSqrL);

    default Coll<V> coll(Ray<V> r) {
        return coll(r, Float.POSITIVE_INFINITY);
    }

    boolean contains(V p);

//    V intersection(Collider<V> c);
}
