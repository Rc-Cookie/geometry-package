package com.github.rccookie.geometry.performance;

import java.util.Iterator;

public class Raycast<V extends Vec<V>> {

    public final Ray<V> r;
    public final Collider<V> e;
    public final double sqrL;
    public final double rI;
    public final double eI;
    public final boolean collided;

    private Double l;
    private V p;

    private Raycast(Coll coll, Ray<V> r, Collider<V> e, Double maxL, double maxSqrL) {
        this.r = r;
        collided = coll != null;
        if(collided) {
            this.e = e;
            sqrL = coll.sqrL;
            rI = coll.rI;
            eI = coll.eI;
            l = null;
        }
        else {
            this.e = null;
            sqrL = maxSqrL;
            rI = eI = Double.NaN;
            l = maxL;
        }
    }

    @Override
    public String toString() {
        return r + ": " + e + " at " + point() + " (" + length() + ')';
    }

    public double length() {
        return l != null ? l : (l = Math.sqrt(sqrL));
    }

    public V point() {
        return p != null ? p :
                (p = r.get(collided ? rI : length() / r.d.abs()));
    }


    public static <V extends Vec<V>> Raycast<V> calc(Ray<V> r, Iterable<Collider<V>> edges) {
        return calc(r, edges, Double.POSITIVE_INFINITY);
    }

    public static <V extends Vec<V>> Raycast<V> calc(Ray<V> r, Iterable<Collider<V>> edges, double maxL) {
        double maxSqrL = maxL * maxL;
        Iterator<Collider<V>> edgeIt = edges.iterator();
        if(!edgeIt.hasNext())
            return new Raycast<>(null, r, null, maxL, maxSqrL);

        Collider<V> e = edgeIt.next();
        Coll coll = e.coll(r, maxSqrL);

        while(edgeIt.hasNext()) {
            Collider<V> newE = edgeIt.next();
            Coll newColl = newE.coll(r, maxSqrL);
            if(coll == null || (newColl != null && newColl.sqrL < coll.sqrL)) {
                e = newE;
                coll = newColl;
            }
        }

        return new Raycast<>(coll, r, e, coll == null ? maxL : null, maxSqrL);
    }

    public static <V extends Vec<V>> Raycast<V> calc(Ray<V> r, Collider<V>[] colliders) {
        return calc(r, colliders, Double.POSITIVE_INFINITY);
    }

    public static <V extends Vec<V>> Raycast<V> calc(Ray<V> r, Collider<V>[] colliders, double maxL) {
        double maxSqrL = maxL * maxL;
        if(colliders.length == 0)
            return new Raycast<>(null, r, null, maxL, maxSqrL);

        Collider<V> e = colliders[0];
        Coll coll = e.coll(r, maxSqrL);

        for(int i = 1; i< colliders.length; i++) {
            Collider<V> newE = colliders[i];
            Coll newColl = newE.coll(r, maxSqrL);
            if(coll == null || (newColl != null && newColl.sqrL < coll.sqrL)) {
                e = newE;
                coll = newColl;
            }
        }

        return new Raycast<>(coll, r, e, coll == null ? maxL : null, maxSqrL);
    }
}
