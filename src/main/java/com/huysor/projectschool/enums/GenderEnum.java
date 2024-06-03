package com.huysor.projectschool.enums;



public enum GenderEnum {
    MALE,
    FEMALE,
    OTHER;
    public static GenderEnum fromValue(String value) {
        try {
            return GenderEnum.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid gender value: " + value);
        }
    }
}
