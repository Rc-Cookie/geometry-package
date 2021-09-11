package com.github.rccookie.geometry;

public class IntVector1D extends AbstractIntVector<IntVector1D> {

    private static final long serialVersionUID = 1119286347023474308L;

    public IntVector1D(int x) {
        super(x);
    }

    @Override
    public IntVector1D clone() {
        return new IntVector1D(x());
    }

    @Override
    public IntVector1D invert() {
        return setX(-x());
    }
    
    @Override
    public int size() {
        return 1;
    }

    @Override
    public int[] toArray() {
        return new int[] { x() };
    }

    @Override
    public IntVector1D setY(int y) throws UnsupportedOperationException, DimensionOutOfBoundsException {
        throw new DimensionOutOfBoundsException(Y, 1);
    }

    @Override
    public int y() {
        return 0;
    }

    @Override
    public String toString() {
        return "[" + x() + "]";
    }

    @Override
    public Vector1D getVector() {
        return new Vector1D(x());
    }
}
