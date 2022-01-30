package com.github.rccookie.geometry.performance;

import java.util.Arrays;

public class Mat<I extends Vec<I,?>, O extends Vec<O,?>> {

    public final I[] r;
    public final int w, h;

    private final I i;
    private final O o;

    @SuppressWarnings("unchecked")
    public Mat(I i, O o) {
        w = i.size();
        h = o.size();
        this.i = i;
        this.o = o;

        r = (I[]) new Vec[h];
        for(int j=0; j<h; j++)
            r[j] = i.clone().setZero();
    }

    public Mat(I i, O o, I[] r) {
        w = i.size();
        h = o.size();
        this.i = i;
        this.o = o;
        this.r = r;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Mat)) return false;
        return Arrays.equals(r, ((Mat<?, ?>)o).r);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(r);
    }

    @Override
    public String toString() {
        return Arrays.toString(r);
    }

    @SuppressWarnings("deprecation")
    public float get(int x, int y) {
        return r[y].getDim(x);
    }

    @SuppressWarnings("deprecation")
    public void set(int x, int y, float v) {
        r[y].setDim(x, v);
    }

    @SuppressWarnings("deprecation")
    public O apply(I i) {
        O o = this.o.clone();
        for(int j=0; j<h; j++)
            o.setDim(j, r[j].dot(i));
        return o;
    }

    @SuppressWarnings("deprecation")
    public <I2 extends Vec<I2,?>> Mat<I2, O> multiply(Mat<I2,I> m) {
        Mat<I2,O> out = new Mat<>(m.i, o);

        for(int y=0; y<out.h; y++) for(int x=0; x<out.w; x++) {
            float v = 0;
            for(int i=0; i<w; i++)
                v += r[y].getDim(i) * m.get(x, i);
            out.set(x, y, v);
        }
        return out;
    }

    public O newO() {
        return o.clone();
    }
}
