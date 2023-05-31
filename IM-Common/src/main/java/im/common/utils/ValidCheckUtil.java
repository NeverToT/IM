package im.common.utils;

import java.util.regex.Pattern;

/**
 * 校验手机号，邮箱，密码合法工具
 */
public class ValidCheckUtil {
    private static final String PHONE_NUMBER_PATTERN = "^1[0-9]{10}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    /**
     * 手机号校验
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return Pattern.matches(PHONE_NUMBER_PATTERN, phoneNumber);
    }
    /**
     * 邮箱校验
     */
    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    /**
     * 密码校验，必须大于等于8位数，有英文有数字
     */
    public static boolean isValidPassword(String password) {
        // 密码长度必须大于等于8位
        if (password.length() < 8) {
            return false;
        }
        // 密码必须包含至少一个英文字母和一个数字
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }

            // 如果同时包含字母和数字，则校验通过
            if (hasLetter && hasDigit) {
                return true;
            }
        }

        return false;
    }
}
