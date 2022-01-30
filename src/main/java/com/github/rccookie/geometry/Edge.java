package com.github.rccookie.geometry;

import java.util.Objects;

public class Edge implements Border {

    public final Vector start;
    public final Vector end;
    public final Vector connection;

    public Edge(Vector start, Vector end) {
        this.start = Vectors.immutableVector(start);
        this.end = Vectors.immutableVector(end);
        this.connection = Vectors.immutableVector(Vector.between(start, end));
    }



    @Override
    public double[] rayIntersection(Ray ray) {
        return Edge.intersection(this, ray);
    }

    @Override
    public Vector get(double index) {
        return start.added(connection.scaled(index));
    }

    @Override
    public Vector getNormal(double index) {
        return connection.get2D().rotated(-90);
    }

    @Override
    public double length() {
        return connection.abs();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Edge)) return false;
        return Objects.equals(start, ((Edge)obj).start) && Objects.equals(end, ((Edge)obj).end);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Edge from " + start + " to " + end + " (" + connection.abs() + " units)}";
    }



    public static double[] intersection(Edge e, Ray r) {
        double[] intersection = Ray.intersection(new Ray(e.start, e.connection), r);
        return (intersection == null || intersection[0] < 0 || intersection[0] > 1) ? null : intersection;
    }

    public static double[] intersection(Edge e, Edge f) {
        double[] intersection = Ray.intersection(new Ray(e.start, e.connection), new Ray(f.start, f.connection));
        return (intersection == null || intersection[0] < 0 || intersection[0] > 1 || intersection[1] < 0 || intersection[1] > 1) ? null : intersection;
    }

    public static Double intersection(Edge e, Vector p) {
        double posA = (p.x() - e.start.x()) / e.connection.x();
        return (posA >= 0 && posA <= 1 && (p.y() - e.start.y()) / e.connection.y() == posA) ? posA : null;
    }
}
