package com.byf.interview.juc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum CountryEnum {
    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"赵"),
    FIVE(5,"魏"),
    SIX(6,"韩");
    @Getter private Integer countryCode;
    @Getter private String countryMessage;

    public static CountryEnum iteratorEnums(int i){
        CountryEnum[] arrays = CountryEnum.values();
        for (CountryEnum countryEnum : arrays){
            if (i == countryEnum.countryCode){
                return countryEnum;
            }
        }
        return null;
    }
}
