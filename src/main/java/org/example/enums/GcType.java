package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GcType {
    G1(1),
    ZGC(2) {
        @Override
        public String getName() {
            return "zgc";
        }
    },
    ;

    public String getName() {
        return this.name();
    }

    private int value;

    public static GcType fromValue(int i) {
        for (GcType t : values()) {
            if (t.value == i) {
                return t;
            }
        }
        return null;
    }
}
