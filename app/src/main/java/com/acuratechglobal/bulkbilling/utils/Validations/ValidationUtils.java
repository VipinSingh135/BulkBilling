package com.acuratechglobal.bulkbilling.utils.Validations;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

//    private static final String EMPTY_STRING = "";
//    private static final String EMPTY_STRING = "";
//    private static final String EMPTY_STRING = "";
//    private static final String EMPTY_STRING = "";
//    private static final String EMPTY_STRING = "";
//

    //validate for email
    static boolean isValidEmail(CharSequence target) {
        return target != null && Patterns.EMAIL_ADDRESS.matcher(target)
                .matches();
    }


    //validate for name
     static boolean isValidName(CharSequence target) {
        boolean check = false;
        if (Pattern.matches("[a-zA-Z]+", target)) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }


    //validate for username
    public static boolean isValidUserName(CharSequence target) {
        boolean check = false;
        if (Pattern.matches("^[a-zA-Z0-9._-]{2,25}$", target)) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    //validate for phone number
     static boolean isValidPhone(String phone) {
        boolean check = false;
        if (phone.length()>12){
            return false;
        }
//        String expression = "[\\+]^([0-9]|\\(\\d{1,2}\\))[0-9]{6,15}$";
//        String expression = "^\\+([0-9]|\\(\\d{1,2}\\))[0-9]{6,15}$";
        String expression = "^([0-9]|\\(\\d{1,2}\\))[0-9]{6,15}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

     static boolean isValidPassword(String newPassword) {
        if (newPassword.length()<6){
            return false;
        }else
            return true;
    }

    static boolean isValidOTP(String otp) {
        if (otp.length()!=6){
            return false;
        }else
            return true;
    }
}
