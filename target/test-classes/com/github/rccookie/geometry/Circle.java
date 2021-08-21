package com.github.rccookie.geometry;

import com.github.rccookie.util.Console;

public class Circle implements Border {

    private final Vector center;

    private final double radius;

    private final double rotation;

    private final boolean insideOut;


    public Circle(Vector center, double radius, double rotation) {
        this(center, radius, rotation, false);
    }

    public Circle(Vector center, double radius, double rotation, boolean insideOut) {
        this.center = Vectors.immutableVector(center.clone());
        this.radius = radius;
        this.rotation = rotation;
        this.insideOut = insideOut;
    }


    public double radius() {
        return radius;
    }

    public Vector center() {
        return Vectors.immutableVector(center);
    }

    public double rotation() {
        return rotation;
    }

    public boolean isInsideOut() {
        return insideOut;
    }


    @Override
    public double[] rayIntersection(Ray ray) {
        Double ri = rayIndex(ray);
        if(ri == null) return null;
        double ci = radius == 0 ? 0 : (Vector.between(center, ray.get(ri)).angle() - rotation) / 360 + 0.25;
        if(ci < 0) ci++;
        return new double[] {ci, ri};
    }

    private Double rayIndex(Ray ray) {
        Vector between = Vector.between(center, ray.root);

        double a = 2 * ray.direction.sqrAbs();
        double b = 2 * between.dot(ray.direction);
        double x = b * b - 2 * a * (between.sqrAbs() - radius * radius);

        if(x < 0) return null;
        x = Math.sqrt(x);

        if(insideOut) {
            double i2 = (-b + x)/a;
            return i2 < 0 ? null : i2;
        }
        double i1 = (-b - x)/a;
        return i1 < 0 ? null : i1;
    }

    @Override
    public Vector get(double index) {
        return Vector2D.angled(index * 360 - 90, radius).add(center);
    }

    @Override
    public Vector getNormal(double index) {
        return Vector2D.angled(index * 360 + (insideOut ? 90 : -90), radius);
    }

    @Override
    public double length() {
        return 2 * radius * Math.PI;
    }

    public static void main(String[] args) {
        Ray r = new Ray(Vector.of(2), Vector.of(-1));
        Circle c = new Circle(Vector.of(), 1, 0);

        Console.info(c.rayIntersection(r), r.get(c.rayIntersection(r)[1]), c.get(c.rayIntersection(r)[0]));
    }
}
