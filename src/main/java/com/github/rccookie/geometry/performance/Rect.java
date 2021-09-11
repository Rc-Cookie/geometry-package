package com.github.rccookie.geometry.performance;

import com.github.rccookie.util.Console;

public class Rect extends Box<Vec2> {

    public double a;

    public Rect(Vec2 c, Vec2 s, double a, boolean i, boolean ds) {
        super(c, s, i, ds);
        this.a = a;
    }

    public Rect(Vec2 c, Vec2 s, double a, boolean i) {
        this(c, s, a, i, false);
    }

    public Rect(Vec2 c, Vec2 s, double a) {
        this(c, s, a, false, false);
    }

    public Rect(Vec2 c, Vec2 s) {
        this(c, s, 0, false, false);
    }

    @Override
    public double length() {
        return 2 * (s.x + s.y);
    }

    @Override
    public double sqrLength() {
        double l = 2 * (s.x + s.y);
        return l * l;
    }

    @Override
    public Vec2 get(double i) {
        if(i > 1) i %= 1;
        else if(i < 0) i = 1 - (i % 1);

        double hsx = s.x * 0.5, hsy = s.y * 0.5;
        double s1L = s.x / ((s.x + s.y) * 4);
        double sin = FastMath.sin(a), cos = FastMath.cos(a);

        if(i <= s1L) {
            i /= s1L;
            return new Vec2(
                    (/*center*/c.x - /*corner offset*/(hsx * cos - hsy * sin)) - /*direction*/(s.y * sin) * i,
                    (/*center*/c.y - /*corner offset*/(hsy * cos + hsx * sin)) + /*direction*/(s.y * cos) * i
            );
        }
        if(i <= 0.5) {
            i = (i - s1L) / (0.5 - s1L);
            return new Vec2(
                    (/*center*/c.x - /*corner offset*/(hsx * cos + hsy * sin)) + /*direction*/(s.x * cos) * i,
                    (/*center*/c.y + /*corner offset*/(hsy * cos - hsx * sin)) + /*direction*/(s.x * sin) * i
            );
        }
        if(i <= s1L + 0.5) {
            i = (i - 0.5) / s1L;
            Console.map("i", i);
            return new Vec2(
                    (/*center*/c.x + /*corner offset*/(hsx * cos - hsy * sin)) + /*direction*/(s.y * sin) * i,
                    (/*center*/c.y + /*corner offset*/(hsy * cos + hsx * sin)) - /*direction*/(s.y * cos) * i
            );
        }
        i = (i - (s1L + 0.5)) / (0.5 - s1L);
        return new Vec2(
                (/*center*/c.x + /*corner offset*/(hsx * cos + hsy * sin)) - /*direction*/(s.x * cos) * i,
                (/*center*/c.y - /*corner offset*/(hsy * cos - hsx * sin)) - /*direction*/(s.x * sin) * i
        );
    }

    @Override
    public Vec2 getNormal(double i) {
        if(i > 1) i %= 1;
        else if(i < 0) i = 1 - (i % 1);

        double s1L = s.x / ((s.x + s.y) * 4);
        double sin = FastMath.sin(a), cos = FastMath.cos(a);

        if(i <= s1L)
            return new Vec2(-(s.y * cos), -(s.y * sin));
        if(i <= 0.5)
            return new Vec2(-(s.x * sin), (s.x * cos));
        if(i <= s1L + 0.5)
            return new Vec2((s.y * cos), (s.y * sin));
        return new Vec2((s.x * sin), -(s.x * cos));
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Coll coll(Ray<Vec2> r, double maxSqrL) {

        double hsx = s.x * 0.5, hsy = s.y * 0.5;
        double s1L = s.x / ((s.x + s.y) * 4);
        double sin = FastMath.sin(a), cos = FastMath.cos(a);

        if(i) {
            double sx = -s.y * sin, sy = s.y * cos;
            double d = r.d.x * sy + r.d.y * -sx;

            double iD, cx, cy;

            if(d < 0) {
                // Side 3 (right)
                iD = 1 / d;
                cx = c.x - (hsx * cos - hsy * sin);
                cy = c.y - (hsy * cos + hsx * sin);

                double lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    double rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    double dx = rHit * r.d.x, dy = rHit * r.d.y;
                    double sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll(rHit, lHit * s1L + 0.5, sqrL);
                }
            } else if(d != 0) {
                // Side 1 (left)
                sx = -sx;
                sy = -sy;
                iD = -1 / d;
                cx = c.x + (hsx * cos - hsy * sin);
                cy = c.y + (hsy * cos + hsx * sin);

                double lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    double rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    double dx = rHit * r.d.x, dy = rHit * r.d.y;
                    double sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll(rHit, lHit * s1L, sqrL);
                }
            }

            sx = s.x * cos;
            sy = s.x * sin;
            d = r.d.x * sy + r.d.y * -sx;
            if(d == 0) return null;

            iD = 1 / d;
            double lHit;

            if(d < 0) {
                // Side 2 (top)
                cx = c.x - (hsx * cos + hsy * sin);
                cy = c.y + (hsy * cos - hsx * sin);

                lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    double rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    double dx = rHit * r.d.x, dy = rHit * r.d.y;
                    double sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll(rHit, lHit * (0.5 - s1L) + s1L, sqrL);
                }
            }

