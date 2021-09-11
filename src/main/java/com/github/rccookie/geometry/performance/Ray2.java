package com.github.rccookie.geometry.performance;

public class Ray2 extends Ray<Vec2> {

    public Ray2(Vec2 o, Vec2 d) {
        super(o, d, false, false);
    }

    public Ray2(Vec2 o, Vec2 d, boolean ds, boolean i) {
        super(o, d, ds, i);
    }

    @Override
    public Vec2 get(double i) {
        return new Vec2(o.x + i * d.x, o.y + i * d.y);
    }

    @Override
    public Vec2 getNormal(double i) {
        //noinspection SuspiciousNameCombination
        return new Vec2(d.y, -d.x);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Coll coll(Ray<Vec2> r, double maxSqrL) {

        double dot = r.d.x * d.y - r.d.y * d.x;
        if((!ds && (/*(i && dot >= 0) ^*/ (!i && dot <= 0))) ^ dot == 0)
            return null; // Wrong direction
        double iD = 1 / dot;

        double rHit = (d.x * o.y - d.y * o.x + d.y * r.o.x - d.x * r.o.y) * (-iD);

        if(rHit < 0) return null;
        double dx = rHit * r.d.x, dy = rHit * r.d.y;
        double sqrL = dx * dx + dy * dy;
        if(sqrL > maxSqrL) return null;

        double hit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * o.x - r.d.x * o.y) * (iD);

        return hit < 0 ? null : new Coll(rHit, hit, sqrL);
    }

    @Override
    public boolean contains(Vec2 p) {
        double i1 = (p.x - o.x) / d.x, i2 = (p.y - o.y) / d.y;
        return i1 == i2 && i1 >= 0 && i1 <= 1;
    }
}
