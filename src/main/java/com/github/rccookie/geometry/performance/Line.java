package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonSerializable;

public abstract class Line<V extends Vec<V,?>> implements Collider<V>, JsonSerializable {

    public final V a;
    public final V b;
    public boolean ds;

    public Line(V a, V b, boolean ds) {
        this.a = a;
        this.b = b;
        this.ds = ds;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Line)) return false;
        Line<?> l = (Line<?>) o;
        return a.equals(l.a) && b.equals(l.b);
    }

    @Override
    public int hashCode() {
        return 19 * a.hashCode() + b.hashCode();
    }

    @Override
    public String toString() {
        return "Line{"+a+" to "+b+'}';
    }
}
