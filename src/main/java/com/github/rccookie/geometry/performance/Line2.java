package com.github.rccookie.geometry.performance;

import com.github.rccookie.util.Console;
import com.github.rccookie.util.Stopwatch;

public class Line2 extends Line<Vec2> {

    public Line2(Vec2 a, Vec2 b) {
        super(a, b, false);
    }

    public Line2(Vec2 a, Vec2 b, boolean ds) {
        super(a, b, ds);
    }

    @Override
    public double length() {
        double dx = b.x - a.x, dy = b.y - a.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public double sqrLength() {
        double dx = b.x - a.x, dy = b.y - a.y;
        return dx * dx + dy * dy;
    }

    @Override
    public Vec2 get(double i) {
        return new Vec2(a.x + i * (b.x - a.x), a.y + i * (b.y - a.y));
    }

    @Override
    public Vec2 getNormal(double i) {
        return new Vec2(b.y - a.y, a.x - b.x);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Coll coll(Ray<Vec2> r, double maxSqrL) {

        double abx = b.x - a.x, aby = b.y - a.y;

        double d = r.d.x * aby - r.d.y * abx;
        if((!ds && d <= 0) ^ d == 0)
            return null; // Wrong direction
        double iD = 1 / d;

        double lHit = (r.d.x * r.o.y - r.d.y * r.o.x + r.d.y * a.x - r.d.x * a.y) * iD;

        if(lHit < 0 || lHit > 1) return null;

        double rHit = (abx * a.y - aby * a.x + aby * r.o.x - abx * r.o.y) * (-iD);

        if(rHit < 0) return null;
        double dx = rHit * r.d.x, dy = rHit * r.d.y;
        double sqrL = dx * dx + dy * dy;
        return sqrL > maxSqrL ? null : new Coll(rHit, lHit, sqrL);
    }

    @Override
    public boolean contains(Vec2 p) {
        if(a.x == b.x) {
            Console.info("x equals");
            if(a.y == b.y) return a.x == p.x && a.y == b.y;
            Console.info("y equals not");
            double i = (p.y - a.y) / (b.y - a.y);
            return i >= 0 || i <= 1;
        }
        if(a.y == b.y) {
            double i = (p.x - a.x) / (b.x - a.x);
            return i >= 0 || i <= 1;
        }
        double i1 = (p.x - a.x) / (b.x - a.x), i2 = (p.y - a.y) / (b.y - a.y);
        return i1 == i2 && i1 >= 0 && i1 <= 1;
    }

    public static void main(String[] args) {
        Console.Config.includeLineNumber = true;
        Console.Config.manualConsoleWidth = 243;

        Ray2 r1 = new Ray2(Vec2.ZERO, Vec2.ONE, true, true);
        Ray2 r2 = new Ray2(new Vec2(1, -1), Vec2.Y);
        Line2 l = new Line2(Vec2.X, Vec2.Y);
        Circle c = new Circle(new Vec2(2, 2), Math.sqrt(2), 0, false);
        Rect rt = new Rect(Vec2.ZERO, new Vec2(4, 2), 0, true);
        Rect rtr = new Rect(Vec2.ZERO, new Vec2(4, 2), 90);

        //Console.info(Raycast.calc(r1, new Collider[] {  }, 10));

        Console.info(new Line2(Vec2.ZERO, Vec2.Y).contains(new Vec2(-0.1, 0)));

        Console.info(rt.coll(new Ray2(new Vec2(3, 0), new Vec2(-1, 0))));

        FastMath.atan2(1, 1);

        Stopwatch watch = new Stopwatch().start();
        for(int i=0; i<10000000; i++) {
            Raycast.calc(new Ray2(new Vec2(1.1, 2.3), new Vec2(-3.5, -4.7)), new Collider[] {l, c, rt, rtr, r2}, 100);
        }
        Console.map("Time", watch.stop().getPassedNanos());
    }
}
//  670367700
// 9211927800
