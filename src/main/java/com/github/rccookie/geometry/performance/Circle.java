package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonCtor;
import com.github.rccookie.json.JsonObject;
import com.github.rccookie.json.JsonSerializable;

public class Circle implements Collider2, JsonSerializable {

    public final Vec2 c;
    public float r;
    public float a;
    public boolean i;

    @JsonCtor({"c", "r", "a", "i"})
    public Circle(Vec2 c, float r, float a, boolean i) {
        this.c = c;
        this.r = r;
        this.a = a;
        this.i = i;
    }

    public Circle(Vec2 c, float r, float a) {
        this.c = c;
        this.r = r;
        this.a = a;
        i = false;
    }

    public Circle(Vec2 c, float r) {
        this.c = c;
        this.r = r;
        a = 0;
        i = false;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Circle)) return false;
        Circle circle = (Circle) o;
        return circle.r == r && circle.a == a && i == circle.i && c.equals(circle.c);
    }

    @Override
    public int hashCode() {
        return 23 * (23 * (23 * c.hashCode() + Double.hashCode(r)) + Double.hashCode(a)) + Boolean.hashCode(i);
    }

    @Override
    public Object toJson() {
        return new JsonObject("c", c, "r", r, "a", a, "i", i);
    }

    @Override
    public float length() {
        return 2 * r * FastMath.PI;
    }

    @Override
    public float sqrLength() {
        float l = 2 * r * FastMath.PI;
        return l * l;
    }

    @Override
    public Vec2 get(float i) {
        float angle = a + i * 360;
        return new Vec2(c.x + FastMath.cos(angle) * r, c.y + FastMath.sin(angle) * r);
    }

    @Override
    public Vec2 getNormal(float i) {
        float angle = a + i * 360;
        return this.i ?
                new Vec2(-FastMath.cos(angle), -FastMath.sin(angle)) :
                new Vec2(FastMath.cos(angle), FastMath.sin(angle));
    }

    @Override
    public Coll2 coll(Ray<Vec2> r, float maxSqrL) {
        float dx = r.o.x - c.x, dy = r.o.y - c.y;

        float a = 2 * (r.d.x * r.d.x + r.d.y * r.d.y);
        float b = 2 * (dx * r.d.x + dy * r.d.y);
        float x = b * b - 2 * a * (dx * dx + dy * dy - this.r * this.r);

        if(x < 0) return null;
        x = (float) Math.sqrt(x);

        float rHit;
        if(i) {
            float i2 = (-b + x)/a;
            if(i2 < 0) return null;
            rHit = i2;
        }
        else {
            float i1 = (-b - x)/a;
            if(i1 < 0) return null;
            rHit = i1;
        }

        dx = rHit * r.d.x;
        dy = rHit * r.d.y;
        float sqrL = dx * dx + dy * dy;
        if(sqrL > maxSqrL) return null;

        float cHit = (FastMath.atan2(r.o.y + r.d.y * rHit - c.y, r.o.x + r.d.x * rHit - c.x) - this.a) / 360;// + 0.25;
        if(cHit < 0) cHit++;
        return new Coll2(rHit, cHit, sqrL);
    }

    @Override
    public boolean contains(Vec2 p) {
        float dx = p.x - c.x, dy = p.y - c.y;
        return (dx * dx + dy * dy <= r) ^ i;
    }
}