            // Side 4 (bottom)
            sx = -sx;
            sy = -sy;
            cx = c.x + (hsx * cos + hsy * sin);
            cy = c.y - (hsy * cos - hsx * sin);

            lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * (-iD);

            if(lHit < 0 || lHit > 1) return null;
            double rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * iD;

            if(rHit < 0) return null;
            double dx = rHit * r.d.x, dy = rHit * r.d.y;
            double sqrL = dx * dx + dy * dy;
            return sqrL > maxSqrL ? null : new Coll(rHit, lHit * (0.5 - s1L) + (0.5 + s1L), sqrL);
        }
        else {
            double sx = -s.y * sin, sy = s.y * cos;
            double d = r.d.x * sy + r.d.y * -sx;

            double iD, cx, cy;

            if(d > 0) {
                // Side 1 (left)
                iD = 1 / d;
                cx = c.x - (hsx * cos - hsy * sin);
                cy = c.y - (hsy * cos + hsx * sin);

                double lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    double rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    double dx = rHit * r.d.x, dy = rHit * r.d.y;
                    double sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll(rHit, lHit * s1L, sqrL);
                }
            }
            else if(d != 0) {
                // Side 3 (right)
                sx = -sx;
                sy = -sy;
                iD = -1 / d;
                cx = c.x + (hsx * cos - hsy * sin);
                cy = c.y + (hsy * cos + hsx * sin);

                double lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    double rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    double dx = rHit * r.d.x, dy = rHit * r.d.y;
                    double sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll(rHit, lHit * s1L + 0.5, sqrL);
                }
            }

            sx = s.x * cos;
            sy = s.x * sin;
            d = r.d.x * sy + r.d.y * -sx;
            if(d == 0) return null;

            iD = 1 / d;
            double lHit;

            if(d > 0) {
                // Side 2 (top)
                cx = c.x - (hsx * cos + hsy * sin);
                cy = c.y + (hsy * cos - hsx * sin);

                lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * iD;

                if(lHit >= 0 && lHit <= 1) {
                    double rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * (-iD);

                    if(rHit < 0) return null;
                    double dx = rHit * r.d.x, dy = rHit * r.d.y;
                    double sqrL = dx * dx + dy * dy;
                    if(sqrL <= maxSqrL)
                        return new Coll(rHit, lHit * (0.5 - s1L) + s1L, sqrL);
                }
            }

            // Side 4 (bottom)
            sx = -sx;
            sy = -sy;
            cx = c.x + (hsx * cos + hsy * sin);
            cy = c.y - (hsy * cos - hsx * sin);

            lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * cx - r.d.x * cy) * (-iD);

            if(lHit < 0 || lHit > 1) return null;
            double rHit = (sx * cy - sy * cx + sy * r.o.x - sx * r.o.y) * iD;

            if(rHit < 0) return null;
            double dx = rHit * r.d.x, dy = rHit * r.d.y;
            double sqrL = dx * dx + dy * dy;
            return sqrL > maxSqrL ? null : new Coll(rHit, lHit * (0.5 - s1L) + (0.5 + s1L), sqrL);
        }
    }

    @Override
    public boolean contains(Vec2 p) {

        double sin = FastMath.sin(-a), cos = FastMath.cos(-a);
        double hsx = s.x * 0.5, hsy = s.y * 0.5;
        double dx = p.x - c.x, dy = p.y - c.y;
        double px = dx * cos - dy * sin + c.x, py = dx * sin + dy * cos + c.y;

        return (px >= c.x - hsx && px <= c.x + hsx && py >= c.y - hsy && py <= c.y + hsy) ^ i;
    }
}
