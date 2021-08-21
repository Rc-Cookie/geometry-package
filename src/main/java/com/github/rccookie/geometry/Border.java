package com.github.rccookie.geometry;

public interface Border {

    /**
     * @return Index 0: index on border, index 1: index on ray
     */
    double[] rayIntersection(Ray ray);

    Vector get(double index);

    Vector getNormal(double index);

    double length();
}
