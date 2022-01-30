package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonCtor;
import com.github.rccookie.json.JsonObject;
import com.github.rccookie.util.Console;

public class Rect extends Box<Vec2> implements Collider2 {

    public float a;

    @JsonCtor({"c", "s", "a", "i", "ds"})
    public Rect(Vec2 c, Vec2 s, float a, boolean i, boolean ds) {
        super(c, s, i, ds);
        this.a = a;
    }

    public Rect(Vec2 c, Vec2 s, float a, boolean i) {
        this(c, s, a, i, false);
    }

    public Rect(Vec2 c, Vec2 s, float a) {
        this(c, s, a, false, false);
    }

    public Rect(Vec2 c, Vec2 s) {
        this(c, s, 0, false, false);
    }

    @Override
    public Object toJson() {
        JsonObject json = (JsonObject) super.toJson();
        json.put("a", a);
        return json;
    }

    @Override
    public float length() {
        return 2 * (s.x + s.y);
    }

    @Override
    public float sqrLength() {
        float l = 2 * (s.x + s.y);
        return l * l;
    }

    @Override
    public Vec2 get(float i) {
        if(i > 1) i %= 1;
        else if(i < 0) i = 1 - (i % 1);

        float hsx = s.x * 0.5f, hsy = s.y * 0.5f;
        float s1L = s.x / ((s.x + s.y) * 4);
        float sin = FastMath.sin(a), cos = FastMath.cos(a);

        if(i <= s1L) {
            i /= s1L;
            return new Vec2(
                    (/*center*/c.x - /*corner offset*/(hsx * cos - hsy * sin)) - /*direction*/(s.y * sin) * i,
                    (/*center*/c.y - /*corner offset*/(hsy * cos + hsx * sin)) + /*direction*/(s.y * cos) * i
            );
        }
        if(i <= 0.5) {
            i = (i - s1L) / (0.5f - s1L);
            return new Vec2(
                    (/*center*/c.x - /*corner offset*/(hsx * cos + hsy * sin)) + /*direction*/(s.x * cos) * i,
                    (/*center*/c.y + /*corner offset*/(hsy * cos - hsx * sin)) + /*direction*/(s.x * sin) * i
            );
        }
        if(i <= s1L + 0.5f) {
            i = (i - 0.5f) / s1L;
            Console.map("i", i);
            return new Vec2(
                    (/*center*/c.x + /*corner offset*/(hsx * cos - hsy * sin)) + /*direction*/(s.y * sin) * i,
                    (/*center*/c.y + /*corner offset*/(hsy * cos + hsx * sin)) - /*direction*/(s.y * cos) * i
            );
        }
        i = (i - (s1L + 0.5f)) / (0.5f - s1L);
        return new Vec2(
                (/*center*/c.x + /*corner offset*/(hsx * cos + hsy * sin)) - /*direction*/(s.x * cos) * i,
                (/*center*/c.y - /*corner offset*/(hsy * cos - hsx * sin)) - /*direction*/(s.x * sin) * i
        );
    }

    @Override
    public Vec2 getNormal(float i) {
        if(i > 1) i %= 1;
        else if(i < 0) i = 1 - (i % 1);

        float s1L = s.x / ((s.x + s.y) * 4);
        float sin = FastMath.sin(a), cos = FastMath.cos(a);

        if(i <= s1L)
            return new Vec2(-(s.y * cos), -(s.y * sin));
        if(i <= 0.5)
            return this.i ? new Vec2((s.x * sin), -(s.x * cos)) : new Vec2(-(s.x * sin), (s.x * cos));
        if(i <= s1L + 0.5)
            return new Vec2((s.y * cos), (s.y * sin));
        return this.i ? new Vec2(-(s.x * sin), (s.x * cos)) : new Vec2((s.x * sin), -(s.x * cos));
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Coll2 coll(Ray<Vec2> r, float maxSqrL) {

        float hsx = s.x * 0.5f, hsy = s.y * 0.5f;
        float s1L = s.x / ((s.x + s.y) * 4);
        float sin = FastMath.sin(a), cos = FastMath.cos(a);

        if(i) {
            float sx = -s.y * sin, sy = s.y * cos;
            float d = r.d.x * sy + r.d.y * -sx;

            float iD, cx, cy;

            if(d < 0) {
                // Side 3 (right)
                iD = 1 / d;
                cx = c.x - (hsx * cos - hsy * sin);
                cy = c.y - (hsy * cos + hsx * sin);

                float lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    float rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    float dx = rHit * r.d.x, dy = rHit * r.d.y;
                    float sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll2(rHit, lHit * s1L + 0.5f, sqrL);
                }
            } else if(d != 0) {
                // Side 1 (left)
                sx = -sx;
                sy = -sy;
                iD = -1 / d;
                cx = c.x + (hsx * cos - hsy * sin);
                cy = c.y + (hsy * cos + hsx * sin);

                float lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    float rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    float dx = rHit * r.d.x, dy = rHit * r.d.y;
                    float sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll2(rHit, lHit * s1L, sqrL);
                }
            }

            sx = s.x * cos;
            sy = s.x * sin;
            d = r.d.x * sy + r.d.y * -sx;
            if(d == 0) return null;

            iD = 1 / d;
            float lHit;

            if(d < 0) {
                // Side 2 (top)
                cx = c.x - (hsx * cos + hsy * sin);
                cy = c.y + (hsy * cos - hsx * sin);

                lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    float rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    float dx = rHit * r.d.x, dy = rHit * r.d.y;
                    float sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll2(rHit, lHit * (0.5f - s1L) + s1L, sqrL);
                }
            }

