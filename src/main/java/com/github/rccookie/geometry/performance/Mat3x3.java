package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.Json;
import com.github.rccookie.json.JsonCtor;
import com.github.rccookie.json.JsonSerializable;

public class Mat3x3 extends Mat<Vec3, Vec3> implements JsonSerializable {

    public Mat3x3() {
        super(Vec3.ZERO, Vec3.ZERO);
    }

    @JsonCtor
    public Mat3x3(Vec3[] r) {
        super(Vec3.ZERO, Vec3.ZERO, r);
    }

    @Override
    public Object toJson() {
        return Json.extractJson(r);
    }
}
