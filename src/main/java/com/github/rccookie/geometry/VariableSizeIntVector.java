package com.github.rccookie.geometry;

class VariableSizeIntVector extends AbstractIntVector<VariableSizeIntVector> {

    private static final long serialVersionUID = 7925371843942953657L;

    public VariableSizeIntVector(int... coordinates) {
        super(coordinates);
    }

    @Override
    public VariableSizeIntVector clone() {
        return new VariableSizeIntVector(toArray());
    }
}
