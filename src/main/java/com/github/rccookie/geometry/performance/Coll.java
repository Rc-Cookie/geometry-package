package com.github.rccookie.geometry.performance;

public class Coll {

    public final double rI;
    public final double eI;
    public final double sqrL;

    public Coll(double rI, double eI, double sqrL) {
        this.rI = rI;
        this.eI = eI;
        this.sqrL = sqrL;
    }

    @Override
    public String toString() {
        return "Coll{" +
                "rI=" + rI +
                ", eI=" + eI +
                ", sqrL=" + sqrL +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Coll)) return false;
        Coll c = (Coll) o;
        return c.rI == rI && c.eI == eI && c.sqrL == sqrL;
    }

    @Override
    public int hashCode() {
        return 11 * (11 * Double.hashCode(rI) + Double.hashCode(eI)) + Double.hashCode(sqrL);
    }
}
