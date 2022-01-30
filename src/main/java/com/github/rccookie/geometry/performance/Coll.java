package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonSerializable;

public abstract class Coll<V extends Vec<V,?>> implements JsonSerializable {

    public final float rI;
    public final float sqrL;

    public Coll(float rI, float sqrL) {
        this.rI = rI;
        this.sqrL = sqrL;
    }

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
