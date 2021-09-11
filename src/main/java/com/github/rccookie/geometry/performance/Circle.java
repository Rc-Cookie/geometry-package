package com.github.rccookie.geometry.performance;

public class Circle implements Collider<Vec2> {

    public final Vec2 c;
    public double r;
    public double a;
    public boolean i;

    public Circle(Vec2 c, double r, double a, boolean i) {
        this.c = c.clone();
        this.r = r;
        this.a = a;
        this.i = i;
    }

    public Circle(Vec2 c, double r, double a) {
        this.c = c.clone();
        this.r = r;
        this.a = a;
        i = false;
    }

    public Circle(Vec2 c, double r) {
        this.c = c.clone();
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
    public double length() {
        return 2 * r * Math.PI;
    }

    @Override
    public double sqrLength() {
        double l = 2 * r * Math.PI;
        return l * l;
    }

    @Override
    public Vec2 get(double i) {
        double angle = a + i * 360;
        return new Vec2(c.x + FastMath.cos(angle) * r, c.y + FastMath.sin(angle) * r);
    }

    @Override
    public Vec2 getNormal(double i) {
        double angle = a + i * 360 + (this.i ? 90 : -90);
        return new Vec2(c.x + FastMath.cos(angle), c.y + FastMath.sin(angle));
    }

    @Override
    public Coll coll(Ray<Vec2> r, double maxSqrL) {
        double dx = r.o.x - c.x, dy = r.o.y - c.y;

        double a = 2 * (r.d.x * r.d.x + r.d.y * r.d.y);
        double b = 2 * (dx * r.d.x + dy * r.d.y);
        double x = b * b - 2 * a * (dx * dx + dy * dy - this.r * this.r);

        if(x < 0) return null;
        x = Math.sqrt(x);

        double rHit;
        if(i) {
            double i2 = (-b + x)/a;
            if(i2 < 0) return null;
            rHit = i2;
        }
        else {
            double i1 = (-b - x)/a;
            if(i1 < 0) return null;
            rHit = i1;
        }

        dx = rHit * r.d.x;
        dy = rHit * r.d.y;
        double sqrL = dx * dx + dy * dy;
        if(sqrL > maxSqrL) return null;

        double cHit = (FastMath.atan2(r.o.y + r.d.y * rHit - c.y, r.o.x + r.d.x * rHit - c.x) - this.a) / 360 + 0.25;
        if(cHit < 0) cHit++;
        return new Coll(cHit, rHit, sqrL);
    }

    @Override
    public boolean contains(Vec2 p) {
        double dx = p.x - c.x, dy = p.y - c.y;
        return (dx * dx + dy * dy <= r) ^ i;
    }
}
