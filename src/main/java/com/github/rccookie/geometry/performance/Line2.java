package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.Json;
import com.github.rccookie.json.JsonCtor;
import com.github.rccookie.json.JsonObject;
import com.github.rccookie.util.Console;

public class Line2 extends Line<Vec2> implements Collider2 {

    public Line2(Vec2 a, Vec2 b) {
        super(a, b, false);
    }

    @JsonCtor({"a", "b", "ds"})
    public Line2(Vec2 a, Vec2 b, boolean ds) {
        super(a, b, ds);
    }

    @Override
    public Object toJson() {
        return new JsonObject("a", a, "b", b, "ds", ds);
    }

    @Override
    public float length() {
        float dx = b.x - a.x, dy = b.y - a.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public float sqrLength() {
        float dx = b.x - a.x, dy = b.y - a.y;
        return dx * dx + dy * dy;
    }

    @Override
    public Vec2 get(float i) {
        return new Vec2(a.x + i * (b.x - a.x), a.y + i * (b.y - a.y));
    }

    @Override
    public Vec2 getNormal(float i) {
        return new Vec2(a.y - b.y, b.x - a.x);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Coll2 coll(Ray<Vec2> r, float maxSqrL) {

        float abx = b.x - a.x, aby = b.y - a.y;

        float d = r.d.x * aby - r.d.y * abx;
        if((!ds && d <= 0) ^ d == 0)
            return null; // Wrong direction
        float iD = 1 / d;

        float lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * a.x - r.d.x * a.y) * iD;

        if(lHit < 0 || lHit > 1) return null;

        float rHit = (abx * a.y - aby * a.x + aby * r.o.x - abx * r.o.y) * (-iD);

        if(rHit < 0) return null;
        float dx = rHit * r.d.x, dy = rHit * r.d.y;
        float sqrL = dx * dx + dy * dy;
        return sqrL > maxSqrL ? null : new Coll2(rHit, lHit, sqrL);
    }

    @Override
    public boolean contains(Vec2 p) {
        if(a.x == b.x) {
            Console.info("x equals");
            if(a.y == b.y) return a.x == p.x && a.y == b.y;
            Console.info("y equals not");
            float i = (p.y - a.y) / (b.y - a.y);
            return i >= 0 || i <= 1;
        }
        if(a.y == b.y) {
            float i = (p.x - a.x) / (b.x - a.x);
            return i >= 0 || i <= 1;
        }
        float i1 = (p.x - a.x) / (b.x - a.x), i2 = (p.y - a.y) / (b.y - a.y);
        return i1 == i2 && i1 >= 0 && i1 <= 1;
    }

    public static void main(String[] args) {
        Json.write(new Line2(new Vec2(1, 2), new Vec2(3, 4)), System.out);
        System.out.println(Json.parse(Json.toString(new Vec2(3, 4))).as(Vec2.class));
        System.out.println(Json.parse(Json.toString(new Line2(new Vec2(1, 2), new Vec2(3, 4)))).as(Line2.class));
    }
}