            // Side 4 (bottom)
            sx = -sx;
            sy = -sy;
            cx = c.x + (hsx * cos + hsy * sin);
            cy = c.y - (hsy * cos - hsx * sin);

            lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * (-iD);

            if(lHit < 0 || lHit > 1) return null;
            float rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * iD;

            if(rHit < 0) return null;
            float dx = rHit * r.d.x, dy = rHit * r.d.y;
            float sqrL = dx * dx + dy * dy;
            return sqrL > maxSqrL ? null : new Coll2(rHit, lHit * (0.5f - s1L) + (0.5f + s1L), sqrL);
        }
        else {
            float sx = -s.y * sin, sy = s.y * cos;
            float d = r.d.x * sy + r.d.y * -sx;

            float iD, cx, cy;

            if(d > 0) {
                // Side 1 (left)
                iD = 1 / d;
                cx = c.x - (hsx * cos - hsy * sin);
                cy = c.y - (hsy * cos + hsx * sin);

                float lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    float rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    float dx = rHit * r.d.x, dy = rHit * r.d.y;
                    float sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll2(rHit, lHit * s1L, sqrL);
                }
            }
            else if(d != 0) {
                // Side 3 (right)
                sx = -sx;
                sy = -sy;
                iD = -1 / d;
                cx = c.x + (hsx * cos - hsy * sin);
                cy = c.y + (hsy * cos + hsx * sin);

                float lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    float rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    float dx = rHit * r.d.x, dy = rHit * r.d.y;
                    float sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll2(rHit, lHit * s1L + 0.5f, sqrL);
                }
            }

            sx = s.x * cos;
            sy = s.x * sin;
            d = r.d.x * sy + r.d.y * -sx;
            if(d == 0) return null;

            iD = 1 / d;
            float lHit;

            if(d > 0) {
                // Side 2 (top)
                cx = c.x - (hsx * cos + hsy * sin);
                cy = c.y + (hsy * cos - hsx * sin);

                lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    float rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    float dx = rHit * r.d.x, dy = rHit * r.d.y;
                    float sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll2(rHit, lHit * (0.5f - s1L) + s1L, sqrL);
                }
            }

            // Side 4 (bottom)
            sx = -sx;
            sy = -sy;
            cx = c.x + (hsx * cos + hsy * sin);
            cy = c.y - (hsy * cos - hsx * sin);

            lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * (-iD);

            if(lHit < 0 || lHit > 1) return null;
            float rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * iD;

            if(rHit < 0) return null;
            float dx = rHit * r.d.x, dy = rHit * r.d.y;
            float sqrL = dx * dx + dy * dy;
            return sqrL > maxSqrL ? null : new Coll2(rHit, lHit * (0.5f - s1L) + (0.5f + s1L), sqrL);
        }
    }

    @Override
    public boolean contains(Vec2 p) {

        float sin = FastMath.sin(-a), cos = FastMath.cos(-a);
        float hsx = s.x * 0.5f, hsy = s.y * 0.5f;
        float dx = p.x - c.x, dy = p.y - c.y;
        float px = dx * cos - dy * sin + c.x, py = dx * sin + dy * cos + c.y;

        return (px >= c.x - hsx && px <= c.x + hsx && py >= c.y - hsy && py <= c.y + hsy) ^ i;
    }
}
