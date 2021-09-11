package com.github.rccookie.geometry.performance;

public abstract class Box<V extends Vec<V>> implements Collider<V> {

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
}
