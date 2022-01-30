package com.github.rccookie.geometry.performance;

import java.util.Iterator;

public class Raycast<V extends Vec<V,?>> {

    public final Ray<V> r;
    public final Collider<V> e;
    public final float sqrL;
    public final float rI;
    public final boolean collided;
    public final Coll<V> coll;

    private Float l;
    private V p;

    private Raycast(Coll<V> coll, Ray<V> r, Collider<V> e, Float maxL, float maxSqrL) {
        this.r = r;
        collided = coll != null;
        this.coll = coll;
        if(collided) {
            this.e = e;
            sqrL = coll.sqrL;
            rI = coll.rI;
            l = null;
        }
        else {
            this.e = null;
            sqrL = maxSqrL;
            rI = Float.NaN;
            l = maxL;
        }
    }

    @Override
    public String toString() {
        return r + ": " + e + " at " + point() + " (" + length() + ')';
    }

    public float length() {
        return l != null ? l : (l = (float) Math.sqrt(sqrL));
    }

    public V point() {
        return p != null ? p :
                (p = r.get(collided ? rI : length() / r.d.abs()));
    }


    public static <V extends Vec<V,?>> Raycast<V> calc(Ray<V> r, Iterable<Collider<V>> edges) {
        return calc(r, edges, Float.POSITIVE_INFINITY);
    }

    public static <V extends Vec<V,?>> Raycast<V> calc(Ray<V> r, Iterable<Collider<V>> edges, float maxL) {
        float maxSqrL = maxL * maxL;
        Iterator<Collider<V>> edgeIt = edges.iterator();
        if(!edgeIt.hasNext())
            return new Raycast<>(null, r, null, maxL, maxSqrL);

        Collider<V> e = edgeIt.next();
        Coll<V> coll = e.coll(r, maxSqrL);

        while(edgeIt.hasNext()) {
            Collider<V> newE = edgeIt.next();
            Coll<V> newColl = newE.coll(r, maxSqrL);
            if(coll == null || (newColl != null && newColl.sqrL < coll.sqrL)) {
                e = newE;
                coll = newColl;
            }
        }

        return new Raycast<>(coll, r, e, coll == null ? maxL : null, maxSqrL);
    }

    public static <V extends Vec<V,?>> Raycast<V> calc(Ray<V> r, Collider<V>[] colliders) {
        return calc(r, colliders, Float.POSITIVE_INFINITY);
    }

    public static <V extends Vec<V,?>> Raycast<V> calc(Ray<V> r, Collider<V>[] colliders, float maxL) {
        float maxSqrL = maxL * maxL;
        if(colliders.length == 0)
            return new Raycast<>(null, r, null, maxL, maxSqrL);

        Collider<V> e = colliders[0];
        Coll<V> coll = e.coll(r, maxSqrL);

        for(int i = 1; i< colliders.length; i++) {
            Collider<V> newE = colliders[i];
            Coll<V> newColl = newE.coll(r, maxSqrL);
            if(coll == null || (newColl != null && newColl.sqrL < coll.sqrL)) {
                e = newE;
                coll = newColl;
            }
        }

        return new Raycast<>(coll, r, e, coll == null ? maxL : null, maxSqrL);
    }
}
