package com.mulan.mulan_auto.service.customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static void validateUsername(String username) {
        if (username.length() < 6 || username.length() > 30) {
            throw new RuntimeException("Tên người dùng phải có từ 6 đến 30 ký tự");
        }
    }

    public static void validatePassword(String password) {
        if (password.length() < 6 || password.length() > 30) {
            throw new RuntimeException("Mật khẩu phải có từ 6 đến 30 ký tự");
        }
    }

    public static void validatePhone(String phone) {
        if (phone.length() < 10 || phone.length() > 11) {
            throw new RuntimeException("Số điện thoại phải có từ 10 đến 11 ký tự");
        }
    }

    public static void validateEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new RuntimeException("Email không hợp lệ");
        }
    }
}