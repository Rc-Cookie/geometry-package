package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.JsonCtor;
import com.github.rccookie.json.JsonObject;

public class Coll2 extends Coll<Vec2> {

    public final float lI;

    @JsonCtor({"rI", "lI", "sqrL"})
    public Coll2(float rI, float lI, float sqrL) {
        super(rI, sqrL);
        this.lI = lI;
    }

    @Override
    public String toString() {
        return "Coll{" +
                "rI=" + rI +
                ", lI=" + lI +
                ", sqrL=" + sqrL +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Coll2)) return false;
        Coll2 c = (Coll2) o;
        return c.rI == rI && c.lI == lI && c.sqrL == sqrL;
    }

    @Override
    public int hashCode() {
        return 11 * (11 * Double.hashCode(rI) + Double.hashCode(lI)) + Double.hashCode(sqrL);
    }

    @Override
    public Object toJson() {
        return new JsonObject("rI", rI, "lI", lI, "sqrL", sqrL);
    }
}
