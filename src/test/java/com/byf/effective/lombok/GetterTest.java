package com.byf.effective.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Getter.AnyAnnotation;

import javax.validation.constraints.NotNull;

public class GetterTest {

    @Getter(
            lazy = true
    )
    private final String field1 = "Hello";

    @Getter(
            value = AccessLevel.PRIVATE
    )
    private String field2;


    @Getter
    private String field3;
}
