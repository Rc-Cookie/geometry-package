package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonObject;
import com.github.rccookie.json.JsonSerializable;

public abstract class Box<V extends Vec<V,?>> implements Collider<V>, JsonSerializable {

    public final V c;
    public final V s;
    public boolean i;
    public boolean ds;

    public Box(V c, V s, boolean i, boolean ds) {
        this.c = c;
        this.s = s;
        this.i = i;
        this.ds = ds;
    }

    @Override
    public Object toJson() {
        return new JsonObject("c", c, "s", s, "i", i, "ds", ds);
    }
}
