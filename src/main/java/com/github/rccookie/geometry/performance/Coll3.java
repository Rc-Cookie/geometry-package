package com.github.rccookie.geometry.performance;

import java.util.Objects;

import com.github.rccookie.json.JsonCtor;
import com.github.rccookie.json.JsonObject;

public class Coll3 extends Coll<Vec3> {

    public final float tI1, tI2;

    @JsonCtor({"rI", "tI1", "tI2", "sqrL"})
    public Coll3(float rI, float tI1, float tI2, float sqrL) {
        super(rI, sqrL);
        this.tI1 = tI1;
        this.tI2 = tI2;
    }

    @Override
    public String toString() {
        return "Coll3{" +
                "rI=" + rI +
                ", tI1=" + tI1 +
                ", tI2=" + tI2 +
                ", sqrL=" + sqrL +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Coll3 coll3 = (Coll3) o;
        return Float.compare(coll3.tI1, tI1) == 0 && Float.compare(coll3.tI2, tI2) == 0
                && Float.compare(coll3.rI, rI) == 0 && Float.compare(coll3.sqrL, sqrL) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rI, tI1, tI2, sqrL);
    }

    @Override
    public Object toJson() {
        return new JsonObject("rI", rI, "tI1", tI1, "tI2", tI2, "sqrL", sqrL);
    }
}
