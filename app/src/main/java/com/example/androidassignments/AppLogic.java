package com.example.androidassignments;

import java.util.regex.Pattern;

public class AppLogic {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$");

    // True when the text looks like a valid email address.
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    // True when the password is missing or empty.
    public static boolean isPasswordEmpty(String password) {
        return password == null || password.isEmpty();
    }

    // Even rows use the incoming layout, odd rows use the outgoing layout.
    public static boolean isIncomingRow(int position) {
        return position % 2 == 0;
    }
}