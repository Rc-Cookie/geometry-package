package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.Json;
import com.github.rccookie.json.JsonCtor;
import com.github.rccookie.json.JsonSerializable;

public class Mat2x3 extends Mat<Vec2, Vec3> implements JsonSerializable {

    public Mat2x3() {
        super(Vec2.ZERO, Vec3.ZERO);
    }

    @JsonCtor
    public Mat2x3(Vec2[] r) {
        super(Vec2.ZERO, Vec3.ZERO, r);
    }

    @Override
    public Object toJson() {
        return Json.extractJson(r);
    }
}
