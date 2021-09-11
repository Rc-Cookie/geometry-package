package com.github.rccookie.geometry;

import com.github.rccookie.util.Arguments;
import com.github.rccookie.util.Console;
import com.github.rccookie.util.Stopwatch;

import java.util.Arrays;
import java.util.Objects;

public final class Raycast {
    private Raycast() { }

    /**
     * Calculates a raycast from the given ray taking all given borders into consideration. This
     * method will never return null but rather a result describing a non-hit.
     * 
     * @param ray The ray cast
     * @param maxLength The maximum distance of the raycast. Has no performance impact
     * @param borders The borders that the ray may hit
     * @return A RaycastResult2D describing the outcome of the raycast
     * @throws NullPointerException If {@code ray} is {@code null}
     * @see Raycast2D
     */
    public static Raycast2D raycast2D(final Ray ray, double maxLength, final Iterable<Border> borders) {
        Arguments.checkNull(ray, "ray");
        Raycast2D closest = new Raycast2D(Double.NaN, Double.NaN, null, ray, maxLength);
        if(borders == null) return closest;

        for(Border border : borders) {
            Raycast2D thisResult = raycast2D(ray, border, maxLength);
            if(thisResult != null && thisResult.connection != null && (closest.connection == null || thisResult.connection.connection.sqrAbs() < closest.connection.connection.sqrAbs())) closest = thisResult;
        }

        return closest;
    }

    /**
     * Calculates a raycast from the given ray taking all given borders into consideration. This
     * method will never return null but rather a result describing a non-hit.
     * 
     * @param ray The ray cast
     * @param maxLength The maximum distance of the raycast. Has no performance impact
     * @param borders The borders that the ray may hit
     * @return A RaycastResult2D describing the outcome of the raycast
     * @throws NullPointerException If {@code ray} is {@code null}
     * @see Raycast2D
     */
    public static Raycast2D raycast2D(final Ray ray, double maxLength, final Border... borders) {
        return raycast2D(ray, maxLength, Arrays.asList(borders));
    }


    private static Raycast2D raycast2D(final Ray ray, final Border border, double maxLength) {
        double[] intersection = border.rayIntersection(ray);
        if(intersection == null || intersection[1] <= 0) return null;
        return new Raycast2D(intersection[0], intersection[1], border, ray, maxLength);
    }



    public static final class Raycast2D {

        /**
         * The edge that was hit by the ray. May be {@code null} if the hit did
         * not hit anything.
         */
        public final Border hitBorder;

        /**
         * A ray describing the actual ray starting at the root and ending at the
         * hit location. May be {@code null} if the ray did not hit anything.
         */
        public final Edge connection;

        /**
         * The ray that this raycast was based on.
         */
        public final Ray ray;

        private Double length = null;

        /**
         * The length of the ray from its root until the hit location. If the ray
         * did not hit anything, this will have the value of {@link Double#POSITIVE_INFINITY}
         */
        public double length() {
            return connection != null ? (length != null ? length : (length = connection.length())) : maxLength;
        }

        /**
         * The index of the edge where it was hit. If the ray did not hit anything
         * this will have the value of {@link Double#NaN}.
         */
        public final double borderIndex;

        /**
         * The index of the ray where it hit an edge. If the ray did not hit anything
         * this will have the value of {@link Double#POSITIVE_INFINITY}.
         */
        public final double rayIndex;

        /**
         * A vector describing the location of the ray hit. May be {@code null} if
         * the ray did not hit anything.
         */
        public final Vector hitLoc;

        /**
         * The root of the ray.
         */
        public final Vector root;

        private final double maxLength;

        private Raycast2D(double borderIndex, double rayIndex, Border hitBorder, Ray ray, double maxLength) {
            this.root = ray.root;
            this.ray = ray;
            this.hitBorder = hitBorder;
            this.borderIndex = borderIndex;
            this.rayIndex = rayIndex;
            hitLoc = hitBorder != null ? hitBorder.get(borderIndex) : null;
            connection = hitLoc != null ? new Edge(root, hitLoc) : null;
            this.maxLength = maxLength;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Raycast2D)) return false;
            Raycast2D o = (Raycast2D)obj;
            return Objects.equals(root, o.root) && Objects.equals(hitBorder, o.hitBorder) && borderIndex == o.borderIndex && rayIndex == o.rayIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(borderIndex, rayIndex, hitBorder, ray);
        }

        @Override
        public String toString() {
            return "Result{Border hit: " + hitBorder + ", location: " + hitLoc + ", length: " + length() + "}";
        }



        public static Raycast2D emptyResult(Ray ray, double length) {
            return new Raycast2D(Double.NaN, Double.NaN, null, ray, length);
        }
    }

    public static void main(String[] args) {
        Console.Config.includeLineNumber = true;
        Console.Config.manualConsoleWidth = 243;

        Ray r1 = new Ray(Vector.ZERO, Vector.ONE);
        Ray r2 = new Ray(Vector.of(1, -1), Vector.UNIT_Y);
        Edge e = new Edge(Vector.UNIT_X, Vector.UNIT_Y);
        Circle c = new Circle(Vector.of(2, 2), Math.sqrt(2), 0, false);
        Edge rt1 = new Edge(Vector.of(-2, -1), Vector.of(-2, 1)), rt2 = new Edge(Vector.of(-2, 1), Vector.of(2, 1));
        Edge rt3 = new Edge(Vector.of(2, 1), Vector.of(2, -1)), rt4 = new Edge(Vector.of(2, -1), Vector.of(-2, -1));
        Edge rtr1 = new Edge(Vector.of(1, -2), Vector.of(-1, -2)), rtr2 = new Edge(Vector.of(-1, -2), Vector.of(-1, 2));
        Edge rtr3 = new Edge(Vector.of(-1, 2), Vector.of(1, 2)), rtr4 = new Edge(Vector.of(1, 2), Vector.of(1, -2));

        Stopwatch watch = new Stopwatch().start();
        for(int i=0; i<10000000; i++) {
            com.github.rccookie.geometry.Raycast.raycast2D(new com.github.rccookie.geometry.Ray(Vector.of(1.1, 2.3), Vector.of(-3.5, -4.7)), 100, e, c, rt1, rt2, rt3, rt4, rtr1, rtr2, rtr3, rtr4, r2);
        }
        Console.map("Time", watch.stop().getPassedNanos());
    }
}
