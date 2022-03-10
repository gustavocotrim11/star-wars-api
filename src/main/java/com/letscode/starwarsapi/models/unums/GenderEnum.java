package com.letscode.starwarsapi.models.unums;

public enum GenderEnum {

    MALE("male"),
    FEMALE("female");

    private String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public static GenderEnum fromString(String gender) {
        for (GenderEnum value : GenderEnum.values()) {
            if (value.gender.equalsIgnoreCase(gender)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No constant with text " + gender + " found");
    }

}
