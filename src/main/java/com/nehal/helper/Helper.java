package com.nehal.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static Integer parseIntegerWithSuffixString(String input) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(input);
        while(m.find()) {
            return Integer.parseInt(m.group());
        }
        return 0;
    }

    public static String parseStringWithPrefixInt(String input) {
        Integer intPrefix = parseIntegerWithSuffixString(input);
        if(intPrefix > 0) {
            return input.substring(intPrefix.toString().length());
        } else {
            return input;
        }
    }
}
