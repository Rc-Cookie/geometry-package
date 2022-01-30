package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonObject;
import com.github.rccookie.json.JsonSerializable;

public abstract class Ray<V extends Vec<V,?>> implements Collider<V>, JsonSerializable {

    public final V o;
    public final V d;
    public final boolean ds;
    public final boolean i;

    public Ray(V o, V d, boolean ds, boolean i) {
        this.o = o;
        this.d = d;
        this.ds = ds;
        this.i = i;
    }

    @Override
    public Object toJson() {
        return new JsonObject("o", o, "d", d, "ds", ds, "i", i);
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
    public float length() {
        return d.isZero() ? 0 : Float.POSITIVE_INFINITY;
    }

    @Override
    public float sqrLength() {
        return d.isZero() ? 0 : Float.POSITIVE_INFINITY;
    }
}
