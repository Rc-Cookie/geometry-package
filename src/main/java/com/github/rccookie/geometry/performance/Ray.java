package com.github.rccookie.geometry.performance;

public abstract class Ray<V extends Vec<V>> implements Collider<V> {

    public final V o;
    public final V d;
    public boolean ds;
    public boolean i;

    public Ray(V o, V d, boolean ds, boolean i) {
        this.o = o.clone();
        this.d = d.clone();
        this.ds = ds;
        this.i = i;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Ray)) return false;
        Ray<?> r = (Ray<?>) obj;
        return o.equals(r.o) && d.equals(r.d);
    }

    @Override
    public int hashCode() {
        return 17 * o.hashCode() + d.hashCode();
    }

    @Override
    public String toString() {
        return "Ray{"+o+" -> "+d+'}';
    }

    @Override
    public double length() {
        return d.isZero() ? 0 : Double.POSITIVE_INFINITY;
    }

    @Override
    public double sqrLength() {
        return d.isZero() ? 0 : Double.POSITIVE_INFINITY;
    }
}
