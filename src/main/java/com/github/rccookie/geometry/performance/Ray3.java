package com.github.rccookie.geometry.performance;

import com.github.rccookie.util.Console;

public class Ray3 extends Ray<Vec3> {

    public Ray3(Vec3 o, Vec3 d) {
        this(o, d, false, false);
    }

    public Ray3(Vec3 o, Vec3 d, boolean ds, boolean i) {
        super(o, d, ds, i);
    }

    @Override
    public Vec3 get(float i) {
        return new Vec3(o.x + i * d.x, o.y + i * d.y, o.z + i * d.z);
    }

    @Override
    public Vec3 getNormal(float i) {
        return null;
    }

    @Override
    public Coll3 coll(Ray<Vec3> r, float maxSqrL) {
        return null;
    }

    @Override
    public boolean contains(Vec3 p) {
        return false;
    }



    public Coll3 triIntersection(Vec3 t, Vec3 t1, Vec3 t2) {
        return polygonIntersection(t, t1, t2, true);
    }

    public Coll3 rectIntersection(Vec3 t, Vec3 t1, Vec3 t2) {
        return polygonIntersection(t, t1, t2, false);
    }

    public Coll3 polygonIntersection(Vec3 t, Vec3 t1, Vec3 t2, boolean tri) {

        Vec3 t12X = Vec3.cross(t1, t2);
        Vec3 nd = d.negated();
        float det = nd.dot(t12X);
        if(det == 0) return null;

        float iDet = 1 / det;
        Vec3 t_o = o.subtracted(t);

        float ri = t12X.dot(t_o) * iDet;
        if(ri < 0) return null;

        float t1i = Vec3.cross(t2, nd).dot(t_o) * iDet;
        if(t1i < 0 || t1i > 1) return null;

        float t2i = Vec3.cross(nd, t1).dot(t_o) * iDet;
        if(t2i < 0 || t2i > 1 || (tri && t1i + t2i > 1)) return null;

        float sqrL = d.sqrAbs() * ri;

        return new Coll3(ri, t1i, t2i, sqrL);
    }

    public static void main(String[] args) {
        Ray3 ray = new Ray3(Vec3.ZERO, Vec3.X);
        Console.map("Result", ray.rectIntersection(new Vec3(1, -1, -1), new Vec3(0, 0, 2), new Vec3(0, 2, 0)));
    }
}
