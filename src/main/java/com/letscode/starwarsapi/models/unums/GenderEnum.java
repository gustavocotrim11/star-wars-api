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
}
