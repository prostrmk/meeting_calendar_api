package com.itechart.webflux.web.util;

public class StringUtil {

    public static boolean equals(String first, String second) {
        if (first != null) {
            return first.equals(second);
        }
        return second == null;
    }

}
