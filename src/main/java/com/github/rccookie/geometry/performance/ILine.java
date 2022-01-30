package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonSerializable;

public abstract class ILine<V extends IVec<V,?>> implements JsonSerializable {

    public final V a;
    public final V b;
    public boolean ds;

    public ILine(V a, V b, boolean ds) {
        this.a = a;
        this.b = b;
        this.ds = ds;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ILine)) return false;
        ILine<?> l = (ILine<?>) o;
        return a.equals(l.a) && b.equals(l.b);
    }

    @Override
    public int hashCode() {
        return 19 * a.hashCode() + b.hashCode();
    }

    @Override
    public String toString() {
        return "ILine{"+a+" to "+b+'}';
    }
}
