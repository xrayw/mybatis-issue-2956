package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.GcType;

@Getter
@Setter
public class User {
    private Long id;
    private String uu;
    private GcType type;
}
