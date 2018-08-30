package com.waes.scalableweb.enums;


/**
 * Shows to side of the parameter
 * diff param1(left) param2(right)
 *
 * @author ttdduman
 */
public enum Side {
    LEFT("left"), RIGHT("right");

    private String value;

    Side(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
