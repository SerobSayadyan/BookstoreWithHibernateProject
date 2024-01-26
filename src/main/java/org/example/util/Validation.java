package org.example.util;

import java.util.regex.Pattern;

public class Validation {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n";
    private static final String PHONE_PATTERN = "^\\+(?:[0-9] ?){6,14}[0-9]$\n";

    public static boolean isEmailValid(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    public static boolean isPhoneNumberValid(String phone) {
        return Pattern.matches(PHONE_PATTERN, phone);
    }


}
