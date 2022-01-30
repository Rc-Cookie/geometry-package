package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonCtor;

public class Ray2 extends Ray<Vec2> implements Collider2 {

    public Ray2(Vec2 o, Vec2 d) {
        super(o, d, false, false);
    }

    @JsonCtor({"o", "d", "ds", "i"})
    public Ray2(Vec2 o, Vec2 d, boolean ds, boolean i) {
        super(o, d, ds, i);
    }

    @Override
    public Vec2 get(float i) {
        return new Vec2(o.x + i * d.x, o.y + i * d.y);
    }

    @Override
    public Vec2 getNormal(float i) {
        //noinspection SuspiciousNameCombination
        return new Vec2(-d.y, d.x);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Coll2 coll(Ray<Vec2> r, float maxSqrL) {

        float dot = r.d.x * d.y - r.d.y * d.x;
        if((!ds && (/*(i && dot >= 0) ^*/ (!i && dot <= 0))) ^ dot == 0)
            return null; // Wrong direction
        float iD = 1 / dot;

        float rHit = (d.x * o.y - d.y * o.x + d.y * r.o.x - d.x * r.o.y) * (-iD);

        if(rHit < 0) return null;
        float dx = rHit * r.d.x, dy = rHit * r.d.y;
        float sqrL = dx * dx + dy * dy;
        if(sqrL > maxSqrL) return null;

        float hit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * o.x - r.d.x * o.y) * (iD);

        return hit < 0 ? null : new Coll2(rHit, hit, sqrL);
    }

    @Override
    public boolean contains(Vec2 p) {
        float i1 = (p.x - o.x) / d.x, i2 = (p.y - o.y) / d.y;
        return i1 == i2 && i1 >= 0 && i1 <= 1;
    }
}
