package com.github.rccookie.geometry.performance;

import com.github.rccookie.json.Json;
import com.github.rccookie.json.JsonCtor;
import com.github.rccookie.json.JsonSerializable;

public class Mat2x2 extends Mat<Vec2, Vec2> implements JsonSerializable {

    public Mat2x2() {
        super(Vec2.ZERO, Vec2.ZERO);
    }

    @JsonCtor
    public Mat2x2(Vec2[] r) {
        super(Vec2.ZERO, Vec2.ZERO, r);
    }

    @Override
    public Object toJson() {
        return Json.extractJson(r);
    }
}
