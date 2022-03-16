package com.letscode.starwarsapi.models.unums;

public enum ItemPointEnum {
    WEAPON(4),
    AMMO(3),
    WATER(2),
    FOOD(1);

    int point;

    private ItemPointEnum(int point){
        this.point = point;
    }

    public int getPoint(){
        return point;
    }
}