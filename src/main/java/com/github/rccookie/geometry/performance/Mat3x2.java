package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.Json;
import com.github.rccookie.json.JsonCtor;
import com.github.rccookie.json.JsonSerializable;

public class Mat3x2 extends Mat<Vec3, Vec2> implements JsonSerializable {

    public Mat3x2() {
        super(Vec3.ZERO, Vec2.ZERO);
    }

    @JsonCtor
    public Mat3x2(Vec3[] r) {
        super(Vec3.ZERO, Vec2.ZERO, r);
    }

    @Override
    public Object toJson() {
        return Json.extractJson(r);
    }
}
