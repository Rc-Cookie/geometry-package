package com.github.rccookie.geometry.performance;

/**
 * Describes a math function, typically defining from
 * {@code [0..1]} into {@code [0..1]}.
 */
@FunctionalInterface
public interface Interpolation {

    /**
     * Calculates the result for the given input.
     *
     * @param x The input value
     * @return The output value
     */
    float get(float x);

    /**
     * Calculates the result for the given input and maps
     * into the range {@code [min..max]}, assuming both input
     * and output are in the range {@code [0..1]}.
     *
     * @param x The input value
     * @param min The lower end of the output interval
     * @param max The higher end of the output interval
     * @return The output value mapped into the interval
     *         {@code min..max}
     */
    default float get(float x, float min, float max) {
        return min + get(x) * (max - min);
    }

    /**
     * Returns the composition of {@code i ° this}. This is
     * equivalent to calling {@code i} for the value of
     * this interpolation's result.
     *
     * @param i The other interpolation
     * @return An interpolation describing {@code i ° this}
     */
    default Interpolation composition(Interpolation i) {
        return x -> i.get(get(x));
    }

    /**
     * Returns the composition of {@code this ° i}. This is
     * equivalent to calling this interpolation for the value
     * of the result of {@code i}.
     *
     * @param i The other interpolation
     * @return An interpolation describing {@code this ° i}
     */
    default Interpolation iComposition(Interpolation i) {
        return x -> get(i.get(x));
    }



    /**
     * The identity interpolation, mapping each x to itself.
     * <p><code>x -> x</code>
     */
    Interpolation LINEAR = x -> x;

    /**
     * Maps the inverted input value to the output in the range
     * {@code [0..1]}.
     * <p><code>x -> 1-x</code>
     */
    Interpolation INVERT = x -> 1 - x;

    /**
     * Maps the square input value to the output.
     * <p><code>x -> x²</code>
     */
    Interpolation SQUARED = x -> x * x;

    /**
     * Maps the square root of the input to the output.
     * <p><code>x -> √x</code>
     */
    Interpolation SQRT = a -> (float) Math.sqrt(a);

    /**
     * Maps the input value to a smooth curve from {@code 0} to
     * {@code 1}.
     * <p><code>x -> x²(3-2x)</code>
     */
    Interpolation SMOOTH = x -> x * x * (3 - 2 * x);

    /**
     * Maps the input value to a smooth curve from {@code 0} to
     * {@code 1}. This curve is a bit slower than {@link #SMOOTH}.
     * <p><code>x -> x³(x(6x-15)+10)</code>
     */
    Interpolation FADE = x -> x * x * x * (x * (6 * x - 15) + 10);

    /**
     * Maps the input value to the sin value at that point so that
     * {@code 0} has the value {@code 0} and {@code 1} has the value
     * {@code 1}. {@code -1} would have the value {@code -1}.
     * <p><code>x -> sin(90x) | sin in degrees</code>
     */
    Interpolation SIN = x -> FastMath.sin(x * 90);

    /**
     * Maps the input value to the sin wave so that a whole sin
     * period has a length of {@code 1}.
     * <p><code>x -> 0.5+0.5sin(360x) | sin in degrees</code>
     */
    Interpolation SIN01 = x -> 0.5f + 0.5f * FastMath.sin(x * 360);

    /**
     * Maps the input to {@code e} raised to the power of the input
     * value.
     * <p><code>x -> exp(x)</code>
     */
    Interpolation EXP = a -> (float) Math.exp(a);
}
