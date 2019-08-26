package com.ragul.adsplayapi.util;

public enum AdsType {


    BRONZE(1),
    SILVER(2),
    GOLD(3);

    private final int type;

    AdsType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}

